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

/**
 * Key
 *
 * <p>One API key of your workspace. A key belongs to exactly one environment. key, the secret credential,
 * is present only in the answer that created the key: NoResponse keeps a peppered digest of it, so
 * store it now.
 *
 * <p>Every other field is safe to show.
 */
public class Key {
    /**
     * The key's public id, a 26-character ULID.
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
     * The public start of the credential, nrk_&lt;environment&gt;_&lt;key id&gt;, which identifies the key
     * in logs and secret stores without its secret.
     */
    @JsonProperty("prefix")
    private String prefix;

    /**
     * The key's scopes, each at most once.
     */
    @JsonProperty("scopes")
    private List<KeyScope> scopes;

    /**
     * active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked:
     * the key was revoked (revoked_at) and answers 401.
     */
    @JsonProperty("status")
    private KeyStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    /**
     * The external user id of the member who created the key through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that created it. Absent for a key
     * NoResponse issued or a rotation made.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("created_by")
    private String createdBy;

    /**
     * When a rotated key stops authenticating (POST /v1/keys/rotate). Absent for a key with no end.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("revoked_at")
    private String revokedAt;

    /**
     * The external user id of the member who revoked the key through the console, or the prefix of the
     * management key that revoked it. Absent when the key is not revoked or NoResponse revoked it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("revoked_by")
    private String revokedBy;

    /**
     * The credential, nrk_&lt;environment&gt;_&lt;key id&gt;_&lt;secret&gt;. Present only in the 201
     * answer that created the key.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("key")
    private String key;

    @JsonCreator
    public Key(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull KeyEnvironment environment,
            @JsonProperty("prefix") @Nonnull String prefix,
            @JsonProperty("scopes") @Nonnull List<KeyScope> scopes,
            @JsonProperty("status") @Nonnull KeyStatus status,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("created_by") @Nullable String createdBy,
            @JsonProperty("expires_at") @Nullable String expiresAt,
            @JsonProperty("revoked_at") @Nullable String revokedAt,
            @JsonProperty("revoked_by") @Nullable String revokedBy,
            @JsonProperty("key") @Nullable String key) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.prefix =
                Optional.ofNullable(prefix).orElseThrow(() -> new IllegalArgumentException("prefix cannot be null"));
        this.scopes =
                Optional.ofNullable(scopes).orElseThrow(() -> new IllegalArgumentException("scopes cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.createdBy = createdBy;
        this.expiresAt = expiresAt;
        this.revokedAt = revokedAt;
        this.revokedBy = revokedBy;
        this.key = key;
    }

    public Key(
            @Nonnull String id,
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String prefix,
            @Nonnull List<KeyScope> scopes,
            @Nonnull KeyStatus status,
            @Nonnull String createdAt) {
        this(id, workspaceId, environment, prefix, scopes, status, createdAt, null, null, null, null, null);
    }

    /**
     * The key's public id, a 26-character ULID.
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
     * The public start of the credential, nrk_&lt;environment&gt;_&lt;key id&gt;, which identifies the key
     * in logs and secret stores without its secret.
     */
    public String prefix() {
        return this.prefix;
    }

    /**
     * The key's scopes, each at most once.
     */
    public List<KeyScope> scopes() {
        return this.scopes;
    }

    /**
     * active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked:
     * the key was revoked (revoked_at) and answers 401.
     */
    public KeyStatus status() {
        return this.status;
    }

    public String createdAt() {
        return this.createdAt;
    }

    /**
     * The external user id of the member who created the key through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that created it. Absent for a key
     * NoResponse issued or a rotation made.
     */
    public Optional<String> createdBy() {
        return Optional.ofNullable(this.createdBy);
    }

    /**
     * When a rotated key stops authenticating (POST /v1/keys/rotate). Absent for a key with no end.
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    public Optional<String> revokedAt() {
        return Optional.ofNullable(this.revokedAt);
    }

    /**
     * The external user id of the member who revoked the key through the console, or the prefix of the
     * management key that revoked it. Absent when the key is not revoked or NoResponse revoked it.
     */
    public Optional<String> revokedBy() {
        return Optional.ofNullable(this.revokedBy);
    }

