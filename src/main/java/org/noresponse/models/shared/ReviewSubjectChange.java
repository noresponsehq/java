package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ReviewSubjectChange
 *
 * <p>The link and resulting review version.
 */
public class ReviewSubjectChange {

    @JsonProperty("review_version")
    private long reviewVersion;

    /**
     * A tenant-local investigation link. risk_version is the account risk version captured when the link
     * was added. It does not assert a relationship or change feedback or enforcement.
     */
    @JsonProperty("subject")
    private ReviewSubject subject;

    @JsonCreator
    public ReviewSubjectChange(
            @JsonProperty("review_version") long reviewVersion,
            @JsonProperty("subject") @Nonnull ReviewSubject subject) {
        this.reviewVersion = reviewVersion;
        this.subject =
                Optional.ofNullable(subject).orElseThrow(() -> new IllegalArgumentException("subject cannot be null"));
    }

    public long reviewVersion() {
        return this.reviewVersion;
    }

    /**
     * A tenant-local investigation link. risk_version is the account risk version captured when the link
     * was added. It does not assert a relationship or change feedback or enforcement.
     */
    public ReviewSubject subject() {
        return this.subject;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewSubjectChange withReviewVersion(long reviewVersion) {
        this.reviewVersion = reviewVersion;
        return this;
    }

    /**
     * A tenant-local investigation link. risk_version is the account risk version captured when the link
     * was added. It does not assert a relationship or change feedback or enforcement.
     */
    public ReviewSubjectChange withSubject(@Nonnull ReviewSubject subject) {
        this.subject = Utils.checkNotNull(subject, "subject");
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
        ReviewSubjectChange other = (ReviewSubjectChange) o;
        return Utils.enhancedDeepEquals(this.reviewVersion, other.reviewVersion)
                && Utils.enhancedDeepEquals(this.subject, other.subject);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(reviewVersion, subject);
    }

    @Override
    public String toString() {
        return Utils.toString(ReviewSubjectChange.class, "reviewVersion", reviewVersion, "subject", subject);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long reviewVersion;

        private ReviewSubject subject;

        private Builder() {
            // force use of static builder() method
        }

        public Builder reviewVersion(long reviewVersion) {
            this.reviewVersion = reviewVersion;
            return this;
        }

        /**
         * A tenant-local investigation link. risk_version is the account risk version captured when the link
         * was added. It does not assert a relationship or change feedback or enforcement.
         */
        public Builder subject(@Nonnull ReviewSubject subject) {
            this.subject = Utils.checkNotNull(subject, "subject");
            return this;
        }

        public ReviewSubjectChange build() {
            return new ReviewSubjectChange(reviewVersion, subject);
        }
    }
}
