package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PolicyEvaluationRequestCosts {

    @JsonProperty("legitimate")
    private Cost legitimate;

    @JsonProperty("abuse")
    private Cost abuse;

    @JsonCreator
    public PolicyEvaluationRequestCosts(
            @JsonProperty("legitimate") @Nonnull Cost legitimate, @JsonProperty("abuse") @Nonnull Cost abuse) {
        this.legitimate = Optional.ofNullable(legitimate)
                .orElseThrow(() -> new IllegalArgumentException("legitimate cannot be null"));
        this.abuse = Optional.ofNullable(abuse).orElseThrow(() -> new IllegalArgumentException("abuse cannot be null"));
    }

    public Cost legitimate() {
        return this.legitimate;
    }

    public Cost abuse() {
        return this.abuse;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationRequestCosts withLegitimate(@Nonnull Cost legitimate) {
        this.legitimate = Utils.checkNotNull(legitimate, "legitimate");
        return this;
    }

    public PolicyEvaluationRequestCosts withAbuse(@Nonnull Cost abuse) {
        this.abuse = Utils.checkNotNull(abuse, "abuse");
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
        PolicyEvaluationRequestCosts other = (PolicyEvaluationRequestCosts) o;
        return Utils.enhancedDeepEquals(this.legitimate, other.legitimate)
                && Utils.enhancedDeepEquals(this.abuse, other.abuse);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(legitimate, abuse);
    }

    @Override
    public String toString() {
        return Utils.toString(PolicyEvaluationRequestCosts.class, "legitimate", legitimate, "abuse", abuse);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Cost legitimate;

        private Cost abuse;

        private Builder() {
            // force use of static builder() method
        }

        public Builder legitimate(@Nonnull Cost legitimate) {
            this.legitimate = Utils.checkNotNull(legitimate, "legitimate");
            return this;
        }

        public Builder abuse(@Nonnull Cost abuse) {
            this.abuse = Utils.checkNotNull(abuse, "abuse");
            return this;
        }

        public PolicyEvaluationRequestCosts build() {
            return new PolicyEvaluationRequestCosts(legitimate, abuse);
        }
    }
}
