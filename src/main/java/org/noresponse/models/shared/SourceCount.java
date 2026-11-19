package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class SourceCount {

    @JsonProperty("asked")
    private long asked;

    @JsonProperty("answered")
    private long answered;

    @JsonCreator
    public SourceCount(@JsonProperty("asked") long asked, @JsonProperty("answered") long answered) {
        this.asked = asked;
        this.answered = answered;
    }

    public long asked() {
        return this.asked;
    }

    public long answered() {
        return this.answered;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SourceCount withAsked(long asked) {
        this.asked = asked;
        return this;
    }

    public SourceCount withAnswered(long answered) {
        this.answered = answered;
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
        SourceCount other = (SourceCount) o;
        return Utils.enhancedDeepEquals(this.asked, other.asked)
                && Utils.enhancedDeepEquals(this.answered, other.answered);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(asked, answered);
    }

    @Override
    public String toString() {
        return Utils.toString(SourceCount.class, "asked", asked, "answered", answered);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long asked;

        private long answered;

        private Builder() {
            // force use of static builder() method
        }

        public Builder asked(long asked) {
            this.asked = asked;
            return this;
        }

        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        public SourceCount build() {
            return new SourceCount(asked, answered);
        }
    }
}
