package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AuthContextMfa
 *
 * <p>Normalized multi-factor evidence from a verified provider session.
 */
public class AuthContextMfa {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("present")
    private Boolean present;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("methods")
    private List<String> methods;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("age_seconds")
    private Long ageSeconds;

    @JsonCreator
    public AuthContextMfa(
            @JsonProperty("present") @Nullable Boolean present,
            @JsonProperty("methods") @Nullable List<String> methods,
            @JsonProperty("age_seconds") @Nullable Long ageSeconds) {
        this.present = present;
        this.methods = methods;
        this.ageSeconds = ageSeconds;
    }

    public AuthContextMfa() {
        this(null, null, null);
    }

    public Optional<Boolean> present() {
        return Optional.ofNullable(this.present);
    }

    public Optional<List<String>> methods() {
        return Optional.ofNullable(this.methods);
    }

    public Optional<Long> ageSeconds() {
        return Optional.ofNullable(this.ageSeconds);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuthContextMfa withPresent(@Nullable Boolean present) {
        this.present = present;
        return this;
    }

    public AuthContextMfa withMethods(@Nullable List<String> methods) {
        this.methods = methods;
        return this;
    }

    public AuthContextMfa withAgeSeconds(@Nullable Long ageSeconds) {
        this.ageSeconds = ageSeconds;
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
        AuthContextMfa other = (AuthContextMfa) o;
        return Utils.enhancedDeepEquals(this.present, other.present)
                && Utils.enhancedDeepEquals(this.methods, other.methods)
                && Utils.enhancedDeepEquals(this.ageSeconds, other.ageSeconds);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(present, methods, ageSeconds);
    }

    @Override
    public String toString() {
        return Utils.toString(AuthContextMfa.class, "present", present, "methods", methods, "ageSeconds", ageSeconds);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Boolean present;

        private List<String> methods;

        private Long ageSeconds;

        private Builder() {
            // force use of static builder() method
        }

        public Builder present(@Nullable Boolean present) {
            this.present = present;
            return this;
        }

        public Builder methods(@Nullable List<String> methods) {
            this.methods = methods;
            return this;
        }

        public Builder ageSeconds(@Nullable Long ageSeconds) {
            this.ageSeconds = ageSeconds;
            return this;
        }

        public AuthContextMfa build() {
            return new AuthContextMfa(present, methods, ageSeconds);
        }
    }
}
