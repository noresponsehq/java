package org.noresponse.utils;

import java.net.http.HttpResponse;
import org.noresponse.utils.Blob;

public final class AsyncRetryableException extends Exception {
    private final HttpResponse<Blob> response;

    public AsyncRetryableException(HttpResponse<Blob> response) {
        this.response = response;
    }

    public HttpResponse<Blob> response() {
        return response;
    }
}
