package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.AssociateCredentialRequest;
import org.noresponse.models.operations.AssociateCredentialRequestBuilder;
import org.noresponse.models.operations.AssociateCredentialResponse;
import org.noresponse.models.operations.DeleteAccountRequest;
import org.noresponse.models.operations.DeleteAccountRequestBuilder;
import org.noresponse.models.operations.DeleteAccountResponse;
import org.noresponse.models.operations.ExportAccountRequest;
import org.noresponse.models.operations.ExportAccountRequestBuilder;
import org.noresponse.models.operations.ExportAccountResponse;
import org.noresponse.models.operations.GetAccountDecisionRequest;
import org.noresponse.models.operations.GetAccountDecisionRequestBuilder;
import org.noresponse.models.operations.GetAccountDecisionResponse;
import org.noresponse.models.operations.GetAccountRequest;
import org.noresponse.models.operations.GetAccountRequestBuilder;
import org.noresponse.models.operations.GetAccountResponse;
import org.noresponse.models.operations.ListAccountAssessmentsRequest;
import org.noresponse.models.operations.ListAccountAssessmentsRequestBuilder;
import org.noresponse.models.operations.ListAccountAssessmentsResponse;
import org.noresponse.models.operations.ListAccountCredentialsRequest;
import org.noresponse.models.operations.ListAccountCredentialsRequestBuilder;
import org.noresponse.models.operations.ListAccountCredentialsResponse;
import org.noresponse.models.operations.ListAccountDecisionsRequest;
import org.noresponse.models.operations.ListAccountDecisionsRequestBuilder;
import org.noresponse.models.operations.ListAccountDecisionsResponse;
import org.noresponse.models.operations.ListAccountEnforcementsRequest;
import org.noresponse.models.operations.ListAccountEnforcementsRequestBuilder;
import org.noresponse.models.operations.ListAccountEnforcementsResponse;
import org.noresponse.models.operations.ListAccountOutcomesRequest;
import org.noresponse.models.operations.ListAccountOutcomesRequestBuilder;
import org.noresponse.models.operations.ListAccountOutcomesResponse;
import org.noresponse.models.operations.ListAccountsRequest;
import org.noresponse.models.operations.ListAccountsRequestBuilder;
import org.noresponse.models.operations.ListAccountsResponse;
import org.noresponse.models.operations.RecordAccountOutcomeRequest;
import org.noresponse.models.operations.RecordAccountOutcomeRequestBuilder;
import org.noresponse.models.operations.RecordAccountOutcomeResponse;
import org.noresponse.models.operations.RecordEnforcementRequest;
import org.noresponse.models.operations.RecordEnforcementRequestBuilder;
import org.noresponse.models.operations.RecordEnforcementResponse;
import org.noresponse.models.operations.RetireAccountCredentialRequest;
import org.noresponse.models.operations.RetireAccountCredentialRequestBuilder;
import org.noresponse.models.operations.RetireAccountCredentialResponse;
import org.noresponse.models.operations.SearchAccountsRequestBuilder;
import org.noresponse.models.operations.SearchAccountsResponse;
import org.noresponse.models.shared.AccountDirectorySearchRequest;
import org.noresponse.models.shared.AccountOutcomeRequest;
import org.noresponse.models.shared.CredentialRequest;
import org.noresponse.models.shared.EnforcementRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.AssociateCredential;
import org.noresponse.operations.DeleteAccount;
import org.noresponse.operations.ExportAccount;
import org.noresponse.operations.GetAccount;
import org.noresponse.operations.GetAccountDecision;
import org.noresponse.operations.ListAccountAssessments;
import org.noresponse.operations.ListAccountCredentials;
import org.noresponse.operations.ListAccountDecisions;
import org.noresponse.operations.ListAccountEnforcements;
import org.noresponse.operations.ListAccountOutcomes;
import org.noresponse.operations.ListAccounts;
import org.noresponse.operations.RecordAccountOutcome;
import org.noresponse.operations.RecordEnforcement;
import org.noresponse.operations.RetireAccountCredential;
import org.noresponse.operations.SearchAccounts;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Your accounts, each with one current risk, and their assessment history.
 */
public class Accounts {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncAccounts asyncSDK;

