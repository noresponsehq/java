# Members

## Overview

Scoped workspace membership automation.

### Available Operations

* [list](#list) - List a workspace's members.
* [update](#update) - Change a member's role.
* [remove](#remove) - Remove a member.

## list

Read the workspace directory with a live key carrying members or members_manage. Member identities and grants belong to this workspace. Owner-controlled issuance limits team automation to authorised service credentials.

### Example Usage

<!-- UsageSnippet language="java" operationID="listMembers" method="get" path="/v1/workspaces/{workspace_id}/members" example="list" -->
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
import org.noresponse.models.operations.ListMembersResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListMembersResponse res = sdk.members().list()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.memberList().isPresent()) {
            System.out.println(res.memberList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |

### Response

**[ListMembersResponse](../../models/operations/ListMembersResponse.md)**

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

Update a non-owner member role, environment access or sensitive permission exclusions with a live members_manage key. Owner changes, identity binding and support access remain outside this scope. The current delegation ceiling is checked within the change transaction.

### Example Usage: member

<!-- UsageSnippet language="java" operationID="updateMember" method="patch" path="/v1/workspaces/{workspace_id}/members/{member_id}" example="member" -->
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
import org.noresponse.models.operations.UpdateMemberResponse;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.models.shared.MemberUpdateRequestEnvironment;
import org.noresponse.models.shared.Role;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateMemberResponse res = sdk.members().update()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .memberId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(MemberUpdateRequest.builder()
                    .role(Role.VIEWER)
                    .environments(List.of(
                        MemberUpdateRequestEnvironment.TEST))
                    .build())
                .call();

        if (res.member().isPresent()) {
            System.out.println(res.member().get());
        }
    }
}
```
### Example Usage: role

<!-- UsageSnippet language="java" operationID="updateMember" method="patch" path="/v1/workspaces/{workspace_id}/members/{member_id}" example="role" -->
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
import org.noresponse.models.operations.UpdateMemberResponse;
import org.noresponse.models.shared.MemberUpdateRequest;
import org.noresponse.models.shared.Role;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateMemberResponse res = sdk.members().update()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .memberId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(MemberUpdateRequest.builder()
                    .role(Role.ADMIN)
                    .build())
                .call();

        if (res.member().isPresent()) {
            System.out.println(res.member().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `memberId`                                                                                                                         | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | The member's mem_ id. A member of another workspace, or a removed one, answers 404.                                                | mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `body`                                                                                                                             | [MemberUpdateRequest](../../models/shared/MemberUpdateRequest.md)                                                                  | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                | {<br/>"role": "viewer",<br/>"environments": [<br/>"test"<br/>]<br/>}                                                               |

### Response

**[UpdateMemberResponse](../../models/operations/UpdateMemberResponse.md)**

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

## remove

Remove a non-owner member with a live members_manage key. Subsequent access is refused and personal credentials are revoked. Independently owned service credentials follow their own lifecycle.

### Example Usage

<!-- UsageSnippet language="java" operationID="removeMember" method="delete" path="/v1/workspaces/{workspace_id}/members/{member_id}" -->
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
import org.noresponse.models.operations.RemoveMemberResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RemoveMemberResponse res = sdk.members().remove()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .memberId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `memberId`                                                                                                                         | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | The member's mem_ id. A member of another workspace, or a removed one, answers 404.                                                | mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |

### Response

**[RemoveMemberResponse](../../models/operations/RemoveMemberResponse.md)**

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