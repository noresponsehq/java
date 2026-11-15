package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetPersonRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @JsonCreator
    public GetPersonRequest(@Nonnull String groupId, @Nonnull String personId) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
    }

    public String groupId() {
        return this.groupId;
    }

    public String personId() {
        return this.personId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public GetPersonRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    public GetPersonRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
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
        GetPersonRequest other = (GetPersonRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.personId, other.personId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, personId);
    }

    @Override
    public String toString() {
        return Utils.toString(GetPersonRequest.class, "groupId", groupId, "personId", personId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private String personId;

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

        public GetPersonRequest build() {
            return new GetPersonRequest(groupId, personId);
        }
    }
}
