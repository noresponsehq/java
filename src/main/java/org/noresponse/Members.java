package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.ListMembersRequest;
import org.noresponse.models.operations.ListMembersRequestBuilder;
import org.noresponse.models.operations.ListMembersResponse;
import org.noresponse.models.operations.RemoveMemberRequest;
import org.noresponse.models.operations.RemoveMemberRequestBuilder;
import org.noresponse.models.operations.RemoveMemberResponse;
import org.noresponse.models.operations.UpdateMemberRequest;
import org.noresponse.models.operations.UpdateMemberRequestBuilder;
import org.noresponse.models.operations.UpdateMemberResponse;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.operations.ListMembers;
import org.noresponse.operations.RemoveMember;
import org.noresponse.operations.UpdateMember;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Scoped workspace membership automation.
 */
public class Members {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncMembers asyncSDK;

    Members(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncMembers(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncMembers async() {
        return asyncSDK;
    }

    /**
     * List a workspace's members.
     *
     * <p>Read the workspace directory with a live key carrying members or members_manage. Member identities
     * and grants belong to this workspace. Owner-controlled issuance limits team automation to authorised
     * service credentials.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListMembersResponse list(@Nonnull String workspaceId) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListMembersResponse list(@Nonnull String workspaceId, @Nullable Options options) {
        ListMembersRequest request = new ListMembersRequest(workspaceId);
        RequestOperation<ListMembersRequest, ListMembersResponse> operation =
                new ListMembers.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Change a member's role.
     *
     * <p>Update a non-owner member role, environment access or sensitive permission exclusions with a live
     * members_manage key. Owner changes, identity binding and support access remain outside this scope.
     * The current delegation ceiling is checked within the change transaction.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateMemberResponse update(
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public UpdateMemberResponse update(
            @Nonnull String workspaceId,
            @Nonnull String memberId,
            @Nonnull MemberUpdateRequest body,
            @Nullable Options options) {
        UpdateMemberRequest request = new UpdateMemberRequest(workspaceId, memberId, body);
        RequestOperation<UpdateMemberRequest, UpdateMemberResponse> operation =
                new UpdateMember.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Remove a member.
     *
     * <p>Remove a non-owner member with a live members_manage key. Subsequent access is refused and personal
     * credentials are revoked. Independently owned service credentials follow their own lifecycle.
     *
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveMemberResponse remove(@Nonnull String workspaceId, @Nonnull String memberId) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RemoveMemberResponse remove(
            @Nonnull String workspaceId, @Nonnull String memberId, @Nullable Options options) {
        RemoveMemberRequest request = new RemoveMemberRequest(workspaceId, memberId);
        RequestOperation<RemoveMemberRequest, RemoveMemberResponse> operation =
                new RemoveMember.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
