package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.CreateWorkspacePersonRequest;
import org.noresponse.models.operations.DeleteWorkspacePersonRequest;
import org.noresponse.models.operations.GetWorkspacePersonRequest;
import org.noresponse.models.operations.JudgeWorkspacePersonRecordRequest;
import org.noresponse.models.operations.ListWorkspacePeopleRequest;
import org.noresponse.models.operations.RunWorkspacePersonSearchRequest;
import org.noresponse.models.operations.StartWorkspacePersonSearchRequest;
import org.noresponse.models.operations.async.CreateWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.async.CreateWorkspacePersonResponse;
import org.noresponse.models.operations.async.DeleteWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.async.DeleteWorkspacePersonResponse;
import org.noresponse.models.operations.async.GetWorkspacePersonRequestBuilder;
import org.noresponse.models.operations.async.GetWorkspacePersonResponse;
import org.noresponse.models.operations.async.JudgeWorkspacePersonRecordRequestBuilder;
import org.noresponse.models.operations.async.JudgeWorkspacePersonRecordResponse;
import org.noresponse.models.operations.async.ListWorkspacePeopleRequestBuilder;
import org.noresponse.models.operations.async.ListWorkspacePeopleResponse;
import org.noresponse.models.operations.async.RunWorkspacePersonSearchRequestBuilder;
import org.noresponse.models.operations.async.RunWorkspacePersonSearchResponse;
import org.noresponse.models.operations.async.StartWorkspacePersonSearchRequestBuilder;
import org.noresponse.models.operations.async.StartWorkspacePersonSearchResponse;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.models.shared.SearchRequest;
import org.noresponse.models.shared.WorkspacePersonRequest;
import org.noresponse.operations.CreateWorkspacePerson;
import org.noresponse.operations.DeleteWorkspacePerson;
import org.noresponse.operations.GetWorkspacePerson;
import org.noresponse.operations.JudgeWorkspacePersonRecord;
import org.noresponse.operations.ListWorkspacePeople;
import org.noresponse.operations.RunWorkspacePersonSearch;
import org.noresponse.operations.StartWorkspacePersonSearch;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Workspace people, submitted identity, source evidence and expiry.
 */
public class AsyncPeople {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final People syncSDK;

