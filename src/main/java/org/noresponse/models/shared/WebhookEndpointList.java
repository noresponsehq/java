package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookEndpointList
 *
 * <p>GET /v1/webhooks answer: every endpoint of the calling tenant, in every state, ordered by
 * endpoint_id. A tenant holds at most 5, so there is no page.
 */
public class WebhookEndpointList {

    @JsonProperty("items")
    private List<WebhookEndpoint> items;

    @JsonCreator
    public WebhookEndpointList(@JsonProperty("items") @Nonnull List<WebhookEndpoint> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<WebhookEndpoint> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public WebhookEndpointList withItems(@Nonnull List<WebhookEndpoint> items) {
        this.items = Utils.checkNotNull(items, "items");
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
        WebhookEndpointList other = (WebhookEndpointList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookEndpointList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<WebhookEndpoint> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<WebhookEndpoint> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public WebhookEndpointList build() {
            return new WebhookEndpointList(items);
        }
    }
}
