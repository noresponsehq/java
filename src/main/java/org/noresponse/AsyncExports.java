package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.CancelCustomerExportRequest;
import org.noresponse.models.operations.CreateCustomerExportRequest;
import org.noresponse.models.operations.DownloadCustomerExportChunkRequest;
import org.noresponse.models.operations.GetCustomerExportRequest;
import org.noresponse.models.operations.ListCustomerExportsRequest;
import org.noresponse.models.operations.RetryCustomerExportRequest;
import org.noresponse.models.operations.async.CancelCustomerExportRequestBuilder;
import org.noresponse.models.operations.async.CancelCustomerExportResponse;
import org.noresponse.models.operations.async.CreateCustomerExportRequestBuilder;
import org.noresponse.models.operations.async.CreateCustomerExportResponse;
import org.noresponse.models.operations.async.DownloadCustomerExportChunkRequestBuilder;
import org.noresponse.models.operations.async.DownloadCustomerExportChunkResponse;
import org.noresponse.models.operations.async.GetCustomerExportRequestBuilder;
import org.noresponse.models.operations.async.GetCustomerExportResponse;
import org.noresponse.models.operations.async.ListCustomerExportsRequestBuilder;
import org.noresponse.models.operations.async.ListCustomerExportsResponse;
import org.noresponse.models.operations.async.RetryCustomerExportRequestBuilder;
import org.noresponse.models.operations.async.RetryCustomerExportResponse;
import org.noresponse.models.shared.CustomerExportActionRequest;
import org.noresponse.models.shared.CustomerExportRequest;
import org.noresponse.operations.CancelCustomerExport;
import org.noresponse.operations.CreateCustomerExport;
import org.noresponse.operations.DownloadCustomerExportChunk;
import org.noresponse.operations.GetCustomerExport;
import org.noresponse.operations.ListCustomerExports;
import org.noresponse.operations.RetryCustomerExport;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Bounded customer export jobs and encrypted artifacts.
 */
public class AsyncExports {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Exports syncSDK;

