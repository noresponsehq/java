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
 * ScreeningMatch
 *
 * <p>How a profile was matched to the person: the state its evidence earns, and the evidence.
 */
public class ScreeningMatch {
    /**
     * How sure the screening is that a profile is the person's, from its evidence: confirmed is a handle
     * on the person row, or two or more hard identifiers (email, phone, a supplied handle, or a fact an
     * existing source returned); probable is one identifier beyond the name; possible is the name alone.
     */
    @JsonProperty("state")
    private ProfileMatchState state;

    @JsonProperty("evidence")
    private List<ScreeningMatchEvidence> evidence;

    @JsonCreator
    public ScreeningMatch(
            @JsonProperty("state") @Nonnull ProfileMatchState state,
            @JsonProperty("evidence") @Nonnull List<ScreeningMatchEvidence> evidence) {
        this.state = Optional.ofNullable(state).orElseThrow(() -> new IllegalArgumentException("state cannot be null"));
        this.evidence = Optional.ofNullable(evidence)
                .orElseThrow(() -> new IllegalArgumentException("evidence cannot be null"));
    }

    /**
     * How sure the screening is that a profile is the person's, from its evidence: confirmed is a handle
     * on the person row, or two or more hard identifiers (email, phone, a supplied handle, or a fact an
     * existing source returned); probable is one identifier beyond the name; possible is the name alone.
     */
    public ProfileMatchState state() {
        return this.state;
    }

    public List<ScreeningMatchEvidence> evidence() {
        return this.evidence;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How sure the screening is that a profile is the person's, from its evidence: confirmed is a handle
     * on the person row, or two or more hard identifiers (email, phone, a supplied handle, or a fact an
     * existing source returned); probable is one identifier beyond the name; possible is the name alone.
     */
    public ScreeningMatch withState(@Nonnull ProfileMatchState state) {
        this.state = Utils.checkNotNull(state, "state");
        return this;
    }

    public ScreeningMatch withEvidence(@Nonnull List<ScreeningMatchEvidence> evidence) {
        this.evidence = Utils.checkNotNull(evidence, "evidence");
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
        ScreeningMatch other = (ScreeningMatch) o;
        return Utils.enhancedDeepEquals(this.state, other.state)
                && Utils.enhancedDeepEquals(this.evidence, other.evidence);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(state, evidence);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningMatch.class, "state", state, "evidence", evidence);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ProfileMatchState state;

        private List<ScreeningMatchEvidence> evidence;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How sure the screening is that a profile is the person's, from its evidence: confirmed is a handle
         * on the person row, or two or more hard identifiers (email, phone, a supplied handle, or a fact an
         * existing source returned); probable is one identifier beyond the name; possible is the name alone.
         */
        public Builder state(@Nonnull ProfileMatchState state) {
            this.state = Utils.checkNotNull(state, "state");
            return this;
        }

        public Builder evidence(@Nonnull List<ScreeningMatchEvidence> evidence) {
            this.evidence = Utils.checkNotNull(evidence, "evidence");
            return this;
        }

        public ScreeningMatch build() {
            return new ScreeningMatch(state, evidence);
        }
    }
}
