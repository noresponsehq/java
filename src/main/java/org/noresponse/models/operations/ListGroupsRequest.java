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

public class ListGroupsRequest {
    /**
     * At most this many items.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Read groups older than this one.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=before")
    private String before;

    @JsonCreator
    public ListGroupsRequest(@Nullable Long limit, @Nullable String before) {
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.before = before;
    }

    public ListGroupsRequest() {
        this(null, null);
    }

    /**
     * At most this many items.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Read groups older than this one.
     */
    public Optional<String> before() {
        return Optional.ofNullable(this.before);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * At most this many items.
     */
    public ListGroupsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Read groups older than this one.
     */
    public ListGroupsRequest withBefore(@Nullable String before) {
        this.before = before;
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
        ListGroupsRequest other = (ListGroupsRequest) o;
        return Utils.enhancedDeepEquals(this.limit, other.limit) && Utils.enhancedDeepEquals(this.before, other.before);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(limit, before);
    }

    @Override
    public String toString() {
        return Utils.toString(ListGroupsRequest.class, "limit", limit, "before", before);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long limit;

        private String before;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * At most this many items.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Read groups older than this one.
         */
        public Builder before(@Nullable String before) {
            this.before = before;
            return this;
        }

        public ListGroupsRequest build() {
            return new ListGroupsRequest(limit, before);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
