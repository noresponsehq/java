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
 * Consistency
 *
 * <p>Cross-identifier agreement, computed from facts only. Every member is optional; absent makes no
 * claim.
 */
public class Consistency {
    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private ConsistencyValue name;

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private ConsistencyValue phone;

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("ip")
    private ConsistencyValue ip;

    /**
     * IP country, phone country, breach countries and policy_context.region together.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private Country country;

    @JsonCreator
    public Consistency(
            @JsonProperty("name") @Nullable ConsistencyValue name,
            @JsonProperty("phone") @Nullable ConsistencyValue phone,
            @JsonProperty("ip") @Nullable ConsistencyValue ip,
            @JsonProperty("country") @Nullable Country country) {
        this.name = name;
        this.phone = phone;
        this.ip = ip;
        this.country = country;
    }

    public Consistency() {
        this(null, null, null, null);
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Optional<ConsistencyValue> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Optional<ConsistencyValue> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Optional<ConsistencyValue> ip() {
        return Optional.ofNullable(this.ip);
    }

    /**
     * IP country, phone country, breach countries and policy_context.region together.
     */
    public Optional<Country> country() {
        return Optional.ofNullable(this.country);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Consistency withName(@Nullable ConsistencyValue name) {
        this.name = name;
        return this;
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Consistency withPhone(@Nullable ConsistencyValue phone) {
        this.phone = phone;
        return this;
    }

    /**
     * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
     * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
     * comparison was not made.
     */
    public Consistency withIp(@Nullable ConsistencyValue ip) {
        this.ip = ip;
        return this;
    }

    /**
     * IP country, phone country, breach countries and policy_context.region together.
     */
    public Consistency withCountry(@Nullable Country country) {
        this.country = country;
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
        Consistency other = (Consistency) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.ip, other.ip)
                && Utils.enhancedDeepEquals(this.country, other.country);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, phone, ip, country);
    }

    @Override
    public String toString() {
        return Utils.toString(Consistency.class, "name", name, "phone", phone, "ip", ip, "country", country);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ConsistencyValue name;

        private ConsistencyValue phone;

        private ConsistencyValue ip;

        private Country country;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
         * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
         * comparison was not made.
         */
        public Builder name(@Nullable ConsistencyValue name) {
            this.name = name;
            return this;
        }

        /**
         * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
         * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
         * comparison was not made.
         */
        public Builder phone(@Nullable ConsistencyValue phone) {
            this.phone = phone;
            return this;
        }

        /**
         * How a submitted identifier agrees with what the evidence holds for the other identifiers. unknown is
         * a definite 'cannot tell' (for example a truncated or too-common breach result); absent means the
         * comparison was not made.
         */
        public Builder ip(@Nullable ConsistencyValue ip) {
            this.ip = ip;
            return this;
        }

        /**
         * IP country, phone country, breach countries and policy_context.region together.
         */
        public Builder country(@Nullable Country country) {
            this.country = country;
            return this;
        }

        public Consistency build() {
            return new Consistency(name, phone, ip, country);
        }
    }
}
