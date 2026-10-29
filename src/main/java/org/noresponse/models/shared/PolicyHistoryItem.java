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

public class PolicyHistoryItem {
    /**
     * One environment's settings: how long its assessments are kept, and for each flow the risk thresholds
     * that decide it and the depth a request that names none is served at, and the sources it refuses.
     * Live and test each have their own. A flow without thresholds of its own uses account_creation's
     * (inherits_account_creation).
     *
     * <p>Every change is recorded in the audit log as settings.updated, with who made it.
     */
    @JsonProperty("settings")
    private Settings settings;

    /**
     * Capture time, not a backfilled activation time. Absent only for the current configuration before its
     * first capture.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("recorded_at")
    private String recordedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("change_reason")
    private String changeReason;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("restored_from")
    private Long restoredFrom;

    /**
     * Immutable publication provenance remains in policy history after evaluation evidence expires.
     * SHA-256 values identify the frozen manifest, evaluator semantic input, cohort, and canonical result
     * separately.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("evaluation")
    private Publication evaluation;

    @JsonCreator
    public PolicyHistoryItem(
            @JsonProperty("settings") @Nonnull Settings settings,
            @JsonProperty("recorded_at") @Nullable String recordedAt,
            @JsonProperty("change_reason") @Nullable String changeReason,
            @JsonProperty("restored_from") @Nullable Long restoredFrom,
            @JsonProperty("evaluation") @Nullable Publication evaluation) {
        this.settings = Optional.ofNullable(settings)
                .orElseThrow(() -> new IllegalArgumentException("settings cannot be null"));
        this.recordedAt = recordedAt;
        this.changeReason = changeReason;
        this.restoredFrom = restoredFrom;
        this.evaluation = evaluation;
    }

    public PolicyHistoryItem(@Nonnull Settings settings) {
        this(settings, null, null, null, null);
    }

    /**
     * One environment's settings: how long its assessments are kept, and for each flow the risk thresholds
     * that decide it and the depth a request that names none is served at, and the sources it refuses.
     * Live and test each have their own. A flow without thresholds of its own uses account_creation's
     * (inherits_account_creation).
     *
     * <p>Every change is recorded in the audit log as settings.updated, with who made it.
     */
    public Settings settings() {
        return this.settings;
    }

    /**
     * Capture time, not a backfilled activation time. Absent only for the current configuration before its
     * first capture.
     */
    public Optional<String> recordedAt() {
        return Optional.ofNullable(this.recordedAt);
    }

    public Optional<String> changeReason() {
        return Optional.ofNullable(this.changeReason);
    }

    public Optional<Long> restoredFrom() {
        return Optional.ofNullable(this.restoredFrom);
    }

    /**
     * Immutable publication provenance remains in policy history after evaluation evidence expires.
     * SHA-256 values identify the frozen manifest, evaluator semantic input, cohort, and canonical result
     * separately.
     */
    public Optional<Publication> evaluation() {
        return Optional.ofNullable(this.evaluation);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One environment's settings: how long its assessments are kept, and for each flow the risk thresholds
     * that decide it and the depth a request that names none is served at, and the sources it refuses.
     * Live and test each have their own. A flow without thresholds of its own uses account_creation's
     * (inherits_account_creation).
     *
     * <p>Every change is recorded in the audit log as settings.updated, with who made it.
     */
    public PolicyHistoryItem withSettings(@Nonnull Settings settings) {
        this.settings = Utils.checkNotNull(settings, "settings");
        return this;
    }

    /**
     * Capture time, not a backfilled activation time. Absent only for the current configuration before its
     * first capture.
     */
    public PolicyHistoryItem withRecordedAt(@Nullable String recordedAt) {
        this.recordedAt = recordedAt;
        return this;
    }

    public PolicyHistoryItem withChangeReason(@Nullable String changeReason) {
        this.changeReason = changeReason;
        return this;
    }

    public PolicyHistoryItem withRestoredFrom(@Nullable Long restoredFrom) {
        this.restoredFrom = restoredFrom;
        return this;
    }

    /**
     * Immutable publication provenance remains in policy history after evaluation evidence expires.
     * SHA-256 values identify the frozen manifest, evaluator semantic input, cohort, and canonical result
     * separately.
     */
    public PolicyHistoryItem withEvaluation(@Nullable Publication evaluation) {
        this.evaluation = evaluation;
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
        PolicyHistoryItem other = (PolicyHistoryItem) o;
        return Utils.enhancedDeepEquals(this.settings, other.settings)
                && Utils.enhancedDeepEquals(this.recordedAt, other.recordedAt)
                && Utils.enhancedDeepEquals(this.changeReason, other.changeReason)
                && Utils.enhancedDeepEquals(this.restoredFrom, other.restoredFrom)
                && Utils.enhancedDeepEquals(this.evaluation, other.evaluation);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(settings, recordedAt, changeReason, restoredFrom, evaluation);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyHistoryItem.class,
                "settings",
                settings,
                "recordedAt",
                recordedAt,
                "changeReason",
                changeReason,
                "restoredFrom",
                restoredFrom,
                "evaluation",
                evaluation);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Settings settings;

        private String recordedAt;

        private String changeReason;

        private Long restoredFrom;

        private Publication evaluation;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One environment's settings: how long its assessments are kept, and for each flow the risk thresholds
         * that decide it and the depth a request that names none is served at, and the sources it refuses.
         * Live and test each have their own. A flow without thresholds of its own uses account_creation's
         * (inherits_account_creation).
         *
         * <p>Every change is recorded in the audit log as settings.updated, with who made it.
         */
        public Builder settings(@Nonnull Settings settings) {
            this.settings = Utils.checkNotNull(settings, "settings");
            return this;
        }

        /**
         * Capture time, not a backfilled activation time. Absent only for the current configuration before its
         * first capture.
         */
        public Builder recordedAt(@Nullable String recordedAt) {
            this.recordedAt = recordedAt;
            return this;
        }

        public Builder changeReason(@Nullable String changeReason) {
            this.changeReason = changeReason;
            return this;
        }

        public Builder restoredFrom(@Nullable Long restoredFrom) {
            this.restoredFrom = restoredFrom;
            return this;
        }

        /**
         * Immutable publication provenance remains in policy history after evaluation evidence expires.
         * SHA-256 values identify the frozen manifest, evaluator semantic input, cohort, and canonical result
         * separately.
         */
        public Builder evaluation(@Nullable Publication evaluation) {
            this.evaluation = evaluation;
            return this;
        }

        public PolicyHistoryItem build() {
            return new PolicyHistoryItem(settings, recordedAt, changeReason, restoredFrom, evaluation);
        }
    }
}
