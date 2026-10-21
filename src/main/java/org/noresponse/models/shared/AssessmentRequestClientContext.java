package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AssessmentRequestClientContext
 *
 * <p>Optional context observed by your signup client. The server parses these hints into coarse features,
 * leaves raw user_agent and opaque ids out of every answer, and weighs a mismatch with the other
 * evidence.
 */
public class AssessmentRequestClientContext {
    /**
     * IANA time-zone name such as Europe/London or America/Los_Angeles. Fixed numeric offsets are not
     * accepted.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("timezone")
    private String timezone;

    /**
     * BCP 47 locale, for example en-AU. Treat as a presentation hint, not a nationality signal.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("locale")
    private String locale;

    /**
     * Client platform. Closed vocabulary keeps integrations stable.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("platform")
    private Platform platform;

    /**
     * Customer application version, without build metadata that contains secrets.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("app_version")
    private String appVersion;

    /**
     * The raw User-Agent header of the signup request, at most 512 characters. It is parsed into coarse
     * features and left out of every answer.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("user_agent")
    private String userAgent;

    /**
     * Tenant-scoped opaque identifier your application mints and rotates when the account or device
     * changes. Send an id of your own, not a hardware fingerprint.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * Opaque signup session identifier. It is used for tenant-scoped velocity and replay checks only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("session_id")
    private String sessionId;

    /**
     * Elapsed time from form start to submit. Values below three seconds are a weak automation hint,
     * weighed with the other evidence.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("form_duration_ms")
    private Long formDurationMs;

    /**
     * RFC 3339 timestamp observed by the client. Server ingress time remains authoritative; a large clock
     * skew is only a low-confidence feature.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("client_observed_at")
    private String clientObservedAt;

    @JsonCreator
    public AssessmentRequestClientContext(
            @JsonProperty("timezone") @Nullable String timezone,
            @JsonProperty("locale") @Nullable String locale,
            @JsonProperty("platform") @Nullable Platform platform,
            @JsonProperty("app_version") @Nullable String appVersion,
            @JsonProperty("user_agent") @Nullable String userAgent,
            @JsonProperty("device_id") @Nullable String deviceId,
            @JsonProperty("session_id") @Nullable String sessionId,
            @JsonProperty("form_duration_ms") @Nullable Long formDurationMs,
            @JsonProperty("client_observed_at") @Nullable String clientObservedAt) {
        this.timezone = timezone;
        this.locale = locale;
        this.platform = platform;
        this.appVersion = appVersion;
        this.userAgent = userAgent;
        this.deviceId = deviceId;
        this.sessionId = sessionId;
        this.formDurationMs = formDurationMs;
        this.clientObservedAt = clientObservedAt;
    }

    public AssessmentRequestClientContext() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /**
     * IANA time-zone name such as Europe/London or America/Los_Angeles. Fixed numeric offsets are not
     * accepted.
     */
    public Optional<String> timezone() {
        return Optional.ofNullable(this.timezone);
    }

    /**
     * BCP 47 locale, for example en-AU. Treat as a presentation hint, not a nationality signal.
     */
    public Optional<String> locale() {
        return Optional.ofNullable(this.locale);
    }

    /**
     * Client platform. Closed vocabulary keeps integrations stable.
     */
    public Optional<Platform> platform() {
        return Optional.ofNullable(this.platform);
    }

    /**
     * Customer application version, without build metadata that contains secrets.
     */
    public Optional<String> appVersion() {
        return Optional.ofNullable(this.appVersion);
    }

    /**
     * The raw User-Agent header of the signup request, at most 512 characters. It is parsed into coarse
     * features and left out of every answer.
     */
    public Optional<String> userAgent() {
        return Optional.ofNullable(this.userAgent);
    }

    /**
     * Tenant-scoped opaque identifier your application mints and rotates when the account or device
     * changes. Send an id of your own, not a hardware fingerprint.
     */
    public Optional<String> deviceId() {
        return Optional.ofNullable(this.deviceId);
    }

    /**
     * Opaque signup session identifier. It is used for tenant-scoped velocity and replay checks only.
     */
    public Optional<String> sessionId() {
        return Optional.ofNullable(this.sessionId);
    }

    /**
     * Elapsed time from form start to submit. Values below three seconds are a weak automation hint,
     * weighed with the other evidence.
     */
    public Optional<Long> formDurationMs() {
        return Optional.ofNullable(this.formDurationMs);
    }

