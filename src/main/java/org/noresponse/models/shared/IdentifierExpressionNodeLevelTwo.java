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

public class IdentifierExpressionNodeLevelTwo {
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
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("term")
    private IdentifierSearch term;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("and")
    private List<IdentifierExpressionNodeLevelOne> and;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("or")
    private List<IdentifierExpressionNodeLevelOne> or;

    @JsonCreator
    public IdentifierExpressionNodeLevelTwo(
            @JsonProperty("term") @Nullable IdentifierSearch term,
            @JsonProperty("and") @Nullable List<IdentifierExpressionNodeLevelOne> and,
            @JsonProperty("or") @Nullable List<IdentifierExpressionNodeLevelOne> or) {
        this.term = term;
        this.and = and;
        this.or = or;
    }

    public IdentifierExpressionNodeLevelTwo() {
        this(null, null, null);
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
    public Optional<IdentifierSearch> term() {
        return Optional.ofNullable(this.term);
    }

    public Optional<List<IdentifierExpressionNodeLevelOne>> and() {
        return Optional.ofNullable(this.and);
    }

    public Optional<List<IdentifierExpressionNodeLevelOne>> or() {
        return Optional.ofNullable(this.or);
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
    public IdentifierExpressionNodeLevelTwo withTerm(@Nullable IdentifierSearch term) {
        this.term = term;
        return this;
    }

    public IdentifierExpressionNodeLevelTwo withAnd(@Nullable List<IdentifierExpressionNodeLevelOne> and) {
        this.and = and;
        return this;
    }

    public IdentifierExpressionNodeLevelTwo withOr(@Nullable List<IdentifierExpressionNodeLevelOne> or) {
        this.or = or;
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
        IdentifierExpressionNodeLevelTwo other = (IdentifierExpressionNodeLevelTwo) o;
        return Utils.enhancedDeepEquals(this.term, other.term)
                && Utils.enhancedDeepEquals(this.and, other.and)
                && Utils.enhancedDeepEquals(this.or, other.or);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(term, and, or);
    }

    @Override
    public String toString() {
        return Utils.toString(IdentifierExpressionNodeLevelTwo.class, "term", term, "and", and, "or", or);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearch term;

        private List<IdentifierExpressionNodeLevelOne> and;

        private List<IdentifierExpressionNodeLevelOne> or;

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
        public Builder term(@Nullable IdentifierSearch term) {
            this.term = term;
            return this;
        }

        public Builder and(@Nullable List<IdentifierExpressionNodeLevelOne> and) {
            this.and = and;
            return this;
        }

        public Builder or(@Nullable List<IdentifierExpressionNodeLevelOne> or) {
            this.or = or;
            return this;
        }

        public IdentifierExpressionNodeLevelTwo build() {
            return new IdentifierExpressionNodeLevelTwo(term, and, or);
        }
    }
}
