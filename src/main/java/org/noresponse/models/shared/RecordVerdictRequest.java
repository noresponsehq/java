package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RecordVerdictRequest
 *
 * <p>A reviewer's verdict on whether one of a person's lookup records is theirs.
 */
public class RecordVerdictRequest {
    /**
     * One of the person's lookup records.
     */
    @JsonProperty("record_id")
    private String recordId;

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    @JsonProperty("verdict")
    private Verdict verdict;

    @JsonCreator
    public RecordVerdictRequest(
            @JsonProperty("record_id") @Nonnull String recordId, @JsonProperty("verdict") @Nonnull Verdict verdict) {
        this.recordId = Optional.ofNullable(recordId)
                .orElseThrow(() -> new IllegalArgumentException("recordId cannot be null"));
        this.verdict =
                Optional.ofNullable(verdict).orElseThrow(() -> new IllegalArgumentException("verdict cannot be null"));
    }

    /**
     * One of the person's lookup records.
     */
    public String recordId() {
        return this.recordId;
    }

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    public Verdict verdict() {
        return this.verdict;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One of the person's lookup records.
     */
    public RecordVerdictRequest withRecordId(@Nonnull String recordId) {
        this.recordId = Utils.checkNotNull(recordId, "recordId");
        return this;
    }

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    public RecordVerdictRequest withVerdict(@Nonnull Verdict verdict) {
        this.verdict = Utils.checkNotNull(verdict, "verdict");
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
        RecordVerdictRequest other = (RecordVerdictRequest) o;
        return Utils.enhancedDeepEquals(this.recordId, other.recordId)
                && Utils.enhancedDeepEquals(this.verdict, other.verdict);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(recordId, verdict);
    }

    @Override
    public String toString() {
        return Utils.toString(RecordVerdictRequest.class, "recordId", recordId, "verdict", verdict);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String recordId;

        private Verdict verdict;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * One of the person's lookup records.
         */
        public Builder recordId(@Nonnull String recordId) {
            this.recordId = Utils.checkNotNull(recordId, "recordId");
            return this;
        }

        /**
         * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
         * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
         * it.
         */
        public Builder verdict(@Nonnull Verdict verdict) {
            this.verdict = Utils.checkNotNull(verdict, "verdict");
            return this;
        }

        public RecordVerdictRequest build() {
            return new RecordVerdictRequest(recordId, verdict);
        }
    }
}
