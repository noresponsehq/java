package org.noresponse.models.operations;

import static org.noresponse.operations.Operations.RequestOperation;
import static org.noresponse.utils.Exceptions.unchecked;
import static org.noresponse.utils.Utils.toStream;
import static org.noresponse.utils.Utils.transform;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Iterable;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.stream.Stream;
import org.noresponse.SDKConfiguration;
import org.noresponse.models.shared.AssessmentQuery;
import org.noresponse.operations.QueryAccountAssessments;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;
import org.noresponse.utils.pagination.CursorTracker;
import org.noresponse.utils.pagination.Paginator;

public class QueryAccountAssessmentsRequestBuilder {
    private final SDKConfiguration sdkConfiguration;
    private final Headers _headers = new Headers();
    private AssessmentQuery request;
    private final Options.Builder optionsBuilder;

    public QueryAccountAssessmentsRequestBuilder(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.optionsBuilder = Options.builder();
    }

    public QueryAccountAssessmentsRequestBuilder retryConfig(RetryConfig retryConfig) {
        this.optionsBuilder.retryConfig(retryConfig);
        return this;
    }

    public QueryAccountAssessmentsRequestBuilder request(@Nonnull AssessmentQuery request) {
        this.request = Utils.checkNotNull(request, "request");
        return this;
    }

    private AssessmentQuery _buildRequest() {
        return this.request;
    }

    public QueryAccountAssessmentsRequestBuilder header(String name, String value) {
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
    public QueryAccountAssessmentsResponse call() {
        Options options = optionsBuilder.build();
        RequestOperation<AssessmentQuery, QueryAccountAssessmentsResponse> operation =
                new QueryAccountAssessments.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(this._buildRequest()));
    }

    /**
     * Returns an iterable that performs next page calls till no more pages
     * are returned.
     *
     * <p>The returned iterable can be used in a for-each loop:
     * <pre><code>
     * for (QueryAccountAssessmentsResponse page : builder.callAsIterable()) {
     *     // Process each page
     * }
     * </code></pre>
     *
     * @return An iterable that can be used to iterate through all pages
     */
    public Iterable<QueryAccountAssessmentsResponse> callAsIterable() {
        AssessmentQuery request = this.request;
        Options options = optionsBuilder.build();
        RequestOperation<AssessmentQuery, QueryAccountAssessmentsResponse> operation =
                new QueryAccountAssessments.Sync(sdkConfiguration, options, _headers);

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
    public Stream<QueryAccountAssessmentsResponse> callAsStream() {
        return toStream(callAsIterable());
    }
}
