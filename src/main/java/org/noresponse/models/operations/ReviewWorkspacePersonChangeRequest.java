package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ReviewWorkspacePersonChangeRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=change_id")
    private String changeId;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningCancelRequest body;

    @JsonCreator
    public ReviewWorkspacePersonChangeRequest(
            @Nonnull String personId, @Nonnull String changeId, @Nonnull ScreeningCancelRequest body) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.changeId = Optional.ofNullable(changeId)
                .orElseThrow(() -> new IllegalArgumentException("changeId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String personId() {
        return this.personId;
    }

    public String changeId() {
        return this.changeId;
    }

    public ScreeningCancelRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewWorkspacePersonChangeRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public ReviewWorkspacePersonChangeRequest withChangeId(@Nonnull String changeId) {
        this.changeId = Utils.checkNotNull(changeId, "changeId");
        return this;
    }

    public ReviewWorkspacePersonChangeRequest withBody(@Nonnull ScreeningCancelRequest body) {
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
        ReviewWorkspacePersonChangeRequest other = (ReviewWorkspacePersonChangeRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.changeId, other.changeId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, changeId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewWorkspacePersonChangeRequest.class, "personId", personId, "changeId", changeId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private String changeId;

        private ScreeningCancelRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder changeId(@Nonnull String changeId) {
            this.changeId = Utils.checkNotNull(changeId, "changeId");
            return this;
        }

        public Builder body(@Nonnull ScreeningCancelRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public ReviewWorkspacePersonChangeRequest build() {
            return new ReviewWorkspacePersonChangeRequest(personId, changeId, body);
        }
    }
}
