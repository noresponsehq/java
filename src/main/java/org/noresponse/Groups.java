package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.AddPeopleRequest;
import org.noresponse.models.operations.AddPeopleRequestBuilder;
import org.noresponse.models.operations.AddPeopleResponse;
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.models.operations.CancelScreeningRequestBuilder;
import org.noresponse.models.operations.CancelScreeningResponse;
import org.noresponse.models.operations.CreateGroupRequest;
import org.noresponse.models.operations.CreateGroupRequestBuilder;
import org.noresponse.models.operations.CreateGroupResponse;
import org.noresponse.models.operations.DeleteGroupRequest;
import org.noresponse.models.operations.DeleteGroupRequestBuilder;
import org.noresponse.models.operations.DeleteGroupResponse;
import org.noresponse.models.operations.GetGroupRequest;
import org.noresponse.models.operations.GetGroupRequestBuilder;
import org.noresponse.models.operations.GetGroupResponse;
import org.noresponse.models.operations.GetPersonRequest;
import org.noresponse.models.operations.GetPersonRequestBuilder;
import org.noresponse.models.operations.GetPersonResponse;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.operations.GetScreeningRequestBuilder;
import org.noresponse.models.operations.GetScreeningResponse;
import org.noresponse.models.operations.JudgeRecordRequest;
import org.noresponse.models.operations.JudgeRecordRequestBuilder;
import org.noresponse.models.operations.JudgeRecordResponse;
import org.noresponse.models.operations.ListGroupsRequest;
import org.noresponse.models.operations.ListGroupsRequestBuilder;
import org.noresponse.models.operations.ListGroupsResponse;
import org.noresponse.models.operations.ListPeopleRequest;
import org.noresponse.models.operations.ListPeopleRequestBuilder;
import org.noresponse.models.operations.ListPeopleResponse;
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.ListScreeningsRequestBuilder;
import org.noresponse.models.operations.ListScreeningsResponse;
import org.noresponse.models.operations.ReviewPersonRequest;
import org.noresponse.models.operations.ReviewPersonRequestBuilder;
import org.noresponse.models.operations.ReviewPersonResponse;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.StartScreeningRequestBuilder;
import org.noresponse.models.operations.StartScreeningResponse;
import org.noresponse.models.operations.UpdateGroupRequest;
import org.noresponse.models.operations.UpdateGroupRequestBuilder;
import org.noresponse.models.operations.UpdateGroupResponse;
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
import org.noresponse.operations.JudgeRecord;
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
public class Groups {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncGroups asyncSDK;

