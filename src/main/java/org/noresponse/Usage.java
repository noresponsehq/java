package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.GetUsageRequest;
import org.noresponse.models.operations.GetUsageRequestBuilder;
import org.noresponse.models.operations.GetUsageResponse;
import org.noresponse.operations.GetUsage;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Recorded usage for your environment.
 */
public class Usage {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncUsage asyncSDK;

    Usage(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncUsage(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncUsage async() {
        return asyncSDK;
    }

    /**
     * Read a workspace's usage.
     *
     * <p>Read daily usage units for the environments you can access. These counts are unpriced.
     *
     * @return The call builder
     */
    public GetUsageRequestBuilder get() {
        return new GetUsageRequestBuilder(sdkConfiguration);
    }

    /**
     * Read a workspace's usage.
     *
     * <p>Read daily usage units for the environments you can access. These counts are unpriced.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetUsageResponse get(@Nonnull String workspaceId) {
        return get(workspaceId, null, null, null);
    }

    /**
     * Read a workspace's usage.
     *
     * <p>Read daily usage units for the environments you can access. These counts are unpriced.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param from The first UTC day, as YYYY-MM-DD.
     * @param to The last UTC day, included, as YYYY-MM-DD; no later than today.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetUsageResponse get(
            @Nonnull String workspaceId, @Nullable String from, @Nullable String to, @Nullable Options options) {
        GetUsageRequest request = new GetUsageRequest(workspaceId, from, to);
        RequestOperation<GetUsageRequest, GetUsageResponse> operation =
                new GetUsage.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
