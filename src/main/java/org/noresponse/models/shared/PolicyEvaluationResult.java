package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PolicyEvaluationResult
 *
 * <p>The evaluation report and its provenance. A no_change report cannot authorise publication.
 */
public class PolicyEvaluationResult {

    @JsonProperty("evaluation")
    private PolicyEvaluation evaluation;

    @JsonProperty("report")
    private Report report;

    @JsonCreator
    public PolicyEvaluationResult(
            @JsonProperty("evaluation") @Nonnull PolicyEvaluation evaluation,
            @JsonProperty("report") @Nonnull Report report) {
        this.evaluation = Optional.ofNullable(evaluation)
                .orElseThrow(() -> new IllegalArgumentException("evaluation cannot be null"));
        this.report =
                Optional.ofNullable(report).orElseThrow(() -> new IllegalArgumentException("report cannot be null"));
    }

    public PolicyEvaluation evaluation() {
        return this.evaluation;
    }

    public Report report() {
        return this.report;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationResult withEvaluation(@Nonnull PolicyEvaluation evaluation) {
        this.evaluation = Utils.checkNotNull(evaluation, "evaluation");
        return this;
    }

    public PolicyEvaluationResult withReport(@Nonnull Report report) {
        this.report = Utils.checkNotNull(report, "report");
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
        PolicyEvaluationResult other = (PolicyEvaluationResult) o;
        return Utils.enhancedDeepEquals(this.evaluation, other.evaluation)
                && Utils.enhancedDeepEquals(this.report, other.report);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(evaluation, report);
    }

    @Override
    public String toString() {
        return Utils.toString(PolicyEvaluationResult.class, "evaluation", evaluation, "report", report);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private PolicyEvaluation evaluation;

        private Report report;

        private Builder() {
            // force use of static builder() method
        }

        public Builder evaluation(@Nonnull PolicyEvaluation evaluation) {
            this.evaluation = Utils.checkNotNull(evaluation, "evaluation");
            return this;
        }

        public Builder report(@Nonnull Report report) {
            this.report = Utils.checkNotNull(report, "report");
            return this;
        }

        public PolicyEvaluationResult build() {
            return new PolicyEvaluationResult(evaluation, report);
        }
    }
}
