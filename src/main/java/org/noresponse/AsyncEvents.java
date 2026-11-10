package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.AddAttendeesRequest;
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.models.operations.CreateEventRequest;
import org.noresponse.models.operations.DeleteEventRequest;
import org.noresponse.models.operations.GetAttendeeRequest;
import org.noresponse.models.operations.GetEventRequest;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.operations.ListAttendeesRequest;
import org.noresponse.models.operations.ListEventsRequest;
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.ReviewAttendeeRequest;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.UpdateEventRequest;
import org.noresponse.models.operations.async.AddAttendeesRequestBuilder;
import org.noresponse.models.operations.async.AddAttendeesResponse;
import org.noresponse.models.operations.async.CancelScreeningRequestBuilder;
import org.noresponse.models.operations.async.CancelScreeningResponse;
import org.noresponse.models.operations.async.CreateEventRequestBuilder;
import org.noresponse.models.operations.async.CreateEventResponse;
import org.noresponse.models.operations.async.DeleteEventRequestBuilder;
import org.noresponse.models.operations.async.DeleteEventResponse;
import org.noresponse.models.operations.async.GetAttendeeRequestBuilder;
import org.noresponse.models.operations.async.GetAttendeeResponse;
import org.noresponse.models.operations.async.GetEventRequestBuilder;
import org.noresponse.models.operations.async.GetEventResponse;
import org.noresponse.models.operations.async.GetScreeningRequestBuilder;
import org.noresponse.models.operations.async.GetScreeningResponse;
import org.noresponse.models.operations.async.ListAttendeesRequestBuilder;
import org.noresponse.models.operations.async.ListAttendeesResponse;
import org.noresponse.models.operations.async.ListEventsRequestBuilder;
import org.noresponse.models.operations.async.ListEventsResponse;
import org.noresponse.models.operations.async.ListScreeningsRequestBuilder;
import org.noresponse.models.operations.async.ListScreeningsResponse;
import org.noresponse.models.operations.async.ReviewAttendeeRequestBuilder;
import org.noresponse.models.operations.async.ReviewAttendeeResponse;
import org.noresponse.models.operations.async.StartScreeningRequestBuilder;
import org.noresponse.models.operations.async.StartScreeningResponse;
import org.noresponse.models.operations.async.UpdateEventRequestBuilder;
import org.noresponse.models.operations.async.UpdateEventResponse;
import org.noresponse.models.shared.EventRequest;
import org.noresponse.models.shared.EventUpdate;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.operations.AddAttendees;
import org.noresponse.operations.CancelScreening;
import org.noresponse.operations.CreateEvent;
import org.noresponse.operations.DeleteEvent;
import org.noresponse.operations.GetAttendee;
import org.noresponse.operations.GetEvent;
import org.noresponse.operations.GetScreening;
import org.noresponse.operations.ListAttendees;
import org.noresponse.operations.ListEvents;
import org.noresponse.operations.ListScreenings;
import org.noresponse.operations.ReviewAttendee;
import org.noresponse.operations.StartScreening;
import org.noresponse.operations.UpdateEvent;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Events, attendee lists, screenings and reviewer decisions.
 */
public class AsyncEvents {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Events syncSDK;

