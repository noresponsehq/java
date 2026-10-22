package org.noresponse.operations;

import static org.noresponse.operations.Operations.AsyncRequestlessOperation;
import static org.noresponse.operations.Operations.RequestlessOperation;
import static org.noresponse.utils.Exceptions.unchecked;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Exception;
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
import org.noresponse.models.operations.ListWebhookEndpointsResponse;
import org.noresponse.models.shared.WebhookEndpointList;
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
import org.noresponse.utils.Utils;

public class ListWebhookEndpoints {

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
                    "listWebhookEndpoints",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterSuccessContextImpl createAfterSuccessContext() {
            return new AfterSuccessContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "listWebhookEndpoints",
                    java.util.Optional.empty(),
                    securitySource());
        }

        AfterErrorContextImpl createAfterErrorContext() {
            return new AfterErrorContextImpl(
                    this.sdkConfiguration,
                    this.baseUrl,
                    "listWebhookEndpoints",
                    java.util.Optional.empty(),
                    securitySource());
        }

        HttpRequest buildRequest() throws Exception {
            String url = Utils.generateURL(this.baseUrl, "/v1/webhooks");
            HTTPRequest req = new HTTPRequest(url, "GET");
            req.addHeader("Accept", "application/json").addHeader("user-agent", SDKConfiguration.USER_AGENT);
            _headers.forEach((k, list) -> list.forEach(v -> req.addHeader(k, v)));
            Utils.configureSecurity(req, this.sdkConfiguration.securitySource().getSecurity());

            return req.build();
        }
    }

    public static class Sync extends Base implements RequestlessOperation<ListWebhookEndpointsResponse> {
        public Sync(@Nonnull SDKConfiguration sdkConfiguration, @Nullable Options options, Headers _headers) {
            super(sdkConfiguration, options, _headers);
        }

        private HttpRequest onBuildRequest() throws Exception {
            HttpRequest req = buildRequest();
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
        public HttpResponse<InputStream> doRequest() {
            Retries retries = Retries.builder()
                    .action(() -> {
                        HttpRequest r;
                        try {
                            r = onBuildRequest();
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
        public ListWebhookEndpointsResponse handleResponse(HttpResponse<InputStream> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            ListWebhookEndpointsResponse.Builder resBuilder = ListWebhookEndpointsResponse.builder()
                    .contentType(contentType)
                    .statusCode(response.statusCode())
                    .rawResponse(response);

            ListWebhookEndpointsResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "200")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/json")) {
                    return res.withWebhookEndpointList(
                            Utils.unmarshal(response, new TypeReference<WebhookEndpointList>() {}));
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
            implements AsyncRequestlessOperation<org.noresponse.models.operations.async.ListWebhookEndpointsResponse> {
        private final ScheduledExecutorService retryScheduler;

        public Async(
                @Nonnull SDKConfiguration sdkConfiguration,
                @Nullable Options options,
                @Nullable ScheduledExecutorService retryScheduler,
                Headers _headers) {
            super(sdkConfiguration, options, _headers);
            this.retryScheduler = retryScheduler;
        }

        private CompletableFuture<HttpRequest> onBuildRequest() throws Exception {
            HttpRequest req = buildRequest();
            return this.sdkConfiguration.asyncHooks().beforeRequest(createBeforeRequestContext(), req);
        }

        private CompletableFuture<HttpResponse<Blob>> onError(HttpResponse<Blob> response, Throwable error) {
            return this.sdkConfiguration.asyncHooks().afterError(createAfterErrorContext(), response, error);
        }

        private CompletableFuture<HttpResponse<Blob>> onSuccess(HttpResponse<Blob> response) {
            return this.sdkConfiguration.asyncHooks().afterSuccess(createAfterSuccessContext(), response);
        }

        @Override
        public CompletableFuture<HttpResponse<Blob>> doRequest() {
            AsyncRetries retries = AsyncRetries.builder()
                    .retryConfig(retryConfig)
                    .statusCodes(retryStatusCodes)
                    .scheduler(retryScheduler)
                    .build();
            return retries.retry(() -> unchecked(() -> onBuildRequest())
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
        public CompletableFuture<org.noresponse.models.operations.async.ListWebhookEndpointsResponse> handleResponse(
                HttpResponse<Blob> response) {
            String contentType = response.headers().firstValue("Content-Type").orElse("application/octet-stream");
            org.noresponse.models.operations.async.ListWebhookEndpointsResponse.Builder resBuilder =
                    org.noresponse.models.operations.async.ListWebhookEndpointsResponse.builder()
                            .contentType(contentType)
                            .statusCode(response.statusCode())
                            .rawResponse(response);

            org.noresponse.models.operations.async.ListWebhookEndpointsResponse res = resBuilder.build();

            if (Utils.statusCodeMatches(response.statusCode(), "200")) {
                res.withHeaders(response.headers().map());
                if (Utils.contentTypeMatches(contentType, "application/json")) {
                    return Utils.unmarshalAsync(response, new TypeReference<WebhookEndpointList>() {})
                            .thenApply(res::withWebhookEndpointList);
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
