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
 * RiskSummary
 *
 * <p>A few typed facts per identifier. Every fact is optional: absent means the evidence did not answer
 * and makes no claim, while false or 0 is a definite answer. Lanes that do not exist yet leave their
 * members absent.
 */
public class RiskSummary {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private RiskSummaryPhone phone;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private RiskSummaryEmail email;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private RiskSummaryUsername username;

    /**
     * Facts about the submitted IP from the offline database and this workspace's own recent assessments.
     * Every member is optional; absent makes no claim.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("ip")
    private IpSummary ip;

    /**
     * Cross-identifier agreement, computed from facts only. Every member is optional; absent makes no
     * claim.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("consistency")
    private Consistency consistency;

    @JsonCreator
    public RiskSummary(
            @JsonProperty("phone") @Nullable RiskSummaryPhone phone,
            @JsonProperty("email") @Nullable RiskSummaryEmail email,
            @JsonProperty("username") @Nullable RiskSummaryUsername username,
            @JsonProperty("ip") @Nullable IpSummary ip,
            @JsonProperty("consistency") @Nullable Consistency consistency) {
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.ip = ip;
        this.consistency = consistency;
    }

    public RiskSummary() {
        this(null, null, null, null, null);
    }

    public Optional<RiskSummaryPhone> phone() {
        return Optional.ofNullable(this.phone);
    }

    public Optional<RiskSummaryEmail> email() {
        return Optional.ofNullable(this.email);
    }

    public Optional<RiskSummaryUsername> username() {
        return Optional.ofNullable(this.username);
    }

    /**
     * Facts about the submitted IP from the offline database and this workspace's own recent assessments.
     * Every member is optional; absent makes no claim.
     */
    public Optional<IpSummary> ip() {
        return Optional.ofNullable(this.ip);
    }

    /**
     * Cross-identifier agreement, computed from facts only. Every member is optional; absent makes no
     * claim.
     */
    public Optional<Consistency> consistency() {
        return Optional.ofNullable(this.consistency);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RiskSummary withPhone(@Nullable RiskSummaryPhone phone) {
        this.phone = phone;
        return this;
    }

    public RiskSummary withEmail(@Nullable RiskSummaryEmail email) {
        this.email = email;
        return this;
    }

    public RiskSummary withUsername(@Nullable RiskSummaryUsername username) {
        this.username = username;
        return this;
    }

    /**
     * Facts about the submitted IP from the offline database and this workspace's own recent assessments.
     * Every member is optional; absent makes no claim.
     */
    public RiskSummary withIp(@Nullable IpSummary ip) {
        this.ip = ip;
        return this;
    }

    /**
     * Cross-identifier agreement, computed from facts only. Every member is optional; absent makes no
     * claim.
     */
    public RiskSummary withConsistency(@Nullable Consistency consistency) {
        this.consistency = consistency;
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
        RiskSummary other = (RiskSummary) o;
        return Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.ip, other.ip)
                && Utils.enhancedDeepEquals(this.consistency, other.consistency);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(phone, email, username, ip, consistency);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RiskSummary.class,
                "phone",
                phone,
                "email",
                email,
                "username",
                username,
                "ip",
                ip,
                "consistency",
                consistency);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private RiskSummaryPhone phone;

        private RiskSummaryEmail email;

        private RiskSummaryUsername username;

        private IpSummary ip;

        private Consistency consistency;

        private Builder() {
            // force use of static builder() method
        }

        public Builder phone(@Nullable RiskSummaryPhone phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(@Nullable RiskSummaryEmail email) {
            this.email = email;
            return this;
        }

        public Builder username(@Nullable RiskSummaryUsername username) {
            this.username = username;
            return this;
        }

        /**
         * Facts about the submitted IP from the offline database and this workspace's own recent assessments.
         * Every member is optional; absent makes no claim.
         */
        public Builder ip(@Nullable IpSummary ip) {
            this.ip = ip;
            return this;
        }

        /**
         * Cross-identifier agreement, computed from facts only. Every member is optional; absent makes no
         * claim.
         */
        public Builder consistency(@Nullable Consistency consistency) {
            this.consistency = consistency;
            return this;
        }

        public RiskSummary build() {
            return new RiskSummary(phone, email, username, ip, consistency);
        }
    }
}
