package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListInvestigationsRequest {

    @NoResponseMetadata("queryParam:style=form,explode=true,name=person_id")
    private String personId;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=before")
    private String before;

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    @JsonCreator
    public ListInvestigationsRequest(@Nullable String personId, @Nullable String before, @Nullable Long limit) {
        this.personId = personId;
        this.before = before;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
    }

    public ListInvestigationsRequest() {
        this(null, null, null);
    }

    public Optional<String> personId() {
        return Optional.ofNullable(this.personId);
    }

    public Optional<String> before() {
        return Optional.ofNullable(this.before);
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListInvestigationsRequest withPersonId(@Nullable String personId) {
        this.personId = personId;
        return this;
    }

    public ListInvestigationsRequest withBefore(@Nullable String before) {
        this.before = before;
        return this;
    }

    public ListInvestigationsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
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
        ListInvestigationsRequest other = (ListInvestigationsRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.before, other.before)
                && Utils.enhancedDeepEquals(this.limit, other.limit);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, before, limit);
    }

    @Override
    public String toString() {
        return Utils.toString(ListInvestigationsRequest.class, "personId", personId, "before", before, "limit", limit);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private String before;

        private Long limit;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nullable String personId) {
            this.personId = personId;
            return this;
        }

        public Builder before(@Nullable String before) {
            this.before = before;
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public ListInvestigationsRequest build() {
            return new ListInvestigationsRequest(personId, before, limit);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "100", new TypeReference<Long>() {});
    }
}
