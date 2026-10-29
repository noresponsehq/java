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

/**
 * AccountRiskReason
 *
 * <p>One reason that fired. direction is whether it raises or lowers the score, or is neutral
 * (informational, it moves nothing). impact is a coarse band of how much it moves the score: none for
 * a neutral reason, otherwise low, medium or high.
 */
public class AccountRiskReason {
    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    @JsonProperty("code")
    private ReasonCode code;

    @JsonProperty("direction")
    private Direction direction;

    /**
     * The selected reason's served impact band. Requires reason.
     */
    @JsonProperty("impact")
    private Impact impact;

    /**
     * The submitted identifier or client context field a reason concerns; absent when it concerns the
     * whole assessment. Client context is a claimed hint, not a device fingerprint.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("identifier")
    private ReasonIdentifier identifier;

    /**
     * The list entry a list_match reason applied. No other reason carries it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("list_entry_id")
    private String listEntryId;

    @JsonCreator
    public AccountRiskReason(
            @JsonProperty("code") @Nonnull ReasonCode code,
            @JsonProperty("direction") @Nonnull Direction direction,
            @JsonProperty("impact") @Nonnull Impact impact,
            @JsonProperty("identifier") @Nullable ReasonIdentifier identifier,
            @JsonProperty("list_entry_id") @Nullable String listEntryId) {
        this.code = Optional.ofNullable(code).orElseThrow(() -> new IllegalArgumentException("code cannot be null"));
        this.direction = Optional.ofNullable(direction)
                .orElseThrow(() -> new IllegalArgumentException("direction cannot be null"));
        this.impact =
                Optional.ofNullable(impact).orElseThrow(() -> new IllegalArgumentException("impact cannot be null"));
        this.identifier = identifier;
        this.listEntryId = listEntryId;
    }

    public AccountRiskReason(@Nonnull ReasonCode code, @Nonnull Direction direction, @Nonnull Impact impact) {
        this(code, direction, impact, null, null);
    }

    /**
     * A stable reason code. New codes may appear; ignore any you do not know.
     */
    public ReasonCode code() {
        return this.code;
    }

    public Direction direction() {
        return this.direction;
    }

    /**
     * The selected reason's served impact band. Requires reason.
     */
    public Impact impact() {
        return this.impact;
    }

    /**
     * The submitted identifier or client context field a reason concerns; absent when it concerns the
     * whole assessment. Client context is a claimed hint, not a device fingerprint.
     */
    public Optional<ReasonIdentifier> identifier() {
        return Optional.ofNullable(this.identifier);
    }

    /**
     * The list entry a list_match reason applied. No other reason carries it.
     */
    public Optional<String> listEntryId() {
        return Optional.ofNullable(this.listEntryId);
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

    public AccountRiskReason withDirection(@Nonnull Direction direction) {
        this.direction = Utils.checkNotNull(direction, "direction");
        return this;
    }

    /**
     * The selected reason's served impact band. Requires reason.
     */
    public AccountRiskReason withImpact(@Nonnull Impact impact) {
        this.impact = Utils.checkNotNull(impact, "impact");
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

    /**
     * The list entry a list_match reason applied. No other reason carries it.
     */
    public AccountRiskReason withListEntryId(@Nullable String listEntryId) {
        this.listEntryId = listEntryId;
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
                && Utils.enhancedDeepEquals(this.direction, other.direction)
                && Utils.enhancedDeepEquals(this.impact, other.impact)
                && Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.listEntryId, other.listEntryId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(code, direction, impact, identifier, listEntryId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountRiskReason.class,
                "code",
                code,
                "direction",
                direction,
                "impact",
                impact,
                "identifier",
                identifier,
                "listEntryId",
                listEntryId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ReasonCode code;

        private Direction direction;

        private Impact impact;

        private ReasonIdentifier identifier;

        private String listEntryId;

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

        public Builder direction(@Nonnull Direction direction) {
            this.direction = Utils.checkNotNull(direction, "direction");
            return this;
        }

        /**
         * The selected reason's served impact band. Requires reason.
         */
        public Builder impact(@Nonnull Impact impact) {
            this.impact = Utils.checkNotNull(impact, "impact");
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

        /**
         * The list entry a list_match reason applied. No other reason carries it.
         */
        public Builder listEntryId(@Nullable String listEntryId) {
            this.listEntryId = listEntryId;
            return this;
        }

        public AccountRiskReason build() {
            return new AccountRiskReason(code, direction, impact, identifier, listEntryId);
        }
    }
}
