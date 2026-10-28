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

public class ListRequestDiagnosticsRequest {
    /**
     * Inclusive start of a range of at most seven days.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=from")
    private String from;

    /**
     * Exclusive end of the range.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=to")
    private String to;

    /**
     * Optional filter or paging parameter.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=request_id")
    private String requestId;

    /**
     * Optional filter or paging parameter.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=route")
    private String route;

    /**
     * Optional filter or paging parameter.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=status")
    private Long status;

    /**
     * Optional filter or paging parameter.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Optional filter or paging parameter.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    @JsonCreator
    public ListRequestDiagnosticsRequest(
            @Nonnull String from,
            @Nonnull String to,
            @Nullable String requestId,
            @Nullable String route,
            @Nullable Long status,
            @Nullable Long limit,
            @Nullable String cursor) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.requestId = requestId;
        this.route = route;
        this.status = status;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
    }

    public ListRequestDiagnosticsRequest(@Nonnull String from, @Nonnull String to) {
        this(from, to, null, null, null, null, null);
    }

    /**
     * Inclusive start of a range of at most seven days.
     */
    public String from() {
        return this.from;
    }

    /**
     * Exclusive end of the range.
     */
    public String to() {
        return this.to;
    }

    /**
     * Optional filter or paging parameter.
     */
    public Optional<String> requestId() {
        return Optional.ofNullable(this.requestId);
    }

    /**
     * Optional filter or paging parameter.
     */
    public Optional<String> route() {
        return Optional.ofNullable(this.route);
    }

    /**
     * Optional filter or paging parameter.
     */
    public Optional<Long> status() {
        return Optional.ofNullable(this.status);
    }

    /**
     * Optional filter or paging parameter.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Optional filter or paging parameter.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Inclusive start of a range of at most seven days.
     */
    public ListRequestDiagnosticsRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    /**
     * Exclusive end of the range.
     */
    public ListRequestDiagnosticsRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * Optional filter or paging parameter.
     */
    public ListRequestDiagnosticsRequest withRequestId(@Nullable String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Optional filter or paging parameter.
     */
    public ListRequestDiagnosticsRequest withRoute(@Nullable String route) {
        this.route = route;
        return this;
    }

    /**
     * Optional filter or paging parameter.
     */
    public ListRequestDiagnosticsRequest withStatus(@Nullable Long status) {
        this.status = status;
        return this;
    }

    /**
     * Optional filter or paging parameter.
     */
    public ListRequestDiagnosticsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Optional filter or paging parameter.
     */
    public ListRequestDiagnosticsRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
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
        ListRequestDiagnosticsRequest other = (ListRequestDiagnosticsRequest) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId)
                && Utils.enhancedDeepEquals(this.route, other.route)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(from, to, requestId, route, status, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListRequestDiagnosticsRequest.class,
                "from",
                from,
                "to",
                to,
                "requestId",
                requestId,
                "route",
                route,
                "status",
                status,
                "limit",
                limit,
                "cursor",
                cursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private String requestId;

        private String route;

        private Long status;

        private Long limit;

        private String cursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Inclusive start of a range of at most seven days.
         */
        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        /**
         * Exclusive end of the range.
         */
        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * Optional filter or paging parameter.
         */
        public Builder requestId(@Nullable String requestId) {
            this.requestId = requestId;
            return this;
        }

        /**
         * Optional filter or paging parameter.
         */
        public Builder route(@Nullable String route) {
            this.route = route;
            return this;
        }

        /**
         * Optional filter or paging parameter.
         */
        public Builder status(@Nullable Long status) {
            this.status = status;
            return this;
        }

        /**
         * Optional filter or paging parameter.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Optional filter or paging parameter.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        public ListRequestDiagnosticsRequest build() {
            return new ListRequestDiagnosticsRequest(from, to, requestId, route, status, limit, cursor);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});
    }
}
