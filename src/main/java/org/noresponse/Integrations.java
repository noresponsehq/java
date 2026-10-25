package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;
import static org.noresponse.operations.Operations.RequestlessOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.CreateIntegrationRequestBuilder;
import org.noresponse.models.operations.CreateIntegrationResponse;
import org.noresponse.models.operations.DeleteIntegrationRequest;
import org.noresponse.models.operations.DeleteIntegrationRequestBuilder;
import org.noresponse.models.operations.DeleteIntegrationResponse;
import org.noresponse.models.operations.ListIntegrationsRequestBuilder;
import org.noresponse.models.operations.ListIntegrationsResponse;
import org.noresponse.models.operations.RotateIntegrationSecretRequest;
import org.noresponse.models.operations.RotateIntegrationSecretRequestBuilder;
import org.noresponse.models.operations.RotateIntegrationSecretResponse;
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
public class Integrations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncIntegrations asyncSDK;

    Integrations(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncIntegrations(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncIntegrations async() {
        return asyncSDK;
    }

    /**
     * Register a hosted provider integration.
     *
     * <p>Registers a WorkOS, Auth0 or Supabase integration and returns its hosted endpoint, url, so a sign-up
     * or sign-in is scored with no server of your own. For WorkOS, send the Actions signing secret from
     * the WorkOS dashboard and point the user registration and authentication Actions at url. For
     * Supabase, send the hook secret Supabase generates (v1,whsec_...) and set url as the Before User
     * Created hook's HTTPS endpoint.
     *
     * <p>For Auth0, the answer's secret is the shared secret of the NoResponse Auth0 Action, shown once:
     * store it as an Action secret. hook_points defaults to every hook point the platform has (Supabase
     * has only pre_registration); an event that is not listed is allowed without an assessment. The
     * endpoint waits for the full assessment for at most 2.5 seconds (WorkOS, Supabase) or 4.5 seconds
     * (Auth0), denies only when risk.block is true and allows on any failure.
     *
     * <p>Requires the integrations scope; at most 10 per account. Not idempotent: a retry after a lost answer
     * registers another integration.
     *
     * @return The call builder
     */
    public CreateIntegrationRequestBuilder create() {
        return new CreateIntegrationRequestBuilder(sdkConfiguration);
    }

    /**
     * Register a hosted provider integration.
     *
     * <p>Registers a WorkOS, Auth0 or Supabase integration and returns its hosted endpoint, url, so a sign-up
     * or sign-in is scored with no server of your own. For WorkOS, send the Actions signing secret from
     * the WorkOS dashboard and point the user registration and authentication Actions at url. For
     * Supabase, send the hook secret Supabase generates (v1,whsec_...) and set url as the Before User
     * Created hook's HTTPS endpoint.
     *
     * <p>For Auth0, the answer's secret is the shared secret of the NoResponse Auth0 Action, shown once:
     * store it as an Action secret. hook_points defaults to every hook point the platform has (Supabase
     * has only pre_registration); an event that is not listed is allowed without an assessment. The
     * endpoint waits for the full assessment for at most 2.5 seconds (WorkOS, Supabase) or 4.5 seconds
     * (Auth0), denies only when risk.block is true and allows on any failure.
     *
     * <p>Requires the integrations scope; at most 10 per account. Not idempotent: a retry after a lost answer
     * registers another integration.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public CreateIntegrationResponse create(@Nonnull IntegrationRequest request) {
        RequestOperation<IntegrationRequest, CreateIntegrationResponse> operation =
                new CreateIntegration.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * List the workspace's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @return The call builder
     */
    public ListIntegrationsRequestBuilder list() {
        return new ListIntegrationsRequestBuilder(sdkConfiguration);
    }

    /**
     * List the workspace's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListIntegrationsResponse listDirect() {
        return list(null);
    }

    /**
     * List the workspace's integrations.
     *
     * <p>Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.
     *
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListIntegrationsResponse list(@Nullable Options options) {
        RequestlessOperation<ListIntegrationsResponse> operation =
                new ListIntegrations.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest());
    }

    /**
     * Delete an integration.
     *
     * <p>Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action
     * from the provider first. Requires the integrations scope. An unknown or already deleted integration
     * answers 404.
     *
     * @return The call builder
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
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another workspace's id, answers 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteIntegrationResponse delete(@Nonnull String integrationId) {
        return delete(integrationId, null);
    }

    /**
     * Delete an integration.
     *
     * <p>Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action
     * from the provider first. Requires the integrations scope. An unknown or already deleted integration
     * answers 404.
     *
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another workspace's id, answers 404.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public DeleteIntegrationResponse delete(@Nonnull String integrationId, @Nullable Options options) {
        DeleteIntegrationRequest request = new DeleteIntegrationRequest(integrationId);
        RequestOperation<DeleteIntegrationRequest, DeleteIntegrationResponse> operation =
                new DeleteIntegration.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }

    /**
     * Replace an integration's secret.
     *
     * <p>For Auth0, send {} and the answer's secret is the new shared secret, shown once. For WorkOS,
     * regenerate the Actions secret in the WorkOS dashboard and send it as signing_secret; for Supabase,
     * generate a new hook secret in the Supabase dashboard and send it as signing_secret. The replaced
     * secret is still accepted for 24 hours (previous_secret_expires_at).
     *
     * <p>Requires the integrations scope. Not idempotent: a retry after a lost answer rotates again.
     *
     * @return The call builder
     */
    public RotateIntegrationSecretRequestBuilder rotate() {
        return new RotateIntegrationSecretRequestBuilder(sdkConfiguration);
    }

    /**
     * Replace an integration's secret.
     *
     * <p>For Auth0, send {} and the answer's secret is the new shared secret, shown once. For WorkOS,
     * regenerate the Actions secret in the WorkOS dashboard and send it as signing_secret; for Supabase,
     * generate a new hook secret in the Supabase dashboard and send it as signing_secret. The replaced
     * secret is still accepted for 24 hours (previous_secret_expires_at).
     *
     * <p>Requires the integrations scope. Not idempotent: a retry after a lost answer rotates again.
     *
     * @param integrationId Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another workspace's id, answers 404.
     * @param body POST /v1/integrations/{integration_id}/rotate body. For an Auth0 integration send {}: NoResponse generates a new shared secret and returns it once. For a WorkOS or Supabase integration send the new signing_secret after regenerating it in the provider's dashboard. Either way the previous secret is still accepted for 24 hours, so the provider can be switched over without a refused call.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RotateIntegrationSecretResponse rotate(
            @Nonnull String integrationId, @Nonnull IntegrationRotationRequest body) {
        RotateIntegrationSecretRequest request = new RotateIntegrationSecretRequest(integrationId, body);
        RequestOperation<RotateIntegrationSecretRequest, RotateIntegrationSecretResponse> operation =
                new RotateIntegrationSecret.Sync(sdkConfiguration, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
