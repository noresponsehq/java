package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.InviteResendRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ResendInviteRequest {
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

    @NoResponseMetadata("request:mediaType=application/json")
    private InviteResendRequest body;

    @JsonCreator
    public ResendInviteRequest(
            @Nonnull String workspaceId, @Nonnull String inviteId, @Nonnull InviteResendRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.inviteId = Optional.ofNullable(inviteId)
                .orElseThrow(() -> new IllegalArgumentException("inviteId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
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

    public InviteResendRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ResendInviteRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     */
    public ResendInviteRequest withInviteId(@Nonnull String inviteId) {
        this.inviteId = Utils.checkNotNull(inviteId, "inviteId");
        return this;
    }

    public ResendInviteRequest withBody(@Nonnull InviteResendRequest body) {
        this.body = Utils.checkNotNull(body, "body");
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
        ResendInviteRequest other = (ResendInviteRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.inviteId, other.inviteId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, inviteId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ResendInviteRequest.class, "workspaceId", workspaceId, "inviteId", inviteId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String inviteId;

        private InviteResendRequest body;

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

        public Builder body(@Nonnull InviteResendRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ResendInviteRequest build() {
            return new ResendInviteRequest(workspaceId, inviteId, body);
        }
    }
}
