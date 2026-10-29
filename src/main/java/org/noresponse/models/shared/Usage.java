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
 * Usage
 *
 * <p>Body of the 200 answer to GET /v1/workspaces/{workspace_id}/usage: what the workspace's environments
 * were metered for on each UTC day from from through to. Each billable unit is counted once, where it
 * becomes final: an assessment when it is admitted (by flow and depth), a webhook delivery once per
 * event per endpoint (retries and replays add nothing), and a key-usage event once when the ingest
 * accepts it. A count does not change when the assessment, account or workspace is later deleted.
 *
 * <p>A day, environment or metric with nothing counted has no item. No prices, limits or billing.
 */
public class Usage {
    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    /**
     * The first UTC day covered.
     */
    @JsonProperty("from")
    private String from;

    /**
     * The last UTC day covered, included.
     */
    @JsonProperty("to")
    private String to;

    /**
     * Ordered by environment (live first), then day, metric, flow and depth.
     */
    @JsonProperty("items")
    private List<UsageCount> items;

    @JsonCreator
    public Usage(
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("items") @Nonnull List<UsageCount> items) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The first UTC day covered.
     */
    public String from() {
        return this.from;
    }

    /**
     * The last UTC day covered, included.
     */
    public String to() {
        return this.to;
    }

    /**
     * Ordered by environment (live first), then day, metric, flow and depth.
     */
    public List<UsageCount> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public Usage withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The first UTC day covered.
     */
    public Usage withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    /**
     * The last UTC day covered, included.
     */
    public Usage withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    /**
     * Ordered by environment (live first), then day, metric, flow and depth.
     */
    public Usage withItems(@Nonnull List<UsageCount> items) {
        this.items = Utils.checkNotNull(items, "items");
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
        Usage other = (Usage) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, from, to, items);
    }

    @Override
    public String toString() {
        return Utils.toString(Usage.class, "workspaceId", workspaceId, "from", from, "to", to, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String from;

        private String to;

        private List<UsageCount> items;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The first UTC day covered.
         */
        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        /**
         * The last UTC day covered, included.
         */
        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        /**
         * Ordered by environment (live first), then day, metric, flow and depth.
         */
        public Builder items(@Nonnull List<UsageCount> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Usage build() {
            return new Usage(workspaceId, from, to, items);
        }
    }
}
