package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.ChangeListsRequest;
import org.noresponse.models.operations.ChangeListsRequestBuilder;
import org.noresponse.models.operations.ChangeListsResponse;
import org.noresponse.models.operations.CreateListEntryRequest;
import org.noresponse.models.operations.CreateListEntryRequestBuilder;
import org.noresponse.models.operations.CreateListEntryResponse;
import org.noresponse.models.operations.DeleteListEntryRequest;
import org.noresponse.models.operations.DeleteListEntryRequestBuilder;
import org.noresponse.models.operations.DeleteListEntryResponse;
import org.noresponse.models.operations.GetListChangeRequest;
import org.noresponse.models.operations.GetListChangeRequestBuilder;
import org.noresponse.models.operations.GetListChangeResponse;
import org.noresponse.models.operations.ListListEntriesRequest;
import org.noresponse.models.operations.ListListEntriesRequestBuilder;
import org.noresponse.models.operations.ListListEntriesResponse;
import org.noresponse.models.operations.PreviewListRequest;
import org.noresponse.models.operations.PreviewListRequestBuilder;
import org.noresponse.models.operations.PreviewListResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ListBulkRequest;
import org.noresponse.models.shared.ListEntryRequest;
import org.noresponse.models.shared.ListPreviewRequest;
import org.noresponse.operations.ChangeLists;
import org.noresponse.operations.CreateListEntry;
import org.noresponse.operations.DeleteListEntry;
import org.noresponse.operations.GetListChange;
import org.noresponse.operations.ListListEntries;
import org.noresponse.operations.PreviewList;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * List entries, previews and durable changes.
 */
public class Lists {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncLists asyncSDK;

