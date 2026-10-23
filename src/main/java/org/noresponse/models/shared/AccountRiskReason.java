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

public class AccountRiskReason {
    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    @JsonProperty("code")
    private ReasonCode code;

    @JsonProperty("weight")
    private long weight;

    /**
     * The submitted identifier or client context field a reason concerns; absent when it concerns the
     * whole assessment. Client context is a claimed hint, not a device fingerprint.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("identifier")
    private ReasonIdentifier identifier;

    @JsonCreator
    public AccountRiskReason(
            @JsonProperty("code") @Nonnull ReasonCode code,
            @JsonProperty("weight") long weight,
            @JsonProperty("identifier") @Nullable ReasonIdentifier identifier) {
        this.code = Optional.ofNullable(code).orElseThrow(() -> new IllegalArgumentException("code cannot be null"));
        this.weight = weight;
        this.identifier = identifier;
    }

    public AccountRiskReason(@Nonnull ReasonCode code, long weight) {
        this(code, weight, null);
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public ReasonCode code() {
        return this.code;
    }

    public long weight() {
        return this.weight;
    }

    /**
     * The submitted identifier or client context field a reason concerns; absent when it concerns the
     * whole assessment. Client context is a claimed hint, not a device fingerprint.
     */
    public Optional<ReasonIdentifier> identifier() {
        return Optional.ofNullable(this.identifier);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public AccountRiskReason withCode(@Nonnull ReasonCode code) {
        this.code = Utils.checkNotNull(code, "code");
        return this;
    }

    public AccountRiskReason withWeight(long weight) {
        this.weight = weight;
        return this;
    }

    /**
     * The submitted identifier or client context field a reason concerns; absent when it concerns the
     * whole assessment. Client context is a claimed hint, not a device fingerprint.
     */
    public AccountRiskReason withIdentifier(@Nullable ReasonIdentifier identifier) {
        this.identifier = identifier;
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
        AccountRiskReason other = (AccountRiskReason) o;
        return Utils.enhancedDeepEquals(this.code, other.code)
                && Utils.enhancedDeepEquals(this.weight, other.weight)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(code, weight, identifier);
    }

    @Override
    public String toString() {
        return Utils.toString(AccountRiskReason.class, "code", code, "weight", weight, "identifier", identifier);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ReasonCode code;

        private long weight;

        private ReasonIdentifier identifier;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * A stable reason code. New codes may appear; ignore any you do not know.
         */
        public Builder code(@Nonnull ReasonCode code) {
            this.code = Utils.checkNotNull(code, "code");
            return this;
        }

        public Builder weight(long weight) {
            this.weight = weight;
            return this;
        }

        /**
         * The submitted identifier or client context field a reason concerns; absent when it concerns the
         * whole assessment. Client context is a claimed hint, not a device fingerprint.
         */
        public Builder identifier(@Nullable ReasonIdentifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public AccountRiskReason build() {
            return new AccountRiskReason(code, weight, identifier);
        }
    }
}