    AsyncPeople(People syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public People sync() {
        return syncSDK;
    }

    /**
     * List workspace people.
     *
     * <p>Lists retained people in the key workspace and environment, including people in no group. Each
     * returned person records a person.viewed audit event. Expired people are excluded before background
     * deletion.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<ListWorkspacePeopleResponse>} - The async response
     */
    public CompletableFuture<ListWorkspacePeopleResponse> listDirect() {
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
     * @return {@code CompletableFuture<ListWorkspacePeopleResponse>} - The async response
     */
    public CompletableFuture<ListWorkspacePeopleResponse> list(
            @Nullable Long limit, @Nullable String after, @Nullable Options options) {
        ListWorkspacePeopleRequest request = new ListWorkspacePeopleRequest(limit, after);
        AsyncRequestOperation<ListWorkspacePeopleRequest, ListWorkspacePeopleResponse> operation =
                new ListWorkspacePeople.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CreateWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<CreateWorkspacePersonResponse> create(@Nonnull WorkspacePersonRequest body) {
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
     * @return {@code CompletableFuture<CreateWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<CreateWorkspacePersonResponse> create(
            @Nullable String idempotencyKey, @Nonnull WorkspacePersonRequest body) {
        CreateWorkspacePersonRequest request = new CreateWorkspacePersonRequest(idempotencyKey, body);
        AsyncRequestOperation<CreateWorkspacePersonRequest, CreateWorkspacePersonResponse> operation =
                new CreateWorkspacePerson.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<GetWorkspacePersonResponse> get(@Nonnull String personId) {
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
     * @return {@code CompletableFuture<GetWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<GetWorkspacePersonResponse> get(@Nonnull String personId, @Nullable Options options) {
        GetWorkspacePersonRequest request = new GetWorkspacePersonRequest(personId);
        AsyncRequestOperation<GetWorkspacePersonRequest, GetWorkspacePersonResponse> operation =
                new GetWorkspacePerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Delete a workspace person.
     *
     * <p>Deletes a person and their retained evidence, investigation history and group memberships. Requires
     * the separate delete permission. The audit retains only the person id and deletion metadata.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<DeleteWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<DeleteWorkspacePersonResponse> delete(@Nonnull String personId) {
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
     * @return {@code CompletableFuture<DeleteWorkspacePersonResponse>} - The async response
     */
    public CompletableFuture<DeleteWorkspacePersonResponse> delete(
            @Nonnull String personId, @Nullable Options options) {
        DeleteWorkspacePersonRequest request = new DeleteWorkspacePersonRequest(personId);
        AsyncRequestOperation<DeleteWorkspacePersonRequest, DeleteWorkspacePersonResponse> operation =
                new DeleteWorkspacePerson.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<JudgeWorkspacePersonRecordResponse>} - The async response
     */
    public CompletableFuture<JudgeWorkspacePersonRecordResponse> judgeRecord(
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
     * @return {@code CompletableFuture<JudgeWorkspacePersonRecordResponse>} - The async response
     */
    public CompletableFuture<JudgeWorkspacePersonRecordResponse> judgeRecord(
            @Nonnull String personId, @Nonnull RecordVerdictRequest body, @Nullable Options options) {
        JudgeWorkspacePersonRecordRequest request = new JudgeWorkspacePersonRecordRequest(personId, body);
        AsyncRequestOperation<JudgeWorkspacePersonRecordRequest, JudgeWorkspacePersonRecordResponse> operation =
                new JudgeWorkspacePersonRecord.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Search again on a value of one of a workspace person's records.
     *
     * <p>Starts a search on the person alone, outside any group, as the group route does. The person's own
     * enrichment then follows what it finds. Its records join the person report.
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
     * the search found on that site, so a chain grows less likely with each search and stops.
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
     * @return The async call builder
     */
    public StartWorkspacePersonSearchRequestBuilder startSearch() {
        return new StartWorkspacePersonSearchRequestBuilder(sdkConfiguration);
    }

    /**
     * Search again on a value of one of a workspace person's records.
     *
     * <p>Starts a search on the person alone, outside any group, as the group route does. The person's own
     * enrichment then follows what it finds. Its records join the person report.
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
     * the search found on that site, so a chain grows less likely with each search and stops.
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
     * @param personId
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return {@code CompletableFuture<StartWorkspacePersonSearchResponse>} - The async response
     */
    public CompletableFuture<StartWorkspacePersonSearchResponse> startSearch(
            @Nonnull String personId, @Nonnull SearchRequest body) {
        return startSearch(personId, null, body);
    }

    /**
     * Search again on a value of one of a workspace person's records.
     *
     * <p>Starts a search on the person alone, outside any group, as the group route does. The person's own
     * enrichment then follows what it finds. Its records join the person report.
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
     * the search found on that site, so a chain grows less likely with each search and stops.
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
     * @param personId
     * @param idempotencyKey Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person.
     * @param body The value to search again, named by the record and field that hold it rather than sent again.
     * @return {@code CompletableFuture<StartWorkspacePersonSearchResponse>} - The async response
     */
    public CompletableFuture<StartWorkspacePersonSearchResponse> startSearch(
            @Nonnull String personId, @Nullable String idempotencyKey, @Nonnull SearchRequest body) {
        StartWorkspacePersonSearchRequest request =
                new StartWorkspacePersonSearchRequest(personId, idempotencyKey, body);
        AsyncRequestOperation<StartWorkspacePersonSearchRequest, StartWorkspacePersonSearchResponse> operation =
                new StartWorkspacePersonSearch.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Run a counted password or hash search on a workspace person.
     *
     * <p>Runs a counted search that was started on the person alone (POST /v1/people/{person_id}/searches),
     * as the group route does. Runs a password or hash search once a person has seen its count. A search
     * still counting answers 409 group_conflict; one already queued, running or finished answers as it is.
     *
     * <p>The body is an empty object. Its estimate counts against the environment's screening_daily_cap. Each
     * run is audited as person.search_run.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @return The async call builder
     */
    public RunWorkspacePersonSearchRequestBuilder runSearch() {
        return new RunWorkspacePersonSearchRequestBuilder(sdkConfiguration);
    }

    /**
     * Run a counted password or hash search on a workspace person.
     *
     * <p>Runs a counted search that was started on the person alone (POST /v1/people/{person_id}/searches),
     * as the group route does. Runs a password or hash search once a person has seen its count. A search
     * still counting answers 409 group_conflict; one already queued, running or finished answers as it is.
     *
     * <p>The body is an empty object. Its estimate counts against the environment's screening_daily_cap. Each
     * run is audited as person.search_run.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param personId
     * @param searchId
     * @param body An empty object.
     * @return {@code CompletableFuture<RunWorkspacePersonSearchResponse>} - The async response
     */
    public CompletableFuture<RunWorkspacePersonSearchResponse> runSearch(
            @Nonnull String personId, @Nonnull String searchId, @Nonnull ScreeningCancelRequest body) {
        return runSearch(personId, searchId, body, null);
    }

    /**
     * Run a counted password or hash search on a workspace person.
     *
     * <p>Runs a counted search that was started on the person alone (POST /v1/people/{person_id}/searches),
     * as the group route does. Runs a password or hash search once a person has seen its count. A search
     * still counting answers 409 group_conflict; one already queued, running or finished answers as it is.
     *
     * <p>The body is an empty object. Its estimate counts against the environment's screening_daily_cap. Each
     * run is audited as person.search_run.
     *
     * <p>The investigations scope grants this operation. Explicit evidence.read restrictions override default
     * evidence access. The groups scope is also accepted for existing integrations.
     *
     * <p>The API key selects the workspace and environment.
     *
     * @param personId
     * @param searchId
     * @param body An empty object.
     * @param options additional options
     * @return {@code CompletableFuture<RunWorkspacePersonSearchResponse>} - The async response
     */
    public CompletableFuture<RunWorkspacePersonSearchResponse> runSearch(
            @Nonnull String personId,
            @Nonnull String searchId,
            @Nonnull ScreeningCancelRequest body,
            @Nullable Options options) {
        RunWorkspacePersonSearchRequest request = new RunWorkspacePersonSearchRequest(personId, searchId, body);
        AsyncRequestOperation<RunWorkspacePersonSearchRequest, RunWorkspacePersonSearchResponse> operation =
                new RunWorkspacePersonSearch.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
