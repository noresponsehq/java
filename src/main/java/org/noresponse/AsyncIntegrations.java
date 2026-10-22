package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;
import static org.noresponse.operations.Operations.AsyncRequestlessOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.DeleteIntegrationRequest;
import org.noresponse.models.operations.RotateIntegrationSecretRequest;
import org.noresponse.models.operations.async.CreateIntegrationRequestBuilder;
import org.noresponse.models.operations.async.CreateIntegrationResponse;
import org.noresponse.models.operations.async.DeleteIntegrationRequestBuilder;
import org.noresponse.models.operations.async.DeleteIntegrationResponse;
import org.noresponse.models.operations.async.ListIntegrationsRequestBuilder;
import org.noresponse.models.operations.async.ListIntegrationsResponse;
import org.noresponse.models.operations.async.RotateIntegrationSecretRequestBuilder;
import org.noresponse.models.operations.async.RotateIntegrationSecretResponse;
import org.noresponse.models.shared.IntegrationRequest;
import org.noresponse.models.shared.IntegrationRotationRequest;
import org.noresponse.operations.CreateIntegration;
import org.noresponse.operations.DeleteIntegration;
import org.noresponse.operations.ListIntegrations;
import org.noresponse.operations.RotateIntegrationSecret;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Hosted endpoints that answer your sign-up and sign-in hooks.
 */
