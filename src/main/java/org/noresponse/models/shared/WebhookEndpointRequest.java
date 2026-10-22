package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookEndpointRequest
 *
 * <p>POST /v1/webhooks body. The tenant is the API key that signs the request, so it is not a body
 * member. The URL must be https on port 443 or 8443 with a DNS name, not an IP literal, and every
 * address the name resolves to must be public.
 */
public class WebhookEndpointRequest {
    /**
     * Where deliveries are sent: an https URL on port 443 or 8443, with a DNS host and no userinfo or
     * fragment, at most 2048 characters.
     */
    @JsonProperty("url")
    private String url;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    @JsonCreator
    public WebhookEndpointRequest(
            @JsonProperty("url") @Nonnull String url,
            @JsonProperty("response_mode") @Nullable ResponseMode responseMode) {
        this.url = Optional.ofNullable(url).orElseThrow(() -> new IllegalArgumentException("url cannot be null"));
        this.responseMode = responseMode;
    }

    public WebhookEndpointRequest(@Nonnull String url) {
        this(url, null);
    }

    /**
     * Where deliveries are sent: an https URL on port 443 or 8443, with a DNS host and no userinfo or
     * fragment, at most 2048 characters.
     */
    public String url() {
        return this.url;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Where deliveries are sent: an https URL on port 443 or 8443, with a DNS host and no userinfo or
     * fragment, at most 2048 characters.
     */
    public WebhookEndpointRequest withUrl(@Nonnull String url) {
        this.url = Utils.checkNotNull(url, "url");
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public WebhookEndpointRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
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
        WebhookEndpointRequest other = (WebhookEndpointRequest) o;
        return Utils.enhancedDeepEquals(this.url, other.url)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(url, responseMode);
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookEndpointRequest.class, "url", url, "responseMode", responseMode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String url;

        private ResponseMode responseMode;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Where deliveries are sent: an https URL on port 443 or 8443, with a DNS host and no userinfo or
         * fragment, at most 2048 characters.
         */
        public Builder url(@Nonnull String url) {
            this.url = Utils.checkNotNull(url, "url");
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
            return this;
        }

        public WebhookEndpointRequest build() {
            return new WebhookEndpointRequest(url, responseMode);
        }
    }
}
