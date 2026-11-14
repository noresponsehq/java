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
 * RecordMatch
 *
 * <p>One searched value that found the record.
 */
public class RecordMatch {
    /**
     * The kind of searched value: email, phone, username, ip, name, password, hash, or handle for a
     * profile read of a handle.
     */
    @JsonProperty("by")
    private String by;

    /**
     * The field the searched value hit, when the record holds it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("field")
    private String field;

    /**
     * The search a person started on a value of another record that found this one, among the person
     * report's searches, which names the value and the record and field it came from. A record found only
     * by such searches is a lead, whatever kind of value found it. Absent for a match of the lookups.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("search")
    private String search;

    @JsonCreator
    public RecordMatch(
            @JsonProperty("by") @Nonnull String by,
            @JsonProperty("field") @Nullable String field,
            @JsonProperty("search") @Nullable String search) {
        this.by = Optional.ofNullable(by).orElseThrow(() -> new IllegalArgumentException("by cannot be null"));
        this.field = field;
        this.search = search;
    }

    public RecordMatch(@Nonnull String by) {
        this(by, null, null);
    }

    /**
     * The kind of searched value: email, phone, username, ip, name, password, hash, or handle for a
     * profile read of a handle.
     */
    public String by() {
        return this.by;
    }

    /**
     * The field the searched value hit, when the record holds it.
     */
    public Optional<String> field() {
        return Optional.ofNullable(this.field);
    }

    /**
     * The search a person started on a value of another record that found this one, among the person
     * report's searches, which names the value and the record and field it came from. A record found only
     * by such searches is a lead, whatever kind of value found it. Absent for a match of the lookups.
     */
    public Optional<String> search() {
        return Optional.ofNullable(this.search);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The kind of searched value: email, phone, username, ip, name, password, hash, or handle for a
     * profile read of a handle.
     */
    public RecordMatch withBy(@Nonnull String by) {
        this.by = Utils.checkNotNull(by, "by");
        return this;
    }

    /**
     * The field the searched value hit, when the record holds it.
     */
    public RecordMatch withField(@Nullable String field) {
        this.field = field;
        return this;
    }

    /**
     * The search a person started on a value of another record that found this one, among the person
     * report's searches, which names the value and the record and field it came from. A record found only
     * by such searches is a lead, whatever kind of value found it. Absent for a match of the lookups.
     */
    public RecordMatch withSearch(@Nullable String search) {
        this.search = search;
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
        RecordMatch other = (RecordMatch) o;
        return Utils.enhancedDeepEquals(this.by, other.by)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.search, other.search);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(by, field, search);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordMatch.class, "by", by, "field", field, "search", search);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String by;

        private String field;

        private String search;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The kind of searched value: email, phone, username, ip, name, password, hash, or handle for a
         * profile read of a handle.
         */
        public Builder by(@Nonnull String by) {
            this.by = Utils.checkNotNull(by, "by");
            return this;
        }

        /**
         * The field the searched value hit, when the record holds it.
         */
        public Builder field(@Nullable String field) {
            this.field = field;
            return this;
        }

        /**
         * The search a person started on a value of another record that found this one, among the person
         * report's searches, which names the value and the record and field it came from. A record found only
         * by such searches is a lead, whatever kind of value found it. Absent for a match of the lookups.
         */
        public Builder search(@Nullable String search) {
            this.search = search;
            return this;
        }

        public RecordMatch build() {
            return new RecordMatch(by, field, search);
        }
    }
}
