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
 * AssessmentDetail
 *
 * <p>One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
 * running and its timing. risk here is this assessment's own score; the account's current risk is the
 * account member of the assessment. risk and recommendation are present for complete and partial;
 * assessment_url is present for pending.
 */
public class AssessmentDetail {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    /**
     * The identifiers an assessment was sent: each member of the request's identifiers object exactly as
     * received, before the server normalised it (the email domain keeps its case, the name its Unicode
     * form). A member the request did not send is absent. NoResponse keeps them encrypted under the
     * workspace's own key, does not index or log them, and deletes them with the assessment: on DELETE of
     * the assessment or its account, at its expires_at, and with the workspace.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("identifiers")
    private SentIdentifiers identifiers;

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("request_id")
    private String requestId;

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
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("recommendation")
    private Recommendation recommendation;

    /**
     * Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.
     */
    @JsonProperty("confidence")
    private Confidence confidence;

    /**
     * The one risk decision for this event. score is 0 (safe) to 100 (risky), set by the reasons that
     * fired under a hand-set, versioned heuristic policy. Each reason carries its direction and impact
     * band; the policy's weights are not published.
     *
     * <p>Every answer with a recommendation carries it, and the top-level recommendation equals
     * risk.recommendation. It names no source or provider.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("risk")
    private Risk risk;

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope): a standard answer omits
     * signals[], and a detailed one always carries it, possibly empty. The body does not name its mode, so
     * that rule is the serving gate's; the schema states the half it can: an answer with risk.details is a
     * detailed view and carries signals[]. At most one signal per (type, source_id, identifier).
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("signals")
    private List<Signal> signals;

    /**
     * The lanes that did not settle, each listed once with a reason; lanes, not sources. In a detailed
     * view a lane is listed exactly when one of its signals did not settle; a standard view carries no
     * signals to match.
     */
    @JsonProperty("incomplete_sources")
    private List<IncompleteSource> incompleteSources;

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    @JsonProperty("policy_version")
    private String policyVersion;

    /**
     * Labels the v0 policy as hand-authored and not statistically validated.
     */
    @JsonProperty("policy_basis")
    private String policyBasis;

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    @JsonProperty("assessment_version")
    private long assessmentVersion;

    /**
     * When the assessment was created. Fixed for the life of the assessment: every revision carries the
     * same created_at.
     */
    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * After this instant the assessment is no longer served: GET /v1/assessments/{assessment_id} answers
     * 404 and it leaves the account history. It is created_at plus your retention period at creation
     * (default and maximum 365 days) and is fixed per record: a later revision keeps it, and a new
     * assessment for the same account starts its own clock. If you later shorten retention, the effective
     * expiry becomes created_at plus the shorter of the two periods.
     */
    @JsonProperty("expires_at")
    private String expiresAt;

