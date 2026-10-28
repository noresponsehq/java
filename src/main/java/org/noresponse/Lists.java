package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.CreateListEntryRequest;
import org.noresponse.models.operations.CreateListEntryRequestBuilder;
import org.noresponse.models.operations.CreateListEntryResponse;
import org.noresponse.models.operations.DeleteListEntryRequest;
import org.noresponse.models.operations.DeleteListEntryRequestBuilder;
import org.noresponse.models.operations.DeleteListEntryResponse;
import org.noresponse.models.operations.ListListEntriesRequest;
import org.noresponse.models.operations.ListListEntriesRequestBuilder;
import org.noresponse.models.operations.ListListEntriesResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ListEntryRequest;
import org.noresponse.operations.CreateListEntry;
import org.noresponse.operations.DeleteListEntry;
import org.noresponse.operations.ListListEntries;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

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
     * <p>Lists every entry of the signing key's environment's list, newest first: each an email or an email
     * domain that decides the accounts it matches, block or allow, whatever their score. Requires the
     * management scope; the other environment answers 404 not_found. Read-only and safe to retry.
     *
     * @return The call builder
     */
    public ListListEntriesRequestBuilder list() {
        return new ListListEntriesRequestBuilder(sdkConfiguration);
    }

    /**
     * List an environment's list entries.
     *
     * <p>Lists every entry of the signing key's environment's list, newest first: each an email or an email
     * domain that decides the accounts it matches, block or allow, whatever their score. Requires the
     * management scope; the other environment answers 404 not_found. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListListEntriesResponse list(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return list(workspaceId, environment, null);
    }

    /**
     * List an environment's list entries.
     *
     * <p>Lists every entry of the signing key's environment's list, newest first: each an email or an email
     * domain that decides the accounts it matches, block or allow, whatever their score. Requires the
     * management scope; the other environment answers 404 not_found. Read-only and safe to retry.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListListEntriesResponse list(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nullable Options options) {
        ListListEntriesRequest request = new ListListEntriesRequest(workspaceId, environment);
        RequestOperation<ListListEntriesRequest, ListListEntriesResponse> operation =
                new ListListEntries.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Add an entry to an environment's list.
     *
     * <p>Adds an exact email (kind email) or an email domain (kind email_domain) to the signing key's
     * environment's list with the action block or allow, and answers the entry with its value in canonical
     * form. An account matches when one of its assessments sent the email, or an email of the domain: a
     * block entry decides block for every flow and an allow entry allow, block winning when both match,
     * and the account's risk carries list_match with the entry's id while its score is unchanged. Every
     * matching account is re-decided at once and sends account.risk_changed when its decision changes.
     *
     * <p>A value already on the list answers 409 list_entry_exists, a full list 409 list_limit_reached, and
     * an entry matching more than 5,000 accounts 409 list_entry_too_broad. Requires the management scope;
     * the other environment answers 404 not_found.
     *
     * @return The call builder
     */
    public CreateListEntryRequestBuilder create() {
        return new CreateListEntryRequestBuilder(sdkConfiguration);
    }

    /**
     * Add an entry to an environment's list.
     *
     * <p>Adds an exact email (kind email) or an email domain (kind email_domain) to the signing key's
     * environment's list with the action block or allow, and answers the entry with its value in canonical
     * form. An account matches when one of its assessments sent the email, or an email of the domain: a
     * block entry decides block for every flow and an allow entry allow, block winning when both match,
     * and the account's risk carries list_match with the entry's id while its score is unchanged. Every
     * matching account is re-decided at once and sends account.risk_changed when its decision changes.
     *
     * <p>A value already on the list answers 409 list_entry_exists, a full list 409 list_limit_reached, and
     * an entry matching more than 5,000 accounts 409 list_entry_too_broad. Requires the management scope;
     * the other environment answers 404 not_found.
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
     * <p>Adds an exact email (kind email) or an email domain (kind email_domain) to the signing key's
     * environment's list with the action block or allow, and answers the entry with its value in canonical
     * form. An account matches when one of its assessments sent the email, or an email of the domain: a
     * block entry decides block for every flow and an allow entry allow, block winning when both match,
     * and the account's risk carries list_match with the entry's id while its score is unchanged. Every
     * matching account is re-decided at once and sends account.risk_changed when its decision changes.
     *
     * <p>A value already on the list answers 409 list_entry_exists, a full list 409 list_limit_reached, and
     * an entry matching more than 5,000 accounts 409 list_entry_too_broad. Requires the management scope;
     * the other environment answers 404 not_found.
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
     * <p>Removes an entry from the signing key's environment's list and re-decides the accounts it matched,
     * the 5,000 most recently assessed at most, each sending account.risk_changed when its decision
     * changes. An unknown or already removed entry answers 404 not_found. Requires the management scope.
     *
     * @return The call builder
     */
    public DeleteListEntryRequestBuilder delete() {
        return new DeleteListEntryRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove an entry from an environment's list.
     *
     * <p>Removes an entry from the signing key's environment's list and re-decides the accounts it matched,
     * the 5,000 most recently assessed at most, each sending account.risk_changed when its decision
     * changes. An unknown or already removed entry answers 404 not_found. Requires the management scope.
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
     * <p>Removes an entry from the signing key's environment's list and re-decides the accounts it matched,
     * the 5,000 most recently assessed at most, each sending account.risk_changed when its decision
     * changes. An unknown or already removed entry answers 404 not_found. Requires the management scope.
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
}
