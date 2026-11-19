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

public class ScreeningLookupAnswer {
    /**
     * The id of the connector asked: breach for the breach search.
     */
    @JsonProperty("connector")
    private String connector;

    @JsonProperty("identifier")
    private Identifier identifier;

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    @JsonProperty("outcome")
    private SignalOutcome outcome;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("answered_at")
    private String answeredAt;

    @JsonCreator
    public ScreeningLookupAnswer(
            @JsonProperty("connector") @Nonnull String connector,
            @JsonProperty("identifier") @Nonnull Identifier identifier,
            @JsonProperty("outcome") @Nonnull SignalOutcome outcome,
            @JsonProperty("answered_at") @Nullable String answeredAt) {
        this.connector = Optional.ofNullable(connector)
                .orElseThrow(() -> new IllegalArgumentException("connector cannot be null"));
        this.identifier = Optional.ofNullable(identifier)
                .orElseThrow(() -> new IllegalArgumentException("identifier cannot be null"));
        this.outcome =
                Optional.ofNullable(outcome).orElseThrow(() -> new IllegalArgumentException("outcome cannot be null"));
        this.answeredAt = answeredAt;
    }

    public ScreeningLookupAnswer(
            @Nonnull String connector, @Nonnull Identifier identifier, @Nonnull SignalOutcome outcome) {
        this(connector, identifier, outcome, null);
    }

    /**
     * The id of the connector asked: breach for the breach search.
     */
    public String connector() {
        return this.connector;
    }

    public Identifier identifier() {
        return this.identifier;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public SignalOutcome outcome() {
        return this.outcome;
    }

    public Optional<String> answeredAt() {
        return Optional.ofNullable(this.answeredAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The id of the connector asked: breach for the breach search.
     */
    public ScreeningLookupAnswer withConnector(@Nonnull String connector) {
        this.connector = Utils.checkNotNull(connector, "connector");
        return this;
    }

    public ScreeningLookupAnswer withIdentifier(@Nonnull Identifier identifier) {
        this.identifier = Utils.checkNotNull(identifier, "identifier");
        return this;
    }

    /**
     * Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or
     * inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.
     */
    public ScreeningLookupAnswer withOutcome(@Nonnull SignalOutcome outcome) {
        this.outcome = Utils.checkNotNull(outcome, "outcome");
        return this;
    }

    public ScreeningLookupAnswer withAnsweredAt(@Nullable String answeredAt) {
        this.answeredAt = answeredAt;
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
        ScreeningLookupAnswer other = (ScreeningLookupAnswer) o;
        return Utils.enhancedDeepEquals(this.connector, other.connector)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.outcome, other.outcome)
                && Utils.enhancedDeepEquals(this.answeredAt, other.answeredAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(connector, identifier, outcome, answeredAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningLookupAnswer.class,
                "connector",
                connector,
                "identifier",
                identifier,
                "outcome",
                outcome,
                "answeredAt",
                answeredAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String connector;

        private Identifier identifier;

        private SignalOutcome outcome;

        private String answeredAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The id of the connector asked: breach for the breach search.
         */
        public Builder connector(@Nonnull String connector) {
            this.connector = Utils.checkNotNull(connector, "connector");
            return this;
        }

        public Builder identifier(@Nonnull Identifier identifier) {
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

        public Builder answeredAt(@Nullable String answeredAt) {
            this.answeredAt = answeredAt;
            return this;
        }

        public ScreeningLookupAnswer build() {
            return new ScreeningLookupAnswer(connector, identifier, outcome, answeredAt);
        }
    }
}
