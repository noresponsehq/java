# KeyUsage

## Overview

### Available Operations

* [record](#record) - Report requests made with API keys your platform issued.

## record

Records requests made with API keys your platform issued to your accounts, one event per request: the key's SHA-256 (the issued_credential.sha256 the account's assessment carried), the client IP, the time and optionally the user agent, model and token counts. Most callers use the SDK's usage buffer, which batches events and sends them in the background. Sampling is allowed: sample_rate weights an event's request and token counts. Requires the assess scope. Idempotency-Key is required and handled as on POST /v1/assessments: a retry with the same key and body answers the same record with Idempotent-Replayed: true and counts nothing again; a different body answers 409. A batch holds 1 to 500 events and at most 256 KiB. No raw event is kept: usage is aggregated per key, and IPs, user agents and models are kept only as keyed digests, with the IP's country, for 24 hours. When one key is used from 20 or more IP addresses, or 3 or more countries, within 24 hours, every account whose assessment carried that key gets the shared_api_key reason for 30 days.

### Example Usage: batch

<!-- UsageSnippet language="java" operationID="recordKeyUsage" method="post" path="/v1/key_usage/batches" example="batch" -->
```java
package hello.world;

import java.lang.Exception;
import java.util.List;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationException;
import org.noresponse.models.errors.BadRequestException;
import org.noresponse.models.errors.ConflictException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.errors.PermissionDeniedException;
import org.noresponse.models.errors.RateLimitException;
import org.noresponse.models.operations.RecordKeyUsageResponse;
import org.noresponse.models.shared.KeyUsageBatchRequest;
import org.noresponse.models.shared.KeyUsageEvent;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordKeyUsageResponse res = sdk.keyUsage().record()
                .idempotencyKey("account-acct_7f3a9c")
                .body(KeyUsageBatchRequest.builder()
                    .events(List.of(
                        KeyUsageEvent.builder()
                            .keySha256("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08")
                            .ip("203.0.113.24")
                            .occurredAt("2026-10-23T09:14:07Z")
                            .userAgent("python-requests/2.32.3")
                            .model("gpt-4o-mini")
                            .inputTokens(812L)
                            .outputTokens(164L)
                            .build(),
                        KeyUsageEvent.builder()
                            .keySha256("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08")
                            .ip("2001:db8:4a::17")
                            .occurredAt("2026-10-23T09:14:09.250Z")
                            .sampleRate(0.25)
                            .build()))
                    .build())
                .call();

        if (res.keyUsageBatch().isPresent()) {
            System.out.println(res.keyUsageBatch().get());
        }
    }
}
```
### Example Usage: recorded

<!-- UsageSnippet language="java" operationID="recordKeyUsage" method="post" path="/v1/key_usage/batches" example="recorded" -->
```java
package hello.world;

import java.lang.Exception;
import java.util.List;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationException;
import org.noresponse.models.errors.BadRequestException;
import org.noresponse.models.errors.ConflictException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.errors.PermissionDeniedException;
import org.noresponse.models.errors.RateLimitException;
import org.noresponse.models.operations.RecordKeyUsageResponse;
import org.noresponse.models.shared.KeyUsageBatchRequest;
import org.noresponse.models.shared.KeyUsageEvent;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordKeyUsageResponse res = sdk.keyUsage().record()
                .idempotencyKey("account-acct_7f3a9c")
                .body(KeyUsageBatchRequest.builder()
                    .events(List.of(
                        KeyUsageEvent.builder()
                            .keySha256("2bdbf5c72f2a36df0ef40fb92203612274d4546f56dba946070a6a79c2401a1b")
                            .ip("203.0.113.24")
                            .occurredAt("2026-10-23T09:14:07.000Z")
                            .userAgent("python-requests/2.32.3")
                            .model("gpt-4o-mini")
                            .build()))
                    .build())
                .call();

        if (res.keyUsageBatch().isPresent()) {
            System.out.println(res.keyUsageBatch().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                         | Type                                                                                                                                                              | Required                                                                                                                                                          | Description                                                                                                                                                       | Example                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `idempotencyKey`                                                                                                                                                  | *String*                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours. | account-acct_7f3a9c                                                                                                                                               |
| `body`                                                                                                                                                            | [KeyUsageBatchRequest](../../models/shared/KeyUsageBatchRequest.md)                                                                                               | :heavy_check_mark:                                                                                                                                                | N/A                                                                                                                                                               |                                                                                                                                                                   |

### Response

**[RecordKeyUsageResponse](../../models/operations/RecordKeyUsageResponse.md)**

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