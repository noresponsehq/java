# Investigations

## Overview

Person investigation jobs, retained results and contextual decisions.

### Available Operations

* [list](#list) - List investigation history.
* [create](#create) - Start an investigation for a person.
* [get](#get) - Read an investigation and its evidence.
* [review](#review) - Record an investigation decision.
* [cancel](#cancel) - Cancel an investigation

## list

List investigation history. The API key determines the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="listInvestigations" method="get" path="/v1/investigations" -->
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
import org.noresponse.models.operations.ListInvestigationsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListInvestigationsResponse res = sdk.investigations().list()
                .limit(100L)
                .call();

        if (res.investigationsList().isPresent()) {
            System.out.println(res.investigationsList().get());
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        |
| ------------------ | ------------------ | ------------------ | ------------------ |
| `personId`         | @Nullable *String* | :heavy_minus_sign: | N/A                |
| `before`           | @Nullable *String* | :heavy_minus_sign: | N/A                |
| `limit`            | @Nullable *long*   | :heavy_minus_sign: | N/A                |

### Response

**[ListInvestigationsResponse](../../models/operations/ListInvestigationsResponse.md)**

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

## create

Starts an investigation against a retained workspace person, independently of group membership. enrichment overrides, field by field, how the environment's enrichment setting runs after this investigation; the person's latest investigation decides the enrichment that runs on them. Idempotency-Key is required: the same key and input return the same retained job in its current state without repeating billable work. Different input with that key answers 409 idempotency_key_reused; a deliberate new investigation uses a new key. The response includes Location and Retry-After. The person's retention expiry limits both execution and retained history. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="createInvestigation" method="post" path="/v1/investigations" -->
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
import org.noresponse.models.operations.CreateInvestigationResponse;
import org.noresponse.models.shared.InvestigationRequest;
import org.noresponse.models.shared.ScreeningDepth;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateInvestigationResponse res = sdk.investigations().create()
                .idempotencyKey("investigation-case-1042-first")
                .body(InvestigationRequest.builder()
                    .depth(ScreeningDepth.STANDARD)
                    .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .build())
                .call();

        if (res.investigation().isPresent()) {
            System.out.println(res.investigation().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `idempotencyKey`                                                                                                                                                                                                                                                                                                                                         | *String*                                                                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person. | investigation-case-1042-first                                                                                                                                                                                                                                                                                                                            |
| `body`                                                                                                                                                                                                                                                                                                                                                   | [InvestigationRequest](../../models/shared/InvestigationRequest.md)                                                                                                                                                                                                                                                                                      | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                          |

### Response

**[CreateInvestigationResponse](../../models/operations/CreateInvestigationResponse.md)**

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

## get

Returns this investigation's status, coverage, source records, posts, media and contextual review history. Full evidence is returned by default; explicit evidence.read denial keeps submitted person details, status, coverage and decision summaries and hides collected evidence and free-text notes. The read is durably audited as person.viewed. The API key selects the workspace and environment. Missing, foreign or expired results answer 404.

### Example Usage

<!-- UsageSnippet language="java" operationID="getInvestigation" method="get" path="/v1/investigations/{investigation_id}" -->
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
import org.noresponse.models.operations.GetInvestigationResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetInvestigationResponse res = sdk.investigations().get()
                .investigationId("inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.investigationReport().isPresent()) {
            System.out.println(res.investigationReport().get());
        }
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `investigationId`              | *String*                       | :heavy_check_mark:             | N/A                            | inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

### Response

**[GetInvestigationResponse](../../models/operations/GetInvestigationResponse.md)**

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

## review

Record an investigation decision. Deprecated: decisions are leaving people and groups. The console no longer records them and no screening reads them. This route is removed in the next contract release; stop calling it. The API key determines the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="reviewInvestigation" method="post" path="/v1/investigations/{investigation_id}/reviews" -->
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
import org.noresponse.models.operations.ReviewInvestigationResponse;
import org.noresponse.models.shared.PersonDecision;
import org.noresponse.models.shared.PersonReviewRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReviewInvestigationResponse res = sdk.investigations().review()
                .investigationId("inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(PersonReviewRequest.builder()
                    .decision(PersonDecision.WATCH)
                    .note("Known to the venue team.")
                    .build())
                .call();

        if (res.investigation().isPresent()) {
            System.out.println(res.investigation().get());
        }
    }
}
```

### Parameters

| Parameter                                                         | Type                                                              | Required                                                          | Description                                                       | Example                                                           |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `investigationId`                                                 | *String*                                                          | :heavy_check_mark:                                                | N/A                                                               | inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                    |
| `body`                                                            | [PersonReviewRequest](../../models/shared/PersonReviewRequest.md) | :heavy_check_mark:                                                | N/A                                                               |                                                                   |

### Response

**[ReviewInvestigationResponse](../../models/operations/ReviewInvestigationResponse.md)**

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

## cancel

Stops this investigation. A completed or already cancelled investigation keeps its current result. Other investigations for the person keep running.

### Example Usage

<!-- UsageSnippet language="java" operationID="cancelInvestigation" method="post" path="/v1/investigations/{investigation_id}/cancel" -->
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
import org.noresponse.models.operations.CancelInvestigationResponse;
import org.noresponse.models.shared.ScreeningCancelRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CancelInvestigationResponse res = sdk.investigations().cancel()
                .investigationId("inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(ScreeningCancelRequest.builder()
                    .build())
                .call();

        if (res.investigation().isPresent()) {
            System.out.println(res.investigation().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             | Example                                                                 |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `investigationId`                                                       | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `body`                                                                  | [ScreeningCancelRequest](../../models/shared/ScreeningCancelRequest.md) | :heavy_check_mark:                                                      | N/A                                                                     |                                                                         |

### Response

**[CancelInvestigationResponse](../../models/operations/CancelInvestigationResponse.md)**

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