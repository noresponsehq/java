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
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentList
 *
 * <p>Body of the 200 answer to POST /v1/assessments/query: one page of the calling tenant's unexpired
 * assessments for one account, newest first, each at its latest assessment_version. Every item's
 * account_id equals the page's. An assessment past its expires_at is not listed.
 *
 * <p>next_cursor is present only when another page exists.
 */
public class AssessmentList {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

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

    @JsonProperty("items")
    private List<Assessment> items;

    /**
     * Opaque cursor for the next page, bound to your tenant and the account_id it was issued for. Pass it
     * back unchanged in the next query; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    /**
     * Your current retention period, so a consumer knows how far back history reaches. Every item expires
     * no later than its created_at plus this many days.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retention_days")
    private Long retentionDays;

    @JsonCreator
    public AssessmentList(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("items") @Nonnull List<Assessment> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor,
            @JsonProperty("retention_days") @Nullable Long retentionDays) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
        this.retentionDays = Optional.ofNullable(retentionDays).orElse(Builder._SINGLETON_VALUE_RetentionDays.value());
    }

    public AssessmentList(@Nonnull String accountId, @Nonnull List<Assessment> items) {
        this(accountId, items, null, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
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

    public List<Assessment> items() {
        return this.items;
    }

    /**
     * Opaque cursor for the next page, bound to your tenant and the account_id it was issued for. Pass it
     * back unchanged in the next query; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    /**
     * Your current retention period, so a consumer knows how far back history reaches. Every item expires
     * no later than its created_at plus this many days.
     */
    public Optional<Long> retentionDays() {
        return Optional.ofNullable(this.retentionDays);
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
    public AssessmentList withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public AssessmentList withItems(@Nonnull List<Assessment> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque cursor for the next page, bound to your tenant and the account_id it was issued for. Pass it
     * back unchanged in the next query; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    public AssessmentList withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
        return this;
    }

    /**
     * Your current retention period, so a consumer knows how far back history reaches. Every item expires
     * no later than its created_at plus this many days.
     */
    public AssessmentList withRetentionDays(@Nullable Long retentionDays) {
        this.retentionDays = retentionDays;
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
        AssessmentList other = (AssessmentList) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, accountId, items, nextCursor, retentionDays);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentList.class,
                "schemaVersion",
                schemaVersion,
                "accountId",
                accountId,
                "items",
                items,
                "nextCursor",
                nextCursor,
                "retentionDays",
                retentionDays);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private List<Assessment> items;

        private String nextCursor;

        private Long retentionDays;

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

        public Builder items(@Nonnull List<Assessment> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque cursor for the next page, bound to your tenant and the account_id it was issued for. Pass it
         * back unchanged in the next query; a tampered, foreign or expired cursor answers 400 invalid_cursor.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        /**
         * Your current retention period, so a consumer knows how far back history reaches. Every item expires
         * no later than its created_at plus this many days.
         */
        public Builder retentionDays(@Nullable Long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public AssessmentList build() {
            return new AssessmentList(accountId, items, nextCursor, retentionDays);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_RetentionDays =
                new LazySingletonValue<>("retention_days", "365", new TypeReference<Long>() {});
    }
}
