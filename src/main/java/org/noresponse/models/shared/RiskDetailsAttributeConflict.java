package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskDetailsAttributeConflict {
    /**
     * name_gender is the records' gender against the submitted first name's usual one.
     */
    @JsonProperty("attribute")
    private Attribute attribute;

    /**
     * The domains of the breached sites whose tables report the attribute, newest breach first, at most
     * 32. Absent when none of the tables has a site.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("sites")
    private List<String> sites;

    /**
     * How many of the tables reporting the attribute have no site, such as a credential list or a stealer
     * log. Absent when none.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("unsited")
    private Long unsited;

    @JsonCreator
    public RiskDetailsAttributeConflict(
            @JsonProperty("attribute") @Nonnull Attribute attribute,
            @JsonProperty("sites") @Nullable List<String> sites,
            @JsonProperty("unsited") @Nullable Long unsited) {
        this.attribute = Optional.ofNullable(attribute)
                .orElseThrow(() -> new IllegalArgumentException("attribute cannot be null"));
        this.sites = sites;
        this.unsited = unsited;
    }

    public RiskDetailsAttributeConflict(@Nonnull Attribute attribute) {
        this(attribute, null, null);
    }

    /**
     * name_gender is the records' gender against the submitted first name's usual one.
     */
    public Attribute attribute() {
        return this.attribute;
    }

    /**
     * The domains of the breached sites whose tables report the attribute, newest breach first, at most
     * 32. Absent when none of the tables has a site.
     */
    public Optional<List<String>> sites() {
        return Optional.ofNullable(this.sites);
    }

    /**
     * How many of the tables reporting the attribute have no site, such as a credential list or a stealer
     * log. Absent when none.
     */
    public Optional<Long> unsited() {
        return Optional.ofNullable(this.unsited);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * name_gender is the records' gender against the submitted first name's usual one.
     */
    public RiskDetailsAttributeConflict withAttribute(@Nonnull Attribute attribute) {
        this.attribute = Utils.checkNotNull(attribute, "attribute");
        return this;
    }

    /**
     * The domains of the breached sites whose tables report the attribute, newest breach first, at most
     * 32. Absent when none of the tables has a site.
     */
    public RiskDetailsAttributeConflict withSites(@Nullable List<String> sites) {
        this.sites = sites;
        return this;
    }

    /**
     * How many of the tables reporting the attribute have no site, such as a credential list or a stealer
     * log. Absent when none.
     */
    public RiskDetailsAttributeConflict withUnsited(@Nullable Long unsited) {
        this.unsited = unsited;
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
        RiskDetailsAttributeConflict other = (RiskDetailsAttributeConflict) o;
        return Utils.enhancedDeepEquals(this.attribute, other.attribute)
                && Utils.enhancedDeepEquals(this.sites, other.sites)
                && Utils.enhancedDeepEquals(this.unsited, other.unsited);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(attribute, sites, unsited);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskDetailsAttributeConflict.class, "attribute", attribute, "sites", sites, "unsited", unsited);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Attribute attribute;

        private List<String> sites;

        private Long unsited;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * name_gender is the records' gender against the submitted first name's usual one.
         */
        public Builder attribute(@Nonnull Attribute attribute) {
            this.attribute = Utils.checkNotNull(attribute, "attribute");
            return this;
        }

        /**
         * The domains of the breached sites whose tables report the attribute, newest breach first, at most
         * 32. Absent when none of the tables has a site.
         */
        public Builder sites(@Nullable List<String> sites) {
            this.sites = sites;
            return this;
        }

        /**
         * How many of the tables reporting the attribute have no site, such as a credential list or a stealer
         * log. Absent when none.
         */
        public Builder unsited(@Nullable Long unsited) {
            this.unsited = unsited;
            return this;
        }

        public RiskDetailsAttributeConflict build() {
            return new RiskDetailsAttributeConflict(attribute, sites, unsited);
        }
    }
}
