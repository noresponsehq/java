# Events

## Overview

Events, attendee lists, screenings and reviewer decisions.

### Available Operations

* [list](#list) - List an environment's events.
* [create](#create) - Create an event to screen.
* [get](#get) - Read an event.
* [update](#update) - Change an event.
* [delete](#delete) - Erase an event.
* [listAttendees](#listattendees) - List an event's attendees.
* [addAttendees](#addattendees) - Add or update a batch of attendees.
* [getAttendee](#getattendee) - Read an attendee's report.
* [reviewAttendee](#reviewattendee) - Record a decision on an attendee.
* [listScreenings](#listscreenings) - List an event's screenings.
* [startScreening](#startscreening) - Start a screening.
* [getScreening](#getscreening) - Read a screening.
* [cancelScreening](#cancelscreening) - Cancel a screening.

## list

List this environment's events, newest first. Pass next_before back as before for the next page. Requires the events scope.

### Example Usage

<!-- UsageSnippet language="java" operationID="listEvents" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}" -->
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
import org.noresponse.models.operations.ListEventsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListEventsResponse res = sdk.events().list()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .limit(20L)
                .call();

        if (res.eventList().isPresent()) {
            System.out.println(res.eventList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `limit`                                                                                                                                                                | @Nullable *long*                                                                                                                                                       | :heavy_minus_sign:                                                                                                                                                     | At most this many items.                                                                                                                                               |                                                                                                                                                                        |
| `before`                                                                                                                                                               | @Nullable *String*                                                                                                                                                     | :heavy_minus_sign:                                                                                                                                                     | Read events older than this one.                                                                                                                                       |                                                                                                                                                                        |

### Response

**[ListEventsResponse](../../models/operations/ListEventsResponse.md)**

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

Create an event to screen: its name, start, venue, host, the principals it protects and the sensitive topics posts are read against. An environment holds at most 1000 events.

### Example Usage

<!-- UsageSnippet language="java" operationID="createEvent" method="post" path="/v1/workspaces/{workspace_id}/events/{environment}" -->
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
import org.noresponse.models.operations.CreateEventResponse;
import org.noresponse.models.shared.EventRequest;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.Principal;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateEventResponse res = sdk.events().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .body(EventRequest.builder()
                    .name("Annual shareholder meeting")
                    .startsAt("2026-01-01T00:00:00Z")
                    .endsAt("2026-01-02T00:00:00Z")
                    .venue("Example Convention Centre")
                    .host("Example Holdings")
                    .principals(List.of(
                        Principal.builder()
                            .name("Alex Principal")
                            .aliases(List.of(
                                "Alex P"))
                            .build()))
                    .topics(List.of(
                        "executive pay"))
                    .build())
                .call();

        if (res.event().isPresent()) {
            System.out.println(res.event().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [EventRequest](../../models/shared/EventRequest.md)                                                                                                                    | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[CreateEventResponse](../../models/operations/CreateEventResponse.md)**

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

Read an event and how many attendees it holds.

### Example Usage

<!-- UsageSnippet language="java" operationID="getEvent" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}" -->
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
import org.noresponse.models.operations.GetEventResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetEventResponse res = sdk.events().get()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.event().isPresent()) {
            System.out.println(res.event().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `eventId`                                                                                                                                                              | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |

### Response

**[GetEventResponse](../../models/operations/GetEventResponse.md)**

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

## update

Change the fields you name. Send expected_version to apply the change only to that version.

### Example Usage

<!-- UsageSnippet language="java" operationID="updateEvent" method="patch" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}" -->
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
import org.noresponse.models.operations.UpdateEventResponse;
import org.noresponse.models.shared.EventUpdate;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.Principal;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateEventResponse res = sdk.events().update()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(EventUpdate.builder()
                    .name("Annual shareholder meeting")
                    .startsAt("2026-01-01T00:00:00Z")
                    .endsAt("2026-01-02T00:00:00Z")
                    .venue("Example Convention Centre")
                    .host("Example Holdings")
                    .principals(List.of(
                        Principal.builder()
                            .name("Alex Principal")
                            .aliases(List.of(
                                "Alex P"))
                            .build()))
                    .topics(List.of(
                        "executive pay"))
                    .build())
                .call();

        if (res.event().isPresent()) {
            System.out.println(res.event().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `eventId`                                                                                                                                                              | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `body`                                                                                                                                                                 | [EventUpdate](../../models/shared/EventUpdate.md)                                                                                                                      | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[UpdateEventResponse](../../models/operations/UpdateEventResponse.md)**

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

Erase an event with its attendees, their decisions and its screenings, and stop any screening still running.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteEvent" method="delete" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}" -->
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
import org.noresponse.models.operations.DeleteEventResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteEventResponse res = sdk.events().delete()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `eventId`                                                                                                                                                              | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |

### Response

**[DeleteEventResponse](../../models/operations/DeleteEventResponse.md)**

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

## listAttendees

Read an event's roster in upload order, with each attendee's match state, highest finding band and decision. Filter by band or decision; pass next_after back as after.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAttendees" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/attendees" -->
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
import org.noresponse.models.operations.ListAttendeesRequest;
import org.noresponse.models.operations.ListAttendeesResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAttendeesRequest req = ListAttendeesRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();

        ListAttendeesResponse res = sdk.events().listAttendees()
                .request(req)
                .call();

        if (res.attendeeList().isPresent()) {
            System.out.println(res.attendeeList().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `request`                                                               | [ListAttendeesRequest](../../models/operations/ListAttendeesRequest.md) | :heavy_check_mark:                                                      | The request object to use for the request.                              |

### Response

**[ListAttendeesResponse](../../models/operations/ListAttendeesResponse.md)**

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

## addAttendees

Add or update 1 to 500 attendees with an Idempotency-Key. A row with the same external_id, else email, else phone as an existing attendee updates it. Identities are stored encrypted.

### Example Usage

<!-- UsageSnippet language="java" operationID="addAttendees" method="post" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/attendees" -->
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
import org.noresponse.models.operations.AddAttendeesRequest;
import org.noresponse.models.operations.AddAttendeesResponse;
import org.noresponse.models.shared.AttendeeBatchRequest;
import org.noresponse.models.shared.AttendeeIdentity;
import org.noresponse.models.shared.Handle;
import org.noresponse.models.shared.HandlePlatform;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AddAttendeesRequest req = AddAttendeesRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("account-acct_7f3a9c")
                .body(AttendeeBatchRequest.builder()
                    .attendees(List.of(
                        AttendeeIdentity.builder()
                            .name("Jordan Example")
                            .externalId("ticket-1042")
                            .email("jordan@company-example.com")
                            .phone("+14155550100")
                            .company("Example Ltd")
                            .city("Melbourne")
                            .handles(List.of(
                                Handle.builder()
                                    .platform(HandlePlatform.TIKTOK)
                                    .handle("jordan_example")
                                    .build()))
                            .build()))
                    .build())
                .build();

        AddAttendeesResponse res = sdk.events().addAttendees()
                .request(req)
                .call();

        if (res.attendeeBatch().isPresent()) {
            System.out.println(res.attendeeBatch().get());
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `request`                                                             | [AddAttendeesRequest](../../models/operations/AddAttendeesRequest.md) | :heavy_check_mark:                                                    | The request object to use for the request.                            |

### Response

**[AddAttendeesResponse](../../models/operations/AddAttendeesResponse.md)**

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

## getAttendee

Read an attendee's report: matched profiles, findings with their evidence and every recorded decision.

### Example Usage

<!-- UsageSnippet language="java" operationID="getAttendee" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/attendees/{attendee_id}" -->
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
import org.noresponse.models.operations.GetAttendeeResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAttendeeResponse res = sdk.events().getAttendee()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .attendeeId("att_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.attendeeReport().isPresent()) {
            System.out.println(res.attendeeReport().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `eventId`                                                                                                                                                              | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `attendeeId`                                                                                                                                                           | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | att_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |

### Response

**[GetAttendeeResponse](../../models/operations/GetAttendeeResponse.md)**

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

## reviewAttendee

Record a cleared, watch or escalated decision with a note. The latest decision is the attendee's; the next screening refreshes everyone on watch or escalated.

### Example Usage

<!-- UsageSnippet language="java" operationID="reviewAttendee" method="post" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/attendees/{attendee_id}/review" -->
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
import org.noresponse.models.operations.ReviewAttendeeRequest;
import org.noresponse.models.operations.ReviewAttendeeResponse;
import org.noresponse.models.shared.AttendeeDecision;
import org.noresponse.models.shared.AttendeeReviewRequest;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReviewAttendeeRequest req = ReviewAttendeeRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .attendeeId("att_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(AttendeeReviewRequest.builder()
                    .decision(AttendeeDecision.CLEARED)
                    .note("Known to the venue team.")
                    .build())
                .build();

        ReviewAttendeeResponse res = sdk.events().reviewAttendee()
                .request(req)
                .call();

        if (res.attendee().isPresent()) {
            System.out.println(res.attendee().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [ReviewAttendeeRequest](../../models/operations/ReviewAttendeeRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[ReviewAttendeeResponse](../../models/operations/ReviewAttendeeResponse.md)**

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

## listScreenings

List an event's screenings, newest first.

### Example Usage

<!-- UsageSnippet language="java" operationID="listScreenings" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/screenings" -->
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
import org.noresponse.models.operations.ListScreeningsRequest;
import org.noresponse.models.operations.ListScreeningsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListScreeningsRequest req = ListScreeningsRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();

        ListScreeningsResponse res = sdk.events().listScreenings()
                .request(req)
                .call();

        if (res.screeningList().isPresent()) {
            System.out.println(res.screeningList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [ListScreeningsRequest](../../models/operations/ListScreeningsRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[ListScreeningsResponse](../../models/operations/ListScreeningsResponse.md)**

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

## startScreening

Start a screening of new attendees and everyone on watch or escalated, with an Idempotency-Key. It finishes completed, partial or failed minutes to hours later; poll it. The answer carries estimated_spend; a screening whose estimate exceeds what the daily screening cap has left answers 409 screening_cap_exceeded. The test environment screens with the synthetic connector, which makes up deterministic profiles, posts and findings.

### Example Usage

<!-- UsageSnippet language="java" operationID="startScreening" method="post" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/screenings" -->
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
import org.noresponse.models.operations.StartScreeningRequest;
import org.noresponse.models.operations.StartScreeningResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ScreeningDepth;
import org.noresponse.models.shared.ScreeningRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        StartScreeningRequest req = StartScreeningRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("account-acct_7f3a9c")
                .body(ScreeningRequest.builder()
                    .depth(ScreeningDepth.STANDARD)
                    .platforms(List.of())
                    .build())
                .build();

        StartScreeningResponse res = sdk.events().startScreening()
                .request(req)
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `request`                                                                 | [StartScreeningRequest](../../models/operations/StartScreeningRequest.md) | :heavy_check_mark:                                                        | The request object to use for the request.                                |

### Response

**[StartScreeningResponse](../../models/operations/StartScreeningResponse.md)**

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

## getScreening

Read a screening's status and progress.

### Example Usage

<!-- UsageSnippet language="java" operationID="getScreening" method="get" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/screenings/{screening_id}" -->
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
import org.noresponse.models.operations.GetScreeningResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetScreeningResponse res = sdk.events().getScreening()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .screeningId("scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `eventId`                                                                                                                                                              | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `screeningId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    | scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |

### Response

**[GetScreeningResponse](../../models/operations/GetScreeningResponse.md)**

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

## cancelScreening

Stop a queued or running screening. Attendees already screened keep their results.

### Example Usage

<!-- UsageSnippet language="java" operationID="cancelScreening" method="post" path="/v1/workspaces/{workspace_id}/events/{environment}/{event_id}/screenings/{screening_id}/cancel" -->
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
import org.noresponse.models.operations.CancelScreeningRequest;
import org.noresponse.models.operations.CancelScreeningResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ScreeningCancelRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CancelScreeningRequest req = CancelScreeningRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .eventId("evn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .screeningId("scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(ScreeningCancelRequest.builder()
                    .build())
                .build();

        CancelScreeningResponse res = sdk.events().cancelScreening()
                .request(req)
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [CancelScreeningRequest](../../models/operations/CancelScreeningRequest.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[CancelScreeningResponse](../../models/operations/CancelScreeningResponse.md)**

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