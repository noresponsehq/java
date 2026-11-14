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
 * ScreeningFinding
 *
 * <p>Something the analyser flagged. band is capped by the strongest match among the profiles of its
 * evidence: possible at most moderate, probable at most high. capped_from is the band the analyser
 * gave when the cap lowered it.
 */
public class ScreeningFinding {

    @JsonProperty("id")
    private String id;

    /**
     * What a finding is about: a direct threat to the principal or the group, intent to harm without a
     * named target, fixation on the principal, a grievance against the host, research into the venue,
     * route or security, weapons tied to any of these, disruption or protest planning toward this group,
     * violent extremist content or identification, prior harassment or stalking of staff or principals, or
     * posting the principal's addresses or movements.
     */
    @JsonProperty("category")
    private FindingCategory category;

    /**
     * How severe a finding is, most severe first.
     */
    @JsonProperty("band")
    private FindingBand band;

    /**
     * How severe a finding is, most severe first.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("capped_from")
    private FindingBand cappedFrom;

    @JsonProperty("confidence")
    private double confidence;

    @JsonProperty("rationale")
    private String rationale;

    /**
     * The exact words of the first evidence item that the finding rests on, as the analyser quoted them.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("quote")
    private String quote;

    /**
     * The posts and media that support the finding.
     */
    @JsonProperty("evidence")
    private List<String> evidence;

