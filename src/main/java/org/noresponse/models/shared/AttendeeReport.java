package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeReport
 *
 * <p>Everything known about one attendee.
 */
public class AttendeeReport {
    /**
     * An attendee and where their screening and review stand.
     */
    @JsonProperty("attendee")
    private Attendee attendee;

    /**
     * The accounts the screening matched, with how each was matched.
     */
    @JsonProperty("profiles")
    private List<Profile> profiles;

    /**
     * What the screening flagged, with its evidence.
     */
    @JsonProperty("findings")
    private List<Finding> findings;

    /**
     * Every recorded decision, oldest first.
     */
    @JsonProperty("reviews")
    private List<AttendeeReview> reviews;

    @JsonCreator
    public AttendeeReport(
            @JsonProperty("attendee") @Nonnull Attendee attendee,
            @JsonProperty("profiles") @Nonnull List<Profile> profiles,
            @JsonProperty("findings") @Nonnull List<Finding> findings,
            @JsonProperty("reviews") @Nonnull List<AttendeeReview> reviews) {
        this.attendee = Optional.ofNullable(attendee)
                .orElseThrow(() -> new IllegalArgumentException("attendee cannot be null"));
        this.profiles = Optional.ofNullable(profiles)
                .orElseThrow(() -> new IllegalArgumentException("profiles cannot be null"));
        this.findings = Optional.ofNullable(findings)
                .orElseThrow(() -> new IllegalArgumentException("findings cannot be null"));
        this.reviews =
                Optional.ofNullable(reviews).orElseThrow(() -> new IllegalArgumentException("reviews cannot be null"));
    }

    /**
     * An attendee and where their screening and review stand.
     */
    public Attendee attendee() {
        return this.attendee;
    }

    /**
     * The accounts the screening matched, with how each was matched.
     */
    public List<Profile> profiles() {
        return this.profiles;
    }

    /**
     * What the screening flagged, with its evidence.
     */
    public List<Finding> findings() {
        return this.findings;
    }

    /**
     * Every recorded decision, oldest first.
     */
    public List<AttendeeReview> reviews() {
        return this.reviews;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * An attendee and where their screening and review stand.
     */
    public AttendeeReport withAttendee(@Nonnull Attendee attendee) {
        this.attendee = Utils.checkNotNull(attendee, "attendee");
        return this;
    }

    /**
     * The accounts the screening matched, with how each was matched.
     */
    public AttendeeReport withProfiles(@Nonnull List<Profile> profiles) {
        this.profiles = Utils.checkNotNull(profiles, "profiles");
        return this;
    }

    /**
     * What the screening flagged, with its evidence.
     */
    public AttendeeReport withFindings(@Nonnull List<Finding> findings) {
        this.findings = Utils.checkNotNull(findings, "findings");
        return this;
    }

    /**
     * Every recorded decision, oldest first.
     */
    public AttendeeReport withReviews(@Nonnull List<AttendeeReview> reviews) {
        this.reviews = Utils.checkNotNull(reviews, "reviews");
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
        AttendeeReport other = (AttendeeReport) o;
        return Utils.enhancedDeepEquals(this.attendee, other.attendee)
                && Utils.enhancedDeepEquals(this.profiles, other.profiles)
                && Utils.enhancedDeepEquals(this.findings, other.findings)
                && Utils.enhancedDeepEquals(this.reviews, other.reviews);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(attendee, profiles, findings, reviews);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AttendeeReport.class,
                "attendee",
                attendee,
                "profiles",
                profiles,
                "findings",
                findings,
                "reviews",
                reviews);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Attendee attendee;

        private List<Profile> profiles;

        private List<Finding> findings;

        private List<AttendeeReview> reviews;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * An attendee and where their screening and review stand.
         */
        public Builder attendee(@Nonnull Attendee attendee) {
            this.attendee = Utils.checkNotNull(attendee, "attendee");
            return this;
        }

        /**
         * The accounts the screening matched, with how each was matched.
         */
        public Builder profiles(@Nonnull List<Profile> profiles) {
            this.profiles = Utils.checkNotNull(profiles, "profiles");
            return this;
        }

        /**
         * What the screening flagged, with its evidence.
         */
        public Builder findings(@Nonnull List<Finding> findings) {
            this.findings = Utils.checkNotNull(findings, "findings");
            return this;
        }

        /**
         * Every recorded decision, oldest first.
         */
        public Builder reviews(@Nonnull List<AttendeeReview> reviews) {
            this.reviews = Utils.checkNotNull(reviews, "reviews");
            return this;
        }

        public AttendeeReport build() {
            return new AttendeeReport(attendee, profiles, findings, reviews);
        }
    }
}
