package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class UpdateMemberRequest {
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

    @NoResponseMetadata("request:mediaType=application/json")
    private MemberUpdateRequest body;

    @JsonCreator
    public UpdateMemberRequest(
            @Nonnull String workspaceId, @Nonnull String memberId, @Nonnull MemberUpdateRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.memberId = Optional.ofNullable(memberId)
                .orElseThrow(() -> new IllegalArgumentException("memberId cannot be null"));
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
     * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     */
    public String memberId() {
        return this.memberId;
    }

    public MemberUpdateRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public UpdateMemberRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     */
    public UpdateMemberRequest withMemberId(@Nonnull String memberId) {
        this.memberId = Utils.checkNotNull(memberId, "memberId");
        return this;
    }

    public UpdateMemberRequest withBody(@Nonnull MemberUpdateRequest body) {
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
        UpdateMemberRequest other = (UpdateMemberRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.memberId, other.memberId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, memberId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                UpdateMemberRequest.class, "workspaceId", workspaceId, "memberId", memberId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String memberId;

        private MemberUpdateRequest body;

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

        public Builder body(@Nonnull MemberUpdateRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public UpdateMemberRequest build() {
            return new UpdateMemberRequest(workspaceId, memberId, body);
        }
    }
}
