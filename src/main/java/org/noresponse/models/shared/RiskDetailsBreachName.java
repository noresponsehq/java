package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskDetailsBreachName {

    @JsonProperty("value")
    private String value;

    @JsonProperty("seen_in")
    private long seenIn;

    @JsonCreator
    public RiskDetailsBreachName(@JsonProperty("value") @Nonnull String value, @JsonProperty("seen_in") long seenIn) {
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.seenIn = seenIn;
    }

    public String value() {
        return this.value;
    }

    public long seenIn() {
        return this.seenIn;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskDetailsBreachName withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    public RiskDetailsBreachName withSeenIn(long seenIn) {
        this.seenIn = seenIn;
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
        RiskDetailsBreachName other = (RiskDetailsBreachName) o;
        return Utils.enhancedDeepEquals(this.value, other.value) && Utils.enhancedDeepEquals(this.seenIn, other.seenIn);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value, seenIn);
    }

    @Override
    public String toString() {
        return Utils.toString(RiskDetailsBreachName.class, "value", value, "seenIn", seenIn);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String value;

        private long seenIn;

        private Builder() {
            // force use of static builder() method
        }

        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        public Builder seenIn(long seenIn) {
            this.seenIn = seenIn;
            return this;
        }

        public RiskDetailsBreachName build() {
            return new RiskDetailsBreachName(value, seenIn);
        }
    }
}
