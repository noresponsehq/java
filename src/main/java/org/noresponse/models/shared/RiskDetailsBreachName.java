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

public class RiskDetailsBreachName {

    @JsonProperty("value")
    private String value;

    @JsonProperty("seen_in")
    private long seenIn;

    /**
     * The domains of the breached sites whose tables hold the name, lower case, each once, newest breach
     * first, at most 32: the domain values of breaches[]. Covers the email's and the username's tables
     * together. Absent when none of the tables has a site.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("sites")
    private List<String> sites;

    /**
     * How many of the tables that hold the name have no site, such as a credential list or a stealer log.
     * Absent when none.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("unsited")
    private Long unsited;

    @JsonCreator
    public RiskDetailsBreachName(
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("seen_in") long seenIn,
            @JsonProperty("sites") @Nullable List<String> sites,
            @JsonProperty("unsited") @Nullable Long unsited) {
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.seenIn = seenIn;
        this.sites = sites;
        this.unsited = unsited;
    }

    public RiskDetailsBreachName(@Nonnull String value, long seenIn) {
        this(value, seenIn, null, null);
    }

    public String value() {
        return this.value;
    }

    public long seenIn() {
        return this.seenIn;
    }

    /**
     * The domains of the breached sites whose tables hold the name, lower case, each once, newest breach
     * first, at most 32: the domain values of breaches[]. Covers the email's and the username's tables
     * together. Absent when none of the tables has a site.
     */
    public Optional<List<String>> sites() {
        return Optional.ofNullable(this.sites);
    }

    /**
     * How many of the tables that hold the name have no site, such as a credential list or a stealer log.
     * Absent when none.
     */
    public Optional<Long> unsited() {
        return Optional.ofNullable(this.unsited);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskDetailsBreachName withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    public RiskDetailsBreachName withSeenIn(long seenIn) {
        this.seenIn = seenIn;
        return this;
    }

    /**
     * The domains of the breached sites whose tables hold the name, lower case, each once, newest breach
     * first, at most 32: the domain values of breaches[]. Covers the email's and the username's tables
     * together. Absent when none of the tables has a site.
     */
    public RiskDetailsBreachName withSites(@Nullable List<String> sites) {
        this.sites = sites;
        return this;
    }

    /**
     * How many of the tables that hold the name have no site, such as a credential list or a stealer log.
     * Absent when none.
     */
    public RiskDetailsBreachName withUnsited(@Nullable Long unsited) {
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
        RiskDetailsBreachName other = (RiskDetailsBreachName) o;
        return Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.seenIn, other.seenIn)
                && Utils.enhancedDeepEquals(this.sites, other.sites)
                && Utils.enhancedDeepEquals(this.unsited, other.unsited);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value, seenIn, sites, unsited);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskDetailsBreachName.class, "value", value, "seenIn", seenIn, "sites", sites, "unsited", unsited);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String value;

        private long seenIn;

        private List<String> sites;

        private Long unsited;

        private Builder() {
            // force use of static builder() method
        }

        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        public Builder seenIn(long seenIn) {
            this.seenIn = seenIn;
            return this;
        }

        /**
         * The domains of the breached sites whose tables hold the name, lower case, each once, newest breach
         * first, at most 32: the domain values of breaches[]. Covers the email's and the username's tables
         * together. Absent when none of the tables has a site.
         */
        public Builder sites(@Nullable List<String> sites) {
            this.sites = sites;
            return this;
        }

        /**
         * How many of the tables that hold the name have no site, such as a credential list or a stealer log.
         * Absent when none.
         */
        public Builder unsited(@Nullable Long unsited) {
            this.unsited = unsited;
            return this;
        }

        public RiskDetailsBreachName build() {
            return new RiskDetailsBreachName(value, seenIn, sites, unsited);
        }
    }
}
