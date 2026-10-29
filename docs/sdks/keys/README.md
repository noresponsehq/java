# Keys

## Overview

Your own API keys.

### Available Operations

* [rotate](#rotate) - Rotate the API key that signs the request.
* [create](#create) - Create an API key.
* [list](#list) - List API keys.
* [revoke](#revoke) - Revoke an API key.

## rotate

Mints a new key with the same scopes as the key that signs the request and returns it once. The presented key keeps working for overlap_seconds (default 24 hours, from 60 seconds to 7 days), then answers 401. A key accepts one rotation: rotating again with a key already in its overlap answers 409 key_rotation_in_progress, so rotate with the new key. Rotation is not idempotent: a retry after a lost answer mints another key.

### Example Usage: default

<!-- UsageSnippet language="java" operationID="rotateApiKey" method="post" path="/v1/keys/rotate" example="default" -->
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
import org.noresponse.models.operations.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        KeyRotationRequest req = KeyRotationRequest.builder()
                .build();

        RotateApiKeyResponse res = sdk.keys().rotate()
                .request(req)
                .call();

        if (res.keyRotation().isPresent()) {
            System.out.println(res.keyRotation().get());
        }
    }
}
```
### Example Usage: one_hour

<!-- UsageSnippet language="java" operationID="rotateApiKey" method="post" path="/v1/keys/rotate" example="one_hour" -->
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
import org.noresponse.models.operations.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        KeyRotationRequest req = KeyRotationRequest.builder()
                .overlapSeconds(3600L)
                .build();

        RotateApiKeyResponse res = sdk.keys().rotate()
                .request(req)
                .call();

        if (res.keyRotation().isPresent()) {
            System.out.println(res.keyRotation().get());
        }
    }
}
```
### Example Usage: rotated

<!-- UsageSnippet language="java" operationID="rotateApiKey" method="post" path="/v1/keys/rotate" example="rotated" -->
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
import org.noresponse.models.operations.RotateApiKeyResponse;
import org.noresponse.models.shared.KeyRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        KeyRotationRequest req = KeyRotationRequest.builder()
                .build();

        RotateApiKeyResponse res = sdk.keys().rotate()
                .request(req)
                .call();

        if (res.keyRotation().isPresent()) {
            System.out.println(res.keyRotation().get());
        }
    }
}
```

### Parameters

| Parameter                                                       | Type                                                            | Required                                                        | Description                                                     |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| `request`                                                       | [KeyRotationRequest](../../models/shared/KeyRotationRequest.md) | :heavy_check_mark:                                              | The request object to use for the request.                      |

### Response

**[RotateApiKeyResponse](../../models/operations/RotateApiKeyResponse.md)**

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

Create an API key in the signing key environment with authority no greater than the issuer holds. Requires management. Optional name, purpose, kind and expiry describe its lifecycle. Send request_id for safe issuance retries: the first successful response returns the secret once; a repeat returns existing metadata without the secret, and changed parameters conflict. Without request_id a retry can create another key. A service credential is independent of its issuer; personal credentials require an authenticated member and retire when that member loses authority.

### Example Usage: live

<!-- UsageSnippet language="java" operationID="createKey" method="post" path="/v1/workspaces/{workspace_id}/keys" example="live" -->
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
import org.noresponse.models.operations.CreateKeyResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.KeyRequest;
import org.noresponse.models.shared.KeyRequestScope;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateKeyResponse res = sdk.keys().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(KeyRequest.builder()
                    .environment(KeyEnvironment.LIVE)
                    .scopes(List.of(
                        KeyRequestScope.ASSESS))
                    .build())
                .call();

        if (res.key().isPresent()) {
            System.out.println(res.key().get());
        }
    }
}
```
### Example Usage: test

<!-- UsageSnippet language="java" operationID="createKey" method="post" path="/v1/workspaces/{workspace_id}/keys" example="test" -->
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
import org.noresponse.models.operations.CreateKeyResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.KeyRequest;
import org.noresponse.models.shared.KeyRequestScope;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateKeyResponse res = sdk.keys().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(KeyRequest.builder()
                    .environment(KeyEnvironment.TEST)
                    .scopes(List.of(
                        KeyRequestScope.ASSESS,
                        KeyRequestScope.HISTORY,
                        KeyRequestScope.WEBHOOKS))
                    .build())
                .call();

        if (res.key().isPresent()) {
            System.out.println(res.key().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `body`                                                                                                                             | [KeyRequest](../../models/shared/KeyRequest.md)                                                                                    | :heavy_check_mark:                                                                                                                 | N/A                                                                                                                                |                                                                                                                                    |

### Response

**[CreateKeyResponse](../../models/operations/CreateKeyResponse.md)**

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

Lists the keys of the signing key's environment, newest first, at most 200, revoked and expired keys included, without secrets: each key's id, prefix, scopes, status, and who created and revoked it. Requires the management scope. Read-only and safe to retry.

### Example Usage

<!-- UsageSnippet language="java" operationID="listKeys" method="get" path="/v1/workspaces/{workspace_id}/keys" example="list" -->
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
import org.noresponse.models.operations.ListKeysResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListKeysResponse res = sdk.keys().list()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.keyList().isPresent()) {
            System.out.println(res.keyList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |

### Response

**[ListKeysResponse](../../models/operations/ListKeysResponse.md)**

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

Revokes a key of the signing key's environment at once: its next request answers 401. Requires the management scope. A key of the other environment or of another workspace answers 404 not_found. A key cannot revoke itself (409 self_revocation): rotate it with keys.rotate, or revoke it with another management key. Revoking a revoked key changes nothing and answers the key as it was revoked, so a retry is safe.

### Example Usage

<!-- UsageSnippet language="java" operationID="revokeKey" method="delete" path="/v1/workspaces/{workspace_id}/keys/{key_id}" example="key" -->
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
import org.noresponse.models.operations.RevokeKeyResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RevokeKeyResponse res = sdk.keys().revoke()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .keyId("01JA2ZE4K5M6N7P8Q9R0S1T2V3")
                .call();

        if (res.key().isPresent()) {
            System.out.println(res.key().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                          | Type                                                                                                                               | Required                                                                                                                           | Description                                                                                                                        | Example                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                      | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404. | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                     |
| `keyId`                                                                                                                            | *String*                                                                                                                           | :heavy_check_mark:                                                                                                                 | The key's id. A key of another workspace answers 404, as does a key of the other environment to an API key.                        | 01JA2ZE4K5M6N7P8Q9R0S1T2V3                                                                                                         |

### Response

**[RevokeKeyResponse](../../models/operations/RevokeKeyResponse.md)**

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