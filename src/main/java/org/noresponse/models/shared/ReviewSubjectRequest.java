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
 * ReviewSubjectRequest
 *
 * <p>An assessment link also requires assessment_id and assessment_version; an issued_credential link
 * also requires credential_id. Every reference must belong to account_id in this environment. Other
 * kinds reject those fields.
 */
public class ReviewSubjectRequest {

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

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("expected_version")
    private long expectedVersion;

    @JsonCreator
    public ReviewSubjectRequest(
            @JsonProperty("kind") @Nonnull ReviewSubjectKind kind,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("assessment_id") @Nullable String assessmentId,
            @JsonProperty("assessment_version") @Nullable Long assessmentVersion,
            @JsonProperty("credential_id") @Nullable String credentialId,
            @JsonProperty("reason") @Nonnull String reason,
            @JsonProperty("expected_version") long expectedVersion) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.assessmentId = assessmentId;
        this.assessmentVersion = assessmentVersion;
        this.credentialId = credentialId;
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
        this.expectedVersion = expectedVersion;
    }

    public ReviewSubjectRequest(
            @Nonnull ReviewSubjectKind kind, @Nonnull String accountId, @Nonnull String reason, long expectedVersion) {
        this(kind, accountId, null, null, null, reason, expectedVersion);
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

    public String reason() {
        return this.reason;
    }

    public long expectedVersion() {
        return this.expectedVersion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewSubjectRequest withKind(@Nonnull ReviewSubjectKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ReviewSubjectRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public ReviewSubjectRequest withAssessmentId(@Nullable String assessmentId) {
        this.assessmentId = assessmentId;
        return this;
    }

    public ReviewSubjectRequest withAssessmentVersion(@Nullable Long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    public ReviewSubjectRequest withCredentialId(@Nullable String credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public ReviewSubjectRequest withReason(@Nonnull String reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
        return this;
    }

    public ReviewSubjectRequest withExpectedVersion(long expectedVersion) {
        this.expectedVersion = expectedVersion;
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
        ReviewSubjectRequest other = (ReviewSubjectRequest) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.credentialId, other.credentialId)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                kind, accountId, assessmentId, assessmentVersion, credentialId, reason, expectedVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewSubjectRequest.class,
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
                "reason",
                reason,
                "expectedVersion",
                expectedVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ReviewSubjectKind kind;

        private String accountId;

        private String assessmentId;

        private Long assessmentVersion;

        private String credentialId;

        private String reason;

        private long expectedVersion;

        private Builder() {
            // force use of static builder() method
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

        public Builder reason(@Nonnull String reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        public Builder expectedVersion(long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public ReviewSubjectRequest build() {
            return new ReviewSubjectRequest(
                    kind, accountId, assessmentId, assessmentVersion, credentialId, reason, expectedVersion);
        }
    }
}
