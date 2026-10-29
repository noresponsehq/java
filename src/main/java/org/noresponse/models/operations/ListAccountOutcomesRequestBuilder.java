package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;
import static org.noresponse.utils.Exceptions.unchecked;
import static org.noresponse.utils.Utils.toStream;
import static org.noresponse.utils.Utils.transform;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Iterable;
import java.lang.Long;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.stream.Stream;
import org.noresponse.SDKConfiguration;
import org.noresponse.operations.ListAccountOutcomes;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;
import org.noresponse.utils.pagination.CursorTracker;
import org.noresponse.utils.pagination.Paginator;

public class ListAccountOutcomesRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListAccountOutcomesRequest.Builder pojoBuilder;
    private ListAccountOutcomesRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListAccountOutcomesRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListAccountOutcomesRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListAccountOutcomesRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public ListAccountOutcomesRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListAccountOutcomesRequestBuilder cursor(@Nullable String cursor) {
        this.pojoBuilder.cursor(cursor);
        this._setterCalled = true;
        return this;
    }

    public ListAccountOutcomesRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListAccountOutcomesRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListAccountOutcomesRequestBuilder header(String name, String value) {
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
    public ListAccountOutcomesResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListAccountOutcomesRequest, ListAccountOutcomesResponse> operation =
                new ListAccountOutcomes.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }

    /**
     * Returns an iterable that performs next page calls till no more pages
     * are returned.
     *
     * <p>The returned iterable can be used in a for-each loop:
     * <pre><code>
     * for (ListAccountOutcomesResponse page : builder.callAsIterable()) {
     *     // Process each page
     * }
     * </code></pre>
     *
     * @return An iterable that can be used to iterate through all pages
     */
    public Iterable<ListAccountOutcomesResponse> callAsIterable() {
        ListAccountOutcomesRequest request = pojoBuilder.build();
        Options options = optionsBuilder.build();
        RequestOperation<ListAccountOutcomesRequest, ListAccountOutcomesResponse> operation =
                new ListAccountOutcomes.Sync(sdkConfiguration, options, _headers);

        Iterator<HttpResponse<InputStream>> iterator =
                new Paginator<>(request, new CursorTracker<>("$.next_cursor", String.class), (req, pos) -> {
                    var modifiedReq = pos == null ? req : req.withCursor(pos);
                    return unchecked(() -> operation.doRequest(modifiedReq)).get();
                });

        return () -> transform(iterator, operation::handleResponse);
    }

    /**
     * Returns a stream that performs next page calls till no more pages
     * are returned.
     **/
    public Stream<ListAccountOutcomesResponse> callAsStream() {
        return toStream(callAsIterable());
    }
}
