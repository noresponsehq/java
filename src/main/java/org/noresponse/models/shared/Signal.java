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

public class Signal {
    /**
     * Signal kind, for example account_presence or breach_exposure. New kinds may appear; ignore any you
     * do not know.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.
     */
    @JsonProperty("identifier")
    private IdentifierKind identifier;

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    @JsonProperty("outcome")
    private SignalOutcome outcome;

    /**
     * Opaque, tenant-scoped projection of the source that produced a signal. It stays stable for your
     * tenant while the projection key is unchanged and reveals no upstream provider, feed, breach table or
     * source record.
     */
    @JsonProperty("source_id")
    private String sourceId;

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reason")
    private FailureReason reason;

    /**
     * Opaque pointer to evidence NoResponse holds for this signal. It carries no breach value, credential
     * or page body.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("evidence_ref")
    private String evidenceRef;

    /**
     * When the evidence was first observed.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("observed_at")
    private String observedAt;

    /**
     * When the source was last queried for this identifier.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_checked_at")
    private String lastCheckedAt;

    /**
     * Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.
     */
    @JsonProperty("freshness")
    private Freshness freshness;

    /**
     * Facts about the number only: line type, carrier and country. The upstream provider, its scores and
     * its verdicts stay internal.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("enrichment")
    private PhoneEnrichment enrichment;

    @JsonCreator
    public Signal(
            @JsonProperty("type") @Nonnull String type,
            @JsonProperty("identifier") @Nonnull IdentifierKind identifier,
            @JsonProperty("outcome") @Nonnull SignalOutcome outcome,
            @JsonProperty("source_id") @Nonnull String sourceId,
            @JsonProperty("reason") @Nullable FailureReason reason,
            @JsonProperty("evidence_ref") @Nullable String evidenceRef,
            @JsonProperty("observed_at") @Nullable String observedAt,
            @JsonProperty("last_checked_at") @Nullable String lastCheckedAt,
            @JsonProperty("freshness") @Nonnull Freshness freshness,
            @JsonProperty("enrichment") @Nullable PhoneEnrichment enrichment) {
        this.type = Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("type cannot be null"));
        this.identifier = Optional.ofNullable(identifier)
                .orElseThrow(() -> new IllegalArgumentException("identifier cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.sourceId = Optional.ofNullable(sourceId)
                .orElseThrow(() -> new IllegalArgumentException("sourceId cannot be null"));
        this.reason = reason;
        this.evidenceRef = evidenceRef;
        this.observedAt = observedAt;
        this.lastCheckedAt = lastCheckedAt;
        this.freshness = Optional.ofNullable(freshness)
                .orElseThrow(() -> new IllegalArgumentException("freshness cannot be null"));
        this.enrichment = enrichment;
    }

    public Signal(
            @Nonnull String type,
            @Nonnull IdentifierKind identifier,
            @Nonnull SignalOutcome outcome,
            @Nonnull String sourceId,
            @Nonnull Freshness freshness) {
        this(type, identifier, outcome, sourceId, null, null, null, null, freshness, null);
    }

    /**
     * Signal kind, for example account_presence or breach_exposure. New kinds may appear; ignore any you
     * do not know.
     */
    public String type() {
        return this.type;
    }

