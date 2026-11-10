package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.AddAttendeesRequest;
import org.noresponse.models.operations.AddAttendeesRequestBuilder;
import org.noresponse.models.operations.AddAttendeesResponse;
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.models.operations.CancelScreeningRequestBuilder;
import org.noresponse.models.operations.CancelScreeningResponse;
import org.noresponse.models.operations.CreateEventRequest;
import org.noresponse.models.operations.CreateEventRequestBuilder;
import org.noresponse.models.operations.CreateEventResponse;
import org.noresponse.models.operations.DeleteEventRequest;
import org.noresponse.models.operations.DeleteEventRequestBuilder;
import org.noresponse.models.operations.DeleteEventResponse;
import org.noresponse.models.operations.GetAttendeeRequest;
import org.noresponse.models.operations.GetAttendeeRequestBuilder;
import org.noresponse.models.operations.GetAttendeeResponse;
import org.noresponse.models.operations.GetEventRequest;
import org.noresponse.models.operations.GetEventRequestBuilder;
import org.noresponse.models.operations.GetEventResponse;
import org.noresponse.models.operations.GetScreeningRequest;
import org.noresponse.models.operations.GetScreeningRequestBuilder;
import org.noresponse.models.operations.GetScreeningResponse;
import org.noresponse.models.operations.ListAttendeesRequest;
import org.noresponse.models.operations.ListAttendeesRequestBuilder;
import org.noresponse.models.operations.ListAttendeesResponse;
import org.noresponse.models.operations.ListEventsRequest;
import org.noresponse.models.operations.ListEventsRequestBuilder;
import org.noresponse.models.operations.ListEventsResponse;
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.ListScreeningsRequestBuilder;
import org.noresponse.models.operations.ListScreeningsResponse;
import org.noresponse.models.operations.ReviewAttendeeRequest;
import org.noresponse.models.operations.ReviewAttendeeRequestBuilder;
import org.noresponse.models.operations.ReviewAttendeeResponse;
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.StartScreeningRequestBuilder;
import org.noresponse.models.operations.StartScreeningResponse;
import org.noresponse.models.operations.UpdateEventRequest;
import org.noresponse.models.operations.UpdateEventRequestBuilder;
import org.noresponse.models.operations.UpdateEventResponse;
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
public class Events {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncEvents asyncSDK;

    Events(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncEvents(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncEvents async() {
        return asyncSDK;
    }

    /**
     * List an environment's events.
     *
     * <p>List this environment's events, newest first. Pass next_before back as before for the next page.
     * Requires the events scope.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListEventsResponse list(@Nonnull String workspaceId, @Nonnull KeyEnvironment environment) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListEventsResponse list(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nullable Long limit,
            @Nullable String before,
            @Nullable Options options) {
        ListEventsRequest request = new ListEventsRequest(workspaceId, environment, limit, before);
        RequestOperation<ListEventsRequest, ListEventsResponse> operation =
                new ListEvents.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Create an event to screen.
     *
     * <p>Create an event to screen: its name, start, venue, host, the principals it protects and the
     * sensitive topics posts are read against. An environment holds at most 1000 events.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateEventResponse create(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull EventRequest body) {
        CreateEventRequest request = new CreateEventRequest(workspaceId, environment, body);
        RequestOperation<CreateEventRequest, CreateEventResponse> operation =
                new CreateEvent.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read an event.
     *
     * <p>Read an event and how many attendees it holds.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetEventResponse get(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetEventResponse get(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nullable Options options) {
        GetEventRequest request = new GetEventRequest(workspaceId, environment, eventId);
        RequestOperation<GetEventRequest, GetEventResponse> operation =
                new GetEvent.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Change an event.
     *
     * <p>Change the fields you name. Send expected_version to apply the change only to that version.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateEventResponse update(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateEventResponse update(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull EventUpdate body,
            @Nullable Options options) {
        UpdateEventRequest request = new UpdateEventRequest(workspaceId, environment, eventId, body);
        RequestOperation<UpdateEventRequest, UpdateEventResponse> operation =
                new UpdateEvent.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Erase an event.
     *
     * <p>Erase an event with its attendees, their decisions and its screenings, and stop any screening still
     * running.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteEventResponse delete(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteEventResponse delete(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nullable Options options) {
        DeleteEventRequest request = new DeleteEventRequest(workspaceId, environment, eventId);
        RequestOperation<DeleteEventRequest, DeleteEventResponse> operation =
                new DeleteEvent.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List an event's attendees.
     *
     * <p>Read an event's roster in upload order, with each attendee's match state, highest finding band and
     * decision. Filter by band or decision; pass next_after back as after.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAttendeesResponse listAttendees(@Nonnull ListAttendeesRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListAttendeesResponse listAttendees(@Nonnull ListAttendeesRequest request, @Nullable Options options) {
        RequestOperation<ListAttendeesRequest, ListAttendeesResponse> operation =
                new ListAttendees.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Add or update a batch of attendees.
     *
     * <p>Add or update 1 to 500 attendees with an Idempotency-Key. A row with the same external_id, else
     * email, else phone as an existing attendee updates it. Identities are stored encrypted.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddAttendeesResponse addAttendees(@Nonnull AddAttendeesRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public AddAttendeesResponse addAttendees(@Nonnull AddAttendeesRequest request, @Nullable Options options) {
        RequestOperation<AddAttendeesRequest, AddAttendeesResponse> operation =
                new AddAttendees.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read an attendee's report.
     *
     * <p>Read an attendee's report: matched profiles, findings with their evidence and every recorded
     * decision.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAttendeeResponse getAttendee(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetAttendeeResponse getAttendee(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String attendeeId,
            @Nullable Options options) {
        GetAttendeeRequest request = new GetAttendeeRequest(workspaceId, environment, eventId, attendeeId);
        RequestOperation<GetAttendeeRequest, GetAttendeeResponse> operation =
                new GetAttendee.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Record a decision on an attendee.
     *
     * <p>Record a cleared, watch or escalated decision with a note. The latest decision is the attendee's;
     * the next screening refreshes everyone on watch or escalated.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ReviewAttendeeResponse reviewAttendee(@Nonnull ReviewAttendeeRequest request) {
        RequestOperation<ReviewAttendeeRequest, ReviewAttendeeResponse> operation =
                new ReviewAttendee.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List an event's screenings.
     *
     * <p>List an event's screenings, newest first.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListScreeningsResponse listScreenings(@Nonnull ListScreeningsRequest request) {
        return listScreenings(request, null);
    }

    /**
     * List an event's screenings.
     *
     * <p>List an event's screenings, newest first.
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
     * <p>Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It
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
     * <p>Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It
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
     * @param eventId
     * @param screeningId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetScreeningResponse getScreening(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String eventId,
            @Nonnull String screeningId,
            @Nullable Options options) {
        GetScreeningRequest request = new GetScreeningRequest(workspaceId, environment, eventId, screeningId);
        RequestOperation<GetScreeningRequest, GetScreeningResponse> operation =
                new GetScreening.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. Attendees already screened keep their results.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CancelScreeningResponse cancelScreening(@Nonnull CancelScreeningRequest request) {
        return cancelScreening(request, null);
    }

    /**
     * Cancel a screening.
     *
     * <p>Stop a queued or running screening. Attendees already screened keep their results.
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