    /**
     * RFC 3339 timestamp observed by the client. Server ingress time remains authoritative; a large clock
     * skew is only a low-confidence feature.
     */
    public Optional<String> clientObservedAt() {
        return Optional.ofNullable(this.clientObservedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * IANA time-zone name such as Europe/London or America/Los_Angeles. Fixed numeric offsets are not
     * accepted.
     */
    public AssessmentRequestClientContext withTimezone(@Nullable String timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * BCP 47 locale, for example en-AU. Treat as a presentation hint, not a nationality signal.
     */
    public AssessmentRequestClientContext withLocale(@Nullable String locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Client platform. Closed vocabulary keeps integrations stable.
     */
    public AssessmentRequestClientContext withPlatform(@Nullable Platform platform) {
        this.platform = platform;
        return this;
    }

    /**
     * Customer application version, without build metadata that contains secrets.
     */
    public AssessmentRequestClientContext withAppVersion(@Nullable String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    /**
     * The raw User-Agent header of the signup request, at most 512 characters. It is parsed into coarse
     * features and left out of every answer.
     */
    public AssessmentRequestClientContext withUserAgent(@Nullable String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Tenant-scoped opaque identifier your application mints and rotates when the account or device
     * changes. Send an id of your own, not a hardware fingerprint.
     */
    public AssessmentRequestClientContext withDeviceId(@Nullable String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    /**
     * Opaque signup session identifier. It is used for tenant-scoped velocity and replay checks only.
     */
    public AssessmentRequestClientContext withSessionId(@Nullable String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    /**
     * Elapsed time from form start to submit. Values below three seconds are a weak automation hint,
     * weighed with the other evidence.
     */
    public AssessmentRequestClientContext withFormDurationMs(@Nullable Long formDurationMs) {
        this.formDurationMs = formDurationMs;
        return this;
    }

    /**
     * RFC 3339 timestamp observed by the client. Server ingress time remains authoritative; a large clock
     * skew is only a low-confidence feature.
     */
    public AssessmentRequestClientContext withClientObservedAt(@Nullable String clientObservedAt) {
        this.clientObservedAt = clientObservedAt;
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
        AssessmentRequestClientContext other = (AssessmentRequestClientContext) o;
        return Utils.enhancedDeepEquals(this.timezone, other.timezone)
                && Utils.enhancedDeepEquals(this.locale, other.locale)
                && Utils.enhancedDeepEquals(this.platform, other.platform)
                && Utils.enhancedDeepEquals(this.appVersion, other.appVersion)
                && Utils.enhancedDeepEquals(this.userAgent, other.userAgent)
                && Utils.enhancedDeepEquals(this.deviceId, other.deviceId)
                && Utils.enhancedDeepEquals(this.sessionId, other.sessionId)
                && Utils.enhancedDeepEquals(this.formDurationMs, other.formDurationMs)
                && Utils.enhancedDeepEquals(this.clientObservedAt, other.clientObservedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                timezone,
                locale,
                platform,
                appVersion,
                userAgent,
                deviceId,
                sessionId,
                formDurationMs,
                clientObservedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentRequestClientContext.class,
                "timezone",
                timezone,
                "locale",
                locale,
                "platform",
                platform,
                "appVersion",
                appVersion,
                "userAgent",
                userAgent,
                "deviceId",
                deviceId,
                "sessionId",
                sessionId,
                "formDurationMs",
                formDurationMs,
                "clientObservedAt",
                clientObservedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String timezone;

        private String locale;

        private Platform platform;

        private String appVersion;

        private String userAgent;

        private String deviceId;

        private String sessionId;

        private Long formDurationMs;

        private String clientObservedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * IANA time-zone name such as Europe/London or America/Los_Angeles. Fixed numeric offsets are not
         * accepted.
         */
        public Builder timezone(@Nullable String timezone) {
            this.timezone = timezone;
            return this;
        }

        /**
         * BCP 47 locale, for example en-AU. Treat as a presentation hint, not a nationality signal.
         */
        public Builder locale(@Nullable String locale) {
            this.locale = locale;
            return this;
        }

        /**
         * Client platform. Closed vocabulary keeps integrations stable.
         */
        public Builder platform(@Nullable Platform platform) {
            this.platform = platform;
            return this;
        }

        /**
         * Customer application version, without build metadata that contains secrets.
         */
        public Builder appVersion(@Nullable String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        /**
         * The raw User-Agent header of the signup request, at most 512 characters. It is parsed into coarse
         * features and left out of every answer.
         */
        public Builder userAgent(@Nullable String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        /**
         * Tenant-scoped opaque identifier your application mints and rotates when the account or device
         * changes. Send an id of your own, not a hardware fingerprint.
         */
        public Builder deviceId(@Nullable String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        /**
         * Opaque signup session identifier. It is used for tenant-scoped velocity and replay checks only.
         */
        public Builder sessionId(@Nullable String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        /**
         * Elapsed time from form start to submit. Values below three seconds are a weak automation hint,
         * weighed with the other evidence.
         */
        public Builder formDurationMs(@Nullable Long formDurationMs) {
            this.formDurationMs = formDurationMs;
            return this;
        }

        /**
         * RFC 3339 timestamp observed by the client. Server ingress time remains authoritative; a large clock
         * skew is only a low-confidence feature.
         */
        public Builder clientObservedAt(@Nullable String clientObservedAt) {
            this.clientObservedAt = clientObservedAt;
            return this;
        }

        public AssessmentRequestClientContext build() {
            return new AssessmentRequestClientContext(
                    timezone,
                    locale,
                    platform,
                    appVersion,
                    userAgent,
                    deviceId,
                    sessionId,
                    formDurationMs,
                    clientObservedAt);
        }
    }
}
