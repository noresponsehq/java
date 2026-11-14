package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.AddPeopleRequest;
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.models.operations.CreateGroupRequest;
import org.noresponse.models.operations.DeleteGroupRequest;
import org.noresponse.models.operations.GetGroupRequest;
import org.noresponse.models.operations.GetPersonRequest;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.operations.ListGroupsRequest;
import org.noresponse.models.operations.ListPeopleRequest;
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.ReviewPersonRequest;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.UpdateGroupRequest;
import org.noresponse.models.operations.async.AddPeopleRequestBuilder;
import org.noresponse.models.operations.async.AddPeopleResponse;
import org.noresponse.models.operations.async.CancelScreeningRequestBuilder;
import org.noresponse.models.operations.async.CancelScreeningResponse;
import org.noresponse.models.operations.async.CreateGroupRequestBuilder;
import org.noresponse.models.operations.async.CreateGroupResponse;
import org.noresponse.models.operations.async.DeleteGroupRequestBuilder;
import org.noresponse.models.operations.async.DeleteGroupResponse;
import org.noresponse.models.operations.async.GetGroupRequestBuilder;
import org.noresponse.models.operations.async.GetGroupResponse;
import org.noresponse.models.operations.async.GetPersonRequestBuilder;
import org.noresponse.models.operations.async.GetPersonResponse;
import org.noresponse.models.operations.async.GetScreeningRequestBuilder;
import org.noresponse.models.operations.async.GetScreeningResponse;
import org.noresponse.models.operations.async.ListGroupsRequestBuilder;
import org.noresponse.models.operations.async.ListGroupsResponse;
import org.noresponse.models.operations.async.ListPeopleRequestBuilder;
import org.noresponse.models.operations.async.ListPeopleResponse;
import org.noresponse.models.operations.async.ListScreeningsRequestBuilder;
import org.noresponse.models.operations.async.ListScreeningsResponse;
import org.noresponse.models.operations.async.ReviewPersonRequestBuilder;
import org.noresponse.models.operations.async.ReviewPersonResponse;
import org.noresponse.models.operations.async.StartScreeningRequestBuilder;
import org.noresponse.models.operations.async.StartScreeningResponse;
import org.noresponse.models.operations.async.UpdateGroupRequestBuilder;
import org.noresponse.models.operations.async.UpdateGroupResponse;
import org.noresponse.models.shared.GroupRequest;
import org.noresponse.models.shared.GroupUpdate;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.AddPeople;
import org.noresponse.operations.CancelScreening;
import org.noresponse.operations.CreateGroup;
import org.noresponse.operations.DeleteGroup;
import org.noresponse.operations.GetGroup;
import org.noresponse.operations.GetPerson;
import org.noresponse.operations.GetScreening;
import org.noresponse.operations.ListGroups;
import org.noresponse.operations.ListPeople;
import org.noresponse.operations.ListScreenings;
import org.noresponse.operations.ReviewPerson;
import org.noresponse.operations.StartScreening;
import org.noresponse.operations.UpdateGroup;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Groups, lists of people, screenings and reviewer decisions.
 */
public class AsyncGroups {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Groups syncSDK;