    Accounts(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncAccounts(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncAccounts async() {
        return asyncSDK;
    }

    /**
     * Poll the accounts whose risk changed.
     *
     * <p>Your accounts whose risk changed, oldest change first, each as accounts.get answers it. Every page
     * carries next_cursor: pass it back as changed_since to read the changes made after that page; without
     * changed_since the list starts from the oldest change. When nothing changed, items is empty and
     * next_cursor is the cursor you sent, so poll by calling again with the latest next_cursor.
     *
     * <p>An account appears again each time its risk changes, with a higher risk_version; keep the copy with
     * the highest. unenforced=true lists only accounts with a decision other than allow for some flow and
     * no applied enforcement at their current risk_version; such a page can hold fewer than limit items,
     * or none, while later changes exist, so keep polling with next_cursor. Requires the history scope.
     *
     * <p>Read-only and safe to retry. A tampered or foreign cursor answers 400 invalid_cursor.
     *
     * @return The call builder
     */
    public ListAccountsRequestBuilder listChanged() {
        return new ListAccountsRequestBuilder(sdkConfiguration);
    }

    /**
     * Poll the accounts whose risk changed.
     *
     * <p>Your accounts whose risk changed, oldest change first, each as accounts.get answers it. Every page
     * carries next_cursor: pass it back as changed_since to read the changes made after that page; without
     * changed_since the list starts from the oldest change. When nothing changed, items is empty and
     * next_cursor is the cursor you sent, so poll by calling again with the latest next_cursor.
     *
     * <p>An account appears again each time its risk changes, with a higher risk_version; keep the copy with
     * the highest. unenforced=true lists only accounts with a decision other than allow for some flow and
     * no applied enforcement at their current risk_version; such a page can hold fewer than limit items,
     * or none, while later changes exist, so keep polling with next_cursor. Requires the history scope.
     *
     * <p>Read-only and safe to retry. A tampered or foreign cursor answers 400 invalid_cursor.
     *
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountsResponse listChangedDirect() {
        return listChanged(null, null, null, null);
    }

    /**
     * Poll the accounts whose risk changed.
     *
     * <p>Your accounts whose risk changed, oldest change first, each as accounts.get answers it. Every page
     * carries next_cursor: pass it back as changed_since to read the changes made after that page; without
     * changed_since the list starts from the oldest change. When nothing changed, items is empty and
     * next_cursor is the cursor you sent, so poll by calling again with the latest next_cursor.
     *
     * <p>An account appears again each time its risk changes, with a higher risk_version; keep the copy with
     * the highest. unenforced=true lists only accounts with a decision other than allow for some flow and
     * no applied enforcement at their current risk_version; such a page can hold fewer than limit items,
     * or none, while later changes exist, so keep polling with next_cursor. Requires the history scope.
     *
     * <p>Read-only and safe to retry. A tampered or foreign cursor answers 400 invalid_cursor.
     *
     * @param changedSince next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.
     * @param limit Most accounts per page.
     * @param unenforced true lists only accounts with no applied enforcement at their current risk_version that have a decision other than allow for some flow or a hold no later applied enforcement resolved.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountsResponse listChanged(
            @Nullable String changedSince,
            @Nullable Long limit,
            @Nullable Boolean unenforced,
            @Nullable Options options) {
        ListAccountsRequest request = new ListAccountsRequest(changedSince, limit, unenforced);
        RequestOperation<ListAccountsRequest, ListAccountsResponse> operation =
                new ListAccounts.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read an account's one current risk.
     *
     * <p>An account is the account_id you send with your assessments. Its current risk is the risk of its
     * latest assessment with a decision (status complete or partial), scored as GET
     * /v1/assessments/{assessment_id} scores that assessment, with a decision for each flow; risk.decision
     * is the decision for the flow of that assessment. Every assessment answers with this same account.
     *
     * <p>Every assessment updates its account: once an assessment completes, the account carries its risk.
     * Requires the history scope. Read-only and safe to retry.
     *
     * <p>An account with no unexpired assessment that has a decision answers 404 not_found, and a malformed
     * account_id answers 400 invalid_request.
     *
     * @return The call builder
     */
    public GetAccountRequestBuilder get() {
        return new GetAccountRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an account's one current risk.
     *
     * <p>An account is the account_id you send with your assessments. Its current risk is the risk of its
     * latest assessment with a decision (status complete or partial), scored as GET
     * /v1/assessments/{assessment_id} scores that assessment, with a decision for each flow; risk.decision
     * is the decision for the flow of that assessment. Every assessment answers with this same account.
     *
     * <p>Every assessment updates its account: once an assessment completes, the account carries its risk.
     * Requires the history scope. Read-only and safe to retry.
     *
     * <p>An account with no unexpired assessment that has a decision answers 404 not_found, and a malformed
     * account_id answers 400 invalid_request.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAccountResponse get(@Nonnull String accountId) {
        return get(accountId, null);
    }

    /**
     * Read an account's one current risk.
     *
     * <p>An account is the account_id you send with your assessments. Its current risk is the risk of its
     * latest assessment with a decision (status complete or partial), scored as GET
     * /v1/assessments/{assessment_id} scores that assessment, with a decision for each flow; risk.decision
     * is the decision for the flow of that assessment. Every assessment answers with this same account.
     *
     * <p>Every assessment updates its account: once an assessment completes, the account carries its risk.
     * Requires the history scope. Read-only and safe to retry.
     *
     * <p>An account with no unexpired assessment that has a decision answers 404 not_found, and a malformed
     * account_id answers 400 invalid_request.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAccountResponse get(@Nonnull String accountId, @Nullable Options options) {
        GetAccountRequest request = new GetAccountRequest(accountId);
        RequestOperation<GetAccountRequest, GetAccountResponse> operation =
                new GetAccount.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Erase an account and its history.
     *
     * <p>Erases the account in the environment of your key, for an erasure request from your user: every
     * assessment of it, with its revisions, evidence and outcomes, and the identity-graph marks its
     * outcomes set. An assessment of the account begun before the call is refused or stopped. Afterwards
     * the account and each erased assessment answer 404 and the history is empty.
     *
     * <p>When the call deleted at least one assessment, an account.deleted event goes to your verified
     * webhook endpoints. Requires the history scope. Idempotent and safe to retry: a repeat, or an account
     * with nothing to erase, answers 200 with assessments_deleted 0 and sends no event.
     *
     * <p>The account_id can be assessed again later, as a new account.
     *
     * @return The call builder
     */
    public DeleteAccountRequestBuilder delete() {
        return new DeleteAccountRequestBuilder(sdkConfiguration);
    }

    /**
     * Erase an account and its history.
     *
     * <p>Erases the account in the environment of your key, for an erasure request from your user: every
     * assessment of it, with its revisions, evidence and outcomes, and the identity-graph marks its
     * outcomes set. An assessment of the account begun before the call is refused or stopped. Afterwards
     * the account and each erased assessment answer 404 and the history is empty.
     *
     * <p>When the call deleted at least one assessment, an account.deleted event goes to your verified
     * webhook endpoints. Requires the history scope. Idempotent and safe to retry: a repeat, or an account
     * with nothing to erase, answers 200 with assessments_deleted 0 and sends no event.
     *
     * <p>The account_id can be assessed again later, as a new account.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteAccountResponse delete(@Nonnull String accountId) {
        return delete(accountId, null);
    }

    /**
     * Erase an account and its history.
     *
     * <p>Erases the account in the environment of your key, for an erasure request from your user: every
     * assessment of it, with its revisions, evidence and outcomes, and the identity-graph marks its
     * outcomes set. An assessment of the account begun before the call is refused or stopped. Afterwards
     * the account and each erased assessment answer 404 and the history is empty.
     *
     * <p>When the call deleted at least one assessment, an account.deleted event goes to your verified
     * webhook endpoints. Requires the history scope. Idempotent and safe to retry: a repeat, or an account
     * with nothing to erase, answers 200 with assessments_deleted 0 and sends no event.
     *
     * <p>The account_id can be assessed again later, as a new account.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteAccountResponse delete(@Nonnull String accountId, @Nullable Options options) {
        DeleteAccountRequest request = new DeleteAccountRequest(accountId);
        RequestOperation<DeleteAccountRequest, DeleteAccountResponse> operation =
                new DeleteAccount.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>The account's history: the same page as POST /v1/assessments/query for this account_id, newest
     * first, each at its latest assessment_version. Requires the history scope. Read-only and safe to
     * retry.
     *
     * <p>An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your
     * workspace and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>Each item's identifiers holds the identifiers its assessment was sent, exactly as received. The
     * account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @return The call builder
     */
    public ListAccountAssessmentsRequestBuilder listAssessments() {
        return new ListAccountAssessmentsRequestBuilder(sdkConfiguration);
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>The account's history: the same page as POST /v1/assessments/query for this account_id, newest
     * first, each at its latest assessment_version. Requires the history scope. Read-only and safe to
     * retry.
     *
     * <p>An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your
     * workspace and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>Each item's identifiers holds the identifiers its assessment was sent, exactly as received. The
     * account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountAssessmentsResponse listAssessments(@Nonnull String accountId) {
        return listAssessments(accountId, null, null, null, null);
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>The account's history: the same page as POST /v1/assessments/query for this account_id, newest
     * first, each at its latest assessment_version. Requires the history scope. Read-only and safe to
     * retry.
     *
     * <p>An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your
     * workspace and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>Each item's identifiers holds the identifiers its assessment was sent, exactly as received. The
     * account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param responseMode standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountAssessmentsResponse listAssessments(
            @Nonnull String accountId,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable ResponseMode responseMode,
            @Nullable Options options) {
        ListAccountAssessmentsRequest request =
                new ListAccountAssessmentsRequest(accountId, limit, cursor, responseMode);
        RequestOperation<ListAccountAssessmentsRequest, ListAccountAssessmentsResponse> operation =
                new ListAccountAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record what you did to an account in your identity provider.
     *
     * <p>Records what you did to the account in your identity provider for one flow's decision at one
     * risk_version: the action (or none), the account's state before it, the provider and whether it was
     * applied or failed. It is your action, not an outcome, and it leaves the account's risk unchanged. A
     * record is kept once per account, risk_version, flow and action: the first report answers 201, and a
     * repeat answers 200 with the stored record unchanged, so retries are safe without an Idempotency-Key.
     *
     * <p>An applied report after a failed one replaces the failed record, keeping its id and created_at, and
     * answers 201. A risk_version older than the account's current one is accepted and stored, but only an
     * applied record at the current risk_version takes the account out of listChanged with
     * unenforced=true; a newer one answers 409 risk_version_ahead. An account with no current risk answers
     * 404 not_found.
     *
     * <p>error_code is allowed only with status failed. Requires the history scope.
     *
     * @return The call builder
     */
    public RecordEnforcementRequestBuilder recordEnforcement() {
        return new RecordEnforcementRequestBuilder(sdkConfiguration);
    }

    /**
     * Record what you did to an account in your identity provider.
     *
     * <p>Records what you did to the account in your identity provider for one flow's decision at one
     * risk_version: the action (or none), the account's state before it, the provider and whether it was
     * applied or failed. It is your action, not an outcome, and it leaves the account's risk unchanged. A
     * record is kept once per account, risk_version, flow and action: the first report answers 201, and a
     * repeat answers 200 with the stored record unchanged, so retries are safe without an Idempotency-Key.
     *
     * <p>An applied report after a failed one replaces the failed record, keeping its id and created_at, and
     * answers 201. A risk_version older than the account's current one is accepted and stored, but only an
     * applied record at the current risk_version takes the account out of listChanged with
     * unenforced=true; a newer one answers 409 risk_version_ahead. An account with no current risk answers
     * 404 not_found.
     *
     * <p>error_code is allowed only with status failed. Requires the history scope.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param body POST /v1/accounts/{account_id}/enforcements body: what the workspace did to the account in its identity provider after reading the account's risk at risk_version. The account is named by the path and the workspace by the API key, so neither is a body member. An enforcement is the workspace's own action, not an outcome: what the workspace later learned about an assessment goes to POST /v1/assessments/{assessment_id}/outcomes. The record's key is (account, risk_version, flow, action), so a retry of the same report is safe and needs no Idempotency-Key.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordEnforcementResponse recordEnforcement(@Nonnull String accountId, @Nonnull EnforcementRequest body) {
        return recordEnforcement(accountId, body, null);
    }

    /**
     * Record what you did to an account in your identity provider.
     *
     * <p>Records what you did to the account in your identity provider for one flow's decision at one
     * risk_version: the action (or none), the account's state before it, the provider and whether it was
     * applied or failed. It is your action, not an outcome, and it leaves the account's risk unchanged. A
     * record is kept once per account, risk_version, flow and action: the first report answers 201, and a
     * repeat answers 200 with the stored record unchanged, so retries are safe without an Idempotency-Key.
     *
     * <p>An applied report after a failed one replaces the failed record, keeping its id and created_at, and
     * answers 201. A risk_version older than the account's current one is accepted and stored, but only an
     * applied record at the current risk_version takes the account out of listChanged with
     * unenforced=true; a newer one answers 409 risk_version_ahead. An account with no current risk answers
     * 404 not_found.
     *
     * <p>error_code is allowed only with status failed. Requires the history scope.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param body POST /v1/accounts/{account_id}/enforcements body: what the workspace did to the account in its identity provider after reading the account's risk at risk_version. The account is named by the path and the workspace by the API key, so neither is a body member. An enforcement is the workspace's own action, not an outcome: what the workspace later learned about an assessment goes to POST /v1/assessments/{assessment_id}/outcomes. The record's key is (account, risk_version, flow, action), so a retry of the same report is safe and needs no Idempotency-Key.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordEnforcementResponse recordEnforcement(
            @Nonnull String accountId, @Nonnull EnforcementRequest body, @Nullable Options options) {
        RecordEnforcementRequest request = new RecordEnforcementRequest(accountId, body);
        RequestOperation<RecordEnforcementRequest, RecordEnforcementResponse> operation =
                new RecordEnforcement.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Page through account enforcements
     *
     * <p>List retained enforcement reports for one account with an authenticated cursor.
     *
     * @return The call builder
     */
    public ListAccountEnforcementsRequestBuilder listEnforcements() {
        return new ListAccountEnforcementsRequestBuilder(sdkConfiguration);
    }

    /**
     * Page through account enforcements
     *
     * <p>List retained enforcement reports for one account with an authenticated cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountEnforcementsResponse listEnforcements(@Nonnull String accountId) {
        return listEnforcements(accountId, null, null, null);
    }

    /**
     * Page through account enforcements
     *
     * <p>List retained enforcement reports for one account with an authenticated cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountEnforcementsResponse listEnforcements(
            @Nonnull String accountId, @Nullable Long limit, @Nullable String cursor, @Nullable Options options) {
        ListAccountEnforcementsRequest request = new ListAccountEnforcementsRequest(accountId, limit, cursor);
        RequestOperation<ListAccountEnforcementsRequest, ListAccountEnforcementsResponse> operation =
                new ListAccountEnforcements.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record or correct an account outcome
     *
     * <p>Record a factual report, not an enforcement action. A known outcome replaces the effective report.
     * unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts
     * it.
     *
     * <p>supersedes must name the current effective report or the request answers 409 outcome_conflict.
     * Reports remain append-only. Idempotency-Key is required.
     *
     * <p>observed_at defaults to receipt time and cannot be over five minutes ahead.
     *
     * @return The call builder
     */
    public RecordAccountOutcomeRequestBuilder recordOutcome() {
        return new RecordAccountOutcomeRequestBuilder(sdkConfiguration);
    }

    /**
     * Record or correct an account outcome
     *
     * <p>Record a factual report, not an enforcement action. A known outcome replaces the effective report.
     * unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts
     * it.
     *
     * <p>supersedes must name the current effective report or the request answers 409 outcome_conflict.
     * Reports remain append-only. Idempotency-Key is required.
     *
     * <p>observed_at defaults to receipt time and cannot be over five minutes ahead.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Record a factual report, not an enforcement action. A known outcome replaces the effective report. unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts it. supersedes must name the current effective report or the request answers 409 outcome_conflict. Reports remain append-only. Idempotency-Key is required. observed_at defaults to receipt time and cannot be over five minutes ahead.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordAccountOutcomeResponse recordOutcome(
            @Nonnull String accountId, @Nonnull String idempotencyKey, @Nonnull AccountOutcomeRequest body) {
        return recordOutcome(accountId, idempotencyKey, body, null);
    }

    /**
     * Record or correct an account outcome
     *
     * <p>Record a factual report, not an enforcement action. A known outcome replaces the effective report.
     * unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts
     * it.
     *
     * <p>supersedes must name the current effective report or the request answers 409 outcome_conflict.
     * Reports remain append-only. Idempotency-Key is required.
     *
     * <p>observed_at defaults to receipt time and cannot be over five minutes ahead.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Record a factual report, not an enforcement action. A known outcome replaces the effective report. unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts it. supersedes must name the current effective report or the request answers 409 outcome_conflict. Reports remain append-only. Idempotency-Key is required. observed_at defaults to receipt time and cannot be over five minutes ahead.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordAccountOutcomeResponse recordOutcome(
            @Nonnull String accountId,
            @Nonnull String idempotencyKey,
            @Nonnull AccountOutcomeRequest body,
            @Nullable Options options) {
        RecordAccountOutcomeRequest request = new RecordAccountOutcomeRequest(accountId, idempotencyKey, body);
        RequestOperation<RecordAccountOutcomeRequest, RecordAccountOutcomeResponse> operation =
                new RecordAccountOutcome.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List retained account feedback
     *
     * <p>List retained account reports, newest first. The page-level effective outcome id names the current
     * report. Each receipt preserves the effective report and risk version from its original write.
     *
     * <p>Page with the returned cursor.
     *
     * @return The call builder
     */
    public ListAccountOutcomesRequestBuilder listOutcomes() {
        return new ListAccountOutcomesRequestBuilder(sdkConfiguration);
    }

    /**
     * List retained account feedback
     *
     * <p>List retained account reports, newest first. The page-level effective outcome id names the current
     * report. Each receipt preserves the effective report and risk version from its original write.
     *
     * <p>Page with the returned cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountOutcomesResponse listOutcomes(@Nonnull String accountId) {
        return listOutcomes(accountId, null, null, null);
    }

    /**
     * List retained account feedback
     *
     * <p>List retained account reports, newest first. The page-level effective outcome id names the current
     * report. Each receipt preserves the effective report and risk version from its original write.
     *
     * <p>Page with the returned cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountOutcomesResponse listOutcomes(
            @Nonnull String accountId, @Nullable Long limit, @Nullable String cursor, @Nullable Options options) {
        ListAccountOutcomesRequest request = new ListAccountOutcomesRequest(accountId, limit, cursor);
        RequestOperation<ListAccountOutcomesRequest, ListAccountOutcomesResponse> operation =
                new ListAccountOutcomes.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Retrieve an exact historical account decision
     *
     * <p>Retrieve the account decision at one recorded risk version. The answer uses the original assessment
     * and account-specific risk inputs. Earlier versions that were not captured return 404.
     *
     * <p>The assessment version, policy version and account calculation version identify the decision.
     *
     * @return The call builder
     */
    public GetAccountDecisionRequestBuilder retrieveDecision() {
        return new GetAccountDecisionRequestBuilder(sdkConfiguration);
    }

    /**
     * Retrieve an exact historical account decision
     *
     * <p>Retrieve the account decision at one recorded risk version. The answer uses the original assessment
     * and account-specific risk inputs. Earlier versions that were not captured return 404.
     *
     * <p>The assessment version, policy version and account calculation version identify the decision.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param riskVersion The canonical decimal risk_version originally served.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAccountDecisionResponse retrieveDecision(@Nonnull String accountId, long riskVersion) {
        return retrieveDecision(accountId, riskVersion, null);
    }

    /**
     * Retrieve an exact historical account decision
     *
     * <p>Retrieve the account decision at one recorded risk version. The answer uses the original assessment
     * and account-specific risk inputs. Earlier versions that were not captured return 404.
     *
     * <p>The assessment version, policy version and account calculation version identify the decision.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param riskVersion The canonical decimal risk_version originally served.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAccountDecisionResponse retrieveDecision(
            @Nonnull String accountId, long riskVersion, @Nullable Options options) {
        GetAccountDecisionRequest request = new GetAccountDecisionRequest(accountId, riskVersion);
        RequestOperation<GetAccountDecisionRequest, GetAccountDecisionResponse> operation =
                new GetAccountDecision.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Associate a customer-issued API key
     *
     * <p>Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the
     * key. Repeating a hash returns its original association, including its retirement state.
     *
     * <p>A hash associated with another account returns a conflict.
     *
     * @return The call builder
     */
    public AssociateCredentialRequestBuilder associateCredential() {
        return new AssociateCredentialRequestBuilder(sdkConfiguration);
    }

    /**
     * Associate a customer-issued API key
     *
     * <p>Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the
     * key. Repeating a hash returns its original association, including its retirement state.
     *
     * <p>A hash associated with another account returns a conflict.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param body Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the key. Repeating a hash returns its original association, including its retirement state. A hash associated with another account returns a conflict.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AssociateCredentialResponse associateCredential(@Nonnull String accountId, @Nonnull CredentialRequest body) {
        return associateCredential(accountId, body, null);
    }

    /**
     * Associate a customer-issued API key
     *
     * <p>Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the
     * key. Repeating a hash returns its original association, including its retirement state.
     *
     * <p>A hash associated with another account returns a conflict.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param body Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the key. Repeating a hash returns its original association, including its retirement state. A hash associated with another account returns a conflict.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AssociateCredentialResponse associateCredential(
            @Nonnull String accountId, @Nonnull CredentialRequest body, @Nullable Options options) {
        AssociateCredentialRequest request = new AssociateCredentialRequest(accountId, body);
        RequestOperation<AssociateCredentialRequest, AssociateCredentialResponse> operation =
                new AssociateCredential.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List issued credential associations
     *
     * <p>Retained credential associations, ordered by id. Page with the authenticated cursor. Associations
     * are removed on account erasure.
     *
     * @return The call builder
     */
    public ListAccountCredentialsRequestBuilder listCredentials() {
        return new ListAccountCredentialsRequestBuilder(sdkConfiguration);
    }

    /**
     * List issued credential associations
     *
     * <p>Retained credential associations, ordered by id. Page with the authenticated cursor. Associations
     * are removed on account erasure.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountCredentialsResponse listCredentials(@Nonnull String accountId) {
        return listCredentials(accountId, null, null, null);
    }

    /**
     * List issued credential associations
     *
     * <p>Retained credential associations, ordered by id. Page with the authenticated cursor. Associations
     * are removed on account erasure.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountCredentialsResponse listCredentials(
            @Nonnull String accountId, @Nullable Long limit, @Nullable String cursor, @Nullable Options options) {
        ListAccountCredentialsRequest request = new ListAccountCredentialsRequest(accountId, limit, cursor);
        RequestOperation<ListAccountCredentialsRequest, ListAccountCredentialsResponse> operation =
                new ListAccountCredentials.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Retire an issued credential association
     *
     * <p>Mark this association retired, idempotently. This does not revoke the actual key. Requires delete
     * scope.
     *
     * @return The call builder
     */
    public RetireAccountCredentialRequestBuilder retireCredential() {
        return new RetireAccountCredentialRequestBuilder(sdkConfiguration);
    }

    /**
     * Retire an issued credential association
     *
     * <p>Mark this association retired, idempotently. This does not revoke the actual key. Requires delete
     * scope.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param credentialId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RetireAccountCredentialResponse retireCredential(@Nonnull String accountId, @Nonnull String credentialId) {
        return retireCredential(accountId, credentialId, null);
    }

    /**
     * Retire an issued credential association
     *
     * <p>Mark this association retired, idempotently. This does not revoke the actual key. Requires delete
     * scope.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param credentialId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RetireAccountCredentialResponse retireCredential(
            @Nonnull String accountId, @Nonnull String credentialId, @Nullable Options options) {
        RetireAccountCredentialRequest request = new RetireAccountCredentialRequest(accountId, credentialId);
        RequestOperation<RetireAccountCredentialRequest, RetireAccountCredentialResponse> operation =
                new RetireAccountCredential.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Export an account
     *
     * <p>Retrieve one consistent snapshot of retained customer-accessible records, including assessment
     * revisions, account decisions, feedback, key associations, enforcements and request diagnostics. Each
     * assessment revision's detail.identifiers holds the identifiers that assessment was sent, exactly as
     * received. Secrets, restricted source information, other customers' data and legacy free-text notes
     * are excluded.
     *
     * <p>The same standard/detailed permissions apply. An export exceeding 10000 records or 8 MiB returns
     * export_too_large without a partial result. Historical decisions that were not captured are
     * identified as an exclusion.
     *
     * @return The call builder
     */
    public ExportAccountRequestBuilder export() {
        return new ExportAccountRequestBuilder(sdkConfiguration);
    }

    /**
     * Export an account
     *
     * <p>Retrieve one consistent snapshot of retained customer-accessible records, including assessment
     * revisions, account decisions, feedback, key associations, enforcements and request diagnostics. Each
     * assessment revision's detail.identifiers holds the identifiers that assessment was sent, exactly as
     * received. Secrets, restricted source information, other customers' data and legacy free-text notes
     * are excluded.
     *
     * <p>The same standard/detailed permissions apply. An export exceeding 10000 records or 8 MiB returns
     * export_too_large without a partial result. Historical decisions that were not captured are
     * identified as an exclusion.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ExportAccountResponse export(@Nonnull String accountId) {
        return export(accountId, null, null);
    }

    /**
     * Export an account
     *
     * <p>Retrieve one consistent snapshot of retained customer-accessible records, including assessment
     * revisions, account decisions, feedback, key associations, enforcements and request diagnostics. Each
     * assessment revision's detail.identifiers holds the identifiers that assessment was sent, exactly as
     * received. Secrets, restricted source information, other customers' data and legacy free-text notes
     * are excluded.
     *
     * <p>The same standard/detailed permissions apply. An export exceeding 10000 records or 8 MiB returns
     * export_too_large without a partial result. Historical decisions that were not captured are
     * identified as an exclusion.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param responseMode standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ExportAccountResponse export(
            @Nonnull String accountId, @Nullable ResponseMode responseMode, @Nullable Options options) {
        ExportAccountRequest request = new ExportAccountRequest(accountId, responseMode);
        RequestOperation<ExportAccountRequest, ExportAccountResponse> operation =
                new ExportAccount.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Page through recorded account decisions
     *
     * <p>List retained immutable risk decisions for one account with an authenticated cursor.
     *
     * @return The call builder
     */
    public ListAccountDecisionsRequestBuilder listDecisions() {
        return new ListAccountDecisionsRequestBuilder(sdkConfiguration);
    }

    /**
     * Page through recorded account decisions
     *
     * <p>List retained immutable risk decisions for one account with an authenticated cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountDecisionsResponse listDecisions(@Nonnull String accountId) {
        return listDecisions(accountId, null, null, null);
    }

    /**
     * Page through recorded account decisions
     *
     * <p>List retained immutable risk decisions for one account with an authenticated cursor.
     *
     * @param accountId The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAccountDecisionsResponse listDecisions(
            @Nonnull String accountId, @Nullable Long limit, @Nullable String cursor, @Nullable Options options) {
        ListAccountDecisionsRequest request = new ListAccountDecisionsRequest(accountId, limit, cursor);
        RequestOperation<ListAccountDecisionsRequest, ListAccountDecisionsResponse> operation =
                new ListAccountDecisions.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Search distinct accounts by email or account ID
     *
     * <p>Search distinct accounts observed in retained assessments in this environment. Match an exact
     * account_id or canonical email; an email matches any retained assessment belonging to the account.
     * Set needs_action to select accounts with an open or in-progress retained review, or a current
     * review/block decision without an effective retained outcome.
     *
     * <p>This filter applies before pagination and counting, adds waiting_for reasons to each row and
     * requires management scope as well as history or investigate access. The response contains one row
     * per account with its newest retained assessment, current decision when available, per-account
     * assessment count, and total distinct-account count. Send next_cursor with unchanged filters to
     * continue.
     *
     * <p>Totals are exact below 10000 and capped at 10000. Results are live across pages. Requires history
     * access and identifier access for email.
     *
     * <p>Read-only and safe to retry.
     *
     * @return The call builder
     */
    public SearchAccountsRequestBuilder search() {
        return new SearchAccountsRequestBuilder(sdkConfiguration);
    }

    /**
     * Search distinct accounts by email or account ID
     *
     * <p>Search distinct accounts observed in retained assessments in this environment. Match an exact
     * account_id or canonical email; an email matches any retained assessment belonging to the account.
     * Set needs_action to select accounts with an open or in-progress retained review, or a current
     * review/block decision without an effective retained outcome.
     *
     * <p>This filter applies before pagination and counting, adds waiting_for reasons to each row and
     * requires management scope as well as history or investigate access. The response contains one row
     * per account with its newest retained assessment, current decision when available, per-account
     * assessment count, and total distinct-account count. Send next_cursor with unchanged filters to
     * continue.
     *
     * <p>Totals are exact below 10000 and capped at 10000. Results are live across pages. Requires history
     * access and identifier access for email.
     *
     * <p>Read-only and safe to retry.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public SearchAccountsResponse search(@Nonnull AccountDirectorySearchRequest request) {
        return search(request, null);
    }

    /**
     * Search distinct accounts by email or account ID
     *
     * <p>Search distinct accounts observed in retained assessments in this environment. Match an exact
     * account_id or canonical email; an email matches any retained assessment belonging to the account.
     * Set needs_action to select accounts with an open or in-progress retained review, or a current
     * review/block decision without an effective retained outcome.
     *
     * <p>This filter applies before pagination and counting, adds waiting_for reasons to each row and
     * requires management scope as well as history or investigate access. The response contains one row
     * per account with its newest retained assessment, current decision when available, per-account
     * assessment count, and total distinct-account count. Send next_cursor with unchanged filters to
     * continue.
     *
     * <p>Totals are exact below 10000 and capped at 10000. Results are live across pages. Requires history
     * access and identifier access for email.
     *
     * <p>Read-only and safe to retry.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public SearchAccountsResponse search(@Nonnull AccountDirectorySearchRequest request, @Nullable Options options) {
        RequestOperation<AccountDirectorySearchRequest, SearchAccountsResponse> operation =
                new SearchAccounts.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
