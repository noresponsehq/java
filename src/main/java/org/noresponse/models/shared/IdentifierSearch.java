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
 * IdentifierSearch
 *
 * <p>One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
 * canonical uses the supported identifier normalisation without an ownership claim: email preserves
 * local-part case, NFC-normalises it and lowercases the domain without removing dots or tags; phone
 * uses international E.164 without extensions or an inferred region; username uses NFC and Unicode
 * case folding within the customer namespace.
 *
 * <p>mailbox is email-only and explicitly selects existing list rules: case-insensitive, remove +tag, and
 * ignore local-part dots at gmail.com and googlemail.com; googlemail.com maps to gmail.com. Username
 * requires namespace customer. Email and phone forbid namespace.
 *
 * <p>Source-discovered identifiers and platform handles are not searched. Unsupported kinds, namespaces,
 * modes and invalid values return 400. Search equality does not establish verified ownership or merge
 * accounts.
 */
public class IdentifierSearch {

    @JsonProperty("kind")
    private IdentifierSearchKind kind;

    /**
     * The comparison value. Stored original identifiers remain unchanged. An invalid identifier is refused
     * instead of returning an empty result.
     */
    @JsonProperty("value")
    private String value;

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("match")
    private Match match;

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("namespace")
    private Namespace namespace;

    @JsonCreator
    public IdentifierSearch(
            @JsonProperty("kind") @Nonnull IdentifierSearchKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("match") @Nullable Match match,
            @JsonProperty("namespace") @Nullable Namespace namespace) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.match = match;
        this.namespace = namespace;
    }

    public IdentifierSearch(@Nonnull IdentifierSearchKind kind, @Nonnull String value) {
        this(kind, value, null, null);
    }

    public IdentifierSearchKind kind() {
        return this.kind;
    }

    /**
     * The comparison value. Stored original identifiers remain unchanged. An invalid identifier is refused
     * instead of returning an empty result.
     */
    public String value() {
        return this.value;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public Optional<Match> match() {
        return Optional.ofNullable(this.match);
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public Optional<Namespace> namespace() {
        return Optional.ofNullable(this.namespace);
    }

    public static Builder builder() {
        return new Builder();
    }

    public IdentifierSearch withKind(@Nonnull IdentifierSearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The comparison value. Stored original identifiers remain unchanged. An invalid identifier is refused
     * instead of returning an empty result.
     */
    public IdentifierSearch withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public IdentifierSearch withMatch(@Nullable Match match) {
        this.match = match;
        return this;
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public IdentifierSearch withNamespace(@Nullable Namespace namespace) {
        this.namespace = namespace;
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
        IdentifierSearch other = (IdentifierSearch) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.match, other.match)
                && Utils.enhancedDeepEquals(this.namespace, other.namespace);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, value, match, namespace);
    }

    @Override
    public String toString() {
        return Utils.toString(
                IdentifierSearch.class, "kind", kind, "value", value, "match", match, "namespace", namespace);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearchKind kind;

        private String value;

        private Match match;

        private Namespace namespace;

        private Builder() {
            // force use of static builder() method
        }

        public Builder kind(@Nonnull IdentifierSearchKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The comparison value. Stored original identifiers remain unchanged. An invalid identifier is refused
         * instead of returning an empty result.
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        /**
         * exact compares the original submitted value; canonical uses identifier-specific normalisation;
         * mailbox explicitly selects the email alias rules used by lists.
         */
        public Builder match(@Nullable Match match) {
            this.match = match;
            return this;
        }

        /**
         * Required for username: the username submitted by this customer in this workspace/environment. It
         * does not name or join a source platform. Omit for email and phone.
         */
        public Builder namespace(@Nullable Namespace namespace) {
            this.namespace = namespace;
            return this;
        }

        public IdentifierSearch build() {
            return new IdentifierSearch(kind, value, match, namespace);
        }
    }
}
