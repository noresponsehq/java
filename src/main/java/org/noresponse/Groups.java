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
import org.noresponse.models.operations.ExportPeopleRequest;
import org.noresponse.models.operations.ExportPeopleRequestBuilder;
import org.noresponse.models.operations.ExportPeopleResponse;
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
import org.noresponse.models.operations.RunSearchRequest;
import org.noresponse.models.operations.RunSearchRequestBuilder;
import org.noresponse.models.operations.RunSearchResponse;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.StartScreeningRequestBuilder;
import org.noresponse.models.operations.StartScreeningResponse;
import org.noresponse.models.operations.StartSearchRequest;
import org.noresponse.models.operations.StartSearchRequestBuilder;
import org.noresponse.models.operations.StartSearchResponse;
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
import org.noresponse.operations.ExportPeople;
import org.noresponse.operations.GetGroup;
import org.noresponse.operations.GetPerson;
import org.noresponse.operations.GetScreening;
import org.noresponse.operations.JudgeRecord;
import org.noresponse.operations.ListGroups;
import org.noresponse.operations.ListPeople;
import org.noresponse.operations.ListScreenings;
import org.noresponse.operations.ReviewPerson;
import org.noresponse.operations.RunSearch;
import org.noresponse.operations.StartScreening;
import org.noresponse.operations.StartSearch;
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
     * <p>Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions
     * override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public ListGroupsRequestBuilder list() {
        return new ListGroupsRequestBuilder(sdkConfiguration);
    }

    /**
     * List an environment's groups.
     *
     * <p>Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions
     * override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions
     * override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Creates a group with its name, start, venue, host, the principals it protects and the sensitive
     * topics its screening reads posts against. An environment holds at most 1000 groups. Send an
     * Idempotency-Key to retry safely: the same key and request return the same resource in its current
     * state while it is retained, even after work completes.
     *
     * <p>Reusing the key with different input answers 409 idempotency_key_reused. Use a new key for a
     * deliberate new operation. The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @return The call builder
     */
    public CreateGroupRequestBuilder create() {
        return new CreateGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Create a group to screen.
     *
     * <p>Creates a group with its name, start, venue, host, the principals it protects and the sensitive
     * topics its screening reads posts against. An environment holds at most 1000 groups. Send an
     * Idempotency-Key to retry safely: the same key and request return the same resource in its current
     * state while it is retained, even after work completes.
     *
     * <p>Reusing the key with different input answers 409 idempotency_key_reused. Use a new key for a
     * deliberate new operation. The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Creates a group.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateGroupResponse create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull GroupRequest body) {
        return create(workspaceId, environment, null, body);
    }

    /**
     * Create a group to screen.
     *
     * <p>Creates a group with its name, start, venue, host, the principals it protects and the sensitive
     * topics its screening reads posts against. An environment holds at most 1000 groups. Send an
     * Idempotency-Key to retry safely: the same key and request return the same resource in its current
     * state while it is retained, even after work completes.
     *
     * <p>Reusing the key with different input answers 409 idempotency_key_reused. Use a new key for a
     * deliberate new operation. The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body Creates a group.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateGroupResponse create(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable String idempotencyKey,
            @Nonnull GroupRequest body) {
        CreateGroupRequest request = new CreateGroupRequest(workspaceId, environment, idempotencyKey, body);
        RequestOperation<CreateGroupRequest, CreateGroupResponse> operation =
                new CreateGroup.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a group.
     *
     * <p>The group and how many people it holds. The people scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public GetGroupRequestBuilder get() {
        return new GetGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a group.
     *
     * <p>The group and how many people it holds. The people scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>The group and how many people it holds. The people scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Changes the fields the body names. With expected_version, the change applies only to that version
     * (409 group_conflict otherwise). The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @return The call builder
     */
    public UpdateGroupRequestBuilder update() {
        return new UpdateGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Change a group.
     *
     * <p>Changes the fields the body names. With expected_version, the change applies only to that version
     * (409 group_conflict otherwise). The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>Changes the fields the body names. With expected_version, the change applies only to that version
     * (409 group_conflict otherwise). The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>Erases the group with its people, their reviews and its screenings, and stops any screening still
     * running. The delete scope is required; group read, write, investigation and review access do not
     * grant deletion.
     *
     * @return The call builder
     */
    public DeleteGroupRequestBuilder delete() {
        return new DeleteGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Erase a group.
     *
     * <p>Erases the group with its people, their reviews and its screenings, and stops any screening still
     * running. The delete scope is required; group read, write, investigation and review access do not
     * grant deletion.
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
     * <p>Erases the group with its people, their reviews and its screenings, and stops any screening still
     * running. The delete scope is required; group read, write, investigation and review access do not
     * grant deletion.
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
     * <p>The roster in upload order, with each person's role, match state, highest finding band and decision.
     * Filter by band (or none), decision (or undecided) and role (or unstated). The people scope grants
     * this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @return The call builder
     */
    public ListPeopleRequestBuilder listPeople() {
        return new ListPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * List a group's people.
     *
     * <p>The roster in upload order, with each person's role, match state, highest finding band and decision.
     * Filter by band (or none), decision (or undecided) and role (or unstated). The people scope grants
     * this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>The roster in upload order, with each person's role, match state, highest finding band and decision.
     * Filter by band (or none), decision (or undecided) and role (or unstated). The people scope grants
     * this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>Adds 1 to 500 people, at most 512 KiB. A row naming an existing person in the group (the same
     * external_id, else the same email, else the same phone) updates it; a changed identity is screened
     * afresh by the next screening. Identities are stored encrypted under the workspace's key.
     *
     * <p>Idempotency-Key is required: a retry with the same key and body within 24 hours answers the first
     * result with Idempotent-Replayed: true, and the same key with another body answers 409
     * idempotency_key_reused. A group holds at most 20000 people. The people_write scope grants this
     * operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @return The call builder
     */
    public AddPeopleRequestBuilder addPeople() {
        return new AddPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Adds 1 to 500 people, at most 512 KiB. A row naming an existing person in the group (the same
     * external_id, else the same email, else the same phone) updates it; a changed identity is screened
     * afresh by the next screening. Identities are stored encrypted under the workspace's key.
     *
     * <p>Idempotency-Key is required: a retry with the same key and body within 24 hours answers the first
     * result with Idempotent-Replayed: true, and the same key with another body answers 409
     * idempotency_key_reused. A group holds at most 20000 people. The people_write scope grants this
     * operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>Adds 1 to 500 people, at most 512 KiB. A row naming an existing person in the group (the same
     * external_id, else the same email, else the same phone) updates it; a changed identity is screened
     * afresh by the next screening. Identities are stored encrypted under the workspace's key.
     *
     * <p>Idempotency-Key is required: a retry with the same key and body within 24 hours answers the first
     * result with Idempotent-Replayed: true, and the same key with another body answers 409
     * idempotency_key_reused. A group holds at most 20000 people. The people_write scope grants this
     * operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
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
     * <p>The person, the profiles the screening matched with how each was matched, its findings with their
     * evidence, every recorded decision, and the other groups in the environment where a person with the
     * same email or phone appears, with the decision recorded there. Profiles and findings fill in as the
     * screening pipeline ships. The people scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations. Each successful read is durably audited as person.viewed.
     *
     * <p>Restricted responses keep submitted details, coverage, job status and decision summaries and set
     * evidence_restricted to true.
     *
     * @return The call builder
     */
    public GetPersonRequestBuilder getPerson() {
        return new GetPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a person's report.
     *
     * <p>The person, the profiles the screening matched with how each was matched, its findings with their
     * evidence, every recorded decision, and the other groups in the environment where a person with the
     * same email or phone appears, with the decision recorded there. Profiles and findings fill in as the
     * screening pipeline ships. The people scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations. Each successful read is durably audited as person.viewed.
     *
     * <p>Restricted responses keep submitted details, coverage, job status and decision summaries and set
     * evidence_restricted to true.
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
     * <p>The person, the profiles the screening matched with how each was matched, its findings with their
     * evidence, every recorded decision, and the other groups in the environment where a person with the
     * same email or phone appears, with the decision recorded there. Profiles and findings fill in as the
     * screening pipeline ships. The people scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations. Each successful read is durably audited as person.viewed.
     *
     * <p>Restricted responses keep submitted details, coverage, job status and decision summaries and set
     * evidence_restricted to true.
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
     * <p>Marks the person cleared, watch or escalated with a note. Decisions are kept in order; the latest is
     * the person's. The next screening refreshes everyone on watch or escalated.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public ReviewPersonRequestBuilder reviewPerson() {
        return new ReviewPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Record a decision on a person.
     *
     * <p>Marks the person cleared, watch or escalated with a note. Decisions are kept in order; the latest is
     * the person's. The next screening refreshes everyone on watch or escalated.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
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
     * <p>Records a verdict on one of the records the person's latest lookups found: joined makes the record
     * the person's whatever its likelihood, and rejected keeps it a lead. The verdict sticks for as long
     * as a lookup finds the same record, and a later verdict on the same record replaces it. A record the
     * person's latest lookup does not hold answers 404.
     *
     * <p>Each verdict is audited as person.record_judged. The decisions scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public JudgeRecordRequestBuilder judgeRecord() {
        return new JudgeRecordRequestBuilder(sdkConfiguration);
    }

    /**
     * Join one of a person's records to them, or reject it.
     *
     * <p>Records a verdict on one of the records the person's latest lookups found: joined makes the record
     * the person's whatever its likelihood, and rejected keeps it a lead. The verdict sticks for as long
     * as a lookup finds the same record, and a later verdict on the same record replaces it. A record the
     * person's latest lookup does not hold answers 404.
     *
     * <p>Each verdict is audited as person.record_judged. The decisions scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Records a verdict on one of the records the person's latest lookups found: joined makes the record
     * the person's whatever its likelihood, and rejected keeps it a lead. The verdict sticks for as long
     * as a lookup finds the same record, and a later verdict on the same record replaces it. A record the
     * person's latest lookup does not hold answers 404.
     *
     * <p>Each verdict is audited as person.record_judged. The decisions scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * Search again on a value of one of a person's records.
     *
     * <p>Starts a new search on the value of one field of one of the person's records: a lookup's, a
     * profile's or one an earlier search found. Name the record and the field; the value itself is not
     * sent. The field's kind must be email, phone, username, person_name, password, hash, ip or domain.
     *
     * <p>A domain search finds the breach rows of every email address at the domain, which must be a
     * registrable domain or a host under one; any other domain answers 400. Nothing ever searches again by
     * itself: a search starts only when a person asks. The search runs the breach search and the account
     * and phone lookups that take the value's kind, under the environment's source controls, and finishes
     * seconds later.
     *
     * <p>A password or hash search counts first: it stops at counted with rows, how many breach rows hold the
     * value, and runs only when a person runs it. Name a table, as a lookup remaining entry names it, to
     * fetch the rows an earlier search left out, up to 100. What the search finds joins the person's
     * lookup records as leads, each match naming the search; the person report lists the searches, each
     * with the value and the record and field it came from, and its records are scored like the rest.
     *
     * <p>Without an Idempotency-Key, starting the same open search returns it. Its estimate counts against
     * the environment's screening_daily_cap, and one the cap cannot hold answers 409
     * screening_cap_exceeded. A record or field the person does not hold answers 404.
     *
     * <p>Each search is audited as person.search_started. Send an Idempotency-Key to retry safely: the same
     * key and request return the same resource in its current state while it is retained, even after work
     * completes. Reusing the key with different input answers 409 idempotency_key_reused.
     *
     * <p>Use a new key for a deliberate new operation. The investigations scope grants this operation.
     * Explicit evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public StartSearchRequestBuilder startSearch() {
        return new StartSearchRequestBuilder(sdkConfiguration);
    }

    /**
     * Search again on a value of one of a person's records.
     *
     * <p>Starts a new search on the value of one field of one of the person's records: a lookup's, a
     * profile's or one an earlier search found. Name the record and the field; the value itself is not
     * sent. The field's kind must be email, phone, username, person_name, password, hash, ip or domain.
     *
     * <p>A domain search finds the breach rows of every email address at the domain, which must be a
     * registrable domain or a host under one; any other domain answers 400. Nothing ever searches again by
     * itself: a search starts only when a person asks. The search runs the breach search and the account
     * and phone lookups that take the value's kind, under the environment's source controls, and finishes
     * seconds later.
     *
     * <p>A password or hash search counts first: it stops at counted with rows, how many breach rows hold the
     * value, and runs only when a person runs it. Name a table, as a lookup remaining entry names it, to
     * fetch the rows an earlier search left out, up to 100. What the search finds joins the person's
     * lookup records as leads, each match naming the search; the person report lists the searches, each
     * with the value and the record and field it came from, and its records are scored like the rest.
     *
     * <p>Without an Idempotency-Key, starting the same open search returns it. Its estimate counts against
     * the environment's screening_daily_cap, and one the cap cannot hold answers 409
     * screening_cap_exceeded. A record or field the person does not hold answers 404.
     *
     * <p>Each search is audited as person.search_started. Send an Idempotency-Key to retry safely: the same
     * key and request return the same resource in its current state while it is retained, even after work
     * completes. Reusing the key with different input answers 409 idempotency_key_reused.
     *
     * <p>Use a new key for a deliberate new operation. The investigations scope grants this operation.
     * Explicit evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartSearchResponse startSearch(@Nonnull StartSearchRequest request) {
        RequestOperation<StartSearchRequest, StartSearchResponse> operation =
                new StartSearch.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Run a counted password or hash search.
     *
     * <p>Runs a password or hash search once a person has seen its count. A search still counting answers 409
     * group_conflict; one already queued, running or finished answers as it is. The body is an empty
     * object.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap. Each run is audited as
     * person.search_run. The investigations scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @return The call builder
     */
    public RunSearchRequestBuilder runSearch() {
        return new RunSearchRequestBuilder(sdkConfiguration);
    }

    /**
     * Run a counted password or hash search.
     *
     * <p>Runs a password or hash search once a person has seen its count. A search still counting answers 409
     * group_conflict; one already queued, running or finished answers as it is. The body is an empty
     * object.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap. Each run is audited as
     * person.search_run. The investigations scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RunSearchResponse runSearch(@Nonnull RunSearchRequest request) {
        return runSearch(request, null);
    }

    /**
     * Run a counted password or hash search.
     *
     * <p>Runs a password or hash search once a person has seen its count. A search still counting answers 409
     * group_conflict; one already queued, running or finished answers as it is. The body is an empty
     * object.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap. Each run is audited as
     * person.search_run. The investigations scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RunSearchResponse runSearch(@Nonnull RunSearchRequest request, @Nullable Options options) {
        RequestOperation<RunSearchRequest, RunSearchResponse> operation =
                new RunSearch.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List a group's screenings.
     *
     * <p>Screenings, newest first. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public ListScreeningsRequestBuilder listScreenings() {
        return new ListScreeningsRequestBuilder(sdkConfiguration);
    }

    /**
     * List a group's screenings.
     *
     * <p>Screenings, newest first. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Screenings, newest first. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Queues a screening of every person no screening has reached and everyone on watch or escalated. One
     * screening of a group runs at a time; it finishes completed, partial (some people not covered,
     * counted in people_failed) or failed, and fails with error_code expired after 24 hours.
     * Idempotency-Key is required and binds the body for the screening's life.
     *
     * <p>The 202 body carries estimated_spend, what the selected people would cost at the depth and
     * platforms. A screening whose estimate exceeds the environment's screening_daily_cap, less the day's
     * recorded spend and what open screenings may still spend, answers 409 screening_cap_exceeded and
     * queues nothing. The test environment screens with a deterministic simulator that contacts no source.
     *
     * <p>A platform that no connector serves in the environment answers 409 screening_unavailable. The
     * investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public StartScreeningRequestBuilder startScreening() {
        return new StartScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Start a screening.
     *
     * <p>Queues a screening of every person no screening has reached and everyone on watch or escalated. One
     * screening of a group runs at a time; it finishes completed, partial (some people not covered,
     * counted in people_failed) or failed, and fails with error_code expired after 24 hours.
     * Idempotency-Key is required and binds the body for the screening's life.
     *
     * <p>The 202 body carries estimated_spend, what the selected people would cost at the depth and
     * platforms. A screening whose estimate exceeds the environment's screening_daily_cap, less the day's
     * recorded spend and what open screenings may still spend, answers 409 screening_cap_exceeded and
     * queues nothing. The test environment screens with a deterministic simulator that contacts no source.
     *
     * <p>A platform that no connector serves in the environment answers 409 screening_unavailable. The
     * investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Queues a screening of every person no screening has reached and everyone on watch or escalated. One
     * screening of a group runs at a time; it finishes completed, partial (some people not covered,
     * counted in people_failed) or failed, and fails with error_code expired after 24 hours.
     * Idempotency-Key is required and binds the body for the screening's life.
     *
     * <p>The 202 body carries estimated_spend, what the selected people would cost at the depth and
     * platforms. A screening whose estimate exceeds the environment's screening_daily_cap, less the day's
     * recorded spend and what open screenings may still spend, answers 409 screening_cap_exceeded and
     * queues nothing. The test environment screens with a deterministic simulator that contacts no source.
     *
     * <p>A platform that no connector serves in the environment answers 409 screening_unavailable. The
     * investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>The screening's status and progress. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public GetScreeningRequestBuilder getScreening() {
        return new GetScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a screening.
     *
     * <p>The screening's status and progress. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>The screening's status and progress. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Stops a queued or running screening. People it already screened keep their results. Cancelling a
     * finished screening answers it unchanged.
     *
     * <p>The body is an empty object. The investigations scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
     *
     * @return The call builder
     */
    public CancelScreeningRequestBuilder cancelScreening() {
        return new CancelScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stops a queued or running screening. People it already screened keep their results. Cancelling a
     * finished screening answers it unchanged.
     *
     * <p>The body is an empty object. The investigations scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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
     * <p>Stops a queued or running screening. People it already screened keep their results. Cancelling a
     * finished screening answers it unchanged.
     *
     * <p>The body is an empty object. The investigations scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations.
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

    /**
     * Export the people in a group.
     *
     * <p>Returns one page for export. Each returned person is durably audited as person.exported with the
     * actor, group, person ID and request ID. Requires both people read access and exports.
     *
     * <p>Explicit evidence restrictions also hide free-text decision notes. No raw evidence enters audit
     * logs. Follow next_after until absent for a complete export.
     *
     * @return The call builder
     */
    public ExportPeopleRequestBuilder exportPeople() {
        return new ExportPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * Export the people in a group.
     *
     * <p>Returns one page for export. Each returned person is durably audited as person.exported with the
     * actor, group, person ID and request ID. Requires both people read access and exports.
     *
     * <p>Explicit evidence restrictions also hide free-text decision notes. No raw evidence enters audit
     * logs. Follow next_after until absent for a complete export.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ExportPeopleResponse exportPeople(@Nonnull ExportPeopleRequest request) {
        return exportPeople(request, null);
    }

    /**
     * Export the people in a group.
     *
     * <p>Returns one page for export. Each returned person is durably audited as person.exported with the
     * actor, group, person ID and request ID. Requires both people read access and exports.
     *
     * <p>Explicit evidence restrictions also hide free-text decision notes. No raw evidence enters audit
     * logs. Follow next_after until absent for a complete export.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ExportPeopleResponse exportPeople(@Nonnull ExportPeopleRequest request, @Nullable Options options) {
        RequestOperation<ExportPeopleRequest, ExportPeopleResponse> operation =
                new ExportPeople.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
