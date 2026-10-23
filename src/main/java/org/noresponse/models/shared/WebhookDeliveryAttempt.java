package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class WebhookDeliveryAttempt {
    /**
     * The event id the receiver saw as NR-Webhook-Id and as the body's id. Replay it with POST
     * /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay.
     */
    @JsonProperty("event_id")
    private String eventId;

    @JsonProperty("event_type")
    private EventType eventType;

    /**
     * Which attempt of this delivery this was: 1 for the first send, up to 9 with retries. A replay counts
     * its own attempts from 1.
     */
    @JsonProperty("attempt")
    private long attempt;

    /**
     * true for an attempt of a replay you asked for.
     */
    @JsonProperty("replay")
    private boolean replay;

    /**
     * delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.
     */
    @JsonProperty("status")
    private WebhookDeliveryAttemptStatus status;

    /**
     * The HTTP status the endpoint answered. Absent when no response was read.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_code")
    private Long responseCode;

    /**
     * Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an
     * address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are
     * not followed) or another non-2xx status. Absent for a delivered attempt.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("failure")
    private Failure failure;

    /**
     * Milliseconds from sending the request to the answer or the failure.
     */
    @JsonProperty("latency_ms")
    private long latencyMs;

    /**
     * When the request was sent. Its NR-Webhook-Timestamp is this time in Unix seconds.
     */
    @JsonProperty("attempted_at")
    private String attemptedAt;

    @JsonCreator
    public WebhookDeliveryAttempt(
            @JsonProperty("event_id") @Nonnull String eventId,
            @JsonProperty("event_type") @Nonnull EventType eventType,
            @JsonProperty("attempt") long attempt,
            @JsonProperty("replay") boolean replay,
            @JsonProperty("status") @Nonnull WebhookDeliveryAttemptStatus status,
            @JsonProperty("response_code") @Nullable Long responseCode,
            @JsonProperty("failure") @Nullable Failure failure,
            @JsonProperty("latency_ms") long latencyMs,
            @JsonProperty("attempted_at") @Nonnull String attemptedAt) {
        this.eventId =
                Optional.ofNullable(eventId).orElseThrow(() -> new IllegalArgumentException("eventId cannot be null"));
        this.eventType = Optional.ofNullable(eventType)
                .orElseThrow(() -> new IllegalArgumentException("eventType cannot be null"));
        this.attempt = attempt;
        this.replay = replay;
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.responseCode = responseCode;
        this.failure = failure;
        this.latencyMs = latencyMs;
        this.attemptedAt = Optional.ofNullable(attemptedAt)
                .orElseThrow(() -> new IllegalArgumentException("attemptedAt cannot be null"));
    }

    public WebhookDeliveryAttempt(
            @Nonnull String eventId,
            @Nonnull EventType eventType,
            long attempt,
            boolean replay,
            @Nonnull WebhookDeliveryAttemptStatus status,
            long latencyMs,
            @Nonnull String attemptedAt) {
        this(eventId, eventType, attempt, replay, status, null, null, latencyMs, attemptedAt);
    }

    /**
     * The event id the receiver saw as NR-Webhook-Id and as the body's id. Replay it with POST
     * /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay.
     */
    public String eventId() {
        return this.eventId;
    }

    public EventType eventType() {
        return this.eventType;
    }

    /**
     * Which attempt of this delivery this was: 1 for the first send, up to 9 with retries. A replay counts
     * its own attempts from 1.
     */
    public long attempt() {
        return this.attempt;
    }

    /**
     * true for an attempt of a replay you asked for.
     */
    public boolean replay() {
        return this.replay;
    }

    /**
     * delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.
     */
    public WebhookDeliveryAttemptStatus status() {
        return this.status;
    }

    /**
     * The HTTP status the endpoint answered. Absent when no response was read.
     */
    public Optional<Long> responseCode() {
        return Optional.ofNullable(this.responseCode);
    }

    /**
     * Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an
     * address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are
     * not followed) or another non-2xx status. Absent for a delivered attempt.
     */
    public Optional<Failure> failure() {
        return Optional.ofNullable(this.failure);
    }

    /**
     * Milliseconds from sending the request to the answer or the failure.
     */
    public long latencyMs() {
        return this.latencyMs;
    }

    /**
     * When the request was sent. Its NR-Webhook-Timestamp is this time in Unix seconds.
     */
    public String attemptedAt() {
        return this.attemptedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event id the receiver saw as NR-Webhook-Id and as the body's id. Replay it with POST
     * /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay.
     */
    public WebhookDeliveryAttempt withEventId(@Nonnull String eventId) {
        this.eventId = Utils.checkNotNull(eventId, "eventId");
        return this;
    }

    public WebhookDeliveryAttempt withEventType(@Nonnull EventType eventType) {
        this.eventType = Utils.checkNotNull(eventType, "eventType");
        return this;
    }

    /**
     * Which attempt of this delivery this was: 1 for the first send, up to 9 with retries. A replay counts
     * its own attempts from 1.
     */
    public WebhookDeliveryAttempt withAttempt(long attempt) {
        this.attempt = attempt;
        return this;
    }

    /**
     * true for an attempt of a replay you asked for.
     */
    public WebhookDeliveryAttempt withReplay(boolean replay) {
        this.replay = replay;
        return this;
    }

    /**
     * delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.
     */
    public WebhookDeliveryAttempt withStatus(@Nonnull WebhookDeliveryAttemptStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The HTTP status the endpoint answered. Absent when no response was read.
     */
    public WebhookDeliveryAttempt withResponseCode(@Nullable Long responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    /**
     * Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an
     * address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are
     * not followed) or another non-2xx status. Absent for a delivered attempt.
     */
    public WebhookDeliveryAttempt withFailure(@Nullable Failure failure) {
        this.failure = failure;
        return this;
    }

    /**
     * Milliseconds from sending the request to the answer or the failure.
     */
    public WebhookDeliveryAttempt withLatencyMs(long latencyMs) {
        this.latencyMs = latencyMs;
        return this;
    }

    /**
     * When the request was sent. Its NR-Webhook-Timestamp is this time in Unix seconds.
     */
    public WebhookDeliveryAttempt withAttemptedAt(@Nonnull String attemptedAt) {
        this.attemptedAt = Utils.checkNotNull(attemptedAt, "attemptedAt");
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebhookDeliveryAttempt other = (WebhookDeliveryAttempt) o;
        return Utils.enhancedDeepEquals(this.eventId, other.eventId)
                && Utils.enhancedDeepEquals(this.eventType, other.eventType)
                && Utils.enhancedDeepEquals(this.attempt, other.attempt)
                && Utils.enhancedDeepEquals(this.replay, other.replay)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.responseCode, other.responseCode)
                && Utils.enhancedDeepEquals(this.failure, other.failure)
                && Utils.enhancedDeepEquals(this.latencyMs, other.latencyMs)
                && Utils.enhancedDeepEquals(this.attemptedAt, other.attemptedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                eventId, eventType, attempt, replay, status, responseCode, failure, latencyMs, attemptedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookDeliveryAttempt.class,
                "eventId",
                eventId,
                "eventType",
                eventType,
                "attempt",
                attempt,
                "replay",
                replay,
                "status",
                status,
                "responseCode",
                responseCode,
                "failure",
                failure,
                "latencyMs",
                latencyMs,
                "attemptedAt",
                attemptedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String eventId;

        private EventType eventType;

        private long attempt;

        private boolean replay;

        private WebhookDeliveryAttemptStatus status;

        private Long responseCode;

        private Failure failure;

        private long latencyMs;

        private String attemptedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The event id the receiver saw as NR-Webhook-Id and as the body's id. Replay it with POST
         * /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay.
         */
        public Builder eventId(@Nonnull String eventId) {
            this.eventId = Utils.checkNotNull(eventId, "eventId");
            return this;
        }

        public Builder eventType(@Nonnull EventType eventType) {
            this.eventType = Utils.checkNotNull(eventType, "eventType");
            return this;
        }

        /**
         * Which attempt of this delivery this was: 1 for the first send, up to 9 with retries. A replay counts
         * its own attempts from 1.
         */
        public Builder attempt(long attempt) {
            this.attempt = attempt;
            return this;
        }

        /**
         * true for an attempt of a replay you asked for.
         */
        public Builder replay(boolean replay) {
            this.replay = replay;
            return this;
        }

        /**
         * delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.
         */
        public Builder status(@Nonnull WebhookDeliveryAttemptStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * The HTTP status the endpoint answered. Absent when no response was read.
         */
        public Builder responseCode(@Nullable Long responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        /**
         * Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an
         * address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are
         * not followed) or another non-2xx status. Absent for a delivered attempt.
         */
        public Builder failure(@Nullable Failure failure) {
            this.failure = failure;
            return this;
        }

        /**
         * Milliseconds from sending the request to the answer or the failure.
         */
        public Builder latencyMs(long latencyMs) {
            this.latencyMs = latencyMs;
            return this;
        }

        /**
         * When the request was sent. Its NR-Webhook-Timestamp is this time in Unix seconds.
         */
        public Builder attemptedAt(@Nonnull String attemptedAt) {
            this.attemptedAt = Utils.checkNotNull(attemptedAt, "attemptedAt");
            return this;
        }

        public WebhookDeliveryAttempt build() {
            return new WebhookDeliveryAttempt(
                    eventId, eventType, attempt, replay, status, responseCode, failure, latencyMs, attemptedAt);
        }
    }
}
