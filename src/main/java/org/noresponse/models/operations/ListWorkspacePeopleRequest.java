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

    /**
     * Return only people whose email is this address, compared without case or surrounding spaces.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=email")
    private String email;

    /**
     * Return only people whose phone is this number, compared without surrounding spaces.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=phone")
    private String phone;

    @JsonCreator
    public ListWorkspacePeopleRequest(
            @Nullable Long limit, @Nullable String after, @Nullable String email, @Nullable String phone) {
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.after = after;
        this.email = email;
        this.phone = phone;
    }

    public ListWorkspacePeopleRequest() {
        this(null, null, null, null);
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

    /**
     * Return only people whose email is this address, compared without case or surrounding spaces.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * Return only people whose phone is this number, compared without surrounding spaces.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
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

    /**
     * Return only people whose email is this address, compared without case or surrounding spaces.
     */
    public ListWorkspacePeopleRequest withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * Return only people whose phone is this number, compared without surrounding spaces.
     */
    public ListWorkspacePeopleRequest withPhone(@Nullable String phone) {
        this.phone = phone;
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
        return Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.after, other.after)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.phone, other.phone);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(limit, after, email, phone);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListWorkspacePeopleRequest.class, "limit", limit, "after", after, "email", email, "phone", phone);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Long limit;

        private String after;

        private String email;

        private String phone;

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

        /**
         * Return only people whose email is this address, compared without case or surrounding spaces.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * Return only people whose phone is this number, compared without surrounding spaces.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        public ListWorkspacePeopleRequest build() {
            return new ListWorkspacePeopleRequest(limit, after, email, phone);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "100", new TypeReference<Long>() {});
    }
}
