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
     * The search a person started that matched the rows. Searching the same field again with this source's
     * table fetches them.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("search")
    private String search;

    /**
     * How many rows of the source that search matched beyond the records the lookup carries.
     */
    @JsonProperty("rows")
    private long rows;

    @JsonCreator
    public RecordRemaining(
            @JsonProperty("source") @Nonnull RecordSource source,
            @JsonProperty("by") @Nonnull String by,
            @JsonProperty("search") @Nullable String search,
            @JsonProperty("rows") long rows) {
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.by = Optional.ofNullable(by).orElseThrow(() -> new IllegalArgumentException("by cannot be null"));
        this.search = search;
        this.rows = rows;
    }

    public RecordRemaining(@Nonnull RecordSource source, @Nonnull String by, long rows) {
        this(source, by, null, rows);
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
     * The search a person started that matched the rows. Searching the same field again with this source's
     * table fetches them.
     */
    public Optional<String> search() {
        return Optional.ofNullable(this.search);
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
     * The search a person started that matched the rows. Searching the same field again with this source's
     * table fetches them.
     */
    public RecordRemaining withSearch(@Nullable String search) {
        this.search = search;
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
                && Utils.enhancedDeepEquals(this.search, other.search)
                && Utils.enhancedDeepEquals(this.rows, other.rows);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(source, by, search, rows);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordRemaining.class, "source", source, "by", by, "search", search, "rows", rows);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private RecordSource source;

        private String by;

        private String search;

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
         * The search a person started that matched the rows. Searching the same field again with this source's
         * table fetches them.
         */
        public Builder search(@Nullable String search) {
            this.search = search;
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
            return new RecordRemaining(source, by, search, rows);
        }
    }
}
