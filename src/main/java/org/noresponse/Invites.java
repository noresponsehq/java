package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.CreateInviteRequest;
import org.noresponse.models.operations.CreateInviteRequestBuilder;
import org.noresponse.models.operations.CreateInviteResponse;
import org.noresponse.models.operations.ListInvitesRequest;
import org.noresponse.models.operations.ListInvitesRequestBuilder;
import org.noresponse.models.operations.ListInvitesResponse;
import org.noresponse.models.operations.ResendInviteRequest;
import org.noresponse.models.operations.ResendInviteRequestBuilder;
import org.noresponse.models.operations.ResendInviteResponse;
import org.noresponse.models.operations.RevokeInviteRequest;
import org.noresponse.models.operations.RevokeInviteRequestBuilder;
import org.noresponse.models.operations.RevokeInviteResponse;
import org.noresponse.models.shared.InviteRequest;
import org.noresponse.models.shared.InviteResendRequest;
import org.noresponse.operations.CreateInvite;
import org.noresponse.operations.ListInvites;
import org.noresponse.operations.ResendInvite;
import org.noresponse.operations.RevokeInvite;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Workspace-bound invitation lifecycle.
 */
public class Invites {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncInvites asyncSDK;

    Invites(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncInvites(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncInvites async() {
        return asyncSDK;
    }

    /**
     * Invite a member.
     *
     * <p>Create a workspace-bound invite for a non-owner role with a live members_manage key. The new token
     * is returned once. Set external_user_id to bind acceptance to an exact verified provider subject;
     * email is contact information.
     *
     * <p>Creating a token does not send an email.
     *
     * @return The call builder
     */
    public CreateInviteRequestBuilder create() {
        return new CreateInviteRequestBuilder(sdkConfiguration);
    }

    /**
     * Invite a member.
     *
     * <p>Create a workspace-bound invite for a non-owner role with a live members_manage key. The new token
     * is returned once. Set external_user_id to bind acceptance to an exact verified provider subject;
     * email is contact information.
     *
     * <p>Creating a token does not send an email.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param body POST /v1/workspaces/{workspace_id}/invites body. Creates an invite for role and returns its token once. email is where the console sends the token and is not checked against the user who accepts. With external_user_id set, only that user can accept.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateInviteResponse create(@Nonnull String workspaceId, @Nonnull InviteRequest body) {
        CreateInviteRequest request = new CreateInviteRequest(workspaceId, body);
        RequestOperation<CreateInviteRequest, CreateInviteResponse> operation =
                new CreateInvite.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List pending invites.
     *
     * <p>Read pending workspace invitations with a live members_manage key. Tokens are excluded. Expiry and
     * token reissue timestamps describe invitation state; they do not establish email delivery.
     *
     * @return The call builder
     */
    public ListInvitesRequestBuilder list() {
        return new ListInvitesRequestBuilder(sdkConfiguration);
    }

    /**
     * List pending invites.
     *
     * <p>Read pending workspace invitations with a live members_manage key. Tokens are excluded. Expiry and
     * token reissue timestamps describe invitation state; they do not establish email delivery.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListInvitesResponse list(@Nonnull String workspaceId) {
        return list(workspaceId, null);
    }

    /**
     * List pending invites.
     *
     * <p>Read pending workspace invitations with a live members_manage key. Tokens are excluded. Expiry and
     * token reissue timestamps describe invitation state; they do not establish email delivery.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListInvitesResponse list(@Nonnull String workspaceId, @Nullable Options options) {
        ListInvitesRequest request = new ListInvitesRequest(workspaceId);
        RequestOperation<ListInvitesRequest, ListInvitesResponse> operation =
                new ListInvites.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Revoke an invite.
     *
     * <p>Revoke a pending invitation with a live members_manage key. A revoked token cannot be accepted.
     * Owner-role invitations remain outside machine delegation.
     *
     * @return The call builder
     */
    public RevokeInviteRequestBuilder revoke() {
        return new RevokeInviteRequestBuilder(sdkConfiguration);
    }

    /**
     * Revoke an invite.
     *
     * <p>Revoke a pending invitation with a live members_manage key. A revoked token cannot be accepted.
     * Owner-role invitations remain outside machine delegation.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param inviteId The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RevokeInviteResponse revoke(@Nonnull String workspaceId, @Nonnull String inviteId) {
        return revoke(workspaceId, inviteId, null);
    }

    /**
     * Revoke an invite.
     *
     * <p>Revoke a pending invitation with a live members_manage key. A revoked token cannot be accepted.
     * Owner-role invitations remain outside machine delegation.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param inviteId The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RevokeInviteResponse revoke(
            @Nonnull String workspaceId, @Nonnull String inviteId, @Nullable Options options) {
        RevokeInviteRequest request = new RevokeInviteRequest(workspaceId, inviteId);
        RequestOperation<RevokeInviteRequest, RevokeInviteResponse> operation =
                new RevokeInvite.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Reissue an invitation for delivery.
     *
     * <p>Reissue a pending non-owner invitation with a live members_manage key. The old token is invalidated
     * and the new token is returned once with renewed expiry. Its role and permissions are preserved.
     *
     * <p>This operation does not prove email delivery and is not automatically retried.
     *
     * @return The call builder
     */
    public ResendInviteRequestBuilder resend() {
        return new ResendInviteRequestBuilder(sdkConfiguration);
    }

    /**
     * Reissue an invitation for delivery.
     *
     * <p>Reissue a pending non-owner invitation with a live members_manage key. The old token is invalidated
     * and the new token is returned once with renewed expiry. Its role and permissions are preserved.
     *
     * <p>This operation does not prove email delivery and is not automatically retried.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param inviteId The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.
     * @param body
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ResendInviteResponse resend(
            @Nonnull String workspaceId, @Nonnull String inviteId, @Nonnull InviteResendRequest body) {
        ResendInviteRequest request = new ResendInviteRequest(workspaceId, inviteId, body);
        RequestOperation<ResendInviteRequest, ResendInviteResponse> operation =
                new ResendInvite.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
