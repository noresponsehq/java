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

public class ListWebhookDeliveriesRequest {
    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    /**
     * Attempts per page.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * next_cursor from the previous page, unchanged.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    @JsonCreator
    public ListWebhookDeliveriesRequest(@Nonnull String endpointId, @Nullable Long limit, @Nullable String cursor) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
    }

    public ListWebhookDeliveriesRequest(@Nonnull String endpointId) {
        this(endpointId, null, null);
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public String endpointId() {
        return this.endpointId;
    }

    /**
     * Attempts per page.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * next_cursor from the previous page, unchanged.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public ListWebhookDeliveriesRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    /**
     * Attempts per page.
     */
    public ListWebhookDeliveriesRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * next_cursor from the previous page, unchanged.
     */
    public ListWebhookDeliveriesRequest withCursor(@Nullable String cursor) {
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
        ListWebhookDeliveriesRequest other = (ListWebhookDeliveriesRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListWebhookDeliveriesRequest.class, "endpointId", endpointId, "limit", limit, "cursor", cursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private Long limit;

        private String cursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        /**
         * Attempts per page.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * next_cursor from the previous page, unchanged.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        public ListWebhookDeliveriesRequest build() {
            return new ListWebhookDeliveriesRequest(endpointId, limit, cursor);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
