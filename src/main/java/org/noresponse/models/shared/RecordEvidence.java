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
 * RecordEvidence
 *
 * <p>One item of evidence behind a record's likelihood.
 */
public class RecordEvidence {
    /**
     * found_by: what found the record, which sets where its likelihood starts. agrees: a value the record
     * shares with what the investigator gave or with a joined record. clashes: a birth date, full name,
     * gender or country that differs from theirs.
     */
    @JsonProperty("signal")
    private RecordEvidenceSignal signal;

    /**
     * For found_by, what the search looked up (email, phone, username, handle, name). Otherwise the kind
     * of value compared, such as email, username, person_name, born, city or crypto_address.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("kind")
    private String kind;

    /**
     * The record's own field that agreed or clashed, or for found_by the field the search matched.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("field")
    private String field;

    /**
     * given for what the investigator gave, or the id of the person's record it agreed or clashed with, or
     * for found_by the record whose value the search looked up.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("with")
    private String with;

    /**
     * The field on the other side: the field of the given identity or of the record named by with.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("with_field")
    private String withField;

    /**
     * The item's weight in bits, to two places: log2 of how much likelier the evidence is for the same
     * person than for a stranger. Positive raises the likelihood and negative lowers it.
     */
    @JsonProperty("bits")
    private double bits;

    @JsonCreator
    public RecordEvidence(
            @JsonProperty("signal") @Nonnull RecordEvidenceSignal signal,
            @JsonProperty("kind") @Nullable String kind,
            @JsonProperty("field") @Nullable String field,
            @JsonProperty("with") @Nullable String with,
            @JsonProperty("with_field") @Nullable String withField,
            @JsonProperty("bits") double bits) {
        this.signal =
                Optional.ofNullable(signal).orElseThrow(() -> new IllegalArgumentException("signal cannot be null"));
        this.kind = kind;
        this.field = field;
        this.with = with;
        this.withField = withField;
        this.bits = bits;
    }

    public RecordEvidence(@Nonnull RecordEvidenceSignal signal, double bits) {
        this(signal, null, null, null, null, bits);
    }

    /**
     * found_by: what found the record, which sets where its likelihood starts. agrees: a value the record
     * shares with what the investigator gave or with a joined record. clashes: a birth date, full name,
     * gender or country that differs from theirs.
     */
    public RecordEvidenceSignal signal() {
        return this.signal;
    }

    /**
     * For found_by, what the search looked up (email, phone, username, handle, name). Otherwise the kind
     * of value compared, such as email, username, person_name, born, city or crypto_address.
     */
    public Optional<String> kind() {
        return Optional.ofNullable(this.kind);
    }

    /**
     * The record's own field that agreed or clashed, or for found_by the field the search matched.
     */
    public Optional<String> field() {
        return Optional.ofNullable(this.field);
    }

    /**
     * given for what the investigator gave, or the id of the person's record it agreed or clashed with, or
     * for found_by the record whose value the search looked up.
     */
    public Optional<String> with() {
        return Optional.ofNullable(this.with);
    }

    /**
     * The field on the other side: the field of the given identity or of the record named by with.
     */
    public Optional<String> withField() {
        return Optional.ofNullable(this.withField);
    }

    /**
     * The item's weight in bits, to two places: log2 of how much likelier the evidence is for the same
     * person than for a stranger. Positive raises the likelihood and negative lowers it.
     */
    public double bits() {
        return this.bits;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * found_by: what found the record, which sets where its likelihood starts. agrees: a value the record
     * shares with what the investigator gave or with a joined record. clashes: a birth date, full name,
     * gender or country that differs from theirs.
     */
    public RecordEvidence withSignal(@Nonnull RecordEvidenceSignal signal) {
        this.signal = Utils.checkNotNull(signal, "signal");
        return this;
    }

    /**
     * For found_by, what the search looked up (email, phone, username, handle, name). Otherwise the kind
     * of value compared, such as email, username, person_name, born, city or crypto_address.
     */
    public RecordEvidence withKind(@Nullable String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * The record's own field that agreed or clashed, or for found_by the field the search matched.
     */
    public RecordEvidence withField(@Nullable String field) {
        this.field = field;
        return this;
    }

    /**
     * given for what the investigator gave, or the id of the person's record it agreed or clashed with, or
     * for found_by the record whose value the search looked up.
     */
    public RecordEvidence withWith(@Nullable String with) {
        this.with = with;
        return this;
    }

    /**
     * The field on the other side: the field of the given identity or of the record named by with.
     */
    public RecordEvidence withWithField(@Nullable String withField) {
        this.withField = withField;
        return this;
    }

    /**
     * The item's weight in bits, to two places: log2 of how much likelier the evidence is for the same
     * person than for a stranger. Positive raises the likelihood and negative lowers it.
     */
    public RecordEvidence withBits(double bits) {
        this.bits = bits;
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
        RecordEvidence other = (RecordEvidence) o;
        return Utils.enhancedDeepEquals(this.signal, other.signal)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.with, other.with)
                && Utils.enhancedDeepEquals(this.withField, other.withField)
                && Utils.enhancedDeepEquals(this.bits, other.bits);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(signal, kind, field, with, withField, bits);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordEvidence.class,
                "signal",
                signal,
                "kind",
                kind,
                "field",
                field,
                "with",
                with,
                "withField",
                withField,
                "bits",
                bits);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private RecordEvidenceSignal signal;

        private String kind;

        private String field;

        private String with;

        private String withField;

        private double bits;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * found_by: what found the record, which sets where its likelihood starts. agrees: a value the record
         * shares with what the investigator gave or with a joined record. clashes: a birth date, full name,
         * gender or country that differs from theirs.
         */
        public Builder signal(@Nonnull RecordEvidenceSignal signal) {
            this.signal = Utils.checkNotNull(signal, "signal");
            return this;
        }

        /**
         * For found_by, what the search looked up (email, phone, username, handle, name). Otherwise the kind
         * of value compared, such as email, username, person_name, born, city or crypto_address.
         */
        public Builder kind(@Nullable String kind) {
            this.kind = kind;
            return this;
        }

        /**
         * The record's own field that agreed or clashed, or for found_by the field the search matched.
         */
        public Builder field(@Nullable String field) {
            this.field = field;
            return this;
        }

        /**
         * given for what the investigator gave, or the id of the person's record it agreed or clashed with, or
         * for found_by the record whose value the search looked up.
         */
        public Builder with(@Nullable String with) {
            this.with = with;
            return this;
        }

        /**
         * The field on the other side: the field of the given identity or of the record named by with.
         */
        public Builder withField(@Nullable String withField) {
            this.withField = withField;
            return this;
        }

        /**
         * The item's weight in bits, to two places: log2 of how much likelier the evidence is for the same
         * person than for a stranger. Positive raises the likelihood and negative lowers it.
         */
        public Builder bits(double bits) {
            this.bits = bits;
            return this;
        }

        public RecordEvidence build() {
            return new RecordEvidence(signal, kind, field, with, withField, bits);
        }
    }
}
