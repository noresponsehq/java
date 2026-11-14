package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningNotAnalysedCount
 *
 * <p>How many items read were not analysed for one reason.
 */
public class ScreeningNotAnalysedCount {
    /**
     * Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable:
     * the image could not be fetched from the platform.
     *
     * <p>image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the
     * most images one person's analysis reads. refused: the model declined to read it.
     *
     * <p>unfinished: the model cut its batch short or answered outside the schema, or the request was
     * rejected. unverified: a finding cited it with words it does not contain or an image it did not
     * describe. unspecified: the analyser gave no reason.
     */
    @JsonProperty("reason")
    private ScreeningNotAnalysedReason reason;

    @JsonProperty("items")
    private long items;

    @JsonCreator
    public ScreeningNotAnalysedCount(
            @JsonProperty("reason") @Nonnull ScreeningNotAnalysedReason reason, @JsonProperty("items") long items) {
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
        this.items = items;
    }

    /**
     * Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable:
     * the image could not be fetched from the platform.
     *
     * <p>image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the
     * most images one person's analysis reads. refused: the model declined to read it.
     *
     * <p>unfinished: the model cut its batch short or answered outside the schema, or the request was
     * rejected. unverified: a finding cited it with words it does not contain or an image it did not
     * describe. unspecified: the analyser gave no reason.
     */
    public ScreeningNotAnalysedReason reason() {
        return this.reason;
    }

    public long items() {
        return this.items;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable:
     * the image could not be fetched from the platform.
     *
     * <p>image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the
     * most images one person's analysis reads. refused: the model declined to read it.
     *
     * <p>unfinished: the model cut its batch short or answered outside the schema, or the request was
     * rejected. unverified: a finding cited it with words it does not contain or an image it did not
     * describe. unspecified: the analyser gave no reason.
     */
    public ScreeningNotAnalysedCount withReason(@Nonnull ScreeningNotAnalysedReason reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
        return this;
    }

    public ScreeningNotAnalysedCount withItems(long items) {
        this.items = items;
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
        ScreeningNotAnalysedCount other = (ScreeningNotAnalysedCount) o;
        return Utils.enhancedDeepEquals(this.reason, other.reason) && Utils.enhancedDeepEquals(this.items, other.items);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(reason, items);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningNotAnalysedCount.class, "reason", reason, "items", items);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningNotAnalysedReason reason;

        private long items;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable:
         * the image could not be fetched from the platform.
         *
         * <p>image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the
         * most images one person's analysis reads. refused: the model declined to read it.
         *
         * <p>unfinished: the model cut its batch short or answered outside the schema, or the request was
         * rejected. unverified: a finding cited it with words it does not contain or an image it did not
         * describe. unspecified: the analyser gave no reason.
         */
        public Builder reason(@Nonnull ScreeningNotAnalysedReason reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        public Builder items(long items) {
            this.items = items;
            return this;
        }

        public ScreeningNotAnalysedCount build() {
            return new ScreeningNotAnalysedCount(reason, items);
        }
    }
}
