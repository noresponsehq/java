package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PolicyHistory
 *
 * <p>Immutable settings snapshots, newest first. history_complete is false when earlier versions predate
 * capture. A restored version is published with a fresh version number.
 *
 * <p>History is removed with the tenant.
 */
public class PolicyHistory {

    @JsonProperty("items")
    private List<PolicyHistoryItem> items;

    @JsonProperty("has_more")
    private boolean hasMore;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_before_version")
    private Long nextBeforeVersion;

    @JsonProperty("history_complete")
    private boolean historyComplete;

    @JsonCreator
    public PolicyHistory(
            @JsonProperty("items") @Nonnull List<PolicyHistoryItem> items,
            @JsonProperty("has_more") boolean hasMore,
            @JsonProperty("next_before_version") @Nullable Long nextBeforeVersion,
            @JsonProperty("history_complete") boolean historyComplete) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.hasMore = hasMore;
        this.nextBeforeVersion = nextBeforeVersion;
        this.historyComplete = historyComplete;
    }

    public PolicyHistory(@Nonnull List<PolicyHistoryItem> items, boolean hasMore, boolean historyComplete) {
        this(items, hasMore, null, historyComplete);
    }

    public List<PolicyHistoryItem> items() {
        return this.items;
    }

    public boolean hasMore() {
        return this.hasMore;
    }

    public Optional<Long> nextBeforeVersion() {
        return Optional.ofNullable(this.nextBeforeVersion);
    }

    public boolean historyComplete() {
        return this.historyComplete;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyHistory withItems(@Nonnull List<PolicyHistoryItem> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public PolicyHistory withHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public PolicyHistory withNextBeforeVersion(@Nullable Long nextBeforeVersion) {
        this.nextBeforeVersion = nextBeforeVersion;
        return this;
    }

    public PolicyHistory withHistoryComplete(boolean historyComplete) {
        this.historyComplete = historyComplete;
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
        PolicyHistory other = (PolicyHistory) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.hasMore, other.hasMore)
                && Utils.enhancedDeepEquals(this.nextBeforeVersion, other.nextBeforeVersion)
                && Utils.enhancedDeepEquals(this.historyComplete, other.historyComplete);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, hasMore, nextBeforeVersion, historyComplete);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyHistory.class,
                "items",
                items,
                "hasMore",
                hasMore,
                "nextBeforeVersion",
                nextBeforeVersion,
                "historyComplete",
                historyComplete);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<PolicyHistoryItem> items;

        private boolean hasMore;

        private Long nextBeforeVersion;

        private boolean historyComplete;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<PolicyHistoryItem> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder hasMore(boolean hasMore) {
            this.hasMore = hasMore;
            return this;
        }

        public Builder nextBeforeVersion(@Nullable Long nextBeforeVersion) {
            this.nextBeforeVersion = nextBeforeVersion;
            return this;
        }

        public Builder historyComplete(boolean historyComplete) {
            this.historyComplete = historyComplete;
            return this;
        }

        public PolicyHistory build() {
            return new PolicyHistory(items, hasMore, nextBeforeVersion, historyComplete);
        }
    }
}
