package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.CreateInviteRequest;
import org.noresponse.models.operations.ListInvitesRequest;
import org.noresponse.models.operations.ResendInviteRequest;
import org.noresponse.models.operations.RevokeInviteRequest;
import org.noresponse.models.operations.async.CreateInviteRequestBuilder;
import org.noresponse.models.operations.async.CreateInviteResponse;
import org.noresponse.models.operations.async.ListInvitesRequestBuilder;
import org.noresponse.models.operations.async.ListInvitesResponse;
import org.noresponse.models.operations.async.ResendInviteRequestBuilder;
import org.noresponse.models.operations.async.ResendInviteResponse;
import org.noresponse.models.operations.async.RevokeInviteRequestBuilder;
import org.noresponse.models.operations.async.RevokeInviteResponse;
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
public class AsyncInvites {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Invites syncSDK;

    AsyncInvites(Invites syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Invites sync() {
        return syncSDK;
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
     * @return The async call builder
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
     * @return {@code CompletableFuture<CreateInviteResponse>} - The async response
     */
    public CompletableFuture<CreateInviteResponse> create(@Nonnull String workspaceId, @Nonnull InviteRequest body) {
        CreateInviteRequest request = new CreateInviteRequest(workspaceId, body);
        AsyncRequestOperation<CreateInviteRequest, CreateInviteResponse> operation =
                new CreateInvite.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List pending invites.
     *
     * <p>Read pending workspace invitations with a live members_manage key. Tokens are excluded. Expiry and
     * token reissue timestamps describe invitation state; they do not establish email delivery.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<ListInvitesResponse>} - The async response
     */
    public CompletableFuture<ListInvitesResponse> list(@Nonnull String workspaceId) {
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
     * @return {@code CompletableFuture<ListInvitesResponse>} - The async response
     */
    public CompletableFuture<ListInvitesResponse> list(@Nonnull String workspaceId, @Nullable Options options) {
        ListInvitesRequest request = new ListInvitesRequest(workspaceId);
        AsyncRequestOperation<ListInvitesRequest, ListInvitesResponse> operation =
                new ListInvites.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Revoke an invite.
     *
     * <p>Revoke a pending invitation with a live members_manage key. A revoked token cannot be accepted.
     * Owner-role invitations remain outside machine delegation.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<RevokeInviteResponse>} - The async response
     */
    public CompletableFuture<RevokeInviteResponse> revoke(@Nonnull String workspaceId, @Nonnull String inviteId) {
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
     * @return {@code CompletableFuture<RevokeInviteResponse>} - The async response
     */
    public CompletableFuture<RevokeInviteResponse> revoke(
            @Nonnull String workspaceId, @Nonnull String inviteId, @Nullable Options options) {
        RevokeInviteRequest request = new RevokeInviteRequest(workspaceId, inviteId);
        AsyncRequestOperation<RevokeInviteRequest, RevokeInviteResponse> operation =
                new RevokeInvite.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Reissue an invitation for delivery.
     *
     * <p>Reissue a pending non-owner invitation with a live members_manage key. The old token is invalidated
     * and the new token is returned once with renewed expiry. Its role and permissions are preserved.
     *
     * <p>This operation does not prove email delivery and is not automatically retried.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<ResendInviteResponse>} - The async response
     */
    public CompletableFuture<ResendInviteResponse> resend(
            @Nonnull String workspaceId, @Nonnull String inviteId, @Nonnull InviteResendRequest body) {
        ResendInviteRequest request = new ResendInviteRequest(workspaceId, inviteId, body);
        AsyncRequestOperation<ResendInviteRequest, ResendInviteResponse> operation =
                new ResendInvite.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
