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
 * PersonBatch
 *
 * <p>What the batch did, row by row.
 */
public class PersonBatch {

    @JsonProperty("created")
    private long created;

    @JsonProperty("updated")
    private long updated;

    @JsonProperty("items")
    private List<PersonBatchItem> items;

    @JsonCreator
    public PersonBatch(
            @JsonProperty("created") long created,
            @JsonProperty("updated") long updated,
            @JsonProperty("items") @Nonnull List<PersonBatchItem> items) {
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

    public List<PersonBatchItem> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PersonBatch withCreated(long created) {
        this.created = created;
        return this;
    }

    public PersonBatch withUpdated(long updated) {
        this.updated = updated;
        return this;
    }

    public PersonBatch withItems(@Nonnull List<PersonBatchItem> items) {
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
        PersonBatch other = (PersonBatch) o;
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
        return Utils.toString(PersonBatch.class, "created", created, "updated", updated, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long created;

        private long updated;

        private List<PersonBatchItem> items;

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

        public Builder items(@Nonnull List<PersonBatchItem> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public PersonBatch build() {
            return new PersonBatch(created, updated, items);
        }
    }
}
