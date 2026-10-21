package org.noresponse.utils;

import java.net.http.HttpResponse;
import org.noresponse.utils.Blob;

public interface AsyncResponse {

    /**
     * Returns the value of the Content-Type header.
     **/
    String contentType();

    /**
     * Returns the HTTP status code.
     **/
    int statusCode();

    /**
     * Returns the raw response.
     **/
    HttpResponse<Blob> rawResponse();
}
