package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class PolicyEvaluationRequestLimits {

    @JsonProperty("review_rate")
    private double reviewRate;

    @JsonProperty("server_error_rate")
    private double serverErrorRate;

    @JsonProperty("unavailable_result_rate")
    private double unavailableResultRate;

    @JsonProperty("p95_ms")
    private double p95Ms;

    @JsonCreator
    public PolicyEvaluationRequestLimits(
            @JsonProperty("review_rate") double reviewRate,
            @JsonProperty("server_error_rate") double serverErrorRate,
            @JsonProperty("unavailable_result_rate") double unavailableResultRate,
            @JsonProperty("p95_ms") double p95Ms) {
        this.reviewRate = reviewRate;
        this.serverErrorRate = serverErrorRate;
        this.unavailableResultRate = unavailableResultRate;
        this.p95Ms = p95Ms;
    }

    public double reviewRate() {
        return this.reviewRate;
    }

    public double serverErrorRate() {
        return this.serverErrorRate;
    }

    public double unavailableResultRate() {
        return this.unavailableResultRate;
    }

    public double p95Ms() {
        return this.p95Ms;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PolicyEvaluationRequestLimits withReviewRate(double reviewRate) {
        this.reviewRate = reviewRate;
        return this;
    }

    public PolicyEvaluationRequestLimits withServerErrorRate(double serverErrorRate) {
        this.serverErrorRate = serverErrorRate;
        return this;
    }

    public PolicyEvaluationRequestLimits withUnavailableResultRate(double unavailableResultRate) {
        this.unavailableResultRate = unavailableResultRate;
        return this;
    }

    public PolicyEvaluationRequestLimits withP95Ms(double p95Ms) {
        this.p95Ms = p95Ms;
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
        PolicyEvaluationRequestLimits other = (PolicyEvaluationRequestLimits) o;
        return Utils.enhancedDeepEquals(this.reviewRate, other.reviewRate)
                && Utils.enhancedDeepEquals(this.serverErrorRate, other.serverErrorRate)
                && Utils.enhancedDeepEquals(this.unavailableResultRate, other.unavailableResultRate)
                && Utils.enhancedDeepEquals(this.p95Ms, other.p95Ms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(reviewRate, serverErrorRate, unavailableResultRate, p95Ms);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PolicyEvaluationRequestLimits.class,
                "reviewRate",
                reviewRate,
                "serverErrorRate",
                serverErrorRate,
                "unavailableResultRate",
                unavailableResultRate,
                "p95Ms",
                p95Ms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private double reviewRate;

        private double serverErrorRate;

        private double unavailableResultRate;

        private double p95Ms;

        private Builder() {
            // force use of static builder() method
        }

        public Builder reviewRate(double reviewRate) {
            this.reviewRate = reviewRate;
            return this;
        }

        public Builder serverErrorRate(double serverErrorRate) {
            this.serverErrorRate = serverErrorRate;
            return this;
        }

        public Builder unavailableResultRate(double unavailableResultRate) {
            this.unavailableResultRate = unavailableResultRate;
            return this;
        }

        public Builder p95Ms(double p95Ms) {
            this.p95Ms = p95Ms;
            return this;
        }

        public PolicyEvaluationRequestLimits build() {
            return new PolicyEvaluationRequestLimits(reviewRate, serverErrorRate, unavailableResultRate, p95Ms);
        }
    }
}
