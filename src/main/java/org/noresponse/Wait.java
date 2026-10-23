package org.noresponse;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.LongSupplier;
import org.noresponse.models.operations.GetAssessmentResponse;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.Status;

/** Wait for an assessment to complete. Hand-written beside the generated client. */
public final class Wait {
    /** How long {@link #wait(NoResponse, String)} polls before giving up. */
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    /** Sleeps for the given number of milliseconds. */
    @FunctionalInterface
    public interface Sleeper {
        void sleep(long millis) throws InterruptedException;
    }

    private Wait() {
    }

    /**
     * Poll {@code assessments().get} until the assessment's status is complete,
     * sleeping for the Retry-After the API sends (at least 1 s) between polls.
     * After 30 seconds a partial answer is returned as it stands, its status
     * still {@code PARTIAL}.
     *
     * @throws WaitTimeoutException once 30 seconds have passed while the assessment is still pending
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    public static Assessment wait(NoResponse client, String assessmentId) throws InterruptedException {
        return wait(client, assessmentId, DEFAULT_TIMEOUT, Thread::sleep, System::nanoTime);
    }

    /**
     * Poll with an explicit timeout, sleeper and nanosecond clock. When the
     * timeout passes, a partial answer is returned as it stands.
     *
     * @throws WaitTimeoutException once the timeout has passed while the assessment is still pending
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    public static Assessment wait(NoResponse client, String assessmentId, Duration timeout, Sleeper sleeper,
            LongSupplier nanoClock) throws InterruptedException {
        long deadline = nanoClock.getAsLong() + timeout.toNanos();
        while (true) {
            GetAssessmentResponse response = client.assessments().get(assessmentId);
            Assessment assessment = response.assessment().orElseThrow(
                    () -> new IllegalStateException("The API answered without an assessment"));
            if (Status.COMPLETE.equals(assessment.status())) {
                return assessment;
            }
            long delay = retryAfterMillis(response.headers());
            if (nanoClock.getAsLong() + Duration.ofMillis(delay).toNanos() > deadline) {
                if (Status.PARTIAL.equals(assessment.status())) {
                    return assessment;
                }
                throw new WaitTimeoutException(assessmentId, assessment);
            }
            sleeper.sleep(delay);
        }
    }

    private static long retryAfterMillis(Map<String, List<String>> headers) {
        for (Map.Entry<String, List<String>> header : headers.entrySet()) {
            if ("retry-after".equalsIgnoreCase(header.getKey()) && !header.getValue().isEmpty()) {
                try {
                    return Math.max(1000L, (long) (Double.parseDouble(header.getValue().get(0)) * 1000));
                } catch (NumberFormatException e) {
                    break;
                }
            }
        }
        return 1000L;
    }
}
