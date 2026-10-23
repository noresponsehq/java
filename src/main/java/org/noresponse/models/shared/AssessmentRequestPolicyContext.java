package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AssessmentRequestPolicyContext
 *
 * <p>Optional policy inputs: region.
 */
public class AssessmentRequestPolicyContext {
    /**
     * ISO 3166-1 alpha-2 region of the user, as the provider determines it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("region")
    private String region;

    @JsonCreator
    public AssessmentRequestPolicyContext(@JsonProperty("region") @Nullable String region) {
        this.region = region;
    }

    public AssessmentRequestPolicyContext() {
        this(null);
    }

    /**
     * ISO 3166-1 alpha-2 region of the user, as the provider determines it.
     */
    public Optional<String> region() {
        return Optional.ofNullable(this.region);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * ISO 3166-1 alpha-2 region of the user, as the provider determines it.
     */
    public AssessmentRequestPolicyContext withRegion(@Nullable String region) {
        this.region = region;
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
        AssessmentRequestPolicyContext other = (AssessmentRequestPolicyContext) o;
        return Utils.enhancedDeepEquals(this.region, other.region);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(region);
    }

    @Override
    public String toString() {
        return Utils.toString(AssessmentRequestPolicyContext.class, "region", region);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String region;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * ISO 3166-1 alpha-2 region of the user, as the provider determines it.
         */
        public Builder region(@Nullable String region) {
            this.region = region;
            return this;
        }

        public AssessmentRequestPolicyContext build() {
            return new AssessmentRequestPolicyContext(region);
        }
    }
}
