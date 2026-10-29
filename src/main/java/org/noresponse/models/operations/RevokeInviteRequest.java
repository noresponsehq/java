package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RevokeInviteRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=invite_id")
    private String inviteId;

    @JsonCreator
    public RevokeInviteRequest(@Nonnull String workspaceId, @Nonnull String inviteId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.inviteId = Optional.ofNullable(inviteId)
                .orElseThrow(() -> new IllegalArgumentException("inviteId cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     */
    public String inviteId() {
        return this.inviteId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public RevokeInviteRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     */
    public RevokeInviteRequest withInviteId(@Nonnull String inviteId) {
        this.inviteId = Utils.checkNotNull(inviteId, "inviteId");
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
        RevokeInviteRequest other = (RevokeInviteRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.inviteId, other.inviteId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, inviteId);
    }

    @Override
    public String toString() {
        return Utils.toString(RevokeInviteRequest.class, "workspaceId", workspaceId, "inviteId", inviteId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String inviteId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
         * test environment's id answer 404.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
         */
        public Builder inviteId(@Nonnull String inviteId) {
            this.inviteId = Utils.checkNotNull(inviteId, "inviteId");
            return this;
        }

        public RevokeInviteRequest build() {
            return new RevokeInviteRequest(workspaceId, inviteId);
        }
    }
}
