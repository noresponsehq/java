package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * LinkedAccountPage
 *
 * <p>Other accounts in this workspace and environment whose retained assessments submitted an email,
 * phone or customer-namespace username that one of account_id's retained assessments also submitted.
 * Matching uses the identifier search rules: exact, canonical and, for email, mailbox. Only
 * customer-submitted identifiers link accounts; source-discovered identifiers do not.
 *
 * <p>Expired, retention-shortened, deleted and erased assessments link nothing. A shared identifier is
 * not verified ownership. Accounts use stable opaque order, the page and total share one database
 * snapshot, and later pages are live.
 *
 * <p>Cursors bind the workspace, environment, operation and account_id, and expire.
 */
public class LinkedAccountPage {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * The account whose submitted identifiers were compared. It is excluded from items.
     */
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("items")
    private List<LinkedAccount> items;

    /**
     * Distinct linked accounts, exact below 10000 and capped at 10000.
     */
    @JsonProperty("total")
    private long total;

    /**
     * True when at least 10000 accounts are linked.
     */
    @JsonProperty("total_capped")
    private boolean totalCapped;

    /**
     * Opaque, expiring cursor bound to this environment, operation and account_id. Absent on the last
     * page.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public LinkedAccountPage(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("items") @Nonnull List<LinkedAccount> items,
            @JsonProperty("total") long total,
            @JsonProperty("total_capped") boolean totalCapped,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.total = total;
        this.totalCapped = totalCapped;
        this.nextCursor = nextCursor;
    }

    public LinkedAccountPage(
            @Nonnull String accountId, @Nonnull List<LinkedAccount> items, long total, boolean totalCapped) {
        this(accountId, items, total, totalCapped, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * The account whose submitted identifiers were compared. It is excluded from items.
     */
    public String accountId() {
        return this.accountId;
    }

    public List<LinkedAccount> items() {
        return this.items;
    }

    /**
     * Distinct linked accounts, exact below 10000 and capped at 10000.
     */
    public long total() {
        return this.total;
    }

    /**
     * True when at least 10000 accounts are linked.
     */
    public boolean totalCapped() {
        return this.totalCapped;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and account_id. Absent on the last
     * page.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account whose submitted identifiers were compared. It is excluded from items.
     */
    public LinkedAccountPage withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public LinkedAccountPage withItems(@Nonnull List<LinkedAccount> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Distinct linked accounts, exact below 10000 and capped at 10000.
     */
    public LinkedAccountPage withTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * True when at least 10000 accounts are linked.
     */
    public LinkedAccountPage withTotalCapped(boolean totalCapped) {
        this.totalCapped = totalCapped;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and account_id. Absent on the last
     * page.
     */
    public LinkedAccountPage withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
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
        LinkedAccountPage other = (LinkedAccountPage) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.total, other.total)
                && Utils.enhancedDeepEquals(this.totalCapped, other.totalCapped)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, accountId, items, total, totalCapped, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                LinkedAccountPage.class,
                "schemaVersion",
                schemaVersion,
                "accountId",
                accountId,
                "items",
                items,
                "total",
                total,
                "totalCapped",
                totalCapped,
                "nextCursor",
                nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private List<LinkedAccount> items;

        private long total;

        private boolean totalCapped;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account whose submitted identifiers were compared. It is excluded from items.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder items(@Nonnull List<LinkedAccount> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Distinct linked accounts, exact below 10000 and capped at 10000.
         */
        public Builder total(long total) {
            this.total = total;
            return this;
        }

        /**
         * True when at least 10000 accounts are linked.
         */
        public Builder totalCapped(boolean totalCapped) {
            this.totalCapped = totalCapped;
            return this;
        }

        /**
         * Opaque, expiring cursor bound to this environment, operation and account_id. Absent on the last
         * page.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public LinkedAccountPage build() {
            return new LinkedAccountPage(accountId, items, total, totalCapped, nextCursor);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
