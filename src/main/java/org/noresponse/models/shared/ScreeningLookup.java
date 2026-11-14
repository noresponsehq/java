package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningLookup
 *
 * <p>What the signup check's lookups found for a person: account presence for the email, phone and
 * usernames (the person's handles), the phone lookup, the email domain's registration and the breach
 * search. Context and coverage for the reviewer, not a finding: nothing here sets a band or a match
 * state.
 */
public class ScreeningLookup {
    /**
     * Each lane each identifier was looked up in, identifiers in the order email, phone, username and
     * lanes in the order accounts, phone, breach, domain. Every lane the person's identifiers are looked
     * up in is listed: one that no source answered, or that no source could be asked in, is not_covered.
     * Empty when the person has no email, phone or handle to look up.
     */
    @JsonProperty("checks")
    private List<ScreeningLookupCheck> checks;

    /**
     * A few typed facts per identifier: an assessment's risk.summary, and a screened person's lookup
     * summary. Every fact is optional: absent means the evidence did not answer and makes no claim, while
     * false or 0 is a definite answer.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("summary")
    private RiskSummary summary;

    /**
     * Merged observations behind the summary, served only to a caller who may read evidence: no provider,
     * source, origin, raw breach record or score.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("details")
    private RiskDetails details;

    /**
     * What each source answered, whole, each answer once: every field of each account a connector found,
     * and every column of each breach row the person's email or usernames matched, under the source's own
     * names, and what each search a person started found, each match naming its search. Anyone who may
     * read the person reads them: no extra permission applies, breach passwords and hashes included.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("records")
    private List<Record> records;

    /**
     * For each search and source whose matched rows records leave out, how many: a breach search returns
     * at most 20 rows of a table and 100 in all, from its first 64 tables by name. A search a person
     * starts on one table of its source fetches the rest of that table, up to 100 rows. Absent when
     * records hold every matched row.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("remaining")
    private List<RecordRemaining> remaining;

    /**
     * When the lookups ran.
     */
    @JsonProperty("looked_at")
    private String lookedAt;

    @JsonCreator
    public ScreeningLookup(
            @JsonProperty("checks") @Nonnull List<ScreeningLookupCheck> checks,
            @JsonProperty("summary") @Nullable RiskSummary summary,
            @JsonProperty("details") @Nullable RiskDetails details,
            @JsonProperty("records") @Nullable List<Record> records,
            @JsonProperty("remaining") @Nullable List<RecordRemaining> remaining,
            @JsonProperty("looked_at") @Nonnull String lookedAt) {
        this.checks =
                Optional.ofNullable(checks).orElseThrow(() -> new IllegalArgumentException("checks cannot be null"));
        this.summary = summary;
        this.details = details;
        this.records = records;
        this.remaining = remaining;
        this.lookedAt = Optional.ofNullable(lookedAt)
                .orElseThrow(() -> new IllegalArgumentException("lookedAt cannot be null"));
    }

    public ScreeningLookup(@Nonnull List<ScreeningLookupCheck> checks, @Nonnull String lookedAt) {
        this(checks, null, null, null, null, lookedAt);
    }

    /**
     * Each lane each identifier was looked up in, identifiers in the order email, phone, username and
     * lanes in the order accounts, phone, breach, domain. Every lane the person's identifiers are looked
     * up in is listed: one that no source answered, or that no source could be asked in, is not_covered.
     * Empty when the person has no email, phone or handle to look up.
     */
    public List<ScreeningLookupCheck> checks() {
        return this.checks;
    }

    /**
     * A few typed facts per identifier: an assessment's risk.summary, and a screened person's lookup
     * summary. Every fact is optional: absent means the evidence did not answer and makes no claim, while
     * false or 0 is a definite answer.
     */
    public Optional<RiskSummary> summary() {
        return Optional.ofNullable(this.summary);
    }

    /**
     * Merged observations behind the summary, served only to a caller who may read evidence: no provider,
     * source, origin, raw breach record or score.
     */
    public Optional<RiskDetails> details() {
        return Optional.ofNullable(this.details);
    }

    /**
     * What each source answered, whole, each answer once: every field of each account a connector found,
     * and every column of each breach row the person's email or usernames matched, under the source's own
     * names, and what each search a person started found, each match naming its search. Anyone who may
     * read the person reads them: no extra permission applies, breach passwords and hashes included.
     */
    public Optional<List<Record>> records() {
        return Optional.ofNullable(this.records);
    }

    /**
     * For each search and source whose matched rows records leave out, how many: a breach search returns
     * at most 20 rows of a table and 100 in all, from its first 64 tables by name. A search a person
     * starts on one table of its source fetches the rest of that table, up to 100 rows. Absent when
     * records hold every matched row.
     */
    public Optional<List<RecordRemaining>> remaining() {
        return Optional.ofNullable(this.remaining);
    }

    /**
     * When the lookups ran.
     */
    public String lookedAt() {
        return this.lookedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Each lane each identifier was looked up in, identifiers in the order email, phone, username and
     * lanes in the order accounts, phone, breach, domain. Every lane the person's identifiers are looked
     * up in is listed: one that no source answered, or that no source could be asked in, is not_covered.
     * Empty when the person has no email, phone or handle to look up.
     */
    public ScreeningLookup withChecks(@Nonnull List<ScreeningLookupCheck> checks) {
        this.checks = Utils.checkNotNull(checks, "checks");
        return this;
    }

    /**
     * A few typed facts per identifier: an assessment's risk.summary, and a screened person's lookup
     * summary. Every fact is optional: absent means the evidence did not answer and makes no claim, while
     * false or 0 is a definite answer.
     */
    public ScreeningLookup withSummary(@Nullable RiskSummary summary) {
        this.summary = summary;
        return this;
    }

    /**
     * Merged observations behind the summary, served only to a caller who may read evidence: no provider,
     * source, origin, raw breach record or score.
     */
    public ScreeningLookup withDetails(@Nullable RiskDetails details) {
        this.details = details;
        return this;
    }

    /**
     * What each source answered, whole, each answer once: every field of each account a connector found,
     * and every column of each breach row the person's email or usernames matched, under the source's own
     * names, and what each search a person started found, each match naming its search. Anyone who may
     * read the person reads them: no extra permission applies, breach passwords and hashes included.
     */
    public ScreeningLookup withRecords(@Nullable List<Record> records) {
        this.records = records;
        return this;
    }

    /**
     * For each search and source whose matched rows records leave out, how many: a breach search returns
     * at most 20 rows of a table and 100 in all, from its first 64 tables by name. A search a person
     * starts on one table of its source fetches the rest of that table, up to 100 rows. Absent when
     * records hold every matched row.
     */
    public ScreeningLookup withRemaining(@Nullable List<RecordRemaining> remaining) {
        this.remaining = remaining;
        return this;
    }

    /**
     * When the lookups ran.
     */
    public ScreeningLookup withLookedAt(@Nonnull String lookedAt) {
        this.lookedAt = Utils.checkNotNull(lookedAt, "lookedAt");
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
        ScreeningLookup other = (ScreeningLookup) o;
        return Utils.enhancedDeepEquals(this.checks, other.checks)
                && Utils.enhancedDeepEquals(this.summary, other.summary)
                && Utils.enhancedDeepEquals(this.details, other.details)
                && Utils.enhancedDeepEquals(this.records, other.records)
                && Utils.enhancedDeepEquals(this.remaining, other.remaining)
                && Utils.enhancedDeepEquals(this.lookedAt, other.lookedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(checks, summary, details, records, remaining, lookedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningLookup.class,
                "checks",
                checks,
                "summary",
                summary,
                "details",
                details,
                "records",
                records,
                "remaining",
                remaining,
                "lookedAt",
                lookedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<ScreeningLookupCheck> checks;

        private RiskSummary summary;

        private RiskDetails details;

        private List<Record> records;

        private List<RecordRemaining> remaining;

        private String lookedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Each lane each identifier was looked up in, identifiers in the order email, phone, username and
         * lanes in the order accounts, phone, breach, domain. Every lane the person's identifiers are looked
         * up in is listed: one that no source answered, or that no source could be asked in, is not_covered.
         * Empty when the person has no email, phone or handle to look up.
         */
        public Builder checks(@Nonnull List<ScreeningLookupCheck> checks) {
            this.checks = Utils.checkNotNull(checks, "checks");
            return this;
        }

        /**
         * A few typed facts per identifier: an assessment's risk.summary, and a screened person's lookup
         * summary. Every fact is optional: absent means the evidence did not answer and makes no claim, while
         * false or 0 is a definite answer.
         */
        public Builder summary(@Nullable RiskSummary summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Merged observations behind the summary, served only to a caller who may read evidence: no provider,
         * source, origin, raw breach record or score.
         */
        public Builder details(@Nullable RiskDetails details) {
            this.details = details;
            return this;
        }

        /**
         * What each source answered, whole, each answer once: every field of each account a connector found,
         * and every column of each breach row the person's email or usernames matched, under the source's own
         * names, and what each search a person started found, each match naming its search. Anyone who may
         * read the person reads them: no extra permission applies, breach passwords and hashes included.
         */
        public Builder records(@Nullable List<Record> records) {
            this.records = records;
            return this;
        }

        /**
         * For each search and source whose matched rows records leave out, how many: a breach search returns
         * at most 20 rows of a table and 100 in all, from its first 64 tables by name. A search a person
         * starts on one table of its source fetches the rest of that table, up to 100 rows. Absent when
         * records hold every matched row.
         */
        public Builder remaining(@Nullable List<RecordRemaining> remaining) {
            this.remaining = remaining;
            return this;
        }

        /**
         * When the lookups ran.
         */
        public Builder lookedAt(@Nonnull String lookedAt) {
            this.lookedAt = Utils.checkNotNull(lookedAt, "lookedAt");
            return this;
        }

        public ScreeningLookup build() {
            return new ScreeningLookup(checks, summary, details, records, remaining, lookedAt);
        }
    }
}
