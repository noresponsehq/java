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
import org.noresponse.models.operations.ExportPeopleRequest;
import org.noresponse.models.operations.GetGroupRequest;
import org.noresponse.models.operations.GetPersonRequest;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.operations.JudgeRecordRequest;
import org.noresponse.models.operations.ListGroupsRequest;
import org.noresponse.models.operations.ListPeopleRequest;
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.RemoveGroupPersonRequest;
import org.noresponse.models.operations.ReviewPersonRequest;
import org.noresponse.models.operations.RunSearchRequest;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.StartSearchRequest;
import org.noresponse.models.operations.UpdateGroupRequest;
import org.noresponse.models.operations.async.AddPeopleRequestBuilder;
import org.noresponse.models.operations.async.AddPeopleResponse;
import org.noresponse.models.operations.async.CancelScreeningRequestBuilder;
import org.noresponse.models.operations.async.CancelScreeningResponse;
import org.noresponse.models.operations.async.CreateGroupRequestBuilder;
import org.noresponse.models.operations.async.CreateGroupResponse;
import org.noresponse.models.operations.async.DeleteGroupRequestBuilder;
import org.noresponse.models.operations.async.DeleteGroupResponse;
import org.noresponse.models.operations.async.ExportPeopleRequestBuilder;
import org.noresponse.models.operations.async.ExportPeopleResponse;
import org.noresponse.models.operations.async.GetGroupRequestBuilder;
import org.noresponse.models.operations.async.GetGroupResponse;
import org.noresponse.models.operations.async.GetPersonRequestBuilder;
import org.noresponse.models.operations.async.GetPersonResponse;
import org.noresponse.models.operations.async.GetScreeningRequestBuilder;
import org.noresponse.models.operations.async.GetScreeningResponse;
import org.noresponse.models.operations.async.JudgeRecordRequestBuilder;
import org.noresponse.models.operations.async.JudgeRecordResponse;
import org.noresponse.models.operations.async.ListGroupsRequestBuilder;
import org.noresponse.models.operations.async.ListGroupsResponse;
import org.noresponse.models.operations.async.ListPeopleRequestBuilder;
import org.noresponse.models.operations.async.ListPeopleResponse;
import org.noresponse.models.operations.async.ListScreeningsRequestBuilder;
import org.noresponse.models.operations.async.ListScreeningsResponse;
import org.noresponse.models.operations.async.RemoveGroupPersonRequestBuilder;
import org.noresponse.models.operations.async.RemoveGroupPersonResponse;
import org.noresponse.models.operations.async.ReviewPersonRequestBuilder;
import org.noresponse.models.operations.async.ReviewPersonResponse;
import org.noresponse.models.operations.async.RunSearchRequestBuilder;
import org.noresponse.models.operations.async.RunSearchResponse;
import org.noresponse.models.operations.async.StartScreeningRequestBuilder;
import org.noresponse.models.operations.async.StartScreeningResponse;
import org.noresponse.models.operations.async.StartSearchRequestBuilder;
import org.noresponse.models.operations.async.StartSearchResponse;
import org.noresponse.models.operations.async.UpdateGroupRequestBuilder;
import org.noresponse.models.operations.async.UpdateGroupResponse;
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
     * <p>Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions
     * override default evidence access.
     *
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<ListGroupsResponse>} - The async response
     */
    public CompletableFuture<ListGroupsResponse> listDirect() {
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
     * @return {@code CompletableFuture<ListGroupsResponse>} - The async response
     */
    public CompletableFuture<ListGroupsResponse> list(
            @Nullable Long limit, @Nullable String before, @Nullable Options options) {
        ListGroupsRequest request = new ListGroupsRequest(limit, before);
        AsyncRequestOperation<ListGroupsRequest, ListGroupsResponse> operation =
                new ListGroups.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CreateGroupResponse>} - The async response
     */
    public CompletableFuture<CreateGroupResponse> create(@Nonnull GroupRequest body) {
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
     * @return {@code CompletableFuture<CreateGroupResponse>} - The async response
     */
    public CompletableFuture<CreateGroupResponse> create(@Nullable String idempotencyKey, @Nonnull GroupRequest body) {
        CreateGroupRequest request = new CreateGroupRequest(idempotencyKey, body);
        AsyncRequestOperation<CreateGroupRequest, CreateGroupResponse> operation =
                new CreateGroup.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetGroupResponse>} - The async response
     */
    public CompletableFuture<GetGroupResponse> get(@Nonnull String groupId) {
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
     * @return {@code CompletableFuture<GetGroupResponse>} - The async response
     */
    public CompletableFuture<GetGroupResponse> get(@Nonnull String groupId, @Nullable Options options) {
        GetGroupRequest request = new GetGroupRequest(groupId);
        AsyncRequestOperation<GetGroupRequest, GetGroupResponse> operation =
                new GetGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<UpdateGroupResponse>} - The async response
     */
    public CompletableFuture<UpdateGroupResponse> update(@Nonnull String groupId, @Nonnull GroupUpdate body) {
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
     * @return {@code CompletableFuture<UpdateGroupResponse>} - The async response
     */
    public CompletableFuture<UpdateGroupResponse> update(
            @Nonnull String groupId, @Nonnull GroupUpdate body, @Nullable Options options) {
        UpdateGroupRequest request = new UpdateGroupRequest(groupId, body);
        AsyncRequestOperation<UpdateGroupRequest, UpdateGroupResponse> operation =
                new UpdateGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<DeleteGroupResponse>} - The async response
     */
    public CompletableFuture<DeleteGroupResponse> delete(@Nonnull String groupId) {
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
     * @return {@code CompletableFuture<DeleteGroupResponse>} - The async response
     */
    public CompletableFuture<DeleteGroupResponse> delete(@Nonnull String groupId, @Nullable Options options) {
        DeleteGroupRequest request = new DeleteGroupRequest(groupId);
        AsyncRequestOperation<DeleteGroupRequest, DeleteGroupResponse> operation =
                new DeleteGroup.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<ListPeopleResponse>} - The async response
     */
    public CompletableFuture<ListPeopleResponse> listPeople(@Nonnull ListPeopleRequest request) {
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
     * <p>Adds 1 to 500 workspace people to this group, at most 512 KiB. An explicit person_id or external_id
     * reuses the named workspace person. A reference-only row keeps submitted details; an identity
     * supplied with that reference replaces those details.
     *
     * <p>A shared email or phone only suggests matches and does not merge people. A changed identity clears
     * current group screening summaries; prior investigation history remains available. Identities are
     * encrypted under the workspace key.
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
     * @return The async call builder
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
     * <p>A shared email or phone only suggests matches and does not merge people. A changed identity clears
     * current group screening summaries; prior investigation history remains available. Identities are
     * encrypted under the workspace key.
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
     * @return {@code CompletableFuture<AddPeopleResponse>} - The async response
     */
    public CompletableFuture<AddPeopleResponse> addPeople(
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
     * <p>A shared email or phone only suggests matches and does not merge people. A changed identity clears
     * current group screening summaries; prior investigation history remains available. Identities are
     * encrypted under the workspace key.
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
     * @return {@code CompletableFuture<AddPeopleResponse>} - The async response
     */
    public CompletableFuture<AddPeopleResponse> addPeople(
            @Nonnull String groupId,
            @Nonnull String idempotencyKey,
            @Nonnull PersonBatchRequest body,
            @Nullable Options options) {
        AddPeopleRequest request = new AddPeopleRequest(groupId, idempotencyKey, body);
        AsyncRequestOperation<AddPeopleRequest, AddPeopleResponse> operation =
                new AddPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetPersonResponse>} - The async response
     */
    public CompletableFuture<GetPersonResponse> getPerson(@Nonnull String groupId, @Nonnull String personId) {
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
     * @return {@code CompletableFuture<GetPersonResponse>} - The async response
     */
    public CompletableFuture<GetPersonResponse> getPerson(
            @Nonnull String groupId, @Nonnull String personId, @Nullable Options options) {
        GetPersonRequest request = new GetPersonRequest(groupId, personId);
        AsyncRequestOperation<GetPersonRequest, GetPersonResponse> operation =
                new GetPerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Remove a person from a group
     *
     * <p>Removes this membership and its group review history. Keeps the workspace person, source evidence
     * and completed investigations. Needs delete access.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<RemoveGroupPersonResponse>} - The async response
     */
    public CompletableFuture<RemoveGroupPersonResponse> removePerson(
            @Nonnull String groupId, @Nonnull String personId) {
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
     * @return {@code CompletableFuture<RemoveGroupPersonResponse>} - The async response
     */
    public CompletableFuture<RemoveGroupPersonResponse> removePerson(
            @Nonnull String groupId, @Nonnull String personId, @Nullable Options options) {
        RemoveGroupPersonRequest request = new RemoveGroupPersonRequest(groupId, personId);
        AsyncRequestOperation<RemoveGroupPersonRequest, RemoveGroupPersonResponse> operation =
                new RemoveGroupPerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * <p>The API key selects the workspace and environment.
     *
     * @return The async call builder
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
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param body Records a decision on a person.
     * @return {@code CompletableFuture<ReviewPersonResponse>} - The async response
     */
    public CompletableFuture<ReviewPersonResponse> reviewPerson(
            @Nonnull String groupId, @Nonnull String personId, @Nonnull PersonReviewRequest body) {
        ReviewPersonRequest request = new ReviewPersonRequest(groupId, personId, body);
        AsyncRequestOperation<ReviewPersonRequest, ReviewPersonResponse> operation =
                new ReviewPerson.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<JudgeRecordResponse>} - The async response
     */
    public CompletableFuture<JudgeRecordResponse> judgeRecord(
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
     * @return {@code CompletableFuture<JudgeRecordResponse>} - The async response
     */
    public CompletableFuture<JudgeRecordResponse> judgeRecord(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull RecordVerdictRequest body,
            @Nullable Options options) {
        JudgeRecordRequest request = new JudgeRecordRequest(groupId, personId, body);
        AsyncRequestOperation<JudgeRecordRequest, JudgeRecordResponse> operation =
                new JudgeRecord.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * investigation, enrichment also starts searches by itself, with requested_by enrichment, on the
     * distinctive usernames of records more than 0.5 likely to be the person's: each value at most once
     * per person (once more if that search failed), at most 5 searches from one search's records and 20
     * per person, at most 6 searches deep, within the screening cap; at the cap it waits and tries again
     * later. A record a search finds starts from the likelihood of the record it came from, divided by one
     * plus the results the search found on that site, so a chain grows less likely with each search and
     * stops.
     *
     * <p>Group screening only suggests. The search runs the breach search and the account and phone lookups
     * that take the value's kind, under the environment's source controls, and finishes seconds later. A
     * password or hash search counts first: it stops at counted with rows, how many breach rows hold the
     * value, and runs only when a person runs it.
     *
     * <p>Name a table, as a lookup remaining entry names it, to fetch the rows an earlier search left out, up
     * to 100. What the search finds joins the person's lookup records as leads, each match naming the
     * search; the person report lists the searches, each with the value and the record and field it came
     * from, and its records are scored like the rest. Without an Idempotency-Key, starting the same open
     * search returns it.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap, and one the cap cannot hold
     * answers 409 screening_cap_exceeded. A record or field the person does not hold answers 404. Each
     * search is audited as person.search_started.
     *
     * <p>Send an Idempotency-Key to retry safely: the same key and request return the same resource in its
     * current state while it is retained, even after work completes. Reusing the key with different input
     * answers 409 idempotency_key_reused. Use a new key for a deliberate new operation.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The async call builder
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
     * investigation, enrichment also starts searches by itself, with requested_by enrichment, on the
     * distinctive usernames of records more than 0.5 likely to be the person's: each value at most once
     * per person (once more if that search failed), at most 5 searches from one search's records and 20
     * per person, at most 6 searches deep, within the screening cap; at the cap it waits and tries again
     * later. A record a search finds starts from the likelihood of the record it came from, divided by one
     * plus the results the search found on that site, so a chain grows less likely with each search and
     * stops.
     *
     * <p>Group screening only suggests. The search runs the breach search and the account and phone lookups
     * that take the value's kind, under the environment's source controls, and finishes seconds later. A
     * password or hash search counts first: it stops at counted with rows, how many breach rows hold the
     * value, and runs only when a person runs it.
     *
     * <p>Name a table, as a lookup remaining entry names it, to fetch the rows an earlier search left out, up
     * to 100. What the search finds joins the person's lookup records as leads, each match naming the
     * search; the person report lists the searches, each with the value and the record and field it came
     * from, and its records are scored like the rest. Without an Idempotency-Key, starting the same open
     * search returns it.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap, and one the cap cannot hold
     * answers 409 screening_cap_exceeded. A record or field the person does not hold answers 404. Each
     * search is audited as person.search_started.
     *
     * <p>Send an Idempotency-Key to retry safely: the same key and request return the same resource in its
     * current state while it is retained, even after work completes. Reusing the key with different input
     * answers 409 idempotency_key_reused. Use a new key for a deliberate new operation.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return {@code CompletableFuture<StartSearchResponse>} - The async response
     */
    public CompletableFuture<StartSearchResponse> startSearch(
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
     * investigation, enrichment also starts searches by itself, with requested_by enrichment, on the
     * distinctive usernames of records more than 0.5 likely to be the person's: each value at most once
     * per person (once more if that search failed), at most 5 searches from one search's records and 20
     * per person, at most 6 searches deep, within the screening cap; at the cap it waits and tries again
     * later. A record a search finds starts from the likelihood of the record it came from, divided by one
     * plus the results the search found on that site, so a chain grows less likely with each search and
     * stops.
     *
     * <p>Group screening only suggests. The search runs the breach search and the account and phone lookups
     * that take the value's kind, under the environment's source controls, and finishes seconds later. A
     * password or hash search counts first: it stops at counted with rows, how many breach rows hold the
     * value, and runs only when a person runs it.
     *
     * <p>Name a table, as a lookup remaining entry names it, to fetch the rows an earlier search left out, up
     * to 100. What the search finds joins the person's lookup records as leads, each match naming the
     * search; the person report lists the searches, each with the value and the record and field it came
     * from, and its records are scored like the rest. Without an Idempotency-Key, starting the same open
     * search returns it.
     *
     * <p>Its estimate counts against the environment's screening_daily_cap, and one the cap cannot hold
     * answers 409 screening_cap_exceeded. A record or field the person does not hold answers 404. Each
     * search is audited as person.search_started.
     *
     * <p>Send an Idempotency-Key to retry safely: the same key and request return the same resource in its
     * current state while it is retained, even after work completes. Reusing the key with different input
     * answers 409 idempotency_key_reused. Use a new key for a deliberate new operation.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param groupId
     * @param personId
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return {@code CompletableFuture<StartSearchResponse>} - The async response
     */
    public CompletableFuture<StartSearchResponse> startSearch(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nullable String idempotencyKey,
            @Nonnull SearchRequest body) {
        StartSearchRequest request = new StartSearchRequest(groupId, personId, idempotencyKey, body);
        AsyncRequestOperation<StartSearchRequest, StartSearchResponse> operation =
                new StartSearch.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<RunSearchResponse>} - The async response
     */
    public CompletableFuture<RunSearchResponse> runSearch(
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
     * @return {@code CompletableFuture<RunSearchResponse>} - The async response
     */
    public CompletableFuture<RunSearchResponse> runSearch(
            @Nonnull String groupId,
            @Nonnull String personId,
            @Nonnull String searchId,
            @Nonnull ScreeningCancelRequest body,
            @Nullable Options options) {
        RunSearchRequest request = new RunSearchRequest(groupId, personId, searchId, body);
        AsyncRequestOperation<RunSearchRequest, RunSearchResponse> operation =
                new RunSearch.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<ListScreeningsResponse>} - The async response
     */
    public CompletableFuture<ListScreeningsResponse> listScreenings(@Nonnull String groupId) {
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
     * @return {@code CompletableFuture<ListScreeningsResponse>} - The async response
     */
    public CompletableFuture<ListScreeningsResponse> listScreenings(
            @Nonnull String groupId, @Nullable Long limit, @Nullable String before, @Nullable Options options) {
        ListScreeningsRequest request = new ListScreeningsRequest(groupId, limit, before);
        AsyncRequestOperation<ListScreeningsRequest, ListScreeningsResponse> operation =
                new ListScreenings.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @return The async call builder
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Screens the group's people no screening has reached and everyone on watch or escalated.
     * @return {@code CompletableFuture<StartScreeningResponse>} - The async response
     */
    public CompletableFuture<StartScreeningResponse> startScreening(
            @Nonnull String groupId, @Nonnull String idempotencyKey, @Nonnull ScreeningRequest body) {
        return startScreening(groupId, idempotencyKey, body, null);
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
     * <p>The groups scope is also accepted for existing integrations. The API key selects the workspace and
     * environment.
     *
     * @param groupId
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.
     * @param body Screens the group's people no screening has reached and everyone on watch or escalated.
     * @param options additional options
     * @return {@code CompletableFuture<StartScreeningResponse>} - The async response
     */
    public CompletableFuture<StartScreeningResponse> startScreening(
            @Nonnull String groupId,
            @Nonnull String idempotencyKey,
            @Nonnull ScreeningRequest body,
            @Nullable Options options) {
        StartScreeningRequest request = new StartScreeningRequest(groupId, idempotencyKey, body);
        AsyncRequestOperation<StartScreeningRequest, StartScreeningResponse> operation =
                new StartScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(@Nonnull String groupId, @Nonnull String screeningId) {
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
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(
            @Nonnull String groupId, @Nonnull String screeningId, @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(groupId, screeningId);
        AsyncRequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CancelScreeningResponse>} - The async response
     */
    public CompletableFuture<CancelScreeningResponse> cancelScreening(
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
     * @return {@code CompletableFuture<CancelScreeningResponse>} - The async response
     */
    public CompletableFuture<CancelScreeningResponse> cancelScreening(
            @Nonnull String groupId,
            @Nonnull String screeningId,
            @Nonnull ScreeningCancelRequest body,
            @Nullable Options options) {
        CancelScreeningRequest request = new CancelScreeningRequest(groupId, screeningId, body);
        AsyncRequestOperation<CancelScreeningRequest, CancelScreeningResponse> operation =
                new CancelScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<ExportPeopleResponse>} - The async response
     */
    public CompletableFuture<ExportPeopleResponse> exportPeople(@Nonnull ExportPeopleRequest request) {
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
     * @return {@code CompletableFuture<ExportPeopleResponse>} - The async response
     */
    public CompletableFuture<ExportPeopleResponse> exportPeople(
            @Nonnull ExportPeopleRequest request, @Nullable Options options) {
        AsyncRequestOperation<ExportPeopleRequest, ExportPeopleResponse> operation =
                new ExportPeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
