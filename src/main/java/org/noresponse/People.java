package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import org.noresponse.models.operations.CreateWorkspacePersonRequest;
import org.noresponse.models.operations.CreateWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.CreateWorkspacePersonResponse;
import org.noresponse.models.operations.DeleteWorkspacePersonRequest;
import org.noresponse.models.operations.DeleteWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.DeleteWorkspacePersonResponse;
import org.noresponse.models.operations.GetWorkspacePersonRequest;
import org.noresponse.models.operations.GetWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.GetWorkspacePersonResponse;
import org.noresponse.models.operations.JudgeWorkspacePersonRecordRequest;
import org.noresponse.models.operations.JudgeWorkspacePersonRecordRequestBuilder;
import org.noresponse.models.operations.JudgeWorkspacePersonRecordResponse;
import org.noresponse.models.operations.ListWorkspacePeopleRequest;
import org.noresponse.models.operations.ListWorkspacePeopleRequestBuilder;
import org.noresponse.models.operations.ListWorkspacePeopleResponse;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.models.shared.WorkspacePersonRequest;
import org.noresponse.operations.CreateWorkspacePerson;
import org.noresponse.operations.DeleteWorkspacePerson;
import org.noresponse.operations.GetWorkspacePerson;
import org.noresponse.operations.JudgeWorkspacePersonRecord;
import org.noresponse.operations.ListWorkspacePeople;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Workspace people, submitted identity, source evidence and expiry.
 */
public class People {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncPeople asyncSDK;

