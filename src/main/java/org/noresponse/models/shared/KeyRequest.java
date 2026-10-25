package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * KeyRequest
 *
 * <p>POST /v1/workspaces/{workspace_id}/keys body. Creates an API key in one environment of the workspace
 * with the scopes named, and returns its secret once. A live key needs the admin or owner role; a test
 * key needs developer or above.
 *
 * <p>An API key with the management scope creates keys in its own environment with scopes it holds
 * itself. Each scope may appear once.
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

    @JsonCreator
    public KeyRequest(
            @JsonProperty("environment") @Nonnull KeyEnvironment environment,
            @JsonProperty("scopes") @Nonnull List<KeyRequestScope> scopes) {
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.scopes =
                Optional.ofNullable(scopes).orElseThrow(() -> new IllegalArgumentException("scopes cannot be null"));
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
                && Utils.enhancedDeepEquals(this.scopes, other.scopes);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(environment, scopes);
    }

    @Override
    public String toString() {
        return Utils.toString(KeyRequest.class, "environment", environment, "scopes", scopes);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private KeyEnvironment environment;

        private List<KeyRequestScope> scopes;

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

        public KeyRequest build() {
            return new KeyRequest(environment, scopes);
        }
    }
}
