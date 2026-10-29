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
 * Export
 *
 * <p>Retained review snapshot.
 */
public class Export {

    @JsonProperty("exported_at")
    private String exportedAt;

    @JsonProperty("review")
    private Review review;

    @JsonProperty("comments")
    private List<ReviewComment> comments;

    @JsonProperty("resolutions")
    private List<Resolution> resolutions;

    @JsonProperty("subjects")
    private List<ReviewSubject> subjects;

    @JsonCreator
    public Export(
            @JsonProperty("exported_at") @Nonnull String exportedAt,
            @JsonProperty("review") @Nonnull Review review,
            @JsonProperty("comments") @Nonnull List<ReviewComment> comments,
            @JsonProperty("resolutions") @Nonnull List<Resolution> resolutions,
            @JsonProperty("subjects") @Nonnull List<ReviewSubject> subjects) {
        this.exportedAt = Optional.ofNullable(exportedAt)
                .orElseThrow(() -> new IllegalArgumentException("exportedAt cannot be null"));
        this.review =
                Optional.ofNullable(review).orElseThrow(() -> new IllegalArgumentException("review cannot be null"));
        this.comments = Optional.ofNullable(comments)
                .orElseThrow(() -> new IllegalArgumentException("comments cannot be null"));
        this.resolutions = Optional.ofNullable(resolutions)
                .orElseThrow(() -> new IllegalArgumentException("resolutions cannot be null"));
        this.subjects = Optional.ofNullable(subjects)
                .orElseThrow(() -> new IllegalArgumentException("subjects cannot be null"));
    }

    public String exportedAt() {
        return this.exportedAt;
    }

    public Review review() {
        return this.review;
    }

    public List<ReviewComment> comments() {
        return this.comments;
    }

    public List<Resolution> resolutions() {
        return this.resolutions;
    }

    public List<ReviewSubject> subjects() {
        return this.subjects;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Export withExportedAt(@Nonnull String exportedAt) {
        this.exportedAt = Utils.checkNotNull(exportedAt, "exportedAt");
        return this;
    }

    public Export withReview(@Nonnull Review review) {
        this.review = Utils.checkNotNull(review, "review");
        return this;
    }

    public Export withComments(@Nonnull List<ReviewComment> comments) {
        this.comments = Utils.checkNotNull(comments, "comments");
        return this;
    }

    public Export withResolutions(@Nonnull List<Resolution> resolutions) {
        this.resolutions = Utils.checkNotNull(resolutions, "resolutions");
        return this;
    }

    public Export withSubjects(@Nonnull List<ReviewSubject> subjects) {
        this.subjects = Utils.checkNotNull(subjects, "subjects");
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
        Export other = (Export) o;
        return Utils.enhancedDeepEquals(this.exportedAt, other.exportedAt)
                && Utils.enhancedDeepEquals(this.review, other.review)
                && Utils.enhancedDeepEquals(this.comments, other.comments)
                && Utils.enhancedDeepEquals(this.resolutions, other.resolutions)
                && Utils.enhancedDeepEquals(this.subjects, other.subjects);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(exportedAt, review, comments, resolutions, subjects);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Export.class,
                "exportedAt",
                exportedAt,
                "review",
                review,
                "comments",
                comments,
                "resolutions",
                resolutions,
                "subjects",
                subjects);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String exportedAt;

        private Review review;

        private List<ReviewComment> comments;

        private List<Resolution> resolutions;

        private List<ReviewSubject> subjects;

        private Builder() {
            // force use of static builder() method
        }

        public Builder exportedAt(@Nonnull String exportedAt) {
            this.exportedAt = Utils.checkNotNull(exportedAt, "exportedAt");
            return this;
        }

        public Builder review(@Nonnull Review review) {
            this.review = Utils.checkNotNull(review, "review");
            return this;
        }

        public Builder comments(@Nonnull List<ReviewComment> comments) {
            this.comments = Utils.checkNotNull(comments, "comments");
            return this;
        }

        public Builder resolutions(@Nonnull List<Resolution> resolutions) {
            this.resolutions = Utils.checkNotNull(resolutions, "resolutions");
            return this;
        }

        public Builder subjects(@Nonnull List<ReviewSubject> subjects) {
            this.subjects = Utils.checkNotNull(subjects, "subjects");
            return this;
        }

        public Export build() {
            return new Export(exportedAt, review, comments, resolutions, subjects);
        }
    }
}
