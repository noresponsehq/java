package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Double;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * KeyUsageEvent
 *
 * <p>One request made with an issued key. An event whose ip is not a public address, or whose occurred_at
 * is more than 24 hours old or more than 5 minutes ahead, is counted in ignored and adds nothing.
 */
public class KeyUsageEvent {
    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters: the value sent as
     * issued_credential.sha256 when the account was assessed. Each SDK's issued-credential helper computes
     * it.
     */
    @JsonProperty("key_sha256")
    private String keySha256;

    /**
     * The client IP address the request came from, IPv4 or IPv6, as your server saw it. Stored only as a
     * keyed digest and its country.
     */
    @JsonProperty("ip")
    private String ip;

    /**
     * When the request was made.
     */
    @JsonProperty("occurred_at")
    private String occurredAt;

    /**
     * The request's User-Agent header, at most 512 characters. Stored only as a keyed digest.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("user_agent")
    private String userAgent;

    /**
     * The model or product the request used, such as gpt-4o-mini. Stored only as a keyed digest.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("model")
    private String model;

    /**
     * Input tokens the request consumed.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("input_tokens")
    private Long inputTokens;

    /**
     * Output tokens the request produced.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("output_tokens")
    private Long outputTokens;

    /**
     * The share of requests your sampler records, when it records only some: 0.1 means this event stands
     * for 10 requests. Request and token totals are weighted by its inverse. Distinct IP and country
     * counts see only the events sent.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("sample_rate")
    private Double sampleRate;

    @JsonCreator
    public KeyUsageEvent(
            @JsonProperty("key_sha256") @Nonnull String keySha256,
            @JsonProperty("ip") @Nonnull String ip,
            @JsonProperty("occurred_at") @Nonnull String occurredAt,
            @JsonProperty("user_agent") @Nullable String userAgent,
            @JsonProperty("model") @Nullable String model,
            @JsonProperty("input_tokens") @Nullable Long inputTokens,
            @JsonProperty("output_tokens") @Nullable Long outputTokens,
            @JsonProperty("sample_rate") @Nullable Double sampleRate) {
        this.keySha256 = Optional.ofNullable(keySha256)
                .orElseThrow(() -> new IllegalArgumentException("keySha256 cannot be null"));
        this.ip = Optional.ofNullable(ip).orElseThrow(() -> new IllegalArgumentException("ip cannot be null"));
        this.occurredAt = Optional.ofNullable(occurredAt)
                .orElseThrow(() -> new IllegalArgumentException("occurredAt cannot be null"));
        this.userAgent = userAgent;
        this.model = model;
        this.inputTokens = inputTokens;
        this.outputTokens = outputTokens;
        this.sampleRate = Optional.ofNullable(sampleRate).orElse(Builder._SINGLETON_VALUE_SampleRate.value());
    }

    public KeyUsageEvent(@Nonnull String keySha256, @Nonnull String ip, @Nonnull String occurredAt) {
        this(keySha256, ip, occurredAt, null, null, null, null, null);
    }

    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters: the value sent as
     * issued_credential.sha256 when the account was assessed. Each SDK's issued-credential helper computes
     * it.
     */
    public String keySha256() {
        return this.keySha256;
    }

    /**
     * The client IP address the request came from, IPv4 or IPv6, as your server saw it. Stored only as a
     * keyed digest and its country.
     */
    public String ip() {
        return this.ip;
    }

    /**
     * When the request was made.
     */
    public String occurredAt() {
        return this.occurredAt;
    }

    /**
     * The request's User-Agent header, at most 512 characters. Stored only as a keyed digest.
     */
    public Optional<String> userAgent() {
        return Optional.ofNullable(this.userAgent);
    }

    /**
     * The model or product the request used, such as gpt-4o-mini. Stored only as a keyed digest.
     */
    public Optional<String> model() {
        return Optional.ofNullable(this.model);
    }

    /**
     * Input tokens the request consumed.
     */
    public Optional<Long> inputTokens() {
        return Optional.ofNullable(this.inputTokens);
    }

    /**
     * Output tokens the request produced.
     */
    public Optional<Long> outputTokens() {
        return Optional.ofNullable(this.outputTokens);
    }

