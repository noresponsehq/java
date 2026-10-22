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
 * IntegrationList
 *
 * <p>GET /v1/integrations: every integration of the tenant, oldest first, at most 10. No secret is ever
 * listed.
 */
public class IntegrationList {

    @JsonProperty("items")
    private List<Integration> items;

    @JsonCreator
    public IntegrationList(@JsonProperty("items") @Nonnull List<Integration> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<Integration> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public IntegrationList withItems(@Nonnull List<Integration> items) {
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
        IntegrationList other = (IntegrationList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(IntegrationList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Integration> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Integration> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public IntegrationList build() {
            return new IntegrationList(items);
        }
    }
}
