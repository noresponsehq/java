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

public class ListWorkspacePeopleRequest {

    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Pass next_cursor from the prior page.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=after")
    private String after;

    @JsonCreator
    public ListWorkspacePeopleRequest(@Nullable Long limit, @Nullable String after) {
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.after = after;
    }

    public ListWorkspacePeopleRequest() {
        this(null, null);
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Pass next_cursor from the prior page.
     */
    public Optional<String> after() {
        return Optional.ofNullable(this.after);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListWorkspacePeopleRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Pass next_cursor from the prior page.
     */
    public ListWorkspacePeopleRequest withAfter(@Nullable String after) {
        this.after = after;
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
        ListWorkspacePeopleRequest other = (ListWorkspacePeopleRequest) o;
        return Utils.enhancedDeepEquals(this.limit, other.limit) && Utils.enhancedDeepEquals(this.after, other.after);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(limit, after);
    }

    @Override
    public String toString() {
        return Utils.toString(ListWorkspacePeopleRequest.class, "limit", limit, "after", after);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long limit;

        private String after;

        private Builder() {
            // force use of static builder() method
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Pass next_cursor from the prior page.
         */
        public Builder after(@Nullable String after) {
            this.after = after;
            return this;
        }

        public ListWorkspacePeopleRequest build() {
            return new ListWorkspacePeopleRequest(limit, after);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "100", new TypeReference<Long>() {});
    }
}
