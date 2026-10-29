# Erasures

## Overview

Durable deletion receipts.

### Available Operations

* [get](#get) - Read an erasure receipt

## get

Read retained erasure progress and downstream acknowledgements. Completion does not certify deletion from backups.

### Example Usage

<!-- UsageSnippet language="java" operationID="getErasureReceipt" method="get" path="/v1/erasures/{erasure_id}" example="receipt" -->
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
import org.noresponse.models.operations.GetErasureReceiptResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetErasureReceiptResponse res = sdk.erasures().get()
                .erasureId("<id>")
                .call();

        if (res.erasureReceipt().isPresent()) {
            System.out.println(res.erasureReceipt().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `erasureId`        | *String*           | :heavy_check_mark: | N/A                |

### Response

**[GetErasureReceiptResponse](../../models/operations/GetErasureReceiptResponse.md)**

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