    /**
     * Server-relative path of the authoritative assessment, GET /v1/assessments/{assessment_id}.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assessment_url")
    private String assessmentUrl;

    @JsonProperty("timing")
    private AssessmentDetailTiming timing;

    /**
     * The identity fields of the auth context the assessment was made with, echoed for the workspace's own
     * correlation: the request's auth_context, or the one a hosted provider hook (POST
     * /hooks/{integration_id}) built, with transport provider_hook and its hook_point. Present exactly
     * when the request carried an auth_context, and the same on every revision of the assessment. Values
     * are as the request or the hook sent them; tokens, timestamps, roles and scopes are not echoed, and
     * the echo does not change the score.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("auth_context")
    private AssessmentDetailAuthContext authContext;

    @JsonCreator
    public AssessmentDetail(
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("identifiers") @Nullable SentIdentifiers identifiers,
            @JsonProperty("request_id") @Nullable String requestId,
            @JsonProperty("status") @Nonnull Status status,
            @JsonProperty("recommendation") @Nullable Recommendation recommendation,
            @JsonProperty("confidence") @Nonnull Confidence confidence,
            @JsonProperty("risk") @Nullable Risk risk,
            @JsonProperty("signals") @Nullable List<Signal> signals,
            @JsonProperty("incomplete_sources") @Nonnull List<IncompleteSource> incompleteSources,
            @JsonProperty("policy_version") @Nonnull String policyVersion,
            @JsonProperty("assessment_version") long assessmentVersion,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("assessment_url") @Nullable String assessmentUrl,
            @JsonProperty("timing") @Nonnull AssessmentDetailTiming timing,
            @JsonProperty("auth_context") @Nullable AssessmentDetailAuthContext authContext) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.identifiers = identifiers;
        this.requestId = requestId;
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.recommendation = recommendation;
        this.confidence = Optional.ofNullable(confidence)
                .orElseThrow(() -> new IllegalArgumentException("confidence cannot be null"));
        this.risk = risk;
        this.signals = signals;
        this.incompleteSources = Optional.ofNullable(incompleteSources)
                .orElseThrow(() -> new IllegalArgumentException("incompleteSources cannot be null"));
        this.policyVersion = Optional.ofNullable(policyVersion)
                .orElseThrow(() -> new IllegalArgumentException("policyVersion cannot be null"));
        this.policyBasis = Builder._SINGLETON_VALUE_PolicyBasis.value();
        this.assessmentVersion = assessmentVersion;
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.assessmentUrl = assessmentUrl;
        this.timing =
                Optional.ofNullable(timing).orElseThrow(() -> new IllegalArgumentException("timing cannot be null"));
        this.authContext = authContext;
    }

    public AssessmentDetail(
            @Nonnull String assessmentId,
            @Nonnull String accountId,
            @Nonnull Status status,
            @Nonnull Confidence confidence,
            @Nonnull List<IncompleteSource> incompleteSources,
            @Nonnull String policyVersion,
            long assessmentVersion,
            @Nonnull String createdAt,
            @Nonnull String updatedAt,
            @Nonnull String expiresAt,
            @Nonnull AssessmentDetailTiming timing) {
        this(
                assessmentId,
                accountId,
                null,
                null,
                status,
                null,
                confidence,
                null,
                null,
                incompleteSources,
                policyVersion,
                assessmentVersion,
                createdAt,
                updatedAt,
                expiresAt,
                null,
                timing,
                null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * The identifiers an assessment was sent: each member of the request's identifiers object exactly as
     * received, before the server normalised it (the email domain keeps its case, the name its Unicode
     * form). A member the request did not send is absent. NoResponse keeps them encrypted under the
     * workspace's own key, does not index or log them, and deletes them with the assessment: on DELETE of
     * the assessment or its account, at its expires_at, and with the workspace.
     */
    public Optional<SentIdentifiers> identifiers() {
        return Optional.ofNullable(this.identifiers);
    }

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    public Optional<String> requestId() {
        return Optional.ofNullable(this.requestId);
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
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public Optional<Recommendation> recommendation() {
        return Optional.ofNullable(this.recommendation);
    }

    /**
     * Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.
     */
    public Confidence confidence() {
        return this.confidence;
    }

    /**
     * The one risk decision for this event. score is 0 (safe) to 100 (risky), set by the reasons that
     * fired under a hand-set, versioned heuristic policy. Each reason carries its direction and impact
     * band; the policy's weights are not published.
     *
     * <p>Every answer with a recommendation carries it, and the top-level recommendation equals
     * risk.recommendation. It names no source or provider.
     */
    public Optional<Risk> risk() {
        return Optional.ofNullable(this.risk);
    }

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope): a standard answer omits
     * signals[], and a detailed one always carries it, possibly empty. The body does not name its mode, so
     * that rule is the serving gate's; the schema states the half it can: an answer with risk.details is a
     * detailed view and carries signals[]. At most one signal per (type, source_id, identifier).
     */
    public Optional<List<Signal>> signals() {
        return Optional.ofNullable(this.signals);
    }

