package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AuditSettings
 *
 * <p>Read audit retention and available history.
 */
public class AuditSettings {

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    @JsonProperty("retention_days")
    private long retentionDays;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("oldest_available_at")
    private String oldestAvailableAt;

    @JsonProperty("capture")
    private String capture;

    @JsonCreator
    public AuditSettings(
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("retention_days") long retentionDays,
            @JsonProperty("oldest_available_at") @Nullable String oldestAvailableAt) {
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.retentionDays = retentionDays;
        this.oldestAvailableAt = oldestAvailableAt;
        this.capture = Builder._SINGLETON_VALUE_Capture.value();
    }

    public AuditSettings(@Nonnull AuditSettingsEnvironment environment, long retentionDays) {
        this(environment, retentionDays, null);
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    public long retentionDays() {
        return this.retentionDays;
    }

    public Optional<String> oldestAvailableAt() {
        return Optional.ofNullable(this.oldestAvailableAt);
    }

    public String capture() {
        return this.capture;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuditSettings withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public AuditSettings withRetentionDays(long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    public AuditSettings withOldestAvailableAt(@Nullable String oldestAvailableAt) {
        this.oldestAvailableAt = oldestAvailableAt;
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
        AuditSettings other = (AuditSettings) o;
        return Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.oldestAvailableAt, other.oldestAvailableAt)
                && Utils.enhancedDeepEquals(this.capture, other.capture);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(environment, retentionDays, oldestAvailableAt, capture);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AuditSettings.class,
                "environment",
                environment,
                "retentionDays",
                retentionDays,
                "oldestAvailableAt",
                oldestAvailableAt,
                "capture",
                capture);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AuditSettingsEnvironment environment;

        private long retentionDays;

        private String oldestAvailableAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder retentionDays(long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public Builder oldestAvailableAt(@Nullable String oldestAvailableAt) {
            this.oldestAvailableAt = oldestAvailableAt;
            return this;
        }

        public AuditSettings build() {
            return new AuditSettings(environment, retentionDays, oldestAvailableAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Capture =
                new LazySingletonValue<>("capture", "\"available_events_only\"", new TypeReference<String>() {});
    }
}
