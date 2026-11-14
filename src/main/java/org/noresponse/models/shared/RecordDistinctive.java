package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RecordDistinctive
 *
 * <p>How distinctive a searchable value is: an email, phone, username, password, hash, person name or IP.
 * Absent for other values, and when its rating could not be worked out, such as when a count failed.
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

    @JsonCreator
    public RecordDistinctive(
            @JsonProperty("rating") @Nonnull Rating rating, @JsonProperty("reason") @Nonnull String reason) {
        this.rating =
                Optional.ofNullable(rating).orElseThrow(() -> new IllegalArgumentException("rating cannot be null"));
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordDistinctive other = (RecordDistinctive) o;
        return Utils.enhancedDeepEquals(this.rating, other.rating) && Utils.enhancedDeepEquals(this.reason, other.reason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(rating, reason);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordDistinctive.class, "rating", rating, "reason", reason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Rating rating;

        private String reason;

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

        public RecordDistinctive build() {
            return new RecordDistinctive(rating, reason);
        }
    }
}
