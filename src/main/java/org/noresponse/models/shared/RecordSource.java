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
 * RecordSource
 *
 * <p>Where a record came from.
 */
public class RecordSource {
    /**
     * The id of the connector that read the record: breach for a breach row.
     */
    @JsonProperty("connector")
    private String connector;

    /**
     * The kind of source: platform for an account read off a live platform; for a breach row, the kind of
     * leak its table is: site_breach, credential_list, stealer_log, scrape, hacking_forum, abuse_service
     * or darkweb_site.
     */
    @JsonProperty("kind")
    private String kind;

    /**
     * The host a person knows the source's site by.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("site")
    private String site;

    /**
     * The table a leaked row came from.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("table")
    private String table;

    /**
     * When the source's data dates from, at the precision it is known: YYYY or YYYY-MM for a breach table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("date")
    private String date;

    @JsonCreator
    public RecordSource(
            @JsonProperty("connector") @Nonnull String connector,
            @JsonProperty("kind") @Nonnull String kind,
            @JsonProperty("site") @Nullable String site,
            @JsonProperty("table") @Nullable String table,
            @JsonProperty("date") @Nullable String date) {
        this.connector = Optional.ofNullable(connector)
                .orElseThrow(() -> new IllegalArgumentException("connector cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.site = site;
        this.table = table;
        this.date = date;
    }

    public RecordSource(@Nonnull String connector, @Nonnull String kind) {
        this(connector, kind, null, null, null);
    }

    /**
     * The id of the connector that read the record: breach for a breach row.
     */
    public String connector() {
        return this.connector;
    }

    /**
     * The kind of source: platform for an account read off a live platform; for a breach row, the kind of
     * leak its table is: site_breach, credential_list, stealer_log, scrape, hacking_forum, abuse_service
     * or darkweb_site.
     */
    public String kind() {
        return this.kind;
    }

    /**
     * The host a person knows the source's site by.
     */
    public Optional<String> site() {
        return Optional.ofNullable(this.site);
    }

    /**
     * The table a leaked row came from.
     */
    public Optional<String> table() {
        return Optional.ofNullable(this.table);
    }

    /**
     * When the source's data dates from, at the precision it is known: YYYY or YYYY-MM for a breach table.
     */
    public Optional<String> date() {
        return Optional.ofNullable(this.date);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The id of the connector that read the record: breach for a breach row.
     */
    public RecordSource withConnector(@Nonnull String connector) {
        this.connector = Utils.checkNotNull(connector, "connector");
        return this;
    }

    /**
     * The kind of source: platform for an account read off a live platform; for a breach row, the kind of
     * leak its table is: site_breach, credential_list, stealer_log, scrape, hacking_forum, abuse_service
     * or darkweb_site.
     */
    public RecordSource withKind(@Nonnull String kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The host a person knows the source's site by.
     */
    public RecordSource withSite(@Nullable String site) {
        this.site = site;
        return this;
    }

    /**
     * The table a leaked row came from.
     */
    public RecordSource withTable(@Nullable String table) {
        this.table = table;
        return this;
    }

    /**
     * When the source's data dates from, at the precision it is known: YYYY or YYYY-MM for a breach table.
     */
    public RecordSource withDate(@Nullable String date) {
        this.date = date;
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
        RecordSource other = (RecordSource) o;
        return Utils.enhancedDeepEquals(this.connector, other.connector)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.site, other.site)
                && Utils.enhancedDeepEquals(this.table, other.table)
                && Utils.enhancedDeepEquals(this.date, other.date);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(connector, kind, site, table, date);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordSource.class, "connector", connector, "kind", kind, "site", site, "table", table, "date", date);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String connector;

        private String kind;

        private String site;

        private String table;

        private String date;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The id of the connector that read the record: breach for a breach row.
         */
        public Builder connector(@Nonnull String connector) {
            this.connector = Utils.checkNotNull(connector, "connector");
            return this;
        }

        /**
         * The kind of source: platform for an account read off a live platform; for a breach row, the kind of
         * leak its table is: site_breach, credential_list, stealer_log, scrape, hacking_forum, abuse_service
         * or darkweb_site.
         */
        public Builder kind(@Nonnull String kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The host a person knows the source's site by.
         */
        public Builder site(@Nullable String site) {
            this.site = site;
            return this;
        }

        /**
         * The table a leaked row came from.
         */
        public Builder table(@Nullable String table) {
            this.table = table;
            return this;
        }

        /**
         * When the source's data dates from, at the precision it is known: YYYY or YYYY-MM for a breach table.
         */
        public Builder date(@Nullable String date) {
            this.date = date;
            return this;
        }

        public RecordSource build() {
            return new RecordSource(connector, kind, site, table, date);
        }
    }
}
