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
 * AuditStreamList
 *
 * <p>Read audit stream subscriptions.
 */
public class AuditStreamList {

    @JsonProperty("items")
    private List<AuditStream> items;

    @JsonCreator
    public AuditStreamList(@JsonProperty("items") @Nonnull List<AuditStream> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<AuditStream> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuditStreamList withItems(@Nonnull List<AuditStream> items) {
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
        AuditStreamList other = (AuditStreamList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(AuditStreamList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<AuditStream> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<AuditStream> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public AuditStreamList build() {
            return new AuditStreamList(items);
        }
    }
}
