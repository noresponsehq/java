package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * WebhookReplayRequest
 *
 * <p>POST /v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay body: an empty object.
 */
public class WebhookReplayRequest {
    @JsonCreator
    public WebhookReplayRequest() {}

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
        return Utils.toString(WebhookReplayRequest.class);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Builder() {
            // force use of static builder() method
        }

        public WebhookReplayRequest build() {
            return new WebhookReplayRequest();
        }
    }
}
