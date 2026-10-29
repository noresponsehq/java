package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.GetUsageRequest;
import org.noresponse.models.operations.async.GetUsageRequestBuilder;
import org.noresponse.models.operations.async.GetUsageResponse;
import org.noresponse.operations.GetUsage;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Recorded usage for your environment.
 */
public class AsyncUsage {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Usage syncSDK;

    AsyncUsage(Usage syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Usage sync() {
        return syncSDK;
    }

    /**
     * Read a workspace's usage.
     *
     * <p>Read daily usage units for the environments you can access. These counts are unpriced.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetUsageResponse>} - The async response
     */
    public CompletableFuture<GetUsageResponse> get(@Nonnull String workspaceId) {
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
     * @return {@code CompletableFuture<GetUsageResponse>} - The async response
     */
    public CompletableFuture<GetUsageResponse> get(
            @Nonnull String workspaceId, @Nullable String from, @Nullable String to, @Nullable Options options) {
        GetUsageRequest request = new GetUsageRequest(workspaceId, from, to);
        AsyncRequestOperation<GetUsageRequest, GetUsageResponse> operation =
                new GetUsage.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
