package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AuditEvent
 *
 * <p>One privileged workspace action with its actor, target, outcome and time. Successful data changes
 * commit their audit evidence atomically. Sensitive exports record access before returning bytes.
 *
 * <p>Details exclude secrets and subject identifiers.
 */
public class AuditEvent {
    /**
     * Opaque, server-assigned event id: aud_ and a 26-character ULID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    /**
     * What happened, as resource.verb: workspace.created, member.role_changed, member.removed,
     * invite.created, invite.revoked, invite.accepted, key.rotated, webhook_endpoint.created,
     * webhook_endpoint.deleted, webhook_endpoint.secret_rotated, integration.created, integration.deleted,
     * integration.secret_rotated, list_entry.created, list_entry.deleted, webhook_endpoint.test_sent,
     * webhook_endpoint.verification_requested, webhook_endpoint.delivery_replayed, assessment.created,
     * assessment.outcome_recorded, assessment.deleted, account.outcome_recorded,
     * account.enforcement_recorded, account.deleted, account.exported. A change with no finer name carries
     * the permission it used, such as keys.live.write. New actions may appear; treat an unknown one as a
     * change to its target.
     */
    @JsonProperty("action")
    private String action;

    @JsonProperty("outcome")
    private CustomerExportRequestFilterOutcome outcome;

    @JsonProperty("actor")
    private Actor actor;

    @JsonProperty("target")
    private AuditEventTarget target;

