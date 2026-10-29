package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class Resolution {

    @JsonProperty("review_version")
    private long reviewVersion;

    @JsonProperty("risk_version")
    private long riskVersion;

    @JsonProperty("assessment_version")
    private long assessmentVersion;

    @JsonProperty("assessment_id")
    private String assessmentId;

    @JsonProperty("conclusion")
    private Conclusion conclusion;

    @JsonProperty("note")
    private String note;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public Resolution(
            @JsonProperty("review_version") long reviewVersion,
            @JsonProperty("risk_version") long riskVersion,
            @JsonProperty("assessment_version") long assessmentVersion,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("conclusion") @Nonnull Conclusion conclusion,
            @JsonProperty("note") @Nonnull String note,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.reviewVersion = reviewVersion;
        this.riskVersion = riskVersion;
        this.assessmentVersion = assessmentVersion;
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.conclusion = Optional.ofNullable(conclusion)
                .orElseThrow(() -> new IllegalArgumentException("conclusion cannot be null"));
        this.note = Optional.ofNullable(note).orElseThrow(() -> new IllegalArgumentException("note cannot be null"));
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public long reviewVersion() {
        return this.reviewVersion;
    }

    public long riskVersion() {
        return this.riskVersion;
    }

    public long assessmentVersion() {
        return this.assessmentVersion;
    }

    public String assessmentId() {
        return this.assessmentId;
    }

    public Conclusion conclusion() {
        return this.conclusion;
    }

    public String note() {
        return this.note;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Resolution withReviewVersion(long reviewVersion) {
        this.reviewVersion = reviewVersion;
        return this;
    }

    public Resolution withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
        return this;
    }

    public Resolution withAssessmentVersion(long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    public Resolution withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    public Resolution withConclusion(@Nonnull Conclusion conclusion) {
        this.conclusion = Utils.checkNotNull(conclusion, "conclusion");
        return this;
    }

    public Resolution withNote(@Nonnull String note) {
        this.note = Utils.checkNotNull(note, "note");
        return this;
    }

    public Resolution withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public Resolution withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
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
        Resolution other = (Resolution) o;
        return Utils.enhancedDeepEquals(this.reviewVersion, other.reviewVersion)
                && Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.conclusion, other.conclusion)
                && Utils.enhancedDeepEquals(this.note, other.note)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                reviewVersion, riskVersion, assessmentVersion, assessmentId, conclusion, note, createdBy, createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Resolution.class,
                "reviewVersion",
                reviewVersion,
                "riskVersion",
                riskVersion,
                "assessmentVersion",
                assessmentVersion,
                "assessmentId",
                assessmentId,
                "conclusion",
                conclusion,
                "note",
                note,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long reviewVersion;

        private long riskVersion;

        private long assessmentVersion;

        private String assessmentId;

        private Conclusion conclusion;

        private String note;

        private String createdBy;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder reviewVersion(long reviewVersion) {
            this.reviewVersion = reviewVersion;
            return this;
        }

        public Builder riskVersion(long riskVersion) {
            this.riskVersion = riskVersion;
            return this;
        }

        public Builder assessmentVersion(long assessmentVersion) {
            this.assessmentVersion = assessmentVersion;
            return this;
        }

        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        public Builder conclusion(@Nonnull Conclusion conclusion) {
            this.conclusion = Utils.checkNotNull(conclusion, "conclusion");
            return this;
        }

        public Builder note(@Nonnull String note) {
            this.note = Utils.checkNotNull(note, "note");
            return this;
        }

        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Resolution build() {
            return new Resolution(
                    reviewVersion,
                    riskVersion,
                    assessmentVersion,
                    assessmentId,
                    conclusion,
                    note,
                    createdBy,
                    createdAt);
        }
    }
}
