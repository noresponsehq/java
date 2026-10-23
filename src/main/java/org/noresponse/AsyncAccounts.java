package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.DeleteAccountRequest;
import org.noresponse.models.operations.GetAccountRequest;
import org.noresponse.models.operations.ListAccountAssessmentsRequest;
import org.noresponse.models.operations.async.DeleteAccountRequestBuilder;
import org.noresponse.models.operations.async.DeleteAccountResponse;
import org.noresponse.models.operations.async.GetAccountRequestBuilder;
import org.noresponse.models.operations.async.GetAccountResponse;
import org.noresponse.models.operations.async.ListAccountAssessmentsRequestBuilder;
import org.noresponse.models.operations.async.ListAccountAssessmentsResponse;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.DeleteAccount;
import org.noresponse.operations.GetAccount;
import org.noresponse.operations.ListAccountAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Your accounts, each with one current risk, and their assessment history.
 */
public class AsyncAccounts {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Accounts syncSDK;

    AsyncAccounts(Accounts syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Accounts sync() {
        return syncSDK;
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
     * @return The async call builder
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
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return {@code CompletableFuture<GetAccountResponse>} - The async response
     */
    public CompletableFuture<GetAccountResponse> get(@Nonnull String accountId) {
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
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param options additional options
     * @return {@code CompletableFuture<GetAccountResponse>} - The async response
     */
    public CompletableFuture<GetAccountResponse> get(@Nonnull String accountId, @Nullable Options options) {
        GetAccountRequest request = new GetAccountRequest(accountId);
        AsyncRequestOperation<GetAccountRequest, GetAccountResponse> operation =
                new GetAccount.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return {@code CompletableFuture<DeleteAccountResponse>} - The async response
     */
    public CompletableFuture<DeleteAccountResponse> delete(@Nonnull String accountId) {
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
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param options additional options
     * @return {@code CompletableFuture<DeleteAccountResponse>} - The async response
     */
    public CompletableFuture<DeleteAccountResponse> delete(@Nonnull String accountId, @Nullable Options options) {
        DeleteAccountRequest request = new DeleteAccountRequest(accountId);
        AsyncRequestOperation<DeleteAccountRequest, DeleteAccountResponse> operation =
                new DeleteAccount.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Page through one account's unexpired assessments.
     *
     * <p>The account's history: the same page as POST /v1/assessments/query for this account_id, newest
     * first, each at its latest assessment_version. Requires the history scope. Read-only and safe to
     * retry.
     *
     * <p>An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your
     * tenant and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @return The async call builder
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
     * tenant and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @return {@code CompletableFuture<ListAccountAssessmentsResponse>} - The async response
     */
    public CompletableFuture<ListAccountAssessmentsResponse> listAssessments(@Nonnull String accountId) {
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
     * tenant and the account; a tampered or expired cursor answers 400 invalid_cursor.
     * response_mode=detailed needs the detailed scope.
     *
     * <p>The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.
     *
     * @param accountId The account_id the tenant sends with its assessments, as one path segment (percent-encode '|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request.
     * @param limit Items per page.
     * @param cursor next_cursor from the previous page, unchanged.
     * @param responseMode standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.
     * @param options additional options
     * @return {@code CompletableFuture<ListAccountAssessmentsResponse>} - The async response
     */
    public CompletableFuture<ListAccountAssessmentsResponse> listAssessments(
            @Nonnull String accountId,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable ResponseMode responseMode,
            @Nullable Options options) {
        ListAccountAssessmentsRequest request =
                new ListAccountAssessmentsRequest(accountId, limit, cursor, responseMode);
        AsyncRequestOperation<ListAccountAssessmentsRequest, ListAccountAssessmentsResponse> operation =
                new ListAccountAssessments.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
