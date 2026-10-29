package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class PolicyPreviewCoverage {

    @JsonProperty("scanned")
    private long scanned;

    @JsonProperty("eligible")
    private long eligible;

    @JsonProperty("missing_score")
    private long missingScore;

    @JsonProperty("unsupported")
    private long unsupported;

    @JsonProperty("truncated")
    private boolean truncated;

    @JsonProperty("complete_retained_window")
    private boolean completeRetainedWindow;

    /**
     * Deleted, expired and uncaptured records cannot be reconstructed. Counts describe retained evidence
     * and provide no causal estimate of fraud prevented.
     */
    @JsonProperty("historical_population_known")
    private boolean historicalPopulationKnown;

    @JsonCreator
    public PolicyPreviewCoverage(
            @JsonProperty("scanned") long scanned,
            @JsonProperty("eligible") long eligible,
            @JsonProperty("missing_score") long missingScore,
            @JsonProperty("unsupported") long unsupported,
            @JsonProperty("truncated") boolean truncated,
            @JsonProperty("complete_retained_window") boolean completeRetainedWindow) {
        this.scanned = scanned;
        this.eligible = eligible;
        this.missingScore = missingScore;
        this.unsupported = unsupported;
        this.truncated = truncated;
        this.completeRetainedWindow = completeRetainedWindow;
        this.historicalPopulationKnown = Builder._SINGLETON_VALUE_HistoricalPopulationKnown.value();
    }

    public long scanned() {
        return this.scanned;
    }

    public long eligible() {
        return this.eligible;
    }

    public long missingScore() {
        return this.missingScore;
    }

    public long unsupported() {
        return this.unsupported;
    }

    public boolean truncated() {
        return this.truncated;
    }

    public boolean completeRetainedWindow() {
        return this.completeRetainedWindow;
    }

    /**
     * Deleted, expired and uncaptured records cannot be reconstructed. Counts describe retained evidence
     * and provide no causal estimate of fraud prevented.
     */
    public boolean historicalPopulationKnown() {
        return this.historicalPopulationKnown;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyPreviewCoverage withScanned(long scanned) {
        this.scanned = scanned;
        return this;
    }

    public PolicyPreviewCoverage withEligible(long eligible) {
        this.eligible = eligible;
        return this;
    }

    public PolicyPreviewCoverage withMissingScore(long missingScore) {
        this.missingScore = missingScore;
        return this;
    }

    public PolicyPreviewCoverage withUnsupported(long unsupported) {
        this.unsupported = unsupported;
        return this;
    }

    public PolicyPreviewCoverage withTruncated(boolean truncated) {
        this.truncated = truncated;
        return this;
    }

    public PolicyPreviewCoverage withCompleteRetainedWindow(boolean completeRetainedWindow) {
        this.completeRetainedWindow = completeRetainedWindow;
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
        PolicyPreviewCoverage other = (PolicyPreviewCoverage) o;
        return Utils.enhancedDeepEquals(this.scanned, other.scanned)
                && Utils.enhancedDeepEquals(this.eligible, other.eligible)
                && Utils.enhancedDeepEquals(this.missingScore, other.missingScore)
                && Utils.enhancedDeepEquals(this.unsupported, other.unsupported)
                && Utils.enhancedDeepEquals(this.truncated, other.truncated)
                && Utils.enhancedDeepEquals(this.completeRetainedWindow, other.completeRetainedWindow)
                && Utils.enhancedDeepEquals(this.historicalPopulationKnown, other.historicalPopulationKnown);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                scanned,
                eligible,
                missingScore,
                unsupported,
                truncated,
                completeRetainedWindow,
                historicalPopulationKnown);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyPreviewCoverage.class,
                "scanned",
                scanned,
                "eligible",
                eligible,
                "missingScore",
                missingScore,
                "unsupported",
                unsupported,
                "truncated",
                truncated,
                "completeRetainedWindow",
                completeRetainedWindow,
                "historicalPopulationKnown",
                historicalPopulationKnown);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long scanned;

        private long eligible;

        private long missingScore;

        private long unsupported;

        private boolean truncated;

        private boolean completeRetainedWindow;

        private Builder() {
            // force use of static builder() method
        }

        public Builder scanned(long scanned) {
            this.scanned = scanned;
            return this;
        }

        public Builder eligible(long eligible) {
            this.eligible = eligible;
            return this;
        }

        public Builder missingScore(long missingScore) {
            this.missingScore = missingScore;
            return this;
        }

        public Builder unsupported(long unsupported) {
            this.unsupported = unsupported;
            return this;
        }

        public Builder truncated(boolean truncated) {
            this.truncated = truncated;
            return this;
        }

        public Builder completeRetainedWindow(boolean completeRetainedWindow) {
            this.completeRetainedWindow = completeRetainedWindow;
            return this;
        }

        public PolicyPreviewCoverage build() {
            return new PolicyPreviewCoverage(
                    scanned, eligible, missingScore, unsupported, truncated, completeRetainedWindow);
        }

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_HistoricalPopulationKnown =
                new LazySingletonValue<>("historical_population_known", "false", new TypeReference<Boolean>() {});
    }
}
