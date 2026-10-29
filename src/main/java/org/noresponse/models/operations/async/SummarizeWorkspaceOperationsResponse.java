package org.noresponse.models.operations.async;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Override;
import java.lang.String;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.noresponse.models.shared.WorkspaceOperationsSummary;
import org.noresponse.utils.AsyncResponse;
import org.noresponse.utils.Blob;
import org.noresponse.utils.Utils;

public class SummarizeWorkspaceOperationsResponse implements AsyncResponse {
    /**
     * HTTP response content type for this operation
     */
    private String contentType;

    /**
     * HTTP response status code for this operation
     */
    private int statusCode;

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    private HttpResponse<Blob> rawResponse;

    /**
     * Complete retained outcome, enforcement and review populations with independent denominators and
     * label coverage. The data snapshot and audit commit complete before any counts are returned.
     */
    private WorkspaceOperationsSummary workspaceOperationsSummary;

    private Map<String, List<String>> headers;

    @JsonCreator
    public SummarizeWorkspaceOperationsResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nullable WorkspaceOperationsSummary workspaceOperationsSummary,
            @Nonnull Map<String, List<String>> headers) {
        headers = Utils.emptyMapIfNull(headers);
        this.contentType = Optional.ofNullable(contentType)
                .orElseThrow(() -> new IllegalArgumentException("contentType cannot be null"));
        this.statusCode = statusCode;
        this.rawResponse = Optional.ofNullable(rawResponse)
                .orElseThrow(() -> new IllegalArgumentException("rawResponse cannot be null"));
        this.workspaceOperationsSummary = workspaceOperationsSummary;
        this.headers =
                Optional.ofNullable(headers).orElseThrow(() -> new IllegalArgumentException("headers cannot be null"));
    }

    public SummarizeWorkspaceOperationsResponse(
            @Nonnull String contentType,
            int statusCode,
            @Nonnull HttpResponse<Blob> rawResponse,
            @Nonnull Map<String, List<String>> headers) {
        this(contentType, statusCode, rawResponse, null, headers);
    }

    /**
     * HTTP response content type for this operation
     */
    public String contentType() {
        return this.contentType;
    }

    /**
     * HTTP response status code for this operation
     */
    public int statusCode() {
        return this.statusCode;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public HttpResponse<Blob> rawResponse() {
        return this.rawResponse;
    }

    /**
     * Complete retained outcome, enforcement and review populations with independent denominators and
     * label coverage. The data snapshot and audit commit complete before any counts are returned.
     */
    public Optional<WorkspaceOperationsSummary> workspaceOperationsSummary() {
        return Optional.ofNullable(this.workspaceOperationsSummary);
    }

    public Map<String, List<String>> headers() {
        return this.headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * HTTP response content type for this operation
     */
    public SummarizeWorkspaceOperationsResponse withContentType(@Nonnull String contentType) {
        this.contentType = Utils.checkNotNull(contentType, "contentType");
        return this;
    }

    /**
     * HTTP response status code for this operation
     */
    public SummarizeWorkspaceOperationsResponse withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Raw HTTP response; suitable for custom response parsing
     */
    public SummarizeWorkspaceOperationsResponse withRawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
        this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
        return this;
    }

    /**
     * Complete retained outcome, enforcement and review populations with independent denominators and
     * label coverage. The data snapshot and audit commit complete before any counts are returned.
     */
    public SummarizeWorkspaceOperationsResponse withWorkspaceOperationsSummary(
            @Nullable WorkspaceOperationsSummary workspaceOperationsSummary) {
        this.workspaceOperationsSummary = workspaceOperationsSummary;
        return this;
    }

    public SummarizeWorkspaceOperationsResponse withHeaders(@Nonnull Map<String, List<String>> headers) {
        this.headers = Utils.checkNotNull(headers, "headers");
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SummarizeWorkspaceOperationsResponse other = (SummarizeWorkspaceOperationsResponse) o;
        return Utils.enhancedDeepEquals(this.contentType, other.contentType)
                && Utils.enhancedDeepEquals(this.statusCode, other.statusCode)
                && Utils.enhancedDeepEquals(this.rawResponse, other.rawResponse)
                && Utils.enhancedDeepEquals(this.workspaceOperationsSummary, other.workspaceOperationsSummary)
                && Utils.enhancedDeepEquals(this.headers, other.headers);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(contentType, statusCode, rawResponse, workspaceOperationsSummary, headers);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SummarizeWorkspaceOperationsResponse.class,
                "contentType",
                contentType,
                "statusCode",
                statusCode,
                "rawResponse",
                rawResponse,
                "workspaceOperationsSummary",
                workspaceOperationsSummary,
                "headers",
                headers);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String contentType;

        private int statusCode;

        private HttpResponse<Blob> rawResponse;

        private WorkspaceOperationsSummary workspaceOperationsSummary;

        private Map<String, List<String>> headers;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * HTTP response content type for this operation
         */
        public Builder contentType(@Nonnull String contentType) {
            this.contentType = Utils.checkNotNull(contentType, "contentType");
            return this;
        }

        /**
         * HTTP response status code for this operation
         */
        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        /**
         * Raw HTTP response; suitable for custom response parsing
         */
        public Builder rawResponse(@Nonnull HttpResponse<Blob> rawResponse) {
            this.rawResponse = Utils.checkNotNull(rawResponse, "rawResponse");
            return this;
        }

        /**
         * Complete retained outcome, enforcement and review populations with independent denominators and
         * label coverage. The data snapshot and audit commit complete before any counts are returned.
         */
        public Builder workspaceOperationsSummary(@Nullable WorkspaceOperationsSummary workspaceOperationsSummary) {
            this.workspaceOperationsSummary = workspaceOperationsSummary;
            return this;
        }

        public Builder headers(@Nonnull Map<String, List<String>> headers) {
            this.headers = Utils.checkNotNull(headers, "headers");
            return this;
        }

        public SummarizeWorkspaceOperationsResponse build() {
            return new SummarizeWorkspaceOperationsResponse(
                    contentType, statusCode, rawResponse, workspaceOperationsSummary, headers);
        }
    }
}
