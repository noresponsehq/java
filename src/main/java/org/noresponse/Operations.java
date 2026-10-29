package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.SummarizeWorkspaceOperationsRequest;
import org.noresponse.models.operations.SummarizeWorkspaceOperationsRequestBuilder;
import org.noresponse.models.operations.SummarizeWorkspaceOperationsResponse;
import org.noresponse.models.shared.WorkspaceOperationsSummaryRequest;
import org.noresponse.operations.SummarizeWorkspaceOperations;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

/**
 * Retained outcomes, enforcement reports and review populations.
 */
public class Operations {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncOperations asyncSDK;

    Operations(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncOperations(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncOperations async() {
        return asyncSDK;
    }

    /**
     * Summarize retained account outcomes, enforcement reports and reviews.
     *
     * <p>Read one retained snapshot of account outcomes, enforcement reports and review states for the
     * environment and a window of at most 31 days. Each section declares its own population, numerator,
     * denominator and label coverage. Current outcome heads preserve explicit unknown labels.
     *
     * <p>Enforcement rows are reports from your application and do not independently verify execution.
     * Requires history or investigate together with management scope. A result exceeding 100000 population
     * rows or the query budget is refused without truncation.
     *
     * @return The call builder
     */
    public SummarizeWorkspaceOperationsRequestBuilder summarize() {
        return new SummarizeWorkspaceOperationsRequestBuilder(sdkConfiguration);
    }

    /**
     * Summarize retained account outcomes, enforcement reports and reviews.
     *
     * <p>Read one retained snapshot of account outcomes, enforcement reports and review states for the
     * environment and a window of at most 31 days. Each section declares its own population, numerator,
     * denominator and label coverage. Current outcome heads preserve explicit unknown labels.
     *
     * <p>Enforcement rows are reports from your application and do not independently verify execution.
     * Requires history or investigate together with management scope. A result exceeding 100000 population
     * rows or the query budget is refused without truncation.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param body Read a past [from,to) interval of at most 31 days. The complete retained populations must fit a combined 100000-row budget and a 1500ms SQL statement budget. No pagination or sampled totals; choose a shorter interval after a row-budget refusal.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public SummarizeWorkspaceOperationsResponse summarize(
            @Nonnull String workspaceId, @Nonnull WorkspaceOperationsSummaryRequest body) {
        return summarize(workspaceId, body, null);
    }

    /**
     * Summarize retained account outcomes, enforcement reports and reviews.
     *
     * <p>Read one retained snapshot of account outcomes, enforcement reports and review states for the
     * environment and a window of at most 31 days. Each section declares its own population, numerator,
     * denominator and label coverage. Current outcome heads preserve explicit unknown labels.
     *
     * <p>Enforcement rows are reports from your application and do not independently verify execution.
     * Requires history or investigate together with management scope. A result exceeding 100000 population
     * rows or the query budget is refused without truncation.
     *
     * @param workspaceId Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.
     * @param body Read a past [from,to) interval of at most 31 days. The complete retained populations must fit a combined 100000-row budget and a 1500ms SQL statement budget. No pagination or sampled totals; choose a shorter interval after a row-budget refusal.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public SummarizeWorkspaceOperationsResponse summarize(
            @Nonnull String workspaceId, @Nonnull WorkspaceOperationsSummaryRequest body, @Nullable Options options) {
        SummarizeWorkspaceOperationsRequest request = new SummarizeWorkspaceOperationsRequest(workspaceId, body);
        RequestOperation<SummarizeWorkspaceOperationsRequest, SummarizeWorkspaceOperationsResponse> operation =
                new SummarizeWorkspaceOperations.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
