package org.noresponse.operations;

import static org.noresponse.operations.Operations.AsyncRequestOperation;
import static org.noresponse.operations.Operations.RequestOperation;
import static org.noresponse.utils.Exceptions.unchecked;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
import org.noresponse.models.operations.DeleteReviewViewRequest;
import org.noresponse.models.operations.DeleteReviewViewResponse;
import org.noresponse.utils.AsyncRetries;
import org.noresponse.utils.BackoffStrategy;
import org.noresponse.utils.Blob;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.HTTPRequest;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Hook.AfterErrorContextImpl;
import org.noresponse.utils.Hook.AfterSuccessContextImpl;
import org.noresponse.utils.Hook.BeforeRequestContextImpl;
import org.noresponse.utils.NonRetryableException;
import org.noresponse.utils.Options;
import org.noresponse.utils.Retries;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.SerializedBody;
import org.noresponse.utils.Utils;
import org.noresponse.utils.Utils.JsonShape;

public class DeleteReviewView {

    abstract static class Base {
        final SDKConfiguration sdkConfiguration;
        final String baseUrl;
        final SecuritySource securitySource;
        final List<String> retryStatusCodes;
        final RetryConfig retryConfig;
        final HTTPClient client;
        final Headers _headers;

        public Base(@Nonnull SDKConfiguration sdkConfiguration, @Nullable Options options, Headers _headers) {
            this.sdkConfiguration = sdkConfiguration;
            this._headers = _headers;
            this.baseUrl = this.sdkConfiguration.serverUrl();
            this.securitySource = this.sdkConfiguration.securitySource();
            Optional.ofNullable(options).ifPresent(o -> o.validate(List.of(Options.Option.RETRY_CONFIG)));
            this.retryStatusCodes = List.of("408", "429", "5XX");
            this.retryConfig = Optional.ofNullable(options)
                    .flatMap(Options::retryConfig)
                    .or(sdkConfiguration::retryConfig)
                    .orElse(RetryConfig.builder()
                            .backoff(BackoffStrategy.builder()
                                    .initialInterval(500, TimeUnit.MILLISECONDS)
                                    .maxInterval(8000, TimeUnit.MILLISECONDS)
                                    .baseFactor((double) (2))
                                    .maxElapsedTime(30000, TimeUnit.MILLISECONDS)
                                    .retryConnectError(true)
                                    .build())
                            .build());
            this.client = this.sdkConfiguration.client();
        }

        Optional<SecuritySource> securitySource() {
            return Optional.ofNullable(this.securitySource);
        }

        BeforeRequestContextImpl createBeforeRequestContext() {
            return new BeforeRequestContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "deleteReviewView",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterSuccessContextImpl createAfterSuccessContext() {
            return new AfterSuccessContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "deleteReviewView",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterErrorContextImpl createAfterErrorContext() {
            return new AfterErrorContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "deleteReviewView",
                    java.util.Optional.empty(),
                    securitySource());
        }

        <T, U> HttpRequest buildRequest(T request, Class<T> klass, TypeReference<U> typeReference) throws Exception {
            String url = Utils.generateURL(
                    klass,
                    this.baseUrl,
                    "/v1/workspaces/{workspace_id}/review_views/{environment}/{view_id}",
                    request,
                    null);
            HTTPRequest req = new HTTPRequest(url, "DELETE");
            Object convertedRequest = Utils.convertToShape(request, JsonShape.DEFAULT, typeReference);
            SerializedBody serializedRequestBody = Utils.serializeRequestBody(convertedRequest, "body", "json", false);
            if (serializedRequestBody == null) {
                throw new IllegalArgumentException("Request body is required");
            }
            req.setBody(Optional.ofNullable(serializedRequestBody));
            req.addHeader("Accept", "application/problem+json").addHeader("user-agent", SDKConfiguration.USER_AGENT);
            _headers.forEach((k, list) -> list.forEach(v -> req.addHeader(k, v)));
            Utils.configureSecurity(req, this.sdkConfiguration.securitySource().getSecurity());

            return req.build();
        }
    }

