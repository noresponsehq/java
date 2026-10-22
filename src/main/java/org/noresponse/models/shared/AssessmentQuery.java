package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentQuery
 *
 * <p>POST /v1/assessments/query body. account_id travels in the body rather than the URL path so a
 * provider value that turns out to hold personal data is not written to access logs, proxies or
 * browser history. Read-only: no Idempotency-Key.
 *
 * <p>An account with no unexpired assessments answers 200 with empty items.
 */
public class AssessmentQuery {
    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    /**
     * next_cursor from the previous page, unchanged. Opaque, MAC'd and bound to the tenant and account_id.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cursor")
    private String cursor;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    @JsonCreator
    public AssessmentQuery(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor,
            @JsonProperty("response_mode") @Nullable ResponseMode responseMode) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.responseMode = responseMode;
    }

    public AssessmentQuery(@Nonnull String accountId) {
        this(accountId, null, null, null);
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * next_cursor from the previous page, unchanged. Opaque, MAC'd and bound to the tenant and account_id.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
     * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
     * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
     * JWT; a hex digest such as a SHA-256 is allowed.
     *
     * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
     * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AssessmentQuery withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public AssessmentQuery withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * next_cursor from the previous page, unchanged. Opaque, MAC'd and bound to the tenant and account_id.
     */
    public AssessmentQuery withCursor(@Nullable String cursor) {
        this.cursor = cursor;
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public AssessmentQuery withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
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
        AssessmentQuery other = (AssessmentQuery) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, limit, cursor, responseMode);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentQuery.class,
                "accountId",
                accountId,
                "limit",
                limit,
                "cursor",
                cursor,
                "responseMode",
                responseMode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private Long limit;

        private String cursor;

        private ResponseMode responseMode;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your tenant. Use an id you mint,
         * with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an Auth0
         * user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused, as is a
         * JWT; a hex digest such as a SHA-256 is allowed.
         *
         * <p>It travels only in request bodies. An assessment requested by a provider hook at pre_registration,
         * which has no account yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * next_cursor from the previous page, unchanged. Opaque, MAC'd and bound to the tenant and account_id.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
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

        public AssessmentQuery build() {
            return new AssessmentQuery(accountId, limit, cursor, responseMode);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