    Lists(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncLists(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncLists async() {
        return asyncSDK;
    }

    /**
     * List an environment's list entries.
     *
     * <p>Page through this environment allow and block entries, with canonical values, reasons and optional
     * expiry. Requires settings or management. Follow the returned cursor until exhausted; a page is not
     * the complete list.
     *
     * <p>The cursor is bound to the environment and current filters.
     *
     * @return The call builder
     */
    public ListListEntriesRequestBuilder list() {
        return new ListListEntriesRequestBuilder(sdkConfiguration);
    }

    /**
     * List an environment's list entries.
     *
     * <p>Page through this environment allow and block entries, with canonical values, reasons and optional
     * expiry. Requires settings or management. Follow the returned cursor until exhausted; a page is not
     * the complete list.
     *
     * <p>The cursor is bound to the environment and current filters.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListListEntriesResponse list(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return list(workspaceId, environment, null, null, null);
    }

    /**
     * List an environment's list entries.
     *
     * <p>Page through this environment allow and block entries, with canonical values, reasons and optional
     * expiry. Requires settings or management. Follow the returned cursor until exhausted; a page is not
     * the complete list.
     *
     * <p>The cursor is bound to the environment and current filters.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param limit
     * @param cursor
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListListEntriesResponse list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable Options options) {
        ListListEntriesRequest request = new ListListEntriesRequest(workspaceId, environment, limit, cursor);
        RequestOperation<ListListEntriesRequest, ListListEntriesResponse> operation =
                new ListListEntries.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Add an entry to an environment's list.
     *
     * <p>Add a canonical email or email-domain override with a reason and optional expiry. Requires settings
     * plus policy_publish, or legacy management. Block wins over allow when both match.
     *
     * <p>A small single-entry change retains the documented synchronous match budget; use lists.change for
     * larger changes, whose account propagation is durable and resumable.
     *
     * @return The call builder
     */
    public CreateListEntryRequestBuilder create() {
        return new CreateListEntryRequestBuilder(sdkConfiguration);
    }

    /**
     * Add an entry to an environment's list.
     *
     * <p>Add a canonical email or email-domain override with a reason and optional expiry. Requires settings
     * plus policy_publish, or legacy management. Block wins over allow when both match.
     *
     * <p>A small single-entry change retains the documented synchronous match budget; use lists.change for
     * larger changes, whose account propagation is durable and resumable.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's list and, in the same change, re-decides every account it matches: each sends account.risk_changed when a decision changes. Needs the admin or owner role, or a management key of the environment.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateListEntryResponse create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull ListEntryRequest body) {
        return create(workspaceId, environment, body, null);
    }

    /**
     * Add an entry to an environment's list.
     *
     * <p>Add a canonical email or email-domain override with a reason and optional expiry. Requires settings
     * plus policy_publish, or legacy management. Block wins over allow when both match.
     *
     * <p>A small single-entry change retains the documented synchronous match budget; use lists.change for
     * larger changes, whose account propagation is durable and resumable.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's list and, in the same change, re-decides every account it matches: each sends account.risk_changed when a decision changes. Needs the admin or owner role, or a management key of the environment.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateListEntryResponse create(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull ListEntryRequest body,
            @Nullable Options options) {
        CreateListEntryRequest request = new CreateListEntryRequest(workspaceId, environment, body);
        RequestOperation<CreateListEntryRequest, CreateListEntryResponse> operation =
                new CreateListEntry.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Remove an entry from an environment's list.
     *
     * <p>Remove an override with settings plus policy_publish, or legacy management. The removal takes effect
     * for new decisions and schedules resumable propagation across matching retained accounts. An unknown
     * or already removed entry answers404.
     *
     * <p>Account risk events announce changed decisions as propagation runs.
     *
     * @return The call builder
     */
    public DeleteListEntryRequestBuilder delete() {
        return new DeleteListEntryRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove an entry from an environment's list.
     *
     * <p>Remove an override with settings plus policy_publish, or legacy management. The removal takes effect
     * for new decisions and schedules resumable propagation across matching retained accounts. An unknown
     * or already removed entry answers404.
     *
     * <p>Account risk events announce changed decisions as propagation runs.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param listEntryId The entry's id. An entry of another environment or workspace answers 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteListEntryResponse delete(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String listEntryId) {
        return delete(workspaceId, environment, listEntryId, null);
    }

    /**
     * Remove an entry from an environment's list.
     *
     * <p>Remove an override with settings plus policy_publish, or legacy management. The removal takes effect
     * for new decisions and schedules resumable propagation across matching retained accounts. An unknown
     * or already removed entry answers404.
     *
     * <p>Account risk events announce changed decisions as propagation runs.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param listEntryId The entry's id. An entry of another environment or workspace answers 404.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteListEntryResponse delete(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String listEntryId,
            @Nullable Options options) {
        DeleteListEntryRequest request = new DeleteListEntryRequest(workspaceId, environment, listEntryId);
        RequestOperation<DeleteListEntryRequest, DeleteListEntryResponse> operation =
                new DeleteListEntry.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Preview a list override change without side effects.
     *
     * <p>Preview canonical list matches and current override effects with settings or management. The result
     * states its bounded coverage and changes no list entries.
     *
     * @return The call builder
     */
    public PreviewListRequestBuilder preview() {
        return new PreviewListRequestBuilder(sdkConfiguration);
    }

    /**
     * Preview a list override change without side effects.
     *
     * <p>Preview canonical list matches and current override effects with settings or management. The result
     * states its bounded coverage and changes no list entries.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Choose an addition or a removal. The preview performs no list, account, audit, outcome or outbox write.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public PreviewListResponse preview(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull ListPreviewRequest body) {
        return preview(workspaceId, environment, body, null);
    }

    /**
     * Preview a list override change without side effects.
     *
     * <p>Preview canonical list matches and current override effects with settings or management. The result
     * states its bounded coverage and changes no list entries.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Choose an addition or a removal. The preview performs no list, account, audit, outcome or outbox write.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public PreviewListResponse preview(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull ListPreviewRequest body,
            @Nullable Options options) {
        PreviewListRequest request = new PreviewListRequest(workspaceId, environment, body);
        RequestOperation<PreviewListRequest, PreviewListResponse> operation =
                new PreviewList.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Apply an idempotent atomic bulk list change.
     *
     * <p>Atomically add and remove a bounded batch of entries with an idempotency key. Requires settings plus
     * policy_publish, or legacy management. The receipt exposes durable account propagation beyond the
     * synchronous single-entry budget; poll lists.getChange until completion.
     *
     * @return The call builder
     */
    public ChangeListsRequestBuilder change() {
        return new ChangeListsRequestBuilder(sdkConfiguration);
    }

    /**
     * Apply an idempotent atomic bulk list change.
     *
     * <p>Atomically add and remove a bounded batch of entries with an idempotency key. Requires settings plus
     * policy_publish, or legacy management. The receipt exposes durable account propagation beyond the
     * synchronous single-entry budget; poll lists.getChange until completion.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body At most 100 additions and removals combined. One transaction applies all changes or none. Idempotency-Key prevents duplicate changes for 24 hours; a different body under the same key returns 409. Matching reads see the new list immediately, while bounded durable jobs propagate account risk notifications.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ChangeListsResponse change(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String idempotencyKey,
            @Nonnull ListBulkRequest body) {
        return change(workspaceId, environment, idempotencyKey, body, null);
    }

    /**
     * Apply an idempotent atomic bulk list change.
     *
     * <p>Atomically add and remove a bounded batch of entries with an idempotency key. Requires settings plus
     * policy_publish, or legacy management. The receipt exposes durable account propagation beyond the
     * synchronous single-entry budget; poll lists.getChange until completion.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body At most 100 additions and removals combined. One transaction applies all changes or none. Idempotency-Key prevents duplicate changes for 24 hours; a different body under the same key returns 409. Matching reads see the new list immediately, while bounded durable jobs propagate account risk notifications.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ChangeListsResponse change(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String idempotencyKey,
            @Nonnull ListBulkRequest body,
            @Nullable Options options) {
        ChangeListsRequest request = new ChangeListsRequest(workspaceId, environment, idempotencyKey, body);
        RequestOperation<ChangeListsRequest, ChangeListsResponse> operation =
                new ChangeLists.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a bulk list change and propagation progress.
     *
     * <p>Read retained propagation progress for a list change with settings or management.
     *
     * @return The call builder
     */
    public GetListChangeRequestBuilder getChange() {
        return new GetListChangeRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a bulk list change and propagation progress.
     *
     * <p>Read retained propagation progress for a list change with settings or management.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param changeId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetListChangeResponse getChange(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String changeId) {
        return getChange(workspaceId, environment, changeId, null);
    }

    /**
     * Read a bulk list change and propagation progress.
     *
     * <p>Read retained propagation progress for a list change with settings or management.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param changeId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetListChangeResponse getChange(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String changeId,
            @Nullable Options options) {
        GetListChangeRequest request = new GetListChangeRequest(workspaceId, environment, changeId);
        RequestOperation<GetListChangeRequest, GetListChangeResponse> operation =
                new GetListChange.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
