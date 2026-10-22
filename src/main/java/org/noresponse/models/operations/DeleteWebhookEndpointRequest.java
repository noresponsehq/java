package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteWebhookEndpointRequest {
    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=endpoint_id")
    private String endpointId;

    @JsonCreator
    public DeleteWebhookEndpointRequest(@Nonnull String endpointId) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public String endpointId() {
        return this.endpointId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.
     */
    public DeleteWebhookEndpointRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
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
        DeleteWebhookEndpointRequest other = (DeleteWebhookEndpointRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId);
    }

    @Override
    public String toString() {
        return Utils.toString(DeleteWebhookEndpointRequest.class, "endpointId", endpointId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

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

        public DeleteWebhookEndpointRequest build() {
            return new DeleteWebhookEndpointRequest(endpointId);
        }
    }
}