    AsyncGroups(Groups syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Groups sync() {
        return syncSDK;
    }

    /**
     * List an environment's groups.
     *
     * <p>List this environment's groups, newest first. Pass next_before back as before for the next page.
     * Requires the groups scope.
     *
     * @return The async call builder
     */
    public ListGroupsRequestBuilder list() {
        return new ListGroupsRequestBuilder(sdkConfiguration);
    }

    /**
     * List an environment's groups.
     *
     * <p>List this environment's groups, newest first. Pass next_before back as before for the next page.
     * Requires the groups scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return {@code CompletableFuture<ListGroupsResponse>} - The async response
     */
    public CompletableFuture<ListGroupsResponse> list(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return list(workspaceId, environment, null, null, null);
    }

    /**
     * List an environment's groups.
     *
     * <p>List this environment's groups, newest first. Pass next_before back as before for the next page.
     * Requires the groups scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param limit At most this many items.
     * @param before Read groups older than this one.
     * @param options additional options
     * @return {@code CompletableFuture<ListGroupsResponse>} - The async response
     */
    public CompletableFuture<ListGroupsResponse> list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String before,
            @Nullable Options options) {
        ListGroupsRequest request = new ListGroupsRequest(workspaceId, environment, limit, before);
        AsyncRequestOperation<ListGroupsRequest, ListGroupsResponse> operation =
                new ListGroups.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Create a group to screen.
     *
     * <p>Create a group to screen: its name, start, venue, host, the principals it protects and the sensitive
     * topics posts are read against. An environment holds at most 1000 groups.
     *
     * @return The async call builder
     */
    public CreateGroupRequestBuilder create() {
        return new CreateGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Create a group to screen.
     *
     * <p>Create a group to screen: its name, start, venue, host, the principals it protects and the sensitive
     * topics posts are read against. An environment holds at most 1000 groups.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Creates a group.
     * @return {@code CompletableFuture<CreateGroupResponse>} - The async response
     */
    public CompletableFuture<CreateGroupResponse> create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull GroupRequest body) {
        CreateGroupRequest request = new CreateGroupRequest(workspaceId, environment, body);
        AsyncRequestOperation<CreateGroupRequest, CreateGroupResponse> operation =
                new CreateGroup.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read a group.
     *
     * <p>Read a group and how many people it holds.
     *
     * @return The async call builder
     */
    public GetGroupRequestBuilder get() {
        return new GetGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a group.
     *
     * <p>Read a group and how many people it holds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @return {@code CompletableFuture<GetGroupResponse>} - The async response
     */
    public CompletableFuture<GetGroupResponse> get(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String groupId) {
        return get(workspaceId, environment, groupId, null);
    }

    /**
     * Read a group.
     *
     * <p>Read a group and how many people it holds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param options additional options
     * @return {@code CompletableFuture<GetGroupResponse>} - The async response
     */
    public CompletableFuture<GetGroupResponse> get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nullable Options options) {
        GetGroupRequest request = new GetGroupRequest(workspaceId, environment, groupId);
        AsyncRequestOperation<GetGroupRequest, GetGroupResponse> operation =
                new GetGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Change a group.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @return The async call builder
     */
    public UpdateGroupRequestBuilder update() {
        return new UpdateGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Change a group.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param body Changes the fields it names; at least one besides expected_version.
     * @return {@code CompletableFuture<UpdateGroupResponse>} - The async response
     */
    public CompletableFuture<UpdateGroupResponse> update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull GroupUpdate body) {
        return update(workspaceId, environment, groupId, body, null);
    }

    /**
     * Change a group.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param body Changes the fields it names; at least one besides expected_version.
     * @param options additional options
     * @return {@code CompletableFuture<UpdateGroupResponse>} - The async response
     */
    public CompletableFuture<UpdateGroupResponse> update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull GroupUpdate body,
            @Nullable Options options) {
        UpdateGroupRequest request = new UpdateGroupRequest(workspaceId, environment, groupId, body);
        AsyncRequestOperation<UpdateGroupRequest, UpdateGroupResponse> operation =
                new UpdateGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Erase a group.
     *
     * <p>Erase a group with its people, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @return The async call builder
     */
    public DeleteGroupRequestBuilder delete() {
        return new DeleteGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Erase a group.
     *
     * <p>Erase a group with its people, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @return {@code CompletableFuture<DeleteGroupResponse>} - The async response
     */
    public CompletableFuture<DeleteGroupResponse> delete(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String groupId) {
        return delete(workspaceId, environment, groupId, null);
    }

    /**
     * Erase a group.
     *
     * <p>Erase a group with its people, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param options additional options
     * @return {@code CompletableFuture<DeleteGroupResponse>} - The async response
     */
    public CompletableFuture<DeleteGroupResponse> delete(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nullable Options options) {
        DeleteGroupRequest request = new DeleteGroupRequest(workspaceId, environment, groupId);
        AsyncRequestOperation<DeleteGroupRequest, DeleteGroupResponse> operation =
                new DeleteGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List a group's people.
     *
     * <p>Read a group's roster in upload order, with each person's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @return The async call builder
     */
    public ListPeopleRequestBuilder listPeople() {
        return new ListPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * List a group's people.
     *
     * <p>Read a group's roster in upload order, with each person's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListPeopleResponse>} - The async response
     */
    public CompletableFuture<ListPeopleResponse> listPeople(@Nonnull ListPeopleRequest request) {
        return listPeople(request, null);
    }

    /**
     * List a group's people.
     *
     * <p>Read a group's roster in upload order, with each person's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListPeopleResponse>} - The async response
     */
    public CompletableFuture<ListPeopleResponse> listPeople(
            @Nonnull ListPeopleRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListPeopleRequest, ListPeopleResponse> operation =
                new ListPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Add or update 1 to 500 people with an Idempotency-Key. A row with the same external_id, else email,
     * else phone as an existing person updates it. Identities are stored encrypted.
     *
     * @return The async call builder
     */
    public AddPeopleRequestBuilder addPeople() {
        return new AddPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Add or update 1 to 500 people with an Idempotency-Key. A row with the same external_id, else email,
     * else phone as an existing person updates it. Identities are stored encrypted.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<AddPeopleResponse>} - The async response
     */
    public CompletableFuture<AddPeopleResponse> addPeople(@Nonnull AddPeopleRequest request) {
        return addPeople(request, null);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Add or update 1 to 500 people with an Idempotency-Key. A row with the same external_id, else email,
     * else phone as an existing person updates it. Identities are stored encrypted.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<AddPeopleResponse>} - The async response
     */
    public CompletableFuture<AddPeopleResponse> addPeople(
            @Nonnull AddPeopleRequest request, @Nullable Options options) {
        AsyncRequestOperation<AddPeopleRequest, AddPeopleResponse> operation =
                new AddPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read a person's report.
     *
     * <p>Read a person's report: matched profiles, findings with their evidence, every recorded decision, and
     * the other groups where the same email or phone appears.
     *
     * @return The async call builder
     */
    public GetPersonRequestBuilder getPerson() {
        return new GetPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a person's report.
     *
     * <p>Read a person's report: matched profiles, findings with their evidence, every recorded decision, and
     * the other groups where the same email or phone appears.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param personId
     * @return {@code CompletableFuture<GetPersonResponse>} - The async response
     */
    public CompletableFuture<GetPersonResponse> getPerson(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String personId) {
        return getPerson(workspaceId, environment, groupId, personId, null);
    }

    /**
     * Read a person's report.
     *
     * <p>Read a person's report: matched profiles, findings with their evidence, every recorded decision, and
     * the other groups where the same email or phone appears.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param personId
     * @param options additional options
     * @return {@code CompletableFuture<GetPersonResponse>} - The async response
     */
    public CompletableFuture<GetPersonResponse> getPerson(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nullable Options options) {
        GetPersonRequest request = new GetPersonRequest(workspaceId, environment, groupId, personId);
        AsyncRequestOperation<GetPersonRequest, GetPersonResponse> operation =
                new GetPerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Record a decision on a person.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the person's; the
     * next screening refreshes everyone on watch or escalated.
     *
     * @return The async call builder
     */
    public ReviewPersonRequestBuilder reviewPerson() {
        return new ReviewPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Record a decision on a person.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the person's; the
     * next screening refreshes everyone on watch or escalated.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ReviewPersonResponse>} - The async response
     */
    public CompletableFuture<ReviewPersonResponse> reviewPerson(@Nonnull ReviewPersonRequest request) {
        AsyncRequestOperation<ReviewPersonRequest, ReviewPersonResponse> operation =
                new ReviewPerson.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List a group's screenings.
     *
     * <p>List a group's screenings, newest first.
     *
     * @return The async call builder
     */
    public ListScreeningsRequestBuilder listScreenings() {
        return new ListScreeningsRequestBuilder(sdkConfiguration);
    }

    /**
     * List a group's screenings.
     *
     * <p>List a group's screenings, newest first.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListScreeningsResponse>} - The async response
     */
    public CompletableFuture<ListScreeningsResponse> listScreenings(@Nonnull ListScreeningsRequest request) {
        return listScreenings(request, null);
    }

    /**
     * List a group's screenings.
     *
     * <p>List a group's screenings, newest first.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListScreeningsResponse>} - The async response
     */
    public CompletableFuture<ListScreeningsResponse> listScreenings(
            @Nonnull ListScreeningsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListScreeningsRequest, ListScreeningsResponse> operation =
                new ListScreenings.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Start a screening.
     *
     * <p>Start a screening of new people and everyone on watch or escalated, with an Idempotency-Key. It
     * finishes completed, partial or failed minutes to hours later; poll it. The answer carries
     * estimated_spend; a screening whose estimate exceeds what the daily screening cap has left answers
     * 409 screening_cap_exceeded.
     *
     * <p>The test environment screens with the synthetic connector, which makes up deterministic profiles,
     * posts and findings.
     *
     * @return The async call builder
     */
    public StartScreeningRequestBuilder startScreening() {
        return new StartScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Start a screening.
     *
     * <p>Start a screening of new people and everyone on watch or escalated, with an Idempotency-Key. It
     * finishes completed, partial or failed minutes to hours later; poll it. The answer carries
     * estimated_spend; a screening whose estimate exceeds what the daily screening cap has left answers
     * 409 screening_cap_exceeded.
     *
     * <p>The test environment screens with the synthetic connector, which makes up deterministic profiles,
     * posts and findings.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<StartScreeningResponse>} - The async response
     */
    public CompletableFuture<StartScreeningResponse> startScreening(@Nonnull StartScreeningRequest request) {
        return startScreening(request, null);
    }

    /**
     * Start a screening.
     *
     * <p>Start a screening of new people and everyone on watch or escalated, with an Idempotency-Key. It
     * finishes completed, partial or failed minutes to hours later; poll it. The answer carries
     * estimated_spend; a screening whose estimate exceeds what the daily screening cap has left answers
     * 409 screening_cap_exceeded.
     *
     * <p>The test environment screens with the synthetic connector, which makes up deterministic profiles,
     * posts and findings.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<StartScreeningResponse>} - The async response
     */
    public CompletableFuture<StartScreeningResponse> startScreening(
            @Nonnull StartScreeningRequest request, @Nullable Options options) {
        AsyncRequestOperation<StartScreeningRequest, StartScreeningResponse> operation =
                new StartScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read a screening.
     *
     * <p>Read a screening's status and progress.
     *
     * @return The async call builder
     */
    public GetScreeningRequestBuilder getScreening() {
        return new GetScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a screening.
     *
     * <p>Read a screening's status and progress.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param screeningId
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String screeningId) {
        return getScreening(workspaceId, environment, groupId, screeningId, null);
    }

    /**
     * Read a screening.
     *
     * <p>Read a screening's status and progress.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param groupId
     * @param screeningId
     * @param options additional options
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String screeningId,
            @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(workspaceId, environment, groupId, screeningId);
        AsyncRequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. People already screened keep their results.
     *
     * @return The async call builder
     */
    public CancelScreeningRequestBuilder cancelScreening() {
        return new CancelScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. People already screened keep their results.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<CancelScreeningResponse>} - The async response
     */
    public CompletableFuture<CancelScreeningResponse> cancelScreening(@Nonnull CancelScreeningRequest request) {
        return cancelScreening(request, null);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. People already screened keep their results.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<CancelScreeningResponse>} - The async response
     */
    public CompletableFuture<CancelScreeningResponse> cancelScreening(
            @Nonnull CancelScreeningRequest request, @Nullable Options options) {
        AsyncRequestOperation<CancelScreeningRequest, CancelScreeningResponse> operation =
                new CancelScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
