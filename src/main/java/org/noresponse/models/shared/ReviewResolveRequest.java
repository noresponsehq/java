package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ReviewResolveRequest {

    @JsonProperty("expected_version")
    private long expectedVersion;

    @JsonProperty("expected_risk_version")
    private long expectedRiskVersion;

    @JsonProperty("conclusion")
    private Conclusion conclusion;

    @JsonProperty("note")
    private String note;

    @JsonCreator
    public ReviewResolveRequest(
            @JsonProperty("expected_version") long expectedVersion,
            @JsonProperty("expected_risk_version") long expectedRiskVersion,
            @JsonProperty("conclusion") @Nonnull Conclusion conclusion,
            @JsonProperty("note") @Nonnull String note) {
        this.expectedVersion = expectedVersion;
        this.expectedRiskVersion = expectedRiskVersion;
        this.conclusion = Optional.ofNullable(conclusion)
                .orElseThrow(() -> new IllegalArgumentException("conclusion cannot be null"));
        this.note = Optional.ofNullable(note).orElseThrow(() -> new IllegalArgumentException("note cannot be null"));
    }

    public long expectedVersion() {
        return this.expectedVersion;
    }

    public long expectedRiskVersion() {
        return this.expectedRiskVersion;
    }

    public Conclusion conclusion() {
        return this.conclusion;
    }

    public String note() {
        return this.note;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewResolveRequest withExpectedVersion(long expectedVersion) {
        this.expectedVersion = expectedVersion;
        return this;
    }

    public ReviewResolveRequest withExpectedRiskVersion(long expectedRiskVersion) {
        this.expectedRiskVersion = expectedRiskVersion;
        return this;
    }

    public ReviewResolveRequest withConclusion(@Nonnull Conclusion conclusion) {
        this.conclusion = Utils.checkNotNull(conclusion, "conclusion");
        return this;
    }

    public ReviewResolveRequest withNote(@Nonnull String note) {
        this.note = Utils.checkNotNull(note, "note");
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
        ReviewResolveRequest other = (ReviewResolveRequest) o;
        return Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion)
                && Utils.enhancedDeepEquals(this.expectedRiskVersion, other.expectedRiskVersion)
                && Utils.enhancedDeepEquals(this.conclusion, other.conclusion)
                && Utils.enhancedDeepEquals(this.note, other.note);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(expectedVersion, expectedRiskVersion, conclusion, note);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewResolveRequest.class,
                "expectedVersion",
                expectedVersion,
                "expectedRiskVersion",
                expectedRiskVersion,
                "conclusion",
                conclusion,
                "note",
                note);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long expectedVersion;

        private long expectedRiskVersion;

        private Conclusion conclusion;

        private String note;

        private Builder() {
            // force use of static builder() method
        }

        public Builder expectedVersion(long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public Builder expectedRiskVersion(long expectedRiskVersion) {
            this.expectedRiskVersion = expectedRiskVersion;
            return this;
        }

        public Builder conclusion(@Nonnull Conclusion conclusion) {
            this.conclusion = Utils.checkNotNull(conclusion, "conclusion");
            return this;
        }

        public Builder note(@Nonnull String note) {
            this.note = Utils.checkNotNull(note, "note");
            return this;
        }

        public ReviewResolveRequest build() {
            return new ReviewResolveRequest(expectedVersion, expectedRiskVersion, conclusion, note);
        }
    }
}
