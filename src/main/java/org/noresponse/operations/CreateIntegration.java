package org.noresponse.operations;

import static org.noresponse.operations.Operations.AsyncRequestOperation;
import static org.noresponse.operations.Operations.RequestOperation;
import static org.noresponse.utils.Exceptions.unchecked;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.io.InputStream;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import org.noresponse.SDKConfiguration;
import org.noresponse.SecuritySource;
import org.noresponse.models.errors.APIStatusException;
import org.noresponse.models.errors.AuthenticationException;
import org.noresponse.models.errors.BadRequestException;
import org.noresponse.models.errors.ConflictException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.errors.PermissionDeniedException;
import org.noresponse.models.errors.RateLimitException;
import org.noresponse.models.operations.CreateIntegrationResponse;
import org.noresponse.models.shared.Integration;
import org.noresponse.models.shared.IntegrationRequest;
import org.noresponse.utils.Blob;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.HTTPRequest;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Hook.AfterErrorContextImpl;
import org.noresponse.utils.Hook.AfterSuccessContextImpl;
import org.noresponse.utils.Hook.BeforeRequestContextImpl;
import org.noresponse.utils.SerializedBody;
import org.noresponse.utils.Utils;
import org.noresponse.utils.Utils.JsonShape;

public class CreateIntegration {

    abstract static class Base {
        final SDKConfiguration sdkConfiguration;
        final String baseUrl;
        final SecuritySource securitySource;
        final HTTPClient client;
        final Headers _headers;

        public Base(@Nonnull SDKConfiguration sdkConfiguration, Headers _headers) {
            this.sdkConfiguration = sdkConfiguration;
            this._headers = _headers;
            this.baseUrl = this.sdkConfiguration.serverUrl();
            this.securitySource = this.sdkConfiguration.securitySource();
            this.client = this.sdkConfiguration.client();
        }

        Optional<SecuritySource> securitySource() {
            return Optional.ofNullable(this.securitySource);
        }

        BeforeRequestContextImpl createBeforeRequestContext() {
            return new BeforeRequestContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "createIntegration",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterSuccessContextImpl createAfterSuccessContext() {
            return new AfterSuccessContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "createIntegration",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterErrorContextImpl createAfterErrorContext() {
            return new AfterErrorContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "createIntegration",
                    java.util.Optional.empty(),
                    securitySource());
        }

        <T, U> HttpRequest buildRequest(T request, TypeReference<U> typeReference) throws Exception {
            String url = Utils.generateURL(this.baseUrl, "/v1/integrations");
            HTTPRequest req = new HTTPRequest(url, "POST");
            Object convertedRequest = Utils.convertToShape(request, JsonShape.DEFAULT, typeReference);
            SerializedBody serializedRequestBody = Utils.serializeRequestBody(convertedRequest, "", "json", false);
            if (serializedRequestBody == null) {
                throw new IllegalArgumentException("Request body is required");
            }
            req.setBody(Optional.ofNullable(serializedRequestBody));
            req.addHeader("Accept", "application/json").addHeader("user-agent", SDKConfiguration.USER_AGENT);
            _headers.forEach((k, list) -> list.forEach(v -> req.addHeader(k, v)));
            Utils.configureSecurity(req, this.sdkConfiguration.securitySource().getSecurity());

            return req.build();
        }
    }

    public static class Sync extends Base implements RequestOperation<IntegrationRequest, CreateIntegrationResponse> {
        public Sync(@Nonnull SDKConfiguration sdkConfiguration, Headers _headers) {
            super(sdkConfiguration, _headers);
        }

        private HttpRequest onBuildRequest(IntegrationRequest request) throws Exception {
            HttpRequest req = buildRequest(request, new TypeReference<IntegrationRequest>() {});
            return sdkConfiguration.hooks().beforeRequest(createBeforeRequestContext(), req);
        }

        private HttpResponse<InputStream> onError(HttpResponse<InputStream> response, Exception error)
                throws Exception {
            return sdkConfiguration
                    .hooks()
                    .afterError(createAfterErrorContext(), Optional.ofNullable(response), Optional.ofNullable(error));
        }

        private HttpResponse<InputStream> onSuccess(HttpResponse<InputStream> response) throws Exception {
            return sdkConfiguration.hooks().afterSuccess(createAfterSuccessContext(), response);
        }

