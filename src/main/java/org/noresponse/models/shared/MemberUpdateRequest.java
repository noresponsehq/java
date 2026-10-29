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
 * MemberUpdateRequest
 *
 * <p>PATCH /v1/workspaces/{workspace_id}/members/{member_id} body: the member's new role. The workspace's
 * last owner cannot be demoted.
 */
public class MemberUpdateRequest {
    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    @JsonProperty("role")
    private Role role;

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("environments")
    private List<MemberUpdateRequestEnvironment> environments;

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("denied_actions")
    private List<MemberUpdateRequestDeniedAction> deniedActions;

    @JsonCreator
    public MemberUpdateRequest(
            @JsonProperty("role") @Nonnull Role role,
            @JsonProperty("environments") @Nullable List<MemberUpdateRequestEnvironment> environments,
            @JsonProperty("denied_actions") @Nullable List<MemberUpdateRequestDeniedAction> deniedActions) {
        this.role = Optional.ofNullable(role).orElseThrow(() -> new IllegalArgumentException("role cannot be null"));
        this.environments = environments;
        this.deniedActions = deniedActions;
    }

    public MemberUpdateRequest(@Nonnull Role role) {
        this(role, null, null);
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public Role role() {
        return this.role;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public Optional<List<MemberUpdateRequestEnvironment>> environments() {
        return Optional.ofNullable(this.environments);
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public Optional<List<MemberUpdateRequestDeniedAction>> deniedActions() {
        return Optional.ofNullable(this.deniedActions);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A built-in capability set. Specialist roles are independent; effective access is returned by GET
     * /v1/workspaces/{workspace_id}/capabilities.
     */
    public MemberUpdateRequest withRole(@Nonnull Role role) {
        this.role = Utils.checkNotNull(role, "role");
        return this;
    }

    /**
     * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
     * grants; owners retain both.
     */
    public MemberUpdateRequest withEnvironments(@Nullable List<MemberUpdateRequestEnvironment> environments) {
        this.environments = environments;
        return this;
    }

    /**
     * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
     * publication. Owners retain full authority. Omitted member updates preserve exclusions.
     */
    public MemberUpdateRequest withDeniedActions(@Nullable List<MemberUpdateRequestDeniedAction> deniedActions) {
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
        MemberUpdateRequest other = (MemberUpdateRequest) o;
        return Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.environments, other.environments)
                && Utils.enhancedDeepEquals(this.deniedActions, other.deniedActions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(role, environments, deniedActions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                MemberUpdateRequest.class, "role", role, "environments", environments, "deniedActions", deniedActions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Role role;

        private List<MemberUpdateRequestEnvironment> environments;

        private List<MemberUpdateRequestDeniedAction> deniedActions;

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
         * Allowed data environments. Invites default to both. Omitted on member PATCH preserves the current
         * grants; owners retain both.
         */
        public Builder environments(@Nullable List<MemberUpdateRequestEnvironment> environments) {
            this.environments = environments;
            return this;
        }

        /**
         * Independent removals from a built-in preset: sensitive evidence, exports, or active policy
         * publication. Owners retain full authority. Omitted member updates preserve exclusions.
         */
        public Builder deniedActions(@Nullable List<MemberUpdateRequestDeniedAction> deniedActions) {
            this.deniedActions = deniedActions;
            return this;
        }

        public MemberUpdateRequest build() {
            return new MemberUpdateRequest(role, environments, deniedActions);
        }
    }
}
