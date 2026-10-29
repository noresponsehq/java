package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AccountDirectoryPageItem {
    /**
     * Present for needs_action searches. outcome means a current review/block decision lacks a retained
     * effective outcome; review means a retained open or in-progress review. Both can apply.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("waiting_for")
    private List<WaitingFor> waitingFor;

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
     * Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     */
    @JsonProperty("latest")
    private AssessmentRevision latest;

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
    @JsonProperty("current")
    private Account current;

    /**
     * Count of admitted retained assessments belonging to this account, not just the returned page.
     */
    @JsonProperty("assessments")
    private long assessments;

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

    @JsonCreator
    public AccountDirectoryPageItem(
            @JsonProperty("waiting_for") @Nullable List<WaitingFor> waitingFor,
            @JsonProperty("account_id") @Nonnull String accountId,
            @JsonProperty("latest") @Nonnull AssessmentRevision latest,
            @JsonProperty("current") @Nullable Account current,
            @JsonProperty("assessments") long assessments,
            @JsonProperty("matched_identifier") @Nullable IdentifierMatch matchedIdentifier) {
        this.waitingFor = waitingFor;
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.latest =
                Optional.ofNullable(latest).orElseThrow(() -> new IllegalArgumentException("latest cannot be null"));
        this.current = current;
        this.assessments = assessments;
        this.matchedIdentifier = matchedIdentifier;
    }

    public AccountDirectoryPageItem(@Nonnull String accountId, @Nonnull AssessmentRevision latest, long assessments) {
        this(null, accountId, latest, null, assessments, null);
    }

    /**
     * Present for needs_action searches. outcome means a current review/block decision lacks a retained
     * effective outcome; review means a retained open or in-progress review. Both can apply.
     */
    public Optional<List<WaitingFor>> waitingFor() {
        return Optional.ofNullable(this.waitingFor);
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
     * Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     */
    public AssessmentRevision latest() {
        return this.latest;
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
    public Optional<Account> current() {
        return Optional.ofNullable(this.current);
    }

    /**
     * Count of admitted retained assessments belonging to this account, not just the returned page.
     */
    public long assessments() {
        return this.assessments;
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

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Present for needs_action searches. outcome means a current review/block decision lacks a retained
     * effective outcome; review means a retained open or in-progress review. Both can apply.
     */
    public AccountDirectoryPageItem withWaitingFor(@Nullable List<WaitingFor> waitingFor) {
        this.waitingFor = waitingFor;
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
    public AccountDirectoryPageItem withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * Retrieve a retained assessment version with its original decision, policy, flow and depth. This
     * response excludes the current account decision. Current retention and response-mode permissions
     * still apply.
     */
    public AccountDirectoryPageItem withLatest(@Nonnull AssessmentRevision latest) {
        this.latest = Utils.checkNotNull(latest, "latest");
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
    public AccountDirectoryPageItem withCurrent(@Nullable Account current) {
        this.current = current;
        return this;
    }

    /**
     * Count of admitted retained assessments belonging to this account, not just the returned page.
     */
    public AccountDirectoryPageItem withAssessments(long assessments) {
        this.assessments = assessments;
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
    public AccountDirectoryPageItem withMatchedIdentifier(@Nullable IdentifierMatch matchedIdentifier) {
        this.matchedIdentifier = matchedIdentifier;
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
        AccountDirectoryPageItem other = (AccountDirectoryPageItem) o;
        return Utils.enhancedDeepEquals(this.waitingFor, other.waitingFor)
                && Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.latest, other.latest)
                && Utils.enhancedDeepEquals(this.current, other.current)
                && Utils.enhancedDeepEquals(this.assessments, other.assessments)
                && Utils.enhancedDeepEquals(this.matchedIdentifier, other.matchedIdentifier);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(waitingFor, accountId, latest, current, assessments, matchedIdentifier);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDirectoryPageItem.class,
                "waitingFor",
                waitingFor,
                "accountId",
                accountId,
                "latest",
                latest,
                "current",
                current,
                "assessments",
                assessments,
                "matchedIdentifier",
                matchedIdentifier);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<WaitingFor> waitingFor;

        private String accountId;

        private AssessmentRevision latest;

        private Account current;

        private long assessments;

        private IdentifierMatch matchedIdentifier;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Present for needs_action searches. outcome means a current review/block decision lacks a retained
         * effective outcome; review means a retained open or in-progress review. Both can apply.
         */
        public Builder waitingFor(@Nullable List<WaitingFor> waitingFor) {
            this.waitingFor = waitingFor;
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
         * Retrieve a retained assessment version with its original decision, policy, flow and depth. This
         * response excludes the current account decision. Current retention and response-mode permissions
         * still apply.
         */
        public Builder latest(@Nonnull AssessmentRevision latest) {
            this.latest = Utils.checkNotNull(latest, "latest");
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
        public Builder current(@Nullable Account current) {
            this.current = current;
            return this;
        }

        /**
         * Count of admitted retained assessments belonging to this account, not just the returned page.
         */
        public Builder assessments(long assessments) {
            this.assessments = assessments;
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

        public AccountDirectoryPageItem build() {
            return new AccountDirectoryPageItem(waitingFor, accountId, latest, current, assessments, matchedIdentifier);
        }
    }
}
