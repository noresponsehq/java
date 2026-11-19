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
 * RecordConflict
 *
 * <p>Two identifiers you gave that lead to records of different people.
 */
public class RecordConflict {
    /**
     * anchors_disagree: records found by two of the identifiers you gave clash with each other, so those
     * identifiers may belong to different people.
     */
    @JsonProperty("signal")
    private RecordConflictSignal signal;

    /**
     * The kind of value that clashes: person_name or born.
     */
    @JsonProperty("kind")
    private String kind;

    /**
     * The two identifiers you gave whose records disagree.
     */
    @JsonProperty("between")
    private List<Between> between;

    /**
     * The ids of the two records that clash, in the order of between.
     */
    @JsonProperty("records")
    private List<String> records;

    @JsonCreator
    public RecordConflict(
            @JsonProperty("signal") @Nonnull RecordConflictSignal signal,
            @JsonProperty("kind") @Nonnull String kind,
            @JsonProperty("between") @Nonnull List<Between> between,
            @JsonProperty("records") @Nonnull List<String> records) {
        this.signal =
                Optional.ofNullable(signal).orElseThrow(() -> new IllegalArgumentException("signal cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.between =
                Optional.ofNullable(between).orElseThrow(() -> new IllegalArgumentException("between cannot be null"));
        this.records =
                Optional.ofNullable(records).orElseThrow(() -> new IllegalArgumentException("records cannot be null"));
    }

    /**
     * anchors_disagree: records found by two of the identifiers you gave clash with each other, so those
     * identifiers may belong to different people.
     */
    public RecordConflictSignal signal() {
        return this.signal;
    }

    /**
     * The kind of value that clashes: person_name or born.
     */
    public String kind() {
        return this.kind;
    }

    /**
     * The two identifiers you gave whose records disagree.
     */
    public List<Between> between() {
        return this.between;
    }

    /**
     * The ids of the two records that clash, in the order of between.
     */
    public List<String> records() {
        return this.records;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * anchors_disagree: records found by two of the identifiers you gave clash with each other, so those
     * identifiers may belong to different people.
     */
    public RecordConflict withSignal(@Nonnull RecordConflictSignal signal) {
        this.signal = Utils.checkNotNull(signal, "signal");
        return this;
    }

    /**
     * The kind of value that clashes: person_name or born.
     */
    public RecordConflict withKind(@Nonnull String kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The two identifiers you gave whose records disagree.
     */
    public RecordConflict withBetween(@Nonnull List<Between> between) {
        this.between = Utils.checkNotNull(between, "between");
        return this;
    }

    /**
     * The ids of the two records that clash, in the order of between.
     */
    public RecordConflict withRecords(@Nonnull List<String> records) {
        this.records = Utils.checkNotNull(records, "records");
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
        RecordConflict other = (RecordConflict) o;
        return Utils.enhancedDeepEquals(this.signal, other.signal)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.between, other.between)
                && Utils.enhancedDeepEquals(this.records, other.records);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(signal, kind, between, records);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordConflict.class, "signal", signal, "kind", kind, "between", between, "records", records);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private RecordConflictSignal signal;

        private String kind;

        private List<Between> between;

        private List<String> records;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * anchors_disagree: records found by two of the identifiers you gave clash with each other, so those
         * identifiers may belong to different people.
         */
        public Builder signal(@Nonnull RecordConflictSignal signal) {
            this.signal = Utils.checkNotNull(signal, "signal");
            return this;
        }

        /**
         * The kind of value that clashes: person_name or born.
         */
        public Builder kind(@Nonnull String kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The two identifiers you gave whose records disagree.
         */
        public Builder between(@Nonnull List<Between> between) {
            this.between = Utils.checkNotNull(between, "between");
            return this;
        }

        /**
         * The ids of the two records that clash, in the order of between.
         */
        public Builder records(@Nonnull List<String> records) {
            this.records = Utils.checkNotNull(records, "records");
            return this;
        }

        public RecordConflict build() {
            return new RecordConflict(signal, kind, between, records);
        }
    }
}