    AsyncExports(Exports syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Exports sync() {
        return syncSDK;
    }

    /**
     * Request an asynchronous customer export.
     *
     * <p>Request an encrypted JSON Lines snapshot of retained account or audit records. Requires exports plus
     * history or audit scope; detailed account data also needs evidence or detailed scope. Criteria are
     * frozen and hashed.
     *
     * <p>Each environment permits four active jobs, 64 MiB per artifact, and 256 KiB per chunk. Artifacts
     * expire after 24 hours. Send an Idempotency-Key to safely repeat a request.
     *
     * <p>Every operation checks current permissions and source retention.
     *
     * @return The async call builder
     */
    public CreateCustomerExportRequestBuilder create() {
        return new CreateCustomerExportRequestBuilder(sdkConfiguration);
    }

    /**
     * Request an asynchronous customer export.
     *
     * <p>Request an encrypted JSON Lines snapshot of retained account or audit records. Requires exports plus
     * history or audit scope; detailed account data also needs evidence or detailed scope. Criteria are
     * frozen and hashed.
     *
     * <p>Each environment permits four active jobs, 64 MiB per artifact, and 256 KiB per chunk. Artifacts
     * expire after 24 hours. Send an Idempotency-Key to safely repeat a request.
     *
     * <p>Every operation checks current permissions and source retention.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param body
     * @return {@code CompletableFuture<CreateCustomerExportResponse>} - The async response
     */
    public CompletableFuture<CreateCustomerExportResponse> create(
            @Nonnull String workspaceId, @Nonnull CustomerExportRequest body) {
        return create(workspaceId, null, body);
    }

    /**
     * Request an asynchronous customer export.
     *
     * <p>Request an encrypted JSON Lines snapshot of retained account or audit records. Requires exports plus
     * history or audit scope; detailed account data also needs evidence or detailed scope. Criteria are
     * frozen and hashed.
     *
     * <p>Each environment permits four active jobs, 64 MiB per artifact, and 256 KiB per chunk. Artifacts
     * expire after 24 hours. Send an Idempotency-Key to safely repeat a request.
     *
     * <p>Every operation checks current permissions and source retention.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param idempotencyKey
     * @param body
     * @return {@code CompletableFuture<CreateCustomerExportResponse>} - The async response
     */
    public CompletableFuture<CreateCustomerExportResponse> create(
            @Nonnull String workspaceId, @Nullable String idempotencyKey, @Nonnull CustomerExportRequest body) {
        CreateCustomerExportRequest request = new CreateCustomerExportRequest(workspaceId, idempotencyKey, body);
        AsyncRequestOperation<CreateCustomerExportRequest, CreateCustomerExportResponse> operation =
                new CreateCustomerExport.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List exports for one collection.
     *
     * <p>List authorised jobs for one kind and environment. Detailed jobs require current detailed-data
     * permission and are filtered before pagination. Keep the kind and environment unchanged when
     * following next_cursor.
     *
     * @return The async call builder
     */
    public ListCustomerExportsRequestBuilder list() {
        return new ListCustomerExportsRequestBuilder(sdkConfiguration);
    }

    /**
     * List exports for one collection.
     *
     * <p>List authorised jobs for one kind and environment. Detailed jobs require current detailed-data
     * permission and are filtered before pagination. Keep the kind and environment unchanged when
     * following next_cursor.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListCustomerExportsResponse>} - The async response
     */
    public CompletableFuture<ListCustomerExportsResponse> list(@Nonnull ListCustomerExportsRequest request) {
        return list(request, null);
    }

    /**
     * List exports for one collection.
     *
     * <p>List authorised jobs for one kind and environment. Detailed jobs require current detailed-data
     * permission and are filtered before pagination. Keep the kind and environment unchanged when
     * following next_cursor.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListCustomerExportsResponse>} - The async response
     */
    public CompletableFuture<ListCustomerExportsResponse> list(
            @Nonnull ListCustomerExportsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListCustomerExportsRequest, ListCustomerExportsResponse> operation =
                new ListCustomerExports.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read export state and progress.
     *
     * <p>Read the status of an authorised export. Records and bytes describe committed progress in the
     * current generation. Only completed, unexpired artifacts can be downloaded.
     *
     * <p>Source erasure, retention changes and workspace removal can invalidate a job.
     *
     * @return The async call builder
     */
    public GetCustomerExportRequestBuilder get() {
        return new GetCustomerExportRequestBuilder(sdkConfiguration);
    }

    /**
     * Read export state and progress.
     *
     * <p>Read the status of an authorised export. Records and bytes describe committed progress in the
     * current generation. Only completed, unexpired artifacts can be downloaded.
     *
     * <p>Source erasure, retention changes and workspace removal can invalidate a job.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @return {@code CompletableFuture<GetCustomerExportResponse>} - The async response
     */
    public CompletableFuture<GetCustomerExportResponse> get(@Nonnull String workspaceId, @Nonnull String exportId) {
        return get(workspaceId, exportId, null);
    }

    /**
     * Read export state and progress.
     *
     * <p>Read the status of an authorised export. Records and bytes describe committed progress in the
     * current generation. Only completed, unexpired artifacts can be downloaded.
     *
     * <p>Source erasure, retention changes and workspace removal can invalidate a job.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param options additional options
     * @return {@code CompletableFuture<GetCustomerExportResponse>} - The async response
     */
    public CompletableFuture<GetCustomerExportResponse> get(
            @Nonnull String workspaceId, @Nonnull String exportId, @Nullable Options options) {
        GetCustomerExportRequest request = new GetCustomerExportRequest(workspaceId, exportId);
        AsyncRequestOperation<GetCustomerExportRequest, GetCustomerExportResponse> operation =
                new GetCustomerExport.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Cancel and remove an export artifact.
     *
     * <p>Cancel an export and remove its stored chunks. Repeating cancellation is safe. An already admitted
     * download may finish; bytes already returned cannot be recalled.
     *
     * <p>Send an empty JSON object.
     *
     * @return The async call builder
     */
    public CancelCustomerExportRequestBuilder cancel() {
        return new CancelCustomerExportRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel and remove an export artifact.
     *
     * <p>Cancel an export and remove its stored chunks. Repeating cancellation is safe. An already admitted
     * download may finish; bytes already returned cannot be recalled.
     *
     * <p>Send an empty JSON object.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param body Send an empty JSON object to cancel or retry a customer export.
     * @return {@code CompletableFuture<CancelCustomerExportResponse>} - The async response
     */
    public CompletableFuture<CancelCustomerExportResponse> cancel(
            @Nonnull String workspaceId, @Nonnull String exportId, @Nonnull CustomerExportActionRequest body) {
        return cancel(workspaceId, exportId, body, null);
    }

    /**
     * Cancel and remove an export artifact.
     *
     * <p>Cancel an export and remove its stored chunks. Repeating cancellation is safe. An already admitted
     * download may finish; bytes already returned cannot be recalled.
     *
     * <p>Send an empty JSON object.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param body Send an empty JSON object to cancel or retry a customer export.
     * @param options additional options
     * @return {@code CompletableFuture<CancelCustomerExportResponse>} - The async response
     */
    public CompletableFuture<CancelCustomerExportResponse> cancel(
            @Nonnull String workspaceId,
            @Nonnull String exportId,
            @Nonnull CustomerExportActionRequest body,
            @Nullable Options options) {
        CancelCustomerExportRequest request = new CancelCustomerExportRequest(workspaceId, exportId, body);
        AsyncRequestOperation<CancelCustomerExportRequest, CancelCustomerExportResponse> operation =
                new CancelCustomerExport.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Retry a failed export.
     *
     * <p>Start a new generation of a failed export before its original expiry, up to ten generations. The
     * request criteria stay fixed and the new generation takes a fresh source snapshot. Cancelled, erased
     * and expired jobs cannot be retried.
     *
     * <p>Send an empty JSON object.
     *
     * @return The async call builder
     */
    public RetryCustomerExportRequestBuilder retry() {
        return new RetryCustomerExportRequestBuilder(sdkConfiguration);
    }

    /**
     * Retry a failed export.
     *
     * <p>Start a new generation of a failed export before its original expiry, up to ten generations. The
     * request criteria stay fixed and the new generation takes a fresh source snapshot. Cancelled, erased
     * and expired jobs cannot be retried.
     *
     * <p>Send an empty JSON object.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param body Send an empty JSON object to cancel or retry a customer export.
     * @return {@code CompletableFuture<RetryCustomerExportResponse>} - The async response
     */
    public CompletableFuture<RetryCustomerExportResponse> retry(
            @Nonnull String workspaceId, @Nonnull String exportId, @Nonnull CustomerExportActionRequest body) {
        RetryCustomerExportRequest request = new RetryCustomerExportRequest(workspaceId, exportId, body);
        AsyncRequestOperation<RetryCustomerExportRequest, RetryCustomerExportResponse> operation =
                new RetryCustomerExport.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Download one authorized export chunk.
     *
     * <p>Download one chunk of a completed, unexpired export after a fresh permission and privacy check. The
     * response contains base64-encoded JSON Lines, a SHA-256, generation and next index. Decode and
     * concatenate chunks in order from one generation.
     *
     * <p>Audit storage failure withholds the bytes.
     *
     * @return The async call builder
     */
    public DownloadCustomerExportChunkRequestBuilder downloadChunk() {
        return new DownloadCustomerExportChunkRequestBuilder(sdkConfiguration);
    }

    /**
     * Download one authorized export chunk.
     *
     * <p>Download one chunk of a completed, unexpired export after a fresh permission and privacy check. The
     * response contains base64-encoded JSON Lines, a SHA-256, generation and next index. Decode and
     * concatenate chunks in order from one generation.
     *
     * <p>Audit storage failure withholds the bytes.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param chunkIndex
     * @return {@code CompletableFuture<DownloadCustomerExportChunkResponse>} - The async response
     */
    public CompletableFuture<DownloadCustomerExportChunkResponse> downloadChunk(
            @Nonnull String workspaceId, @Nonnull String exportId, long chunkIndex) {
        return downloadChunk(workspaceId, exportId, chunkIndex, null);
    }

    /**
     * Download one authorized export chunk.
     *
     * <p>Download one chunk of a completed, unexpired export after a fresh permission and privacy check. The
     * response contains base64-encoded JSON Lines, a SHA-256, generation and next index. Decode and
     * concatenate chunks in order from one generation.
     *
     * <p>Audit storage failure withholds the bytes.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param exportId
     * @param chunkIndex
     * @param options additional options
     * @return {@code CompletableFuture<DownloadCustomerExportChunkResponse>} - The async response
     */
    public CompletableFuture<DownloadCustomerExportChunkResponse> downloadChunk(
            @Nonnull String workspaceId, @Nonnull String exportId, long chunkIndex, @Nullable Options options) {
        DownloadCustomerExportChunkRequest request =
                new DownloadCustomerExportChunkRequest(workspaceId, exportId, chunkIndex);
        AsyncRequestOperation<DownloadCustomerExportChunkRequest, DownloadCustomerExportChunkResponse> operation =
                new DownloadCustomerExportChunk.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
