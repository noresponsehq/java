package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AccountExport
 *
 * <p>Retrieve one consistent snapshot of retained customer-accessible records, including assessment
 * revisions, account decisions, feedback, key associations, enforcements and request diagnostics.
 * Secrets, restricted source information, other customers' data and legacy free-text notes are
 * excluded. The same standard/detailed permissions apply.
 *
 * <p>An export exceeding 10000 records or 8 MiB returns export_too_large without a partial result.
 * Historical decisions that were not captured are identified as an exclusion.
 */
public class AccountExport {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("exported_at")
    private String exportedAt;

    @JsonProperty("complete")
    private boolean complete;

    @JsonProperty("current_risk_version")
    private long currentRiskVersion;

    @JsonProperty("assessments")
    private List<AssessmentRevision> assessments;

    @JsonProperty("decisions")
    private List<AccountDecisionSnapshot> decisions;

    @JsonProperty("outcomes")
    private List<AccountOutcome> outcomes;

    @JsonProperty("assessment_outcomes")
    private List<ExportAssessmentOutcome> assessmentOutcomes;

    @JsonProperty("credentials")
    private List<CredentialAssociation> credentials;

    @JsonProperty("bindings")
    private List<AccountBinding> bindings;

    @JsonProperty("enforcements")
    private List<Enforcement> enforcements;

    @JsonProperty("requests")
    private List<RequestDiagnostic> requests;

    @JsonProperty("exclusions")
    private List<Exclusion> exclusions;

    @JsonCreator
    public AccountExport(
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("exported_at") @Nonnull String exportedAt,
            @JsonProperty("current_risk_version") long currentRiskVersion,
            @JsonProperty("assessments") @Nonnull List<AssessmentRevision> assessments,
            @JsonProperty("decisions") @Nonnull List<AccountDecisionSnapshot> decisions,
            @JsonProperty("outcomes") @Nonnull List<AccountOutcome> outcomes,
            @JsonProperty("assessment_outcomes") @Nonnull List<ExportAssessmentOutcome> assessmentOutcomes,
            @JsonProperty("credentials") @Nonnull List<CredentialAssociation> credentials,
            @JsonProperty("bindings") @Nonnull List<AccountBinding> bindings,
            @JsonProperty("enforcements") @Nonnull List<Enforcement> enforcements,
            @JsonProperty("requests") @Nonnull List<RequestDiagnostic> requests,
            @JsonProperty("exclusions") @Nonnull List<Exclusion> exclusions) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.exportedAt = Optional.ofNullable(exportedAt)
                .orElseThrow(() -> new IllegalArgumentException("exportedAt cannot be null"));
        this.complete = Builder._SINGLETON_VALUE_Complete.value();
        this.currentRiskVersion = currentRiskVersion;
        this.assessments = Optional.ofNullable(assessments)
                .orElseThrow(() -> new IllegalArgumentException("assessments cannot be null"));
        this.decisions = Optional.ofNullable(decisions)
                .orElseThrow(() -> new IllegalArgumentException("decisions cannot be null"));
        this.outcomes = Optional.ofNullable(outcomes)
                .orElseThrow(() -> new IllegalArgumentException("outcomes cannot be null"));
        this.assessmentOutcomes = Optional.ofNullable(assessmentOutcomes)
                .orElseThrow(() -> new IllegalArgumentException("assessmentOutcomes cannot be null"));
        this.credentials = Optional.ofNullable(credentials)
                .orElseThrow(() -> new IllegalArgumentException("credentials cannot be null"));
        this.bindings = Optional.ofNullable(bindings)
                .orElseThrow(() -> new IllegalArgumentException("bindings cannot be null"));
        this.enforcements = Optional.ofNullable(enforcements)
                .orElseThrow(() -> new IllegalArgumentException("enforcements cannot be null"));
        this.requests = Optional.ofNullable(requests)
                .orElseThrow(() -> new IllegalArgumentException("requests cannot be null"));
        this.exclusions = Optional.ofNullable(exclusions)
                .orElseThrow(() -> new IllegalArgumentException("exclusions cannot be null"));
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public String accountId() {
        return this.accountId;
    }

    public String exportedAt() {
        return this.exportedAt;
    }

    public boolean complete() {
        return this.complete;
    }

