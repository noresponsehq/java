package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ListBulkRequest
 *
 * <p>At most 100 additions and removals combined. One transaction applies all changes or none.
 * Idempotency-Key prevents duplicate changes for 24 hours; a different body under the same key returns
 * 409.
 *
 * <p>Matching reads see the new list immediately, while bounded durable jobs propagate account risk
 * notifications.
 */
public class ListBulkRequest {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("add")
    private List<ListEntryRequest> add;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("remove")
    private List<String> remove;

    @JsonCreator
    public ListBulkRequest(
            @JsonProperty("add") @Nullable List<ListEntryRequest> add,
            @JsonProperty("remove") @Nullable List<String> remove) {
        this.add = add;
        this.remove = remove;
    }

    public ListBulkRequest() {
        this(null, null);
    }

    public Optional<List<ListEntryRequest>> add() {
        return Optional.ofNullable(this.add);
    }

    public Optional<List<String>> remove() {
        return Optional.ofNullable(this.remove);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListBulkRequest withAdd(@Nullable List<ListEntryRequest> add) {
        this.add = add;
        return this;
    }

    public ListBulkRequest withRemove(@Nullable List<String> remove) {
        this.remove = remove;
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
        ListBulkRequest other = (ListBulkRequest) o;
        return Utils.enhancedDeepEquals(this.add, other.add) && Utils.enhancedDeepEquals(this.remove, other.remove);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(add, remove);
    }

    @Override
    public String toString() {
        return Utils.toString(ListBulkRequest.class, "add", add, "remove", remove);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ListEntryRequest> add;

        private List<String> remove;

        private Builder() {
            // force use of static builder() method
        }

        public Builder add(@Nullable List<ListEntryRequest> add) {
            this.add = add;
            return this;
        }

        public Builder remove(@Nullable List<String> remove) {
            this.remove = remove;
            return this;
        }

        public ListBulkRequest build() {
            return new ListBulkRequest(add, remove);
        }
    }
}
