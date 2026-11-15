package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetWorkspacePersonRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @JsonCreator
    public GetWorkspacePersonRequest(@Nonnull String personId) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
    }

    public String personId() {
        return this.personId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public GetWorkspacePersonRequest withPersonId(@Nonnull String personId) {
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
        GetWorkspacePersonRequest other = (GetWorkspacePersonRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId);
    }

    @Override
    public String toString() {
        return Utils.toString(GetWorkspacePersonRequest.class, "personId", personId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public GetWorkspacePersonRequest build() {
            return new GetWorkspacePersonRequest(personId);
        }
    }
}
