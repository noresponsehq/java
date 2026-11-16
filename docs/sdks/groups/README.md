# Groups

## Overview

Groups, lists of people, screenings and reviewer decisions.

### Available Operations

* [list](#list) - List an environment's groups.
* [create](#create) - Create a group to screen.
* [get](#get) - Read a group.
* [update](#update) - Change a group.
* [delete](#delete) - Erase a group.
* [listPeople](#listpeople) - List a group's people.
* [addPeople](#addpeople) - Add or update a batch of people.
* [getPerson](#getperson) - Read a person's report.
* [removePerson](#removeperson) - Remove a person from a group
* [reviewPerson](#reviewperson) - Record a decision on a person.
* [judgeRecord](#judgerecord) - Join one of a person's records to them, or reject it.
* [startSearch](#startsearch) - Search again on a value of one of a person's records.
* [runSearch](#runsearch) - Run a counted password or hash search.
* [listScreenings](#listscreenings) - List a group's screenings.
* [startScreening](#startscreening) - Start a screening.
* [getScreening](#getscreening) - Read a screening.
* [cancelScreening](#cancelscreening) - Cancel a screening.
* [exportPeople](#exportpeople) - Export the people in a group.

## list

Groups, newest first. The people scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="listGroups" method="get" path="/v1/groups" -->
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
import org.noresponse.models.operations.ListGroupsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListGroupsResponse res = sdk.groups().list()
                .limit(20L)
                .call();

        if (res.groupList().isPresent()) {
            System.out.println(res.groupList().get());
        }
    }
}
```

### Parameters

| Parameter                        | Type                             | Required                         | Description                      |
| -------------------------------- | -------------------------------- | -------------------------------- | -------------------------------- |
| `limit`                          | @Nullable *long*                 | :heavy_minus_sign:               | At most this many items.         |
| `before`                         | @Nullable *String*               | :heavy_minus_sign:               | Read groups older than this one. |

### Response

**[ListGroupsResponse](../../models/operations/ListGroupsResponse.md)**

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

Creates a group with its name, start, venue, host, the principals it protects and the sensitive topics its screening reads posts against. An environment holds at most 1000 groups. Send an Idempotency-Key to retry safely: the same key and request return the same resource in its current state while it is retained, even after work completes. Reusing the key with different input answers 409 idempotency_key_reused. Use a new key for a deliberate new operation. The people_write scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="createGroup" method="post" path="/v1/groups" -->
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
import org.noresponse.models.operations.CreateGroupResponse;
import org.noresponse.models.shared.GroupRequest;
import org.noresponse.models.shared.Principal;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateGroupResponse res = sdk.groups().create()
                .idempotencyKey("request-01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(GroupRequest.builder()
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

        if (res.group().isPresent()) {
            System.out.println(res.group().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `idempotencyKey`                                                                                                                                                                                                                                                                                                                                         | @Nullable *String*                                                                                                                                                                                                                                                                                                                                       | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                       | Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person. | request-01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                                                                                                                                                                                                       |
| `body`                                                                                                                                                                                                                                                                                                                                                   | [GroupRequest](../../models/shared/GroupRequest.md)                                                                                                                                                                                                                                                                                                      | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                          |

### Response

**[CreateGroupResponse](../../models/operations/CreateGroupResponse.md)**

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

The group and how many people it holds. The people scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="getGroup" method="get" path="/v1/groups/{group_id}" -->
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
import org.noresponse.models.operations.GetGroupResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetGroupResponse res = sdk.groups().get()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.group().isPresent()) {
            System.out.println(res.group().get());
        }
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `groupId`                      | *String*                       | :heavy_check_mark:             | N/A                            | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

### Response

**[GetGroupResponse](../../models/operations/GetGroupResponse.md)**

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

Changes the fields the body names. With expected_version, the change applies only to that version (409 group_conflict otherwise). The people_write scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="updateGroup" method="patch" path="/v1/groups/{group_id}" -->
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
import org.noresponse.models.operations.UpdateGroupResponse;
import org.noresponse.models.shared.GroupUpdate;
import org.noresponse.models.shared.Principal;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateGroupResponse res = sdk.groups().update()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(GroupUpdate.builder()
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

        if (res.group().isPresent()) {
            System.out.println(res.group().get());
        }
    }
}
```

### Parameters

| Parameter                                         | Type                                              | Required                                          | Description                                       | Example                                           |
| ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| `groupId`                                         | *String*                                          | :heavy_check_mark:                                | N/A                                               | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                    |
| `body`                                            | [GroupUpdate](../../models/shared/GroupUpdate.md) | :heavy_check_mark:                                | N/A                                               |                                                   |

### Response

**[UpdateGroupResponse](../../models/operations/UpdateGroupResponse.md)**

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

Deletes the group, its memberships and its review context, and cancels unfinished group work. Workspace people, source evidence and completed investigations remain available. Retained investigations no longer name the deleted group. The delete scope is required; group read, write, investigation and review access do not grant deletion. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteGroup" method="delete" path="/v1/groups/{group_id}" -->
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
import org.noresponse.models.operations.DeleteGroupResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteGroupResponse res = sdk.groups().delete()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `groupId`                      | *String*                       | :heavy_check_mark:             | N/A                            | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

### Response

**[DeleteGroupResponse](../../models/operations/DeleteGroupResponse.md)**

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

## listPeople

The roster in upload order, with each person's role, match state, highest finding band and decision. Filter by band (or none), decision (or undecided) and role (or unstated). The people scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="listPeople" method="get" path="/v1/groups/{group_id}/people" -->
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
import org.noresponse.models.operations.ListPeopleRequest;
import org.noresponse.models.operations.ListPeopleResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListPeopleRequest req = ListPeopleRequest.builder()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();

        ListPeopleResponse res = sdk.groups().listPeople()
                .request(req)
                .call();

        if (res.personList().isPresent()) {
            System.out.println(res.personList().get());
        }
    }
}
```

### Parameters

| Parameter                                                         | Type                                                              | Required                                                          | Description                                                       |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `request`                                                         | [ListPeopleRequest](../../models/operations/ListPeopleRequest.md) | :heavy_check_mark:                                                | The request object to use for the request.                        |

### Response

**[ListPeopleResponse](../../models/operations/ListPeopleResponse.md)**

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

## addPeople

Adds 1 to 500 workspace people to this group, at most 512 KiB. An explicit person_id or external_id reuses the named workspace person. A reference-only row keeps submitted details; an identity supplied with that reference replaces those details. A shared email or phone only suggests matches and does not merge people. A changed identity clears current group screening summaries; prior investigation history remains available. Identities are encrypted under the workspace key. Idempotency-Key is required: the same key and body return the original import result with Idempotent-Replayed: true while its people remain retained. An expired or deleted imported person answers 404; expired or deleted match suggestions are omitted. A different body answers 409 idempotency_key_reused. A group holds at most 20000 people. The people_write scope grants this operation; groups is also accepted. Suggested matches require people-read access. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="addPeople" method="post" path="/v1/groups/{group_id}/people" -->
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
import org.noresponse.models.operations.AddPeopleResponse;
import org.noresponse.models.shared.PersonBatchRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AddPeopleResponse res = sdk.groups().addPeople()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("account-acct_7f3a9c")
                .body(PersonBatchRequest.builder()
                    .people(List.of())
                    .build())
                .call();

        if (res.personBatch().isPresent()) {
            System.out.println(res.personBatch().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                         | Type                                                                                                                                                              | Required                                                                                                                                                          | Description                                                                                                                                                       | Example                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `groupId`                                                                                                                                                         | *String*                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                | N/A                                                                                                                                                               | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                    |
| `idempotencyKey`                                                                                                                                                  | *String*                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours. | account-acct_7f3a9c                                                                                                                                               |
| `body`                                                                                                                                                            | [PersonBatchRequest](../../models/shared/PersonBatchRequest.md)                                                                                                   | :heavy_check_mark:                                                                                                                                                | N/A                                                                                                                                                               |                                                                                                                                                                   |

### Response

**[AddPeopleResponse](../../models/operations/AddPeopleResponse.md)**

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

## getPerson

Returns submitted details, this group's latest screening evidence and contextual decisions, and the other groups that explicitly include the same workspace person. Membership requires an explicit person or customer reference. The people scope grants this operation; groups is also accepted. Each successful read is durably audited as person.viewed. Full source records, posts and media are returned by default. Explicit evidence.read denial keeps submitted details, coverage, job status and decision summaries, hides collected evidence and free-text notes, and sets evidence_restricted to true. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="getPerson" method="get" path="/v1/groups/{group_id}/people/{person_id}" example="records" -->
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
import org.noresponse.models.operations.GetPersonResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetPersonResponse res = sdk.groups().getPerson()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.personReport().isPresent()) {
            System.out.println(res.personReport().get());
        }
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `groupId`                      | *String*                       | :heavy_check_mark:             | N/A                            | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM |
| `personId`                     | *String*                       | :heavy_check_mark:             | N/A                            | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

### Response

**[GetPersonResponse](../../models/operations/GetPersonResponse.md)**

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

## removePerson

Removes this membership and its group review history. Keeps the workspace person, source evidence and completed investigations. Needs delete access. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="removeGroupPerson" method="delete" path="/v1/groups/{group_id}/people/{person_id}" -->
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
import org.noresponse.models.operations.RemoveGroupPersonResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RemoveGroupPersonResponse res = sdk.groups().removePerson()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `groupId`                      | *String*                       | :heavy_check_mark:             | N/A                            | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM |
| `personId`                     | *String*                       | :heavy_check_mark:             | N/A                            | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

### Response

**[RemoveGroupPersonResponse](../../models/operations/RemoveGroupPersonResponse.md)**

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

## reviewPerson

Marks the person cleared, watch or escalated with a note. Decisions are kept in order; the latest is the person's. The next screening refreshes everyone on watch or escalated. The decisions scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="reviewPerson" method="post" path="/v1/groups/{group_id}/people/{person_id}/review" -->
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
import org.noresponse.models.operations.ReviewPersonResponse;
import org.noresponse.models.shared.PersonDecision;
import org.noresponse.models.shared.PersonReviewRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ReviewPersonResponse res = sdk.groups().reviewPerson()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(PersonReviewRequest.builder()
                    .decision(PersonDecision.ESCALATED)
                    .note("Known to the venue team.")
                    .build())
                .call();

        if (res.person().isPresent()) {
            System.out.println(res.person().get());
        }
    }
}
```

### Parameters

| Parameter                                                         | Type                                                              | Required                                                          | Description                                                       | Example                                                           |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `groupId`                                                         | *String*                                                          | :heavy_check_mark:                                                | N/A                                                               | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                    |
| `personId`                                                        | *String*                                                          | :heavy_check_mark:                                                | N/A                                                               | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                    |
| `body`                                                            | [PersonReviewRequest](../../models/shared/PersonReviewRequest.md) | :heavy_check_mark:                                                | N/A                                                               |                                                                   |

### Response

**[ReviewPersonResponse](../../models/operations/ReviewPersonResponse.md)**

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

## judgeRecord

Records a verdict on one of the records the person's latest lookups found: joined makes the record the person's whatever its likelihood, and rejected keeps it a lead. The verdict sticks for as long as a lookup finds the same record, and a later verdict on the same record replaces it. A record the person's latest lookup does not hold answers 404. Each verdict is audited as person.record_judged. The decisions scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="judgeRecord" method="post" path="/v1/groups/{group_id}/people/{person_id}/records" -->
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
import org.noresponse.models.operations.JudgeRecordResponse;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.models.shared.Verdict;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        JudgeRecordResponse res = sdk.groups().judgeRecord()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(RecordVerdictRequest.builder()
                    .recordId("rec_3f9a1c22d4e5b6a73f9a1c22d4e5b6a7")
                    .verdict(Verdict.REJECTED)
                    .build())
                .call();

        if (res.recordVerdict().isPresent()) {
            System.out.println(res.recordVerdict().get());
        }
    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         | Example                                                             |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `groupId`                                                           | *String*                                                            | :heavy_check_mark:                                                  | N/A                                                                 | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                      |
| `personId`                                                          | *String*                                                            | :heavy_check_mark:                                                  | N/A                                                                 | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                      |
| `body`                                                              | [RecordVerdictRequest](../../models/shared/RecordVerdictRequest.md) | :heavy_check_mark:                                                  | N/A                                                                 |                                                                     |

### Response

**[JudgeRecordResponse](../../models/operations/JudgeRecordResponse.md)**

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

## startSearch

Starts a new search on the value of one field of one of the person's records: a lookup's, a profile's or one an earlier search found. Name the record and the field; the value itself is not sent. The field's kind must be email, phone, username, person_name, password, hash, ip or domain. A domain search finds the breach rows of every email address at the domain, which must be a registrable domain or a host under one; any other domain answers 400. Nothing ever searches again by itself: a search starts only when a person asks. The search runs the breach search and the account and phone lookups that take the value's kind, under the environment's source controls, and finishes seconds later. A password or hash search counts first: it stops at counted with rows, how many breach rows hold the value, and runs only when a person runs it. Name a table, as a lookup remaining entry names it, to fetch the rows an earlier search left out, up to 100. What the search finds joins the person's lookup records as leads, each match naming the search; the person report lists the searches, each with the value and the record and field it came from, and its records are scored like the rest. Without an Idempotency-Key, starting the same open search returns it. Its estimate counts against the environment's screening_daily_cap, and one the cap cannot hold answers 409 screening_cap_exceeded. A record or field the person does not hold answers 404. Each search is audited as person.search_started. Send an Idempotency-Key to retry safely: the same key and request return the same resource in its current state while it is retained, even after work completes. Reusing the key with different input answers 409 idempotency_key_reused. Use a new key for a deliberate new operation. The investigations scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="startSearch" method="post" path="/v1/groups/{group_id}/people/{person_id}/searches" -->
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
import org.noresponse.models.operations.StartSearchResponse;
import org.noresponse.models.shared.SearchRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        StartSearchResponse res = sdk.groups().startSearch()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("request-01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(SearchRequest.builder()
                    .recordId("rec_3f9a1c22d4e5b6a73f9a1c22d4e5b6a7")
                    .field("username")
                    .table("FORUM_EXAMPLE_COM_2019")
                    .build())
                .call();

        if (res.search().isPresent()) {
            System.out.println(res.search().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `groupId`                                                                                                                                                                                                                                                                                                                                                | *String*                                                                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                      | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                                                                                                                                                                                                           |
| `personId`                                                                                                                                                                                                                                                                                                                                               | *String*                                                                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                      | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                                                                                                                                                                                                           |
| `idempotencyKey`                                                                                                                                                                                                                                                                                                                                         | @Nullable *String*                                                                                                                                                                                                                                                                                                                                       | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                       | Opaque request key, 16 to 128 characters. Repeating the key and request returns the same resource in its current state while it is retained. A different request with the same key answers 409. Use a new key for a deliberate new operation. Keys are scoped to the environment and operation; person searches also scope them to the group and person. | request-01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                                                                                                                                                                                                       |
| `body`                                                                                                                                                                                                                                                                                                                                                   | [SearchRequest](../../models/shared/SearchRequest.md)                                                                                                                                                                                                                                                                                                    | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                          |

### Response

**[StartSearchResponse](../../models/operations/StartSearchResponse.md)**

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

## runSearch

Runs a password or hash search once a person has seen its count. A search still counting answers 409 group_conflict; one already queued, running or finished answers as it is. The body is an empty object. Its estimate counts against the environment's screening_daily_cap. Each run is audited as person.search_run. The investigations scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="runSearch" method="post" path="/v1/groups/{group_id}/people/{person_id}/searches/{search_id}/run" -->
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
import org.noresponse.models.operations.RunSearchResponse;
import org.noresponse.models.shared.ScreeningCancelRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RunSearchResponse res = sdk.groups().runSearch()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .personId("per_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .searchId("srh_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(ScreeningCancelRequest.builder()
                    .build())
                .call();

        if (res.search().isPresent()) {
            System.out.println(res.search().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             | Example                                                                 |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `groupId`                                                               | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `personId`                                                              | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | per_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `searchId`                                                              | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | srh_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `body`                                                                  | [ScreeningCancelRequest](../../models/shared/ScreeningCancelRequest.md) | :heavy_check_mark:                                                      | N/A                                                                     |                                                                         |

### Response

**[RunSearchResponse](../../models/operations/RunSearchResponse.md)**

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

Screenings, newest first. The people scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="listScreenings" method="get" path="/v1/groups/{group_id}/screenings" -->
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
import org.noresponse.models.operations.ListScreeningsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListScreeningsResponse res = sdk.groups().listScreenings()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .limit(20L)
                .call();

        if (res.screeningList().isPresent()) {
            System.out.println(res.screeningList().get());
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `groupId`                            | *String*                             | :heavy_check_mark:                   | N/A                                  | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM       |
| `limit`                              | @Nullable *long*                     | :heavy_minus_sign:                   | At most this many items.             |                                      |
| `before`                             | @Nullable *String*                   | :heavy_minus_sign:                   | Read screenings older than this one. |                                      |

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

Queues a screening of every person no screening has reached and everyone on watch or escalated. One screening of a group runs at a time; it finishes completed, partial (some people not covered, counted in people_failed) or failed, and fails with error_code expired after 24 hours. Idempotency-Key is required and binds the body for the screening's life. The 202 body carries estimated_spend, what the selected people would cost at the depth and platforms. A screening whose estimate exceeds the environment's screening_daily_cap, less the day's recorded spend and what open screenings may still spend, answers 409 screening_cap_exceeded and queues nothing. The test environment screens with a deterministic simulator that contacts no source. A platform that no connector serves in the environment answers 409 screening_unavailable. The investigations scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="startScreening" method="post" path="/v1/groups/{group_id}/screenings" -->
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
import org.noresponse.models.operations.StartScreeningResponse;
import org.noresponse.models.shared.ScreeningDepth;
import org.noresponse.models.shared.ScreeningRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        StartScreeningResponse res = sdk.groups().startScreening()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("account-acct_7f3a9c")
                .body(ScreeningRequest.builder()
                    .depth(ScreeningDepth.STANDARD)
                    .platforms(List.of(
                        "bluesky"))
                    .build())
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                         | Type                                                                                                                                                              | Required                                                                                                                                                          | Description                                                                                                                                                       | Example                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `groupId`                                                                                                                                                         | *String*                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                | N/A                                                                                                                                                               | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                    |
| `idempotencyKey`                                                                                                                                                  | *String*                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours. | account-acct_7f3a9c                                                                                                                                               |
| `body`                                                                                                                                                            | [ScreeningRequest](../../models/shared/ScreeningRequest.md)                                                                                                       | :heavy_check_mark:                                                                                                                                                | N/A                                                                                                                                                               |                                                                                                                                                                   |

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

The screening's status and progress. The people scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="getScreening" method="get" path="/v1/groups/{group_id}/screenings/{screening_id}" -->
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

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetScreeningResponse res = sdk.groups().getScreening()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .screeningId("scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                      | Type                           | Required                       | Description                    | Example                        |
| ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ | ------------------------------ |
| `groupId`                      | *String*                       | :heavy_check_mark:             | N/A                            | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM |
| `screeningId`                  | *String*                       | :heavy_check_mark:             | N/A                            | scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM |

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

Stops a queued or running screening. People it already screened keep their results. Cancelling a finished screening answers it unchanged. The body is an empty object. The investigations scope grants this operation. Explicit evidence.read restrictions override default evidence access. The groups scope is also accepted for existing integrations. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="cancelScreening" method="post" path="/v1/groups/{group_id}/screenings/{screening_id}/cancel" -->
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
import org.noresponse.models.operations.CancelScreeningResponse;
import org.noresponse.models.shared.ScreeningCancelRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CancelScreeningResponse res = sdk.groups().cancelScreening()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .screeningId("scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(ScreeningCancelRequest.builder()
                    .build())
                .call();

        if (res.screening().isPresent()) {
            System.out.println(res.screening().get());
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             | Example                                                                 |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `groupId`                                                               | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `screeningId`                                                           | *String*                                                                | :heavy_check_mark:                                                      | N/A                                                                     | scn_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                          |
| `body`                                                                  | [ScreeningCancelRequest](../../models/shared/ScreeningCancelRequest.md) | :heavy_check_mark:                                                      | N/A                                                                     |                                                                         |

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

## exportPeople

Returns one page for export. Each returned person is durably audited as person.exported with the actor, group, person ID and request ID. Requires both people read access and exports. Explicit evidence restrictions also hide free-text decision notes. No raw evidence enters audit logs. Follow next_after until absent for a complete export. The API key selects the workspace and environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="exportPeople" method="get" path="/v1/groups/{group_id}/export" -->
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
import org.noresponse.models.operations.ExportPeopleRequest;
import org.noresponse.models.operations.ExportPeopleResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ExportPeopleRequest req = ExportPeopleRequest.builder()
                .groupId("grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .build();

        ExportPeopleResponse res = sdk.groups().exportPeople()
                .request(req)
                .call();

        if (res.personList().isPresent()) {
            System.out.println(res.personList().get());
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `request`                                                             | [ExportPeopleRequest](../../models/operations/ExportPeopleRequest.md) | :heavy_check_mark:                                                    | The request object to use for the request.                            |

### Response

**[ExportPeopleResponse](../../models/operations/ExportPeopleResponse.md)**

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