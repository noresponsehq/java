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
 * Invite
 *
 * <p>A pending workspace invitation. Creating or reissuing it returns a token once; lists omit it. The
 * token can be accepted once before expiry and is invalidated on revocation or reissue.
 *
 * <p>Token state does not establish email delivery.
 */
public class Invite {
    /**
     * Opaque, server-assigned invite id: inv_ and a 26-character ULID.
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
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    @JsonProperty("role")
    private Role role;

    /**
     * Where the console sends the invite. Informational: it is not matched against the accepting user.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("external_user_id")
    private String externalUserId;

    /**
     * The stable member id that issued this invite, or the authenticated live API key prefix for
     * members_manage automation. Reissuing preserves the original creator; the audit event names the
     * reissuing actor.
     */
    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * The invite token: nrinv_, the invite id's ULID, _ and 43 unpadded base64url characters (32 random
     * bytes). The new member's console sends it to POST /v1/invites/accept.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("token")
    private String token;

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("environments")
    private List<InviteEnvironment> environments;

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("denied_actions")
    private List<InviteDeniedAction> deniedActions;

    /**
     * Last token reissue, not proof of email delivery.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reissued_at")
    private String reissuedAt;

    @JsonCreator
    public Invite(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("role") @Nonnull Role role,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("external_user_id") @Nullable String externalUserId,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("token") @Nullable String token,
            @JsonProperty("environments") @Nullable List<InviteEnvironment> environments,
            @JsonProperty("denied_actions") @Nullable List<InviteDeniedAction> deniedActions,
            @JsonProperty("reissued_at") @Nullable String reissuedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.role = Optional.ofNullable(role).orElseThrow(() -> new IllegalArgumentException("role cannot be null"));
        this.email = email;
        this.externalUserId = externalUserId;
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.token = token;
        this.environments = environments;
        this.deniedActions = deniedActions;
        this.reissuedAt = reissuedAt;
    }

    public Invite(
            @Nonnull String id,
            @Nonnull String workspaceId,
            @Nonnull Role role,
            @Nonnull String createdBy,
            @Nonnull String createdAt,
            @Nonnull String expiresAt) {
        this(id, workspaceId, role, null, null, createdBy, createdAt, expiresAt, null, null, null, null);
    }

    /**
     * Opaque, server-assigned invite id: inv_ and a 26-character ULID.
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
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public Role role() {
        return this.role;
    }

    /**
     * Where the console sends the invite. Informational: it is not matched against the accepting user.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public Optional<String> externalUserId() {
        return Optional.ofNullable(this.externalUserId);
    }

    /**
     * The stable member id that issued this invite, or the authenticated live API key prefix for
     * members_manage automation. Reissuing preserves the original creator; the audit event names the
     * reissuing actor.
     */
    public String createdBy() {
        return this.createdBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    /**
     * The invite token: nrinv_, the invite id's ULID, _ and 43 unpadded base64url characters (32 random
     * bytes). The new member's console sends it to POST /v1/invites/accept.
     */
    public Optional<String> token() {
        return Optional.ofNullable(this.token);
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Optional<List<InviteEnvironment>> environments() {
        return Optional.ofNullable(this.environments);
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Optional<List<InviteDeniedAction>> deniedActions() {
        return Optional.ofNullable(this.deniedActions);
    }

    /**
     * Last token reissue, not proof of email delivery.
     */
    public Optional<String> reissuedAt() {
        return Optional.ofNullable(this.reissuedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned invite id: inv_ and a 26-character ULID.
     */
    public Invite withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public Invite withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public Invite withRole(@Nonnull Role role) {
        this.role = Utils.checkNotNull(role, "role");
        return this;
    }

    /**
     * Where the console sends the invite. Informational: it is not matched against the accepting user.
     */
    public Invite withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public Invite withExternalUserId(@Nullable String externalUserId) {
        this.externalUserId = externalUserId;
        return this;
    }

    /**
     * The stable member id that issued this invite, or the authenticated live API key prefix for
     * members_manage automation. Reissuing preserves the original creator; the audit event names the
     * reissuing actor.
     */
    public Invite withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    public Invite withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Invite withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    /**
     * The invite token: nrinv_, the invite id's ULID, _ and 43 unpadded base64url characters (32 random
     * bytes). The new member's console sends it to POST /v1/invites/accept.
     */
    public Invite withToken(@Nullable String token) {
        this.token = token;
        return this;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Invite withEnvironments(@Nullable List<InviteEnvironment> environments) {
        this.environments = environments;
        return this;
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Invite withDeniedActions(@Nullable List<InviteDeniedAction> deniedActions) {
        this.deniedActions = deniedActions;
        return this;
    }

    /**
     * Last token reissue, not proof of email delivery.
     */
    public Invite withReissuedAt(@Nullable String reissuedAt) {
        this.reissuedAt = reissuedAt;
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
        Invite other = (Invite) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.externalUserId, other.externalUserId)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.token, other.token)
                && Utils.enhancedDeepEquals(this.environments, other.environments)
                && Utils.enhancedDeepEquals(this.deniedActions, other.deniedActions)
                && Utils.enhancedDeepEquals(this.reissuedAt, other.reissuedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                workspaceId,
                role,
                email,
                externalUserId,
                createdBy,
                createdAt,
                expiresAt,
                token,
                environments,
                deniedActions,
                reissuedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Invite.class,
                "id",
                id,
                "workspaceId",
                workspaceId,
                "role",
                role,
                "email",
                email,
                "externalUserId",
                externalUserId,
                "createdBy",
                createdBy,
                "createdAt",
                createdAt,
                "expiresAt",
                expiresAt,
                "token",
                token,
                "environments",
                environments,
                "deniedActions",
                deniedActions,
                "reissuedAt",
                reissuedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String workspaceId;

        private Role role;

        private String email;

        private String externalUserId;

        private String createdBy;

        private String createdAt;

        private String expiresAt;

        private String token;

        private List<InviteEnvironment> environments;

        private List<InviteDeniedAction> deniedActions;

        private String reissuedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned invite id: inv_ and a 26-character ULID.
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
         * A built-in capability set. Specialist roles are independent; effective access is returned by GET
         * /v1/workspaces/{workspace_id}/capabilities.
         */
        public Builder role(@Nonnull Role role) {
            this.role = Utils.checkNotNull(role, "role");
            return this;
        }

        /**
         * Where the console sends the invite. Informational: it is not matched against the accepting user.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
         * characters with no space, compared exactly.
         */
        public Builder externalUserId(@Nullable String externalUserId) {
            this.externalUserId = externalUserId;
            return this;
        }

        /**
         * The stable member id that issued this invite, or the authenticated live API key prefix for
         * members_manage automation. Reissuing preserves the original creator; the audit event names the
         * reissuing actor.
         */
        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
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

        /**
         * The invite token: nrinv_, the invite id's ULID, _ and 43 unpadded base64url characters (32 random
         * bytes). The new member's console sends it to POST /v1/invites/accept.
         */
        public Builder token(@Nullable String token) {
            this.token = token;
            return this;
        }

        /**
         * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
         * grants; owners retain both.
         */
        public Builder environments(@Nullable List<InviteEnvironment> environments) {
            this.environments = environments;
            return this;
        }

        /**
         * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
         * publication. Owners retain full authority. Omitted member updates preserve exclusions.
         */
        public Builder deniedActions(@Nullable List<InviteDeniedAction> deniedActions) {
            this.deniedActions = deniedActions;
            return this;
        }

        /**
         * Last token reissue, not proof of email delivery.
         */
        public Builder reissuedAt(@Nullable String reissuedAt) {
            this.reissuedAt = reissuedAt;
            return this;
        }

        public Invite build() {
            return new Invite(
                    id,
                    workspaceId,
                    role,
                    email,
                    externalUserId,
                    createdBy,
                    createdAt,
                    expiresAt,
                    token,
                    environments,
                    deniedActions,
                    reissuedAt);
        }
    }
}
