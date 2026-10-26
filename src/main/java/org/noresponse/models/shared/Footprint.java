package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Footprint
 *
 * <p>How long and how widely the signup's email and username have existed, and how far independent
 * sources agree on the person behind them, in years and counts only. families are the kinds of
 * evidence that found either identifier. Omitted when nothing found them: a footprint lookup that
 * failed makes no claim, and absence is no_digital_footprint's to state.
 */
public class Footprint {
    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private FootprintTimeline email;

    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private FootprintTimeline username;

    @JsonProperty("families")
    private List<FootprintFamily> families;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("coherence")
    private FootprintCoherence coherence;

    @JsonCreator
    public Footprint(
            @JsonProperty("email") @Nullable FootprintTimeline email,
            @JsonProperty("username") @Nullable FootprintTimeline username,
            @JsonProperty("families") @Nonnull List<FootprintFamily> families,
            @JsonProperty("coherence") @Nullable FootprintCoherence coherence) {
        this.email = email;
        this.username = username;
        this.families = Optional.ofNullable(families)
                .orElseThrow(() -> new IllegalArgumentException("families cannot be null"));
        this.coherence = coherence;
    }

    public Footprint(@Nonnull List<FootprintFamily> families) {
        this(null, null, families, null);
    }

    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    public Optional<FootprintTimeline> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    public Optional<FootprintTimeline> username() {
        return Optional.ofNullable(this.username);
    }

    public List<FootprintFamily> families() {
        return this.families;
    }

    public Optional<FootprintCoherence> coherence() {
        return Optional.ofNullable(this.coherence);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    public Footprint withEmail(@Nullable FootprintTimeline email) {
        this.email = email;
        return this;
    }

    /**
     * One identifier's dated footprint across every source: the first and last year any event was seen,
     * the years between them, the latest year a source dated activity (omitted when none did), how many
     * distinct sites and which kinds of evidence saw it.
     */
    public Footprint withUsername(@Nullable FootprintTimeline username) {
        this.username = username;
        return this;
    }

    public Footprint withFamilies(@Nonnull List<FootprintFamily> families) {
        this.families = Utils.checkNotNull(families, "families");
        return this;
    }

    public Footprint withCoherence(@Nullable FootprintCoherence coherence) {
        this.coherence = coherence;
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
        Footprint other = (Footprint) o;
        return Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.families, other.families)
                && Utils.enhancedDeepEquals(this.coherence, other.coherence);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(email, username, families, coherence);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Footprint.class, "email", email, "username", username, "families", families, "coherence", coherence);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private FootprintTimeline email;

        private FootprintTimeline username;

        private List<FootprintFamily> families;

        private FootprintCoherence coherence;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One identifier's dated footprint across every source: the first and last year any event was seen,
         * the years between them, the latest year a source dated activity (omitted when none did), how many
         * distinct sites and which kinds of evidence saw it.
         */
        public Builder email(@Nullable FootprintTimeline email) {
            this.email = email;
            return this;
        }

        /**
         * One identifier's dated footprint across every source: the first and last year any event was seen,
         * the years between them, the latest year a source dated activity (omitted when none did), how many
         * distinct sites and which kinds of evidence saw it.
         */
        public Builder username(@Nullable FootprintTimeline username) {
            this.username = username;
            return this;
        }

        public Builder families(@Nonnull List<FootprintFamily> families) {
            this.families = Utils.checkNotNull(families, "families");
            return this;
        }

        public Builder coherence(@Nullable FootprintCoherence coherence) {
            this.coherence = coherence;
            return this;
        }

        public Footprint build() {
            return new Footprint(email, username, families, coherence);
        }
    }
}
