# WebhookEndpoints

## Overview

Your webhook endpoints: register, list, rotate signing keys, send test events and delete.

### Available Operations

* [list](#list) - List the tenant's webhook endpoints.
* [create](#create) - Register a webhook endpoint.
* [delete](#delete) - Delete a webhook endpoint.
* [rotateSigningKey](#rotatesigningkey) - Rotate an endpoint's signing key.
* [sendTestEvent](#sendtestevent) - Send a test event to an endpoint.
* [listDeliveries](#listdeliveries) - List an endpoint's delivery attempts.
* [replayDelivery](#replaydelivery) - Send one delivery again.

## list

Lists your webhook endpoints in every state, ordered by endpoint_id, with the ids of their signing keys and without secrets. Requires the webhooks scope.

### Example Usage

<!-- UsageSnippet language="java" operationID="listWebhookEndpoints" method="get" path="/v1/webhooks" example="endpoints" -->
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
import org.noresponse.models.operations.ListWebhookEndpointsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListWebhookEndpointsResponse res = sdk.webhookEndpoints().list()
                .call();

        if (res.webhookEndpointList().isPresent()) {
            System.out.println(res.webhookEndpointList().get());
        }
    }
}
```

### Response

**[ListWebhookEndpointsResponse](../../models/operations/ListWebhookEndpointsResponse.md)**

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

Registers a webhook endpoint and returns it with its first signing secret, shown only in this answer: store it where your receiver reads it. The URL is https on port 443 or 8443 with a DNS host whose every address is public; it is checked now and on every delivery. The endpoint starts unverified and receives assessment events once it echoes an endpoint.verification challenge, which sendTestEvent sends. You can hold 5 endpoints. Requires the webhooks scope, and response_mode detailed also the detailed scope. Registration is not idempotent: a retry after a lost answer registers a second endpoint, which you can list and delete.

### Example Usage: created

<!-- UsageSnippet language="java" operationID="createWebhookEndpoint" method="post" path="/v1/webhooks" example="created" -->
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
import org.noresponse.models.operations.CreateWebhookEndpointResponse;
import org.noresponse.models.shared.WebhookEndpointRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        WebhookEndpointRequest req = WebhookEndpointRequest.builder()
                .url("https://hooks.example.com/noresponse")
                .build();

        CreateWebhookEndpointResponse res = sdk.webhookEndpoints().create()
                .request(req)
                .call();

        if (res.webhookEndpointCreated().isPresent()) {
            System.out.println(res.webhookEndpointCreated().get());
        }
    }
}
```
### Example Usage: detailed

<!-- UsageSnippet language="java" operationID="createWebhookEndpoint" method="post" path="/v1/webhooks" example="detailed" -->
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
import org.noresponse.models.operations.CreateWebhookEndpointResponse;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.models.shared.WebhookEndpointRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        WebhookEndpointRequest req = WebhookEndpointRequest.builder()
                .url("https://hooks.example.com:8443/noresponse/detailed")
                .responseMode(ResponseMode.DETAILED)
                .build();

        CreateWebhookEndpointResponse res = sdk.webhookEndpoints().create()
                .request(req)
                .call();

        if (res.webhookEndpointCreated().isPresent()) {
            System.out.println(res.webhookEndpointCreated().get());
        }
    }
}
```
### Example Usage: standard

<!-- UsageSnippet language="java" operationID="createWebhookEndpoint" method="post" path="/v1/webhooks" example="standard" -->
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
import org.noresponse.models.operations.CreateWebhookEndpointResponse;
import org.noresponse.models.shared.WebhookEndpointRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        WebhookEndpointRequest req = WebhookEndpointRequest.builder()
                .url("https://hooks.example.com/noresponse")
                .build();

        CreateWebhookEndpointResponse res = sdk.webhookEndpoints().create()
                .request(req)
                .call();

        if (res.webhookEndpointCreated().isPresent()) {
            System.out.println(res.webhookEndpointCreated().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `request`                                                               | [WebhookEndpointRequest](../../models/shared/WebhookEndpointRequest.md) | :heavy_check_mark:                                                      | The request object to use for the request.                              |

### Response

**[CreateWebhookEndpointResponse](../../models/operations/CreateWebhookEndpointResponse.md)**

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

## delete

Deletes the endpoint and its signing keys. Deliveries still queued for it are dropped. Requires the webhooks scope. An endpoint that is already gone answers 404.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteWebhookEndpoint" method="delete" path="/v1/webhooks/{endpoint_id}" -->
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
import org.noresponse.models.operations.DeleteWebhookEndpointResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteWebhookEndpointResponse res = sdk.webhookEndpoints().delete()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `endpointId`                                                                              | *String*                                                                                  | :heavy_check_mark:                                                                        | The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404. | whe_01JA2ZD0E1F2G3H4J5K6M7N8P9                                                            |

### Response

**[DeleteWebhookEndpointResponse](../../models/operations/DeleteWebhookEndpointResponse.md)**

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

## rotateSigningKey

Issues a new signing key for the endpoint and returns its secret once. Every delivery from now on is signed with it; the key it replaces keeps verifying for 7 days, so your receiver can hold both while it switches. Requires the webhooks scope. Rotation is not idempotent: a retry after a lost answer issues another key, which replaces the first.

### Example Usage: empty

<!-- UsageSnippet language="java" operationID="rotateWebhookSigningKey" method="post" path="/v1/webhooks/{endpoint_id}/rotate" example="empty" -->
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
import org.noresponse.models.operations.RotateWebhookSigningKeyResponse;
import org.noresponse.models.shared.WebhookKeyRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RotateWebhookSigningKeyResponse res = sdk.webhookEndpoints().rotateSigningKey()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .body(WebhookKeyRotationRequest.builder()
                    .build())
                .call();

        if (res.webhookKeyRotation().isPresent()) {
            System.out.println(res.webhookKeyRotation().get());
        }
    }
}
```
### Example Usage: rotated

<!-- UsageSnippet language="java" operationID="rotateWebhookSigningKey" method="post" path="/v1/webhooks/{endpoint_id}/rotate" example="rotated" -->
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
import org.noresponse.models.operations.RotateWebhookSigningKeyResponse;
import org.noresponse.models.shared.WebhookKeyRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RotateWebhookSigningKeyResponse res = sdk.webhookEndpoints().rotateSigningKey()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .body(WebhookKeyRotationRequest.builder()
                    .build())
                .call();

        if (res.webhookKeyRotation().isPresent()) {
            System.out.println(res.webhookKeyRotation().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `endpointId`                                                                              | *String*                                                                                  | :heavy_check_mark:                                                                        | The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404. | whe_01JA2ZD0E1F2G3H4J5K6M7N8P9                                                            |
| `body`                                                                                    | [WebhookKeyRotationRequest](../../models/shared/WebhookKeyRotationRequest.md)             | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[RotateWebhookSigningKeyResponse](../../models/operations/RotateWebhookSigningKeyResponse.md)**

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

## sendTestEvent

endpoint.verification sends a signed challenge now and answers whether the endpoint echoed it within 5 seconds; an echo verifies the endpoint, including a disabled one. assessment.updated queues one signed event with test: true and a fixed example assessment in the endpoint's response mode, delivered and retried like any other event; the endpoint must be verified. Both use the endpoint's current signing key. Requires the webhooks scope.

### Example Usage: assessment_updated

<!-- UsageSnippet language="java" operationID="sendWebhookTestEvent" method="post" path="/v1/webhooks/{endpoint_id}/test" example="assessment_updated" -->
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
import org.noresponse.models.operations.SendWebhookTestEventResponse;
import org.noresponse.models.shared.WebhookTestRequest;
import org.noresponse.models.shared.WebhookTestRequestEvent;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        SendWebhookTestEventResponse res = sdk.webhookEndpoints().sendTestEvent()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .body(WebhookTestRequest.builder()
                    .event(WebhookTestRequestEvent.ASSESSMENT_UPDATED)
                    .build())
                .call();

        if (res.webhookTest().isPresent()) {
            System.out.println(res.webhookTest().get());
        }
    }
}
```
### Example Usage: verification

<!-- UsageSnippet language="java" operationID="sendWebhookTestEvent" method="post" path="/v1/webhooks/{endpoint_id}/test" example="verification" -->
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
import org.noresponse.models.operations.SendWebhookTestEventResponse;
import org.noresponse.models.shared.WebhookTestRequest;
import org.noresponse.models.shared.WebhookTestRequestEvent;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        SendWebhookTestEventResponse res = sdk.webhookEndpoints().sendTestEvent()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .body(WebhookTestRequest.builder()
                    .event(WebhookTestRequestEvent.ENDPOINT_VERIFICATION)
                    .build())
                .call();

        if (res.webhookTest().isPresent()) {
            System.out.println(res.webhookTest().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `endpointId`                                                                              | *String*                                                                                  | :heavy_check_mark:                                                                        | The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404. | whe_01JA2ZD0E1F2G3H4J5K6M7N8P9                                                            |
| `body`                                                                                    | [WebhookTestRequest](../../models/shared/WebhookTestRequest.md)                           | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[SendWebhookTestEventResponse](../../models/operations/SendWebhookTestEventResponse.md)**

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

## listDeliveries

Every POST the endpoint received, newest first: the event id and type, which attempt it was, whether it was a replay, delivered or failed, the response code, why it failed and how long it took. Attempts are kept for 30 days, or your retention_days when that is shorter, and are deleted with the assessment they carried. Requires the webhooks scope. Read-only and safe to retry. The cursor is opaque and bound to your tenant and the endpoint; a tampered or expired cursor answers 400 invalid_cursor.

### Example Usage

<!-- UsageSnippet language="java" operationID="listWebhookDeliveries" method="get" path="/v1/webhooks/{endpoint_id}/deliveries" example="attempts" -->
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
import org.noresponse.models.operations.ListWebhookDeliveriesResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();


        sdk.webhookEndpoints().listDeliveries()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .limit(20L)
                .callAsStream()
                .forEach((ListWebhookDeliveriesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `endpointId`                                                                              | *String*                                                                                  | :heavy_check_mark:                                                                        | The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404. | whe_01JA2ZD0E1F2G3H4J5K6M7N8P9                                                            |
| `limit`                                                                                   | @Nullable *long*                                                                          | :heavy_minus_sign:                                                                        | Attempts per page.                                                                        |                                                                                           |
| `cursor`                                                                                  | @Nullable *String*                                                                        | :heavy_minus_sign:                                                                        | next_cursor from the previous page, unchanged.                                            |                                                                                           |

### Response

**[ListWebhookDeliveriesResponse](../../models/operations/ListWebhookDeliveriesResponse.md)**

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

## replayDelivery

Queues the event for the endpoint again: the same event id and body it received, with a new NR-Webhook-Timestamp and a signature from the endpoint's current signing key, retried like any other delivery and listed with replay: true. Your receiver deduplicates on the event id. The endpoint must be verified. Requires the webhooks scope. Not idempotent: each call, a retry included, sends the event again.

### Example Usage: empty

<!-- UsageSnippet language="java" operationID="replayWebhookDelivery" method="post" path="/v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay" example="empty" -->
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
import org.noresponse.models.operations.ReplayWebhookDeliveryResponse;
import org.noresponse.models.shared.WebhookReplayRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReplayWebhookDeliveryResponse res = sdk.webhookEndpoints().replayDelivery()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .eventId("evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2")
                .body(WebhookReplayRequest.builder()
                    .build())
                .call();

        if (res.webhookReplay().isPresent()) {
            System.out.println(res.webhookReplay().get());
        }
    }
}
```
### Example Usage: replay

<!-- UsageSnippet language="java" operationID="replayWebhookDelivery" method="post" path="/v1/webhooks/{endpoint_id}/deliveries/{event_id}/replay" example="replay" -->
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
import org.noresponse.models.operations.ReplayWebhookDeliveryResponse;
import org.noresponse.models.shared.WebhookReplayRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReplayWebhookDeliveryResponse res = sdk.webhookEndpoints().replayDelivery()
                .endpointId("whe_01JA2ZD0E1F2G3H4J5K6M7N8P9")
                .eventId("evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2")
                .body(WebhookReplayRequest.builder()
                    .build())
                .call();

        if (res.webhookReplay().isPresent()) {
            System.out.println(res.webhookReplay().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                | Type                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                 | Description                                                                                                                                                                                                              | Example                                                                                                                                                                                                                  |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `endpointId`                                                                                                                                                                                                             | *String*                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                       | The endpoint's whe_ id. An id that is unknown, malformed or another tenant's answers 404.                                                                                                                                | whe_01JA2ZD0E1F2G3H4J5K6M7N8P9                                                                                                                                                                                           |
| `eventId`                                                                                                                                                                                                                | *String*                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                       | The evt_ id of a delivery the endpoint's log holds, as GET /v1/webhooks/{endpoint_id}/deliveries lists it. An id that is malformed, not sent to this endpoint, past the log's retention or another tenant's answers 404. | evt_01JA2ZD3K4M5N6P7Q8R9S0T1V2                                                                                                                                                                                           |
| `body`                                                                                                                                                                                                                   | [WebhookReplayRequest](../../models/shared/WebhookReplayRequest.md)                                                                                                                                                      | :heavy_check_mark:                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                      |                                                                                                                                                                                                                          |

### Response

**[ReplayWebhookDeliveryResponse](../../models/operations/ReplayWebhookDeliveryResponse.md)**

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