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
 * <p>A bounded page of latest assessment revisions, newest creation first. Each revision identifies the
 * exact result found; no current account risk is attached.
 */
public class AssessmentSearchPage {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    @JsonProperty("items")
    private List<AssessmentRevision> items;

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
            @JsonProperty("items") @Nonnull List<AssessmentRevision> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor,
            @JsonProperty("retention_days") @Nullable Long retentionDays) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
        this.retentionDays = Optional.ofNullable(retentionDays).orElse(Builder._SINGLETON_VALUE_RetentionDays.value());
    }

    public AssessmentSearchPage(@Nonnull List<AssessmentRevision> items) {
        this(items, null, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    public List<AssessmentRevision> items() {
        return this.items;
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

    public AssessmentSearchPage withItems(@Nonnull List<AssessmentRevision> items) {
        this.items = Utils.checkNotNull(items, "items");
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
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, items, nextCursor, retentionDays);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentSearchPage.class,
                "schemaVersion",
                schemaVersion,
                "items",
                items,
                "nextCursor",
                nextCursor,
                "retentionDays",
                retentionDays);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<AssessmentRevision> items;

        private String nextCursor;

        private Long retentionDays;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<AssessmentRevision> items) {
            this.items = Utils.checkNotNull(items, "items");
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
            return new AssessmentSearchPage(items, nextCursor, retentionDays);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_RetentionDays =
                new LazySingletonValue<>("retention_days", "365", new TypeReference<Long>() {});
    }
}
