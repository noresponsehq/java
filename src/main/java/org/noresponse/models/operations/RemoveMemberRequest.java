package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RemoveMemberRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=member_id")
    private String memberId;

    @JsonCreator
    public RemoveMemberRequest(@Nonnull String workspaceId, @Nonnull String memberId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.memberId = Optional.ofNullable(memberId)
                .orElseThrow(() -> new IllegalArgumentException("memberId cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     */
    public String memberId() {
        return this.memberId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public RemoveMemberRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     */
    public RemoveMemberRequest withMemberId(@Nonnull String memberId) {
        this.memberId = Utils.checkNotNull(memberId, "memberId");
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
        RemoveMemberRequest other = (RemoveMemberRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.memberId, other.memberId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, memberId);
    }

    @Override
    public String toString() {
        return Utils.toString(RemoveMemberRequest.class, "workspaceId", workspaceId, "memberId", memberId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String memberId;

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
         * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
         */
        public Builder memberId(@Nonnull String memberId) {
            this.memberId = Utils.checkNotNull(memberId, "memberId");
            return this;
        }

        public RemoveMemberRequest build() {
            return new RemoveMemberRequest(workspaceId, memberId);
        }
    }
}
