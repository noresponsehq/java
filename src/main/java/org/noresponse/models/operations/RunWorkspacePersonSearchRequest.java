package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RunWorkspacePersonSearchRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=search_id")
    private String searchId;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningCancelRequest body;

    @JsonCreator
    public RunWorkspacePersonSearchRequest(
            @Nonnull String personId, @Nonnull String searchId, @Nonnull ScreeningCancelRequest body) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.searchId = Optional.ofNullable(searchId)
                .orElseThrow(() -> new IllegalArgumentException("searchId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
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

    public RunWorkspacePersonSearchRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public RunWorkspacePersonSearchRequest withSearchId(@Nonnull String searchId) {
        this.searchId = Utils.checkNotNull(searchId, "searchId");
        return this;
    }

    public RunWorkspacePersonSearchRequest withBody(@Nonnull ScreeningCancelRequest body) {
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
        RunWorkspacePersonSearchRequest other = (RunWorkspacePersonSearchRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.searchId, other.searchId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, searchId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RunWorkspacePersonSearchRequest.class, "personId", personId, "searchId", searchId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private String searchId;

        private ScreeningCancelRequest body;

        private Builder() {
            // force use of static builder() method
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

        public RunWorkspacePersonSearchRequest build() {
            return new RunWorkspacePersonSearchRequest(personId, searchId, body);
        }
    }
}
