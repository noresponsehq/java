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
 * MemberList
 *
 * <p>GET /v1/workspaces/{workspace_id}/members: the workspace's active members, oldest first, at most
 * 100.
 */
public class MemberList {

    @JsonProperty("items")
    private List<Member> items;

    @JsonCreator
    public MemberList(@JsonProperty("items") @Nonnull List<Member> items) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
    }

    public List<Member> items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    public MemberList withItems(@Nonnull List<Member> items) {
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
        MemberList other = (MemberList) o;
        return Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items);
    }

    @Override
    public String toString() {
        return Utils.toString(MemberList.class, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Member> items;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Member> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public MemberList build() {
            return new MemberList(items);
        }
    }
}
