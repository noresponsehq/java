package org.noresponse.models.errors;

import jakarta.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import org.noresponse.utils.Utils;

/**
 * Thrown by a service call when an error response occurs. Contains details about the response.
 */
@SuppressWarnings("serial")
public class APIStatusException extends NoResponseException {

    public APIStatusException(
            String message,
            int code,
            @Nullable byte[] body,
            HttpResponse<InputStream> rawResponse,
            @Nullable Throwable cause) {
        super(message, code, body, rawResponse, cause);
    }

    public static APIStatusException from(String message, HttpResponse<InputStream> rawResponse) {
        return from(message, rawResponse, null);
    }

    public static APIStatusException from(
            String message, HttpResponse<InputStream> rawResponse, @Nullable Throwable cause) {
        try {
            return new APIStatusException(
                    message, rawResponse.statusCode(), Utils.extractByteArrayFromBody(rawResponse), rawResponse, cause);
        } catch (IOException e) {
            // Gracefully handle IOExceptions that occur while reading the body
            // by returning an error without a body.
            return new APIStatusException(message, rawResponse.statusCode(), null, rawResponse, cause);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public HttpResponse<InputStream> rawResponse() {
        return (HttpResponse<InputStream>) super.rawResponse();
    }
}
