package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Record
 *
 * <p>One answer from one source: where it came from, what found it and every field it holds. A person's
 * lookup records also say how likely each is the person.
 */
public class Record {
    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id. Its fields are typed as its latest finding typed them.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Where a record came from.
     */
    @JsonProperty("source")
    private RecordSource source;

    /**
     * Each kind of searched value that found the record.
     */
    @JsonProperty("matched")
    private List<RecordMatch> matched;

    /**
     * Every field the source returned, in the order the source's connector lists them; a breach row's
     * columns in name order. Nothing is withheld: a breach row's passwords, hashes and salts are fields
     * like any other.
     */
    @JsonProperty("fields")
    private List<RecordField> fields;

    /**
     * When the source last gave this answer.
     */
    @JsonProperty("observed_at")
    private String observedAt;

    /**
     * How likely the record is the person it was found for, from 0.01 to 0.99, to two places. A record
     * found by the person's own email or phone starts as theirs, and one found by a search of a value
     * another record holds starts at that record's likelihood divided by one plus the records its search
     * found on its site; any other starts lower the more people its search found on its site, or anywhere
     * for a name search. Each value it shares with what is known of the person raises it, a rare value
     * more than a common one, and a different birth date or full name lowers it.
     *
     * <p>What is known is what the person's row gives and the values of their joined records: those found by
     * their email or phone, those a reviewer joined and those whose likelihood reaches 0.9. Each record
     * that joins adds its values and the rest are scored again until the joined records stop changing; a
     * record whose likelihood falls below 0.9 is not joined and adds nothing, so a reviewer's rejection
     * also undoes the joins that came through the rejected record. A record whose source kind starts with
     * domain_ describes an email's domain, not the person, so it is not compared and adds nothing to what
     * is known: it stays at 0.99 when the person's own email found it and at 0.5 otherwise.
     *
     * <p>Present on a person's lookup records, with agreed, clashed and joined.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("likelihood")
    private Double likelihood;

    /**
     * The fields of this record whose values agreed with what is known of the person, by their field
     * names, in the record's order. The value that found the record is not among them.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("agreed")
    private List<String> agreed;

    /**
     * The fields of this record that clashed with what is known of the person: a birth date or a full name
     * that differs from the person's.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("clashed")
    private List<String> clashed;

    /**
     * Whether the record joins the person: its likelihood reached 0.9 or a reviewer joined it, and no
     * reviewer rejected it. A record that does not join stays a lead.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("joined")
    private Boolean joined;

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("verdict")
    private Verdict verdict;

    /**
     * Why the record has its likelihood, as the items that make it up: what found it and each value it
     * agrees or clashes with, each with its weight in bits. The bits add up to the likelihood before
     * rounding: likelihood = 1 / (1 + 2^-sum). Only the strongest agreement of each kind counts, and a
     * place counts once however many of its city, region, country and address agree.
     *
     * <p>Present on a person's lookup records that have any such item.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("evidence")
    private List<RecordEvidence> evidence;

    /**
     * How the record connects to what the investigator gave: given first, then the ids of the joined
     * records it reached the person through, nearest last. Each step follows what found the record before
     * the strongest agreement. A record with no link to the person has no path.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("path")
    private List<String> path;

    @JsonCreator
    public Record(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("source") @Nonnull RecordSource source,
            @JsonProperty("matched") @Nonnull List<RecordMatch> matched,
            @JsonProperty("fields") @Nonnull List<RecordField> fields,
            @JsonProperty("observed_at") @Nonnull String observedAt,
            @JsonProperty("likelihood") @Nullable Double likelihood,
            @JsonProperty("agreed") @Nullable List<String> agreed,
            @JsonProperty("clashed") @Nullable List<String> clashed,
            @JsonProperty("joined") @Nullable Boolean joined,
            @JsonProperty("verdict") @Nullable Verdict verdict,
            @JsonProperty("evidence") @Nullable List<RecordEvidence> evidence,
            @JsonProperty("path") @Nullable List<String> path) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.matched =
                Optional.ofNullable(matched).orElseThrow(() -> new IllegalArgumentException("matched cannot be null"));
        this.fields =
                Optional.ofNullable(fields).orElseThrow(() -> new IllegalArgumentException("fields cannot be null"));
        this.observedAt = Optional.ofNullable(observedAt)
                .orElseThrow(() -> new IllegalArgumentException("observedAt cannot be null"));
        this.likelihood = likelihood;
        this.agreed = agreed;
        this.clashed = clashed;
        this.joined = joined;
        this.verdict = verdict;
        this.evidence = evidence;
        this.path = path;
    }

    public Record(
            @Nonnull String id,
            @Nonnull RecordSource source,
            @Nonnull List<RecordMatch> matched,
            @Nonnull List<RecordField> fields,
            @Nonnull String observedAt) {
        this(id, source, matched, fields, observedAt, null, null, null, null, null, null, null);
    }

    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id. Its fields are typed as its latest finding typed them.
     */
    public String id() {
        return this.id;
    }

