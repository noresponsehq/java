package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class CustomerExportRequestFilter {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("action")
    private String action;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("actor")
    private String actor;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("target_type")
    private String targetType;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("target_id")
    private String targetId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("environment")
    private Environment environment;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("outcome")
    private CustomerExportRequestFilterOutcome outcome;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("since")
    private String since;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("until")
    private String until;

    @JsonCreator
    public CustomerExportRequestFilter(
            @JsonProperty("action") @Nullable String action,
            @JsonProperty("actor") @Nullable String actor,
            @JsonProperty("target_type") @Nullable String targetType,
            @JsonProperty("target_id") @Nullable String targetId,
            @JsonProperty("environment") @Nullable Environment environment,
            @JsonProperty("outcome") @Nullable CustomerExportRequestFilterOutcome outcome,
            @JsonProperty("since") @Nullable String since,
            @JsonProperty("until") @Nullable String until) {
        this.action = action;
        this.actor = actor;
        this.targetType = targetType;
        this.targetId = targetId;
        this.environment = environment;
        this.outcome = outcome;
        this.since = since;
        this.until = until;
    }

    public CustomerExportRequestFilter() {
        this(null, null, null, null, null, null, null, null);
    }

    public Optional<String> action() {
        return Optional.ofNullable(this.action);
    }

    public Optional<String> actor() {
        return Optional.ofNullable(this.actor);
    }

    public Optional<String> targetType() {
        return Optional.ofNullable(this.targetType);
    }

    public Optional<String> targetId() {
        return Optional.ofNullable(this.targetId);
    }

    public Optional<Environment> environment() {
        return Optional.ofNullable(this.environment);
    }

    public Optional<CustomerExportRequestFilterOutcome> outcome() {
        return Optional.ofNullable(this.outcome);
    }

    public Optional<String> since() {
        return Optional.ofNullable(this.since);
    }

    public Optional<String> until() {
        return Optional.ofNullable(this.until);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerExportRequestFilter withAction(@Nullable String action) {
        this.action = action;
        return this;
    }

    public CustomerExportRequestFilter withActor(@Nullable String actor) {
        this.actor = actor;
        return this;
    }

    public CustomerExportRequestFilter withTargetType(@Nullable String targetType) {
        this.targetType = targetType;
        return this;
    }

    public CustomerExportRequestFilter withTargetId(@Nullable String targetId) {
        this.targetId = targetId;
        return this;
    }

    public CustomerExportRequestFilter withEnvironment(@Nullable Environment environment) {
        this.environment = environment;
        return this;
    }

    public CustomerExportRequestFilter withOutcome(@Nullable CustomerExportRequestFilterOutcome outcome) {
        this.outcome = outcome;
        return this;
    }

    public CustomerExportRequestFilter withSince(@Nullable String since) {
        this.since = since;
        return this;
    }

    public CustomerExportRequestFilter withUntil(@Nullable String until) {
        this.until = until;
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
        CustomerExportRequestFilter other = (CustomerExportRequestFilter) o;
        return Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.actor, other.actor)
                && Utils.enhancedDeepEquals(this.targetType, other.targetType)
                && Utils.enhancedDeepEquals(this.targetId, other.targetId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.since, other.since)
                && Utils.enhancedDeepEquals(this.until, other.until);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(action, actor, targetType, targetId, environment, outcome, since, until);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CustomerExportRequestFilter.class,
                "action",
                action,
                "actor",
                actor,
                "targetType",
                targetType,
                "targetId",
                targetId,
                "environment",
                environment,
                "outcome",
                outcome,
                "since",
                since,
                "until",
                until);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String action;

        private String actor;

        private String targetType;

        private String targetId;

        private Environment environment;

        private CustomerExportRequestFilterOutcome outcome;

        private String since;

        private String until;

        private Builder() {
            // force use of static builder() method
        }

        public Builder action(@Nullable String action) {
            this.action = action;
            return this;
        }

        public Builder actor(@Nullable String actor) {
            this.actor = actor;
            return this;
        }

        public Builder targetType(@Nullable String targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder targetId(@Nullable String targetId) {
            this.targetId = targetId;
            return this;
        }

        public Builder environment(@Nullable Environment environment) {
            this.environment = environment;
            return this;
        }

        public Builder outcome(@Nullable CustomerExportRequestFilterOutcome outcome) {
            this.outcome = outcome;
            return this;
        }

        public Builder since(@Nullable String since) {
            this.since = since;
            return this;
        }

        public Builder until(@Nullable String until) {
            this.until = until;
            return this;
        }

        public CustomerExportRequestFilter build() {
            return new CustomerExportRequestFilter(
                    action, actor, targetType, targetId, environment, outcome, since, until);
        }
    }
}
