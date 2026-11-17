package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Double;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RecordDistinctive
 *
 * <p>How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
 * Absent for other values, for a field with no value, and when its rating could not be worked out,
 * such as when a count failed. Linkage weighs a password, hash or username by these numbers: the rarer
 * the value, the more a shared one counts.
 */
public class RecordDistinctive {
    /**
     * unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a
     * new search on it likely finds one person. common: shared by many people, so a search on it finds
     * strangers.
     *
     * <p>A new search is suggested for unique and distinctive values only; any value can still be searched.
     */
    @JsonProperty("rating")
    private Rating rating;

    /**
     * Why, in plain words, such as "seen 3,861,493 times in breach data", "in 4 breach rows" or "38 bits,
     * about one in 430 billion usernames".
     */
    @JsonProperty("reason")
    private String reason;

    /**
     * How many breach rows hold the value, when they were counted. Set for a username, password, hash or
     * person name.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("rows")
    private Long rows;

    /**
     * How many times the Pwned Passwords list holds the password, when it was asked. Set for a password
     * only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("pwned")
    private Long pwned;

    /**
     * How surprising the username is, to one decimal place: a character model spells it about once in
     * 2^bits usernames. Set for a username the model can spell.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("bits")
    private Double bits;

    @JsonCreator
    public RecordDistinctive(
            @JsonProperty("rating") @Nonnull Rating rating,
            @JsonProperty("reason") @Nonnull String reason,
            @JsonProperty("rows") @Nullable Long rows,
            @JsonProperty("pwned") @Nullable Long pwned,
            @JsonProperty("bits") @Nullable Double bits) {
        this.rating =
                Optional.ofNullable(rating).orElseThrow(() -> new IllegalArgumentException("rating cannot be null"));
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
        this.rows = rows;
        this.pwned = pwned;
        this.bits = bits;
    }

    public RecordDistinctive(@Nonnull Rating rating, @Nonnull String reason) {
        this(rating, reason, null, null, null);
    }

    /**
     * unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a
     * new search on it likely finds one person. common: shared by many people, so a search on it finds
     * strangers.
     *
     * <p>A new search is suggested for unique and distinctive values only; any value can still be searched.
     */
    public Rating rating() {
        return this.rating;
    }

    /**
     * Why, in plain words, such as "seen 3,861,493 times in breach data", "in 4 breach rows" or "38 bits,
     * about one in 430 billion usernames".
     */
    public String reason() {
        return this.reason;
    }

    /**
     * How many breach rows hold the value, when they were counted. Set for a username, password, hash or
     * person name.
     */
    public Optional<Long> rows() {
        return Optional.ofNullable(this.rows);
    }

    /**
     * How many times the Pwned Passwords list holds the password, when it was asked. Set for a password
     * only.
     */
    public Optional<Long> pwned() {
        return Optional.ofNullable(this.pwned);
    }

    /**
     * How surprising the username is, to one decimal place: a character model spells it about once in
     * 2^bits usernames. Set for a username the model can spell.
     */
    public Optional<Double> bits() {
        return Optional.ofNullable(this.bits);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a
     * new search on it likely finds one person. common: shared by many people, so a search on it finds
     * strangers.
     *
     * <p>A new search is suggested for unique and distinctive values only; any value can still be searched.
     */
    public RecordDistinctive withRating(@Nonnull Rating rating) {
        this.rating = Utils.checkNotNull(rating, "rating");
        return this;
    }

    /**
     * Why, in plain words, such as "seen 3,861,493 times in breach data", "in 4 breach rows" or "38 bits,
     * about one in 430 billion usernames".
     */
    public RecordDistinctive withReason(@Nonnull String reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
        return this;
    }

    /**
     * How many breach rows hold the value, when they were counted. Set for a username, password, hash or
     * person name.
     */
    public RecordDistinctive withRows(@Nullable Long rows) {
        this.rows = rows;
        return this;
    }

    /**
     * How many times the Pwned Passwords list holds the password, when it was asked. Set for a password
     * only.
     */
    public RecordDistinctive withPwned(@Nullable Long pwned) {
        this.pwned = pwned;
        return this;
    }

    /**
     * How surprising the username is, to one decimal place: a character model spells it about once in
     * 2^bits usernames. Set for a username the model can spell.
     */
    public RecordDistinctive withBits(@Nullable Double bits) {
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
        RecordDistinctive other = (RecordDistinctive) o;
        return Utils.enhancedDeepEquals(this.rating, other.rating)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.rows, other.rows)
                && Utils.enhancedDeepEquals(this.pwned, other.pwned)
                && Utils.enhancedDeepEquals(this.bits, other.bits);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(rating, reason, rows, pwned, bits);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordDistinctive.class,
                "rating",
                rating,
                "reason",
                reason,
                "rows",
                rows,
                "pwned",
                pwned,
                "bits",
                bits);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Rating rating;

        private String reason;

        private Long rows;

        private Long pwned;

        private Double bits;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a
         * new search on it likely finds one person. common: shared by many people, so a search on it finds
         * strangers.
         *
         * <p>A new search is suggested for unique and distinctive values only; any value can still be searched.
         */
        public Builder rating(@Nonnull Rating rating) {
            this.rating = Utils.checkNotNull(rating, "rating");
            return this;
        }

        /**
         * Why, in plain words, such as "seen 3,861,493 times in breach data", "in 4 breach rows" or "38 bits,
         * about one in 430 billion usernames".
         */
        public Builder reason(@Nonnull String reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        /**
         * How many breach rows hold the value, when they were counted. Set for a username, password, hash or
         * person name.
         */
        public Builder rows(@Nullable Long rows) {
            this.rows = rows;
            return this;
        }

        /**
         * How many times the Pwned Passwords list holds the password, when it was asked. Set for a password
         * only.
         */
        public Builder pwned(@Nullable Long pwned) {
            this.pwned = pwned;
            return this;
        }

        /**
         * How surprising the username is, to one decimal place: a character model spells it about once in
         * 2^bits usernames. Set for a username the model can spell.
         */
        public Builder bits(@Nullable Double bits) {
            this.bits = bits;
            return this;
        }

        public RecordDistinctive build() {
            return new RecordDistinctive(rating, reason, rows, pwned, bits);
        }
    }
}
