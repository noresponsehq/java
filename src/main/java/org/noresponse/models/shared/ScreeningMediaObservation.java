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
 * ScreeningMediaObservation
 *
 * <p>What an analyser saw in one image that a finding rests on.
 */
public class ScreeningMediaObservation {
    /**
     * The image, one of the finding's evidence.
     */
    @JsonProperty("media_id")
    private String mediaId;

    /**
     * What the analyser saw in the image that supports the finding.
     */
    @JsonProperty("description")
    private String description;

    /**
     * Words the analyser read in the image, as it read them. They come from the image's pixels, not from a
     * post's text, so they are not a quote and were not checked against the record.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("read_text")
    private String readText;

    @JsonCreator
    public ScreeningMediaObservation(
            @JsonProperty("media_id") @Nonnull String mediaId,
            @JsonProperty("description") @Nonnull String description,
            @JsonProperty("read_text") @Nullable String readText) {
        this.mediaId =
                Optional.ofNullable(mediaId).orElseThrow(() -> new IllegalArgumentException("mediaId cannot be null"));
        this.description = Optional.ofNullable(description)
                .orElseThrow(() -> new IllegalArgumentException("description cannot be null"));
        this.readText = readText;
    }

    public ScreeningMediaObservation(@Nonnull String mediaId, @Nonnull String description) {
        this(mediaId, description, null);
    }

    /**
     * The image, one of the finding's evidence.
     */
    public String mediaId() {
        return this.mediaId;
    }

    /**
     * What the analyser saw in the image that supports the finding.
     */
    public String description() {
        return this.description;
    }

    /**
     * Words the analyser read in the image, as it read them. They come from the image's pixels, not from a
     * post's text, so they are not a quote and were not checked against the record.
     */
    public Optional<String> readText() {
        return Optional.ofNullable(this.readText);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The image, one of the finding's evidence.
     */
    public ScreeningMediaObservation withMediaId(@Nonnull String mediaId) {
        this.mediaId = Utils.checkNotNull(mediaId, "mediaId");
        return this;
    }

    /**
     * What the analyser saw in the image that supports the finding.
     */
    public ScreeningMediaObservation withDescription(@Nonnull String description) {
        this.description = Utils.checkNotNull(description, "description");
        return this;
    }

    /**
     * Words the analyser read in the image, as it read them. They come from the image's pixels, not from a
     * post's text, so they are not a quote and were not checked against the record.
     */
    public ScreeningMediaObservation withReadText(@Nullable String readText) {
        this.readText = readText;
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
        ScreeningMediaObservation other = (ScreeningMediaObservation) o;
        return Utils.enhancedDeepEquals(this.mediaId, other.mediaId)
                && Utils.enhancedDeepEquals(this.description, other.description)
                && Utils.enhancedDeepEquals(this.readText, other.readText);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(mediaId, description, readText);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningMediaObservation.class, "mediaId", mediaId, "description", description, "readText", readText);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String mediaId;

        private String description;

        private String readText;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The image, one of the finding's evidence.
         */
        public Builder mediaId(@Nonnull String mediaId) {
            this.mediaId = Utils.checkNotNull(mediaId, "mediaId");
            return this;
        }

        /**
         * What the analyser saw in the image that supports the finding.
         */
        public Builder description(@Nonnull String description) {
            this.description = Utils.checkNotNull(description, "description");
            return this;
        }

        /**
         * Words the analyser read in the image, as it read them. They come from the image's pixels, not from a
         * post's text, so they are not a quote and were not checked against the record.
         */
        public Builder readText(@Nullable String readText) {
            this.readText = readText;
            return this;
        }

        public ScreeningMediaObservation build() {
            return new ScreeningMediaObservation(mediaId, description, readText);
        }
    }
}
