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
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.operations.ListAccountAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;
import org.noresponse.utils.pagination.CursorTracker;
import org.noresponse.utils.pagination.Paginator;

public class ListAccountAssessmentsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private final ListAccountAssessmentsRequest.Builder pojoBuilder;
    private ListAccountAssessmentsRequest request;
    private final Options.Builder optionsBuilder;
    private boolean _setterCalled;

    public ListAccountAssessmentsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.pojoBuilder = ListAccountAssessmentsRequest.builder();
        this.optionsBuilder = Options.builder();
    }

    public ListAccountAssessmentsRequestBuilder accountId(@Nonnull String accountId) {
        this.pojoBuilder.accountId(accountId);
        this._setterCalled = true;
        return this;
    }

    public ListAccountAssessmentsRequestBuilder limit(@Nullable Long limit) {
        this.pojoBuilder.limit(limit);
        this._setterCalled = true;
        return this;
    }

    public ListAccountAssessmentsRequestBuilder cursor(@Nullable String cursor) {
        this.pojoBuilder.cursor(cursor);
        this._setterCalled = true;
        return this;
    }

    public ListAccountAssessmentsRequestBuilder responseMode(@Nullable ResponseMode responseMode) {
        this.pojoBuilder.responseMode(responseMode);
        this._setterCalled = true;
        return this;
    }

    public ListAccountAssessmentsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    private ListAccountAssessmentsRequest _buildRequest() {
        if (this._setterCalled) {
            this.request = this.pojoBuilder.build();
        }
        return this.request;
    }

    public ListAccountAssessmentsRequestBuilder header(String name, String value) {
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
    public ListAccountAssessmentsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<ListAccountAssessmentsRequest, ListAccountAssessmentsResponse> operation =
                new ListAccountAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }

    /**
     * Returns an iterable that performs next page calls till no more pages
     * are returned.
     *
     * <p>The returned iterable can be used in a for-each loop:
     * <pre><code>
     * for (ListAccountAssessmentsResponse page : builder.callAsIterable()) {
     *     // Process each page
     * }
     * </code></pre>
     *
     * @return An iterable that can be used to iterate through all pages
     */
    public Iterable<ListAccountAssessmentsResponse> callAsIterable() {
        ListAccountAssessmentsRequest request = pojoBuilder.build();
        Options options = optionsBuilder.build();
        RequestOperation<ListAccountAssessmentsRequest, ListAccountAssessmentsResponse> operation =
                new ListAccountAssessments.Sync(sdkConfiguration, options, _headers);

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
    public Stream<ListAccountAssessmentsResponse> callAsStream() {
        return toStream(callAsIterable());
    }
}
