package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.GroupUpdate;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class UpdateGroupRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("request:mediaType=application/json")
    private GroupUpdate body;

    @JsonCreator
    public UpdateGroupRequest(@Nonnull String groupId, @Nonnull GroupUpdate body) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public GroupUpdate body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UpdateGroupRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public UpdateGroupRequest withBody(@Nonnull GroupUpdate body) {
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
        UpdateGroupRequest other = (UpdateGroupRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId) && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(UpdateGroupRequest.class, "groupId", groupId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private GroupUpdate body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder body(@Nonnull GroupUpdate body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public UpdateGroupRequest build() {
            return new UpdateGroupRequest(groupId, body);
        }
    }
}
