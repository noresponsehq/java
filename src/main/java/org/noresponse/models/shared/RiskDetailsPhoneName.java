package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class RiskDetailsPhoneName {

    @JsonProperty("value")
    private String value;

    @JsonProperty("seen_by")
    private long seenBy;

    @JsonCreator
    public RiskDetailsPhoneName(@JsonProperty("value") @Nonnull String value, @JsonProperty("seen_by") long seenBy) {
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.seenBy = seenBy;
    }

    public String value() {
        return this.value;
    }

    public long seenBy() {
        return this.seenBy;
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskDetailsPhoneName withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    public RiskDetailsPhoneName withSeenBy(long seenBy) {
        this.seenBy = seenBy;
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
        RiskDetailsPhoneName other = (RiskDetailsPhoneName) o;
        return Utils.enhancedDeepEquals(this.value, other.value) && Utils.enhancedDeepEquals(this.seenBy, other.seenBy);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value, seenBy);
    }

    @Override
    public String toString() {
        return Utils.toString(RiskDetailsPhoneName.class, "value", value, "seenBy", seenBy);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String value;

        private long seenBy;

        private Builder() {
            // force use of static builder() method
        }

        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        public Builder seenBy(long seenBy) {
            this.seenBy = seenBy;
            return this;
        }

        public RiskDetailsPhoneName build() {
            return new RiskDetailsPhoneName(value, seenBy);
        }
    }
}
