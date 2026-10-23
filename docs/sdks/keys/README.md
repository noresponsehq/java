# Keys

## Overview

Your own API keys.

### Available Operations

* [rotate](#rotate) - Rotate the API key that signs the request.

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