        @Override
        public HttpResponse<InputStream> doRequest(IntegrationRequest request) {
            HttpRequest r = unchecked(() -> onBuildRequest(request)).get();
            HttpResponse<InputStream> httpRes;
            try {
                httpRes = client.send(r);
                if (Utils.statusCodeMatches(httpRes.statusCode(), "4XX", "5XX")) {
                    httpRes = onError(httpRes, null);
                } else {
                    httpRes = onSuccess(httpRes);
                }
            } catch (Exception e) {
                httpRes = unchecked(() -> onError(null, e)).get();
            }

            return httpRes;
        }

        @Override
        public CreateIntegrationResponse handleResponse(HttpResponse<InputStream> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            CreateIntegrationResponse.Builder resBuilder = CreateIntegrationResponse.builder()
                    .contentType(contentType)
                    .statusCode(response.statusCode())
                    .rawResponse(response);

            CreateIntegrationResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "201")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/json")) {
                    return res.withIntegration(Utils.unmarshal(response, new TypeReference<Integration>() {}));
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "400")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw BadRequestException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "401")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw AuthenticationException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "403")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw PermissionDeniedException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "404")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw NotFoundException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "409")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw ConflictException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "429")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    throw RateLimitException.from(response);
                } else {
                    throw APIStatusException.from("Unexpected content-type received: " + contentType, response);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "4XX")) {
                // no content
                throw APIStatusException.from("API error occurred", response);
            }
            if (Utils.statusCodeMatches(response.statusCode(), "5XX")) {
                // no content
                throw APIStatusException.from("API error occurred", response);
            }
            throw APIStatusException.from("Unexpected status code received: " + response.statusCode(), response);
        }
    }

    public static class Async extends Base
            implements AsyncRequestOperation<
                    IntegrationRequest, org.noresponse.models.operations.async.CreateIntegrationResponse> {

        public Async(@Nonnull SDKConfiguration sdkConfiguration, Headers _headers) {
            super(sdkConfiguration, _headers);
        }

        private CompletableFuture<HttpRequest> onBuildRequest(IntegrationRequest request) throws Exception {
            HttpRequest req = buildRequest(request, new TypeReference<IntegrationRequest>() {});
            return this.sdkConfiguration.asyncHooks().beforeRequest(createBeforeRequestContext(), req);
        }

        private CompletableFuture<HttpResponse<Blob>> onError(HttpResponse<Blob> response, Throwable error) {
            return this.sdkConfiguration.asyncHooks().afterError(createAfterErrorContext(), response, error);
        }

        private CompletableFuture<HttpResponse<Blob>> onSuccess(HttpResponse<Blob> response) {
            return this.sdkConfiguration.asyncHooks().afterSuccess(createAfterSuccessContext(), response);
        }

        @Override
        public CompletableFuture<HttpResponse<Blob>> doRequest(IntegrationRequest request) {
            return unchecked(() -> onBuildRequest(request))
                    .get()
                    .thenCompose(client::sendAsync)
                    .handle((resp, err) -> {
                        if (err != null) {
                            return onError(null, err);
                        }
                        if (Utils.statusCodeMatches(resp.statusCode(), "4XX", "5XX")) {
                            return onError(resp, null);
                        }
                        return CompletableFuture.completedFuture(resp);
                    })
                    .thenCompose(Function.identity())
                    .thenCompose(this::onSuccess);
        }

        @Override
        public CompletableFuture<org.noresponse.models.operations.async.CreateIntegrationResponse> handleResponse(
                HttpResponse<Blob> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            org.noresponse.models.operations.async.CreateIntegrationResponse.Builder resBuilder =
                    org.noresponse.models.operations.async.CreateIntegrationResponse.builder()
                            .contentType(contentType)
                            .statusCode(response.statusCode())
                            .rawResponse(response);

            org.noresponse.models.operations.async.CreateIntegrationResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "201")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/json")) {
                    return Utils.unmarshalAsync(response, new TypeReference<Integration>() {})
                            .thenApply(res::withIntegration);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "400")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return BadRequestException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "401")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return AuthenticationException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "403")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return PermissionDeniedException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "404")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return NotFoundException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "409")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return ConflictException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "429")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/problem+json")) {
                    return RateLimitException.fromAsync(response).thenCompose(CompletableFuture::failedFuture);
                } else {
                    return Utils.createAsyncApiError(response, "Unexpected content-type received: " + contentType);
                }
            }
            if (Utils.statusCodeMatches(response.statusCode(), "4XX")) {
                // no content
                return Utils.createAsyncApiError(response, "API error occurred");
            }
            if (Utils.statusCodeMatches(response.statusCode(), "5XX")) {
                // no content
                return Utils.createAsyncApiError(response, "API error occurred");
            }
            return Utils.createAsyncApiError(response, "Unexpected status code received: " + response.statusCode());
        }
    }
}