    public static class Sync extends Base
            implements RequestOperation<DeleteReviewViewRequest, DeleteReviewViewResponse> {
        public Sync(@Nonnull SDKConfiguration sdkConfiguration, @Nullable Options options, Headers _headers) {
            super(sdkConfiguration, options, _headers);
        }

        private HttpRequest onBuildRequest(DeleteReviewViewRequest request) throws Exception {
            HttpRequest req = buildRequest(
                    request, DeleteReviewViewRequest.class, new TypeReference<DeleteReviewViewRequest>() {});
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
        public HttpResponse<InputStream> doRequest(DeleteReviewViewRequest request) {
            Retries retries = Retries.builder()
                    .action(() -> {
                        HttpRequest r;
                        try {
                            r = onBuildRequest(request);
                        } catch (Exception e) {
                            throw new NonRetryableException(e);
                        }
                        try {
                            HttpResponse<InputStream> httpRes = client.send(r);
                            if (Utils.statusCodeMatches(httpRes.statusCode(), "4XX", "5XX")) {
                                return onError(httpRes, null);
                            }
                            return httpRes;
                        } catch (Exception e) {
                            return onError(null, e);
                        }
                    })
                    .retryConfig(retryConfig)
                    .statusCodes(retryStatusCodes)
                    .build();
            return unchecked(() -> onSuccess(retries.run())).get();
        }

        @Override
        public DeleteReviewViewResponse handleResponse(HttpResponse<InputStream> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            DeleteReviewViewResponse.Builder resBuilder = DeleteReviewViewResponse.builder()
                    .contentType(contentType)
                    .statusCode(response.statusCode())
                    .rawResponse(response);

            DeleteReviewViewResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "204")) {
                res.withHeaders(response.headers().map());
                // no content
                return res;
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
                    DeleteReviewViewRequest, org.noresponse.models.operations.async.DeleteReviewViewResponse> {
        private final ScheduledExecutorService retryScheduler;

        public Async(
                @Nonnull SDKConfiguration sdkConfiguration,
                @Nullable Options options,
                @Nullable ScheduledExecutorService retryScheduler,
                Headers _headers) {
            super(sdkConfiguration, options, _headers);
            this.retryScheduler = retryScheduler;
        }

        private CompletableFuture<HttpRequest> onBuildRequest(DeleteReviewViewRequest request) throws Exception {
            HttpRequest req = buildRequest(
                    request, DeleteReviewViewRequest.class, new TypeReference<DeleteReviewViewRequest>() {});
            return this.sdkConfiguration.asyncHooks().beforeRequest(createBeforeRequestContext(), req);
        }

        private CompletableFuture<HttpResponse<Blob>> onError(HttpResponse<Blob> response, Throwable error) {
            return this.sdkConfiguration.asyncHooks().afterError(createAfterErrorContext(), response, error);
        }

        private CompletableFuture<HttpResponse<Blob>> onSuccess(HttpResponse<Blob> response) {
            return this.sdkConfiguration.asyncHooks().afterSuccess(createAfterSuccessContext(), response);
        }

        @Override
        public CompletableFuture<HttpResponse<Blob>> doRequest(DeleteReviewViewRequest request) {
            AsyncRetries retries = AsyncRetries.builder()
                    .retryConfig(retryConfig)
                    .statusCodes(retryStatusCodes)
                    .scheduler(retryScheduler)
                    .build();
            return retries.retry(() -> unchecked(() -> onBuildRequest(request))
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
                    .thenCompose(Function.identity()))
                    .thenCompose(this::onSuccess);
        }

        @Override
        public CompletableFuture<org.noresponse.models.operations.async.DeleteReviewViewResponse> handleResponse(
                HttpResponse<Blob> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            org.noresponse.models.operations.async.DeleteReviewViewResponse.Builder resBuilder =
                    org.noresponse.models.operations.async.DeleteReviewViewResponse.builder()
                            .contentType(contentType)
                            .statusCode(response.statusCode())
                            .rawResponse(response);

            org.noresponse.models.operations.async.DeleteReviewViewResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "204")) {
                res.withHeaders(response.headers().map());
                // no content
                return CompletableFuture.completedFuture(res);
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
