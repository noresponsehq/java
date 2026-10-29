package org.noresponse;

import static org.noresponse.operations.Operations.AsyncRequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.operations.GetErasureReceiptRequest;
import org.noresponse.models.operations.async.GetErasureReceiptRequestBuilder;
import org.noresponse.models.operations.async.GetErasureReceiptResponse;
import org.noresponse.operations.GetErasureReceipt;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Durable deletion receipts.
 */
public class AsyncErasures {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final Erasures syncSDK;

    AsyncErasures(Erasures syncSDK, SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.syncSDK = syncSDK;
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public Erasures sync() {
        return syncSDK;
    }

    /**
     * Read an erasure receipt
     *
     * <p>Read retained erasure progress and downstream acknowledgements. Completion does not certify deletion
     * from backups.
     *
     * @return The async call builder
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
     * @return {@code CompletableFuture<GetErasureReceiptResponse>} - The async response
     */
    public CompletableFuture<GetErasureReceiptResponse> get(@Nonnull String erasureId) {
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
     * @return {@code CompletableFuture<GetErasureReceiptResponse>} - The async response
     */
    public CompletableFuture<GetErasureReceiptResponse> get(@Nonnull String erasureId, @Nullable Options options) {
        GetErasureReceiptRequest request = new GetErasureReceiptRequest(erasureId);
        AsyncRequestOperation<GetErasureReceiptRequest, GetErasureReceiptResponse> operation =
                new GetErasureReceipt.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(request).thenCompose(operation::handleResponse);
    }
}
