package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RecordVerdict
 *
 * <p>A recorded verdict on one record. A later verdict on the same record replaces it.
 */
public class RecordVerdict {

    @JsonProperty("record_id")
    private String recordId;

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    @JsonProperty("verdict")
    private Verdict verdict;

    @JsonProperty("decided_by")
    private String decidedBy;

    @JsonProperty("decided_at")
    private String decidedAt;

    @JsonCreator
    public RecordVerdict(
            @JsonProperty("record_id") @Nonnull String recordId,
            @JsonProperty("verdict") @Nonnull Verdict verdict,
            @JsonProperty("decided_by") @Nonnull String decidedBy,
            @JsonProperty("decided_at") @Nonnull String decidedAt) {
        this.recordId = Optional.ofNullable(recordId)
                .orElseThrow(() -> new IllegalArgumentException("recordId cannot be null"));
        this.verdict =
                Optional.ofNullable(verdict).orElseThrow(() -> new IllegalArgumentException("verdict cannot be null"));
        this.decidedBy = Optional.ofNullable(decidedBy)
                .orElseThrow(() -> new IllegalArgumentException("decidedBy cannot be null"));
        this.decidedAt = Optional.ofNullable(decidedAt)
                .orElseThrow(() -> new IllegalArgumentException("decidedAt cannot be null"));
    }

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

    public String decidedBy() {
        return this.decidedBy;
    }

    public String decidedAt() {
        return this.decidedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RecordVerdict withRecordId(@Nonnull String recordId) {
        this.recordId = Utils.checkNotNull(recordId, "recordId");
        return this;
    }

    /**
     * A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the
     * likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled
     * it.
     */
    public RecordVerdict withVerdict(@Nonnull Verdict verdict) {
        this.verdict = Utils.checkNotNull(verdict, "verdict");
        return this;
    }

    public RecordVerdict withDecidedBy(@Nonnull String decidedBy) {
        this.decidedBy = Utils.checkNotNull(decidedBy, "decidedBy");
        return this;
    }

    public RecordVerdict withDecidedAt(@Nonnull String decidedAt) {
        this.decidedAt = Utils.checkNotNull(decidedAt, "decidedAt");
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
        RecordVerdict other = (RecordVerdict) o;
        return Utils.enhancedDeepEquals(this.recordId, other.recordId)
                && Utils.enhancedDeepEquals(this.verdict, other.verdict)
                && Utils.enhancedDeepEquals(this.decidedBy, other.decidedBy)
                && Utils.enhancedDeepEquals(this.decidedAt, other.decidedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(recordId, verdict, decidedBy, decidedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RecordVerdict.class,
                "recordId",
                recordId,
                "verdict",
                verdict,
                "decidedBy",
                decidedBy,
                "decidedAt",
                decidedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String recordId;

        private Verdict verdict;

        private String decidedBy;

        private String decidedAt;

        private Builder() {
            // force use of static builder() method
        }

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

        public Builder decidedBy(@Nonnull String decidedBy) {
            this.decidedBy = Utils.checkNotNull(decidedBy, "decidedBy");
            return this;
        }

        public Builder decidedAt(@Nonnull String decidedAt) {
            this.decidedAt = Utils.checkNotNull(decidedAt, "decidedAt");
            return this;
        }

        public RecordVerdict build() {
            return new RecordVerdict(recordId, verdict, decidedBy, decidedAt);
        }
    }
}
