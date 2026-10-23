package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * Assessment
 *
 * <p>One assessment, as POST /v1/assessments, GET /v1/assessments/{assessment_id} and the
 * assessment.updated webhook return it. account is the account's one current risk: its score, the
 * decision for this assessment's flow, the reasons and the decision for each flow. detail holds this
 * assessment's own facts.
 *
 * <p>status says whether the assessment is final (complete), usable with a source still running (partial)
 * or not yet usable (pending). While the account's first assessment is pending, account is absent;
 * wait for the webhook or poll the assessment. Keep the highest detail.assessment_version.
 */
public class Assessment {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    @JsonProperty("status")
    private Status status;

    /**
     * The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or
     * purchase (a payment or credit grant). The account's risk is reported with the decision for this
     * flow.
     */
    @JsonProperty("flow")
    private Flow flow;

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    @JsonProperty("depth")
    private Depth depth;

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * tenant's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment. Its assessments are the account's history
     * (GET /v1/accounts/{account_id}/assessments).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account")
    private Account account;

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    @JsonProperty("detail")
    private AssessmentDetail detail;

    @JsonCreator
    public Assessment(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("status") @Nonnull Status status,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("depth") @Nonnull Depth depth,
            @JsonProperty("account") @Nullable Account account,
            @JsonProperty("detail") @Nonnull AssessmentDetail detail) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.account = account;
        this.detail =
                Optional.ofNullable(detail).orElseThrow(() -> new IllegalArgumentException("detail cannot be null"));
    }

    public Assessment(
            @Nonnull String id,
            @Nonnull Status status,
            @Nonnull Flow flow,
            @Nonnull Depth depth,
            @Nonnull AssessmentDetail detail) {
        this(id, status, flow, depth, null, detail);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    public String id() {
        return this.id;
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

    /**
     * The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or
     * purchase (a payment or credit grant). The account's risk is reported with the decision for this
     * flow.
     */
    public Flow flow() {
        return this.flow;
    }

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public Depth depth() {
        return this.depth;
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * tenant's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment. Its assessments are the account's history
     * (GET /v1/accounts/{account_id}/assessments).
     */
    public Optional<Account> account() {
        return Optional.ofNullable(this.account);
    }

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    public AssessmentDetail detail() {
        return this.detail;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    public Assessment withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public Assessment withStatus(@Nonnull Status status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or
     * purchase (a payment or credit grant). The account's risk is reported with the decision for this
     * flow.
     */
    public Assessment withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public Assessment withDepth(@Nonnull Depth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    /**
     * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
     * tenant's accounts. It is the risk of the account's latest assessment that has a decision (status
     * complete or partial), by created_at and then assessment_id, scored exactly as GET
     * /v1/assessments/{assessment_id} scores that assessment. Its assessments are the account's history
     * (GET /v1/accounts/{account_id}/assessments).
     */
    public Assessment withAccount(@Nullable Account account) {
        this.account = account;
        return this;
    }

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    public Assessment withDetail(@Nonnull AssessmentDetail detail) {
        this.detail = Utils.checkNotNull(detail, "detail");
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
        Assessment other = (Assessment) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.account, other.account)
                && Utils.enhancedDeepEquals(this.detail, other.detail);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, id, status, flow, depth, account, detail);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Assessment.class,
                "schemaVersion",
                schemaVersion,
                "id",
                id,
                "status",
                status,
                "flow",
                flow,
                "depth",
                depth,
                "account",
                account,
                "detail",
                detail);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private Status status;

        private Flow flow;

        private Depth depth;

        private Account account;

        private AssessmentDetail detail;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * This assessment's id, equal to detail.assessment_id.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
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

        /**
         * The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or
         * purchase (a payment or credit grant). The account's risk is reported with the decision for this
         * flow.
         */
        public Builder flow(@Nonnull Flow flow) {
            this.flow = Utils.checkNotNull(flow, "flow");
            return this;
        }

        /**
         * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
         * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
         * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
         * way.
         */
        public Builder depth(@Nonnull Depth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        /**
         * Body of the 200 answer to GET /v1/accounts/{account_id}: the one current risk of one of the calling
         * tenant's accounts. It is the risk of the account's latest assessment that has a decision (status
         * complete or partial), by created_at and then assessment_id, scored exactly as GET
         * /v1/assessments/{assessment_id} scores that assessment. Its assessments are the account's history
         * (GET /v1/accounts/{account_id}/assessments).
         */
        public Builder account(@Nullable Account account) {
            this.account = account;
            return this;
        }

        /**
         * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
         * running and its timing. risk here is this assessment's own score; the account's current risk is the
         * account member of the assessment. risk and recommendation are present for complete and partial;
         * assessment_url is present for pending.
         */
        public Builder detail(@Nonnull AssessmentDetail detail) {
            this.detail = Utils.checkNotNull(detail, "detail");
            return this;
        }

        public Assessment build() {
            return new Assessment(id, status, flow, depth, account, detail);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
