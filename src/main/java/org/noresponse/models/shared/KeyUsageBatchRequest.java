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
 * KeyUsageBatchRequest
 *
 * <p>POST /v1/key_usage/batches body: facts about requests made with API keys your platform issued to
 * your accounts, one event per request. Each event names the key by the SHA-256 that the assessment's
 * issued_credential.sha256 carried, not the key itself. The SDKs buffer events and send them in the
 * background.
 *
 * <p>A batch holds 1 to 500 events and at most 256 KiB. Idempotency-Key is required and binds the body as
 * on POST /v1/assessments, so a retried batch is counted once.
 */
public class KeyUsageBatchRequest {

    @JsonProperty("events")
    private List<KeyUsageEvent> events;

    @JsonCreator
    public KeyUsageBatchRequest(@JsonProperty("events") @Nonnull List<KeyUsageEvent> events) {
        this.events =
                Optional.ofNullable(events).orElseThrow(() -> new IllegalArgumentException("events cannot be null"));
    }

    public List<KeyUsageEvent> events() {
        return this.events;
    }

    public static Builder builder() {
        return new Builder();
    }

    public KeyUsageBatchRequest withEvents(@Nonnull List<KeyUsageEvent> events) {
        this.events = Utils.checkNotNull(events, "events");
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
        KeyUsageBatchRequest other = (KeyUsageBatchRequest) o;
        return Utils.enhancedDeepEquals(this.events, other.events);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(events);
    }

    @Override
    public String toString() {
        return Utils.toString(KeyUsageBatchRequest.class, "events", events);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<KeyUsageEvent> events;

        private Builder() {
            // force use of static builder() method
        }

        public Builder events(@Nonnull List<KeyUsageEvent> events) {
            this.events = Utils.checkNotNull(events, "events");
            return this;
        }

        public KeyUsageBatchRequest build() {
            return new KeyUsageBatchRequest(events);
        }
    }
}
