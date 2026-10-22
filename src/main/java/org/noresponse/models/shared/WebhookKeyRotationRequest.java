package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * WebhookKeyRotationRequest
 *
 * <p>POST /v1/webhooks/{endpoint_id}/rotate body: an empty object. The old key keeps verifying for 7
 * days.
 */
public class WebhookKeyRotationRequest {
    @JsonCreator
    public WebhookKeyRotationRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash();
    }

    @Override
    public String toString() {
        return Utils.toString(WebhookKeyRotationRequest.class);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Builder() {
            // force use of static builder() method
        }

        public WebhookKeyRotationRequest build() {
            return new WebhookKeyRotationRequest();
        }
    }
}
