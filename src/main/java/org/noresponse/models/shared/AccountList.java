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
 * AccountList
 *
 * <p>Body of the 200 answer to GET /v1/accounts: the calling workspace's accounts whose risk changed
 * after changed_since, in the environment of the calling key, oldest change first. An account is
 * listed when its risk_version advanced and it has a current risk, and each item is the account
 * exactly as GET /v1/accounts/{account_id} answers it. An account appears once per page, at its latest
 * change; when its risk changes again later, it appears again on a later page with the newer
 * risk_version.
 *
 * <p>next_cursor is always present: pass it back as changed_since to read the changes made after this
 * page.
 */
public class AccountList {

    @JsonProperty("items")
    private List<Account> items;

    /**
     * Opaque cursor after the last change this page read, bound to the workspace and the environment of
     * the calling key. Pass it back unchanged as changed_since. It is present on every page, the last one
     * included: when nothing changed after changed_since it is the cursor that was sent, so a poller keeps
     * calling with the latest next_cursor.
     *
     * <p>A tampered or foreign cursor answers 400 invalid_cursor. Consumers do not parse it.
     */
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public AccountList(
            @JsonProperty("items") @Nonnull List<Account> items,
            @JsonProperty("next_cursor") @Nonnull String nextCursor) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = Optional.ofNullable(nextCursor)
                .orElseThrow(() -> new IllegalArgumentException("nextCursor cannot be null"));
    }

    public List<Account> items() {
        return this.items;
    }

    /**
     * Opaque cursor after the last change this page read, bound to the workspace and the environment of
     * the calling key. Pass it back unchanged as changed_since. It is present on every page, the last one
     * included: when nothing changed after changed_since it is the cursor that was sent, so a poller keeps
     * calling with the latest next_cursor.
     *
     * <p>A tampered or foreign cursor answers 400 invalid_cursor. Consumers do not parse it.
     */
    public String nextCursor() {
        return this.nextCursor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AccountList withItems(@Nonnull List<Account> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque cursor after the last change this page read, bound to the workspace and the environment of
     * the calling key. Pass it back unchanged as changed_since. It is present on every page, the last one
     * included: when nothing changed after changed_since it is the cursor that was sent, so a poller keeps
     * calling with the latest next_cursor.
     *
     * <p>A tampered or foreign cursor answers 400 invalid_cursor. Consumers do not parse it.
     */
    public AccountList withNextCursor(@Nonnull String nextCursor) {
        this.nextCursor = Utils.checkNotNull(nextCursor, "nextCursor");
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
        AccountList other = (AccountList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(AccountList.class, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<Account> items;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<Account> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque cursor after the last change this page read, bound to the workspace and the environment of
         * the calling key. Pass it back unchanged as changed_since. It is present on every page, the last one
         * included: when nothing changed after changed_since it is the cursor that was sent, so a poller keeps
         * calling with the latest next_cursor.
         *
         * <p>A tampered or foreign cursor answers 400 invalid_cursor. Consumers do not parse it.
         */
        public Builder nextCursor(@Nonnull String nextCursor) {
            this.nextCursor = Utils.checkNotNull(nextCursor, "nextCursor");
            return this;
        }

        public AccountList build() {
            return new AccountList(items, nextCursor);
        }
    }
}
