package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetScreeningRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=screening_id")
    private String screeningId;

    @JsonCreator
    public GetScreeningRequest(@Nonnull String groupId, @Nonnull String screeningId) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.screeningId = Optional.ofNullable(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("screeningId cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public String screeningId() {
        return this.screeningId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public GetScreeningRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public GetScreeningRequest withScreeningId(@Nonnull String screeningId) {
        this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
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
        GetScreeningRequest other = (GetScreeningRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.screeningId, other.screeningId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, screeningId);
    }

    @Override
    public String toString() {
        return Utils.toString(GetScreeningRequest.class, "groupId", groupId, "screeningId", screeningId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String screeningId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder screeningId(@Nonnull String screeningId) {
            this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
            return this;
        }

        public GetScreeningRequest build() {
            return new GetScreeningRequest(groupId, screeningId);
        }
    }
}
