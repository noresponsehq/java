package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RecordRemaining
 *
 * <p>Rows one search matched in one source that the lookup's records leave out, so no answer is cut short
 * in silence.
 */
public class RecordRemaining {
    /**
     * Where a record came from.
     */
    @JsonProperty("source")
    private RecordSource source;

    /**
     * The kind of searched value whose search matched the rows, as a record's matched names it.
     */
    @JsonProperty("by")
    private String by;

    /**
     * How many rows of the source that search matched beyond the records the lookup carries.
     */
    @JsonProperty("rows")
    private long rows;

    @JsonCreator
    public RecordRemaining(
            @JsonProperty("source") @Nonnull RecordSource source,
            @JsonProperty("by") @Nonnull String by,
            @JsonProperty("rows") long rows) {
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.by = Optional.ofNullable(by).orElseThrow(() -> new IllegalArgumentException("by cannot be null"));
        this.rows = rows;
    }

    /**
     * Where a record came from.
     */
    public RecordSource source() {
        return this.source;
    }

    /**
     * The kind of searched value whose search matched the rows, as a record's matched names it.
     */
    public String by() {
        return this.by;
    }

    /**
     * How many rows of the source that search matched beyond the records the lookup carries.
     */
    public long rows() {
        return this.rows;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Where a record came from.
     */
    public RecordRemaining withSource(@Nonnull RecordSource source) {
        this.source = Utils.checkNotNull(source, "source");
        return this;
    }

    /**
     * The kind of searched value whose search matched the rows, as a record's matched names it.
     */
    public RecordRemaining withBy(@Nonnull String by) {
        this.by = Utils.checkNotNull(by, "by");
        return this;
    }

    /**
     * How many rows of the source that search matched beyond the records the lookup carries.
     */
    public RecordRemaining withRows(long rows) {
        this.rows = rows;
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
        RecordRemaining other = (RecordRemaining) o;
        return Utils.enhancedDeepEquals(this.source, other.source)
                && Utils.enhancedDeepEquals(this.by, other.by)
                && Utils.enhancedDeepEquals(this.rows, other.rows);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(source, by, rows);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordRemaining.class, "source", source, "by", by, "rows", rows);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private RecordSource source;

        private String by;

        private long rows;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Where a record came from.
         */
        public Builder source(@Nonnull RecordSource source) {
            this.source = Utils.checkNotNull(source, "source");
            return this;
        }

        /**
         * The kind of searched value whose search matched the rows, as a record's matched names it.
         */
        public Builder by(@Nonnull String by) {
            this.by = Utils.checkNotNull(by, "by");
            return this;
        }

        /**
         * How many rows of the source that search matched beyond the records the lookup carries.
         */
        public Builder rows(long rows) {
            this.rows = rows;
            return this;
        }

        public RecordRemaining build() {
            return new RecordRemaining(source, by, rows);
        }
    }
}
