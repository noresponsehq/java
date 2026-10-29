package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.ListMembersRequest;
import org.noresponse.models.operations.RemoveMemberRequest;
import org.noresponse.models.operations.UpdateMemberRequest;
import org.noresponse.models.operations.async.ListMembersRequestBuilder;
import org.noresponse.models.operations.async.ListMembersResponse;
import org.noresponse.models.operations.async.RemoveMemberRequestBuilder;
import org.noresponse.models.operations.async.RemoveMemberResponse;
import org.noresponse.models.operations.async.UpdateMemberRequestBuilder;
import org.noresponse.models.operations.async.UpdateMemberResponse;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.operations.ListMembers;
import org.noresponse.operations.RemoveMember;
import org.noresponse.operations.UpdateMember;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Scoped workspace membership automation.
 */
public class AsyncMembers {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Members syncSDK;

    AsyncMembers(Members syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Members sync() {
        return syncSDK;
    }

    /**
     * List a workspace's members.
     *
     * <p>Read the workspace directory with a live key carrying members or members_manage. Member identities
     * and grants belong to this workspace. Owner-controlled issuance limits team automation to authorised
     * service credentials.
     *
     * @return The async call builder
     */
    public ListMembersRequestBuilder list() {
        return new ListMembersRequestBuilder(sdkConfiguration);
    }

    /**
     * List a workspace's members.
     *
     * <p>Read the workspace directory with a live key carrying members or members_manage. Member identities
     * and grants belong to this workspace. Owner-controlled issuance limits team automation to authorised
     * service credentials.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @return {@code CompletableFuture<ListMembersResponse>} - The async response
     */
    public CompletableFuture<ListMembersResponse> list(@Nonnull String workspaceId) {
        return list(workspaceId, null);
    }

    /**
     * List a workspace's members.
     *
     * <p>Read the workspace directory with a live key carrying members or members_manage. Member identities
     * and grants belong to this workspace. Owner-controlled issuance limits team automation to authorised
     * service credentials.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param options additional options
     * @return {@code CompletableFuture<ListMembersResponse>} - The async response
     */
    public CompletableFuture<ListMembersResponse> list(@Nonnull String workspaceId, @Nullable Options options) {
        ListMembersRequest request = new ListMembersRequest(workspaceId);
        AsyncRequestOperation<ListMembersRequest, ListMembersResponse> operation =
                new ListMembers.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Change a member's role.
     *
     * <p>Update a non-owner member role, environment access or sensitive permission exclusions with a live
     * members_manage key. Owner changes, identity binding and support access remain outside this scope.
     * The current delegation ceiling is checked within the change transaction.
     *
     * @return The async call builder
     */
    public UpdateMemberRequestBuilder update() {
        return new UpdateMemberRequestBuilder(sdkConfiguration);
    }

    /**
     * Change a member's role.
     *
     * <p>Update a non-owner member role, environment access or sensitive permission exclusions with a live
     * members_manage key. Owner changes, identity binding and support access remain outside this scope.
     * The current delegation ceiling is checked within the change transaction.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param memberId The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     * @param body PATCH /v1/workspaces/{workspace_id}/members/{member_id} body: the member's new role. The workspace's last owner cannot be demoted.
     * @return {@code CompletableFuture<UpdateMemberResponse>} - The async response
     */
    public CompletableFuture<UpdateMemberResponse> update(
            @Nonnull String workspaceId, @Nonnull String memberId, @Nonnull MemberUpdateRequest body) {
        return update(workspaceId, memberId, body, null);
    }

    /**
     * Change a member's role.
     *
     * <p>Update a non-owner member role, environment access or sensitive permission exclusions with a live
     * members_manage key. Owner changes, identity binding and support access remain outside this scope.
     * The current delegation ceiling is checked within the change transaction.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param memberId The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     * @param body PATCH /v1/workspaces/{workspace_id}/members/{member_id} body: the member's new role. The workspace's last owner cannot be demoted.
     * @param options additional options
     * @return {@code CompletableFuture<UpdateMemberResponse>} - The async response
     */
    public CompletableFuture<UpdateMemberResponse> update(
            @Nonnull String workspaceId,
            @Nonnull String memberId,
            @Nonnull MemberUpdateRequest body,
            @Nullable Options options) {
        UpdateMemberRequest request = new UpdateMemberRequest(workspaceId, memberId, body);
        AsyncRequestOperation<UpdateMemberRequest, UpdateMemberResponse> operation =
                new UpdateMember.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Remove a member.
     *
     * <p>Remove a non-owner member with a live members_manage key. Subsequent access is refused and personal
     * credentials are revoked. Independently owned service credentials follow their own lifecycle.
     *
     * @return The async call builder
     */
    public RemoveMemberRequestBuilder remove() {
        return new RemoveMemberRequestBuilder(sdkConfiguration);
    }

    /**
     * Remove a member.
     *
     * <p>Remove a non-owner member with a live members_manage key. Subsequent access is refused and personal
     * credentials are revoked. Independently owned service credentials follow their own lifecycle.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param memberId The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     * @return {@code CompletableFuture<RemoveMemberResponse>} - The async response
     */
    public CompletableFuture<RemoveMemberResponse> remove(@Nonnull String workspaceId, @Nonnull String memberId) {
        return remove(workspaceId, memberId, null);
    }

    /**
     * Remove a member.
     *
     * <p>Remove a non-owner member with a live members_manage key. Subsequent access is refused and personal
     * credentials are revoked. Independently owned service credentials follow their own lifecycle.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param memberId The member's mem_ id. A member of another workspace, or a removed one, answers 404.
     * @param options additional options
     * @return {@code CompletableFuture<RemoveMemberResponse>} - The async response
     */
    public CompletableFuture<RemoveMemberResponse> remove(
            @Nonnull String workspaceId, @Nonnull String memberId, @Nullable Options options) {
        RemoveMemberRequest request = new RemoveMemberRequest(workspaceId, memberId);
        AsyncRequestOperation<RemoveMemberRequest, RemoveMemberResponse> operation =
                new RemoveMember.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