    /**
     * The share of requests your sampler records, when it records only some: 0.1 means this event stands
     * for 10 requests. Request and token totals are weighted by its inverse. Distinct IP and country
     * counts see only the events sent.
     */
    public Optional<Double> sampleRate() {
        return Optional.ofNullable(this.sampleRate);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters: the value sent as
     * issued_credential.sha256 when the account was assessed. Each SDK's issued-credential helper computes
     * it.
     */
    public KeyUsageEvent withKeySha256(@Nonnull String keySha256) {
        this.keySha256 = Utils.checkNotNull(keySha256, "keySha256");
        return this;
    }

    /**
     * The client IP address the request came from, IPv4 or IPv6, as your server saw it. Stored only as a
     * keyed digest and its country.
     */
    public KeyUsageEvent withIp(@Nonnull String ip) {
        this.ip = Utils.checkNotNull(ip, "ip");
        return this;
    }

    /**
     * When the request was made.
     */
    public KeyUsageEvent withOccurredAt(@Nonnull String occurredAt) {
        this.occurredAt = Utils.checkNotNull(occurredAt, "occurredAt");
        return this;
    }

    /**
     * The request's User-Agent header, at most 512 characters. Stored only as a keyed digest.
     */
    public KeyUsageEvent withUserAgent(@Nullable String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * The model or product the request used, such as gpt-4o-mini. Stored only as a keyed digest.
     */
    public KeyUsageEvent withModel(@Nullable String model) {
        this.model = model;
        return this;
    }

    /**
     * Input tokens the request consumed.
     */
    public KeyUsageEvent withInputTokens(@Nullable Long inputTokens) {
        this.inputTokens = inputTokens;
        return this;
    }

    /**
     * Output tokens the request produced.
     */
    public KeyUsageEvent withOutputTokens(@Nullable Long outputTokens) {
        this.outputTokens = outputTokens;
        return this;
    }

    /**
     * The share of requests your sampler records, when it records only some: 0.1 means this event stands
     * for 10 requests. Request and token totals are weighted by its inverse. Distinct IP and country
     * counts see only the events sent.
     */
    public KeyUsageEvent withSampleRate(@Nullable Double sampleRate) {
        this.sampleRate = sampleRate;
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
        KeyUsageEvent other = (KeyUsageEvent) o;
        return Utils.enhancedDeepEquals(this.keySha256, other.keySha256)
                && Utils.enhancedDeepEquals(this.ip, other.ip)
                && Utils.enhancedDeepEquals(this.occurredAt, other.occurredAt)
                && Utils.enhancedDeepEquals(this.userAgent, other.userAgent)
                && Utils.enhancedDeepEquals(this.model, other.model)
                && Utils.enhancedDeepEquals(this.inputTokens, other.inputTokens)
                && Utils.enhancedDeepEquals(this.outputTokens, other.outputTokens)
                && Utils.enhancedDeepEquals(this.sampleRate, other.sampleRate);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(keySha256, ip, occurredAt, userAgent, model, inputTokens, outputTokens, sampleRate);
    }

    @Override
    public String toString() {
        return Utils.toString(
                KeyUsageEvent.class,
                "keySha256",
                keySha256,
                "ip",
                ip,
                "occurredAt",
                occurredAt,
                "userAgent",
                userAgent,
                "model",
                model,
                "inputTokens",
                inputTokens,
                "outputTokens",
                outputTokens,
                "sampleRate",
                sampleRate);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String keySha256;

        private String ip;

        private String occurredAt;

        private String userAgent;

        private String model;

        private Long inputTokens;

        private Long outputTokens;

        private Double sampleRate;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * SHA-256 of the issued key's UTF-8 bytes, as 64 lower-case hex characters: the value sent as
         * issued_credential.sha256 when the account was assessed. Each SDK's issued-credential helper computes
         * it.
         */
        public Builder keySha256(@Nonnull String keySha256) {
            this.keySha256 = Utils.checkNotNull(keySha256, "keySha256");
            return this;
        }

        /**
         * The client IP address the request came from, IPv4 or IPv6, as your server saw it. Stored only as a
         * keyed digest and its country.
         */
        public Builder ip(@Nonnull String ip) {
            this.ip = Utils.checkNotNull(ip, "ip");
            return this;
        }

        /**
         * When the request was made.
         */
        public Builder occurredAt(@Nonnull String occurredAt) {
            this.occurredAt = Utils.checkNotNull(occurredAt, "occurredAt");
            return this;
        }

        /**
         * The request's User-Agent header, at most 512 characters. Stored only as a keyed digest.
         */
        public Builder userAgent(@Nullable String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * The model or product the request used, such as gpt-4o-mini. Stored only as a keyed digest.
         */
        public Builder model(@Nullable String model) {
            this.model = model;
            return this;
        }

        /**
         * Input tokens the request consumed.
         */
        public Builder inputTokens(@Nullable Long inputTokens) {
            this.inputTokens = inputTokens;
            return this;
        }

        /**
         * Output tokens the request produced.
         */
        public Builder outputTokens(@Nullable Long outputTokens) {
            this.outputTokens = outputTokens;
            return this;
        }

        /**
         * The share of requests your sampler records, when it records only some: 0.1 means this event stands
         * for 10 requests. Request and token totals are weighted by its inverse. Distinct IP and country
         * counts see only the events sent.
         */
        public Builder sampleRate(@Nullable Double sampleRate) {
            this.sampleRate = sampleRate;
            return this;
        }

        public KeyUsageEvent build() {
            return new KeyUsageEvent(
                    keySha256, ip, occurredAt, userAgent, model, inputTokens, outputTokens, sampleRate);
        }

        private static final LazySingletonValue<Double> _SINGLETON_VALUE_SampleRate =
                new LazySingletonValue<>("sample_rate", "1", new TypeReference<Double>() {});
    }
}