    public long currentRiskVersion() {
        return this.currentRiskVersion;
    }

    public List<AssessmentRevision> assessments() {
        return this.assessments;
    }

    public List<AccountDecisionSnapshot> decisions() {
        return this.decisions;
    }

    public List<AccountOutcome> outcomes() {
        return this.outcomes;
    }

    public List<ExportAssessmentOutcome> assessmentOutcomes() {
        return this.assessmentOutcomes;
    }

    public List<CredentialAssociation> credentials() {
        return this.credentials;
    }

    public List<AccountBinding> bindings() {
        return this.bindings;
    }

    public List<Enforcement> enforcements() {
        return this.enforcements;
    }

    public List<RequestDiagnostic> requests() {
        return this.requests;
    }

    public List<Exclusion> exclusions() {
        return this.exclusions;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
     * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
     * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
     * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
     * internal id is a common pseudonymous scheme.
     *
     * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
     * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
     * /assessments and its /linked_accounts. An assessment requested by a provider hook at
     * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
     * its assessment_id.
     */
    public AccountExport withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    public AccountExport withExportedAt(@Nonnull String exportedAt) {
        this.exportedAt = Utils.checkNotNull(exportedAt, "exportedAt");
        return this;
    }

    public AccountExport withCurrentRiskVersion(long currentRiskVersion) {
        this.currentRiskVersion = currentRiskVersion;
        return this;
    }

    public AccountExport withAssessments(@Nonnull List<AssessmentRevision> assessments) {
        this.assessments = Utils.checkNotNull(assessments, "assessments");
        return this;
    }

    public AccountExport withDecisions(@Nonnull List<AccountDecisionSnapshot> decisions) {
        this.decisions = Utils.checkNotNull(decisions, "decisions");
        return this;
    }

    public AccountExport withOutcomes(@Nonnull List<AccountOutcome> outcomes) {
        this.outcomes = Utils.checkNotNull(outcomes, "outcomes");
        return this;
    }

    public AccountExport withAssessmentOutcomes(@Nonnull List<ExportAssessmentOutcome> assessmentOutcomes) {
        this.assessmentOutcomes = Utils.checkNotNull(assessmentOutcomes, "assessmentOutcomes");
        return this;
    }

    public AccountExport withCredentials(@Nonnull List<CredentialAssociation> credentials) {
        this.credentials = Utils.checkNotNull(credentials, "credentials");
        return this;
    }

    public AccountExport withBindings(@Nonnull List<AccountBinding> bindings) {
        this.bindings = Utils.checkNotNull(bindings, "bindings");
        return this;
    }

    public AccountExport withEnforcements(@Nonnull List<Enforcement> enforcements) {
        this.enforcements = Utils.checkNotNull(enforcements, "enforcements");
        return this;
    }

    public AccountExport withRequests(@Nonnull List<RequestDiagnostic> requests) {
        this.requests = Utils.checkNotNull(requests, "requests");
        return this;
    }

    public AccountExport withExclusions(@Nonnull List<Exclusion> exclusions) {
        this.exclusions = Utils.checkNotNull(exclusions, "exclusions");
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
        AccountExport other = (AccountExport) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.exportedAt, other.exportedAt)
                && Utils.enhancedDeepEquals(this.complete, other.complete)
                && Utils.enhancedDeepEquals(this.currentRiskVersion, other.currentRiskVersion)
                && Utils.enhancedDeepEquals(this.assessments, other.assessments)
                && Utils.enhancedDeepEquals(this.decisions, other.decisions)
                && Utils.enhancedDeepEquals(this.outcomes, other.outcomes)
                && Utils.enhancedDeepEquals(this.assessmentOutcomes, other.assessmentOutcomes)
                && Utils.enhancedDeepEquals(this.credentials, other.credentials)
                && Utils.enhancedDeepEquals(this.bindings, other.bindings)
                && Utils.enhancedDeepEquals(this.enforcements, other.enforcements)
                && Utils.enhancedDeepEquals(this.requests, other.requests)
                && Utils.enhancedDeepEquals(this.exclusions, other.exclusions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                schemaVersion,
                accountId,
                exportedAt,
                complete,
                currentRiskVersion,
                assessments,
                decisions,
                outcomes,
                assessmentOutcomes,
                credentials,
                bindings,
                enforcements,
                requests,
                exclusions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountExport.class,
                "schemaVersion",
                schemaVersion,
                "accountId",
                accountId,
                "exportedAt",
                exportedAt,
                "complete",
                complete,
                "currentRiskVersion",
                currentRiskVersion,
                "assessments",
                assessments,
                "decisions",
                decisions,
                "outcomes",
                outcomes,
                "assessmentOutcomes",
                assessmentOutcomes,
                "credentials",
                credentials,
                "bindings",
                bindings,
                "enforcements",
                enforcements,
                "requests",
                requests,
                "exclusions",
                exclusions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private String exportedAt;

        private long currentRiskVersion;

        private List<AssessmentRevision> assessments;

        private List<AccountDecisionSnapshot> decisions;

        private List<AccountOutcome> outcomes;

        private List<ExportAssessmentOutcome> assessmentOutcomes;

        private List<CredentialAssociation> credentials;

        private List<AccountBinding> bindings;

        private List<Enforcement> enforcements;

        private List<RequestDiagnostic> requests;

        private List<Exclusion> exclusions;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your own opaque identifier for the account being checked, scoped to your workspace. Use an id you
         * mint, with no direct identifier (email, phone, name or national id) in it. '|' is accepted so an
         * Auth0 user id such as 'auth0|64abc' passes unchanged; '@', '+', '$' and whitespace are refused by
         * the pattern, as is an exact-shape JWT; a hex digest such as a SHA-256 is allowed, because hashing an
         * internal id is a common pseudonymous scheme.
         *
         * <p>A value such as '07700900123' or 'jane.doe.gmail.com' cannot be told apart from an opaque id. It
         * travels in bodies, and in a URL only as the path segment of GET /v1/accounts/{account_id}, its
         * /assessments and its /linked_accounts. An assessment requested by a provider hook at
         * pre_registration, which has no account yet, carries the server-issued account_id pre: followed by
         * its assessment_id.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        public Builder exportedAt(@Nonnull String exportedAt) {
            this.exportedAt = Utils.checkNotNull(exportedAt, "exportedAt");
            return this;
        }

        public Builder currentRiskVersion(long currentRiskVersion) {
            this.currentRiskVersion = currentRiskVersion;
            return this;
        }

        public Builder assessments(@Nonnull List<AssessmentRevision> assessments) {
            this.assessments = Utils.checkNotNull(assessments, "assessments");
            return this;
        }

        public Builder decisions(@Nonnull List<AccountDecisionSnapshot> decisions) {
            this.decisions = Utils.checkNotNull(decisions, "decisions");
            return this;
        }

        public Builder outcomes(@Nonnull List<AccountOutcome> outcomes) {
            this.outcomes = Utils.checkNotNull(outcomes, "outcomes");
            return this;
        }

        public Builder assessmentOutcomes(@Nonnull List<ExportAssessmentOutcome> assessmentOutcomes) {
            this.assessmentOutcomes = Utils.checkNotNull(assessmentOutcomes, "assessmentOutcomes");
            return this;
        }

        public Builder credentials(@Nonnull List<CredentialAssociation> credentials) {
            this.credentials = Utils.checkNotNull(credentials, "credentials");
            return this;
        }

        public Builder bindings(@Nonnull List<AccountBinding> bindings) {
            this.bindings = Utils.checkNotNull(bindings, "bindings");
            return this;
        }

        public Builder enforcements(@Nonnull List<Enforcement> enforcements) {
            this.enforcements = Utils.checkNotNull(enforcements, "enforcements");
            return this;
        }

        public Builder requests(@Nonnull List<RequestDiagnostic> requests) {
            this.requests = Utils.checkNotNull(requests, "requests");
            return this;
        }

        public Builder exclusions(@Nonnull List<Exclusion> exclusions) {
            this.exclusions = Utils.checkNotNull(exclusions, "exclusions");
            return this;
        }

        public AccountExport build() {
            return new AccountExport(
                    accountId,
                    exportedAt,
                    currentRiskVersion,
                    assessments,
                    decisions,
                    outcomes,
                    assessmentOutcomes,
                    credentials,
                    bindings,
                    enforcements,
                    requests,
                    exclusions);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_Complete =
                new LazySingletonValue<>("complete", "true", new TypeReference<Boolean>() {});
    }
}
