package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Member
 *
 * <p>One member of a workspace: an external user id holding one role. A removed member loses every
 * permission at once.
 */
public class Member {
    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    @JsonProperty("external_user_id")
    private String externalUserId;

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    @JsonProperty("role")
    private Role role;

    /**
     * Listed and returned members are active.
     */
    @JsonProperty("status")
    private MemberStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("environments")
    private List<MemberEnvironment> environments;

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("denied_actions")
    private List<MemberDeniedAction> deniedActions;

    /**
     * Immutable internal workspace principal, equal to member id, preserved across explicit
     * provider-identity migrations.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("principal_id")
    private String principalId;

    /**
     * Informational display name. Authentication and provider identity use the verified subject binding.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("display_name")
    private String displayName;

    /**
     * Unverified contact address for display. Empty clears it; a supplied value must be an address.
     * Identity linking uses the verified provider subject.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("unverified_email")
    private String unverifiedEmail;

    @JsonCreator
    public Member(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("external_user_id") @Nonnull String externalUserId,
            @JsonProperty("role") @Nonnull Role role,
            @JsonProperty("status") @Nonnull MemberStatus status,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("environments") @Nullable List<MemberEnvironment> environments,
            @JsonProperty("denied_actions") @Nullable List<MemberDeniedAction> deniedActions,
            @JsonProperty("principal_id") @Nullable String principalId,
            @JsonProperty("display_name") @Nullable String displayName,
            @JsonProperty("unverified_email") @Nullable String unverifiedEmail) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.externalUserId = Optional.ofNullable(externalUserId)
                .orElseThrow(() -> new IllegalArgumentException("externalUserId cannot be null"));
        this.role = Optional.ofNullable(role).orElseThrow(() -> new IllegalArgumentException("role cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.environments = environments;
        this.deniedActions = deniedActions;
        this.principalId = principalId;
        this.displayName = displayName;
        this.unverifiedEmail = unverifiedEmail;
    }

    public Member(
            @Nonnull String id,
            @Nonnull String workspaceId,
            @Nonnull String externalUserId,
            @Nonnull Role role,
            @Nonnull MemberStatus status,
            @Nonnull String createdAt,
            @Nonnull String updatedAt) {
        this(id, workspaceId, externalUserId, role, status, createdAt, updatedAt, null, null, null, null, null);
    }

    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    public String id() {
        return this.id;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public String externalUserId() {
        return this.externalUserId;
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public Role role() {
        return this.role;
    }

    /**
     * Listed and returned members are active.
     */
    public MemberStatus status() {
        return this.status;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Optional<List<MemberEnvironment>> environments() {
        return Optional.ofNullable(this.environments);
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Optional<List<MemberDeniedAction>> deniedActions() {
        return Optional.ofNullable(this.deniedActions);
    }

    /**
     * Immutable internal workspace principal, equal to member id, preserved across explicit
     * provider-identity migrations.
     */
    public Optional<String> principalId() {
        return Optional.ofNullable(this.principalId);
    }

    /**
     * Informational display name. Authentication and provider identity use the verified subject binding.
     */
    public Optional<String> displayName() {
        return Optional.ofNullable(this.displayName);
    }

    /**
     * Unverified contact address for display. Empty clears it; a supplied value must be an address.
     * Identity linking uses the verified provider subject.
     */
    public Optional<String> unverifiedEmail() {
        return Optional.ofNullable(this.unverifiedEmail);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
     * member is invited back.
     */
    public Member withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public Member withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public Member withExternalUserId(@Nonnull String externalUserId) {
        this.externalUserId = Utils.checkNotNull(externalUserId, "externalUserId");
        return this;
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public Member withRole(@Nonnull Role role) {
        this.role = Utils.checkNotNull(role, "role");
        return this;
    }

    /**
     * Listed and returned members are active.
     */
    public Member withStatus(@Nonnull MemberStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Member withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Member withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Member withEnvironments(@Nullable List<MemberEnvironment> environments) {
        this.environments = environments;
        return this;
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Member withDeniedActions(@Nullable List<MemberDeniedAction> deniedActions) {
        this.deniedActions = deniedActions;
        return this;
    }

    /**
     * Immutable internal workspace principal, equal to member id, preserved across explicit
     * provider-identity migrations.
     */
    public Member withPrincipalId(@Nullable String principalId) {
        this.principalId = principalId;
        return this;
    }

    /**
     * Informational display name. Authentication and provider identity use the verified subject binding.
     */
    public Member withDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Unverified contact address for display. Empty clears it; a supplied value must be an address.
     * Identity linking uses the verified provider subject.
     */
    public Member withUnverifiedEmail(@Nullable String unverifiedEmail) {
        this.unverifiedEmail = unverifiedEmail;
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
        Member other = (Member) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.externalUserId, other.externalUserId)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.environments, other.environments)
                && Utils.enhancedDeepEquals(this.deniedActions, other.deniedActions)
                && Utils.enhancedDeepEquals(this.principalId, other.principalId)
                && Utils.enhancedDeepEquals(this.displayName, other.displayName)
                && Utils.enhancedDeepEquals(this.unverifiedEmail, other.unverifiedEmail);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                workspaceId,
                externalUserId,
                role,
                status,
                createdAt,
                updatedAt,
                environments,
                deniedActions,
                principalId,
                displayName,
                unverifiedEmail);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Member.class,
                "id",
                id,
                "workspaceId",
                workspaceId,
                "externalUserId",
                externalUserId,
                "role",
                role,
                "status",
                status,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "environments",
                environments,
                "deniedActions",
                deniedActions,
                "principalId",
                principalId,
                "displayName",
                displayName,
                "unverifiedEmail",
                unverifiedEmail);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String workspaceId;

        private String externalUserId;

        private Role role;

        private MemberStatus status;

        private String createdAt;

        private String updatedAt;

        private List<MemberEnvironment> environments;

        private List<MemberDeniedAction> deniedActions;

        private String principalId;

        private String displayName;

        private String unverifiedEmail;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned member id: mem_ and a 26-character ULID. It stays the same when a removed
         * member is invited back.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
         * characters with no space, compared exactly.
         */
        public Builder externalUserId(@Nonnull String externalUserId) {
            this.externalUserId = Utils.checkNotNull(externalUserId, "externalUserId");
            return this;
        }

        /**
         * A built-in capability set. Specialist roles are independent; effective access is returned by GET
         * /v1/workspaces/{workspace_id}/capabilities.
         */
        public Builder role(@Nonnull Role role) {
            this.role = Utils.checkNotNull(role, "role");
            return this;
        }

        /**
         * Listed and returned members are active.
         */
        public Builder status(@Nonnull MemberStatus status) {
            this.status = Utils.checkNotNull(status, "status");
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

        /**
         * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
         * grants; owners retain both.
         */
        public Builder environments(@Nullable List<MemberEnvironment> environments) {
            this.environments = environments;
            return this;
        }

        /**
         * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
         * publication. Owners retain full authority. Omitted member updates preserve exclusions.
         */
        public Builder deniedActions(@Nullable List<MemberDeniedAction> deniedActions) {
            this.deniedActions = deniedActions;
            return this;
        }

        /**
         * Immutable internal workspace principal, equal to member id, preserved across explicit
         * provider-identity migrations.
         */
        public Builder principalId(@Nullable String principalId) {
            this.principalId = principalId;
            return this;
        }

        /**
         * Informational display name. Authentication and provider identity use the verified subject binding.
         */
        public Builder displayName(@Nullable String displayName) {
            this.displayName = displayName;
            return this;
        }

        /**
         * Unverified contact address for display. Empty clears it; a supplied value must be an address.
         * Identity linking uses the verified provider subject.
         */
        public Builder unverifiedEmail(@Nullable String unverifiedEmail) {
            this.unverifiedEmail = unverifiedEmail;
            return this;
        }

        public Member build() {
            return new Member(
                    id,
                    workspaceId,
                    externalUserId,
                    role,
                    status,
                    createdAt,
                    updatedAt,
                    environments,
                    deniedActions,
                    principalId,
                    displayName,
                    unverifiedEmail);
        }
    }
}
