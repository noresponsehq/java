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
 * SearchRequest
 *
 * <p>The value to search again, named by the record and field that hold it rather than sent again.
 */
public class SearchRequest {
    /**
     * One of the person's records: a lookup's, a profile's or one an earlier search found.
     */
    @JsonProperty("record_id")
    private String recordId;

    /**
     * The name of the record's field whose value to search. Its kind must be email, phone, username,
     * person_name, password, hash or ip.
     */
    @JsonProperty("field")
    private String field;

    /**
     * Search only this breach table, as a remaining entry names it, to fetch the rows an earlier search
     * left out. Not for a phone.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("table")
    private String table;

    @JsonCreator
    public SearchRequest(
            @JsonProperty("record_id") @Nonnull String recordId,
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("table") @Nullable String table) {
        this.recordId = Optional.ofNullable(recordId)
                .orElseThrow(() -> new IllegalArgumentException("recordId cannot be null"));
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.table = table;
    }

    public SearchRequest(@Nonnull String recordId, @Nonnull String field) {
        this(recordId, field, null);
    }

    /**
     * One of the person's records: a lookup's, a profile's or one an earlier search found.
     */
    public String recordId() {
        return this.recordId;
    }

    /**
     * The name of the record's field whose value to search. Its kind must be email, phone, username,
     * person_name, password, hash or ip.
     */
    public String field() {
        return this.field;
    }

    /**
     * Search only this breach table, as a remaining entry names it, to fetch the rows an earlier search
     * left out. Not for a phone.
     */
    public Optional<String> table() {
        return Optional.ofNullable(this.table);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One of the person's records: a lookup's, a profile's or one an earlier search found.
     */
    public SearchRequest withRecordId(@Nonnull String recordId) {
        this.recordId = Utils.checkNotNull(recordId, "recordId");
        return this;
    }

    /**
     * The name of the record's field whose value to search. Its kind must be email, phone, username,
     * person_name, password, hash or ip.
     */
    public SearchRequest withField(@Nonnull String field) {
        this.field = Utils.checkNotNull(field, "field");
        return this;
    }

    /**
     * Search only this breach table, as a remaining entry names it, to fetch the rows an earlier search
     * left out. Not for a phone.
     */
    public SearchRequest withTable(@Nullable String table) {
        this.table = table;
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
        SearchRequest other = (SearchRequest) o;
        return Utils.enhancedDeepEquals(this.recordId, other.recordId)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.table, other.table);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(recordId, field, table);
    }

    @Override
    public String toString() {
        return Utils.toString(SearchRequest.class, "recordId", recordId, "field", field, "table", table);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String recordId;

        private String field;

        private String table;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One of the person's records: a lookup's, a profile's or one an earlier search found.
         */
        public Builder recordId(@Nonnull String recordId) {
            this.recordId = Utils.checkNotNull(recordId, "recordId");
            return this;
        }

        /**
         * The name of the record's field whose value to search. Its kind must be email, phone, username,
         * person_name, password, hash or ip.
         */
        public Builder field(@Nonnull String field) {
            this.field = Utils.checkNotNull(field, "field");
            return this;
        }

        /**
         * Search only this breach table, as a remaining entry names it, to fetch the rows an earlier search
         * left out. Not for a phone.
         */
        public Builder table(@Nullable String table) {
            this.table = table;
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(recordId, field, table);
        }
    }
}
