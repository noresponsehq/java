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
 * KeyRequest
 *
 * <p>Creates a key whose scopes are bounded by the issuer capabilities in this environment. Delete is
 * owner-only. A service key outlives its creator; a personal key belongs to the authenticated member
 * and is revoked on membership or authority changes.
 *
 * <p>request_id deduplicates creation: retry returns 200 metadata without the secret; changed parameters
 * answer 409. Team scopes members and members_manage are explicitly workspace-wide, live-only, and
 * delegated by a current full owner. Personal credentials cannot hold management or members_manage.
 *
 * <p>Legacy management includes policy publication; use settings without policy_publish for configuration
 * preparation and retention-only edits.
 */
public class KeyRequest {
    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    @JsonProperty("environment")
    private KeyEnvironment environment;

    /**
     * The key's scopes, each at most once.
     */
    @JsonProperty("scopes")
    private List<KeyRequestScope> scopes;

    /**
     * Optional display name for this API key, at most 100 printable characters. Preserved on rotation.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * Optional operational purpose, at most 200 printable characters without leading or trailing
     * whitespace. Do not put secrets or personal data here. Preserved on rotation and included in the
     * creation request fingerprint; omitted for older credentials.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("purpose")
    private String purpose;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("kind")
    private KeyKind kind;

    /**
     * Between one minute and 365 days from now.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Caller-chosen identifier for one creation attempt, scoped to the issuing actor and environment. Use
     * 1 to 256 printable ASCII characters without spaces. A retry with the same parameters returns the
     * original key metadata with status 200 and no secret; changed parameters return 409.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("request_id")
    private String requestId;

    @JsonCreator
    public KeyRequest(
            @JsonProperty("environment") @Nonnull KeyEnvironment environment,
            @JsonProperty("scopes") @Nonnull List<KeyRequestScope> scopes,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("purpose") @Nullable String purpose,
            @JsonProperty("kind") @Nullable KeyKind kind,
            @JsonProperty("expires_at") @Nullable String expiresAt,
            @JsonProperty("request_id") @Nullable String requestId) {
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.scopes =
                Optional.ofNullable(scopes).orElseThrow(() -> new IllegalArgumentException("scopes cannot be null"));
        this.name = name;
        this.purpose = purpose;
        this.kind = kind;
        this.expiresAt = expiresAt;
        this.requestId = requestId;
    }

    public KeyRequest(@Nonnull KeyEnvironment environment, @Nonnull List<KeyRequestScope> scopes) {
        this(environment, scopes, null, null, null, null, null);
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    /**
     * The key's scopes, each at most once.
     */
    public List<KeyRequestScope> scopes() {
        return this.scopes;
    }

    /**
     * Optional display name for this API key, at most 100 printable characters. Preserved on rotation.
     */
    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * Optional operational purpose, at most 200 printable characters without leading or trailing
     * whitespace. Do not put secrets or personal data here. Preserved on rotation and included in the
     * creation request fingerprint; omitted for older credentials.
     */
    public Optional<String> purpose() {
        return Optional.ofNullable(this.purpose);
    }

    public Optional<KeyKind> kind() {
        return Optional.ofNullable(this.kind);
    }

    /**
     * Between one minute and 365 days from now.
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    /**
     * Caller-chosen identifier for one creation attempt, scoped to the issuing actor and environment. Use
     * 1 to 256 printable ASCII characters without spaces. A retry with the same parameters returns the
     * original key metadata with status 200 and no secret; changed parameters return 409.
     */
    public Optional<String> requestId() {
        return Optional.ofNullable(this.requestId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The environment the key belongs to. A live key assesses with live sources; a test key answers from
     * the deterministic simulator.
     */
    public KeyRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * The key's scopes, each at most once.
     */
    public KeyRequest withScopes(@Nonnull List<KeyRequestScope> scopes) {
        this.scopes = Utils.checkNotNull(scopes, "scopes");
        return this;
    }

    /**
     * Optional display name for this API key, at most 100 printable characters. Preserved on rotation.
     */
    public KeyRequest withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * Optional operational purpose, at most 200 printable characters without leading or trailing
     * whitespace. Do not put secrets or personal data here. Preserved on rotation and included in the
     * creation request fingerprint; omitted for older credentials.
     */
    public KeyRequest withPurpose(@Nullable String purpose) {
        this.purpose = purpose;
        return this;
    }

    public KeyRequest withKind(@Nullable KeyKind kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Between one minute and 365 days from now.
     */
    public KeyRequest withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    /**
     * Caller-chosen identifier for one creation attempt, scoped to the issuing actor and environment. Use
     * 1 to 256 printable ASCII characters without spaces. A retry with the same parameters returns the
     * original key metadata with status 200 and no secret; changed parameters return 409.
     */
    public KeyRequest withRequestId(@Nullable String requestId) {
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
        KeyRequest other = (KeyRequest) o;
        return Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.scopes, other.scopes)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.purpose, other.purpose)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(environment, scopes, name, purpose, kind, expiresAt, requestId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                KeyRequest.class,
                "environment",
                environment,
                "scopes",
                scopes,
                "name",
                name,
                "purpose",
                purpose,
                "kind",
                kind,
                "expiresAt",
                expiresAt,
                "requestId",
                requestId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private KeyEnvironment environment;

        private List<KeyRequestScope> scopes;

        private String name;

        private String purpose;

        private KeyKind kind;

        private String expiresAt;

        private String requestId;

        private Builder() {
            // force use of static builder() method
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
         * The key's scopes, each at most once.
         */
        public Builder scopes(@Nonnull List<KeyRequestScope> scopes) {
            this.scopes = Utils.checkNotNull(scopes, "scopes");
            return this;
        }

        /**
         * Optional display name for this API key, at most 100 printable characters. Preserved on rotation.
         */
        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * Optional operational purpose, at most 200 printable characters without leading or trailing
         * whitespace. Do not put secrets or personal data here. Preserved on rotation and included in the
         * creation request fingerprint; omitted for older credentials.
         */
        public Builder purpose(@Nullable String purpose) {
            this.purpose = purpose;
            return this;
        }

        public Builder kind(@Nullable KeyKind kind) {
            this.kind = kind;
            return this;
        }

        /**
         * Between one minute and 365 days from now.
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        /**
         * Caller-chosen identifier for one creation attempt, scoped to the issuing actor and environment. Use
         * 1 to 256 printable ASCII characters without spaces. A retry with the same parameters returns the
         * original key metadata with status 200 and no secret; changed parameters return 409.
         */
        public Builder requestId(@Nullable String requestId) {
            this.requestId = requestId;
            return this;
        }

        public KeyRequest build() {
            return new KeyRequest(environment, scopes, name, purpose, kind, expiresAt, requestId);
        }
    }
}
