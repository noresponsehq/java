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

/**
 * Publication
 *
 * <p>Immutable publication provenance remains in policy history after evaluation evidence expires.
 * SHA-256 values identify the frozen manifest, evaluator semantic input, cohort, and canonical result
 * separately.
 */
public class Publication {

    @JsonProperty("manifest_sha256")
    private String manifestSha256;

    @JsonProperty("input_sha256")
    private String inputSha256;

    @JsonProperty("cohort_sha256")
    private String cohortSha256;

    @JsonProperty("result_sha256")
    private String resultSha256;

    @JsonProperty("evaluator_sha256")
    private String evaluatorSha256;

    @JsonProperty("evaluation_id")
    private String evaluationId;

    @JsonProperty("evaluator_version")
    private String evaluatorVersion;

    @JsonProperty("build_version")
    private String buildVersion;

    @JsonProperty("captured_at")
    private String capturedAt;

    @JsonProperty("published_at")
    private String publishedAt;

    @JsonCreator
    public Publication(
            @JsonProperty("manifest_sha256") @Nonnull String manifestSha256,
            @JsonProperty("input_sha256") @Nonnull String inputSha256,
            @JsonProperty("cohort_sha256") @Nonnull String cohortSha256,
            @JsonProperty("result_sha256") @Nonnull String resultSha256,
            @JsonProperty("evaluator_sha256") @Nonnull String evaluatorSha256,
            @JsonProperty("evaluation_id") @Nonnull String evaluationId,
            @JsonProperty("build_version") @Nonnull String buildVersion,
            @JsonProperty("captured_at") @Nonnull String capturedAt,
            @JsonProperty("published_at") @Nonnull String publishedAt) {
        this.manifestSha256 = Optional.ofNullable(manifestSha256)
                .orElseThrow(() -> new IllegalArgumentException("manifestSha256 cannot be null"));
        this.inputSha256 = Optional.ofNullable(inputSha256)
                .orElseThrow(() -> new IllegalArgumentException("inputSha256 cannot be null"));
        this.cohortSha256 = Optional.ofNullable(cohortSha256)
                .orElseThrow(() -> new IllegalArgumentException("cohortSha256 cannot be null"));
        this.resultSha256 = Optional.ofNullable(resultSha256)
                .orElseThrow(() -> new IllegalArgumentException("resultSha256 cannot be null"));
        this.evaluatorSha256 = Optional.ofNullable(evaluatorSha256)
                .orElseThrow(() -> new IllegalArgumentException("evaluatorSha256 cannot be null"));
        this.evaluationId = Optional.ofNullable(evaluationId)
                .orElseThrow(() -> new IllegalArgumentException("evaluationId cannot be null"));
        this.evaluatorVersion = Builder._SINGLETON_VALUE_EvaluatorVersion.value();
        this.buildVersion = Optional.ofNullable(buildVersion)
                .orElseThrow(() -> new IllegalArgumentException("buildVersion cannot be null"));
        this.capturedAt = Optional.ofNullable(capturedAt)
                .orElseThrow(() -> new IllegalArgumentException("capturedAt cannot be null"));
        this.publishedAt = Optional.ofNullable(publishedAt)
                .orElseThrow(() -> new IllegalArgumentException("publishedAt cannot be null"));
    }

    public String manifestSha256() {
        return this.manifestSha256;
    }

    public String inputSha256() {
        return this.inputSha256;
    }

    public String cohortSha256() {
        return this.cohortSha256;
    }

    public String resultSha256() {
        return this.resultSha256;
    }

    public String evaluatorSha256() {
        return this.evaluatorSha256;
    }

    public String evaluationId() {
        return this.evaluationId;
    }

    public String evaluatorVersion() {
        return this.evaluatorVersion;
    }

    public String buildVersion() {
        return this.buildVersion;
    }

    public String capturedAt() {
        return this.capturedAt;
    }

