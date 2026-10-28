package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.ListRequestDiagnosticsRequest;
import org.noresponse.models.operations.async.ListRequestDiagnosticsRequestBuilder;
import org.noresponse.models.operations.async.ListRequestDiagnosticsResponse;
import org.noresponse.operations.ListRequestDiagnostics;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

public class AsyncRequests {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Requests syncSDK;

    AsyncRequests(Requests syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Requests sync() {
        return syncSDK;
    }

    /**
     * Search request diagnostics
     *
     * <p>Search authenticated request attempts by time, route, status or request correlation id. The interval
     * is at most seven days. Each attempt has a unique id and may include the exact served decision.
     *
     * <p>Records are bounded best effort and exclude request bodies, secrets and source payloads. A repeated
     * correlation id does not overwrite another attempt.
     *
     * @return The async call builder
     */
    public ListRequestDiagnosticsRequestBuilder list() {
        return new ListRequestDiagnosticsRequestBuilder(sdkConfiguration);
    }

    /**
     * Search request diagnostics
     *
     * <p>Search authenticated request attempts by time, route, status or request correlation id. The interval
     * is at most seven days. Each attempt has a unique id and may include the exact served decision.
     *
     * <p>Records are bounded best effort and exclude request bodies, secrets and source payloads. A repeated
     * correlation id does not overwrite another attempt.
     *
     * @param request The request object containing all the parameters for the API call.
     * @return {@code CompletableFuture<ListRequestDiagnosticsResponse>} - The async response
     */
    public CompletableFuture<ListRequestDiagnosticsResponse> list(@Nonnull ListRequestDiagnosticsRequest request) {
        return list(request, null);
    }

    /**
     * Search request diagnostics
     *
     * <p>Search authenticated request attempts by time, route, status or request correlation id. The interval
     * is at most seven days. Each attempt has a unique id and may include the exact served decision.
     *
     * <p>Records are bounded best effort and exclude request bodies, secrets and source payloads. A repeated
     * correlation id does not overwrite another attempt.
     *
     * @param request The request object containing all the parameters for the API call.
     * @param options additional options
     * @return {@code CompletableFuture<ListRequestDiagnosticsResponse>} - The async response
     */
    public CompletableFuture<ListRequestDiagnosticsResponse> list(
            @Nonnull ListRequestDiagnosticsRequest request, @Nullable Options options) {
        AsyncRequestOperation<ListRequestDiagnosticsRequest, ListRequestDiagnosticsResponse> operation =
                new ListRequestDiagnostics.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
