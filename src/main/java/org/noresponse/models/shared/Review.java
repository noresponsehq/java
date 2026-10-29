package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class Review {

    @JsonProperty("id")
    private String id;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("status")
    private ReviewStatus status;

    @JsonProperty("priority")
    private Priority priority;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("version")
    private long version;

    @JsonProperty("opened_risk_version")
    private long openedRiskVersion;

    @JsonProperty("current_risk_version")
    private long currentRiskVersion;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_by")
    private String updatedBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("resolution")
    private Resolution resolution;

    /**
     * Immutable workspace member ID. assignee is the historical display identity captured at assignment.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assignee_principal_id")
    private String assigneePrincipalId;

    @JsonCreator
    public Review(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("status") @Nonnull ReviewStatus status,
            @JsonProperty("priority") @Nonnull Priority priority,
            @JsonProperty("assignee") @Nullable String assignee,
            @JsonProperty("version") long version,
            @JsonProperty("opened_risk_version") long openedRiskVersion,
            @JsonProperty("current_risk_version") long currentRiskVersion,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("updated_by") @Nonnull String updatedBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("resolution") @Nullable Resolution resolution,
            @JsonProperty("assignee_principal_id") @Nullable String assigneePrincipalId) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.priority = Optional.ofNullable(priority)
                .orElseThrow(() -> new IllegalArgumentException("priority cannot be null"));
        this.assignee = assignee;
        this.version = version;
        this.openedRiskVersion = openedRiskVersion;
        this.currentRiskVersion = currentRiskVersion;
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.updatedBy = Optional.ofNullable(updatedBy)
                .orElseThrow(() -> new IllegalArgumentException("updatedBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.resolution = resolution;
        this.assigneePrincipalId = assigneePrincipalId;
    }

    public Review(
            @Nonnull String id,
            @Nonnull String accountId,
            @Nonnull ReviewStatus status,
            @Nonnull Priority priority,
            long version,
            long openedRiskVersion,
            long currentRiskVersion,
            @Nonnull String createdBy,
            @Nonnull String updatedBy,
            @Nonnull String createdAt,
            @Nonnull String updatedAt,
            @Nonnull String expiresAt) {
        this(
                id,
                accountId,
                status,
                priority,
                null,
                version,
                openedRiskVersion,
                currentRiskVersion,
                createdBy,
                updatedBy,
                createdAt,
                updatedAt,
                expiresAt,
                null,
                null);
    }

    public String id() {
        return this.id;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    public ReviewStatus status() {
        return this.status;
    }

    public Priority priority() {
        return this.priority;
    }

    public Optional<String> assignee() {
        return Optional.ofNullable(this.assignee);
    }

    public long version() {
        return this.version;
    }

    public long openedRiskVersion() {
        return this.openedRiskVersion;
    }

    public long currentRiskVersion() {
        return this.currentRiskVersion;
    }

    public String createdBy() {
        return this.createdBy;
    }

    public String updatedBy() {
        return this.updatedBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    public Optional<Resolution> resolution() {
        return Optional.ofNullable(this.resolution);
    }

    /**
     * Immutable workspace member ID. assignee is the historical display identity captured at assignment.
     */
    public Optional<String> assigneePrincipalId() {
        return Optional.ofNullable(this.assigneePrincipalId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Review withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public Review withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public Review withStatus(@Nonnull ReviewStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Review withPriority(@Nonnull Priority priority) {
        this.priority = Utils.checkNotNull(priority, "priority");
        return this;
    }

    public Review withAssignee(@Nullable String assignee) {
        this.assignee = assignee;
        return this;
    }

    public Review withVersion(long version) {
        this.version = version;
        return this;
    }

    public Review withOpenedRiskVersion(long openedRiskVersion) {
        this.openedRiskVersion = openedRiskVersion;
        return this;
    }

    public Review withCurrentRiskVersion(long currentRiskVersion) {
        this.currentRiskVersion = currentRiskVersion;
        return this;
    }

    public Review withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public Review withUpdatedBy(@Nonnull String updatedBy) {
        this.updatedBy = Utils.checkNotNull(updatedBy, "updatedBy");
        return this;
    }

    public Review withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Review withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    public Review withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    public Review withResolution(@Nullable Resolution resolution) {
        this.resolution = resolution;
        return this;
    }

    /**
     * Immutable workspace member ID. assignee is the historical display identity captured at assignment.
     */
    public Review withAssigneePrincipalId(@Nullable String assigneePrincipalId) {
        this.assigneePrincipalId = assigneePrincipalId;
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
        Review other = (Review) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.priority, other.priority)
                && Utils.enhancedDeepEquals(this.assignee, other.assignee)
                && Utils.enhancedDeepEquals(this.version, other.version)
                && Utils.enhancedDeepEquals(this.openedRiskVersion, other.openedRiskVersion)
                && Utils.enhancedDeepEquals(this.currentRiskVersion, other.currentRiskVersion)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.updatedBy, other.updatedBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.resolution, other.resolution)
                && Utils.enhancedDeepEquals(this.assigneePrincipalId, other.assigneePrincipalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                accountId,
                status,
                priority,
                assignee,
                version,
                openedRiskVersion,
                currentRiskVersion,
                createdBy,
                updatedBy,
                createdAt,
                updatedAt,
                expiresAt,
                resolution,
                assigneePrincipalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Review.class,
                "id",
                id,
                "accountId",
                accountId,
                "status",
                status,
                "priority",
                priority,
                "assignee",
                assignee,
                "version",
                version,
                "openedRiskVersion",
                openedRiskVersion,
                "currentRiskVersion",
                currentRiskVersion,
                "createdBy",
                createdBy,
                "updatedBy",
                updatedBy,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "expiresAt",
                expiresAt,
                "resolution",
                resolution,
                "assigneePrincipalId",
                assigneePrincipalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String accountId;

        private ReviewStatus status;

        private Priority priority;

        private String assignee;

        private long version;

        private long openedRiskVersion;

        private long currentRiskVersion;

        private String createdBy;

        private String updatedBy;

        private String createdAt;

        private String updatedAt;

        private String expiresAt;

        private Resolution resolution;

        private String assigneePrincipalId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
         * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
         * yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder status(@Nonnull ReviewStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder priority(@Nonnull Priority priority) {
            this.priority = Utils.checkNotNull(priority, "priority");
            return this;
        }

        public Builder assignee(@Nullable String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder version(long version) {
            this.version = version;
            return this;
        }

        public Builder openedRiskVersion(long openedRiskVersion) {
            this.openedRiskVersion = openedRiskVersion;
            return this;
        }

        public Builder currentRiskVersion(long currentRiskVersion) {
            this.currentRiskVersion = currentRiskVersion;
            return this;
        }

        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
            return this;
        }

        public Builder updatedBy(@Nonnull String updatedBy) {
            this.updatedBy = Utils.checkNotNull(updatedBy, "updatedBy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder updatedAt(@Nonnull String updatedAt) {
            this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
            return this;
        }

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public Builder resolution(@Nullable Resolution resolution) {
            this.resolution = resolution;
            return this;
        }

        /**
         * Immutable workspace member ID. assignee is the historical display identity captured at assignment.
         */
        public Builder assigneePrincipalId(@Nullable String assigneePrincipalId) {
            this.assigneePrincipalId = assigneePrincipalId;
            return this;
        }

        public Review build() {
            return new Review(
                    id,
                    accountId,
                    status,
                    priority,
                    assignee,
                    version,
                    openedRiskVersion,
                    currentRiskVersion,
                    createdBy,
                    updatedBy,
                    createdAt,
                    updatedAt,
                    expiresAt,
                    resolution,
                    assigneePrincipalId);
        }
    }
}
