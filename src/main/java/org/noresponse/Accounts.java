package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.DeleteAccountRequest;
import org.noresponse.models.operations.DeleteAccountRequestBuilder;
import org.noresponse.models.operations.DeleteAccountResponse;
import org.noresponse.models.operations.GetAccountRequest;
import org.noresponse.models.operations.GetAccountRequestBuilder;
import org.noresponse.models.operations.GetAccountResponse;
import org.noresponse.models.operations.ListAccountAssessmentsRequest;
import org.noresponse.models.operations.ListAccountAssessmentsRequestBuilder;
import org.noresponse.models.operations.ListAccountAssessmentsResponse;
import org.noresponse.models.operations.ListAccountsRequest;
import org.noresponse.models.operations.ListAccountsRequestBuilder;
import org.noresponse.models.operations.ListAccountsResponse;
import org.noresponse.models.operations.RecordEnforcementRequest;
import org.noresponse.models.operations.RecordEnforcementRequestBuilder;
import org.noresponse.models.operations.RecordEnforcementResponse;
import org.noresponse.models.shared.EnforcementRequest;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.DeleteAccount;
import org.noresponse.operations.GetAccount;
import org.noresponse.operations.ListAccountAssessments;
import org.noresponse.operations.ListAccounts;
import org.noresponse.operations.RecordEnforcement;
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
     * @param unenforced true lists only accounts with a decision other than allow for some flow and no applied enforcement at their current risk_version.
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
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
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
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
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
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
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
}