    /**
     * Where a record came from.
     */
    public RecordSource source() {
        return this.source;
    }

    /**
     * Each kind of searched value that found the record.
     */
    public List<RecordMatch> matched() {
        return this.matched;
    }

    /**
     * Every field the source returned, in the order the source's connector lists them; a breach row's
     * columns in name order. Nothing is withheld: a breach row's passwords, hashes and salts are fields
     * like any other.
     */
    public List<RecordField> fields() {
        return this.fields;
    }

    /**
     * When the source last gave this answer.
     */
    public String observedAt() {
        return this.observedAt;
    }

    /**
     * How likely the record is the person it was found for, from 0.01 to 0.99, to two places. A record
     * found by the person's own email or phone starts as theirs, and one found by a search of a value
     * another record holds starts at that record's likelihood divided by one plus the records its search
     * found on its site; any other starts lower the more people its search found on its site, or anywhere
     * for a name search. Each value it shares with what is known of the person raises it, a rare value
     * more than a common one, and a different birth date or full name lowers it.
     *
     * <p>What is known is what the person's row gives and the values of their joined records: those found by
     * their email or phone, those a reviewer joined and those whose likelihood reaches 0.9. Each record
     * that joins adds its values and the rest are scored again until the joined records stop changing; a
     * record whose likelihood falls below 0.9 is not joined and adds nothing, so a reviewer's rejection
     * also undoes the joins that came through the rejected record. A record whose source kind starts with
     * domain_ describes an email's domain, not the person, so it is not compared and adds nothing to what
     * is known: it stays at 0.99 when the person's own email found it and at 0.5 otherwise.
     *
     * <p>Present on a person's lookup records, with agreed, clashed and joined.
     */
    public Optional<Double> likelihood() {
        return Optional.ofNullable(this.likelihood);
    }

    /**
     * The fields of this record whose values agreed with what is known of the person, by their field
     * names, in the record's order. The value that found the record is not among them.
     */
    public Optional<List<String>> agreed() {
        return Optional.ofNullable(this.agreed);
    }

    /**
     * The fields of this record that clashed with what is known of the person: a birth date or a full name
     * that differs from the person's.
     */
    public Optional<List<String>> clashed() {
        return Optional.ofNullable(this.clashed);
    }

    /**
     * Whether the record joins the person: its likelihood reached 0.9 or a reviewer joined it, and no
     * reviewer rejected it. A record that does not join stays a lead.
     */
    public Optional<Boolean> joined() {
        return Optional.ofNullable(this.joined);
    }

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    public Optional<Verdict> verdict() {
        return Optional.ofNullable(this.verdict);
    }

    /**
     * Why the record has its likelihood, as the items that make it up: what found it and each value it
     * agrees or clashes with, each with its weight in bits. The bits add up to the likelihood before
     * rounding: likelihood = 1 / (1 + 2^-sum). Only the strongest agreement of each kind counts, and a
     * place counts once however many of its city, region, country and address agree.
     *
     * <p>Present on a person's lookup records that have any such item.
     */
    public Optional<List<RecordEvidence>> evidence() {
        return Optional.ofNullable(this.evidence);
    }

