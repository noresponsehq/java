package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Record
 *
 * <p>One answer from one source: where it came from, what found it and every field it holds.
 */
public class Record {
    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id.
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

    @JsonCreator
    public Record(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("source") @Nonnull RecordSource source,
            @JsonProperty("matched") @Nonnull List<RecordMatch> matched,
            @JsonProperty("fields") @Nonnull List<RecordField> fields,
            @JsonProperty("observed_at") @Nonnull String observedAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.matched =
                Optional.ofNullable(matched).orElseThrow(() -> new IllegalArgumentException("matched cannot be null"));
        this.fields =
                Optional.ofNullable(fields).orElseThrow(() -> new IllegalArgumentException("fields cannot be null"));
        this.observedAt = Optional.ofNullable(observedAt)
                .orElseThrow(() -> new IllegalArgumentException("observedAt cannot be null"));
    }

    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id.
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Made from the source and the fields' names and original values, so the same answer found twice is
     * one record with the same id.
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
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, source, matched, fields, observedAt);
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
                observedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private RecordSource source;

        private List<RecordMatch> matched;

        private List<RecordField> fields;

        private String observedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Made from the source and the fields' names and original values, so the same answer found twice is
         * one record with the same id.
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

        public Record build() {
            return new Record(id, source, matched, fields, observedAt);
        }
    }
}
