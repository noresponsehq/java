# Operations

## Overview

Retained outcomes, enforcement reports and review populations.

### Available Operations

* [summarize](#summarize) - Summarize retained account outcomes, enforcement reports and reviews.

## summarize

Read one retained snapshot of account outcomes, enforcement reports and review states for the environment and a window of at most 31 days. Each section declares its own population, numerator, denominator and label coverage. Current outcome heads preserve explicit unknown labels. Enforcement rows are reports from your application and do not independently verify execution. Requires history or investigate together with management scope. A result exceeding 100000 population rows or the query budget is refused without truncation.

### Example Usage

<!-- UsageSnippet language="java" operationID="summarizeWorkspaceOperations" method="post" path="/v1/workspaces/{workspace_id}/operations/summary" -->
```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationException;
import org.noresponse.models.errors.BadRequestException;
import org.noresponse.models.errors.ConflictException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.errors.PermissionDeniedException;
import org.noresponse.models.errors.RateLimitException;
import org.noresponse.models.operations.SummarizeWorkspaceOperationsResponse;
import org.noresponse.models.shared.AuditSettingsEnvironment;
import org.noresponse.models.shared.WorkspaceOperationsSummaryRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        SummarizeWorkspaceOperationsResponse res = sdk.operations().summarize()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(WorkspaceOperationsSummaryRequest.builder()
                    .environment(AuditSettingsEnvironment.LIVE)
                    .from("2026-01-01T00:00:00Z")
                    .to("2026-01-02T00:00:00Z")
                    .build())
                .call();

        if (res.workspaceOperationsSummary().isPresent()) {
            System.out.println(res.workspaceOperationsSummary().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `body`                                                                                                                             | [WorkspaceOperationsSummaryRequest](../../models/shared/WorkspaceOperationsSummaryRequest.md)                                      | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[SummarizeWorkspaceOperationsResponse](../../models/operations/SummarizeWorkspaceOperationsResponse.md)**

### Errors

| Error Type                              | Status Code                             | Content Type                            |
| --------------------------------------- | --------------------------------------- | --------------------------------------- |
| models/errors/BadRequestException       | 400                                     | application/problem+json                |
| models/errors/AuthenticationException   | 401                                     | application/problem+json                |
| models/errors/PermissionDeniedException | 403                                     | application/problem+json                |
| models/errors/NotFoundException         | 404                                     | application/problem+json                |
| models/errors/ConflictException         | 409                                     | application/problem+json                |
| models/errors/RateLimitException        | 429                                     | application/problem+json                |
| models/errors/APIStatusException        | 4XX, 5XX                                | \*/\*                                   |