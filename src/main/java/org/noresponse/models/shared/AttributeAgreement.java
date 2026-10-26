package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttributeAgreement
 *
 * <p>How far independent sources agree on one attribute: values is the number of distinct values
 * reported, support the tables and profiles reporting any, and agreement match (one value), partial
 * (the most reported value holds more than half the support) or mismatch. The values themselves are
 * not served.
 */
public class AttributeAgreement {

    @JsonProperty("agreement")
    private Agreement agreement;

    @JsonProperty("values")
    private long values;

    @JsonProperty("support")
    private long support;

    @JsonCreator
    public AttributeAgreement(
            @JsonProperty("agreement") @Nonnull Agreement agreement,
            @JsonProperty("values") long values,
            @JsonProperty("support") long support) {
        this.agreement = Optional.ofNullable(agreement)
                .orElseThrow(() -> new IllegalArgumentException("agreement cannot be null"));
        this.values = values;
        this.support = support;
    }

    public Agreement agreement() {
        return this.agreement;
    }

    public long values() {
        return this.values;
    }

    public long support() {
        return this.support;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttributeAgreement withAgreement(@Nonnull Agreement agreement) {
        this.agreement = Utils.checkNotNull(agreement, "agreement");
        return this;
    }

    public AttributeAgreement withValues(long values) {
        this.values = values;
        return this;
    }

    public AttributeAgreement withSupport(long support) {
        this.support = support;
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
        AttributeAgreement other = (AttributeAgreement) o;
        return Utils.enhancedDeepEquals(this.agreement, other.agreement)
                && Utils.enhancedDeepEquals(this.values, other.values)
                && Utils.enhancedDeepEquals(this.support, other.support);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(agreement, values, support);
    }

    @Override
    public String toString() {
        return Utils.toString(AttributeAgreement.class, "agreement", agreement, "values", values, "support", support);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Agreement agreement;

        private long values;

        private long support;

        private Builder() {
            // force use of static builder() method
        }

        public Builder agreement(@Nonnull Agreement agreement) {
            this.agreement = Utils.checkNotNull(agreement, "agreement");
            return this;
        }

        public Builder values(long values) {
            this.values = values;
            return this;
        }

        public Builder support(long support) {
            this.support = support;
            return this;
        }

        public AttributeAgreement build() {
            return new AttributeAgreement(agreement, values, support);
        }
    }
}
