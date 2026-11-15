package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteGroupRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @JsonCreator
    public DeleteGroupRequest(@Nonnull String groupId) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public DeleteGroupRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
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
        DeleteGroupRequest other = (DeleteGroupRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId);
    }

    @Override
    public String toString() {
        return Utils.toString(DeleteGroupRequest.class, "groupId", groupId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public DeleteGroupRequest build() {
            return new DeleteGroupRequest(groupId);
        }
    }
}
