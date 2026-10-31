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

/**
 * Breach
 *
 * <p>One breach table that matched, detailed view only. name is the catalogue's display name for the
 * table's site, not the upstream provider. No member carries a record, value, password or hash.
 */
public class Breach {

    @JsonProperty("name")
    private String name;

    /**
     * The breached site's domain, lower case. Absent when the table has none (a credential list, for
     * example).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("domain")
    private String domain;

    /**
     * The table's category from its name, lower case (social, gaming, combolist, ...).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("category")
    private String category;

    /**
     * Who is in the table: site_breach, a site's own users; credential_list, a combolist or compilation;
     * stealer_log, malware or botnet victims; scrape; hacking_forum, a criminal hacking, cracking or
     * carding community; abuse_service, the customers of DDoS-for-hire, account generators, dump or card
     * shops, darknet markets, stalkerware or breach-lookup services; darkweb_site, an onion site of
     * unknown purpose.
     */
    @JsonProperty("kind")
    private BreachKind kind;

    /**
     * A breach table's date year.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("year")
    private Long year;

    /**
     * Absent when only the year is known.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("month")
    private Long month;

    /**
     * The submitted email or username whose own search matched the table. A submitted full name is
     * searched as well, and what it finds feeds the identity's reasons rather than this list.
     */
    @JsonProperty("matched_on")
    private BreachSelector matchedOn;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("exposed")
    private List<BreachExposed> exposed;

    /**
     * What this table's rows record against the matched email or username: the tables behind the
     * forum_sanctioned and prior_payment_abuse reasons. Absent when the rows record none, for a table
     * matched on an IP address, and from an answer stored before it was recorded.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("conduct")
    private List<BreachConduct> conduct;

    @JsonCreator
    public Breach(
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("domain") @Nullable String domain,
            @JsonProperty("category") @Nullable String category,
            @JsonProperty("kind") @Nonnull BreachKind kind,
            @JsonProperty("year") @Nullable Long year,
            @JsonProperty("month") @Nullable Long month,
            @JsonProperty("matched_on") @Nonnull BreachSelector matchedOn,
            @JsonProperty("exposed") @Nullable List<BreachExposed> exposed,
            @JsonProperty("conduct") @Nullable List<BreachConduct> conduct) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.domain = domain;
        this.category = category;
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.year = year;
        this.month = month;
        this.matchedOn = Optional.ofNullable(matchedOn)
                .orElseThrow(() -> new IllegalArgumentException("matchedOn cannot be null"));
        this.exposed = exposed;
        this.conduct = conduct;
    }

    public Breach(@Nonnull String name, @Nonnull BreachKind kind, @Nonnull BreachSelector matchedOn) {
        this(name, null, null, kind, null, null, matchedOn, null, null);
    }

    public String name() {
        return this.name;
    }

    /**
     * The breached site's domain, lower case. Absent when the table has none (a credential list, for
     * example).
     */
    public Optional<String> domain() {
        return Optional.ofNullable(this.domain);
    }

    /**
     * The table's category from its name, lower case (social, gaming, combolist, ...).
     */
    public Optional<String> category() {
        return Optional.ofNullable(this.category);
    }

    /**
     * Who is in the table: site_breach, a site's own users; credential_list, a combolist or compilation;
     * stealer_log, malware or botnet victims; scrape; hacking_forum, a criminal hacking, cracking or
     * carding community; abuse_service, the customers of DDoS-for-hire, account generators, dump or card
     * shops, darknet markets, stalkerware or breach-lookup services; darkweb_site, an onion site of
     * unknown purpose.
     */
    public BreachKind kind() {
        return this.kind;
    }

    /**
     * A breach table's date year.
     */
    public Optional<Long> year() {
        return Optional.ofNullable(this.year);
    }

    /**
     * Absent when only the year is known.
     */
    public Optional<Long> month() {
        return Optional.ofNullable(this.month);
    }

    /**
     * The submitted email or username whose own search matched the table. A submitted full name is
     * searched as well, and what it finds feeds the identity's reasons rather than this list.
     */
    public BreachSelector matchedOn() {
        return this.matchedOn;
    }

    public Optional<List<BreachExposed>> exposed() {
        return Optional.ofNullable(this.exposed);
    }

