package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RunSearchRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=search_id")
    private String searchId;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningCancelRequest body;

    @JsonCreator
    public RunSearchRequest(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull String searchId,
            @Nonnull ScreeningCancelRequest body) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.searchId = Optional.ofNullable(searchId)
                .orElseThrow(() -> new IllegalArgumentException("searchId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public String personId() {
        return this.personId;
    }

    public String searchId() {
        return this.searchId;
    }

    public ScreeningCancelRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RunSearchRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public RunSearchRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public RunSearchRequest withSearchId(@Nonnull String searchId) {
        this.searchId = Utils.checkNotNull(searchId, "searchId");
        return this;
    }

    public RunSearchRequest withBody(@Nonnull ScreeningCancelRequest body) {
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
        RunSearchRequest other = (RunSearchRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.searchId, other.searchId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, personId, searchId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RunSearchRequest.class, "groupId", groupId, "personId", personId, "searchId", searchId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String personId;

        private String searchId;

        private ScreeningCancelRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder searchId(@Nonnull String searchId) {
            this.searchId = Utils.checkNotNull(searchId, "searchId");
            return this;
        }

        public Builder body(@Nonnull ScreeningCancelRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public RunSearchRequest build() {
            return new RunSearchRequest(groupId, personId, searchId, body);
        }
    }
}
