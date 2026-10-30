package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class IdentifierExpressionLeaf {
    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
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
    @JsonProperty("term")
    private IdentifierSearch term;

    @JsonCreator
    public IdentifierExpressionLeaf(@JsonProperty("term") @Nonnull IdentifierSearch term) {
        this.term = Optional.ofNullable(term).orElseThrow(() -> new IllegalArgumentException("term cannot be null"));
    }

    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
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
    public IdentifierSearch term() {
        return this.term;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
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
    public IdentifierExpressionLeaf withTerm(@Nonnull IdentifierSearch term) {
        this.term = Utils.checkNotNull(term, "term");
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
        IdentifierExpressionLeaf other = (IdentifierExpressionLeaf) o;
        return Utils.enhancedDeepEquals(this.term, other.term);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(term);
    }

    @Override
    public String toString() {
        return Utils.toString(IdentifierExpressionLeaf.class, "term", term);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearch term;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One customer-submitted identifier. Defaults to exact comparison of the original submitted value.
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
        public Builder term(@Nonnull IdentifierSearch term) {
            this.term = Utils.checkNotNull(term, "term");
            return this;
        }

        public IdentifierExpressionLeaf build() {
            return new IdentifierExpressionLeaf(term);
        }
    }
}
