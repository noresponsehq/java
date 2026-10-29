# Audit

## Overview

Durable audit history, retention and delivery.

### Available Operations

* [list](#list) - List a workspace's audit log.
* [getRetention](#getretention) - Read audit retention and available history.
* [updateRetention](#updateretention) - Set bounded customer audit retention.
* [listStreams](#liststreams) - Read audit stream subscriptions.
* [configureStream](#configurestream) - Configure an audit stream subscription.
* [removeStream](#removestream) - Remove an audit stream.
* [replayStream](#replaystream) - Replay an audit delivery.

## list

Read audit events within your current environment permissions. Filters apply before pagination.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAuditEvents" method="get" path="/v1/workspaces/{workspace_id}/audit_events" example="page" -->
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
import org.noresponse.models.operations.ListAuditEventsRequest;
import org.noresponse.models.operations.ListAuditEventsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAuditEventsRequest req = ListAuditEventsRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();


        sdk.audit().list()
                .request(req)
                .callAsStream()
                .forEach((ListAuditEventsResponse item) -> {
                   // handle page
                });


    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [ListAuditEventsRequest](../../models/operations/ListAuditEventsRequest.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[ListAuditEventsResponse](../../models/operations/ListAuditEventsResponse.md)**

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

## getRetention

Read audit retention for the environment. The default is 365 days. Oldest available is an observation; it does not establish uninterrupted capture. Workspace-wide events follow the live storage policy.

### Example Usage

<!-- UsageSnippet language="java" operationID="getAuditRetention" method="get" path="/v1/workspaces/{workspace_id}/audit_settings/{environment}" -->
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
import org.noresponse.models.operations.GetAuditRetentionResponse;
import org.noresponse.models.shared.AuditSettingsEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAuditRetentionResponse res = sdk.audit().getRetention()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(AuditSettingsEnvironment.TEST)
                .call();

        if (res.auditSettings().isPresent()) {
            System.out.println(res.auditSettings().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [AuditSettingsEnvironment](../../models/shared/AuditSettingsEnvironment.md)                                                        | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[GetAuditRetentionResponse](../../models/operations/GetAuditRetentionResponse.md)**

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

## updateRetention

Set test-environment audit retention from 1 to 365 days with audit_manage scope. Live retention also governs workspace-wide audit events and requires a dashboard member authorised in both environments. Shortening immediately hides older events and invalidates audit exports; extending cannot restore deleted events. External receivers control copies already delivered.

### Example Usage

<!-- UsageSnippet language="java" operationID="updateAuditRetention" method="patch" path="/v1/workspaces/{workspace_id}/audit_settings/{environment}" -->
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
import org.noresponse.models.operations.Environment;
import org.noresponse.models.operations.UpdateAuditRetentionResponse;
import org.noresponse.models.shared.AuditSettingsRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateAuditRetentionResponse res = sdk.audit().updateRetention()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(Environment.TEST)
                .body(AuditSettingsRequest.builder()
                    .retentionDays(90L)
                    .build())
                .call();

        if (res.auditSettings().isPresent()) {
            System.out.println(res.auditSettings().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [Environment](../../models/operations/Environment.md)                                                                              | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                | test                                                                                                                               |
| `body`                                                                                                                             | [AuditSettingsRequest](../../models/shared/AuditSettingsRequest.md)                                                                | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[UpdateAuditRetentionResponse](../../models/operations/UpdateAuditRetentionResponse.md)**

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

## listStreams

List audit subscriptions and their endpoint state and latest delivery attempt. Streams use verified HTTPS webhook endpoints and signed audit.event bodies. Delivery is at least once and unordered; deduplicate by webhook id. An empty action filter accepts all actions within the chosen scope.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAuditStreams" method="get" path="/v1/workspaces/{workspace_id}/audit_streams/{environment}" -->
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
import org.noresponse.models.operations.ListAuditStreamsResponse;
import org.noresponse.models.shared.Environment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAuditStreamsResponse res = sdk.audit().listStreams()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(Environment.TEST)
                .call();

        if (res.auditStreamList().isPresent()) {
            System.out.println(res.auditStreamList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [Environment](../../models/shared/Environment.md)                                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[ListAuditStreamsResponse](../../models/operations/ListAuditStreamsResponse.md)**

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

## configureStream

Configure the action filter for one verified webhook endpoint. Requires audit_manage. Capture starts at subscription creation; use an audit export for available earlier events. Workspace-wide scope requires an unrestricted console member and is unavailable to API keys.

### Example Usage

<!-- UsageSnippet language="java" operationID="configureAuditStream" method="post" path="/v1/workspaces/{workspace_id}/audit_streams/{environment}" -->
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
import org.noresponse.models.operations.ConfigureAuditStreamResponse;
import org.noresponse.models.shared.AuditStreamRequest;
import org.noresponse.models.shared.Environment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ConfigureAuditStreamResponse res = sdk.audit().configureStream()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(Environment.TEST)
                .body(AuditStreamRequest.builder()
                    .endpointId("whe_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .actions(List.of(
                        "key.created",
                        "member.access_changed"))
                    .build())
                .call();

        if (res.auditStreamList().isPresent()) {
            System.out.println(res.auditStreamList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [Environment](../../models/shared/Environment.md)                                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |
| `body`                                                                                                                             | [AuditStreamRequest](../../models/shared/AuditStreamRequest.md)                                                                    | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[ConfigureAuditStreamResponse](../../models/operations/ConfigureAuditStreamResponse.md)**

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

## removeStream

Remove an audit subscription and cancel queued deliveries. Requires audit_manage. An HTTP delivery already in flight cannot be recalled. The endpoint remains registered for other event types.

### Example Usage

<!-- UsageSnippet language="java" operationID="removeAuditStream" method="delete" path="/v1/workspaces/{workspace_id}/audit_streams/{environment}/{endpoint_id}" -->
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
import org.noresponse.models.operations.RemoveAuditStreamResponse;
import org.noresponse.models.shared.Environment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RemoveAuditStreamResponse res = sdk.audit().removeStream()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(Environment.LIVE)
                .endpointId("<id>")
                .call();

        if (res.auditStreamList().isPresent()) {
            System.out.println(res.auditStreamList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [Environment](../../models/shared/Environment.md)                                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |
| `endpointId`                                                                                                                       | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[RemoveAuditStreamResponse](../../models/operations/RemoveAuditStreamResponse.md)**

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

## replayStream

Replay a recorded delivery for the current audit subscription. Requires audit_manage. Source retention and the current action filter are checked again before sending. Expired or unsubscribed events are dropped.

### Example Usage

<!-- UsageSnippet language="java" operationID="replayAuditStream" method="post" path="/v1/workspaces/{workspace_id}/audit_streams/{environment}/{endpoint_id}/replay" -->
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
import org.noresponse.models.operations.ReplayAuditStreamResponse;
import org.noresponse.models.shared.AuditStreamReplayRequest;
import org.noresponse.models.shared.Environment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReplayAuditStreamResponse res = sdk.audit().replayStream()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(Environment.TEST)
                .endpointId("<id>")
                .body(AuditStreamReplayRequest.builder()
                    .eventId("evt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .build())
                .call();

        if (res.auditStreamList().isPresent()) {
            System.out.println(res.auditStreamList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `environment`                                                                                                                      | [Environment](../../models/shared/Environment.md)                                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |
| `endpointId`                                                                                                                       | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |
| `body`                                                                                                                             | [AuditStreamReplayRequest](../../models/shared/AuditStreamReplayRequest.md)                                                        | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[ReplayAuditStreamResponse](../../models/operations/ReplayAuditStreamResponse.md)**

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