    public String publishedAt() {
        return this.publishedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Publication withManifestSha256(@Nonnull String manifestSha256) {
        this.manifestSha256 = Utils.checkNotNull(manifestSha256, "manifestSha256");
        return this;
    }

    public Publication withInputSha256(@Nonnull String inputSha256) {
        this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
        return this;
    }

    public Publication withCohortSha256(@Nonnull String cohortSha256) {
        this.cohortSha256 = Utils.checkNotNull(cohortSha256, "cohortSha256");
        return this;
    }

    public Publication withResultSha256(@Nonnull String resultSha256) {
        this.resultSha256 = Utils.checkNotNull(resultSha256, "resultSha256");
        return this;
    }

    public Publication withEvaluatorSha256(@Nonnull String evaluatorSha256) {
        this.evaluatorSha256 = Utils.checkNotNull(evaluatorSha256, "evaluatorSha256");
        return this;
    }

    public Publication withEvaluationId(@Nonnull String evaluationId) {
        this.evaluationId = Utils.checkNotNull(evaluationId, "evaluationId");
        return this;
    }

    public Publication withBuildVersion(@Nonnull String buildVersion) {
        this.buildVersion = Utils.checkNotNull(buildVersion, "buildVersion");
        return this;
    }

    public Publication withCapturedAt(@Nonnull String capturedAt) {
        this.capturedAt = Utils.checkNotNull(capturedAt, "capturedAt");
        return this;
    }

    public Publication withPublishedAt(@Nonnull String publishedAt) {
        this.publishedAt = Utils.checkNotNull(publishedAt, "publishedAt");
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
        Publication other = (Publication) o;
        return Utils.enhancedDeepEquals(this.manifestSha256, other.manifestSha256)
                && Utils.enhancedDeepEquals(this.inputSha256, other.inputSha256)
                && Utils.enhancedDeepEquals(this.cohortSha256, other.cohortSha256)
                && Utils.enhancedDeepEquals(this.resultSha256, other.resultSha256)
                && Utils.enhancedDeepEquals(this.evaluatorSha256, other.evaluatorSha256)
                && Utils.enhancedDeepEquals(this.evaluationId, other.evaluationId)
                && Utils.enhancedDeepEquals(this.evaluatorVersion, other.evaluatorVersion)
                && Utils.enhancedDeepEquals(this.buildVersion, other.buildVersion)
                && Utils.enhancedDeepEquals(this.capturedAt, other.capturedAt)
                && Utils.enhancedDeepEquals(this.publishedAt, other.publishedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                manifestSha256,
                inputSha256,
                cohortSha256,
                resultSha256,
                evaluatorSha256,
                evaluationId,
                evaluatorVersion,
                buildVersion,
                capturedAt,
                publishedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Publication.class,
                "manifestSha256",
                manifestSha256,
                "inputSha256",
                inputSha256,
                "cohortSha256",
                cohortSha256,
                "resultSha256",
                resultSha256,
                "evaluatorSha256",
                evaluatorSha256,
                "evaluationId",
                evaluationId,
                "evaluatorVersion",
                evaluatorVersion,
                "buildVersion",
                buildVersion,
                "capturedAt",
                capturedAt,
                "publishedAt",
                publishedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String manifestSha256;

        private String inputSha256;

        private String cohortSha256;

        private String resultSha256;

        private String evaluatorSha256;

        private String evaluationId;

        private String buildVersion;

        private String capturedAt;

        private String publishedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder manifestSha256(@Nonnull String manifestSha256) {
            this.manifestSha256 = Utils.checkNotNull(manifestSha256, "manifestSha256");
            return this;
        }

        public Builder inputSha256(@Nonnull String inputSha256) {
            this.inputSha256 = Utils.checkNotNull(inputSha256, "inputSha256");
            return this;
        }

        public Builder cohortSha256(@Nonnull String cohortSha256) {
            this.cohortSha256 = Utils.checkNotNull(cohortSha256, "cohortSha256");
            return this;
        }

        public Builder resultSha256(@Nonnull String resultSha256) {
            this.resultSha256 = Utils.checkNotNull(resultSha256, "resultSha256");
            return this;
        }

        public Builder evaluatorSha256(@Nonnull String evaluatorSha256) {
            this.evaluatorSha256 = Utils.checkNotNull(evaluatorSha256, "evaluatorSha256");
            return this;
        }

        public Builder evaluationId(@Nonnull String evaluationId) {
            this.evaluationId = Utils.checkNotNull(evaluationId, "evaluationId");
            return this;
        }

        public Builder buildVersion(@Nonnull String buildVersion) {
            this.buildVersion = Utils.checkNotNull(buildVersion, "buildVersion");
            return this;
        }

        public Builder capturedAt(@Nonnull String capturedAt) {
            this.capturedAt = Utils.checkNotNull(capturedAt, "capturedAt");
            return this;
        }

        public Builder publishedAt(@Nonnull String publishedAt) {
            this.publishedAt = Utils.checkNotNull(publishedAt, "publishedAt");
            return this;
        }

        public Publication build() {
            return new Publication(
                    manifestSha256,
                    inputSha256,
                    cohortSha256,
                    resultSha256,
                    evaluatorSha256,
                    evaluationId,
                    buildVersion,
                    capturedAt,
                    publishedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_EvaluatorVersion = new LazySingletonValue<>(
                "evaluator_version", "\"identity-threshold-evaluator-v2\"", new TypeReference<String>() {});
    }
}
