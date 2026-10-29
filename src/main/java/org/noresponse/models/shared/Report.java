package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class Report {

    @JsonProperty("evaluator_version")
    private String evaluatorVersion;

    @JsonProperty("input_sha256")
    private String inputSha256;

    @JsonProperty("cohort_sha256")
    private String cohortSha256;

    @JsonProperty("status")
    private ResultStatus status;

    @JsonProperty("candidate")
    private Thresholds candidate;

    @JsonProperty("scope")
    private ReportScope scope;

    @JsonCreator
    public Report(
            @JsonProperty("input_sha256") @Nonnull String inputSha256,
            @JsonProperty("cohort_sha256") @Nonnull String cohortSha256,
            @JsonProperty("status") @Nonnull ResultStatus status,
            @JsonProperty("candidate") @Nonnull Thresholds candidate,
            @JsonProperty("scope") @Nonnull ReportScope scope) {
        this.evaluatorVersion = Builder._SINGLETON_VALUE_EvaluatorVersion.value();
        this.inputSha256 = Optional.ofNullable(inputSha256)
                .orElseThrow(() -> new IllegalArgumentException("inputSha256 cannot be null"));
        this.cohortSha256 = Optional.ofNullable(cohortSha256)
                .orElseThrow(() -> new IllegalArgumentException("cohortSha256 cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.candidate = Optional.ofNullable(candidate)
                .orElseThrow(() -> new IllegalArgumentException("candidate cannot be null"));
        this.scope = Optional.ofNullable(scope).orElseThrow(() -> new IllegalArgumentException("scope cannot be null"));
    }

    public String evaluatorVersion() {
        return this.evaluatorVersion;
    }

    public String inputSha256() {
        return this.inputSha256;
    }

    public String cohortSha256() {
        return this.cohortSha256;
    }

    public ResultStatus status() {
        return this.status;
    }

    public Thresholds candidate() {
        return this.candidate;
    }

    public ReportScope scope() {
        return this.scope;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Report withInputSha256(@Nonnull String inputSha256) {
        this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
        return this;
    }

    public Report withCohortSha256(@Nonnull String cohortSha256) {
        this.cohortSha256 = Utils.checkNotNull(cohortSha256, "cohortSha256");
        return this;
    }

    public Report withStatus(@Nonnull ResultStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Report withCandidate(@Nonnull Thresholds candidate) {
        this.candidate = Utils.checkNotNull(candidate, "candidate");
        return this;
    }

    public Report withScope(@Nonnull ReportScope scope) {
        this.scope = Utils.checkNotNull(scope, "scope");
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
        Report other = (Report) o;
        return Utils.enhancedDeepEquals(this.evaluatorVersion, other.evaluatorVersion)
                && Utils.enhancedDeepEquals(this.inputSha256, other.inputSha256)
                && Utils.enhancedDeepEquals(this.cohortSha256, other.cohortSha256)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.candidate, other.candidate)
                && Utils.enhancedDeepEquals(this.scope, other.scope);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(evaluatorVersion, inputSha256, cohortSha256, status, candidate, scope);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Report.class,
                "evaluatorVersion",
                evaluatorVersion,
                "inputSha256",
                inputSha256,
                "cohortSha256",
                cohortSha256,
                "status",
                status,
                "candidate",
                candidate,
                "scope",
                scope);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String inputSha256;

        private String cohortSha256;

        private ResultStatus status;

        private Thresholds candidate;

        private ReportScope scope;

        private Builder() {
            // force use of static builder() method
        }

        public Builder inputSha256(@Nonnull String inputSha256) {
            this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
            return this;
        }

        public Builder cohortSha256(@Nonnull String cohortSha256) {
            this.cohortSha256 = Utils.checkNotNull(cohortSha256, "cohortSha256");
            return this;
        }

        public Builder status(@Nonnull ResultStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder candidate(@Nonnull Thresholds candidate) {
            this.candidate = Utils.checkNotNull(candidate, "candidate");
            return this;
        }

        public Builder scope(@Nonnull ReportScope scope) {
            this.scope = Utils.checkNotNull(scope, "scope");
            return this;
        }

        public Report build() {
            return new Report(inputSha256, cohortSha256, status, candidate, scope);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_EvaluatorVersion = new LazySingletonValue<>(
                "evaluator_version", "\"identity-threshold-evaluator-v2\"", new TypeReference<String>() {});
    }
}