    People(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncPeople(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncPeople async() {
        return asyncSDK;
    }

    /**
     * List workspace people.
     *
     * <p>Lists retained people in the key workspace and environment, including people in no group. Each
     * returned person records a person.viewed audit event. Expired people are excluded before background
     * deletion.
     *
     * @return The call builder
     */
    public ListWorkspacePeopleRequestBuilder list() {
        return new ListWorkspacePeopleRequestBuilder(sdkConfiguration);
    }

    /**
     * List workspace people.
     *
     * <p>Lists retained people in the key workspace and environment, including people in no group. Each
     * returned person records a person.viewed audit event. Expired people are excluded before background
     * deletion.
     *
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListWorkspacePeopleResponse listDirect() {
        return list(null, null, null);
    }

    /**
     * List workspace people.
     *
     * <p>Lists retained people in the key workspace and environment, including people in no group. Each
     * returned person records a person.viewed audit event. Expired people are excluded before background
     * deletion.
     *
     * @param limit
     * @param after Pass next_cursor from the prior page.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListWorkspacePeopleResponse list(@Nullable Long limit, @Nullable String after, @Nullable Options options) {
        ListWorkspacePeopleRequest request = new ListWorkspacePeopleRequest(limit, after);
        RequestOperation<ListWorkspacePeopleRequest, ListWorkspacePeopleResponse> operation =
                new ListWorkspacePeople.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Create or reuse a workspace person.
     *
     * <p>Creates a person without a group. An explicit person_id or external_id reuses the named workspace
     * person. A reference-only request keeps submitted details; identity supplied with that reference
     * replaces those details.
     *
     * <p>A shared email or phone only suggests a match. Suggested matches are returned only with people-read
     * access. The workspace retention setting determines expires_at.
     *
     * <p>An existing person keeps their original expiry. An Idempotency-Key returns the same retained person
     * on retries, without creating another. Changed input with the same key answers 409
     * idempotency_key_reused.
     *
     * <p>A customer reference that names another person or several people answers 409
     * person_reference_conflict.
     *
     * @return The call builder
     */
    public CreateWorkspacePersonRequestBuilder create() {
        return new CreateWorkspacePersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Create or reuse a workspace person.
     *
     * <p>Creates a person without a group. An explicit person_id or external_id reuses the named workspace
     * person. A reference-only request keeps submitted details; identity supplied with that reference
     * replaces those details.
     *
     * <p>A shared email or phone only suggests a match. Suggested matches are returned only with people-read
     * access. The workspace retention setting determines expires_at.
     *
     * <p>An existing person keeps their original expiry. An Idempotency-Key returns the same retained person
     * on retries, without creating another. Changed input with the same key answers 409
     * idempotency_key_reused.
     *
     * <p>A customer reference that names another person or several people answers 409
     * person_reference_conflict.
     *
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateWorkspacePersonResponse create(@Nonnull WorkspacePersonRequest body) {
        return create(null, body);
    }

    /**
     * Create or reuse a workspace person.
     *
     * <p>Creates a person without a group. An explicit person_id or external_id reuses the named workspace
     * person. A reference-only request keeps submitted details; identity supplied with that reference
     * replaces those details.
     *
     * <p>A shared email or phone only suggests a match. Suggested matches are returned only with people-read
     * access. The workspace retention setting determines expires_at.
     *
     * <p>An existing person keeps their original expiry. An Idempotency-Key returns the same retained person
     * on retries, without creating another. Changed input with the same key answers 409
     * idempotency_key_reused.
     *
     * <p>A customer reference that names another person or several people answers 409
     * person_reference_conflict.
     *
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateWorkspacePersonResponse create(@Nullable String idempotencyKey, @Nonnull WorkspacePersonRequest body) {
        CreateWorkspacePersonRequest request = new CreateWorkspacePersonRequest(idempotencyKey, body);
        RequestOperation<CreateWorkspacePersonRequest, CreateWorkspacePersonResponse> operation =
                new CreateWorkspacePerson.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Read a workspace person.
     *
     * <p>Returns submitted details, the current retention expiry and the most recent retained completed or
     * partial investigation evidence, together with retained follow-up search evidence from all of this
     * person's groups. Search evidence remains available after its group is deleted. Migrated results
     * whose original per-person status is unknown may also supply evidence.
     *
     * <p>Earlier results and pending jobs remain available through investigation history; each investigation
     * retains its own collected result. Full source records, posts and media are returned by default;
     * explicit evidence.read denial withholds collected evidence and free-text notes. Each read records
     * person.viewed without raw evidence in the audit log.
     *
     * <p>A missing or expired person answers 404.
     *
     * @return The call builder
     */
    public GetWorkspacePersonRequestBuilder get() {
        return new GetWorkspacePersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a workspace person.
     *
     * <p>Returns submitted details, the current retention expiry and the most recent retained completed or
     * partial investigation evidence, together with retained follow-up search evidence from all of this
     * person's groups. Search evidence remains available after its group is deleted. Migrated results
     * whose original per-person status is unknown may also supply evidence.
     *
     * <p>Earlier results and pending jobs remain available through investigation history; each investigation
     * retains its own collected result. Full source records, posts and media are returned by default;
     * explicit evidence.read denial withholds collected evidence and free-text notes. Each read records
     * person.viewed without raw evidence in the audit log.
     *
     * <p>A missing or expired person answers 404.
     *
     * @param personId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetWorkspacePersonResponse get(@Nonnull String personId) {
        return get(personId, null);
    }

    /**
     * Read a workspace person.
     *
     * <p>Returns submitted details, the current retention expiry and the most recent retained completed or
     * partial investigation evidence, together with retained follow-up search evidence from all of this
     * person's groups. Search evidence remains available after its group is deleted. Migrated results
     * whose original per-person status is unknown may also supply evidence.
     *
     * <p>Earlier results and pending jobs remain available through investigation history; each investigation
     * retains its own collected result. Full source records, posts and media are returned by default;
     * explicit evidence.read denial withholds collected evidence and free-text notes. Each read records
     * person.viewed without raw evidence in the audit log.
     *
     * <p>A missing or expired person answers 404.
     *
     * @param personId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetWorkspacePersonResponse get(@Nonnull String personId, @Nullable Options options) {
        GetWorkspacePersonRequest request = new GetWorkspacePersonRequest(personId);
        RequestOperation<GetWorkspacePersonRequest, GetWorkspacePersonResponse> operation =
                new GetWorkspacePerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Delete a workspace person.
     *
     * <p>Deletes a person and their retained evidence, investigation history and group memberships. Requires
     * the separate delete permission. The audit retains only the person id and deletion metadata.
     *
     * @return The call builder
     */
    public DeleteWorkspacePersonRequestBuilder delete() {
        return new DeleteWorkspacePersonRequestBuilder(sdkConfiguration);
    }

    /**
     * Delete a workspace person.
     *
     * <p>Deletes a person and their retained evidence, investigation history and group memberships. Requires
     * the separate delete permission. The audit retains only the person id and deletion metadata.
     *
     * @param personId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteWorkspacePersonResponse delete(@Nonnull String personId) {
        return delete(personId, null);
    }

    /**
     * Delete a workspace person.
     *
     * <p>Deletes a person and their retained evidence, investigation history and group memberships. Requires
     * the separate delete permission. The audit retains only the person id and deletion metadata.
     *
     * @param personId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteWorkspacePersonResponse delete(@Nonnull String personId, @Nullable Options options) {
        DeleteWorkspacePersonRequest request = new DeleteWorkspacePersonRequest(personId);
        RequestOperation<DeleteWorkspacePersonRequest, DeleteWorkspacePersonResponse> operation =
                new DeleteWorkspacePerson.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Join one of a workspace person's records to them, or reject it.
     *
     * <p>Records a verdict on one of the records the person report holds: joined makes the record the
     * person's whatever its likelihood, and rejected keeps it a lead that leads nowhere. The verdict
     * belongs to the person on their own: the person report and enrichment follow it, and each group keeps
     * its own verdicts. It sticks for as long as the person's evidence holds the same record, and a later
     * verdict on the same record replaces it.
     *
     * <p>After each verdict, enrichment reads the person again under the environment's enrichment setting: it
     * starts no search from a rejected record or from what a search of it found, and a joined record's
     * values lead on. A record the person report does not hold answers 404. Each verdict is audited as
     * person.record_judged.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The call builder
     */
    public JudgeWorkspacePersonRecordRequestBuilder judgeRecord() {
        return new JudgeWorkspacePersonRecordRequestBuilder(sdkConfiguration);
    }

    /**
     * Join one of a workspace person's records to them, or reject it.
     *
     * <p>Records a verdict on one of the records the person report holds: joined makes the record the
     * person's whatever its likelihood, and rejected keeps it a lead that leads nowhere. The verdict
     * belongs to the person on their own: the person report and enrichment follow it, and each group keeps
     * its own verdicts. It sticks for as long as the person's evidence holds the same record, and a later
     * verdict on the same record replaces it.
     *
     * <p>After each verdict, enrichment reads the person again under the environment's enrichment setting: it
     * starts no search from a rejected record or from what a search of it found, and a joined record's
     * values lead on. A record the person report does not hold answers 404. Each verdict is audited as
     * person.record_judged.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param personId
     * @param body A reviewer's verdict on whether one of a person's lookup records is theirs.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeWorkspacePersonRecordResponse judgeRecord(
            @Nonnull String personId, @Nonnull RecordVerdictRequest body) {
        return judgeRecord(personId, body, null);
    }

    /**
     * Join one of a workspace person's records to them, or reject it.
     *
     * <p>Records a verdict on one of the records the person report holds: joined makes the record the
     * person's whatever its likelihood, and rejected keeps it a lead that leads nowhere. The verdict
     * belongs to the person on their own: the person report and enrichment follow it, and each group keeps
     * its own verdicts. It sticks for as long as the person's evidence holds the same record, and a later
     * verdict on the same record replaces it.
     *
     * <p>After each verdict, enrichment reads the person again under the environment's enrichment setting: it
     * starts no search from a rejected record or from what a search of it found, and a joined record's
     * values lead on. A record the person report does not hold answers 404. Each verdict is audited as
     * person.record_judged.
     *
     * <p>The decisions scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param personId
     * @param body A reviewer's verdict on whether one of a person's lookup records is theirs.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public JudgeWorkspacePersonRecordResponse judgeRecord(
            @Nonnull String personId, @Nonnull RecordVerdictRequest body, @Nullable Options options) {
        JudgeWorkspacePersonRecordRequest request = new JudgeWorkspacePersonRecordRequest(personId, body);
        RequestOperation<JudgeWorkspacePersonRecordRequest, JudgeWorkspacePersonRecordResponse> operation =
                new JudgeWorkspacePersonRecord.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
