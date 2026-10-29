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
 * RequestAggregateRequest
 *
 * <p>Count every retained matching attempt in [from,to). Capture is best effort; missing attempts cannot
 * be inferred. UTC buckets may be partial at the edges.
 *
 * <p>The window is at most 7 days.
 */
public class RequestAggregateRequest {

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

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("route")
    private String route;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("api_key_id")
    private String apiKeyId;

    @JsonCreator
    public RequestAggregateRequest(
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("bucket") @Nullable RequestAggregateRequestBucket bucket,
            @JsonProperty("status") @Nullable Long status,
            @JsonProperty("route") @Nullable String route,
            @JsonProperty("api_key_id") @Nullable String apiKeyId) {
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.bucket = bucket;
        this.status = status;
        this.route = route;
        this.apiKeyId = apiKeyId;
    }

    public RequestAggregateRequest(@Nonnull String from, @Nonnull String to) {
        this(from, to, null, null, null, null);
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

    public Optional<String> route() {
        return Optional.ofNullable(this.route);
    }

    public Optional<String> apiKeyId() {
        return Optional.ofNullable(this.apiKeyId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RequestAggregateRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public RequestAggregateRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public RequestAggregateRequest withBucket(@Nullable RequestAggregateRequestBucket bucket) {
        this.bucket = bucket;
        return this;
    }

    public RequestAggregateRequest withStatus(@Nullable Long status) {
        this.status = status;
        return this;
    }

    public RequestAggregateRequest withRoute(@Nullable String route) {
        this.route = route;
        return this;
    }

    public RequestAggregateRequest withApiKeyId(@Nullable String apiKeyId) {
        this.apiKeyId = apiKeyId;
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
        RequestAggregateRequest other = (RequestAggregateRequest) o;
        return Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.bucket, other.bucket)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.route, other.route)
                && Utils.enhancedDeepEquals(this.apiKeyId, other.apiKeyId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(from, to, bucket, status, route, apiKeyId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RequestAggregateRequest.class,
                "from",
                from,
                "to",
                to,
                "bucket",
                bucket,
                "status",
                status,
                "route",
                route,
                "apiKeyId",
                apiKeyId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String from;

        private String to;

        private RequestAggregateRequestBucket bucket;

        private Long status;

        private String route;

        private String apiKeyId;

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

        public Builder route(@Nullable String route) {
            this.route = route;
            return this;
        }

        public Builder apiKeyId(@Nullable String apiKeyId) {
            this.apiKeyId = apiKeyId;
            return this;
        }

        public RequestAggregateRequest build() {
            return new RequestAggregateRequest(from, to, bucket, status, route, apiKeyId);
        }
    }
}