    /**
     * How the record connects to what the investigator gave: given first, then the ids of the joined
     * records it reached the person through, nearest last. Each step follows what found the record before
     * the strongest agreement. A record with no link to the person has no path.
     */
    public Optional<List<String>> path() {
        return Optional.ofNullable(this.path);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id. Its fields are typed as its latest finding typed them.
     */
    public Record withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Where a record came from.
     */
    public Record withSource(@Nonnull RecordSource source) {
        this.source = Utils.checkNotNull(source, "source");
        return this;
    }

    /**
     * Each kind of searched value that found the record.
     */
    public Record withMatched(@Nonnull List<RecordMatch> matched) {
        this.matched = Utils.checkNotNull(matched, "matched");
        return this;
    }

    /**
     * Every field the source returned, in the order the source's connector lists them; a breach row's
     * columns in name order. Nothing is withheld: a breach row's passwords, hashes and salts are fields
     * like any other.
     */
    public Record withFields(@Nonnull List<RecordField> fields) {
        this.fields = Utils.checkNotNull(fields, "fields");
        return this;
    }

    /**
     * When the source last gave this answer.
     */
    public Record withObservedAt(@Nonnull String observedAt) {
        this.observedAt = Utils.checkNotNull(observedAt, "observedAt");
        return this;
    }

    /**
     * How likely the record is the person it was found for, from 0.01 to 0.99, to two places. A record
     * found by the person's own email or phone starts as theirs, and one found by a search of a value
     * another record holds starts at that record's likelihood divided by one plus the records its search
     * found on its site; any other starts lower the more people its search found on its site, or anywhere
     * for a name search. Each value it shares with what is known of the person raises it, a rare value
     * more than a common one, and a different birth date or full name lowers it.
     *
     * <p>What is known is what the person's row gives and the values of their joined records: those found by
     * their email or phone, those a reviewer joined and those whose likelihood reaches 0.9. Each record
     * that joins adds its values and the rest are scored again until the joined records stop changing; a
     * record whose likelihood falls below 0.9 is not joined and adds nothing, so a reviewer's rejection
     * also undoes the joins that came through the rejected record. A record whose source kind starts with
     * domain_ describes an email's domain, not the person, so it is not compared and adds nothing to what
     * is known: it stays at 0.99 when the person's own email found it and at 0.5 otherwise.
     *
     * <p>Present on a person's lookup records, with agreed, clashed and joined.
     */
    public Record withLikelihood(@Nullable Double likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    /**
     * The fields of this record whose values agreed with what is known of the person, by their field
     * names, in the record's order. The value that found the record is not among them.
     */
    public Record withAgreed(@Nullable List<String> agreed) {
        this.agreed = agreed;
        return this;
    }

    /**
     * The fields of this record that clashed with what is known of the person: a birth date or a full name
     * that differs from the person's.
     */
    public Record withClashed(@Nullable List<String> clashed) {
        this.clashed = clashed;
        return this;
    }

    /**
     * Whether the record joins the person: its likelihood reached 0.9 or a reviewer joined it, and no
     * reviewer rejected it. A record that does not join stays a lead.
     */
    public Record withJoined(@Nullable Boolean joined) {
        this.joined = joined;
        return this;
    }

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    public Record withVerdict(@Nullable Verdict verdict) {
        this.verdict = verdict;
        return this;
    }

    /**
     * Why the record has its likelihood, as the items that make it up: what found it and each value it
     * agrees or clashes with, each with its weight in bits. The bits add up to the likelihood before
     * rounding: likelihood = 1 / (1 + 2^-sum). Only the strongest agreement of each kind counts, and a
     * place counts once however many of its city, region, country and address agree.
     *
     * <p>Present on a person's lookup records that have any such item.
     */
    public Record withEvidence(@Nullable List<RecordEvidence> evidence) {
        this.evidence = evidence;
        return this;
    }

    /**
     * How the record connects to what the investigator gave: given first, then the ids of the joined
     * records it reached the person through, nearest last. Each step follows what found the record before
     * the strongest agreement. A record with no link to the person has no path.
     */
    public Record withPath(@Nullable List<String> path) {
        this.path = path;
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
        Record other = (Record) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.source, other.source)
                && Utils.enhancedDeepEquals(this.matched, other.matched)
                && Utils.enhancedDeepEquals(this.fields, other.fields)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.likelihood, other.likelihood)
                && Utils.enhancedDeepEquals(this.agreed, other.agreed)
                && Utils.enhancedDeepEquals(this.clashed, other.clashed)
                && Utils.enhancedDeepEquals(this.joined, other.joined)
                && Utils.enhancedDeepEquals(this.verdict, other.verdict)
                && Utils.enhancedDeepEquals(this.evidence, other.evidence)
                && Utils.enhancedDeepEquals(this.path, other.path);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, source, matched, fields, observedAt, likelihood, agreed, clashed, joined, verdict, evidence, path);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Record.class,
                "id",
                id,
                "source",
                source,
                "matched",
                matched,
                "fields",
                fields,
                "observedAt",
                observedAt,
                "likelihood",
                likelihood,
                "agreed",
                agreed,
                "clashed",
                clashed,
                "joined",
                joined,
                "verdict",
                verdict,
                "evidence",
                evidence,
                "path",
                path);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private RecordSource source;

        private List<RecordMatch> matched;

        private List<RecordField> fields;

        private String observedAt;

        private Double likelihood;

        private List<String> agreed;

        private List<String> clashed;

        private Boolean joined;

        private Verdict verdict;

        private List<RecordEvidence> evidence;

        private List<String> path;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Made from the source and the fields' names and original values, so the same answer found twice is
         * one record with the same id. Its fields are typed as its latest finding typed them.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * Where a record came from.
         */
        public Builder source(@Nonnull RecordSource source) {
            this.source = Utils.checkNotNull(source, "source");
            return this;
        }

        /**
         * Each kind of searched value that found the record.
         */
        public Builder matched(@Nonnull List<RecordMatch> matched) {
            this.matched = Utils.checkNotNull(matched, "matched");
            return this;
        }

        /**
         * Every field the source returned, in the order the source's connector lists them; a breach row's
         * columns in name order. Nothing is withheld: a breach row's passwords, hashes and salts are fields
         * like any other.
         */
        public Builder fields(@Nonnull List<RecordField> fields) {
            this.fields = Utils.checkNotNull(fields, "fields");
            return this;
        }

        /**
         * When the source last gave this answer.
         */
        public Builder observedAt(@Nonnull String observedAt) {
            this.observedAt = Utils.checkNotNull(observedAt, "observedAt");
            return this;
        }

        /**
         * How likely the record is the person it was found for, from 0.01 to 0.99, to two places. A record
         * found by the person's own email or phone starts as theirs, and one found by a search of a value
         * another record holds starts at that record's likelihood divided by one plus the records its search
         * found on its site; any other starts lower the more people its search found on its site, or anywhere
         * for a name search. Each value it shares with what is known of the person raises it, a rare value
         * more than a common one, and a different birth date or full name lowers it.
         *
         * <p>What is known is what the person's row gives and the values of their joined records: those found by
         * their email or phone, those a reviewer joined and those whose likelihood reaches 0.9. Each record
         * that joins adds its values and the rest are scored again until the joined records stop changing; a
         * record whose likelihood falls below 0.9 is not joined and adds nothing, so a reviewer's rejection
         * also undoes the joins that came through the rejected record. A record whose source kind starts with
         * domain_ describes an email's domain, not the person, so it is not compared and adds nothing to what
         * is known: it stays at 0.99 when the person's own email found it and at 0.5 otherwise.
         *
         * <p>Present on a person's lookup records, with agreed, clashed and joined.
         */
        public Builder likelihood(@Nullable Double likelihood) {
            this.likelihood = likelihood;
            return this;
        }

        /**
         * The fields of this record whose values agreed with what is known of the person, by their field
         * names, in the record's order. The value that found the record is not among them.
         */
        public Builder agreed(@Nullable List<String> agreed) {
            this.agreed = agreed;
            return this;
        }

        /**
         * The fields of this record that clashed with what is known of the person: a birth date or a full name
         * that differs from the person's.
         */
        public Builder clashed(@Nullable List<String> clashed) {
            this.clashed = clashed;
            return this;
        }

        /**
         * Whether the record joins the person: its likelihood reached 0.9 or a reviewer joined it, and no
         * reviewer rejected it. A record that does not join stays a lead.
         */
        public Builder joined(@Nullable Boolean joined) {
            this.joined = joined;
            return this;
        }

        /**
         * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
         * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
         * it.
         */
        public Builder verdict(@Nullable Verdict verdict) {
            this.verdict = verdict;
            return this;
        }

        /**
         * Why the record has its likelihood, as the items that make it up: what found it and each value it
         * agrees or clashes with, each with its weight in bits. The bits add up to the likelihood before
         * rounding: likelihood = 1 / (1 + 2^-sum). Only the strongest agreement of each kind counts, and a
         * place counts once however many of its city, region, country and address agree.
         *
         * <p>Present on a person's lookup records that have any such item.
         */
        public Builder evidence(@Nullable List<RecordEvidence> evidence) {
            this.evidence = evidence;
            return this;
        }

        /**
         * How the record connects to what the investigator gave: given first, then the ids of the joined
         * records it reached the person through, nearest last. Each step follows what found the record before
         * the strongest agreement. A record with no link to the person has no path.
         */
        public Builder path(@Nullable List<String> path) {
            this.path = path;
            return this;
        }

        public Record build() {
            return new Record(
                    id,
                    source,
                    matched,
                    fields,
                    observedAt,
                    likelihood,
                    agreed,
                    clashed,
                    joined,
                    verdict,
                    evidence,
                    path);
        }
    }
}
