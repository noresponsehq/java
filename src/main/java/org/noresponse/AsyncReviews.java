package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.AddReviewSubjectRequest;
import org.noresponse.models.operations.CommentReviewRequest;
import org.noresponse.models.operations.CreateReviewRequest;
import org.noresponse.models.operations.CreateReviewViewRequest;
import org.noresponse.models.operations.DeleteReviewSubjectRequest;
import org.noresponse.models.operations.DeleteReviewViewRequest;
import org.noresponse.models.operations.ExportReviewRequest;
import org.noresponse.models.operations.GetReviewRequest;
import org.noresponse.models.operations.ListReviewCommentsRequest;
import org.noresponse.models.operations.ListReviewResolutionsRequest;
import org.noresponse.models.operations.ListReviewSubjectsRequest;
import org.noresponse.models.operations.ListReviewViewsRequest;
import org.noresponse.models.operations.ListReviewsRequest;
import org.noresponse.models.operations.ResolveReviewRequest;
import org.noresponse.models.operations.UpdateReviewRequest;
import org.noresponse.models.operations.UpdateReviewViewRequest;
import org.noresponse.models.operations.async.AddReviewSubjectRequestBuilder;
import org.noresponse.models.operations.async.AddReviewSubjectResponse;
import org.noresponse.models.operations.async.CommentReviewRequestBuilder;
import org.noresponse.models.operations.async.CommentReviewResponse;
import org.noresponse.models.operations.async.CreateReviewRequestBuilder;
import org.noresponse.models.operations.async.CreateReviewResponse;
import org.noresponse.models.operations.async.CreateReviewViewRequestBuilder;
import org.noresponse.models.operations.async.CreateReviewViewResponse;
import org.noresponse.models.operations.async.DeleteReviewSubjectRequestBuilder;
import org.noresponse.models.operations.async.DeleteReviewSubjectResponse;
import org.noresponse.models.operations.async.DeleteReviewViewRequestBuilder;
import org.noresponse.models.operations.async.DeleteReviewViewResponse;
import org.noresponse.models.operations.async.ExportReviewRequestBuilder;
import org.noresponse.models.operations.async.ExportReviewResponse;
import org.noresponse.models.operations.async.GetReviewRequestBuilder;
import org.noresponse.models.operations.async.GetReviewResponse;
import org.noresponse.models.operations.async.ListReviewCommentsRequestBuilder;
import org.noresponse.models.operations.async.ListReviewCommentsResponse;
import org.noresponse.models.operations.async.ListReviewResolutionsRequestBuilder;
import org.noresponse.models.operations.async.ListReviewResolutionsResponse;
import org.noresponse.models.operations.async.ListReviewSubjectsRequestBuilder;
import org.noresponse.models.operations.async.ListReviewSubjectsResponse;
import org.noresponse.models.operations.async.ListReviewViewsRequestBuilder;
import org.noresponse.models.operations.async.ListReviewViewsResponse;
import org.noresponse.models.operations.async.ListReviewsRequestBuilder;
import org.noresponse.models.operations.async.ListReviewsResponse;
import org.noresponse.models.operations.async.ResolveReviewRequestBuilder;
import org.noresponse.models.operations.async.ResolveReviewResponse;
import org.noresponse.models.operations.async.UpdateReviewRequestBuilder;
import org.noresponse.models.operations.async.UpdateReviewResponse;
import org.noresponse.models.operations.async.UpdateReviewViewRequestBuilder;
import org.noresponse.models.operations.async.UpdateReviewViewResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewCommentRequest;
import org.noresponse.models.shared.ReviewCreateRequest;
import org.noresponse.models.shared.ReviewResolveRequest;
import org.noresponse.models.shared.ReviewSubjectRequest;
import org.noresponse.models.shared.ReviewUpdateRequest;
import org.noresponse.models.shared.ReviewViewDeleteRequest;
import org.noresponse.models.shared.ReviewViewRequest;
import org.noresponse.operations.AddReviewSubject;
import org.noresponse.operations.CommentReview;
import org.noresponse.operations.CreateReview;
import org.noresponse.operations.CreateReviewView;
import org.noresponse.operations.DeleteReviewSubject;
import org.noresponse.operations.DeleteReviewView;
import org.noresponse.operations.ExportReview;
import org.noresponse.operations.GetReview;
import org.noresponse.operations.ListReviewComments;
import org.noresponse.operations.ListReviewResolutions;
import org.noresponse.operations.ListReviewSubjects;
import org.noresponse.operations.ListReviewViews;
import org.noresponse.operations.ListReviews;
import org.noresponse.operations.ResolveReview;
import org.noresponse.operations.UpdateReview;
import org.noresponse.operations.UpdateReviewView;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Collaborative review cases, comments and linked subjects.
 */
