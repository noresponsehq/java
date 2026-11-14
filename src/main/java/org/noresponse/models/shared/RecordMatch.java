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
     * The kind of searched value: email, phone, username, name, or handle for a profile read of a handle.
     */
    @JsonProperty("by")
    private String by;

    /**
     * The field the searched value hit, when the record holds it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("field")
    private String field;

    @JsonCreator
    public RecordMatch(@JsonProperty("by") @Nonnull String by, @JsonProperty("field") @Nullable String field) {
        this.by = Optional.ofNullable(by).orElseThrow(() -> new IllegalArgumentException("by cannot be null"));
        this.field = field;
    }

    public RecordMatch(@Nonnull String by) {
        this(by, null);
    }

    /**
     * The kind of searched value: email, phone, username, name, or handle for a profile read of a handle.
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The kind of searched value: email, phone, username, name, or handle for a profile read of a handle.
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordMatch other = (RecordMatch) o;
        return Utils.enhancedDeepEquals(this.by, other.by) && Utils.enhancedDeepEquals(this.field, other.field);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(by, field);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordMatch.class, "by", by, "field", field);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String by;

        private String field;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The kind of searched value: email, phone, username, name, or handle for a profile read of a handle.
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

        public RecordMatch build() {
            return new RecordMatch(by, field);
        }
    }
}
