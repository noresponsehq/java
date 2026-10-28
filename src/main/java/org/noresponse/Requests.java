package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.noresponse.models.operations.ListRequestDiagnosticsRequest;
import org.noresponse.models.operations.ListRequestDiagnosticsRequestBuilder;
import org.noresponse.models.operations.ListRequestDiagnosticsResponse;
import org.noresponse.operations.ListRequestDiagnostics;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

public class Requests {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncRequests asyncSDK;

    Requests(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncRequests(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncRequests async() {
        return asyncSDK;
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
     * @return The call builder
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListRequestDiagnosticsResponse list(@Nonnull ListRequestDiagnosticsRequest request) {
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
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public ListRequestDiagnosticsResponse list(
            @Nonnull ListRequestDiagnosticsRequest request, @Nullable Options options) {
        RequestOperation<ListRequestDiagnosticsRequest, ListRequestDiagnosticsResponse> operation =
                new ListRequestDiagnostics.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
