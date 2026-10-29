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
 * InviteRequest
 *
 * <p>POST /v1/workspaces/{workspace_id}/invites body. Creates an invite for role and returns its token
 * once. email is where the console sends the token and is not checked against the user who accepts.
 *
 * <p>With external_user_id set, only that user can accept.
 */
public class InviteRequest {
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
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("environments")
    private List<InviteRequestEnvironment> environments;

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("denied_actions")
    private List<InviteRequestDeniedAction> deniedActions;

    @JsonCreator
    public InviteRequest(
            @JsonProperty("role") @Nonnull Role role,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("external_user_id") @Nullable String externalUserId,
            @JsonProperty("environments") @Nullable List<InviteRequestEnvironment> environments,
            @JsonProperty("denied_actions") @Nullable List<InviteRequestDeniedAction> deniedActions) {
        this.role = Optional.ofNullable(role).orElseThrow(() -> new IllegalArgumentException("role cannot be null"));
        this.email = email;
        this.externalUserId = externalUserId;
        this.environments = environments;
        this.deniedActions = deniedActions;
    }

    public InviteRequest(@Nonnull Role role) {
        this(role, null, null, null, null);
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
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Optional<List<InviteRequestEnvironment>> environments() {
        return Optional.ofNullable(this.environments);
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Optional<List<InviteRequestDeniedAction>> deniedActions() {
        return Optional.ofNullable(this.deniedActions);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public InviteRequest withRole(@Nonnull Role role) {
        this.role = Utils.checkNotNull(role, "role");
        return this;
    }

    /**
     * Where the console sends the invite. Informational: it is not matched against the accepting user.
     */
    public InviteRequest withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public InviteRequest withExternalUserId(@Nullable String externalUserId) {
        this.externalUserId = externalUserId;
        return this;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public InviteRequest withEnvironments(@Nullable List<InviteRequestEnvironment> environments) {
        this.environments = environments;
        return this;
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public InviteRequest withDeniedActions(@Nullable List<InviteRequestDeniedAction> deniedActions) {
        this.deniedActions = deniedActions;
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
        InviteRequest other = (InviteRequest) o;
        return Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.externalUserId, other.externalUserId)
                && Utils.enhancedDeepEquals(this.environments, other.environments)
                && Utils.enhancedDeepEquals(this.deniedActions, other.deniedActions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(role, email, externalUserId, environments, deniedActions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                InviteRequest.class,
                "role",
                role,
                "email",
                email,
                "externalUserId",
                externalUserId,
                "environments",
                environments,
                "deniedActions",
                deniedActions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Role role;

        private String email;

        private String externalUserId;

        private List<InviteRequestEnvironment> environments;

        private List<InviteRequestDeniedAction> deniedActions;

        private Builder() {
            // force use of static builder() method
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
         * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
         * grants; owners retain both.
         */
        public Builder environments(@Nullable List<InviteRequestEnvironment> environments) {
            this.environments = environments;
            return this;
        }

        /**
         * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
         * publication. Owners retain full authority. Omitted member updates preserve exclusions.
         */
        public Builder deniedActions(@Nullable List<InviteRequestDeniedAction> deniedActions) {
            this.deniedActions = deniedActions;
            return this;
        }

        public InviteRequest build() {
            return new InviteRequest(role, email, externalUserId, environments, deniedActions);
        }
    }
}
