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
 * AttendeeBatch
 *
 * <p>What the batch did, row by row.
 */
public class AttendeeBatch {

    @JsonProperty("created")
    private long created;

    @JsonProperty("updated")
    private long updated;

    @JsonProperty("items")
    private List<AttendeeBatchItem> items;

    @JsonCreator
    public AttendeeBatch(
            @JsonProperty("created") long created,
            @JsonProperty("updated") long updated,
            @JsonProperty("items") @Nonnull List<AttendeeBatchItem> items) {
        this.created = created;
        this.updated = updated;
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public long created() {
        return this.created;
    }

    public long updated() {
        return this.updated;
    }

    public List<AttendeeBatchItem> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttendeeBatch withCreated(long created) {
        this.created = created;
        return this;
    }

    public AttendeeBatch withUpdated(long updated) {
        this.updated = updated;
        return this;
    }

    public AttendeeBatch withItems(@Nonnull List<AttendeeBatchItem> items) {
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
        AttendeeBatch other = (AttendeeBatch) o;
        return Utils.enhancedDeepEquals(this.created, other.created)
                && Utils.enhancedDeepEquals(this.updated, other.updated)
                && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(created, updated, items);
    }

    @Override
    public String toString() {
        return Utils.toString(AttendeeBatch.class, "created", created, "updated", updated, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long created;

        private long updated;

        private List<AttendeeBatchItem> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder created(long created) {
            this.created = created;
            return this;
        }

        public Builder updated(long updated) {
            this.updated = updated;
            return this;
        }

        public Builder items(@Nonnull List<AttendeeBatchItem> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public AttendeeBatch build() {
            return new AttendeeBatch(created, updated, items);
        }
    }
}