    /**
     * What the analyser saw in each image among evidence that the finding rests on. Unlike quote, an
     * observation cannot be checked against the record: judge it against the image at the media item's
     * permalink.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("media_observations")
    private List<ScreeningMediaObservation> mediaObservations;

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    @JsonProperty("analyser")
    private String analyser;

    @JsonProperty("model")
    private String model;

    /**
     * What the analysis cost, in US cents.
     */
    @JsonProperty("spend_minor")
    private long spendMinor;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonCreator
    public ScreeningFinding(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("category") @Nonnull FindingCategory category,
            @JsonProperty("band") @Nonnull FindingBand band,
            @JsonProperty("capped_from") @Nullable FindingBand cappedFrom,
            @JsonProperty("confidence") double confidence,
            @JsonProperty("rationale") @Nonnull String rationale,
            @JsonProperty("quote") @Nullable String quote,
            @JsonProperty("evidence") @Nonnull List<String> evidence,
            @JsonProperty("media_observations") @Nullable List<ScreeningMediaObservation> mediaObservations,
            @JsonProperty("analyser") @Nonnull String analyser,
            @JsonProperty("model") @Nonnull String model,
            @JsonProperty("spend_minor") long spendMinor,
            @JsonProperty("created_at") @Nonnull String createdAt) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.category = Optional.ofNullable(category)
                .orElseThrow(() -> new IllegalArgumentException("category cannot be null"));
        this.band = Optional.ofNullable(band).orElseThrow(() -> new IllegalArgumentException("band cannot be null"));
        this.cappedFrom = cappedFrom;
        this.confidence = confidence;
        this.rationale = Optional.ofNullable(rationale)
                .orElseThrow(() -> new IllegalArgumentException("rationale cannot be null"));
        this.quote = quote;
        this.evidence = Optional.ofNullable(evidence)
                .orElseThrow(() -> new IllegalArgumentException("evidence cannot be null"));
        this.mediaObservations = mediaObservations;
        this.analyser = Optional.ofNullable(analyser)
                .orElseThrow(() -> new IllegalArgumentException("analyser cannot be null"));
        this.model = Optional.ofNullable(model).orElseThrow(() -> new IllegalArgumentException("model cannot be null"));
        this.spendMinor = spendMinor;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
    }

    public ScreeningFinding(
            @Nonnull String id,
            @Nonnull FindingCategory category,
            @Nonnull FindingBand band,
            double confidence,
            @Nonnull String rationale,
            @Nonnull List<String> evidence,
            @Nonnull String analyser,
            @Nonnull String model,
            long spendMinor,
            @Nonnull String createdAt) {
        this(
                id,
                category,
                band,
                null,
                confidence,
                rationale,
                null,
                evidence,
                null,
                analyser,
                model,
                spendMinor,
                createdAt);
    }

    public String id() {
        return this.id;
    }

    /**
     * What a finding is about: a direct threat to the principal or the group, intent to harm without a
     * named target, fixation on the principal, a grievance against the host, research into the venue,
     * route or security, weapons tied to any of these, disruption or protest planning toward this group,
     * violent extremist content or identification, prior harassment or stalking of staff or principals, or
     * posting the principal's addresses or movements.
     */
    public FindingCategory category() {
        return this.category;
    }

    /**
     * How severe a finding is, most severe first.
     */
    public FindingBand band() {
        return this.band;
    }

    /**
     * How severe a finding is, most severe first.
     */
    public Optional<FindingBand> cappedFrom() {
        return Optional.ofNullable(this.cappedFrom);
    }

    public double confidence() {
        return this.confidence;
    }

    public String rationale() {
        return this.rationale;
    }

    /**
     * The exact words of the first evidence item that the finding rests on, as the analyser quoted them.
     */
    public Optional<String> quote() {
        return Optional.ofNullable(this.quote);
    }

    /**
     * The posts and media that support the finding.
     */
    public List<String> evidence() {
        return this.evidence;
    }

    /**
     * What the analyser saw in each image among evidence that the finding rests on. Unlike quote, an
     * observation cannot be checked against the record: judge it against the image at the media item's
     * permalink.
     */
    public Optional<List<ScreeningMediaObservation>> mediaObservations() {
        return Optional.ofNullable(this.mediaObservations);
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public String analyser() {
        return this.analyser;
    }

    public String model() {
        return this.model;
    }

    /**
     * What the analysis cost, in US cents.
     */
    public long spendMinor() {
        return this.spendMinor;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningFinding withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * What a finding is about: a direct threat to the principal or the group, intent to harm without a
     * named target, fixation on the principal, a grievance against the host, research into the venue,
     * route or security, weapons tied to any of these, disruption or protest planning toward this group,
     * violent extremist content or identification, prior harassment or stalking of staff or principals, or
     * posting the principal's addresses or movements.
     */
    public ScreeningFinding withCategory(@Nonnull FindingCategory category) {
        this.category = Utils.checkNotNull(category, "category");
        return this;
    }

    /**
     * How severe a finding is, most severe first.
     */
    public ScreeningFinding withBand(@Nonnull FindingBand band) {
        this.band = Utils.checkNotNull(band, "band");
        return this;
    }

    /**
     * How severe a finding is, most severe first.
     */
    public ScreeningFinding withCappedFrom(@Nullable FindingBand cappedFrom) {
        this.cappedFrom = cappedFrom;
        return this;
    }

    public ScreeningFinding withConfidence(double confidence) {
        this.confidence = confidence;
        return this;
    }

    public ScreeningFinding withRationale(@Nonnull String rationale) {
        this.rationale = Utils.checkNotNull(rationale, "rationale");
        return this;
    }

    /**
     * The exact words of the first evidence item that the finding rests on, as the analyser quoted them.
     */
    public ScreeningFinding withQuote(@Nullable String quote) {
        this.quote = quote;
        return this;
    }

    /**
     * The posts and media that support the finding.
     */
    public ScreeningFinding withEvidence(@Nonnull List<String> evidence) {
        this.evidence = Utils.checkNotNull(evidence, "evidence");
        return this;
    }

    /**
     * What the analyser saw in each image among evidence that the finding rests on. Unlike quote, an
     * observation cannot be checked against the record: judge it against the image at the media item's
     * permalink.
     */
    public ScreeningFinding withMediaObservations(@Nullable List<ScreeningMediaObservation> mediaObservations) {
        this.mediaObservations = mediaObservations;
        return this;
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public ScreeningFinding withAnalyser(@Nonnull String analyser) {
        this.analyser = Utils.checkNotNull(analyser, "analyser");
        return this;
    }

    public ScreeningFinding withModel(@Nonnull String model) {
        this.model = Utils.checkNotNull(model, "model");
        return this;
    }

    /**
     * What the analysis cost, in US cents.
     */
    public ScreeningFinding withSpendMinor(long spendMinor) {
        this.spendMinor = spendMinor;
        return this;
    }

    public ScreeningFinding withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
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
        ScreeningFinding other = (ScreeningFinding) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.category, other.category)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.cappedFrom, other.cappedFrom)
                && Utils.enhancedDeepEquals(this.confidence, other.confidence)
                && Utils.enhancedDeepEquals(this.rationale, other.rationale)
                && Utils.enhancedDeepEquals(this.quote, other.quote)
                && Utils.enhancedDeepEquals(this.evidence, other.evidence)
                && Utils.enhancedDeepEquals(this.mediaObservations, other.mediaObservations)
                && Utils.enhancedDeepEquals(this.analyser, other.analyser)
                && Utils.enhancedDeepEquals(this.model, other.model)
                && Utils.enhancedDeepEquals(this.spendMinor, other.spendMinor)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                category,
                band,
                cappedFrom,
                confidence,
                rationale,
                quote,
                evidence,
                mediaObservations,
                analyser,
                model,
                spendMinor,
                createdAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningFinding.class,
                "id",
                id,
                "category",
                category,
                "band",
                band,
                "cappedFrom",
                cappedFrom,
                "confidence",
                confidence,
                "rationale",
                rationale,
                "quote",
                quote,
                "evidence",
                evidence,
                "mediaObservations",
                mediaObservations,
                "analyser",
                analyser,
                "model",
                model,
                "spendMinor",
                spendMinor,
                "createdAt",
                createdAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private FindingCategory category;

        private FindingBand band;

        private FindingBand cappedFrom;

        private double confidence;

        private String rationale;

        private String quote;

        private List<String> evidence;

        private List<ScreeningMediaObservation> mediaObservations;

        private String analyser;

        private String model;

        private long spendMinor;

        private String createdAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * What a finding is about: a direct threat to the principal or the group, intent to harm without a
         * named target, fixation on the principal, a grievance against the host, research into the venue,
         * route or security, weapons tied to any of these, disruption or protest planning toward this group,
         * violent extremist content or identification, prior harassment or stalking of staff or principals, or
         * posting the principal's addresses or movements.
         */
        public Builder category(@Nonnull FindingCategory category) {
            this.category = Utils.checkNotNull(category, "category");
            return this;
        }

        /**
         * How severe a finding is, most severe first.
         */
        public Builder band(@Nonnull FindingBand band) {
            this.band = Utils.checkNotNull(band, "band");
            return this;
        }

        /**
         * How severe a finding is, most severe first.
         */
        public Builder cappedFrom(@Nullable FindingBand cappedFrom) {
            this.cappedFrom = cappedFrom;
            return this;
        }

        public Builder confidence(double confidence) {
            this.confidence = confidence;
            return this;
        }

        public Builder rationale(@Nonnull String rationale) {
            this.rationale = Utils.checkNotNull(rationale, "rationale");
            return this;
        }

        /**
         * The exact words of the first evidence item that the finding rests on, as the analyser quoted them.
         */
        public Builder quote(@Nullable String quote) {
            this.quote = quote;
            return this;
        }

        /**
         * The posts and media that support the finding.
         */
        public Builder evidence(@Nonnull List<String> evidence) {
            this.evidence = Utils.checkNotNull(evidence, "evidence");
            return this;
        }

        /**
         * What the analyser saw in each image among evidence that the finding rests on. Unlike quote, an
         * observation cannot be checked against the record: judge it against the image at the media item's
         * permalink.
         */
        public Builder mediaObservations(@Nullable List<ScreeningMediaObservation> mediaObservations) {
            this.mediaObservations = mediaObservations;
            return this;
        }

        /**
         * The resolver, collector or analyser that produced a record, by its catalogue name.
         */
        public Builder analyser(@Nonnull String analyser) {
            this.analyser = Utils.checkNotNull(analyser, "analyser");
            return this;
        }

        public Builder model(@Nonnull String model) {
            this.model = Utils.checkNotNull(model, "model");
            return this;
        }

        /**
         * What the analysis cost, in US cents.
         */
        public Builder spendMinor(long spendMinor) {
            this.spendMinor = spendMinor;
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public ScreeningFinding build() {
            return new ScreeningFinding(
                    id,
                    category,
                    band,
                    cappedFrom,
                    confidence,
                    rationale,
                    quote,
                    evidence,
                    mediaObservations,
                    analyser,
                    model,
                    spendMinor,
                    createdAt);
        }
    }
}