public class AsyncReviews {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Reviews syncSDK;

    AsyncReviews(Reviews syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Reviews sync() {
        return syncSDK;
    }

    /**
     * List retained account reviews.
     *
     * <p>List retained investigation reviews using filters and an authenticated cursor.
     *
     * @return The async call builder
     */
    public ListReviewsRequestBuilder list() {
        return new ListReviewsRequestBuilder(sdkConfiguration);
    }

    /**
     * List retained account reviews.
     *
     * <p>List retained investigation reviews using filters and an authenticated cursor.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListReviewsResponse>} - The async response
     */
    public CompletableFuture<ListReviewsResponse> list(@Nonnull ListReviewsRequest request) {
        return list(request, null);
    }

    /**
     * List retained account reviews.
     *
     * <p>List retained investigation reviews using filters and an authenticated cursor.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListReviewsResponse>} - The async response
     */
    public CompletableFuture<ListReviewsResponse> list(@Nonnull ListReviewsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListReviewsRequest, ListReviewsResponse> operation =
                new ListReviews.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Open a review for a retained account.
     *
     * <p>Open a review for one account. An account can have one active review in its environment.
     *
     * @return The async call builder
     */
    public CreateReviewRequestBuilder create() {
        return new CreateReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Open a review for a retained account.
     *
     * <p>Open a review for one account. An account can have one active review in its environment.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body
     * @return {@code CompletableFuture<CreateReviewResponse>} - The async response
     */
    public CompletableFuture<CreateReviewResponse> create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull ReviewCreateRequest body) {
        CreateReviewRequest request = new CreateReviewRequest(workspaceId, environment, body);
        AsyncRequestOperation<CreateReviewRequest, CreateReviewResponse> operation =
                new CreateReview.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read an account review.
     *
     * <p>Read a retained review and its current version.
     *
     * @return The async call builder
     */
    public GetReviewRequestBuilder get() {
        return new GetReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an account review.
     *
     * <p>Read a retained review and its current version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @return {@code CompletableFuture<GetReviewResponse>} - The async response
     */
    public CompletableFuture<GetReviewResponse> get(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String reviewId) {
        return get(workspaceId, environment, reviewId, null);
    }

    /**
     * Read an account review.
     *
     * <p>Read a retained review and its current version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param options additional options
     * @return {@code CompletableFuture<GetReviewResponse>} - The async response
     */
    public CompletableFuture<GetReviewResponse> get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nullable Options options) {
        GetReviewRequest request = new GetReviewRequest(workspaceId, environment, reviewId);
        AsyncRequestOperation<GetReviewRequest, GetReviewResponse> operation =
                new GetReview.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Assign, prioritize or reopen a guarded review.
     *
     * <p>Change review status, priority or assignment after checking the expected version.
     *
     * @return The async call builder
     */
    public UpdateReviewRequestBuilder update() {
        return new UpdateReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Assign, prioritize or reopen a guarded review.
     *
     * <p>Change review status, priority or assignment after checking the expected version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param body
     * @return {@code CompletableFuture<UpdateReviewResponse>} - The async response
     */
    public CompletableFuture<UpdateReviewResponse> update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nonnull ReviewUpdateRequest body) {
        UpdateReviewRequest request = new UpdateReviewRequest(workspaceId, environment, reviewId, body);
        AsyncRequestOperation<UpdateReviewRequest, UpdateReviewResponse> operation =
                new UpdateReview.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Resolve a review against an exact account risk version.
     *
     * <p>Resolve a review against the exact current review and account decision versions. A resolution does
     * not report a factual outcome or execute enforcement.
     *
     * @return The async call builder
     */
    public ResolveReviewRequestBuilder resolve() {
        return new ResolveReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Resolve a review against an exact account risk version.
     *
     * <p>Resolve a review against the exact current review and account decision versions. A resolution does
     * not report a factual outcome or execute enforcement.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param body
     * @return {@code CompletableFuture<ResolveReviewResponse>} - The async response
     */
    public CompletableFuture<ResolveReviewResponse> resolve(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nonnull ReviewResolveRequest body) {
        ResolveReviewRequest request = new ResolveReviewRequest(workspaceId, environment, reviewId, body);
        AsyncRequestOperation<ResolveReviewRequest, ResolveReviewResponse> operation =
                new ResolveReview.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read retained review comments.
     *
     * <p>Page through retained review comments.
     *
     * @return The async call builder
     */
    public ListReviewCommentsRequestBuilder listComments() {
        return new ListReviewCommentsRequestBuilder(sdkConfiguration);
    }

    /**
     * Read retained review comments.
     *
     * <p>Page through retained review comments.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListReviewCommentsResponse>} - The async response
     */
    public CompletableFuture<ListReviewCommentsResponse> listComments(@Nonnull ListReviewCommentsRequest request) {
        return listComments(request, null);
    }

    /**
     * Read retained review comments.
     *
     * <p>Page through retained review comments.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListReviewCommentsResponse>} - The async response
     */
    public CompletableFuture<ListReviewCommentsResponse> listComments(
            @Nonnull ListReviewCommentsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListReviewCommentsRequest, ListReviewCommentsResponse> operation =
                new ListReviewComments.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Append a comment against the current review version.
     *
     * <p>Append a comment after checking the expected review version.
     *
     * @return The async call builder
     */
    public CommentReviewRequestBuilder comment() {
        return new CommentReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Append a comment against the current review version.
     *
     * <p>Append a comment after checking the expected review version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param body
     * @return {@code CompletableFuture<CommentReviewResponse>} - The async response
     */
    public CompletableFuture<CommentReviewResponse> comment(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nonnull ReviewCommentRequest body) {
        CommentReviewRequest request = new CommentReviewRequest(workspaceId, environment, reviewId, body);
        AsyncRequestOperation<CommentReviewRequest, CommentReviewResponse> operation =
                new CommentReview.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read immutable review resolution history.
     *
     * <p>Page through immutable review resolutions.
     *
     * @return The async call builder
     */
    public ListReviewResolutionsRequestBuilder listResolutions() {
        return new ListReviewResolutionsRequestBuilder(sdkConfiguration);
    }

    /**
     * Read immutable review resolution history.
     *
     * <p>Page through immutable review resolutions.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListReviewResolutionsResponse>} - The async response
     */
    public CompletableFuture<ListReviewResolutionsResponse> listResolutions(
            @Nonnull ListReviewResolutionsRequest request) {
        return listResolutions(request, null);
    }

    /**
     * Read immutable review resolution history.
     *
     * <p>Page through immutable review resolutions.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListReviewResolutionsResponse>} - The async response
     */
    public CompletableFuture<ListReviewResolutionsResponse> listResolutions(
            @Nonnull ListReviewResolutionsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListReviewResolutionsRequest, ListReviewResolutionsResponse> operation =
                new ListReviewResolutions.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List your private and shared review filters.
     *
     * <p>List private and shared saved review filters visible to you.
     *
     * @return The async call builder
     */
    public ListReviewViewsRequestBuilder listViews() {
        return new ListReviewViewsRequestBuilder(sdkConfiguration);
    }

    /**
     * List your private and shared review filters.
     *
     * <p>List private and shared saved review filters visible to you.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return {@code CompletableFuture<ListReviewViewsResponse>} - The async response
     */
    public CompletableFuture<ListReviewViewsResponse> listViews(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return listViews(workspaceId, environment, null);
    }

    /**
     * List your private and shared review filters.
     *
     * <p>List private and shared saved review filters visible to you.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param options additional options
     * @return {@code CompletableFuture<ListReviewViewsResponse>} - The async response
     */
    public CompletableFuture<ListReviewViewsResponse> listViews(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nullable Options options) {
        ListReviewViewsRequest request = new ListReviewViewsRequest(workspaceId, environment);
        AsyncRequestOperation<ListReviewViewsRequest, ListReviewViewsResponse> operation =
                new ListReviewViews.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Save a tenant-local review filter.
     *
     * <p>Save a private or shared review filter.
     *
     * @return The async call builder
     */
    public CreateReviewViewRequestBuilder createView() {
        return new CreateReviewViewRequestBuilder(sdkConfiguration);
    }

    /**
     * Save a tenant-local review filter.
     *
     * <p>Save a private or shared review filter.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body
     * @return {@code CompletableFuture<CreateReviewViewResponse>} - The async response
     */
    public CompletableFuture<CreateReviewViewResponse> createView(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull ReviewViewRequest body) {
        CreateReviewViewRequest request = new CreateReviewViewRequest(workspaceId, environment, body);
        AsyncRequestOperation<CreateReviewViewRequest, CreateReviewViewResponse> operation =
                new CreateReviewView.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Replace your saved filter with optimistic concurrency.
     *
     * <p>Update your saved review filter after checking its expected version.
     *
     * @return The async call builder
     */
    public UpdateReviewViewRequestBuilder updateView() {
        return new UpdateReviewViewRequestBuilder(sdkConfiguration);
    }

    /**
     * Replace your saved filter with optimistic concurrency.
     *
     * <p>Update your saved review filter after checking its expected version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param viewId
     * @param body
     * @return {@code CompletableFuture<UpdateReviewViewResponse>} - The async response
     */
    public CompletableFuture<UpdateReviewViewResponse> updateView(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String viewId,
            @Nonnull ReviewViewRequest body) {
        UpdateReviewViewRequest request = new UpdateReviewViewRequest(workspaceId, environment, viewId, body);
        AsyncRequestOperation<UpdateReviewViewRequest, UpdateReviewViewResponse> operation =
                new UpdateReviewView.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Delete your saved filter with optimistic concurrency.
     *
     * <p>Delete your saved review filter after checking its expected version.
     *
     * @return The async call builder
     */
    public DeleteReviewViewRequestBuilder deleteView() {
        return new DeleteReviewViewRequestBuilder(sdkConfiguration);
    }

    /**
     * Delete your saved filter with optimistic concurrency.
     *
     * <p>Delete your saved review filter after checking its expected version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param viewId
     * @param body
     * @return {@code CompletableFuture<DeleteReviewViewResponse>} - The async response
     */
    public CompletableFuture<DeleteReviewViewResponse> deleteView(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String viewId,
            @Nonnull ReviewViewDeleteRequest body) {
        return deleteView(workspaceId, environment, viewId, body, null);
    }

    /**
     * Delete your saved filter with optimistic concurrency.
     *
     * <p>Delete your saved review filter after checking its expected version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param viewId
     * @param body
     * @param options additional options
     * @return {@code CompletableFuture<DeleteReviewViewResponse>} - The async response
     */
    public CompletableFuture<DeleteReviewViewResponse> deleteView(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String viewId,
            @Nonnull ReviewViewDeleteRequest body,
            @Nullable Options options) {
        DeleteReviewViewRequest request = new DeleteReviewViewRequest(workspaceId, environment, viewId, body);
        AsyncRequestOperation<DeleteReviewViewRequest, DeleteReviewViewResponse> operation =
                new DeleteReviewView.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Export an account review
     *
     * <p>Export retained review details, comments, resolutions and linked subjects. Requires current
     * reviews.read and exports.read; API keys need management together with exports, history or
     * audit_manage. The audit event commits before any bytes are returned.
     *
     * <p>Oversized exports are refused without truncation; use the bounded collection APIs.
     *
     * @return The async call builder
     */
    public ExportReviewRequestBuilder export() {
        return new ExportReviewRequestBuilder(sdkConfiguration);
    }

    /**
     * Export an account review
     *
     * <p>Export retained review details, comments, resolutions and linked subjects. Requires current
     * reviews.read and exports.read; API keys need management together with exports, history or
     * audit_manage. The audit event commits before any bytes are returned.
     *
     * <p>Oversized exports are refused without truncation; use the bounded collection APIs.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @return {@code CompletableFuture<ExportReviewResponse>} - The async response
     */
    public CompletableFuture<ExportReviewResponse> export(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String reviewId) {
        return export(workspaceId, environment, reviewId, null);
    }

    /**
     * Export an account review
     *
     * <p>Export retained review details, comments, resolutions and linked subjects. Requires current
     * reviews.read and exports.read; API keys need management together with exports, history or
     * audit_manage. The audit event commits before any bytes are returned.
     *
     * <p>Oversized exports are refused without truncation; use the bounded collection APIs.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param options additional options
     * @return {@code CompletableFuture<ExportReviewResponse>} - The async response
     */
    public CompletableFuture<ExportReviewResponse> export(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nullable Options options) {
        ExportReviewRequest request = new ExportReviewRequest(workspaceId, environment, reviewId);
        AsyncRequestOperation<ExportReviewRequest, ExportReviewResponse> operation =
                new ExportReview.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List retained subjects linked to a review.
     *
     * <p>List retained subjects linked to this review. A link supplies investigation context and does not
     * prove a relationship or change account risk.
     *
     * @return The async call builder
     */
    public ListReviewSubjectsRequestBuilder listSubjects() {
        return new ListReviewSubjectsRequestBuilder(sdkConfiguration);
    }

    /**
     * List retained subjects linked to a review.
     *
     * <p>List retained subjects linked to this review. A link supplies investigation context and does not
     * prove a relationship or change account risk.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @return {@code CompletableFuture<ListReviewSubjectsResponse>} - The async response
     */
    public CompletableFuture<ListReviewSubjectsResponse> listSubjects(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String reviewId) {
        return listSubjects(workspaceId, environment, reviewId, null);
    }

    /**
     * List retained subjects linked to a review.
     *
     * <p>List retained subjects linked to this review. A link supplies investigation context and does not
     * prove a relationship or change account risk.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param options additional options
     * @return {@code CompletableFuture<ListReviewSubjectsResponse>} - The async response
     */
    public CompletableFuture<ListReviewSubjectsResponse> listSubjects(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nullable Options options) {
        ListReviewSubjectsRequest request = new ListReviewSubjectsRequest(workspaceId, environment, reviewId);
        AsyncRequestOperation<ListReviewSubjectsRequest, ListReviewSubjectsResponse> operation =
                new ListReviewSubjects.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Link retained tenant-local evidence to an investigation.
     *
     * <p>Link an account, exact assessment revision or issued-key association within this environment. Supply
     * the expected review version. A review permits 100 links.
     *
     * <p>Erasure removes links to erased subjects. This action records context without creating feedback or
     * enforcement.
     *
     * @return The async call builder
     */
    public AddReviewSubjectRequestBuilder addSubject() {
        return new AddReviewSubjectRequestBuilder(sdkConfiguration);
    }

    /**
     * Link retained tenant-local evidence to an investigation.
     *
     * <p>Link an account, exact assessment revision or issued-key association within this environment. Supply
     * the expected review version. A review permits 100 links.
     *
     * <p>Erasure removes links to erased subjects. This action records context without creating feedback or
     * enforcement.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param reviewId
     * @param body An assessment link also requires assessment_id and assessment_version; an issued_credential link also requires credential_id. Every reference must belong to account_id in this environment. Other kinds reject those fields.
     * @return {@code CompletableFuture<AddReviewSubjectResponse>} - The async response
     */
    public CompletableFuture<AddReviewSubjectResponse> addSubject(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String reviewId,
            @Nonnull ReviewSubjectRequest body) {
        AddReviewSubjectRequest request = new AddReviewSubjectRequest(workspaceId, environment, reviewId, body);
        AsyncRequestOperation<AddReviewSubjectRequest, AddReviewSubjectResponse> operation =
                new AddReviewSubject.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Remove a linked subject using the current review version.
     *
     * <p>Remove a subject link with the expected review version. The linked account, assessment or key
     * association remains unchanged.
     *
     * @return The async call builder
     */
    public DeleteReviewSubjectRequestBuilder deleteSubject() {
        return new DeleteReviewSubjectRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove a linked subject using the current review version.
     *
     * <p>Remove a subject link with the expected review version. The linked account, assessment or key
     * association remains unchanged.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<DeleteReviewSubjectResponse>} - The async response
     */
    public CompletableFuture<DeleteReviewSubjectResponse> deleteSubject(@Nonnull DeleteReviewSubjectRequest request) {
        return deleteSubject(request, null);
    }

    /**
     * Remove a linked subject using the current review version.
     *
     * <p>Remove a subject link with the expected review version. The linked account, assessment or key
     * association remains unchanged.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<DeleteReviewSubjectResponse>} - The async response
     */
    public CompletableFuture<DeleteReviewSubjectResponse> deleteSubject(
            @Nonnull DeleteReviewSubjectRequest request, @Nullable Options options) {
        AsyncRequestOperation<DeleteReviewSubjectRequest, DeleteReviewSubjectResponse> operation =
                new DeleteReviewSubject.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