public class AsyncIntegrations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Integrations syncSDK;

    AsyncIntegrations(Integrations syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Integrations sync() {
        return syncSDK;
    }

    /**
     * Register a hosted provider integration.
     *
     * <p>Registers a WorkOS or Auth0 integration and returns its hosted endpoint, url, so a sign-up or
     * sign-in is scored with no server of your own. For WorkOS, send the Actions signing secret from the
     * WorkOS dashboard and point the user registration and authentication Actions at url. For Auth0, the
     * answer's secret is the shared secret of the NoResponse Auth0 Action, shown once: store it as an
     * Action secret.
     *
     * <p>hook_points defaults to both pre_registration and post_login; an event that is not listed is allowed
     * without an assessment. The endpoint waits for the full assessment for at most 2.5 seconds (WorkOS)
     * or 4.5 seconds (Auth0), denies only when risk.block is true and allows on any failure. Requires the
     * integrations scope; at most 10 per account.
     *
     * <p>Not idempotent: a retry after a lost answer registers another integration.
     *
     * @return The async call builder
     */
    public CreateIntegrationRequestBuilder create() {
        return new CreateIntegrationRequestBuilder(sdkConfiguration);
    }

    /**
     * Register a hosted provider integration.
     *
     * <p>Registers a WorkOS or Auth0 integration and returns its hosted endpoint, url, so a sign-up or
     * sign-in is scored with no server of your own. For WorkOS, send the Actions signing secret from the
     * WorkOS dashboard and point the user registration and authentication Actions at url. For Auth0, the
     * answer's secret is the shared secret of the NoResponse Auth0 Action, shown once: store it as an
     * Action secret.
     *
     * <p>hook_points defaults to both pre_registration and post_login; an event that is not listed is allowed
     * without an assessment. The endpoint waits for the full assessment for at most 2.5 seconds (WorkOS)
     * or 4.5 seconds (Auth0), denies only when risk.block is true and allows on any failure. Requires the
     * integrations scope; at most 10 per account.
     *
     * <p>Not idempotent: a retry after a lost answer registers another integration.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<CreateIntegrationResponse>} - The async response
     */
    public CompletableFuture<CreateIntegrationResponse> create(@Nonnull IntegrationRequest request) {
        AsyncRequestOperation<IntegrationRequest, CreateIntegrationResponse> operation =
                new CreateIntegration.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * List the tenant's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @return The async call builder
     */
    public ListIntegrationsRequestBuilder list() {
        return new ListIntegrationsRequestBuilder(sdkConfiguration);
    }

    /**
     * List the tenant's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @return {@code CompletableFuture<ListIntegrationsResponse>} - The async response
     */
    public CompletableFuture<ListIntegrationsResponse> listDirect() {
        return list(null);
    }

    /**
     * List the tenant's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @param options additional options
     * @return {@code CompletableFuture<ListIntegrationsResponse>} - The async response
     */
    public CompletableFuture<ListIntegrationsResponse> list(@Nullable Options options) {
        AsyncRequestlessOperation<ListIntegrationsResponse> operation =
                new ListIntegrations.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest().thenCompose(operation::handleResponse);
    }

    /**
     * Delete an integration.
     *
     * <p>Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action
     * from the provider first. Requires the integrations scope. An unknown or already deleted integration
     * answers 404.
     *
     * @return The async call builder
     */
    public DeleteIntegrationRequestBuilder delete() {
        return new DeleteIntegrationRequestBuilder(sdkConfiguration);
    }

    /**
     * Delete an integration.
     *
     * <p>Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action
     * from the provider first. Requires the integrations scope. An unknown or already deleted integration
     * answers 404.
     *
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another tenant's id, answers 404.
     * @return {@code CompletableFuture<DeleteIntegrationResponse>} - The async response
     */
    public CompletableFuture<DeleteIntegrationResponse> delete(@Nonnull String integrationId) {
        return delete(integrationId, null);
    }

    /**
     * Delete an integration.
     *
     * <p>Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action
     * from the provider first. Requires the integrations scope. An unknown or already deleted integration
     * answers 404.
     *
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another tenant's id, answers 404.
     * @param options additional options
     * @return {@code CompletableFuture<DeleteIntegrationResponse>} - The async response
     */
    public CompletableFuture<DeleteIntegrationResponse> delete(
            @Nonnull String integrationId, @Nullable Options options) {
        DeleteIntegrationRequest request = new DeleteIntegrationRequest(integrationId);
        AsyncRequestOperation<DeleteIntegrationRequest, DeleteIntegrationResponse> operation =
                new DeleteIntegration.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }

    /**
     * Replace an integration's secret.
     *
     * <p>For Auth0, send {} and the answer's secret is the new shared secret, shown once. For WorkOS,
     * regenerate the Actions secret in the WorkOS dashboard and send it as signing_secret. The replaced
     * secret is still accepted for 24 hours (previous_secret_expires_at).
     *
     * <p>Requires the integrations scope. Not idempotent: a retry after a lost answer rotates again.
     *
     * @return The async call builder
     */
    public RotateIntegrationSecretRequestBuilder rotate() {
        return new RotateIntegrationSecretRequestBuilder(sdkConfiguration);
    }

    /**
     * Replace an integration's secret.
     *
     * <p>For Auth0, send {} and the answer's secret is the new shared secret, shown once. For WorkOS,
     * regenerate the Actions secret in the WorkOS dashboard and send it as signing_secret. The replaced
     * secret is still accepted for 24 hours (previous_secret_expires_at).
     *
     * <p>Requires the integrations scope. Not idempotent: a retry after a lost answer rotates again.
     *
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another tenant's id, answers 404.
     * @param body POST /v1/integrations/{integration_id}/rotate body. For an Auth0 integration send {}: NoResponse generates a new shared secret and returns it once. For a WorkOS integration send the new signing_secret after regenerating it in the WorkOS dashboard. Either way the previous secret is still accepted for 24 hours, so the provider can be switched over without a refused call.
     * @return {@code CompletableFuture<RotateIntegrationSecretResponse>} - The async response
     */
    public CompletableFuture<RotateIntegrationSecretResponse> rotate(
            @Nonnull String integrationId, @Nonnull IntegrationRotationRequest body) {
        RotateIntegrationSecretRequest request = new RotateIntegrationSecretRequest(integrationId, body);
        AsyncRequestOperation<RotateIntegrationSecretRequest, RotateIntegrationSecretResponse> operation =
                new RotateIntegrationSecret.Async(sdkConfiguration, _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
