package org.noresponse.models.errors;

import jakarta.annotation.Nullable;
import java.net.http.HttpResponse;
import org.noresponse.utils.Blob;

/**
 * Thrown by an async service call when an error response occurs. Contains details about the response.
 * This is an unchecked exception suitable for use in CompletableFuture chains.
 */
@SuppressWarnings("serial")
public class AsyncAPIStatusException extends NoResponseException {

    public AsyncAPIStatusException(
            String message,
            int code,
            @Nullable byte[] body,
            HttpResponse<Blob> rawResponse,
            @Nullable Throwable cause) {
        super(message, code, body, rawResponse, cause);
    }

    @SuppressWarnings("unchecked")
    @Override
    public HttpResponse<Blob> rawResponse() {
        return (HttpResponse<Blob>) super.rawResponse();
    }
}
