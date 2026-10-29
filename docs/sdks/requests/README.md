# Requests

## Overview

Retained request diagnostics and operational summaries.

### Available Operations

* [list](#list) - Search request diagnostics
* [aggregate](#aggregate) - Summarise retained request attempts

## list

Search authenticated request attempts by time, route, status or request correlation id. The interval is at most seven days. Each attempt has a unique id and may include the exact served decision. Records are bounded best effort and exclude request bodies, secrets and source payloads. A repeated correlation id does not overwrite another attempt.

### Example Usage

<!-- UsageSnippet language="java" operationID="listRequestDiagnostics" method="get" path="/v1/requests" -->
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
import org.noresponse.models.operations.ListRequestDiagnosticsRequest;
import org.noresponse.models.operations.ListRequestDiagnosticsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListRequestDiagnosticsRequest req = ListRequestDiagnosticsRequest.builder()
                .from("2026-10-21T00:00:00Z")
                .to("2026-10-22T00:00:00Z")
                .build();


        sdk.requests().list()
                .request(req)
                .callAsStream()
                .forEach((ListRequestDiagnosticsResponse item) -> {
                   // handle page
                });


    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `request`                                                                                 | [ListRequestDiagnosticsRequest](../../models/operations/ListRequestDiagnosticsRequest.md) | :heavy_check_mark:                                                                        | The request object to use for the request.                                                |

### Response

**[ListRequestDiagnosticsResponse](../../models/operations/ListRequestDiagnosticsResponse.md)**

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

## aggregate

Count all retained matching request attempts and latency samples within seven days. Capture is best effort.

### Example Usage

<!-- UsageSnippet language="java" operationID="aggregateRequests" method="post" path="/v1/requests/aggregate" -->
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
import org.noresponse.models.operations.AggregateRequestsResponse;
import org.noresponse.models.shared.RequestAggregateRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RequestAggregateRequest req = RequestAggregateRequest.builder()
                .from("2026-01-01T00:00:00Z")
                .to("2026-01-01T00:00:00Z")
                .route("POST /v1/assessments")
                .apiKeyId("01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();

        AggregateRequestsResponse res = sdk.requests().aggregate()
                .request(req)
                .call();

        if (res.operationalAggregate().isPresent()) {
            System.out.println(res.operationalAggregate().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [RequestAggregateRequest](../../models/shared/RequestAggregateRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[AggregateRequestsResponse](../../models/operations/AggregateRequestsResponse.md)**

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