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
import org.noresponse.models.operations.RemoveGroupPersonRequest;
import org.noresponse.models.operations.RemoveGroupPersonRequestBuilder;
import org.noresponse.models.operations.RemoveGroupPersonResponse;
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
import org.noresponse.models.shared.PersonBatchRequest;
import org.noresponse.models.shared.PersonReviewRequest;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.models.shared.ScreeningRequest;
import org.noresponse.models.shared.SearchRequest;
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
import org.noresponse.operations.RemoveGroupPerson;
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListGroupsResponse listDirect() {
        return list(null, null, null);
    }

    /**
     * List an environment's groups.
     *
     * <p>Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions
     * override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param limit At most this many items.
     * @param before Read groups older than this one.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListGroupsResponse list(@Nullable Long limit, @Nullable String before, @Nullable Options options) {
        ListGroupsRequest request = new ListGroupsRequest(limit, before);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param body Creates an ongoing group or an event with optional dates.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateGroupResponse create(@Nonnull GroupRequest body) {
        return create(null, body);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body Creates an ongoing group or an event with optional dates.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateGroupResponse create(@Nullable String idempotencyKey, @Nonnull GroupRequest body) {
        CreateGroupRequest request = new CreateGroupRequest(idempotencyKey, body);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetGroupResponse get(@Nonnull String groupId) {
        return get(groupId, null);
    }

    /**
     * Read a group.
     *
     * <p>The group and how many people it holds. The people scope grants this operation. Explicit
     * evidence.read restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetGroupResponse get(@Nonnull String groupId, @Nullable Options options) {
        GetGroupRequest request = new GetGroupRequest(groupId);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param groupId
     * @param body Updates a group. Dates describe an optional event and do not control person retention.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateGroupResponse update(@Nonnull String groupId, @Nonnull GroupUpdate body) {
        return update(groupId, body, null);
    }

    /**
     * Change a group.
     *
     * <p>Changes the fields the body names. With expected_version, the change applies only to that version
     * (409 group_conflict otherwise). The people_write scope grants this operation.
     *
     * <p>Explicit evidence.read restrictions override default evidence access. The groups scope is also
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param groupId
     * @param body Updates a group. Dates describe an optional event and do not control person retention.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateGroupResponse update(@Nonnull String groupId, @Nonnull GroupUpdate body, @Nullable Options options) {
        UpdateGroupRequest request = new UpdateGroupRequest(groupId, body);
        RequestOperation<UpdateGroupRequest, UpdateGroupResponse> operation =
                new UpdateGroup.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Erase a group.
     *
     * <p>Deletes the group, its memberships and its review context, and cancels unfinished group work.
     * Workspace people, source evidence and completed investigations remain available. Retained
     * investigations no longer name the deleted group.
     *
     * <p>The delete scope is required; group read, write, investigation and review access do not grant
     * deletion. The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public DeleteGroupRequestBuilder delete() {
        return new DeleteGroupRequestBuilder(sdkConfiguration);
    }

    /**
     * Erase a group.
     *
     * <p>Deletes the group, its memberships and its review context, and cancels unfinished group work.
     * Workspace people, source evidence and completed investigations remain available. Retained
     * investigations no longer name the deleted group.
     *
     * <p>The delete scope is required; group read, write, investigation and review access do not grant
     * deletion. The API key selects the workspace and environment.
     *
     * @param groupId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteGroupResponse delete(@Nonnull String groupId) {
        return delete(groupId, null);
    }

    /**
     * Erase a group.
     *
     * <p>Deletes the group, its memberships and its review context, and cancels unfinished group work.
     * Workspace people, source evidence and completed investigations remain available. Retained
     * investigations no longer name the deleted group.
     *
     * <p>The delete scope is required; group read, write, investigation and review access do not grant
     * deletion. The API key selects the workspace and environment.
     *
     * @param groupId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteGroupResponse delete(@Nonnull String groupId, @Nullable Options options) {
        DeleteGroupRequest request = new DeleteGroupRequest(groupId);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * <p>Adds 1 to 500 workspace people to this group, at most 512 KiB. An explicit person_id or external_id
     * reuses the named workspace person. A reference-only row keeps submitted details; an identity
     * supplied with that reference replaces those details.
     *
     * <p>A shared email or phone only suggests matches and does not merge people. Each person's match, band
     * and coverage in the group come from their latest investigation; a changed identity sets aside
     * investigations started before the change, and prior investigation history remains available.
     * Identities are encrypted under the workspace key.
     *
     * <p>Idempotency-Key is required: the same key and body return the original import result with
     * Idempotent-Replayed: true while its people remain retained. An expired or deleted imported person
     * answers 404; expired or deleted match suggestions are omitted. A different body answers 409
     * idempotency_key_reused.
     *
     * <p>A group holds at most 20000 people. The people_write scope grants this operation; groups is also
     * accepted. Suggested matches require people-read access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public AddPeopleRequestBuilder addPeople() {
        return new AddPeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Adds 1 to 500 workspace people to this group, at most 512 KiB. An explicit person_id or external_id
     * reuses the named workspace person. A reference-only row keeps submitted details; an identity
     * supplied with that reference replaces those details.
     *
     * <p>A shared email or phone only suggests matches and does not merge people. Each person's match, band
     * and coverage in the group come from their latest investigation; a changed identity sets aside
     * investigations started before the change, and prior investigation history remains available.
     * Identities are encrypted under the workspace key.
     *
     * <p>Idempotency-Key is required: the same key and body return the original import result with
     * Idempotent-Replayed: true while its people remain retained. An expired or deleted imported person
     * answers 404; expired or deleted match suggestions are omitted. A different body answers 409
     * idempotency_key_reused.
     *
     * <p>A group holds at most 20000 people. The people_write scope grants this operation; groups is also
     * accepted. Suggested matches require people-read access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Adds 1 to 500 workspace people to the group. Reuses an explicit person_id or external_id. Matching email or phone values only suggest people; automatic merging is disabled.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddPeopleResponse addPeople(
            @Nonnull String groupId, @Nonnull String idempotencyKey, @Nonnull PersonBatchRequest body) {
        return addPeople(groupId, idempotencyKey, body, null);
    }

    /**
     * Add or update a batch of people.
     *
     * <p>Adds 1 to 500 workspace people to this group, at most 512 KiB. An explicit person_id or external_id
     * reuses the named workspace person. A reference-only row keeps submitted details; an identity
     * supplied with that reference replaces those details.
     *
     * <p>A shared email or phone only suggests matches and does not merge people. Each person's match, band
     * and coverage in the group come from their latest investigation; a changed identity sets aside
     * investigations started before the change, and prior investigation history remains available.
     * Identities are encrypted under the workspace key.
     *
     * <p>Idempotency-Key is required: the same key and body return the original import result with
     * Idempotent-Replayed: true while its people remain retained. An expired or deleted imported person
     * answers 404; expired or deleted match suggestions are omitted. A different body answers 409
     * idempotency_key_reused.
     *
     * <p>A group holds at most 20000 people. The people_write scope grants this operation; groups is also
     * accepted. Suggested matches require people-read access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Adds 1 to 500 workspace people to the group. Reuses an explicit person_id or external_id. Matching email or phone values only suggest people; automatic merging is disabled.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddPeopleResponse addPeople(
            @Nonnull String groupId,
            @Nonnull String idempotencyKey,
            @Nonnull PersonBatchRequest body,
            @Nullable Options options) {
        AddPeopleRequest request = new AddPeopleRequest(groupId, idempotencyKey, body);
        RequestOperation<AddPeopleRequest, AddPeopleResponse> operation =
                new AddPeople.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a person's report.
     *
     * <p>Returns submitted details, this group's latest screening evidence and contextual decisions, and the
     * other groups that explicitly include the same workspace person. Membership requires an explicit
     * person or customer reference. The people scope grants this operation; groups is also accepted.
     *
     * <p>Each successful read is durably audited as person.viewed. Full source records, posts and media are
     * returned by default. Explicit evidence.read denial keeps submitted details, coverage, job status and
     * decision summaries, hides collected evidence and free-text notes, and sets evidence_restricted to
     * true.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public GetPersonRequestBuilder getPerson() {
        return new GetPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a person's report.
     *
     * <p>Returns submitted details, this group's latest screening evidence and contextual decisions, and the
     * other groups that explicitly include the same workspace person. Membership requires an explicit
     * person or customer reference. The people scope grants this operation; groups is also accepted.
     *
     * <p>Each successful read is durably audited as person.viewed. Full source records, posts and media are
     * returned by default. Explicit evidence.read denial keeps submitted details, coverage, job status and
     * decision summaries, hides collected evidence and free-text notes, and sets evidence_restricted to
     * true.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPersonResponse getPerson(@Nonnull String groupId, @Nonnull String personId) {
        return getPerson(groupId, personId, null);
    }

    /**
     * Read a person's report.
     *
     * <p>Returns submitted details, this group's latest screening evidence and contextual decisions, and the
     * other groups that explicitly include the same workspace person. Membership requires an explicit
     * person or customer reference. The people scope grants this operation; groups is also accepted.
     *
     * <p>Each successful read is durably audited as person.viewed. Full source records, posts and media are
     * returned by default. Explicit evidence.read denial keeps submitted details, coverage, job status and
     * decision summaries, hides collected evidence and free-text notes, and sets evidence_restricted to
     * true.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetPersonResponse getPerson(@Nonnull String groupId, @Nonnull String personId, @Nullable Options options) {
        GetPersonRequest request = new GetPersonRequest(groupId, personId);
        RequestOperation<GetPersonRequest, GetPersonResponse> operation =
                new GetPerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Remove a person from a group
     *
     * <p>Removes this membership and its group review history. Keeps the workspace person, source evidence
     * and completed investigations. Needs delete access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public RemoveGroupPersonRequestBuilder removePerson() {
        return new RemoveGroupPersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove a person from a group
     *
     * <p>Removes this membership and its group review history. Keeps the workspace person, source evidence
     * and completed investigations. Needs delete access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveGroupPersonResponse removePerson(@Nonnull String groupId, @Nonnull String personId) {
        return removePerson(groupId, personId, null);
    }

    /**
     * Remove a person from a group
     *
     * <p>Removes this membership and its group review history. Keeps the workspace person, source evidence
     * and completed investigations. Needs delete access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveGroupPersonResponse removePerson(
            @Nonnull String groupId, @Nonnull String personId, @Nullable Options options) {
        RemoveGroupPersonRequest request = new RemoveGroupPersonRequest(groupId, personId);
        RequestOperation<RemoveGroupPersonRequest, RemoveGroupPersonResponse> operation =
                new RemoveGroupPerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record a decision on a person.
     *
     * <p>Marks the person cleared, watch or escalated with a note. Decisions are kept in order; the latest is
     * the person's. A decision changes nothing else: no screening reads it.
     *
     * <p>Deprecated: decisions are leaving people and groups. The console no longer records them and no
     * screening reads them. This route is removed in the next contract release; stop calling it.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
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
     * the person's. A decision changes nothing else: no screening reads it.
     *
     * <p>Deprecated: decisions are leaving people and groups. The console no longer records them and no
     * screening reads them. This route is removed in the next contract release; stop calling it.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param body Records a decision on a person.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ReviewPersonResponse reviewPerson(
            @Nonnull String groupId, @Nonnull String personId, @Nonnull PersonReviewRequest body) {
        ReviewPersonRequest request = new ReviewPersonRequest(groupId, personId, body);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param personId
     * @param body A reviewer's verdict on whether one of a person's lookup records is theirs.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeRecordResponse judgeRecord(
            @Nonnull String groupId, @Nonnull String personId, @Nonnull RecordVerdictRequest body) {
        return judgeRecord(groupId, personId, body, null);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param personId
     * @param body A reviewer's verdict on whether one of a person's lookup records is theirs.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeRecordResponse judgeRecord(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull RecordVerdictRequest body,
            @Nullable Options options) {
        JudgeRecordRequest request = new JudgeRecordRequest(groupId, personId, body);
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
     * registrable domain or a host under one; any other domain answers 400. After a workspace person's own
     * investigation or a group's screening, while the environment's enrichment mode, or an investigation's
     * override, is automatic, enrichment also starts searches by itself, with requested_by enrichment, on
     * the distinctive values of the enrichment kinds (usernames by default; email addresses and phone
     * numbers when named) of records more likely than min_likelihood (0.5 by default) to be the person's:
     * each value at most once per person (once more if that search failed), at most 5 searches from one
     * search's records and 20 per person, at most 6 searches deep, within max_spend and the deadline when
     * set, and within the screening cap; at the screening cap it waits and tries again later. A record a
     * search finds starts from the likelihood of the record it came from, divided by one plus the results
     * the search found on that site, or anywhere for a name search, so a chain grows less likely with each
     * search and stops.
     *
     * <p>A group takes mode suggest while the workspace has not set a mode. In suggest mode enrichment starts
     * none by itself, and the person report lists what it would start in suggested_searches. The search
     * runs the breach search and the account and phone lookups that take the value's kind, under the
     * environment's source controls, and finishes seconds later.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * registrable domain or a host under one; any other domain answers 400. After a workspace person's own
     * investigation or a group's screening, while the environment's enrichment mode, or an investigation's
     * override, is automatic, enrichment also starts searches by itself, with requested_by enrichment, on
     * the distinctive values of the enrichment kinds (usernames by default; email addresses and phone
     * numbers when named) of records more likely than min_likelihood (0.5 by default) to be the person's:
     * each value at most once per person (once more if that search failed), at most 5 searches from one
     * search's records and 20 per person, at most 6 searches deep, within max_spend and the deadline when
     * set, and within the screening cap; at the screening cap it waits and tries again later. A record a
     * search finds starts from the likelihood of the record it came from, divided by one plus the results
     * the search found on that site, or anywhere for a name search, so a chain grows less likely with each
     * search and stops.
     *
     * <p>A group takes mode suggest while the workspace has not set a mode. In suggest mode enrichment starts
     * none by itself, and the person report lists what it would start in suggested_searches. The search
     * runs the breach search and the account and phone lookups that take the value's kind, under the
     * environment's source controls, and finishes seconds later.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param personId
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartSearchResponse startSearch(
            @Nonnull String groupId, @Nonnull String personId, @Nonnull SearchRequest body) {
        return startSearch(groupId, personId, null, body);
    }

    /**
     * Search again on a value of one of a person's records.
     *
     * <p>Starts a new search on the value of one field of one of the person's records: a lookup's, a
     * profile's or one an earlier search found. Name the record and the field; the value itself is not
     * sent. The field's kind must be email, phone, username, person_name, password, hash, ip or domain.
     *
     * <p>A domain search finds the breach rows of every email address at the domain, which must be a
     * registrable domain or a host under one; any other domain answers 400. After a workspace person's own
     * investigation or a group's screening, while the environment's enrichment mode, or an investigation's
     * override, is automatic, enrichment also starts searches by itself, with requested_by enrichment, on
     * the distinctive values of the enrichment kinds (usernames by default; email addresses and phone
     * numbers when named) of records more likely than min_likelihood (0.5 by default) to be the person's:
     * each value at most once per person (once more if that search failed), at most 5 searches from one
     * search's records and 20 per person, at most 6 searches deep, within max_spend and the deadline when
     * set, and within the screening cap; at the screening cap it waits and tries again later. A record a
     * search finds starts from the likelihood of the record it came from, divided by one plus the results
     * the search found on that site, or anywhere for a name search, so a chain grows less likely with each
     * search and stops.
     *
     * <p>A group takes mode suggest while the workspace has not set a mode. In suggest mode enrichment starts
     * none by itself, and the person report lists what it would start in suggested_searches. The search
     * runs the breach search and the account and phone lookups that take the value's kind, under the
     * environment's source controls, and finishes seconds later.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param personId
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartSearchResponse startSearch(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nullable String idempotencyKey,
            @Nonnull SearchRequest body) {
        StartSearchRequest request = new StartSearchRequest(groupId, personId, idempotencyKey, body);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
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
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param searchId
     * @param body An empty object.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RunSearchResponse runSearch(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull String searchId,
            @Nonnull ScreeningCancelRequest body) {
        return runSearch(groupId, personId, searchId, body, null);
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
     * accepted for existing integrations. The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param searchId
     * @param body An empty object.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RunSearchResponse runSearch(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull String searchId,
            @Nonnull ScreeningCancelRequest body,
            @Nullable Options options) {
        RunSearchRequest request = new RunSearchRequest(groupId, personId, searchId, body);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListScreeningsResponse listScreenings(@Nonnull String groupId) {
        return listScreenings(groupId, null, null, null);
    }

    /**
     * List a group's screenings.
     *
     * <p>Screenings, newest first. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param limit At most this many items.
     * @param before Read screenings older than this one.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListScreeningsResponse listScreenings(
            @Nonnull String groupId, @Nullable Long limit, @Nullable String before, @Nullable Options options) {
        ListScreeningsRequest request = new ListScreeningsRequest(groupId, limit, before);
        RequestOperation<ListScreeningsRequest, ListScreeningsResponse> operation =
                new ListScreenings.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Start a screening.
     *
     * <p>Queues a screening that reruns the full search for every person in the group. One screening of a
     * group runs at a time; it finishes completed, partial (some people not covered, counted in
     * people_failed) or failed, and fails with error_code expired after 24 hours. Idempotency-Key is
     * required and binds the body for the screening's life.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @return The call builder
     */
    public StartScreeningRequestBuilder startScreening() {
        return new StartScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Start a screening.
     *
     * <p>Queues a screening that reruns the full search for every person in the group. One screening of a
     * group runs at a time; it finishes completed, partial (some people not covered, counted in
     * people_failed) or failed, and fails with error_code expired after 24 hours. Idempotency-Key is
     * required and binds the body for the screening's life.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Screens the group's people no screening has reached.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartScreeningResponse startScreening(
            @Nonnull String groupId, @Nonnull String idempotencyKey, @Nonnull ScreeningRequest body) {
        return startScreening(groupId, idempotencyKey, body, null);
    }

    /**
     * Start a screening.
     *
     * <p>Queues a screening that reruns the full search for every person in the group. One screening of a
     * group runs at a time; it finishes completed, partial (some people not covered, counted in
     * people_failed) or failed, and fails with error_code expired after 24 hours. Idempotency-Key is
     * required and binds the body for the screening's life.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Screens the group's people no screening has reached.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public StartScreeningResponse startScreening(
            @Nonnull String groupId,
            @Nonnull String idempotencyKey,
            @Nonnull ScreeningRequest body,
            @Nullable Options options) {
        StartScreeningRequest request = new StartScreeningRequest(groupId, idempotencyKey, body);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param screeningId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(@Nonnull String groupId, @Nonnull String screeningId) {
        return getScreening(groupId, screeningId, null);
    }

    /**
     * Read a screening.
     *
     * <p>The screening's status and progress. The people scope grants this operation. Explicit evidence.read
     * restrictions override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param screeningId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(
            @Nonnull String groupId, @Nonnull String screeningId, @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(groupId, screeningId);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param screeningId
     * @param body An empty object.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelScreeningResponse cancelScreening(
            @Nonnull String groupId, @Nonnull String screeningId, @Nonnull ScreeningCancelRequest body) {
        return cancelScreening(groupId, screeningId, body, null);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param screeningId
     * @param body An empty object.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelScreeningResponse cancelScreening(
            @Nonnull String groupId,
            @Nonnull String screeningId,
            @Nonnull ScreeningCancelRequest body,
            @Nullable Options options) {
        CancelScreeningRequest request = new CancelScreeningRequest(groupId, screeningId, body);
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
     * <p>The API key selects the workspace and environment.
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
     * <p>The API key selects the workspace and environment.
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
     * <p>The API key selects the workspace and environment.
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