    AsyncEvents(Events syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Events sync() {
        return syncSDK;
    }

    /**
     * List an environment's events.
     *
     * <p>List this environment's events, newest first. Pass next_before back as before for the next page.
     * Requires the events scope.
     *
     * @return The async call builder
     */
    public ListEventsRequestBuilder list() {
        return new ListEventsRequestBuilder(sdkConfiguration);
    }

    /**
     * List an environment's events.
     *
     * <p>List this environment's events, newest first. Pass next_before back as before for the next page.
     * Requires the events scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @return {@code CompletableFuture<ListEventsResponse>} - The async response
     */
    public CompletableFuture<ListEventsResponse> list(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
        return list(workspaceId, environment, null, null, null);
    }

    /**
     * List an environment's events.
     *
     * <p>List this environment's events, newest first. Pass next_before back as before for the next page.
     * Requires the events scope.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param limit At most this many items.
     * @param before Read events older than this one.
     * @param options additional options
     * @return {@code CompletableFuture<ListEventsResponse>} - The async response
     */
    public CompletableFuture<ListEventsResponse> list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String before,
            @Nullable Options options) {
        ListEventsRequest request = new ListEventsRequest(workspaceId, environment, limit, before);
        AsyncRequestOperation<ListEventsRequest, ListEventsResponse> operation =
                new ListEvents.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Create an event to screen.
     *
     * <p>Create an event to screen: its name, start, venue, host, the principals it protects and the
     * sensitive topics posts are read against. An environment holds at most 1000 events.
     *
     * @return The async call builder
     */
    public CreateEventRequestBuilder create() {
        return new CreateEventRequestBuilder(sdkConfiguration);
    }

    /**
     * Create an event to screen.
     *
     * <p>Create an event to screen: its name, start, venue, host, the principals it protects and the
     * sensitive topics posts are read against. An environment holds at most 1000 events.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param body Creates an event.
     * @return {@code CompletableFuture<CreateEventResponse>} - The async response
     */
    public CompletableFuture<CreateEventResponse> create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull EventRequest body) {
        CreateEventRequest request = new CreateEventRequest(workspaceId, environment, body);
        AsyncRequestOperation<CreateEventRequest, CreateEventResponse> operation =
                new CreateEvent.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read an event.
     *
     * <p>Read an event and how many attendees it holds.
     *
     * @return The async call builder
     */
    public GetEventRequestBuilder get() {
        return new GetEventRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an event.
     *
     * <p>Read an event and how many attendees it holds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @return {@code CompletableFuture<GetEventResponse>} - The async response
     */
    public CompletableFuture<GetEventResponse> get(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String eventId) {
        return get(workspaceId, environment, eventId, null);
    }

    /**
     * Read an event.
     *
     * <p>Read an event and how many attendees it holds.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param options additional options
     * @return {@code CompletableFuture<GetEventResponse>} - The async response
     */
    public CompletableFuture<GetEventResponse> get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nullable Options options) {
        GetEventRequest request = new GetEventRequest(workspaceId, environment, eventId);
        AsyncRequestOperation<GetEventRequest, GetEventResponse> operation =
                new GetEvent.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Change an event.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @return The async call builder
     */
    public UpdateEventRequestBuilder update() {
        return new UpdateEventRequestBuilder(sdkConfiguration);
    }

    /**
     * Change an event.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param body Changes the fields it names; at least one besides expected_version.
     * @return {@code CompletableFuture<UpdateEventResponse>} - The async response
     */
    public CompletableFuture<UpdateEventResponse> update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull EventUpdate body) {
        return update(workspaceId, environment, eventId, body, null);
    }

    /**
     * Change an event.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param body Changes the fields it names; at least one besides expected_version.
     * @param options additional options
     * @return {@code CompletableFuture<UpdateEventResponse>} - The async response
     */
    public CompletableFuture<UpdateEventResponse> update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull EventUpdate body,
            @Nullable Options options) {
        UpdateEventRequest request = new UpdateEventRequest(workspaceId, environment, eventId, body);
        AsyncRequestOperation<UpdateEventRequest, UpdateEventResponse> operation =
                new UpdateEvent.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Erase an event.
     *
     * <p>Erase an event with its attendees, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @return The async call builder
     */
    public DeleteEventRequestBuilder delete() {
        return new DeleteEventRequestBuilder(sdkConfiguration);
    }

    /**
     * Erase an event.
     *
     * <p>Erase an event with its attendees, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @return {@code CompletableFuture<DeleteEventResponse>} - The async response
     */
    public CompletableFuture<DeleteEventResponse> delete(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String eventId) {
        return delete(workspaceId, environment, eventId, null);
    }

    /**
     * Erase an event.
     *
     * <p>Erase an event with its attendees, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param options additional options
     * @return {@code CompletableFuture<DeleteEventResponse>} - The async response
     */
    public CompletableFuture<DeleteEventResponse> delete(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nullable Options options) {
        DeleteEventRequest request = new DeleteEventRequest(workspaceId, environment, eventId);
        AsyncRequestOperation<DeleteEventRequest, DeleteEventResponse> operation =
                new DeleteEvent.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List an event's attendees.
     *
     * <p>Read an event's roster in upload order, with each attendee's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @return The async call builder
     */
    public ListAttendeesRequestBuilder listAttendees() {
        return new ListAttendeesRequestBuilder(sdkConfiguration);
    }

    /**
     * List an event's attendees.
     *
     * <p>Read an event's roster in upload order, with each attendee's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListAttendeesResponse>} - The async response
     */
    public CompletableFuture<ListAttendeesResponse> listAttendees(@Nonnull ListAttendeesRequest request) {
        return listAttendees(request, null);
    }

    /**
     * List an event's attendees.
     *
     * <p>Read an event's roster in upload order, with each attendee's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListAttendeesResponse>} - The async response
     */
    public CompletableFuture<ListAttendeesResponse> listAttendees(
            @Nonnull ListAttendeesRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListAttendeesRequest, ListAttendeesResponse> operation =
                new ListAttendees.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Add or update a batch of attendees.
     *
     * <p>Add or update 1 to 500 attendees with an Idempotency-Key. A row with the same external_id, else
     * email, else phone as an existing attendee updates it. Identities are stored encrypted.
     *
     * @return The async call builder
     */
    public AddAttendeesRequestBuilder addAttendees() {
        return new AddAttendeesRequestBuilder(sdkConfiguration);
    }

    /**
     * Add or update a batch of attendees.
     *
     * <p>Add or update 1 to 500 attendees with an Idempotency-Key. A row with the same external_id, else
     * email, else phone as an existing attendee updates it. Identities are stored encrypted.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<AddAttendeesResponse>} - The async response
     */
    public CompletableFuture<AddAttendeesResponse> addAttendees(@Nonnull AddAttendeesRequest request) {
        return addAttendees(request, null);
    }

    /**
     * Add or update a batch of attendees.
     *
     * <p>Add or update 1 to 500 attendees with an Idempotency-Key. A row with the same external_id, else
     * email, else phone as an existing attendee updates it. Identities are stored encrypted.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<AddAttendeesResponse>} - The async response
     */
    public CompletableFuture<AddAttendeesResponse> addAttendees(
            @Nonnull AddAttendeesRequest request, @Nullable Options options) {
        AsyncRequestOperation<AddAttendeesRequest, AddAttendeesResponse> operation =
                new AddAttendees.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Read an attendee's report.
     *
     * <p>Read an attendee's report: matched profiles, findings with their evidence and every recorded
     * decision.
     *
     * @return The async call builder
     */
    public GetAttendeeRequestBuilder getAttendee() {
        return new GetAttendeeRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an attendee's report.
     *
     * <p>Read an attendee's report: matched profiles, findings with their evidence and every recorded
     * decision.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param attendeeId
     * @return {@code CompletableFuture<GetAttendeeResponse>} - The async response
     */
    public CompletableFuture<GetAttendeeResponse> getAttendee(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String attendeeId) {
        return getAttendee(workspaceId, environment, eventId, attendeeId, null);
    }

    /**
     * Read an attendee's report.
     *
     * <p>Read an attendee's report: matched profiles, findings with their evidence and every recorded
     * decision.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param attendeeId
     * @param options additional options
     * @return {@code CompletableFuture<GetAttendeeResponse>} - The async response
     */
    public CompletableFuture<GetAttendeeResponse> getAttendee(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String attendeeId,
            @Nullable Options options) {
        GetAttendeeRequest request = new GetAttendeeRequest(workspaceId, environment, eventId, attendeeId);
        AsyncRequestOperation<GetAttendeeRequest, GetAttendeeResponse> operation =
                new GetAttendee.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Record a decision on an attendee.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the attendee's;
     * the next screening refreshes everyone on watch or escalated.
     *
     * @return The async call builder
     */
    public ReviewAttendeeRequestBuilder reviewAttendee() {
        return new ReviewAttendeeRequestBuilder(sdkConfiguration);
    }

    /**
     * Record a decision on an attendee.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the attendee's;
     * the next screening refreshes everyone on watch or escalated.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ReviewAttendeeResponse>} - The async response
     */
    public CompletableFuture<ReviewAttendeeResponse> reviewAttendee(@Nonnull ReviewAttendeeRequest request) {
        AsyncRequestOperation<ReviewAttendeeRequest, ReviewAttendeeResponse> operation =
                new ReviewAttendee.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List an event's screenings.
     *
     * <p>List an event's screenings, newest first.
     *
     * @return The async call builder
     */
    public ListScreeningsRequestBuilder listScreenings() {
        return new ListScreeningsRequestBuilder(sdkConfiguration);
    }

    /**
     * List an event's screenings.
     *
     * <p>List an event's screenings, newest first.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListScreeningsResponse>} - The async response
     */
    public CompletableFuture<ListScreeningsResponse> listScreenings(@Nonnull ListScreeningsRequest request) {
        return listScreenings(request, null);
    }

    /**
     * List an event's screenings.
     *
     * <p>List an event's screenings, newest first.
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
     * <p>Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It
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
     * <p>Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It
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
     * <p>Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It
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
     * @param eventId
     * @param screeningId
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String screeningId) {
        return getScreening(workspaceId, environment, eventId, screeningId, null);
    }

    /**
     * Read a screening.
     *
     * <p>Read a screening's status and progress.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param environment The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.
     * @param eventId
     * @param screeningId
     * @param options additional options
     * @return {@code CompletableFuture<GetScreeningResponse>} - The async response
     */
    public CompletableFuture<GetScreeningResponse> getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String screeningId,
            @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(workspaceId, environment, eventId, screeningId);
        AsyncRequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. Attendees already screened keep their results.
     *
     * @return The async call builder
     */
    public CancelScreeningRequestBuilder cancelScreening() {
        return new CancelScreeningRequestBuilder(sdkConfiguration);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. Attendees already screened keep their results.
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
     * <p>Stop a queued or running screening. Attendees already screened keep their results.
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