    /**
     * Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.
     */
    public IdentifierKind identifier() {
        return this.identifier;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public SignalOutcome outcome() {
        return this.outcome;
    }

    /**
     * Opaque, tenant-scoped projection of the source that produced a signal. It stays stable for your
     * tenant while the projection key is unchanged and reveals no upstream provider, feed, breach table or
     * source record.
     */
    public String sourceId() {
        return this.sourceId;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public Optional<FailureReason> reason() {
        return Optional.ofNullable(this.reason);
    }

    /**
     * Opaque pointer to evidence NoResponse holds for this signal. It carries no breach value, credential
     * or page body.
     */
    public Optional<String> evidenceRef() {
        return Optional.ofNullable(this.evidenceRef);
    }

    /**
     * When the evidence was first observed.
     */
    public Optional<String> observedAt() {
        return Optional.ofNullable(this.observedAt);
    }

    /**
     * When the source was last queried for this identifier.
     */
    public Optional<String> lastCheckedAt() {
        return Optional.ofNullable(this.lastCheckedAt);
    }

    /**
     * Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.
     */
    public Freshness freshness() {
        return this.freshness;
    }

    /**
     * Facts about the number only: line type, carrier and country. The upstream provider, its scores and
     * its verdicts stay internal.
     */
    public Optional<PhoneEnrichment> enrichment() {
        return Optional.ofNullable(this.enrichment);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Signal kind, for example account_presence or breach_exposure. New kinds may appear; ignore any you
     * do not know.
     */
    public Signal withType(@Nonnull String type) {
        this.type = Utils.checkNotNull(type, "type");
        return this;
    }

    /**
     * Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.
     */
    public Signal withIdentifier(@Nonnull IdentifierKind identifier) {
        this.identifier = Utils.checkNotNull(identifier, "identifier");
        return this;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public Signal withOutcome(@Nonnull SignalOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    /**
     * Opaque, tenant-scoped projection of the source that produced a signal. It stays stable for your
     * tenant while the projection key is unchanged and reveals no upstream provider, feed, breach table or
     * source record.
     */
    public Signal withSourceId(@Nonnull String sourceId) {
        this.sourceId = Utils.checkNotNull(sourceId, "sourceId");
        return this;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public Signal withReason(@Nullable FailureReason reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Opaque pointer to evidence NoResponse holds for this signal. It carries no breach value, credential
     * or page body.
     */
    public Signal withEvidenceRef(@Nullable String evidenceRef) {
        this.evidenceRef = evidenceRef;
        return this;
    }

    /**
     * When the evidence was first observed.
     */
    public Signal withObservedAt(@Nullable String observedAt) {
        this.observedAt = observedAt;
        return this;
    }

    /**
     * When the source was last queried for this identifier.
     */
    public Signal withLastCheckedAt(@Nullable String lastCheckedAt) {
        this.lastCheckedAt = lastCheckedAt;
        return this;
    }

    /**
     * Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.
     */
    public Signal withFreshness(@Nonnull Freshness freshness) {
        this.freshness = Utils.checkNotNull(freshness, "freshness");
        return this;
    }

    /**
     * Facts about the number only: line type, carrier and country. The upstream provider, its scores and
     * its verdicts stay internal.
     */
    public Signal withEnrichment(@Nullable PhoneEnrichment enrichment) {
        this.enrichment = enrichment;
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
        Signal other = (Signal) o;
        return Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.sourceId, other.sourceId)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.evidenceRef, other.evidenceRef)
                && Utils.enhancedDeepEquals(this.observedAt, other.observedAt)
                && Utils.enhancedDeepEquals(this.lastCheckedAt, other.lastCheckedAt)
                && Utils.enhancedDeepEquals(this.freshness, other.freshness)
                && Utils.enhancedDeepEquals(this.enrichment, other.enrichment);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                type,
                identifier,
                outcome,
                sourceId,
                reason,
                evidenceRef,
                observedAt,
                lastCheckedAt,
                freshness,
                enrichment);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Signal.class,
                "type",
                type,
                "identifier",
                identifier,
                "outcome",
                outcome,
                "sourceId",
                sourceId,
                "reason",
                reason,
                "evidenceRef",
                evidenceRef,
                "observedAt",
                observedAt,
                "lastCheckedAt",
                lastCheckedAt,
                "freshness",
                freshness,
                "enrichment",
                enrichment);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String type;

        private IdentifierKind identifier;

        private SignalOutcome outcome;

        private String sourceId;

        private FailureReason reason;

        private String evidenceRef;

        private String observedAt;

        private String lastCheckedAt;

        private Freshness freshness;

        private PhoneEnrichment enrichment;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Signal kind, for example account_presence or breach_exposure. New kinds may appear; ignore any you
         * do not know.
         */
        public Builder type(@Nonnull String type) {
            this.type = Utils.checkNotNull(type, "type");
            return this;
        }

        /**
         * Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.
         */
        public Builder identifier(@Nonnull IdentifierKind identifier) {
            this.identifier = Utils.checkNotNull(identifier, "identifier");
            return this;
        }

        /**
         * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
         * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
         */
        public Builder outcome(@Nonnull SignalOutcome outcome) {
            this.outcome = Utils.checkNotNull(outcome, "outcome");
            return this;
        }

        /**
         * Opaque, tenant-scoped projection of the source that produced a signal. It stays stable for your
         * tenant while the projection key is unchanged and reveals no upstream provider, feed, breach table or
         * source record.
         */
        public Builder sourceId(@Nonnull String sourceId) {
            this.sourceId = Utils.checkNotNull(sourceId, "sourceId");
            return this;
        }

        /**
         * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
         * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
         * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
         */
        public Builder reason(@Nullable FailureReason reason) {
            this.reason = reason;
            return this;
        }

        /**
         * Opaque pointer to evidence NoResponse holds for this signal. It carries no breach value, credential
         * or page body.
         */
        public Builder evidenceRef(@Nullable String evidenceRef) {
            this.evidenceRef = evidenceRef;
            return this;
        }

        /**
         * When the evidence was first observed.
         */
        public Builder observedAt(@Nullable String observedAt) {
            this.observedAt = observedAt;
            return this;
        }

        /**
         * When the source was last queried for this identifier.
         */
        public Builder lastCheckedAt(@Nullable String lastCheckedAt) {
            this.lastCheckedAt = lastCheckedAt;
            return this;
        }

        /**
         * Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.
         */
        public Builder freshness(@Nonnull Freshness freshness) {
            this.freshness = Utils.checkNotNull(freshness, "freshness");
            return this;
        }

        /**
         * Facts about the number only: line type, carrier and country. The upstream provider, its scores and
         * its verdicts stay internal.
         */
        public Builder enrichment(@Nullable PhoneEnrichment enrichment) {
            this.enrichment = enrichment;
            return this;
        }

        public Signal build() {
            return new Signal(
                    type,
                    identifier,
                    outcome,
                    sourceId,
                    reason,
                    evidenceRef,
                    observedAt,
                    lastCheckedAt,
                    freshness,
                    enrichment);
        }
    }
}
