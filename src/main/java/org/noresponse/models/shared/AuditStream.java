package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AuditStream {

    @JsonProperty("endpoint_id")
    private String endpointId;

    @JsonProperty("environment")
    private Environment environment;

    @JsonProperty("actions")
    private List<String> actions;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    @JsonProperty("endpoint_state")
    private WebhookEndpointState endpointState;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_attempt_at")
    private String lastAttemptAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_delivered_at")
    private String lastDeliveredAt;

    @JsonCreator
    public AuditStream(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("environment") @Nonnull Environment environment,
            @JsonProperty("actions") @Nonnull List<String> actions,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("endpoint_state") @Nonnull WebhookEndpointState endpointState,
            @JsonProperty("last_attempt_at") @Nullable String lastAttemptAt,
            @JsonProperty("last_delivered_at") @Nullable String lastDeliveredAt) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.actions =
                Optional.ofNullable(actions).orElseThrow(() -> new IllegalArgumentException("actions cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.endpointState = Optional.ofNullable(endpointState)
                .orElseThrow(() -> new IllegalArgumentException("endpointState cannot be null"));
        this.lastAttemptAt = lastAttemptAt;
        this.lastDeliveredAt = lastDeliveredAt;
    }

    public AuditStream(
            @Nonnull String endpointId,
            @Nonnull Environment environment,
            @Nonnull List<String> actions,
            @Nonnull String createdAt,
            @Nonnull WebhookEndpointState endpointState) {
        this(endpointId, environment, actions, createdAt, endpointState, null, null);
    }

    public String endpointId() {
        return this.endpointId;
    }

    public Environment environment() {
        return this.environment;
    }

    public List<String> actions() {
        return this.actions;
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public WebhookEndpointState endpointState() {
        return this.endpointState;
    }

    public Optional<String> lastAttemptAt() {
        return Optional.ofNullable(this.lastAttemptAt);
    }

    public Optional<String> lastDeliveredAt() {
        return Optional.ofNullable(this.lastDeliveredAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuditStream withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public AuditStream withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public AuditStream withActions(@Nonnull List<String> actions) {
        this.actions = Utils.checkNotNull(actions, "actions");
        return this;
    }

    public AuditStream withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
     * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
     * hours of continuous delivery failure, and verified again by the same challenge.
     */
    public AuditStream withEndpointState(@Nonnull WebhookEndpointState endpointState) {
        this.endpointState = Utils.checkNotNull(endpointState, "endpointState");
        return this;
    }

    public AuditStream withLastAttemptAt(@Nullable String lastAttemptAt) {
        this.lastAttemptAt = lastAttemptAt;
        return this;
    }

    public AuditStream withLastDeliveredAt(@Nullable String lastDeliveredAt) {
        this.lastDeliveredAt = lastDeliveredAt;
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
        AuditStream other = (AuditStream) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.actions, other.actions)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.endpointState, other.endpointState)
                && Utils.enhancedDeepEquals(this.lastAttemptAt, other.lastAttemptAt)
                && Utils.enhancedDeepEquals(this.lastDeliveredAt, other.lastDeliveredAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                endpointId, environment, actions, createdAt, endpointState, lastAttemptAt, lastDeliveredAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AuditStream.class,
                "endpointId",
                endpointId,
                "environment",
                environment,
                "actions",
                actions,
                "createdAt",
                createdAt,
                "endpointState",
                endpointState,
                "lastAttemptAt",
                lastAttemptAt,
                "lastDeliveredAt",
                lastDeliveredAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private Environment environment;

        private List<String> actions;

        private String createdAt;

        private WebhookEndpointState endpointState;

        private String lastAttemptAt;

        private String lastDeliveredAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public Builder environment(@Nonnull Environment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder actions(@Nonnull List<String> actions) {
            this.actions = Utils.checkNotNull(actions, "actions");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * unverified until the endpoint echoes an endpoint.verification challenge (send one with POST
         * /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72
         * hours of continuous delivery failure, and verified again by the same challenge.
         */
        public Builder endpointState(@Nonnull WebhookEndpointState endpointState) {
            this.endpointState = Utils.checkNotNull(endpointState, "endpointState");
            return this;
        }

        public Builder lastAttemptAt(@Nullable String lastAttemptAt) {
            this.lastAttemptAt = lastAttemptAt;
            return this;
        }

        public Builder lastDeliveredAt(@Nullable String lastDeliveredAt) {
            this.lastDeliveredAt = lastDeliveredAt;
            return this;
        }

        public AuditStream build() {
            return new AuditStream(
                    endpointId, environment, actions, createdAt, endpointState, lastAttemptAt, lastDeliveredAt);
        }
    }
}
