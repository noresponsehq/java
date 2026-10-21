package org.noresponse.models.errors;

import jakarta.annotation.Nullable;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Utils;

@SuppressWarnings("serial")
public abstract class NoResponseException extends RuntimeException {

    private int code;
    private byte[] body;
    private HttpResponse<?> rawResponse;

    public NoResponseException(
            String message, int code, @Nullable byte[] body, HttpResponse<?> rawResponse, @Nullable Throwable cause) {
        super(message, cause);
        Utils.checkNotNull(message, "message");
        Utils.checkNotNull(rawResponse, "rawResponse");
        this.body = body;
        this.code = code;
        this.rawResponse = rawResponse;
    }

    public Optional<byte[]> body() {
        return Optional.ofNullable(body);
    }

    public Optional<String> bodyAsString() {
        return body().map(x -> new String(x, StandardCharsets.UTF_8));
    }

    public int code() {
        return code;
    }

    /**
     * Returns the raw HTTP response associated with this exception. The response body stream
     * may not be available (but the body can be accessed via the {@code body()} method).
     *
     * @return the raw HTTP response
     */
    public HttpResponse<?> rawResponse() {
        return rawResponse;
    }

    /**
     * Returns the headers from the raw HTTP response as a map.
     *
     * @return response headers
     */
    public Headers headers() {
        return new Headers(rawResponse.headers().map());
    }

    // present for backwards compatibility
    public String message() {
        return getMessage();
    }

    public NoResponseException withCode(int code) {
        this.code = code;
        return this;
    }

    public NoResponseException withBody(@Nullable byte[] body) {
        Utils.checkNotNull(body, "body");
        this.body = body;
        return this;
    }

    public NoResponseException withRawResponse(HttpResponse<?> rawResponse) {
        Utils.checkNotNull(rawResponse, "rawResponse");
        this.rawResponse = rawResponse;
        return this;
    }

    @Override
    public String toString() {
        return Utils.toString(
                this.getClass(),
                "requestMethod",
                rawResponse.request().method(),
                "requestUri",
                rawResponse.request().uri(),
                "code",
                code,
                "responseHeaders",
                rawResponse.headers().map(),
                "message",
                getMessage(),
                "body",
                bodyAsString().orElse("null"));
    }
}