    Groups(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncGroups(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncGroups async() {
        return asyncSDK;
    }

    /**
     * List an environment's groups.
     *
     * <p>List this environment's groups, newest first. Pass next_before back as before for the next page.
     * Requires the groups scope.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListGroupsResponse list(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListGroupsResponse list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String before,
            @Nullable Options options) {
        ListGroupsRequest request = new ListGroupsRequest(workspaceId, environment, limit, before);
        RequestOperation<ListGroupsRequest, ListGroupsResponse> operation =
                new ListGroups.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Create a group to screen.
     *
     * <p>Create a group to screen: its name, start, venue, host, the principals it protects and the sensitive
     * topics posts are read against. An environment holds at most 1000 groups.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateGroupResponse create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull GroupRequest body) {
        CreateGroupRequest request = new CreateGroupRequest(workspaceId, environment, body);
        RequestOperation<CreateGroupRequest, CreateGroupResponse> operation =
                new CreateGroup.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a group.
     *
     * <p>Read a group and how many people it holds.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetGroupResponse get(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetGroupResponse get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nullable Options options) {
        GetGroupRequest request = new GetGroupRequest(workspaceId, environment, groupId);
        RequestOperation<GetGroupRequest, GetGroupResponse> operation =
                new GetGroup.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Change a group.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateGroupResponse update(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateGroupResponse update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull GroupUpdate body,
            @Nullable Options options) {
        UpdateGroupRequest request = new UpdateGroupRequest(workspaceId, environment, groupId, body);
        RequestOperation<UpdateGroupRequest, UpdateGroupResponse> operation =
                new UpdateGroup.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Erase a group.
     *
     * <p>Erase a group with its people, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteGroupResponse delete(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteGroupResponse delete(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nullable Options options) {
        DeleteGroupRequest request = new DeleteGroupRequest(workspaceId, environment, groupId);
        RequestOperation<DeleteGroupRequest, DeleteGroupResponse> operation =
                new DeleteGroup.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List a group's people.
     *
     * <p>Read a group's roster in upload order, with each person's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPeopleResponse listPeople(@Nonnull ListPeopleRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListPeopleResponse listPeople(@Nonnull ListPeopleRequest request, @Nullable Options options) {
        RequestOperation<ListPeopleRequest, ListPeopleResponse> operation =
                new ListPeople.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Add or update 1 to 500 people with an Idempotency-Key. A row with the same external_id, else email,
     * else phone as an existing person updates it. Identities are stored encrypted.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddPeopleResponse addPeople(@Nonnull AddPeopleRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddPeopleResponse addPeople(@Nonnull AddPeopleRequest request, @Nullable Options options) {
        RequestOperation<AddPeopleRequest, AddPeopleResponse> operation =
                new AddPeople.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a person's report.
     *
     * <p>Read a person's report: matched profiles, findings with their evidence, every recorded decision, and
     * the other groups where the same email or phone appears.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPersonResponse getPerson(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPersonResponse getPerson(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nullable Options options) {
        GetPersonRequest request = new GetPersonRequest(workspaceId, environment, groupId, personId);
        RequestOperation<GetPersonRequest, GetPersonResponse> operation =
                new GetPerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record a decision on a person.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the person's; the
     * next screening refreshes everyone on watch or escalated.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ReviewPersonResponse reviewPerson(@Nonnull ReviewPersonRequest request) {
        RequestOperation<ReviewPersonRequest, ReviewPersonResponse> operation =
                new ReviewPerson.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Join one of a person's records to them, or reject it.
     *
     * <p>Join one of a person's lookup records to them whatever its likelihood, or reject it so it stays a
     * lead. The verdict sticks while lookups find the same record.
     *
     * @return The call builder
     */
    public JudgeRecordRequestBuilder judgeRecord() {
        return new JudgeRecordRequestBuilder(sdkConfiguration);
    }

    /**
     * Join one of a person's records to them, or reject it.
     *
     * <p>Join one of a person's lookup records to them whatever its likelihood, or reject it so it stays a
     * lead. The verdict sticks while lookups find the same record.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeRecordResponse judgeRecord(@Nonnull JudgeRecordRequest request) {
        return judgeRecord(request, null);
    }

    /**
     * Join one of a person's records to them, or reject it.
     *
     * <p>Join one of a person's lookup records to them whatever its likelihood, or reject it so it stays a
     * lead. The verdict sticks while lookups find the same record.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeRecordResponse judgeRecord(@Nonnull JudgeRecordRequest request, @Nullable Options options) {
        RequestOperation<JudgeRecordRequest, JudgeRecordResponse> operation =
                new JudgeRecord.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List a group's screenings.
     *
     * <p>List a group's screenings, newest first.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListScreeningsResponse listScreenings(@Nonnull ListScreeningsRequest request) {
        return listScreenings(request, null);
    }

    /**
     * List a group's screenings.
     *
     * <p>List a group's screenings, newest first.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListScreeningsResponse listScreenings(@Nonnull ListScreeningsRequest request, @Nullable Options options) {
        RequestOperation<ListScreeningsRequest, ListScreeningsResponse> operation =
                new ListScreenings.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
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
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartScreeningResponse startScreening(@Nonnull StartScreeningRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartScreeningResponse startScreening(@Nonnull StartScreeningRequest request, @Nullable Options options) {
        RequestOperation<StartScreeningRequest, StartScreeningResponse> operation =
                new StartScreening.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a screening.
     *
     * <p>Read a screening's status and progress.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nonnull String screeningId,
            @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(workspaceId, environment, groupId, screeningId);
        RequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. People already screened keep their results.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelScreeningResponse cancelScreening(@Nonnull CancelScreeningRequest request) {
        return cancelScreening(request, null);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. People already screened keep their results.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelScreeningResponse cancelScreening(@Nonnull CancelScreeningRequest request, @Nullable Options options) {
        RequestOperation<CancelScreeningRequest, CancelScreeningResponse> operation =
                new CancelScreening.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
