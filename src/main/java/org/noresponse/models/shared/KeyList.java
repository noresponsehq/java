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
 * KeyList
 *
 * <p>GET /v1/workspaces/{workspace_id}/keys: the keys of both of the workspace's environments, newest
 * first, at most 200 of each, revoked and expired keys included. No item carries a secret.
 */
public class KeyList {

    @JsonProperty("items")
    private List<Key> items;

    @JsonCreator
    public KeyList(@JsonProperty("items") @Nonnull List<Key> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<Key> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public KeyList withItems(@Nonnull List<Key> items) {
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
        KeyList other = (KeyList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(KeyList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Key> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Key> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public KeyList build() {
            return new KeyList(items);
        }
    }
}
