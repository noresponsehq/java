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
 * PolicyEvaluationList
 *
 * <p>Retained jobs, newest first. Follow next_before for another page.
 */
public class PolicyEvaluationList {

    @JsonProperty("items")
    private List<PolicyEvaluation> items;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_before")
    private String nextBefore;

    @JsonCreator
    public PolicyEvaluationList(
            @JsonProperty("items") @Nonnull List<PolicyEvaluation> items,
            @JsonProperty("next_before") @Nullable String nextBefore) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextBefore = nextBefore;
    }

    public PolicyEvaluationList(@Nonnull List<PolicyEvaluation> items) {
        this(items, null);
    }

    public List<PolicyEvaluation> items() {
        return this.items;
    }

    public Optional<String> nextBefore() {
        return Optional.ofNullable(this.nextBefore);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationList withItems(@Nonnull List<PolicyEvaluation> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public PolicyEvaluationList withNextBefore(@Nullable String nextBefore) {
        this.nextBefore = nextBefore;
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
        PolicyEvaluationList other = (PolicyEvaluationList) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextBefore, other.nextBefore);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextBefore);
    }

    @Override
    public String toString() {
        return Utils.toString(PolicyEvaluationList.class, "items", items, "nextBefore", nextBefore);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<PolicyEvaluation> items;

        private String nextBefore;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<PolicyEvaluation> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder nextBefore(@Nullable String nextBefore) {
            this.nextBefore = nextBefore;
            return this;
        }

        public PolicyEvaluationList build() {
            return new PolicyEvaluationList(items, nextBefore);
        }
    }
}