    /**
     * The lanes that did not settle, each listed once with a reason; lanes, not sources. In a detailed
     * view a lane is listed exactly when one of its signals did not settle; a standard view carries no
     * signals to match.
     */
    public List<IncompleteSource> incompleteSources() {
        return this.incompleteSources;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public String policyVersion() {
        return this.policyVersion;
    }

    /**
     * Labels the v0 policy as hand-authored and not statistically validated.
     */
    public String policyBasis() {
        return this.policyBasis;
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
     * When the assessment was created. Fixed for the life of the assessment: every revision carries the
     * same created_at.
     */
    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * After this instant the assessment is no longer served: GET /v1/assessments/{assessment_id} answers
     * 404 and it leaves the account history. It is created_at plus your retention period at creation
     * (default and maximum 365 days) and is fixed per record: a later revision keeps it, and a new
     * assessment for the same account starts its own clock. If you later shorten retention, the effective
     * expiry becomes created_at plus the shorter of the two periods.
     */
    public String expiresAt() {
        return this.expiresAt;
    }

    /**
     * Server-relative path of the authoritative assessment, GET /v1/assessments/{assessment_id}.
     */
    public Optional<String> assessmentUrl() {
        return Optional.ofNullable(this.assessmentUrl);
    }

    public AssessmentDetailTiming timing() {
        return this.timing;
    }

    /**
     * The identity fields of the auth context the assessment was made with, echoed for the workspace's own
     * correlation: the request's auth_context, or the one a hosted provider hook (POST
     * /hooks/{integration_id}) built, with transport provider_hook and its hook_point. Present exactly
     * when the request carried an auth_context, and the same on every revision of the assessment. Values
     * are as the request or the hook sent them; tokens, timestamps, roles and scopes are not echoed, and
     * the echo does not change the score.
     */
    public Optional<AssessmentDetailAuthContext> authContext() {
        return Optional.ofNullable(this.authContext);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public AssessmentDetail withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
     * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
     * yet, carries the server-issued account_id pre: followed by its assessment_id.
     */
    public AssessmentDetail withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * The identifiers an assessment was sent: each member of the request's identifiers object exactly as
     * received, before the server normalised it (the email domain keeps its case, the name its Unicode
     * form). A member the request did not send is absent. NoResponse keeps them encrypted under the
     * workspace's own key, does not index or log them, and deletes them with the assessment: on DELETE of
     * the assessment or its account, at its expires_at, and with the workspace.
     */
    public AssessmentDetail withIdentifiers(@Nullable SentIdentifiers identifiers) {
        this.identifiers = identifiers;
        return this;
    }

    /**
     * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
     * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
     */
    public AssessmentDetail withRequestId(@Nullable String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public AssessmentDetail withStatus(@Nonnull Status status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The recommended action: allow, step_up or review. Your application applies it.
     */
    public AssessmentDetail withRecommendation(@Nullable Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    /**
     * Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.
     */
    public AssessmentDetail withConfidence(@Nonnull Confidence confidence) {
        this.confidence = Utils.checkNotNull(confidence, "confidence");
        return this;
    }

    /**
     * The one risk decision for this event. score is 0 (safe) to 100 (risky), set by the reasons that
     * fired under a hand-set, versioned heuristic policy. Each reason carries its direction and impact
     * band; the policy's weights are not published.
     *
     * <p>Every answer with a recommendation carries it, and the top-level recommendation equals
     * risk.recommendation. It names no source or provider.
     */
    public AssessmentDetail withRisk(@Nullable Risk risk) {
        this.risk = risk;
        return this;
    }

    /**
     * Detailed view only (response_mode=detailed, which needs the detailed scope): a standard answer omits
     * signals[], and a detailed one always carries it, possibly empty. The body does not name its mode, so
     * that rule is the serving gate's; the schema states the half it can: an answer with risk.details is a
     * detailed view and carries signals[]. At most one signal per (type, source_id, identifier).
     */
    public AssessmentDetail withSignals(@Nullable List<Signal> signals) {
        this.signals = signals;
        return this;
    }

    /**
     * The lanes that did not settle, each listed once with a reason; lanes, not sources. In a detailed
     * view a lane is listed exactly when one of its signals did not settle; a standard view carries no
     * signals to match.
     */
    public AssessmentDetail withIncompleteSources(@Nonnull List<IncompleteSource> incompleteSources) {
        this.incompleteSources = Utils.checkNotNull(incompleteSources, "incompleteSources");
        return this;
    }

    /**
     * Version of the hand-authored heuristic policy that produced the recommendation, for example
     * 'risk-2026-10.7'. A workspace threshold override adds a +t&lt;N&gt; suffix, e.g.
     * 'risk-2026-10.7+t3'.
     */
    public AssessmentDetail withPolicyVersion(@Nonnull String policyVersion) {
        this.policyVersion = Utils.checkNotNull(policyVersion, "policyVersion");
        return this;
    }

    /**
     * Monotonic revision of this assessment. 1 on the synchronous answer; each material change while the
     * assessment is served (an async continuation settling a source, a change of recommendation,
     * confidence or status) increments it. Expiry does not increment it: an expired assessment is gone,
     * not revised, and the expiry webhook carries the last served version.
     */
    public AssessmentDetail withAssessmentVersion(long assessmentVersion) {
        this.assessmentVersion = assessmentVersion;
        return this;
    }

    /**
     * When the assessment was created. Fixed for the life of the assessment: every revision carries the
     * same created_at.
     */
    public AssessmentDetail withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public AssessmentDetail withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * After this instant the assessment is no longer served: GET /v1/assessments/{assessment_id} answers
     * 404 and it leaves the account history. It is created_at plus your retention period at creation
     * (default and maximum 365 days) and is fixed per record: a later revision keeps it, and a new
     * assessment for the same account starts its own clock. If you later shorten retention, the effective
     * expiry becomes created_at plus the shorter of the two periods.
     */
    public AssessmentDetail withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    /**
     * Server-relative path of the authoritative assessment, GET /v1/assessments/{assessment_id}.
     */
    public AssessmentDetail withAssessmentUrl(@Nullable String assessmentUrl) {
        this.assessmentUrl = assessmentUrl;
        return this;
    }

    public AssessmentDetail withTiming(@Nonnull AssessmentDetailTiming timing) {
        this.timing = Utils.checkNotNull(timing, "timing");
        return this;
    }

    /**
     * The identity fields of the auth context the assessment was made with, echoed for the workspace's own
     * correlation: the request's auth_context, or the one a hosted provider hook (POST
     * /hooks/{integration_id}) built, with transport provider_hook and its hook_point. Present exactly
     * when the request carried an auth_context, and the same on every revision of the assessment. Values
     * are as the request or the hook sent them; tokens, timestamps, roles and scopes are not echoed, and
     * the echo does not change the score.
     */
    public AssessmentDetail withAuthContext(@Nullable AssessmentDetailAuthContext authContext) {
        this.authContext = authContext;
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
        AssessmentDetail other = (AssessmentDetail) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.identifiers, other.identifiers)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.recommendation, other.recommendation)
                && Utils.enhancedDeepEquals(this.confidence, other.confidence)
                && Utils.enhancedDeepEquals(this.risk, other.risk)
                && Utils.enhancedDeepEquals(this.signals, other.signals)
                && Utils.enhancedDeepEquals(this.incompleteSources, other.incompleteSources)
                && Utils.enhancedDeepEquals(this.policyVersion, other.policyVersion)
                && Utils.enhancedDeepEquals(this.policyBasis, other.policyBasis)
                && Utils.enhancedDeepEquals(this.assessmentVersion, other.assessmentVersion)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.assessmentUrl, other.assessmentUrl)
                && Utils.enhancedDeepEquals(this.timing, other.timing)
                && Utils.enhancedDeepEquals(this.authContext, other.authContext);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                schemaVersion,
                assessmentId,
                accountId,
                identifiers,
                requestId,
                status,
                recommendation,
                confidence,
                risk,
                signals,
                incompleteSources,
                policyVersion,
                policyBasis,
                assessmentVersion,
                createdAt,
                updatedAt,
                expiresAt,
                assessmentUrl,
                timing,
                authContext);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentDetail.class,
                "schemaVersion",
                schemaVersion,
                "assessmentId",
                assessmentId,
                "accountId",
                accountId,
                "identifiers",
                identifiers,
                "requestId",
                requestId,
                "status",
                status,
                "recommendation",
                recommendation,
                "confidence",
                confidence,
                "risk",
                risk,
                "signals",
                signals,
                "incompleteSources",
                incompleteSources,
                "policyVersion",
                policyVersion,
                "policyBasis",
                policyBasis,
                "assessmentVersion",
                assessmentVersion,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "expiresAt",
                expiresAt,
                "assessmentUrl",
                assessmentUrl,
                "timing",
                timing,
                "authContext",
                authContext);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String assessmentId;

        private String accountId;

        private SentIdentifiers identifiers;

        private String requestId;

        private Status status;

        private Recommendation recommendation;

        private Confidence confidence;

        private Risk risk;

        private List<Signal> signals;

        private List<IncompleteSource> incompleteSources;

        private String policyVersion;

        private long assessmentVersion;

        private String createdAt;

        private String updatedAt;

        private String expiresAt;

        private String assessmentUrl;

        private AssessmentDetailTiming timing;

        private AssessmentDetailAuthContext authContext;

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
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id} and its
         * /assessments. An assessment requested by a provider hook at pre_registration, which has no account
         * yet, carries the server-issued account_id pre: followed by its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        /**
         * The identifiers an assessment was sent: each member of the request's identifiers object exactly as
         * received, before the server normalised it (the email domain keeps its case, the name its Unicode
         * form). A member the request did not send is absent. NoResponse keeps them encrypted under the
         * workspace's own key, does not index or log them, and deletes them with the assessment: on DELETE of
         * the assessment or its account, at its expires_at, and with the workspace.
         */
        public Builder identifiers(@Nullable SentIdentifiers identifiers) {
            this.identifiers = identifiers;
            return this;
        }

        /**
         * An optional provider correlation id, echoed back. It is not an idempotency key. Refuses an
         * exact-shape JWT or a bare 40, 64 or 128 character hex digest; a UUID is accepted.
         */
        public Builder requestId(@Nullable String requestId) {
            this.requestId = requestId;
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
         * The recommended action: allow, step_up or review. Your application applies it.
         */
        public Builder recommendation(@Nullable Recommendation recommendation) {
            this.recommendation = recommendation;
            return this;
        }

        /**
         * Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.
         */
        public Builder confidence(@Nonnull Confidence confidence) {
            this.confidence = Utils.checkNotNull(confidence, "confidence");
            return this;
        }

        /**
         * The one risk decision for this event. score is 0 (safe) to 100 (risky), set by the reasons that
         * fired under a hand-set, versioned heuristic policy. Each reason carries its direction and impact
         * band; the policy's weights are not published.
         *
         * <p>Every answer with a recommendation carries it, and the top-level recommendation equals
         * risk.recommendation. It names no source or provider.
         */
        public Builder risk(@Nullable Risk risk) {
            this.risk = risk;
            return this;
        }

        /**
         * Detailed view only (response_mode=detailed, which needs the detailed scope): a standard answer omits
         * signals[], and a detailed one always carries it, possibly empty. The body does not name its mode, so
         * that rule is the serving gate's; the schema states the half it can: an answer with risk.details is a
         * detailed view and carries signals[]. At most one signal per (type, source_id, identifier).
         */
        public Builder signals(@Nullable List<Signal> signals) {
            this.signals = signals;
            return this;
        }

        /**
         * The lanes that did not settle, each listed once with a reason; lanes, not sources. In a detailed
         * view a lane is listed exactly when one of its signals did not settle; a standard view carries no
         * signals to match.
         */
        public Builder incompleteSources(@Nonnull List<IncompleteSource> incompleteSources) {
            this.incompleteSources = Utils.checkNotNull(incompleteSources, "incompleteSources");
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
         * When the assessment was created. Fixed for the life of the assessment: every revision carries the
         * same created_at.
         */
        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder updatedAt(@Nonnull String updatedAt) {
            this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
            return this;
        }

        /**
         * After this instant the assessment is no longer served: GET /v1/assessments/{assessment_id} answers
         * 404 and it leaves the account history. It is created_at plus your retention period at creation
         * (default and maximum 365 days) and is fixed per record: a later revision keeps it, and a new
         * assessment for the same account starts its own clock. If you later shorten retention, the effective
         * expiry becomes created_at plus the shorter of the two periods.
         */
        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        /**
         * Server-relative path of the authoritative assessment, GET /v1/assessments/{assessment_id}.
         */
        public Builder assessmentUrl(@Nullable String assessmentUrl) {
            this.assessmentUrl = assessmentUrl;
            return this;
        }

        public Builder timing(@Nonnull AssessmentDetailTiming timing) {
            this.timing = Utils.checkNotNull(timing, "timing");
            return this;
        }

        /**
         * The identity fields of the auth context the assessment was made with, echoed for the workspace's own
         * correlation: the request's auth_context, or the one a hosted provider hook (POST
         * /hooks/{integration_id}) built, with transport provider_hook and its hook_point. Present exactly
         * when the request carried an auth_context, and the same on every revision of the assessment. Values
         * are as the request or the hook sent them; tokens, timestamps, roles and scopes are not echoed, and
         * the echo does not change the score.
         */
        public Builder authContext(@Nullable AssessmentDetailAuthContext authContext) {
            this.authContext = authContext;
            return this;
        }

        public AssessmentDetail build() {
            return new AssessmentDetail(
                    assessmentId,
                    accountId,
                    identifiers,
                    requestId,
                    status,
                    recommendation,
                    confidence,
                    risk,
                    signals,
                    incompleteSources,
                    policyVersion,
                    assessmentVersion,
                    createdAt,
                    updatedAt,
                    expiresAt,
                    assessmentUrl,
                    timing,
                    authContext);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<String> _SINGLETON_VALUE_PolicyBasis =
                new LazySingletonValue<>("policy_basis", "\"heuristic_unvalidated\"", new TypeReference<String>() {});
    }
}
