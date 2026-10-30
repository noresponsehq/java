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
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentSearchItem
 *
 * <p>One stored assessment revision, exactly as GET /v1/assessments/{assessment_id}/versions/{version}
 * returns it, with the decision of the assessment's first answer. It is not re-scored and carries no
 * current account risk.
 */
public class AssessmentSearchItem {
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
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
     */
    @JsonProperty("status")
    private Status status;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
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
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    @JsonProperty("detail")
    private AssessmentDetail detail;

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("first_decision")
    private Decision firstDecision;

    /**
     * Why this result matched a typed identifier search. value is the original value submitted in
     * assessment_id. Provenance records a customer submission, not a source discovery or verified
     * ownership.
     *
     * <p>Equality is deterministic and has no inferred confidence score. An account result names its newest
     * retained matching assessment, which can differ from latest.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("matched_identifier")
    private IdentifierMatch matchedIdentifier;

    /**
     * Present only for identifier_expression. All satisfied unique terms, ordered by term_index, with
     * original submitted values and retained assessment witnesses. A duplicate normalized term appears
     * once at its first raw leaf index.
     *
     * <p>The legacy single identifier filter instead returns matched_identifier.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("matched_identifiers")
    private List<IdentifierTermMatch> matchedIdentifiers;

    @JsonCreator
    public AssessmentSearchItem(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("status") @Nonnull Status status,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("depth") @Nonnull Depth depth,
            @JsonProperty("detail") @Nonnull AssessmentDetail detail,
            @JsonProperty("first_decision") @Nullable Decision firstDecision,
            @JsonProperty("matched_identifier") @Nullable IdentifierMatch matchedIdentifier,
            @JsonProperty("matched_identifiers") @Nullable List<IdentifierTermMatch> matchedIdentifiers) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.detail =
                Optional.ofNullable(detail).orElseThrow(() -> new IllegalArgumentException("detail cannot be null"));
        this.firstDecision = firstDecision;
        this.matchedIdentifier = matchedIdentifier;
        this.matchedIdentifiers = matchedIdentifiers;
    }

    public AssessmentSearchItem(
            @Nonnull String id,
            @Nonnull Status status,
            @Nonnull Flow flow,
            @Nonnull Depth depth,
            @Nonnull AssessmentDetail detail) {
        this(id, status, flow, depth, detail, null, null, null);
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
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
     */
    public Status status() {
        return this.status;
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
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public Depth depth() {
        return this.depth;
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

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public Optional<Decision> firstDecision() {
        return Optional.ofNullable(this.firstDecision);
    }

    /**
     * Why this result matched a typed identifier search. value is the original value submitted in
     * assessment_id. Provenance records a customer submission, not a source discovery or verified
     * ownership.
     *
     * <p>Equality is deterministic and has no inferred confidence score. An account result names its newest
     * retained matching assessment, which can differ from latest.
     */
    public Optional<IdentifierMatch> matchedIdentifier() {
        return Optional.ofNullable(this.matchedIdentifier);
    }

    /**
     * Present only for identifier_expression. All satisfied unique terms, ordered by term_index, with
     * original submitted values and retained assessment witnesses. A duplicate normalized term appears
     * once at its first raw leaf index.
     *
     * <p>The legacy single identifier filter instead returns matched_identifier.
     */
    public Optional<List<IdentifierTermMatch>> matchedIdentifiers() {
        return Optional.ofNullable(this.matchedIdentifiers);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    public AssessmentSearchItem withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
     * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
     * exists and evidence is incomplete; assessment_url identifies continuing work when present.
     *
     * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
     * sources answered.
     */
    public AssessmentSearchItem withStatus(@Nonnull Status status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AssessmentSearchItem withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public AssessmentSearchItem withDepth(@Nonnull Depth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    public AssessmentSearchItem withDetail(@Nonnull AssessmentDetail detail) {
        this.detail = Utils.checkNotNull(detail, "detail");
        return this;
    }

    /**
     * An assessment's own decision: block when its stored risk.block is true, otherwise its
     * recommendation. It is not the account's decision or held-admission state.
     */
    public AssessmentSearchItem withFirstDecision(@Nullable Decision firstDecision) {
        this.firstDecision = firstDecision;
        return this;
    }

    /**
     * Why this result matched a typed identifier search. value is the original value submitted in
     * assessment_id. Provenance records a customer submission, not a source discovery or verified
     * ownership.
     *
     * <p>Equality is deterministic and has no inferred confidence score. An account result names its newest
     * retained matching assessment, which can differ from latest.
     */
    public AssessmentSearchItem withMatchedIdentifier(@Nullable IdentifierMatch matchedIdentifier) {
        this.matchedIdentifier = matchedIdentifier;
        return this;
    }

    /**
     * Present only for identifier_expression. All satisfied unique terms, ordered by term_index, with
     * original submitted values and retained assessment witnesses. A duplicate normalized term appears
     * once at its first raw leaf index.
     *
     * <p>The legacy single identifier filter instead returns matched_identifier.
     */
    public AssessmentSearchItem withMatchedIdentifiers(@Nullable List<IdentifierTermMatch> matchedIdentifiers) {
        this.matchedIdentifiers = matchedIdentifiers;
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
        AssessmentSearchItem other = (AssessmentSearchItem) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.detail, other.detail)
                && Utils.enhancedDeepEquals(this.firstDecision, other.firstDecision)
                && Utils.enhancedDeepEquals(this.matchedIdentifier, other.matchedIdentifier)
                && Utils.enhancedDeepEquals(this.matchedIdentifiers, other.matchedIdentifiers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                schemaVersion, id, status, flow, depth, detail, firstDecision, matchedIdentifier, matchedIdentifiers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentSearchItem.class,
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
                "detail",
                detail,
                "firstDecision",
                firstDecision,
                "matchedIdentifier",
                matchedIdentifier,
                "matchedIdentifiers",
                matchedIdentifiers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private Status status;

        private Flow flow;

        private Depth depth;

        private AssessmentDetail detail;

        private Decision firstDecision;

        private IdentifierMatch matchedIdentifier;

        private List<IdentifierTermMatch> matchedIdentifiers;

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
         * Processing state, separate from evidence coverage. complete: final; nothing is still running, but
         * unsupported, failed or unanswered lanes remain in incomplete_sources. partial: a recommendation
         * exists and evidence is incomplete; assessment_url identifies continuing work when present.
         *
         * <p>pending: no recommendation yet; poll or wait for the webhook. Finished processing does not mean all
         * sources answered.
         */
        public Builder status(@Nonnull Status status) {
            this.status = Utils.checkNotNull(status, "status");
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
         * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
         * running and its timing. risk here is this assessment's own score; the account's current risk is the
         * account member of the assessment. risk and recommendation are present for complete and partial;
         * assessment_url is present for pending.
         */
        public Builder detail(@Nonnull AssessmentDetail detail) {
            this.detail = Utils.checkNotNull(detail, "detail");
            return this;
        }

        /**
         * An assessment's own decision: block when its stored risk.block is true, otherwise its
         * recommendation. It is not the account's decision or held-admission state.
         */
        public Builder firstDecision(@Nullable Decision firstDecision) {
            this.firstDecision = firstDecision;
            return this;
        }

        /**
         * Why this result matched a typed identifier search. value is the original value submitted in
         * assessment_id. Provenance records a customer submission, not a source discovery or verified
         * ownership.
         *
         * <p>Equality is deterministic and has no inferred confidence score. An account result names its newest
         * retained matching assessment, which can differ from latest.
         */
        public Builder matchedIdentifier(@Nullable IdentifierMatch matchedIdentifier) {
            this.matchedIdentifier = matchedIdentifier;
            return this;
        }

        /**
         * Present only for identifier_expression. All satisfied unique terms, ordered by term_index, with
         * original submitted values and retained assessment witnesses. A duplicate normalized term appears
         * once at its first raw leaf index.
         *
         * <p>The legacy single identifier filter instead returns matched_identifier.
         */
        public Builder matchedIdentifiers(@Nullable List<IdentifierTermMatch> matchedIdentifiers) {
            this.matchedIdentifiers = matchedIdentifiers;
            return this;
        }

        public AssessmentSearchItem build() {
            return new AssessmentSearchItem(
                    id, status, flow, depth, detail, firstDecision, matchedIdentifier, matchedIdentifiers);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