    /**
     * At most 8 short facts about the change, keyed by lowercase names: the role a member was given or
     * accepted (role), the invite an accepted member used (invite_id), the key that replaced a rotated one
     * (new_key_id), the assessment a member's change acted on (assessment_id). Absent when there are none.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("detail")
    private Map<String, String> detail;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("affected_environment")
    private Environment affectedEnvironment;

    /**
     * Validated request correlation id, also returned in X-Request-ID, linking this event to request
     * diagnostics. Absent on historical events without correlation.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("request_id")
    private String requestId;

    @JsonCreator
    public AuditEvent(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("action") @Nonnull String action,
            @JsonProperty("outcome") @Nonnull CustomerExportRequestFilterOutcome outcome,
            @JsonProperty("actor") @Nonnull Actor actor,
            @JsonProperty("target") @Nonnull AuditEventTarget target,
            @JsonProperty("detail") @Nullable Map<String, String> detail,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("affected_environment") @Nullable Environment affectedEnvironment,
            @JsonProperty("request_id") @Nullable String requestId) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.action =
                Optional.ofNullable(action).orElseThrow(() -> new IllegalArgumentException("action cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.actor = Optional.ofNullable(actor).orElseThrow(() -> new IllegalArgumentException("actor cannot be null"));
        this.target =
                Optional.ofNullable(target).orElseThrow(() -> new IllegalArgumentException("target cannot be null"));
        this.detail = detail;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.affectedEnvironment = affectedEnvironment;
        this.requestId = requestId;
    }

    public AuditEvent(
            @Nonnull String id,
            @Nonnull String workspaceId,
            @Nonnull AuditSettingsEnvironment environment,
            @Nonnull String action,
            @Nonnull CustomerExportRequestFilterOutcome outcome,
            @Nonnull Actor actor,
            @Nonnull AuditEventTarget target,
            @Nonnull String createdAt) {
        this(id, workspaceId, environment, action, outcome, actor, target, null, createdAt, null, null);
    }

    /**
     * Opaque, server-assigned event id: aud_ and a 26-character ULID.
     */
    public String id() {
        return this.id;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    /**
     * What happened, as resource.verb: workspace.created, member.role_changed, member.removed,
     * invite.created, invite.revoked, invite.accepted, key.rotated, webhook_endpoint.created,
     * webhook_endpoint.deleted, webhook_endpoint.secret_rotated, integration.created, integration.deleted,
     * integration.secret_rotated, list_entry.created, list_entry.deleted, webhook_endpoint.test_sent,
     * webhook_endpoint.verification_requested, webhook_endpoint.delivery_replayed, assessment.created,
     * assessment.outcome_recorded, assessment.deleted, account.outcome_recorded,
     * account.enforcement_recorded, account.deleted, account.exported. A change with no finer name carries
     * the permission it used, such as keys.live.write. New actions may appear; treat an unknown one as a
     * change to its target.
     */
    public String action() {
        return this.action;
    }

    public CustomerExportRequestFilterOutcome outcome() {
        return this.outcome;
    }

    public Actor actor() {
        return this.actor;
    }

    public AuditEventTarget target() {
        return this.target;
    }

    /**
     * At most 8 short facts about the change, keyed by lowercase names: the role a member was given or
     * accepted (role), the invite an accepted member used (invite_id), the key that replaced a rotated one
     * (new_key_id), the assessment a member's change acted on (assessment_id). Absent when there are none.
     */
    public Optional<Map<String, String>> detail() {
        return Optional.ofNullable(this.detail);
    }

    public String createdAt() {
        return this.createdAt;
    }

    public Optional<Environment> affectedEnvironment() {
        return Optional.ofNullable(this.affectedEnvironment);
    }

    /**
     * Validated request correlation id, also returned in X-Request-ID, linking this event to request
     * diagnostics. Absent on historical events without correlation.
     */
    public Optional<String> requestId() {
        return Optional.ofNullable(this.requestId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned event id: aud_ and a 26-character ULID.
     */
    public AuditEvent withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public AuditEvent withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public AuditEvent withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * What happened, as resource.verb: workspace.created, member.role_changed, member.removed,
     * invite.created, invite.revoked, invite.accepted, key.rotated, webhook_endpoint.created,
     * webhook_endpoint.deleted, webhook_endpoint.secret_rotated, integration.created, integration.deleted,
     * integration.secret_rotated, list_entry.created, list_entry.deleted, webhook_endpoint.test_sent,
     * webhook_endpoint.verification_requested, webhook_endpoint.delivery_replayed, assessment.created,
     * assessment.outcome_recorded, assessment.deleted, account.outcome_recorded,
     * account.enforcement_recorded, account.deleted, account.exported. A change with no finer name carries
     * the permission it used, such as keys.live.write. New actions may appear; treat an unknown one as a
     * change to its target.
     */
    public AuditEvent withAction(@Nonnull String action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    public AuditEvent withOutcome(@Nonnull CustomerExportRequestFilterOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public AuditEvent withActor(@Nonnull Actor actor) {
        this.actor = Utils.checkNotNull(actor, "actor");
        return this;
    }

    public AuditEvent withTarget(@Nonnull AuditEventTarget target) {
        this.target = Utils.checkNotNull(target, "target");
        return this;
    }

    /**
     * At most 8 short facts about the change, keyed by lowercase names: the role a member was given or
     * accepted (role), the invite an accepted member used (invite_id), the key that replaced a rotated one
     * (new_key_id), the assessment a member's change acted on (assessment_id). Absent when there are none.
     */
    public AuditEvent withDetail(@Nullable Map<String, String> detail) {
        this.detail = detail;
        return this;
    }

    public AuditEvent withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public AuditEvent withAffectedEnvironment(@Nullable Environment affectedEnvironment) {
        this.affectedEnvironment = affectedEnvironment;
        return this;
    }

    /**
     * Validated request correlation id, also returned in X-Request-ID, linking this event to request
     * diagnostics. Absent on historical events without correlation.
     */
    public AuditEvent withRequestId(@Nullable String requestId) {
        this.requestId = requestId;
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
        AuditEvent other = (AuditEvent) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.actor, other.actor)
                && Utils.enhancedDeepEquals(this.target, other.target)
                && Utils.enhancedDeepEquals(this.detail, other.detail)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.affectedEnvironment, other.affectedEnvironment)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                workspaceId,
                environment,
                action,
                outcome,
                actor,
                target,
                detail,
                createdAt,
                affectedEnvironment,
                requestId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AuditEvent.class,
                "id",
                id,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "action",
                action,
                "outcome",
                outcome,
                "actor",
                actor,
                "target",
                target,
                "detail",
                detail,
                "createdAt",
                createdAt,
                "affectedEnvironment",
                affectedEnvironment,
                "requestId",
                requestId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String workspaceId;

        private AuditSettingsEnvironment environment;

        private String action;

        private CustomerExportRequestFilterOutcome outcome;

        private Actor actor;

        private AuditEventTarget target;

        private Map<String, String> detail;

        private String createdAt;

        private Environment affectedEnvironment;

        private String requestId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned event id: aud_ and a 26-character ULID.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * What happened, as resource.verb: workspace.created, member.role_changed, member.removed,
         * invite.created, invite.revoked, invite.accepted, key.rotated, webhook_endpoint.created,
         * webhook_endpoint.deleted, webhook_endpoint.secret_rotated, integration.created, integration.deleted,
         * integration.secret_rotated, list_entry.created, list_entry.deleted, webhook_endpoint.test_sent,
         * webhook_endpoint.verification_requested, webhook_endpoint.delivery_replayed, assessment.created,
         * assessment.outcome_recorded, assessment.deleted, account.outcome_recorded,
         * account.enforcement_recorded, account.deleted, account.exported. A change with no finer name carries
         * the permission it used, such as keys.live.write. New actions may appear; treat an unknown one as a
         * change to its target.
         */
        public Builder action(@Nonnull String action) {
            this.action = Utils.checkNotNull(action, "action");
            return this;
        }

        public Builder outcome(@Nonnull CustomerExportRequestFilterOutcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        public Builder actor(@Nonnull Actor actor) {
            this.actor = Utils.checkNotNull(actor, "actor");
            return this;
        }

        public Builder target(@Nonnull AuditEventTarget target) {
            this.target = Utils.checkNotNull(target, "target");
            return this;
        }

        /**
         * At most 8 short facts about the change, keyed by lowercase names: the role a member was given or
         * accepted (role), the invite an accepted member used (invite_id), the key that replaced a rotated one
         * (new_key_id), the assessment a member's change acted on (assessment_id). Absent when there are none.
         */
        public Builder detail(@Nullable Map<String, String> detail) {
            this.detail = detail;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder affectedEnvironment(@Nullable Environment affectedEnvironment) {
            this.affectedEnvironment = affectedEnvironment;
            return this;
        }

        /**
         * Validated request correlation id, also returned in X-Request-ID, linking this event to request
         * diagnostics. Absent on historical events without correlation.
         */
        public Builder requestId(@Nullable String requestId) {
            this.requestId = requestId;
            return this;
        }

        public AuditEvent build() {
            return new AuditEvent(
                    id,
                    workspaceId,
                    environment,
                    action,
                    outcome,
                    actor,
                    target,
                    detail,
                    createdAt,
                    affectedEnvironment,
                    requestId);
        }
    }
}
