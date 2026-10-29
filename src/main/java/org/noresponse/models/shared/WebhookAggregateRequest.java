package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WebhookAggregateRequest
 *
 * <p>Count every retained matching attempt in [from,to). Capture is best effort; missing attempts cannot
 * be inferred. UTC buckets may be partial at the edges.
 *
 * <p>The window is at most 30 days.
 */
public class WebhookAggregateRequest {

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("bucket")
    private RequestAggregateRequestBucket bucket;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("status")
    private Long status;

    /**
     * Opaque id of the endpoint registration.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("endpoint_id")
    private String endpointId;

    @JsonCreator
    public WebhookAggregateRequest(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("bucket") @Nullable RequestAggregateRequestBucket bucket,
            @JsonProperty("status") @Nullable Long status,
            @JsonProperty("endpoint_id") @Nullable String endpointId) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.bucket = bucket;
        this.status = status;
        this.endpointId = endpointId;
    }

    public WebhookAggregateRequest(@Nonnull String from, @Nonnull String to) {
        this(from, to, null, null, null);
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public Optional<RequestAggregateRequestBucket> bucket() {
        return Optional.ofNullable(this.bucket);
    }

    public Optional<Long> status() {
        return Optional.ofNullable(this.status);
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public Optional<String> endpointId() {
        return Optional.ofNullable(this.endpointId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public WebhookAggregateRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public WebhookAggregateRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public WebhookAggregateRequest withBucket(@Nullable RequestAggregateRequestBucket bucket) {
        this.bucket = bucket;
        return this;
    }

    public WebhookAggregateRequest withStatus(@Nullable Long status) {
        this.status = status;
        return this;
    }

    /**
     * Opaque id of the endpoint registration.
     */
    public WebhookAggregateRequest withEndpointId(@Nullable String endpointId) {
        this.endpointId = endpointId;
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
        WebhookAggregateRequest other = (WebhookAggregateRequest) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.bucket, other.bucket)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.endpointId, other.endpointId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(from, to, bucket, status, endpointId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WebhookAggregateRequest.class,
                "from",
                from,
                "to",
                to,
                "bucket",
                bucket,
                "status",
                status,
                "endpointId",
                endpointId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private RequestAggregateRequestBucket bucket;

        private Long status;

        private String endpointId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        public Builder bucket(@Nullable RequestAggregateRequestBucket bucket) {
            this.bucket = bucket;
            return this;
        }

        public Builder status(@Nullable Long status) {
            this.status = status;
            return this;
        }

        /**
         * Opaque id of the endpoint registration.
         */
        public Builder endpointId(@Nullable String endpointId) {
            this.endpointId = endpointId;
            return this;
        }

        public WebhookAggregateRequest build() {
            return new WebhookAggregateRequest(from, to, bucket, status, endpointId);
        }
    }
}
