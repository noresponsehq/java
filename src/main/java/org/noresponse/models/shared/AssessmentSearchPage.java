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
 * AssessmentSearchPage
 *
 * <p>A bounded page of latest assessment revisions, newest creation first, with how many assessments
 * match the filters. Each item is the revision found and its first answer's decision; no current
 * account risk is attached.
 */
public class AssessmentSearchPage {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    @JsonProperty("items")
    private List<AssessmentSearchItem> items;

    /**
     * How many assessments match the filters over every page, counted when this page was read: exact up to
     * 10000. At 10000 or more it is 10000 and total_capped is true. The cursor does not change it; a later
     * page can count differently, as results are live.
     */
    @JsonProperty("total")
    private long total;

    /**
     * True when at least 10000 assessments match, so total is 10000 and not the exact count.
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

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("retention_days")
    private Long retentionDays;

    @JsonCreator
    public AssessmentSearchPage(
            @JsonProperty("items") @Nonnull List<AssessmentSearchItem> items,
            @JsonProperty("total") long total,
            @JsonProperty("total_capped") boolean totalCapped,
            @JsonProperty("next_cursor") @Nullable String nextCursor,
            @JsonProperty("retention_days") @Nullable Long retentionDays) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.total = total;
        this.totalCapped = totalCapped;
        this.nextCursor = nextCursor;
        this.retentionDays = Optional.ofNullable(retentionDays).orElse(Builder._SINGLETON_VALUE_RetentionDays.value());
    }

    public AssessmentSearchPage(@Nonnull List<AssessmentSearchItem> items, long total, boolean totalCapped) {
        this(items, total, totalCapped, null, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    public List<AssessmentSearchItem> items() {
        return this.items;
    }

    /**
     * How many assessments match the filters over every page, counted when this page was read: exact up to
     * 10000. At 10000 or more it is 10000 and total_capped is true. The cursor does not change it; a later
     * page can count differently, as results are live.
     */
    public long total() {
        return this.total;
    }

    /**
     * True when at least 10000 assessments match, so total is 10000 and not the exact count.
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

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    public Optional<Long> retentionDays() {
        return Optional.ofNullable(this.retentionDays);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AssessmentSearchPage withItems(@Nonnull List<AssessmentSearchItem> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * How many assessments match the filters over every page, counted when this page was read: exact up to
     * 10000. At 10000 or more it is 10000 and total_capped is true. The cursor does not change it; a later
     * page can count differently, as results are live.
     */
    public AssessmentSearchPage withTotal(long total) {
        this.total = total;
        return this;
    }

    /**
     * True when at least 10000 assessments match, so total is 10000 and not the exact count.
     */
    public AssessmentSearchPage withTotalCapped(boolean totalCapped) {
        this.totalCapped = totalCapped;
        return this;
    }

    /**
     * Opaque, expiring cursor bound to this environment, operation and every filter. Keep the filters
     * unchanged.
     */
    public AssessmentSearchPage withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
        return this;
    }

    /**
     * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
     * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
     * created_at; see assessment expires_at.
     */
    public AssessmentSearchPage withRetentionDays(@Nullable Long retentionDays) {
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
        AssessmentSearchPage other = (AssessmentSearchPage) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.total, other.total)
                && Utils.enhancedDeepEquals(this.totalCapped, other.totalCapped)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, items, total, totalCapped, nextCursor, retentionDays);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentSearchPage.class,
                "schemaVersion",
                schemaVersion,
                "items",
                items,
                "total",
                total,
                "totalCapped",
                totalCapped,
                "nextCursor",
                nextCursor,
                "retentionDays",
                retentionDays);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<AssessmentSearchItem> items;

        private long total;

        private boolean totalCapped;

        private String nextCursor;

        private Long retentionDays;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<AssessmentSearchItem> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * How many assessments match the filters over every page, counted when this page was read: exact up to
         * 10000. At 10000 or more it is 10000 and total_capped is true. The cursor does not change it; a later
         * page can count differently, as results are live.
         */
        public Builder total(long total) {
            this.total = total;
            return this;
        }

        /**
         * True when at least 10000 assessments match, so total is 10000 and not the exact count.
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

        /**
         * The calling workspace's configured retention period in days. Default 365 (12 months), which is also
         * the maximum: a workspace may only shorten it, to at least 1 day. Each record expires from its own
         * created_at; see assessment expires_at.
         */
        public Builder retentionDays(@Nullable Long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public AssessmentSearchPage build() {
            return new AssessmentSearchPage(items, total, totalCapped, nextCursor, retentionDays);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_RetentionDays =
                new LazySingletonValue<>("retention_days", "365", new TypeReference<Long>() {});
    }
}
