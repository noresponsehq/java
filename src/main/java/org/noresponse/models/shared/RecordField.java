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
 * RecordField
 *
 * <p>One value a source returned.
 */
public class RecordField {
    /**
     * The source's own name for the field. A nested value's name is dotted, and a list element's carries
     * its index.
     */
    @JsonProperty("field")
    private String field;

    /**
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339.
     */
    @JsonProperty("original")
    private String original;

    /**
     * What sort of value the field holds. Absent until the field's kind is known.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("kind")
    private String kind;

    /**
     * Which common field it is, where many sources share it, such as handle, display_name, account_id,
     * profile_link, picture, bio, website, linked_account (named by its network), account_status, joined,
     * last_updated, organisation, location, followers, following or posts. Absent for a field only this
     * source returns.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("role")
    private String role;

    /**
     * The cleaned value. Absent when it cannot be cleaned, or until the field's kind is known.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("value")
    private String value;

    @JsonCreator
    public RecordField(
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("original") @Nonnull String original,
            @JsonProperty("kind") @Nullable String kind,
            @JsonProperty("role") @Nullable String role,
            @JsonProperty("value") @Nullable String value) {
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.original = Optional.ofNullable(original)
                .orElseThrow(() -> new IllegalArgumentException("original cannot be null"));
        this.kind = kind;
        this.role = role;
        this.value = value;
    }

    public RecordField(@Nonnull String field, @Nonnull String original) {
        this(field, original, null, null, null);
    }

    /**
     * The source's own name for the field. A nested value's name is dotted, and a list element's carries
     * its index.
     */
    public String field() {
        return this.field;
    }

    /**
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339.
     */
    public String original() {
        return this.original;
    }

    /**
     * What sort of value the field holds. Absent until the field's kind is known.
     */
    public Optional<String> kind() {
        return Optional.ofNullable(this.kind);
    }

    /**
     * Which common field it is, where many sources share it, such as handle, display_name, account_id,
     * profile_link, picture, bio, website, linked_account (named by its network), account_status, joined,
     * last_updated, organisation, location, followers, following or posts. Absent for a field only this
     * source returns.
     */
    public Optional<String> role() {
        return Optional.ofNullable(this.role);
    }

    /**
     * The cleaned value. Absent when it cannot be cleaned, or until the field's kind is known.
     */
    public Optional<String> value() {
        return Optional.ofNullable(this.value);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The source's own name for the field. A nested value's name is dotted, and a list element's carries
     * its index.
     */
    public RecordField withField(@Nonnull String field) {
        this.field = Utils.checkNotNull(field, "field");
        return this;
    }

    /**
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339.
     */
    public RecordField withOriginal(@Nonnull String original) {
        this.original = Utils.checkNotNull(original, "original");
        return this;
    }

    /**
     * What sort of value the field holds. Absent until the field's kind is known.
     */
    public RecordField withKind(@Nullable String kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Which common field it is, where many sources share it, such as handle, display_name, account_id,
     * profile_link, picture, bio, website, linked_account (named by its network), account_status, joined,
     * last_updated, organisation, location, followers, following or posts. Absent for a field only this
     * source returns.
     */
    public RecordField withRole(@Nullable String role) {
        this.role = role;
        return this;
    }

    /**
     * The cleaned value. Absent when it cannot be cleaned, or until the field's kind is known.
     */
    public RecordField withValue(@Nullable String value) {
        this.value = value;
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
        RecordField other = (RecordField) o;
        return Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.original, other.original)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(field, original, kind, role, value);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordField.class, "field", field, "original", original, "kind", kind, "role", role, "value", value);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String field;

        private String original;

        private String kind;

        private String role;

        private String value;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The source's own name for the field. A nested value's name is dotted, and a list element's carries
         * its index.
         */
        public Builder field(@Nonnull String field) {
            this.field = Utils.checkNotNull(field, "field");
            return this;
        }

        /**
         * The value as the source wrote it. A count is written in decimal and a time in RFC 3339.
         */
        public Builder original(@Nonnull String original) {
            this.original = Utils.checkNotNull(original, "original");
            return this;
        }

        /**
         * What sort of value the field holds. Absent until the field's kind is known.
         */
        public Builder kind(@Nullable String kind) {
            this.kind = kind;
            return this;
        }

        /**
         * Which common field it is, where many sources share it, such as handle, display_name, account_id,
         * profile_link, picture, bio, website, linked_account (named by its network), account_status, joined,
         * last_updated, organisation, location, followers, following or posts. Absent for a field only this
         * source returns.
         */
        public Builder role(@Nullable String role) {
            this.role = role;
            return this;
        }

        /**
         * The cleaned value. Absent when it cannot be cleaned, or until the field's kind is known.
         */
        public Builder value(@Nullable String value) {
            this.value = value;
            return this;
        }

        public RecordField build() {
            return new RecordField(field, original, kind, role, value);
        }
    }
}
