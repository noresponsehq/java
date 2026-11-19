package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningCoverageTiming
 *
 * <p>How long this search's latest attempt took, stage by stage. started_at minus the investigation's
 * creation is the time spent queued or retrying.
 */
public class ScreeningCoverageTiming {
    /**
     * When the attempt that wrote this result started.
     */
    @JsonProperty("started_at")
    private String startedAt;

    /**
     * Milliseconds from started_at to the end of the last stage listed.
     */
    @JsonProperty("total_ms")
    private long totalMs;

    /**
     * Stages finished so far, in order. The result first written lists stages up to written; the settled
     * result lists them all.
     */
    @JsonProperty("stages")
    private List<StageTime> stages;

    @JsonCreator
    public ScreeningCoverageTiming(
            @JsonProperty("started_at") @Nonnull String startedAt,
            @JsonProperty("total_ms") long totalMs,
            @JsonProperty("stages") @Nonnull List<StageTime> stages) {
        this.startedAt = Optional.ofNullable(startedAt)
                .orElseThrow(() -> new IllegalArgumentException("startedAt cannot be null"));
        this.totalMs = totalMs;
        this.stages =
                Optional.ofNullable(stages).orElseThrow(() -> new IllegalArgumentException("stages cannot be null"));
    }

    /**
     * When the attempt that wrote this result started.
     */
    public String startedAt() {
        return this.startedAt;
    }

    /**
     * Milliseconds from started_at to the end of the last stage listed.
     */
    public long totalMs() {
        return this.totalMs;
    }

    /**
     * Stages finished so far, in order. The result first written lists stages up to written; the settled
     * result lists them all.
     */
    public List<StageTime> stages() {
        return this.stages;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * When the attempt that wrote this result started.
     */
    public ScreeningCoverageTiming withStartedAt(@Nonnull String startedAt) {
        this.startedAt = Utils.checkNotNull(startedAt, "startedAt");
        return this;
    }

    /**
     * Milliseconds from started_at to the end of the last stage listed.
     */
    public ScreeningCoverageTiming withTotalMs(long totalMs) {
        this.totalMs = totalMs;
        return this;
    }

    /**
     * Stages finished so far, in order. The result first written lists stages up to written; the settled
     * result lists them all.
     */
    public ScreeningCoverageTiming withStages(@Nonnull List<StageTime> stages) {
        this.stages = Utils.checkNotNull(stages, "stages");
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
        ScreeningCoverageTiming other = (ScreeningCoverageTiming) o;
        return Utils.enhancedDeepEquals(this.startedAt, other.startedAt)
                && Utils.enhancedDeepEquals(this.totalMs, other.totalMs)
                && Utils.enhancedDeepEquals(this.stages, other.stages);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(startedAt, totalMs, stages);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningCoverageTiming.class, "startedAt", startedAt, "totalMs", totalMs, "stages", stages);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String startedAt;

        private long totalMs;

        private List<StageTime> stages;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * When the attempt that wrote this result started.
         */
        public Builder startedAt(@Nonnull String startedAt) {
            this.startedAt = Utils.checkNotNull(startedAt, "startedAt");
            return this;
        }

        /**
         * Milliseconds from started_at to the end of the last stage listed.
         */
        public Builder totalMs(long totalMs) {
            this.totalMs = totalMs;
            return this;
        }

        /**
         * Stages finished so far, in order. The result first written lists stages up to written; the settled
         * result lists them all.
         */
        public Builder stages(@Nonnull List<StageTime> stages) {
            this.stages = Utils.checkNotNull(stages, "stages");
            return this;
        }

        public ScreeningCoverageTiming build() {
            return new ScreeningCoverageTiming(startedAt, totalMs, stages);
        }
    }
}
