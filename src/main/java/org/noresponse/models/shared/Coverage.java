package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * Coverage
 *
 * <p>Counts only; a zero denominator means no eligible retained records and does not imply a zero rate.
 */
public class Coverage {

    @JsonProperty("numerator")
    private long numerator;

    @JsonProperty("denominator")
    private long denominator;

    @JsonCreator
    public Coverage(@JsonProperty("numerator") long numerator, @JsonProperty("denominator") long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public long numerator() {
        return this.numerator;
    }

    public long denominator() {
        return this.denominator;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Coverage withNumerator(long numerator) {
        this.numerator = numerator;
        return this;
    }

    public Coverage withDenominator(long denominator) {
        this.denominator = denominator;
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
        Coverage other = (Coverage) o;
        return Utils.enhancedDeepEquals(this.numerator, other.numerator)
                && Utils.enhancedDeepEquals(this.denominator, other.denominator);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(numerator, denominator);
    }

    @Override
    public String toString() {
        return Utils.toString(Coverage.class, "numerator", numerator, "denominator", denominator);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long numerator;

        private long denominator;

        private Builder() {
            // force use of static builder() method
        }

        public Builder numerator(long numerator) {
            this.numerator = numerator;
            return this;
        }

        public Builder denominator(long denominator) {
            this.denominator = denominator;
            return this;
        }

        public Coverage build() {
            return new Coverage(numerator, denominator);
        }
    }
}
