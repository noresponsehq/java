package org.noresponse.models.operations.async;

import static org.noresponse.operations.Operations.AsyncRequestOperation;
import static org.noresponse.utils.Exceptions.unchecked;
import static org.noresponse.utils.reactive.ReactiveUtils.mapAsync;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.operations.ListAccountCredentialsRequest;
import org.noresponse.operations.ListAccountCredentials;
import org.noresponse.utils.Blob;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;
import org.noresponse.utils.pagination.AsyncPaginator;
import org.noresponse.utils.pagination.CursorTracker;
import org.reactivestreams.FlowAdapters;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class ListAccountCredentialsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListAccountCredentialsRequest.Builder pojoBuilder;
    private ListAccountCredentialsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListAccountCredentialsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListAccountCredentialsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListAccountCredentialsRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public ListAccountCredentialsRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListAccountCredentialsRequestBuilder cursor(@Nullable String cursor) {
        this.pojoBuilder.cursor(cursor);
        this._setterCalled = true;
        return this;
    }

    public ListAccountCredentialsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListAccountCredentialsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListAccountCredentialsRequestBuilder header(String name, String value) {
        Utils.checkNotNull(name, "name");
        Utils.checkNotNull(value, "value");
        this._headers.add(name, value);
        return this;
    }

    /**
     * Executes the request and returns the response.
     *
     * @return The response from the server.
     */
    public CompletableFuture<ListAccountCredentialsResponse> call() {
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListAccountCredentialsRequest, ListAccountCredentialsResponse> operation =
                new ListAccountCredentials.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);
        return operation.doRequest(this._buildRequest()).thenCompose(operation::handleResponse);
    }

    /**
     * Returns a {@link Publisher} that performs next page calls till no more pages
     * are returned.
     *
     * <p>The returned {@link Publisher} can be used with reactive frameworks:
     * <pre><code>
     * Publisher&lt;ListAccountCredentialsResponse&gt; publisher = builder.callAsPublisher();
     * publisher.subscribe(new Subscriber&lt;ListAccountCredentialsResponse&gt;() {
     *     // Handle onNext, onError, onComplete
     * });
     * </code></pre>
     *
     * @return A {@link Publisher} that emits pages asynchronously
     */
    public Publisher<ListAccountCredentialsResponse> callAsPublisher() {
        ListAccountCredentialsRequest request = pojoBuilder.build();
        Options options = optionsBuilder.build();
        AsyncRequestOperation<ListAccountCredentialsRequest, ListAccountCredentialsResponse> operation =
                new ListAccountCredentials.Async(sdkConfiguration, options, sdkConfiguration.retryScheduler(), _headers);

        Flow.Publisher<HttpResponse<Blob>> asyncPaginator =
                new AsyncPaginator<>(request, new CursorTracker<>("$.next_cursor", String.class), (req, pos) -> {
                    var modifiedReq = pos == null ? req : req.withCursor(pos);
                    return operation.doRequest(modifiedReq);
                });

        Flow.Publisher<ListAccountCredentialsResponse> flowPublisher =
                mapAsync(asyncPaginator, operation::handleResponse);

        // Convert Flow.Publisher to Reactive Streams Publisher at the last stage
        return FlowAdapters.toPublisher(flowPublisher);
    }
}
