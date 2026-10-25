package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListAccountsRequest {
    /**
     * next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=changed_since")
    private String changedSince;

    /**
     * Most accounts per page.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * true lists only accounts with no applied enforcement at their current risk_version that have a
     * decision other than allow for some flow or a hold no later applied enforcement resolved.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=unenforced")
    private Boolean unenforced;

    @JsonCreator
    public ListAccountsRequest(@Nullable String changedSince, @Nullable Long limit, @Nullable Boolean unenforced) {
        this.changedSince = changedSince;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.unenforced = Optional.ofNullable(unenforced).orElse(Builder._SINGLETON_VALUE_Unenforced.value());
    }

    public ListAccountsRequest() {
        this(null, null, null);
    }

    /**
     * next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.
     */
    public Optional<String> changedSince() {
        return Optional.ofNullable(this.changedSince);
    }

    /**
     * Most accounts per page.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * true lists only accounts with no applied enforcement at their current risk_version that have a
     * decision other than allow for some flow or a hold no later applied enforcement resolved.
     */
    public Optional<Boolean> unenforced() {
        return Optional.ofNullable(this.unenforced);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.
     */
    public ListAccountsRequest withChangedSince(@Nullable String changedSince) {
        this.changedSince = changedSince;
        return this;
    }

    /**
     * Most accounts per page.
     */
    public ListAccountsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * true lists only accounts with no applied enforcement at their current risk_version that have a
     * decision other than allow for some flow or a hold no later applied enforcement resolved.
     */
    public ListAccountsRequest withUnenforced(@Nullable Boolean unenforced) {
        this.unenforced = unenforced;
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
        ListAccountsRequest other = (ListAccountsRequest) o;
        return Utils.enhancedDeepEquals(this.changedSince, other.changedSince)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.unenforced, other.unenforced);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(changedSince, limit, unenforced);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListAccountsRequest.class, "changedSince", changedSince, "limit", limit, "unenforced", unenforced);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String changedSince;

        private Long limit;

        private Boolean unenforced;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.
         */
        public Builder changedSince(@Nullable String changedSince) {
            this.changedSince = changedSince;
            return this;
        }

        /**
         * Most accounts per page.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * true lists only accounts with no applied enforcement at their current risk_version that have a
         * decision other than allow for some flow or a hold no later applied enforcement resolved.
         */
        public Builder unenforced(@Nullable Boolean unenforced) {
            this.unenforced = unenforced;
            return this;
        }

        public ListAccountsRequest build() {
            return new ListAccountsRequest(changedSince, limit, unenforced);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_Unenforced =
                new LazySingletonValue<>("unenforced", "false", new TypeReference<Boolean>() {});
    }
}
