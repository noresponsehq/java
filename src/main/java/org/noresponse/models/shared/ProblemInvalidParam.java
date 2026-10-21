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

public class ProblemInvalidParam {

    @JsonProperty("field")
    private String field;

    @JsonProperty("reason")
    private String reason;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("example")
    private String example;

    @JsonCreator
    public ProblemInvalidParam(
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("reason") @Nonnull String reason,
            @JsonProperty("example") @Nullable String example) {
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
        this.example = example;
    }

    public ProblemInvalidParam(@Nonnull String field, @Nonnull String reason) {
        this(field, reason, null);
    }

    public String field() {
        return this.field;
    }

    public String reason() {
        return this.reason;
    }

    public Optional<String> example() {
        return Optional.ofNullable(this.example);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProblemInvalidParam withField(@Nonnull String field) {
        this.field = Utils.checkNotNull(field, "field");
        return this;
    }

    public ProblemInvalidParam withReason(@Nonnull String reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
        return this;
    }

    public ProblemInvalidParam withExample(@Nullable String example) {
        this.example = example;
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
        ProblemInvalidParam other = (ProblemInvalidParam) o;
        return Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.reason, other.reason)
                && Utils.enhancedDeepEquals(this.example, other.example);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(field, reason, example);
    }

    @Override
    public String toString() {
        return Utils.toString(ProblemInvalidParam.class, "field", field, "reason", reason, "example", example);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String field;

        private String reason;

        private String example;

        private Builder() {
            // force use of static builder() method
        }

        public Builder field(@Nonnull String field) {
            this.field = Utils.checkNotNull(field, "field");
            return this;
        }

        public Builder reason(@Nonnull String reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        public Builder example(@Nullable String example) {
            this.example = example;
            return this;
        }

        public ProblemInvalidParam build() {
            return new ProblemInvalidParam(field, reason, example);
        }
    }
}
