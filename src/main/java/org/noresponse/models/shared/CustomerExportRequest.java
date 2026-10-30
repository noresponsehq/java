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

public class CustomerExportRequest {

    @JsonProperty("kind")
    private CustomerExportRequestKind kind;

    @JsonProperty("environment")
    private Environment environment;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_id")
    private String accountId;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("filter")
    private CustomerExportRequestFilter filter;

    @JsonCreator
    public CustomerExportRequest(
            @JsonProperty("kind") @Nonnull CustomerExportRequestKind kind,
            @JsonProperty("environment") @Nonnull Environment environment,
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("response_mode") @Nullable ResponseMode responseMode,
            @JsonProperty("filter") @Nullable CustomerExportRequestFilter filter) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.accountId = accountId;
        this.responseMode = responseMode;
        this.filter = filter;
    }

    public CustomerExportRequest(@Nonnull CustomerExportRequestKind kind, @Nonnull Environment environment) {
        this(kind, environment, null, null, null);
    }

    public CustomerExportRequestKind kind() {
        return this.kind;
    }

    public Environment environment() {
        return this.environment;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public Optional<String> accountId() {
        return Optional.ofNullable(this.accountId);
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    public Optional<CustomerExportRequestFilter> filter() {
        return Optional.ofNullable(this.filter);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerExportRequest withKind(@Nonnull CustomerExportRequestKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public CustomerExportRequest withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public CustomerExportRequest withAccountId(@Nullable String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public CustomerExportRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
        return this;
    }

    public CustomerExportRequest withFilter(@Nullable CustomerExportRequestFilter filter) {
        this.filter = filter;
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
        CustomerExportRequest other = (CustomerExportRequest) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.filter, other.filter);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, environment, accountId, responseMode, filter);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CustomerExportRequest.class,
                "kind",
                kind,
                "environment",
                environment,
                "accountId",
                accountId,
                "responseMode",
                responseMode,
                "filter",
                filter);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private CustomerExportRequestKind kind;

        private Environment environment;

        private String accountId;

        private ResponseMode responseMode;

        private CustomerExportRequestFilter filter;

        private Builder() {
            // force use of static builder() method
        }

        public Builder kind(@Nonnull CustomerExportRequestKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder environment(@Nonnull Environment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
         * /assessments and its /linked_accounts. An assessment requested by a provider hook at
         * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
         * its assessment_id.
         */
        public Builder accountId(@Nullable String accountId) {
            this.accountId = accountId;
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
            return this;
        }

        public Builder filter(@Nullable CustomerExportRequestFilter filter) {
            this.filter = filter;
            return this;
        }

        public CustomerExportRequest build() {
            return new CustomerExportRequest(kind, environment, accountId, responseMode, filter);
        }
    }
}
