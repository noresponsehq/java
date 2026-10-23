package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookDeliveryList
 *
 * <p>The 200 answer of GET /v1/webhooks/{endpoint_id}/deliveries: one page of the endpoint's delivery
 * attempts, newest first. Each item is one POST to the endpoint. Attempts are kept for 30 days, or the
 * workspace's retention_days when that is shorter, and are deleted with the assessment they carried.
 *
 * <p>next_cursor is present only when another page exists.
 */
public class WebhookDeliveryList {
    /**
     * Opaque id of the endpoint registration.
     */
    @JsonProperty("endpoint_id")
    private String endpointId;

    @JsonProperty("items")
    private List<WebhookDeliveryAttempt> items;

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace and endpoint it was
     * issued for. Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400
     * invalid_cursor.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public WebhookDeliveryList(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("items") @Nonnull List<WebhookDeliveryAttempt> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
    }

    public WebhookDeliveryList(@Nonnull String endpointId, @Nonnull List<WebhookDeliveryAttempt> items) {
        this(endpointId, items, null);
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public String endpointId() {
        return this.endpointId;
    }

    public List<WebhookDeliveryAttempt> items() {
        return this.items;
    }

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace and endpoint it was
     * issued for. Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400
     * invalid_cursor.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookDeliveryList withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public WebhookDeliveryList withItems(@Nonnull List<WebhookDeliveryAttempt> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace and endpoint it was
     * issued for. Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400
     * invalid_cursor.
     */
    public WebhookDeliveryList withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
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
        WebhookDeliveryList other = (WebhookDeliveryList) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookDeliveryList.class, "endpointId", endpointId, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private List<WebhookDeliveryAttempt> items;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public Builder items(@Nonnull List<WebhookDeliveryAttempt> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque cursor for the next page, MAC'd by the server and bound to the workspace and endpoint it was
         * issued for. Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400
         * invalid_cursor.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public WebhookDeliveryList build() {
            return new WebhookDeliveryList(endpointId, items, nextCursor);
        }
    }
}
