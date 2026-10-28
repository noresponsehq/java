package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * DecisionReceipt
 *
 * <p>The decision prepared for one HTTP attempt. Its account field preserves the account decision
 * returned for that attempt, even after subsequent changes.
 */
public class DecisionReceipt {
    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    @JsonProperty("assessment_version")
    private long assessmentVersion;

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("policy_version")
    private String policyVersion;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonProperty("flow")
    private Flow flow;

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    @JsonProperty("status")
    private Status status;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("score")
    private Long score;

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account")
    private Account account;

    @JsonCreator
    public DecisionReceipt(
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("assessment_version") long assessmentVersion,
            @JsonProperty("policy_version") @Nullable String policyVersion,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("status") @Nonnull Status status,
            @JsonProperty("score") @Nullable Long score,
            @JsonProperty("account") @Nullable Account account) {
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.assessmentVersion = assessmentVersion;
        this.policyVersion = policyVersion;
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.score = score;
        this.account = account;
    }

    public DecisionReceipt(
            @Nonnull String assessmentId, long assessmentVersion, @Nonnull Flow flow, @Nonnull Status status) {
        this(assessmentId, assessmentVersion, null, flow, status, null, null);
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public long assessmentVersion() {
        return this.assessmentVersion;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public Optional<String> policyVersion() {
        return Optional.ofNullable(this.policyVersion);
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Flow flow() {
        return this.flow;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public Status status() {
        return this.status;
    }

    public Optional<Long> score() {
        return Optional.ofNullable(this.score);
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    public Optional<Account> account() {
        return Optional.ofNullable(this.account);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public DecisionReceipt withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public DecisionReceipt withAssessmentVersion(long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public DecisionReceipt withPolicyVersion(@Nullable String policyVersion) {
        this.policyVersion = policyVersion;
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public DecisionReceipt withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public DecisionReceipt withStatus(@Nonnull Status status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public DecisionReceipt withScore(@Nullable Long score) {
        this.score = score;
        return this;
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    public DecisionReceipt withAccount(@Nullable Account account) {
        this.account = account;
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
        DecisionReceipt other = (DecisionReceipt) o;
        return Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.score, other.score)
                && Utils.enhancedDeepEquals(this.account, other.account);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(assessmentId, assessmentVersion, policyVersion, flow, status, score, account);
    }

    @Override
    public String toString() {
        return Utils.toString(
                DecisionReceipt.class,
                "assessmentId",
                assessmentId,
                "assessmentVersion",
                assessmentVersion,
                "policyVersion",
                policyVersion,
                "flow",
                flow,
                "status",
                status,
                "score",
                score,
                "account",
                account);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private long assessmentVersion;

        private String policyVersion;

        private Flow flow;

        private Status status;

        private Long score;

        private Account account;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
         * within the workspace; another workspace's assessment id answers 404.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        /**
         * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
         * assessment is served (an async continuation settling a source, a change of recommendation,
         * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
         * not revised, and the expiry webhook carries the last served version.
         */
        public Builder assessmentVersion(long assessmentVersion) {
            this.assessmentVersion = assessmentVersion;
            return this;
        }

        /**
         * Version of the hand-authored heuristic policy that produced the recommendation, for example
         * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
         * 'risk-2026-10.7+t3'.
         */
        public Builder policyVersion(@Nullable String policyVersion) {
            this.policyVersion = policyVersion;
            return this;
        }

        /**
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nonnull Flow flow) {
            this.flow = Utils.checkNotNull(flow, "flow");
            return this;
        }

        /**
         * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
         * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
         * one lane is still running or did not settle (see incomplete_sources).
         *
         * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
         */
        public Builder status(@Nonnull Status status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder score(@Nullable Long score) {
            this.score = score;
            return this;
        }

        /**
         * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
         * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
         * complete or partial), by created_at and then assessment_id, scored exactly as GET
         * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
         * when a key issued to the account was used from many IP addresses or countries (POST
         * /v1/key_usage/batches). Its assessments are the account's history (GET
         * /v1/accounts/{account_id}/assessments).
         */
        public Builder account(@Nullable Account account) {
            this.account = account;
            return this;
        }

        public DecisionReceipt build() {
            return new DecisionReceipt(assessmentId, assessmentVersion, policyVersion, flow, status, score, account);
        }
    }
}
