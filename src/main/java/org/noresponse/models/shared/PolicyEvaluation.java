package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class PolicyEvaluation {

    @JsonProperty("id")
    private String id;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    @JsonProperty("status")
    private PolicyEvaluationStatus status;

    @JsonProperty("policy_version")
    private long policyVersion;

    @JsonProperty("scoring_policy")
    private String scoringPolicy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("captured_at")
    private String capturedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonProperty("accounts")
    private long accounts;

    @JsonProperty("requests")
    private long requests;

    @JsonProperty("evaluator_version")
    private String evaluatorVersion;

    @JsonProperty("evaluator_sha256")
    private String evaluatorSha256;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("build_version")
    private String buildVersion;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("result_status")
    private ResultStatus resultStatus;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("published_policy_version")
    private Long publishedPolicyVersion;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("manifest_sha256")
    private String manifestSha256;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("input_sha256")
    private String inputSha256;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cohort_sha256")
    private String cohortSha256;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("result_sha256")
    private String resultSha256;

    @JsonCreator
    public PolicyEvaluation(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("status") @Nonnull PolicyEvaluationStatus status,
            @JsonProperty("policy_version") long policyVersion,
            @JsonProperty("scoring_policy") @Nonnull String scoringPolicy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("captured_at") @Nullable String capturedAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("accounts") long accounts,
            @JsonProperty("requests") long requests,
            @JsonProperty("evaluator_sha256") @Nonnull String evaluatorSha256,
            @JsonProperty("build_version") @Nullable String buildVersion,
            @JsonProperty("result_status") @Nullable ResultStatus resultStatus,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("published_policy_version") @Nullable Long publishedPolicyVersion,
            @JsonProperty("manifest_sha256") @Nullable String manifestSha256,
            @JsonProperty("input_sha256") @Nullable String inputSha256,
            @JsonProperty("cohort_sha256") @Nullable String cohortSha256,
            @JsonProperty("result_sha256") @Nullable String resultSha256) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.policyVersion = policyVersion;
        this.scoringPolicy = Optional.ofNullable(scoringPolicy)
                .orElseThrow(() -> new IllegalArgumentException("scoringPolicy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.capturedAt = capturedAt;
        this.finishedAt = finishedAt;
        this.accounts = accounts;
        this.requests = requests;
        this.evaluatorVersion = Builder._SINGLETON_VALUE_EvaluatorVersion.value();
        this.evaluatorSha256 = Optional.ofNullable(evaluatorSha256)
                .orElseThrow(() -> new IllegalArgumentException("evaluatorSha256 cannot be null"));
        this.buildVersion = buildVersion;
        this.resultStatus = resultStatus;
        this.errorCode = errorCode;
        this.publishedPolicyVersion = publishedPolicyVersion;
        this.manifestSha256 = manifestSha256;
        this.inputSha256 = inputSha256;
        this.cohortSha256 = cohortSha256;
        this.resultSha256 = resultSha256;
    }

    public PolicyEvaluation(
            @Nonnull String id,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull PolicyEvaluationStatus status,
            long policyVersion,
            @Nonnull String scoringPolicy,
            @Nonnull String createdAt,
            @Nonnull String expiresAt,
            long accounts,
            long requests,
            @Nonnull String evaluatorSha256) {
        this(
                id,
                environment,
                status,
                policyVersion,
                scoringPolicy,
                createdAt,
                expiresAt,
                null,
                null,
                accounts,
                requests,
                evaluatorSha256,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public String id() {
        return this.id;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    public PolicyEvaluationStatus status() {
        return this.status;
    }

    public long policyVersion() {
        return this.policyVersion;
    }

    public String scoringPolicy() {
        return this.scoringPolicy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    public Optional<String> capturedAt() {
        return Optional.ofNullable(this.capturedAt);
    }

    public Optional<String> finishedAt() {
        return Optional.ofNullable(this.finishedAt);
    }

    public long accounts() {
        return this.accounts;
    }

    public long requests() {
        return this.requests;
    }

    public String evaluatorVersion() {
        return this.evaluatorVersion;
    }

    public String evaluatorSha256() {
        return this.evaluatorSha256;
    }

    public Optional<String> buildVersion() {
        return Optional.ofNullable(this.buildVersion);
    }

    public Optional<ResultStatus> resultStatus() {
        return Optional.ofNullable(this.resultStatus);
    }

    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public Optional<Long> publishedPolicyVersion() {
        return Optional.ofNullable(this.publishedPolicyVersion);
    }

    public Optional<String> manifestSha256() {
        return Optional.ofNullable(this.manifestSha256);
    }

    public Optional<String> inputSha256() {
        return Optional.ofNullable(this.inputSha256);
    }

    public Optional<String> cohortSha256() {
        return Optional.ofNullable(this.cohortSha256);
    }

    public Optional<String> resultSha256() {
        return Optional.ofNullable(this.resultSha256);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluation withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public PolicyEvaluation withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public PolicyEvaluation withStatus(@Nonnull PolicyEvaluationStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public PolicyEvaluation withPolicyVersion(long policyVersion) {
        this.policyVersion = policyVersion;
        return this;
    }

    public PolicyEvaluation withScoringPolicy(@Nonnull String scoringPolicy) {
        this.scoringPolicy = Utils.checkNotNull(scoringPolicy, "scoringPolicy");
        return this;
    }

    public PolicyEvaluation withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public PolicyEvaluation withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    public PolicyEvaluation withCapturedAt(@Nullable String capturedAt) {
        this.capturedAt = capturedAt;
        return this;
    }

    public PolicyEvaluation withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    public PolicyEvaluation withAccounts(long accounts) {
        this.accounts = accounts;
        return this;
    }

    public PolicyEvaluation withRequests(long requests) {
        this.requests = requests;
        return this;
    }

    public PolicyEvaluation withEvaluatorSha256(@Nonnull String evaluatorSha256) {
        this.evaluatorSha256 = Utils.checkNotNull(evaluatorSha256, "evaluatorSha256");
        return this;
    }

    public PolicyEvaluation withBuildVersion(@Nullable String buildVersion) {
        this.buildVersion = buildVersion;
        return this;
    }

    public PolicyEvaluation withResultStatus(@Nullable ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
        return this;
    }

    public PolicyEvaluation withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public PolicyEvaluation withPublishedPolicyVersion(@Nullable Long publishedPolicyVersion) {
        this.publishedPolicyVersion = publishedPolicyVersion;
        return this;
    }

    public PolicyEvaluation withManifestSha256(@Nullable String manifestSha256) {
        this.manifestSha256 = manifestSha256;
        return this;
    }

    public PolicyEvaluation withInputSha256(@Nullable String inputSha256) {
        this.inputSha256 = inputSha256;
        return this;
    }

    public PolicyEvaluation withCohortSha256(@Nullable String cohortSha256) {
        this.cohortSha256 = cohortSha256;
        return this;
    }

    public PolicyEvaluation withResultSha256(@Nullable String resultSha256) {
        this.resultSha256 = resultSha256;
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
        PolicyEvaluation other = (PolicyEvaluation) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.scoringPolicy, other.scoringPolicy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.capturedAt, other.capturedAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.accounts, other.accounts)
                && Utils.enhancedDeepEquals(this.requests, other.requests)
                && Utils.enhancedDeepEquals(this.evaluatorVersion, other.evaluatorVersion)
                && Utils.enhancedDeepEquals(this.evaluatorSha256, other.evaluatorSha256)
                && Utils.enhancedDeepEquals(this.buildVersion, other.buildVersion)
                && Utils.enhancedDeepEquals(this.resultStatus, other.resultStatus)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.publishedPolicyVersion, other.publishedPolicyVersion)
                && Utils.enhancedDeepEquals(this.manifestSha256, other.manifestSha256)
                && Utils.enhancedDeepEquals(this.inputSha256, other.inputSha256)
                && Utils.enhancedDeepEquals(this.cohortSha256, other.cohortSha256)
                && Utils.enhancedDeepEquals(this.resultSha256, other.resultSha256);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                environment,
                status,
                policyVersion,
                scoringPolicy,
                createdAt,
                expiresAt,
                capturedAt,
                finishedAt,
                accounts,
                requests,
                evaluatorVersion,
                evaluatorSha256,
                buildVersion,
                resultStatus,
                errorCode,
                publishedPolicyVersion,
                manifestSha256,
                inputSha256,
                cohortSha256,
                resultSha256);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyEvaluation.class,
                "id",
                id,
                "environment",
                environment,
                "status",
                status,
                "policyVersion",
                policyVersion,
                "scoringPolicy",
                scoringPolicy,
                "createdAt",
                createdAt,
                "expiresAt",
                expiresAt,
                "capturedAt",
                capturedAt,
                "finishedAt",
                finishedAt,
                "accounts",
                accounts,
                "requests",
                requests,
                "evaluatorVersion",
                evaluatorVersion,
                "evaluatorSha256",
                evaluatorSha256,
                "buildVersion",
                buildVersion,
                "resultStatus",
                resultStatus,
                "errorCode",
                errorCode,
                "publishedPolicyVersion",
                publishedPolicyVersion,
                "manifestSha256",
                manifestSha256,
                "inputSha256",
                inputSha256,
                "cohortSha256",
                cohortSha256,
                "resultSha256",
                resultSha256);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private AuditSettingsEnvironment environment;

        private PolicyEvaluationStatus status;

        private long policyVersion;

        private String scoringPolicy;

        private String createdAt;

        private String expiresAt;

        private String capturedAt;

        private String finishedAt;

        private long accounts;

        private long requests;

        private String evaluatorSha256;

        private String buildVersion;

        private ResultStatus resultStatus;

        private String errorCode;

        private Long publishedPolicyVersion;

        private String manifestSha256;

        private String inputSha256;

        private String cohortSha256;

        private String resultSha256;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder status(@Nonnull PolicyEvaluationStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder policyVersion(long policyVersion) {
            this.policyVersion = policyVersion;
            return this;
        }

        public Builder scoringPolicy(@Nonnull String scoringPolicy) {
            this.scoringPolicy = Utils.checkNotNull(scoringPolicy, "scoringPolicy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public Builder capturedAt(@Nullable String capturedAt) {
            this.capturedAt = capturedAt;
            return this;
        }

        public Builder finishedAt(@Nullable String finishedAt) {
            this.finishedAt = finishedAt;
            return this;
        }

        public Builder accounts(long accounts) {
            this.accounts = accounts;
            return this;
        }

        public Builder requests(long requests) {
            this.requests = requests;
            return this;
        }

        public Builder evaluatorSha256(@Nonnull String evaluatorSha256) {
            this.evaluatorSha256 = Utils.checkNotNull(evaluatorSha256, "evaluatorSha256");
            return this;
        }

        public Builder buildVersion(@Nullable String buildVersion) {
            this.buildVersion = buildVersion;
            return this;
        }

        public Builder resultStatus(@Nullable ResultStatus resultStatus) {
            this.resultStatus = resultStatus;
            return this;
        }

        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder publishedPolicyVersion(@Nullable Long publishedPolicyVersion) {
            this.publishedPolicyVersion = publishedPolicyVersion;
            return this;
        }

        public Builder manifestSha256(@Nullable String manifestSha256) {
            this.manifestSha256 = manifestSha256;
            return this;
        }

        public Builder inputSha256(@Nullable String inputSha256) {
            this.inputSha256 = inputSha256;
            return this;
        }

        public Builder cohortSha256(@Nullable String cohortSha256) {
            this.cohortSha256 = cohortSha256;
            return this;
        }

        public Builder resultSha256(@Nullable String resultSha256) {
            this.resultSha256 = resultSha256;
            return this;
        }

        public PolicyEvaluation build() {
            return new PolicyEvaluation(
                    id,
                    environment,
                    status,
                    policyVersion,
                    scoringPolicy,
                    createdAt,
                    expiresAt,
                    capturedAt,
                    finishedAt,
                    accounts,
                    requests,
                    evaluatorSha256,
                    buildVersion,
                    resultStatus,
                    errorCode,
                    publishedPolicyVersion,
                    manifestSha256,
                    inputSha256,
                    cohortSha256,
                    resultSha256);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_EvaluatorVersion = new LazySingletonValue<>(
                "evaluator_version", "\"identity-threshold-evaluator-v2\"", new TypeReference<String>() {});
    }
}
