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
 * AccountDirectoryPage
 *
 * <p>Distinct observed accounts with retained assessments. Typed identifier searches use stable opaque
 * account order, independent of newly submitted assessments; ordinary and legacy email searches use
 * newest-seen order. One repeatable-read database snapshot supplies each page's rows and totals.
 *
 * <p>Pages do not share a snapshot: new matching accounts may appear on a later page or before the cursor
 * and be missed until a fresh search; retention, erasure and changed membership can remove results.
 * Current account decisions are read separately and may advance during a page. Cursors bind the
 * workspace/environment, operation and filters, expire, and cannot cross scopes.
 *
 * <p>Erasure and retention always take precedence over pagination.
 */
public class AccountDirectoryPage {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    @JsonProperty("items")
    private List<AccountDirectoryPageItem> items;

    /**
     * Distinct accounts matching the filters, exact below 10000 and capped at 10000.
     */
    @JsonProperty("total")
    private long total;

    /**
     * True when at least 10000 accounts match.
     */
    @JsonProperty("total_capped")
    private boolean totalCapped;

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public AccountDirectoryPage(
            @JsonProperty("items") @Nonnull List<AccountDirectoryPageItem> items,
            @JsonProperty("total") long total,
            @JsonProperty("total_capped") boolean totalCapped,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.total = total;
        this.totalCapped = totalCapped;
        this.nextCursor = nextCursor;
    }

    public AccountDirectoryPage(@Nonnull List<AccountDirectoryPageItem> items, long total, boolean totalCapped) {
        this(items, total, totalCapped, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    public List<AccountDirectoryPageItem> items() {
        return this.items;
    }

    /**
     * Distinct accounts matching the filters, exact below 10000 and capped at 10000.
     */
    public long total() {
        return this.total;
    }

    /**
     * True when at least 10000 accounts match.
     */
    public boolean totalCapped() {
        return this.totalCapped;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AccountDirectoryPage withItems(@Nonnull List<AccountDirectoryPageItem> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Distinct accounts matching the filters, exact below 10000 and capped at 10000.
     */
    public AccountDirectoryPage withTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * True when at least 10000 accounts match.
     */
    public AccountDirectoryPage withTotalCapped(boolean totalCapped) {
        this.totalCapped = totalCapped;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public AccountDirectoryPage withNextCursor(@Nullable String nextCursor) {
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
        AccountDirectoryPage other = (AccountDirectoryPage) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.total, other.total)
                && Utils.enhancedDeepEquals(this.totalCapped, other.totalCapped)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, items, total, totalCapped, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDirectoryPage.class,
                "schemaVersion",
                schemaVersion,
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

        private List<AccountDirectoryPageItem> items;

        private long total;

        private boolean totalCapped;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<AccountDirectoryPageItem> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Distinct accounts matching the filters, exact below 10000 and capped at 10000.
         */
        public Builder total(long total) {
            this.total = total;
            return this;
        }

        /**
         * True when at least 10000 accounts match.
         */
        public Builder totalCapped(boolean totalCapped) {
            this.totalCapped = totalCapped;
            return this;
        }

        /**
         * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
         * unchanged.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public AccountDirectoryPage build() {
            return new AccountDirectoryPage(items, total, totalCapped, nextCursor);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
