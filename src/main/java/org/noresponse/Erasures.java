package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.GetErasureReceiptRequest;
import org.noresponse.models.operations.GetErasureReceiptRequestBuilder;
import org.noresponse.models.operations.GetErasureReceiptResponse;
import org.noresponse.operations.GetErasureReceipt;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Durable deletion receipts.
 */
public class Erasures {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncErasures asyncSDK;

    Erasures(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncErasures(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncErasures async() {
        return asyncSDK;
    }

    /**
     * Read an erasure receipt
     *
     * <p>Read retained erasure progress and downstream acknowledgements. Completion does not certify deletion
     * from backups.
     *
     * @return The call builder
     */
    public GetErasureReceiptRequestBuilder get() {
        return new GetErasureReceiptRequestBuilder(sdkConfiguration);
    }

    /**
     * Read an erasure receipt
     *
     * <p>Read retained erasure progress and downstream acknowledgements. Completion does not certify deletion
     * from backups.
     *
     * @param erasureId
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetErasureReceiptResponse get(@Nonnull String erasureId) {
        return get(erasureId, null);
    }

    /**
     * Read an erasure receipt
     *
     * <p>Read retained erasure progress and downstream acknowledgements. Completion does not certify deletion
     * from backups.
     *
     * @param erasureId
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public GetErasureReceiptResponse get(@Nonnull String erasureId, @Nullable Options options) {
        GetErasureReceiptRequest request = new GetErasureReceiptRequest(erasureId);
        RequestOperation<GetErasureReceiptRequest, GetErasureReceiptResponse> operation =
                new GetErasureReceipt.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