    /**
     * What this table's rows record against the matched email or username: the tables behind the
     * forum_sanctioned and prior_payment_abuse reasons. Absent when the rows record none, for a table
     * matched on an IP address, and from an answer stored before it was recorded.
     */
    public Optional<List<BreachConduct>> conduct() {
        return Optional.ofNullable(this.conduct);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Breach withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * The breached site's domain, lower case. Absent when the table has none (a credential list, for
     * example).
     */
    public Breach withDomain(@Nullable String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * The table's category from its name, lower case (social, gaming, combolist, ...).
     */
    public Breach withCategory(@Nullable String category) {
        this.category = category;
        return this;
    }

    /**
     * Who is in the table: site_breach, a site's own users; credential_list, a combolist or compilation;
     * stealer_log, malware or botnet victims; scrape; hacking_forum, a criminal hacking, cracking or
     * carding community; abuse_service, the customers of DDoS-for-hire, account generators, dump or card
     * shops, darknet markets, stalkerware or breach-lookup services; darkweb_site, an onion site of
     * unknown purpose.
     */
    public Breach withKind(@Nonnull BreachKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * A breach table's date year.
     */
    public Breach withYear(@Nullable Long year) {
        this.year = year;
        return this;
    }

    /**
     * Absent when only the year is known.
     */
    public Breach withMonth(@Nullable Long month) {
        this.month = month;
        return this;
    }

    /**
     * The submitted email or username whose own search matched the table. A submitted full name is
     * searched as well, and what it finds feeds the identity's reasons rather than this list.
     */
    public Breach withMatchedOn(@Nonnull BreachSelector matchedOn) {
        this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
        return this;
    }

    public Breach withExposed(@Nullable List<BreachExposed> exposed) {
        this.exposed = exposed;
        return this;
    }

    /**
     * What this table's rows record against the matched email or username: the tables behind the
     * forum_sanctioned and prior_payment_abuse reasons. Absent when the rows record none, for a table
     * matched on an IP address, and from an answer stored before it was recorded.
     */
    public Breach withConduct(@Nullable List<BreachConduct> conduct) {
        this.conduct = conduct;
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
        Breach other = (Breach) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.domain, other.domain)
                && Utils.enhancedDeepEquals(this.category, other.category)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.year, other.year)
                && Utils.enhancedDeepEquals(this.month, other.month)
                && Utils.enhancedDeepEquals(this.matchedOn, other.matchedOn)
                && Utils.enhancedDeepEquals(this.exposed, other.exposed)
                && Utils.enhancedDeepEquals(this.conduct, other.conduct);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, domain, category, kind, year, month, matchedOn, exposed, conduct);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Breach.class,
                "name",
                name,
                "domain",
                domain,
                "category",
                category,
                "kind",
                kind,
                "year",
                year,
                "month",
                month,
                "matchedOn",
                matchedOn,
                "exposed",
                exposed,
                "conduct",
                conduct);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private String domain;

        private String category;

        private BreachKind kind;

        private Long year;

        private Long month;

        private BreachSelector matchedOn;

        private List<BreachExposed> exposed;

        private List<BreachConduct> conduct;

        private Builder() {
            // force use of static builder() method
        }

        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * The breached site's domain, lower case. Absent when the table has none (a credential list, for
         * example).
         */
        public Builder domain(@Nullable String domain) {
            this.domain = domain;
            return this;
        }

        /**
         * The table's category from its name, lower case (social, gaming, combolist, ...).
         */
        public Builder category(@Nullable String category) {
            this.category = category;
            return this;
        }

        /**
         * Who is in the table: site_breach, a site's own users; credential_list, a combolist or compilation;
         * stealer_log, malware or botnet victims; scrape; hacking_forum, a criminal hacking, cracking or
         * carding community; abuse_service, the customers of DDoS-for-hire, account generators, dump or card
         * shops, darknet markets, stalkerware or breach-lookup services; darkweb_site, an onion site of
         * unknown purpose.
         */
        public Builder kind(@Nonnull BreachKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * A breach table's date year.
         */
        public Builder year(@Nullable Long year) {
            this.year = year;
            return this;
        }

        /**
         * Absent when only the year is known.
         */
        public Builder month(@Nullable Long month) {
            this.month = month;
            return this;
        }

        /**
         * The submitted email or username whose own search matched the table. A submitted full name is
         * searched as well, and what it finds feeds the identity's reasons rather than this list.
         */
        public Builder matchedOn(@Nonnull BreachSelector matchedOn) {
            this.matchedOn = Utils.checkNotNull(matchedOn, "matchedOn");
            return this;
        }

        public Builder exposed(@Nullable List<BreachExposed> exposed) {
            this.exposed = exposed;
            return this;
        }

        /**
         * What this table's rows record against the matched email or username: the tables behind the
         * forum_sanctioned and prior_payment_abuse reasons. Absent when the rows record none, for a table
         * matched on an IP address, and from an answer stored before it was recorded.
         */
        public Builder conduct(@Nullable List<BreachConduct> conduct) {
            this.conduct = conduct;
            return this;
        }

        public Breach build() {
            return new Breach(name, domain, category, kind, year, month, matchedOn, exposed, conduct);
        }
    }
}
