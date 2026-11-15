package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListScreeningsRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    /**
     * At most this many items.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Read screenings older than this one.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=before")
    private String before;

    @JsonCreator
    public ListScreeningsRequest(@Nonnull String groupId, @Nullable Long limit, @Nullable String before) {
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.before = before;
    }

    public ListScreeningsRequest(@Nonnull String groupId) {
        this(groupId, null, null);
    }

    public String groupId() {
        return this.groupId;
    }

    /**
     * At most this many items.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Read screenings older than this one.
     */
    public Optional<String> before() {
        return Optional.ofNullable(this.before);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListScreeningsRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * At most this many items.
     */
    public ListScreeningsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Read screenings older than this one.
     */
    public ListScreeningsRequest withBefore(@Nullable String before) {
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
        ListScreeningsRequest other = (ListScreeningsRequest) o;
        return Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.before, other.before);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(groupId, limit, before);
    }

    @Override
    public String toString() {
        return Utils.toString(ListScreeningsRequest.class, "groupId", groupId, "limit", limit, "before", before);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String groupId;

        private Long limit;

        private String before;

        private Builder() {
            // force use of static builder() method
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * At most this many items.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Read screenings older than this one.
         */
        public Builder before(@Nullable String before) {
            this.before = before;
            return this;
        }

        public ListScreeningsRequest build() {
            return new ListScreeningsRequest(groupId, limit, before);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
