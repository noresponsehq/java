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
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339; a breach
     * column's value is as the leak holds it.
     */
    @JsonProperty("original")
    private String original;

    /**
     * What sort of value a field holds, which decides how it is cleaned, compared and searched again. It
     * comes from the value itself where its shape is certain (an email, an IP, a URL, an international
     * phone number), else from the answer stored for the source's column, else text. A value of any kind
     * is returned.
     *
     * <p>Each kind's description says what it holds and the form value takes.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("kind")
    private RecordKind kind;

    /**
     * Which common field a value is, where many sources share it: a join date and a birth date are both
     * dates, with the roles joined and born. A platform account's field and a breach row's column with the
     * same meaning carry the same kind and role. A linked_account field is named by its network.
     *
     * <p>Absent for a field only its source returns. Each role's description names the kinds its values have.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("role")
    private RecordRole role;

    /**
     * The value cleaned to its kind's one form: an email in lower case, a phone number in international
     * form when its country is known, a date in ISO form, a country as its two-letter code, a flag as true
     * or false. Absent when the value cannot be cleaned as its kind; original still holds it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("value")
    private String value;

    /**
     * What the value shows of itself beyond its kind: the likely algorithm of a hash, the likely coin of a
     * crypto address or the currency of an amount.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("detail")
    private String detail;

    /**
     * How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
     * Absent for other values, and when its rating could not be worked out, such as when a count failed.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("distinctive")
    private RecordDistinctive distinctive;

    @JsonCreator
    public RecordField(
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("original") @Nonnull String original,
            @JsonProperty("kind") @Nullable RecordKind kind,
            @JsonProperty("role") @Nullable RecordRole role,
            @JsonProperty("value") @Nullable String value,
            @JsonProperty("detail") @Nullable String detail,
            @JsonProperty("distinctive") @Nullable RecordDistinctive distinctive) {
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.original = Optional.ofNullable(original)
                .orElseThrow(() -> new IllegalArgumentException("original cannot be null"));
        this.kind = kind;
        this.role = role;
        this.value = value;
        this.detail = detail;
        this.distinctive = distinctive;
    }

    public RecordField(@Nonnull String field, @Nonnull String original) {
        this(field, original, null, null, null, null, null);
    }

    /**
     * The source's own name for the field. A nested value's name is dotted, and a list element's carries
     * its index.
     */
    public String field() {
        return this.field;
    }

    /**
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339; a breach
     * column's value is as the leak holds it.
     */
    public String original() {
        return this.original;
    }

    /**
     * What sort of value a field holds, which decides how it is cleaned, compared and searched again. It
     * comes from the value itself where its shape is certain (an email, an IP, a URL, an international
     * phone number), else from the answer stored for the source's column, else text. A value of any kind
     * is returned.
     *
     * <p>Each kind's description says what it holds and the form value takes.
     */
    public Optional<RecordKind> kind() {
        return Optional.ofNullable(this.kind);
    }

    /**
     * Which common field a value is, where many sources share it: a join date and a birth date are both
     * dates, with the roles joined and born. A platform account's field and a breach row's column with the
     * same meaning carry the same kind and role. A linked_account field is named by its network.
     *
     * <p>Absent for a field only its source returns. Each role's description names the kinds its values have.
     */
    public Optional<RecordRole> role() {
        return Optional.ofNullable(this.role);
    }

    /**
     * The value cleaned to its kind's one form: an email in lower case, a phone number in international
     * form when its country is known, a date in ISO form, a country as its two-letter code, a flag as true
     * or false. Absent when the value cannot be cleaned as its kind; original still holds it.
     */
    public Optional<String> value() {
        return Optional.ofNullable(this.value);
    }

    /**
     * What the value shows of itself beyond its kind: the likely algorithm of a hash, the likely coin of a
     * crypto address or the currency of an amount.
     */
    public Optional<String> detail() {
        return Optional.ofNullable(this.detail);
    }

    /**
     * How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
     * Absent for other values, and when its rating could not be worked out, such as when a count failed.
     */
    public Optional<RecordDistinctive> distinctive() {
        return Optional.ofNullable(this.distinctive);
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
     * The value as the source wrote it. A count is written in decimal and a time in RFC 3339; a breach
     * column's value is as the leak holds it.
     */
    public RecordField withOriginal(@Nonnull String original) {
        this.original = Utils.checkNotNull(original, "original");
        return this;
    }

    /**
     * What sort of value a field holds, which decides how it is cleaned, compared and searched again. It
     * comes from the value itself where its shape is certain (an email, an IP, a URL, an international
     * phone number), else from the answer stored for the source's column, else text. A value of any kind
     * is returned.
     *
     * <p>Each kind's description says what it holds and the form value takes.
     */
    public RecordField withKind(@Nullable RecordKind kind) {
        this.kind = kind;
        return this;
    }

    /**
     * Which common field a value is, where many sources share it: a join date and a birth date are both
     * dates, with the roles joined and born. A platform account's field and a breach row's column with the
     * same meaning carry the same kind and role. A linked_account field is named by its network.
     *
     * <p>Absent for a field only its source returns. Each role's description names the kinds its values have.
     */
    public RecordField withRole(@Nullable RecordRole role) {
        this.role = role;
        return this;
    }

    /**
     * The value cleaned to its kind's one form: an email in lower case, a phone number in international
     * form when its country is known, a date in ISO form, a country as its two-letter code, a flag as true
     * or false. Absent when the value cannot be cleaned as its kind; original still holds it.
     */
    public RecordField withValue(@Nullable String value) {
        this.value = value;
        return this;
    }

    /**
     * What the value shows of itself beyond its kind: the likely algorithm of a hash, the likely coin of a
     * crypto address or the currency of an amount.
     */
    public RecordField withDetail(@Nullable String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
     * Absent for other values, and when its rating could not be worked out, such as when a count failed.
     */
    public RecordField withDistinctive(@Nullable RecordDistinctive distinctive) {
        this.distinctive = distinctive;
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
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.detail, other.detail)
                && Utils.enhancedDeepEquals(this.distinctive, other.distinctive);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(field, original, kind, role, value, detail, distinctive);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordField.class,
                "field",
                field,
                "original",
                original,
                "kind",
                kind,
                "role",
                role,
                "value",
                value,
                "detail",
                detail,
                "distinctive",
                distinctive);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String field;

        private String original;

        private RecordKind kind;

        private RecordRole role;

        private String value;

        private String detail;

        private RecordDistinctive distinctive;

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
         * The value as the source wrote it. A count is written in decimal and a time in RFC 3339; a breach
         * column's value is as the leak holds it.
         */
        public Builder original(@Nonnull String original) {
            this.original = Utils.checkNotNull(original, "original");
            return this;
        }

        /**
         * What sort of value a field holds, which decides how it is cleaned, compared and searched again. It
         * comes from the value itself where its shape is certain (an email, an IP, a URL, an international
         * phone number), else from the answer stored for the source's column, else text. A value of any kind
         * is returned.
         *
         * <p>Each kind's description says what it holds and the form value takes.
         */
        public Builder kind(@Nullable RecordKind kind) {
            this.kind = kind;
            return this;
        }

        /**
         * Which common field a value is, where many sources share it: a join date and a birth date are both
         * dates, with the roles joined and born. A platform account's field and a breach row's column with the
         * same meaning carry the same kind and role. A linked_account field is named by its network.
         *
         * <p>Absent for a field only its source returns. Each role's description names the kinds its values have.
         */
        public Builder role(@Nullable RecordRole role) {
            this.role = role;
            return this;
        }

        /**
         * The value cleaned to its kind's one form: an email in lower case, a phone number in international
         * form when its country is known, a date in ISO form, a country as its two-letter code, a flag as true
         * or false. Absent when the value cannot be cleaned as its kind; original still holds it.
         */
        public Builder value(@Nullable String value) {
            this.value = value;
            return this;
        }

        /**
         * What the value shows of itself beyond its kind: the likely algorithm of a hash, the likely coin of a
         * crypto address or the currency of an amount.
         */
        public Builder detail(@Nullable String detail) {
            this.detail = detail;
            return this;
        }

        /**
         * How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
         * Absent for other values, and when its rating could not be worked out, such as when a count failed.
         */
        public Builder distinctive(@Nullable RecordDistinctive distinctive) {
            this.distinctive = distinctive;
            return this;
        }

        public RecordField build() {
            return new RecordField(field, original, kind, role, value, detail, distinctive);
        }
    }
}
