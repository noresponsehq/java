package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CancelScreeningRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=screening_id")
    private String screeningId;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningCancelRequest body;

    @JsonCreator
    public CancelScreeningRequest(
            @Nonnull String groupId, @Nonnull String screeningId, @Nonnull ScreeningCancelRequest body) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.screeningId = Optional.ofNullable(screeningId)
                .orElseThrow(() -> new IllegalArgumentException("screeningId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public String screeningId() {
        return this.screeningId;
    }

    public ScreeningCancelRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CancelScreeningRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public CancelScreeningRequest withScreeningId(@Nonnull String screeningId) {
        this.screeningId = Utils.checkNotNull(screeningId, "screeningId");
        return this;
    }

    public CancelScreeningRequest withBody(@Nonnull ScreeningCancelRequest body) {
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
        CancelScreeningRequest other = (CancelScreeningRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.screeningId, other.screeningId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, screeningId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CancelScreeningRequest.class, "groupId", groupId, "screeningId", screeningId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String screeningId;

        private ScreeningCancelRequest body;

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

        public Builder body(@Nonnull ScreeningCancelRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CancelScreeningRequest build() {
            return new CancelScreeningRequest(groupId, screeningId, body);
        }
    }
}
