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
 * InviteList
 *
 * <p>GET /v1/workspaces/{workspace_id}/invites: the workspace's pending invites, oldest first, at most
 * 50, with no token.
 */
public class InviteList {

    @JsonProperty("items")
    private List<Invite> items;

    @JsonCreator
    public InviteList(@JsonProperty("items") @Nonnull List<Invite> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<Invite> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InviteList withItems(@Nonnull List<Invite> items) {
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
        InviteList other = (InviteList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(InviteList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Invite> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Invite> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public InviteList build() {
            return new InviteList(items);
        }
    }
}
