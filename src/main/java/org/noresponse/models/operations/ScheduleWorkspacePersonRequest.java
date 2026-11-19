package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.PersonScheduleRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ScheduleWorkspacePersonRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("request:mediaType=application/json")
    private PersonScheduleRequest body;

    @JsonCreator
    public ScheduleWorkspacePersonRequest(@Nonnull String personId, @Nonnull PersonScheduleRequest body) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String personId() {
        return this.personId;
    }

    public PersonScheduleRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScheduleWorkspacePersonRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public ScheduleWorkspacePersonRequest withBody(@Nonnull PersonScheduleRequest body) {
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
        ScheduleWorkspacePersonRequest other = (ScheduleWorkspacePersonRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId) && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(ScheduleWorkspacePersonRequest.class, "personId", personId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private PersonScheduleRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder body(@Nonnull PersonScheduleRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ScheduleWorkspacePersonRequest build() {
            return new ScheduleWorkspacePersonRequest(personId, body);
        }
    }
}
