# Invites

## Overview

Workspace-bound invitation lifecycle.

### Available Operations

* [create](#create) - Invite a member.
* [list](#list) - List pending invites.
* [revoke](#revoke) - Revoke an invite.
* [resend](#resend) - Reissue an invitation for delivery.

## create

Create a workspace-bound invite for a non-owner role with a live members_manage key. The new token is returned once. Set external_user_id to bind acceptance to an exact verified provider subject; email is contact information. Creating a token does not send an email.

### Example Usage: bound

<!-- UsageSnippet language="java" operationID="createInvite" method="post" path="/v1/workspaces/{workspace_id}/invites" example="bound" -->
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
import org.noresponse.models.operations.CreateInviteResponse;
import org.noresponse.models.shared.InviteRequest;
import org.noresponse.models.shared.Role;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateInviteResponse res = sdk.invites().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(InviteRequest.builder()
                    .role(Role.VIEWER)
                    .externalUserId("user_2EXAMPLE")
                    .build())
                .call();

        if (res.invite().isPresent()) {
            System.out.println(res.invite().get());
        }
    }
}
```
### Example Usage: invite

<!-- UsageSnippet language="java" operationID="createInvite" method="post" path="/v1/workspaces/{workspace_id}/invites" example="invite" -->
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
import org.noresponse.models.operations.CreateInviteResponse;
import org.noresponse.models.shared.InviteRequest;
import org.noresponse.models.shared.InviteRequestEnvironment;
import org.noresponse.models.shared.Role;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateInviteResponse res = sdk.invites().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(InviteRequest.builder()
                    .role(Role.VIEWER)
                    .email("alex@example.com")
                    .externalUserId("user_teammate")
                    .environments(List.of(
                        InviteRequestEnvironment.TEST))
                    .build())
                .call();

        if (res.invite().isPresent()) {
            System.out.println(res.invite().get());
        }
    }
}
```
### Example Usage: open

<!-- UsageSnippet language="java" operationID="createInvite" method="post" path="/v1/workspaces/{workspace_id}/invites" example="open" -->
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
import org.noresponse.models.operations.CreateInviteResponse;
import org.noresponse.models.shared.InviteRequest;
import org.noresponse.models.shared.Role;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateInviteResponse res = sdk.invites().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(InviteRequest.builder()
                    .role(Role.DEVELOPER)
                    .email("dev@example.com")
                    .build())
                .call();

        if (res.invite().isPresent()) {
            System.out.println(res.invite().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `body`                                                                                                                             | [InviteRequest](../../models/shared/InviteRequest.md)                                                                              | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                | {<br/>"role": "viewer",<br/>"email": "alex@example.com",<br/>"external_user_id": "user_teammate",<br/>"environments": [<br/>"test"<br/>]<br/>} |

### Response

**[CreateInviteResponse](../../models/operations/CreateInviteResponse.md)**

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

## list

Read pending workspace invitations with a live members_manage key. Tokens are excluded. Expiry and token reissue timestamps describe invitation state; they do not establish email delivery.

### Example Usage

<!-- UsageSnippet language="java" operationID="listInvites" method="get" path="/v1/workspaces/{workspace_id}/invites" example="list" -->
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
import org.noresponse.models.operations.ListInvitesResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListInvitesResponse res = sdk.invites().list()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.inviteList().isPresent()) {
            System.out.println(res.inviteList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |

### Response

**[ListInvitesResponse](../../models/operations/ListInvitesResponse.md)**

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

## revoke

Revoke a pending invitation with a live members_manage key. A revoked token cannot be accepted. Owner-role invitations remain outside machine delegation.

### Example Usage

<!-- UsageSnippet language="java" operationID="revokeInvite" method="delete" path="/v1/workspaces/{workspace_id}/invites/{invite_id}" -->
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
import org.noresponse.models.operations.RevokeInviteResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RevokeInviteResponse res = sdk.invites().revoke()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .inviteId("inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `inviteId`                                                                                                                         | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.                                       | inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |

### Response

**[RevokeInviteResponse](../../models/operations/RevokeInviteResponse.md)**

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

## resend

Reissue a pending non-owner invitation with a live members_manage key. The old token is invalidated and the new token is returned once with renewed expiry. Its role and permissions are preserved. This operation does not prove email delivery and is not automatically retried.

### Example Usage

<!-- UsageSnippet language="java" operationID="resendInvite" method="post" path="/v1/workspaces/{workspace_id}/invites/{invite_id}/resend" example="invite" -->
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
import org.noresponse.models.operations.ResendInviteResponse;
import org.noresponse.models.shared.InviteResendRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ResendInviteResponse res = sdk.invites().resend()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .inviteId("inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(InviteResendRequest.builder()
                    .build())
                .call();

        if (res.invite().isPresent()) {
            System.out.println(res.invite().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `inviteId`                                                                                                                         | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | The invite's inv_ id. An invite of another workspace, or one no longer pending, answers 404.                                       | inv_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `body`                                                                                                                             | [InviteResendRequest](../../models/shared/InviteResendRequest.md)                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[ResendInviteResponse](../../models/operations/ResendInviteResponse.md)**

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