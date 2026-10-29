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
 * ListChange
 *
 * <p>A committed bulk list change and bounded notification progress, available while propagation is
 * pending and for 24 hours after all propagation completes. References count an account once per
 * changed list entry and are not unique accounts. No endpoint delivery or customer enforcement is
 * implied by completed.
 */
public class ListChange {

    @JsonProperty("id")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("state")
    private ListChangeState state;

    @JsonProperty("added")
    private List<ListEntry> added;

    @JsonProperty("removed")
    private List<String> removed;

    @JsonProperty("pending_propagations")
    private long pendingPropagations;

    @JsonProperty("processed_account_references")
    private long processedAccountReferences;

    @JsonCreator
    public ListChange(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("state") @Nonnull ListChangeState state,
            @JsonProperty("added") @Nonnull List<ListEntry> added,
            @JsonProperty("removed") @Nonnull List<String> removed,
            @JsonProperty("pending_propagations") long pendingPropagations,
            @JsonProperty("processed_account_references") long processedAccountReferences) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.state = Optional.ofNullable(state).orElseThrow(() -> new IllegalArgumentException("state cannot be null"));
        this.added = Optional.ofNullable(added).orElseThrow(() -> new IllegalArgumentException("added cannot be null"));
        this.removed =
                Optional.ofNullable(removed).orElseThrow(() -> new IllegalArgumentException("removed cannot be null"));
        this.pendingPropagations = pendingPropagations;
        this.processedAccountReferences = processedAccountReferences;
    }

    public String id() {
        return this.id;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public ListChangeState state() {
        return this.state;
    }

    public List<ListEntry> added() {
        return this.added;
    }

    public List<String> removed() {
        return this.removed;
    }

    public long pendingPropagations() {
        return this.pendingPropagations;
    }

    public long processedAccountReferences() {
        return this.processedAccountReferences;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListChange withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ListChange withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public ListChange withState(@Nonnull ListChangeState state) {
        this.state = Utils.checkNotNull(state, "state");
        return this;
    }

    public ListChange withAdded(@Nonnull List<ListEntry> added) {
        this.added = Utils.checkNotNull(added, "added");
        return this;
    }

    public ListChange withRemoved(@Nonnull List<String> removed) {
        this.removed = Utils.checkNotNull(removed, "removed");
        return this;
    }

    public ListChange withPendingPropagations(long pendingPropagations) {
        this.pendingPropagations = pendingPropagations;
        return this;
    }

    public ListChange withProcessedAccountReferences(long processedAccountReferences) {
        this.processedAccountReferences = processedAccountReferences;
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
        ListChange other = (ListChange) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.state, other.state)
                && Utils.enhancedDeepEquals(this.added, other.added)
                && Utils.enhancedDeepEquals(this.removed, other.removed)
                && Utils.enhancedDeepEquals(this.pendingPropagations, other.pendingPropagations)
                && Utils.enhancedDeepEquals(this.processedAccountReferences, other.processedAccountReferences);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, createdAt, state, added, removed, pendingPropagations, processedAccountReferences);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListChange.class,
                "id",
                id,
                "createdAt",
                createdAt,
                "state",
                state,
                "added",
                added,
                "removed",
                removed,
                "pendingPropagations",
                pendingPropagations,
                "processedAccountReferences",
                processedAccountReferences);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String createdAt;

        private ListChangeState state;

        private List<ListEntry> added;

        private List<String> removed;

        private long pendingPropagations;

        private long processedAccountReferences;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder state(@Nonnull ListChangeState state) {
            this.state = Utils.checkNotNull(state, "state");
            return this;
        }

        public Builder added(@Nonnull List<ListEntry> added) {
            this.added = Utils.checkNotNull(added, "added");
            return this;
        }

        public Builder removed(@Nonnull List<String> removed) {
            this.removed = Utils.checkNotNull(removed, "removed");
            return this;
        }

        public Builder pendingPropagations(long pendingPropagations) {
            this.pendingPropagations = pendingPropagations;
            return this;
        }

        public Builder processedAccountReferences(long processedAccountReferences) {
            this.processedAccountReferences = processedAccountReferences;
            return this;
        }

        public ListChange build() {
            return new ListChange(
                    id, createdAt, state, added, removed, pendingPropagations, processedAccountReferences);
        }
    }
}
