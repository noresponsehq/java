package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ListEntry
 *
 * <p>One entry of an environment's list: an exact email or an email domain that decides an account
 * whatever its score. An account matches when one of its admitted assessments sent the email, or an
 * email of the domain. A block entry decides block for every flow and an allow entry decides allow;
 * when both match, block wins.
 *
 * <p>The account's risk carries the list_match reason with the entry's id, and its score stays the one
 * the assessment's facts give. A held flow still waits for its investigation.
 */
public class ListEntry {
    /**
     * The entry's id: lst_ and a ULID.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    @JsonProperty("environment")
    private KeyEnvironment environment;

    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    @JsonProperty("kind")
    private ListEntryKind kind;

    /**
     * The value the entry matches, in canonical form: an email lower-cased whole, or a domain lower-cased
     * and taken to its registrable domain (mail.example.co.uk is stored as example.co.uk).
     */
    @JsonProperty("value")
    private String value;

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    @JsonProperty("action")
    private ListEntryAction action;

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("note")
    private String note;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * The external user id of the member who added the entry through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that added it.
     */
    @JsonProperty("created_by")
    private String createdBy;

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonCreator
    public ListEntry(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull KeyEnvironment environment,
            @JsonProperty("kind") @Nonnull ListEntryKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("action") @Nonnull ListEntryAction action,
            @JsonProperty("note") @Nullable String note,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("created_by") @Nonnull String createdBy,
            @JsonProperty("expires_at") @Nullable String expiresAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.action =
                Optional.ofNullable(action).orElseThrow(() -> new IllegalArgumentException("action cannot be null"));
        this.note = note;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.createdBy = Optional.ofNullable(createdBy)
                .orElseThrow(() -> new IllegalArgumentException("createdBy cannot be null"));
        this.expiresAt = expiresAt;
    }

    public ListEntry(
            @Nonnull String id,
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull ListEntryKind kind,
            @Nonnull String value,
            @Nonnull ListEntryAction action,
            @Nonnull String createdAt,
            @Nonnull String createdBy) {
        this(id, workspaceId, environment, kind, value, action, null, createdAt, createdBy, null);
    }

    /**
     * The entry's id: lst_ and a ULID.
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

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    public ListEntryKind kind() {
        return this.kind;
    }

    /**
     * The value the entry matches, in canonical form: an email lower-cased whole, or a domain lower-cased
     * and taken to its registrable domain (mail.example.co.uk is stored as example.co.uk).
     */
    public String value() {
        return this.value;
    }

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    public ListEntryAction action() {
        return this.action;
    }

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    public Optional<String> note() {
        return Optional.ofNullable(this.note);
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * The external user id of the member who added the entry through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that added it.
     */
    public String createdBy() {
        return this.createdBy;
    }

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The entry's id: lst_ and a ULID.
     */
    public ListEntry withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public ListEntry withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public ListEntry withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    public ListEntry withKind(@Nonnull ListEntryKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The value the entry matches, in canonical form: an email lower-cased whole, or a domain lower-cased
     * and taken to its registrable domain (mail.example.co.uk is stored as example.co.uk).
     */
    public ListEntry withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    public ListEntry withAction(@Nonnull ListEntryAction action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    public ListEntry withNote(@Nullable String note) {
        this.note = note;
        return this;
    }

    public ListEntry withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * The external user id of the member who added the entry through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that added it.
     */
    public ListEntry withCreatedBy(@Nonnull String createdBy) {
        this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
        return this;
    }

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    public ListEntry withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
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
        ListEntry other = (ListEntry) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.note, other.note)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, workspaceId, environment, kind, value, action, note, createdAt, createdBy, expiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListEntry.class,
                "id",
                id,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "kind",
                kind,
                "value",
                value,
                "action",
                action,
                "note",
                note,
                "createdAt",
                createdAt,
                "createdBy",
                createdBy,
                "expiresAt",
                expiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String workspaceId;

        private KeyEnvironment environment;

        private ListEntryKind kind;

        private String value;

        private ListEntryAction action;

        private String note;

        private String createdAt;

        private String createdBy;

        private String expiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The entry's id: lst_ and a ULID.
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

        /**
         * The environment the key belongs to. A live key assesses with live sources; a test key answers from
         * the deterministic simulator.
         */
        public Builder environment(@Nonnull KeyEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * email matches one address, whatever the case of its local part. email_domain matches every address
         * of the registrable domain and of its subdomains.
         */
        public Builder kind(@Nonnull ListEntryKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The value the entry matches, in canonical form: an email lower-cased whole, or a domain lower-cased
         * and taken to its registrable domain (mail.example.co.uk is stored as example.co.uk).
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        /**
         * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
         * match.
         */
        public Builder action(@Nonnull ListEntryAction action) {
            this.action = Utils.checkNotNull(action, "action");
            return this;
        }

        /**
         * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
         * nothing.
         */
        public Builder note(@Nullable String note) {
            this.note = note;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * The external user id of the member who added the entry through the console, or the prefix
         * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that added it.
         */
        public Builder createdBy(@Nonnull String createdBy) {
            this.createdBy = Utils.checkNotNull(createdBy, "createdBy");
            return this;
        }

        /**
         * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
         * propagates account risk changes.
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public ListEntry build() {
            return new ListEntry(
                    id, workspaceId, environment, kind, value, action, note, createdAt, createdBy, expiresAt);
        }
    }
}
