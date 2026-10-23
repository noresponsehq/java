package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * Account
 *
 * <p>Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
 * tenant's accounts. It is the risk of the account's latest assessment that has a decision (status
 * complete or partial), by created_at and then assessment_id, scored exactly as GET
 * /v1/assessments/{assessment_id} scores that assessment. Its assessments are the account's history
 * (GET /v1/accounts/{account_id}/assessments).
 */
public class Account {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * The account_id the tenant sends with its assessments.
     */
    @JsonProperty("id")
    private String id;

    @JsonProperty("risk")
    private AccountRisk risk;

    /**
     * The decision for each flow. Every flow uses the tenant's signup thresholds today, so all three equal
     * risk.decision.
     */
    @JsonProperty("decisions_by_flow")
    private AccountDecisionsByFlow decisionsByFlow;

    /**
     * The assessment the current risk comes from.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * updated_at of that assessment's latest revision.
     */
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonCreator
    public Account(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("risk") @Nonnull AccountRisk risk,
            @JsonProperty("decisions_by_flow") @Nonnull AccountDecisionsByFlow decisionsByFlow,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("updated_at") @Nonnull String updatedAt) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.risk = Optional.ofNullable(risk).orElseThrow(() -> new IllegalArgumentException("risk cannot be null"));
        this.decisionsByFlow = Optional.ofNullable(decisionsByFlow)
                .orElseThrow(() -> new IllegalArgumentException("decisionsByFlow cannot be null"));
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * The account_id the tenant sends with its assessments.
     */
    public String id() {
        return this.id;
    }

    public AccountRisk risk() {
        return this.risk;
    }

    /**
     * The decision for each flow. Every flow uses the tenant's signup thresholds today, so all three equal
     * risk.decision.
     */
    public AccountDecisionsByFlow decisionsByFlow() {
        return this.decisionsByFlow;
    }

    /**
     * The assessment the current risk comes from.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * updated_at of that assessment's latest revision.
     */
    public String updatedAt() {
        return this.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the tenant sends with its assessments.
     */
    public Account withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public Account withRisk(@Nonnull AccountRisk risk) {
        this.risk = Utils.checkNotNull(risk, "risk");
        return this;
    }

    /**
     * The decision for each flow. Every flow uses the tenant's signup thresholds today, so all three equal
     * risk.decision.
     */
    public Account withDecisionsByFlow(@Nonnull AccountDecisionsByFlow decisionsByFlow) {
        this.decisionsByFlow = Utils.checkNotNull(decisionsByFlow, "decisionsByFlow");
        return this;
    }

    /**
     * The assessment the current risk comes from.
     */
    public Account withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * updated_at of that assessment's latest revision.
     */
    public Account withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
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
        Account other = (Account) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.risk, other.risk)
                && Utils.enhancedDeepEquals(this.decisionsByFlow, other.decisionsByFlow)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, id, risk, decisionsByFlow, assessmentId, updatedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Account.class,
                "schemaVersion",
                schemaVersion,
                "id",
                id,
                "risk",
                risk,
                "decisionsByFlow",
                decisionsByFlow,
                "assessmentId",
                assessmentId,
                "updatedAt",
                updatedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private AccountRisk risk;

        private AccountDecisionsByFlow decisionsByFlow;

        private String assessmentId;

        private String updatedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id the tenant sends with its assessments.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder risk(@Nonnull AccountRisk risk) {
            this.risk = Utils.checkNotNull(risk, "risk");
            return this;
        }

        /**
         * The decision for each flow. Every flow uses the tenant's signup thresholds today, so all three equal
         * risk.decision.
         */
        public Builder decisionsByFlow(@Nonnull AccountDecisionsByFlow decisionsByFlow) {
            this.decisionsByFlow = Utils.checkNotNull(decisionsByFlow, "decisionsByFlow");
            return this;
        }

        /**
         * The assessment the current risk comes from.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        /**
         * updated_at of that assessment's latest revision.
         */
        public Builder updatedAt(@Nonnull String updatedAt) {
            this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
            return this;
        }

        public Account build() {
            return new Account(id, risk, decisionsByFlow, assessmentId, updatedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
