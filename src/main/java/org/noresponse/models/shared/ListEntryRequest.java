package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ListEntryRequest
 *
 * <p>POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's
 * list and, in the same change, re-decides every account it matches: each sends account.risk_changed
 * when a decision changes. Needs the admin or owner role, or a management key of the environment.
 */
public class ListEntryRequest {
    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    @JsonProperty("kind")
    private ListEntryKind kind;

    /**
     * An email address for kind email, written as in an assessment request; an LDH domain (A-label for an
     * internationalised one) such as example.com for kind email_domain, which must sit below a public
     * suffix (co.uk alone is refused with 400). NoResponse stores it in canonical form and answers that
     * form.
     */
    @JsonProperty("value")
    private String value;

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    @JsonProperty("action")
    private ListEntryAction action;

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("note")
    private String note;

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonCreator
    public ListEntryRequest(
            @JsonProperty("kind") @Nonnull ListEntryKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("action") @Nonnull ListEntryAction action,
            @JsonProperty("note") @Nullable String note,
            @JsonProperty("expires_at") @Nullable String expiresAt) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.action =
                Optional.ofNullable(action).orElseThrow(() -> new IllegalArgumentException("action cannot be null"));
        this.note = note;
        this.expiresAt = expiresAt;
    }

    public ListEntryRequest(@Nonnull ListEntryKind kind, @Nonnull String value, @Nonnull ListEntryAction action) {
        this(kind, value, action, null, null);
    }

    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    public ListEntryKind kind() {
        return this.kind;
    }

    /**
     * An email address for kind email, written as in an assessment request; an LDH domain (A-label for an
     * internationalised one) such as example.com for kind email_domain, which must sit below a public
     * suffix (co.uk alone is refused with 400). NoResponse stores it in canonical form and answers that
     * form.
     */
    public String value() {
        return this.value;
    }

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    public ListEntryAction action() {
        return this.action;
    }

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    public Optional<String> note() {
        return Optional.ofNullable(this.note);
    }

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    public Optional<String> expiresAt() {
        return Optional.ofNullable(this.expiresAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * email matches one address, whatever the case of its local part. email_domain matches every address
     * of the registrable domain and of its subdomains.
     */
    public ListEntryRequest withKind(@Nonnull ListEntryKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * An email address for kind email, written as in an assessment request; an LDH domain (A-label for an
     * internationalised one) such as example.com for kind email_domain, which must sit below a public
     * suffix (co.uk alone is refused with 400). NoResponse stores it in canonical form and answers that
     * form.
     */
    public ListEntryRequest withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
     * match.
     */
    public ListEntryRequest withAction(@Nonnull ListEntryAction action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    /**
     * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
     * nothing.
     */
    public ListEntryRequest withNote(@Nullable String note) {
        this.note = note;
        return this;
    }

    /**
     * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
     * propagates account risk changes.
     */
    public ListEntryRequest withExpiresAt(@Nullable String expiresAt) {
        this.expiresAt = expiresAt;
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
        ListEntryRequest other = (ListEntryRequest) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.note, other.note)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, value, action, note, expiresAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListEntryRequest.class,
                "kind",
                kind,
                "value",
                value,
                "action",
                action,
                "note",
                note,
                "expiresAt",
                expiresAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ListEntryKind kind;

        private String value;

        private ListEntryAction action;

        private String note;

        private String expiresAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * email matches one address, whatever the case of its local part. email_domain matches every address
         * of the registrable domain and of its subdomains.
         */
        public Builder kind(@Nonnull ListEntryKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * An email address for kind email, written as in an assessment request; an LDH domain (A-label for an
         * internationalised one) such as example.com for kind email_domain, which must sit below a public
         * suffix (co.uk alone is refused with 400). NoResponse stores it in canonical form and answers that
         * form.
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        /**
         * block decides block for the accounts the entry matches; allow decides allow. Block wins when both
         * match.
         */
        public Builder action(@Nonnull ListEntryAction action) {
            this.action = Utils.checkNotNull(action, "action");
            return this;
        }

        /**
         * Why the entry exists, for the people who manage the list. It is shown with the entry and decides
         * nothing.
         */
        public Builder note(@Nullable String note) {
            this.note = note;
            return this;
        }

        /**
         * Optional expiry. An expired entry stops matching immediately; bounded maintenance removes it and
         * propagates account risk changes.
         */
        public Builder expiresAt(@Nullable String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public ListEntryRequest build() {
            return new ListEntryRequest(kind, value, action, note, expiresAt);
        }
    }
}
