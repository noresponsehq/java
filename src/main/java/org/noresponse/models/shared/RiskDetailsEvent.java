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
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskDetailsEvent {

    @JsonProperty("year")
    private long year;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("month")
    private Long month;

    @JsonProperty("kind")
    private RiskDetailsEventKind kind;

    @JsonProperty("site")
    private String site;

    @JsonProperty("category")
    private String category;

    /**
     * The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider
     * (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and
     * fraud communities' tables) and code_hosting (a code hosting profile).
     */
    @JsonProperty("family")
    private FootprintFamily family;

    @JsonProperty("identifier")
    private Identifier identifier;

    @JsonCreator
    public RiskDetailsEvent(
            @JsonProperty("year") long year,
            @JsonProperty("month") @Nullable Long month,
            @JsonProperty("kind") @Nonnull RiskDetailsEventKind kind,
            @JsonProperty("site") @Nonnull String site,
            @JsonProperty("category") @Nonnull String category,
            @JsonProperty("family") @Nonnull FootprintFamily family,
            @JsonProperty("identifier") @Nonnull Identifier identifier) {
        this.year = year;
        this.month = month;
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.site = Optional.ofNullable(site).orElseThrow(() -> new IllegalArgumentException("site cannot be null"));
        this.category = Optional.ofNullable(category)
                .orElseThrow(() -> new IllegalArgumentException("category cannot be null"));
        this.family =
                Optional.ofNullable(family).orElseThrow(() -> new IllegalArgumentException("family cannot be null"));
        this.identifier = Optional.ofNullable(identifier)
                .orElseThrow(() -> new IllegalArgumentException("identifier cannot be null"));
    }

    public RiskDetailsEvent(
            long year,
            @Nonnull RiskDetailsEventKind kind,
            @Nonnull String site,
            @Nonnull String category,
            @Nonnull FootprintFamily family,
            @Nonnull Identifier identifier) {
        this(year, null, kind, site, category, family, identifier);
    }

    public long year() {
        return this.year;
    }

    public Optional<Long> month() {
        return Optional.ofNullable(this.month);
    }

    public RiskDetailsEventKind kind() {
        return this.kind;
    }

    public String site() {
        return this.site;
    }

    public String category() {
        return this.category;
    }

    /**
     * The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider
     * (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and
     * fraud communities' tables) and code_hosting (a code hosting profile).
     */
    public FootprintFamily family() {
        return this.family;
    }

    public Identifier identifier() {
        return this.identifier;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskDetailsEvent withYear(long year) {
        this.year = year;
        return this;
    }

    public RiskDetailsEvent withMonth(@Nullable Long month) {
        this.month = month;
        return this;
    }

    public RiskDetailsEvent withKind(@Nonnull RiskDetailsEventKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public RiskDetailsEvent withSite(@Nonnull String site) {
        this.site = Utils.checkNotNull(site, "site");
        return this;
    }

    public RiskDetailsEvent withCategory(@Nonnull String category) {
        this.category = Utils.checkNotNull(category, "category");
        return this;
    }

    /**
     * The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider
     * (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and
     * fraud communities' tables) and code_hosting (a code hosting profile).
     */
    public RiskDetailsEvent withFamily(@Nonnull FootprintFamily family) {
        this.family = Utils.checkNotNull(family, "family");
        return this;
    }

    public RiskDetailsEvent withIdentifier(@Nonnull Identifier identifier) {
        this.identifier = Utils.checkNotNull(identifier, "identifier");
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
        RiskDetailsEvent other = (RiskDetailsEvent) o;
        return Utils.enhancedDeepEquals(this.year, other.year)
                && Utils.enhancedDeepEquals(this.month, other.month)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.site, other.site)
                && Utils.enhancedDeepEquals(this.category, other.category)
                && Utils.enhancedDeepEquals(this.family, other.family)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(year, month, kind, site, category, family, identifier);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskDetailsEvent.class,
                "year",
                year,
                "month",
                month,
                "kind",
                kind,
                "site",
                site,
                "category",
                category,
                "family",
                family,
                "identifier",
                identifier);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long year;

        private Long month;

        private RiskDetailsEventKind kind;

        private String site;

        private String category;

        private FootprintFamily family;

        private Identifier identifier;

        private Builder() {
            // force use of static builder() method
        }

        public Builder year(long year) {
            this.year = year;
            return this;
        }

        public Builder month(@Nullable Long month) {
            this.month = month;
            return this;
        }

        public Builder kind(@Nonnull RiskDetailsEventKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder site(@Nonnull String site) {
            this.site = Utils.checkNotNull(site, "site");
            return this;
        }

        public Builder category(@Nonnull String category) {
            this.category = Utils.checkNotNull(category, "category");
            return this;
        }

        /**
         * The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider
         * (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and
         * fraud communities' tables) and code_hosting (a code hosting profile).
         */
        public Builder family(@Nonnull FootprintFamily family) {
            this.family = Utils.checkNotNull(family, "family");
            return this;
        }

        public Builder identifier(@Nonnull Identifier identifier) {
            this.identifier = Utils.checkNotNull(identifier, "identifier");
            return this;
        }

        public RiskDetailsEvent build() {
            return new RiskDetailsEvent(year, month, kind, site, category, family, identifier);
        }
    }
}
