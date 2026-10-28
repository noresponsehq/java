package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AccountDecisionSnapshot
 *
 * <p>Retrieve the account decision at one recorded risk version. The answer uses the original assessment
 * and account-specific risk inputs. Earlier versions that were not captured return 404.
 *
 * <p>The assessment version, policy version and account calculation version identify the decision.
 */
public class AccountDecisionSnapshot {
    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * workspace's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment, plus the account's own reason shared_api_key
     * when a key issued to the account was used from many IP addresses or countries (POST
     * /v1/key_usage/batches). Its assessments are the account's history (GET
     * /v1/accounts/{account_id}/assessments).
     */
    @JsonProperty("account")
    private Account account;

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
    @JsonProperty("policy_version")
    private String policyVersion;

    @JsonProperty("overlay_version")
    private long overlayVersion;

    @JsonProperty("changed_at")
    private String changedAt;

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("feedback_outcome_id")
    private String feedbackOutcomeId;

    @JsonCreator
    public AccountDecisionSnapshot(
            @JsonProperty("account") @Nonnull Account account,
            @JsonProperty("assessment_version") long assessmentVersion,
            @JsonProperty("policy_version") @Nonnull String policyVersion,
            @JsonProperty("changed_at") @Nonnull String changedAt,
            @JsonProperty("feedback_outcome_id") @Nullable String feedbackOutcomeId) {
        this.account =
                Optional.ofNullable(account).orElseThrow(() -> new IllegalArgumentException("account cannot be null"));
        this.assessmentVersion = assessmentVersion;
        this.policyVersion = Optional.ofNullable(policyVersion)
                .orElseThrow(() -> new IllegalArgumentException("policyVersion cannot be null"));
        this.overlayVersion = Builder._SINGLETON_VALUE_OverlayVersion.value();
        this.changedAt = Optional.ofNullable(changedAt)
                .orElseThrow(() -> new IllegalArgumentException("changedAt cannot be null"));
        this.feedbackOutcomeId = feedbackOutcomeId;
    }

    public AccountDecisionSnapshot(
            @Nonnull Account account,
            long assessmentVersion,
            @Nonnull String policyVersion,
            @Nonnull String changedAt) {
        this(account, assessmentVersion, policyVersion, changedAt, null);
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
    public Account account() {
        return this.account;
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
    public String policyVersion() {
        return this.policyVersion;
    }

    public long overlayVersion() {
        return this.overlayVersion;
    }

    public String changedAt() {
        return this.changedAt;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public Optional<String> feedbackOutcomeId() {
        return Optional.ofNullable(this.feedbackOutcomeId);
    }

    public static Builder builder() {
        return new Builder();
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
    public AccountDecisionSnapshot withAccount(@Nonnull Account account) {
        this.account = Utils.checkNotNull(account, "account");
        return this;
    }

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public AccountDecisionSnapshot withAssessmentVersion(long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public AccountDecisionSnapshot withPolicyVersion(@Nonnull String policyVersion) {
        this.policyVersion = Utils.checkNotNull(policyVersion, "policyVersion");
        return this;
    }

    public AccountDecisionSnapshot withChangedAt(@Nonnull String changedAt) {
        this.changedAt = Utils.checkNotNull(changedAt, "changedAt");
        return this;
    }

    /**
     * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
     * Unique within the workspace.
     */
    public AccountDecisionSnapshot withFeedbackOutcomeId(@Nullable String feedbackOutcomeId) {
        this.feedbackOutcomeId = feedbackOutcomeId;
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
        AccountDecisionSnapshot other = (AccountDecisionSnapshot) o;
        return Utils.enhancedDeepEquals(this.account, other.account)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.overlayVersion, other.overlayVersion)
                && Utils.enhancedDeepEquals(this.changedAt, other.changedAt)
                && Utils.enhancedDeepEquals(this.feedbackOutcomeId, other.feedbackOutcomeId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                account, assessmentVersion, policyVersion, overlayVersion, changedAt, feedbackOutcomeId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDecisionSnapshot.class,
                "account",
                account,
                "assessmentVersion",
                assessmentVersion,
                "policyVersion",
                policyVersion,
                "overlayVersion",
                overlayVersion,
                "changedAt",
                changedAt,
                "feedbackOutcomeId",
                feedbackOutcomeId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Account account;

        private long assessmentVersion;

        private String policyVersion;

        private String changedAt;

        private String feedbackOutcomeId;

        private Builder() {
            // force use of static builder() method
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
        public Builder account(@Nonnull Account account) {
            this.account = Utils.checkNotNull(account, "account");
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
        public Builder policyVersion(@Nonnull String policyVersion) {
            this.policyVersion = Utils.checkNotNull(policyVersion, "policyVersion");
            return this;
        }

        public Builder changedAt(@Nonnull String changedAt) {
            this.changedAt = Utils.checkNotNull(changedAt, "changedAt");
            return this;
        }

        /**
         * Opaque, server-assigned id of one recorded outcome: 'out_' and a 26-character Crockford base32 ULID.
         * Unique within the workspace.
         */
        public Builder feedbackOutcomeId(@Nullable String feedbackOutcomeId) {
            this.feedbackOutcomeId = feedbackOutcomeId;
            return this;
        }

        public AccountDecisionSnapshot build() {
            return new AccountDecisionSnapshot(account, assessmentVersion, policyVersion, changedAt, feedbackOutcomeId);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_OverlayVersion =
                new LazySingletonValue<>("overlay_version", "1", new TypeReference<Long>() {});
    }
}
