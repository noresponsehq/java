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
import org.noresponse.operations.ListLinkedAccounts;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;
import org.noresponse.utils.pagination.CursorTracker;
import org.noresponse.utils.pagination.Paginator;

public class ListLinkedAccountsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListLinkedAccountsRequest.Builder pojoBuilder;
    private ListLinkedAccountsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListLinkedAccountsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListLinkedAccountsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListLinkedAccountsRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public ListLinkedAccountsRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListLinkedAccountsRequestBuilder cursor(@Nullable String cursor) {
        this.pojoBuilder.cursor(cursor);
        this._setterCalled = true;
        return this;
    }

    public ListLinkedAccountsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListLinkedAccountsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListLinkedAccountsRequestBuilder header(String name, String value) {
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
    public ListLinkedAccountsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListLinkedAccountsRequest, ListLinkedAccountsResponse> operation =
                new ListLinkedAccounts.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }

    /**
     * Returns an iterable that performs next page calls till no more pages
     * are returned.
     *
     * <p>The returned iterable can be used in a for-each loop:
     * <pre><code>
     * for (ListLinkedAccountsResponse page : builder.callAsIterable()) {
     *     // Process each page
     * }
     * </code></pre>
     *
     * @return An iterable that can be used to iterate through all pages
     */
    public Iterable<ListLinkedAccountsResponse> callAsIterable() {
        ListLinkedAccountsRequest request = pojoBuilder.build();
        Options options = optionsBuilder.build();
        RequestOperation<ListLinkedAccountsRequest, ListLinkedAccountsResponse> operation =
                new ListLinkedAccounts.Sync(sdkConfiguration, options, _headers);

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
    public Stream<ListLinkedAccountsResponse> callAsStream() {
        return toStream(callAsIterable());
    }
}
