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

/**
 * ReviewSubject
 *
 * <p>A tenant-local investigation link. risk_version is the account risk version captured when the link
 * was added. It does not assert a relationship or change feedback or enforcement.
 */
public class ReviewSubject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kind")
    private ReviewSubjectKind kind;

    @JsonProperty("account_id")
    private String accountId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assessment_id")
    private String assessmentId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assessment_version")
    private Long assessmentVersion;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("credential_id")
    private String credentialId;

    @JsonProperty("risk_version")
    private long riskVersion;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public ReviewSubject(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("kind") @Nonnull ReviewSubjectKind kind,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("assessment_id") @Nullable String assessmentId,
            @JsonProperty("assessment_version") @Nullable Long assessmentVersion,
            @JsonProperty("credential_id") @Nullable String credentialId,
            @JsonProperty("risk_version") long riskVersion,
            @JsonProperty("reason") @Nonnull String reason,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.assessmentId = assessmentId;
        this.assessmentVersion = assessmentVersion;
        this.credentialId = credentialId;
        this.riskVersion = riskVersion;
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public ReviewSubject(
            @Nonnull String id,
            @Nonnull ReviewSubjectKind kind,
            @Nonnull String accountId,
            long riskVersion,
            @Nonnull String reason,
            @Nonnull String createdBy,
            @Nonnull String createdAt) {
        this(id, kind, accountId, null, null, null, riskVersion, reason, createdBy, createdAt);
    }

    public String id() {
        return this.id;
    }

    public ReviewSubjectKind kind() {
        return this.kind;
    }

    public String accountId() {
        return this.accountId;
    }

    public Optional<String> assessmentId() {
        return Optional.ofNullable(this.assessmentId);
    }

    public Optional<Long> assessmentVersion() {
        return Optional.ofNullable(this.assessmentVersion);
    }

    public Optional<String> credentialId() {
        return Optional.ofNullable(this.credentialId);
    }

    public long riskVersion() {
        return this.riskVersion;
    }

    public String reason() {
        return this.reason;
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

    public ReviewSubject withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ReviewSubject withKind(@Nonnull ReviewSubjectKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ReviewSubject withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public ReviewSubject withAssessmentId(@Nullable String assessmentId) {
        this.assessmentId = assessmentId;
        return this;
    }

    public ReviewSubject withAssessmentVersion(@Nullable Long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    public ReviewSubject withCredentialId(@Nullable String credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public ReviewSubject withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
        return this;
    }

    public ReviewSubject withReason(@Nonnull String reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
        return this;
    }

    public ReviewSubject withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public ReviewSubject withCreatedAt(@Nonnull String createdAt) {
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
        ReviewSubject other = (ReviewSubject) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.credentialId, other.credentialId)
                && Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                kind,
                accountId,
                assessmentId,
                assessmentVersion,
                credentialId,
                riskVersion,
                reason,
                createdBy,
                createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewSubject.class,
                "id",
                id,
                "kind",
                kind,
                "accountId",
                accountId,
                "assessmentId",
                assessmentId,
                "assessmentVersion",
                assessmentVersion,
                "credentialId",
                credentialId,
                "riskVersion",
                riskVersion,
                "reason",
                reason,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private ReviewSubjectKind kind;

        private String accountId;

        private String assessmentId;

        private Long assessmentVersion;

        private String credentialId;

        private long riskVersion;

        private String reason;

        private String createdBy;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder kind(@Nonnull ReviewSubjectKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder assessmentId(@Nullable String assessmentId) {
            this.assessmentId = assessmentId;
            return this;
        }

        public Builder assessmentVersion(@Nullable Long assessmentVersion) {
            this.assessmentVersion = assessmentVersion;
            return this;
        }

        public Builder credentialId(@Nullable String credentialId) {
            this.credentialId = credentialId;
            return this;
        }

        public Builder riskVersion(long riskVersion) {
            this.riskVersion = riskVersion;
            return this;
        }

        public Builder reason(@Nonnull String reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
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

        public ReviewSubject build() {
            return new ReviewSubject(
                    id,
                    kind,
                    accountId,
                    assessmentId,
                    assessmentVersion,
                    credentialId,
                    riskVersion,
                    reason,
                    createdBy,
                    createdAt);
        }
    }
}