    /**
     * The credential, nrk_&lt;environment&gt;_&lt;key id&gt;_&lt;secret&gt;. Present only in the 201
     * answer that created the key.
     */
    public Optional<String> key() {
        return Optional.ofNullable(this.key);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The key's public id, a 26-character ULID.
     */
    public Key withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public Key withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public Key withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * The public start of the credential, nrk_&lt;environment&gt;_&lt;key id&gt;, which identifies the key
     * in logs and secret stores without its secret.
     */
    public Key withPrefix(@Nonnull String prefix) {
        this.prefix = Utils.checkNotNull(prefix, "prefix");
        return this;
    }

    /**
     * The key's scopes, each at most once.
     */
    public Key withScopes(@Nonnull List<KeyScope> scopes) {
        this.scopes = Utils.checkNotNull(scopes, "scopes");
        return this;
    }

    /**
     * active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked:
     * the key was revoked (revoked_at) and answers 401.
     */
    public Key withStatus(@Nonnull KeyStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public Key withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * The external user id of the member who created the key through the console, or the prefix
     * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that created it. Absent for a key
     * NoResponse issued or a rotation made.
     */
    public Key withCreatedBy(@Nullable String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * When a rotated key stops authenticating (POST /v1/keys/rotate). Absent for a key with no end.
     */
    public Key withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public Key withRevokedAt(@Nullable String revokedAt) {
        this.revokedAt = revokedAt;
        return this;
    }

    /**
     * The external user id of the member who revoked the key through the console, or the prefix of the
     * management key that revoked it. Absent when the key is not revoked or NoResponse revoked it.
     */
    public Key withRevokedBy(@Nullable String revokedBy) {
        this.revokedBy = revokedBy;
        return this;
    }

    /**
     * The credential, nrk_&lt;environment&gt;_&lt;key id&gt;_&lt;secret&gt;. Present only in the 201
     * answer that created the key.
     */
    public Key withKey(@Nullable String key) {
        this.key = key;
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
        Key other = (Key) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.prefix, other.prefix)
                && Utils.enhancedDeepEquals(this.scopes, other.scopes)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.createdBy, other.createdBy)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.revokedAt, other.revokedAt)
                && Utils.enhancedDeepEquals(this.revokedBy, other.revokedBy)
                && Utils.enhancedDeepEquals(this.key, other.key);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                workspaceId,
                environment,
                prefix,
                scopes,
                status,
                createdAt,
                createdBy,
                expiresAt,
                revokedAt,
                revokedBy,
                key);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Key.class,
                "id",
                id,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "prefix",
                prefix,
                "scopes",
                scopes,
                "status",
                status,
                "createdAt",
                createdAt,
                "createdBy",
                createdBy,
                "expiresAt",
                expiresAt,
                "revokedAt",
                revokedAt,
                "revokedBy",
                revokedBy,
                "key",
                key);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String workspaceId;

        private KeyEnvironment environment;

        private String prefix;

        private List<KeyScope> scopes;

        private KeyStatus status;

        private String createdAt;

        private String createdBy;

        private String expiresAt;

        private String revokedAt;

        private String revokedBy;

        private String key;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The key's public id, a 26-character ULID.
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
         * The public start of the credential, nrk_&lt;environment&gt;_&lt;key id&gt;, which identifies the key
         * in logs and secret stores without its secret.
         */
        public Builder prefix(@Nonnull String prefix) {
            this.prefix = Utils.checkNotNull(prefix, "prefix");
            return this;
        }

        /**
         * The key's scopes, each at most once.
         */
        public Builder scopes(@Nonnull List<KeyScope> scopes) {
            this.scopes = Utils.checkNotNull(scopes, "scopes");
            return this;
        }

        /**
         * active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked:
         * the key was revoked (revoked_at) and answers 401.
         */
        public Builder status(@Nonnull KeyStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * The external user id of the member who created the key through the console, or the prefix
         * (nrk_&lt;environment&gt;_&lt;key id&gt;) of the management key that created it. Absent for a key
         * NoResponse issued or a rotation made.
         */
        public Builder createdBy(@Nullable String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        /**
         * When a rotated key stops authenticating (POST /v1/keys/rotate). Absent for a key with no end.
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder revokedAt(@Nullable String revokedAt) {
            this.revokedAt = revokedAt;
            return this;
        }

        /**
         * The external user id of the member who revoked the key through the console, or the prefix of the
         * management key that revoked it. Absent when the key is not revoked or NoResponse revoked it.
         */
        public Builder revokedBy(@Nullable String revokedBy) {
            this.revokedBy = revokedBy;
            return this;
        }

        /**
         * The credential, nrk_&lt;environment&gt;_&lt;key id&gt;_&lt;secret&gt;. Present only in the 201
         * answer that created the key.
         */
        public Builder key(@Nullable String key) {
            this.key = key;
            return this;
        }

        public Key build() {
            return new Key(
                    id,
                    workspaceId,
                    environment,
                    prefix,
                    scopes,
                    status,
                    createdAt,
                    createdBy,
                    expiresAt,
                    revokedAt,
                    revokedBy,
                    key);
        }
    }
}
