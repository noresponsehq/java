# Integrations

## Overview

Hosted endpoints that answer your sign-up and sign-in hooks.

### Available Operations

* [create](#create) - Register a hosted provider integration.
* [list](#list) - List the workspace's integrations.
* [delete](#delete) - Delete an integration.
* [rotate](#rotate) - Replace an integration's secret.

## create

Registers a WorkOS or Auth0 integration and returns its hosted endpoint, url, so a sign-up or sign-in is scored with no server of your own. For WorkOS, send the Actions signing secret from the WorkOS dashboard and point the user registration and authentication Actions at url. For Auth0, the answer's secret is the shared secret of the NoResponse Auth0 Action, shown once: store it as an Action secret. hook_points defaults to both pre_registration and post_login; an event that is not listed is allowed without an assessment. The endpoint waits for the full assessment for at most 2.5 seconds (WorkOS) or 4.5 seconds (Auth0), denies only when risk.block is true and allows on any failure. Requires the integrations scope; at most 10 per account. Not idempotent: a retry after a lost answer registers another integration.

### Example Usage: auth0

<!-- UsageSnippet language="java" operationID="createIntegration" method="post" path="/v1/integrations" example="auth0" -->
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
import org.noresponse.models.operations.CreateIntegrationResponse;
import org.noresponse.models.shared.IntegrationPlatform;
import org.noresponse.models.shared.IntegrationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        IntegrationRequest req = IntegrationRequest.builder()
                .platform(IntegrationPlatform.AUTH0)
                .build();

        CreateIntegrationResponse res = sdk.integrations().create()
                .request(req)
                .call();

        if (res.integration().isPresent()) {
            System.out.println(res.integration().get());
        }
    }
}
```
### Example Usage: workos

<!-- UsageSnippet language="java" operationID="createIntegration" method="post" path="/v1/integrations" example="workos" -->
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
import org.noresponse.models.operations.CreateIntegrationResponse;
import org.noresponse.models.shared.IntegrationHookPoint;
import org.noresponse.models.shared.IntegrationPlatform;
import org.noresponse.models.shared.IntegrationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        IntegrationRequest req = IntegrationRequest.builder()
                .platform(IntegrationPlatform.WORKOS)
                .hookPoints(List.of(
                    IntegrationHookPoint.PRE_REGISTRATION,
                    IntegrationHookPoint.POST_LOGIN))
                .signingSecret("wos_action_secret_k3Yx8pQ2mN7vR4tW")
                .build();

        CreateIntegrationResponse res = sdk.integrations().create()
                .request(req)
                .call();

        if (res.integration().isPresent()) {
            System.out.println(res.integration().get());
        }
    }
}
```

### Parameters

| Parameter                                                       | Type                                                            | Required                                                        | Description                                                     |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| `request`                                                       | [IntegrationRequest](../../models/shared/IntegrationRequest.md) | :heavy_check_mark:                                              | The request object to use for the request.                      |

### Response

**[CreateIntegrationResponse](../../models/operations/CreateIntegrationResponse.md)**

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

Every integration, oldest first. Listings carry no secrets. Requires the integrations scope.

### Example Usage

<!-- UsageSnippet language="java" operationID="listIntegrations" method="get" path="/v1/integrations" example="list" -->
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
import org.noresponse.models.operations.ListIntegrationsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListIntegrationsResponse res = sdk.integrations().list()
                .call();

        if (res.integrationList().isPresent()) {
            System.out.println(res.integrationList().get());
        }
    }
}
```

### Response

**[ListIntegrationsResponse](../../models/operations/ListIntegrationsResponse.md)**

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

Removes the integration and its secrets; its endpoint answers 404 from then on, so remove the Action from the provider first. Requires the integrations scope. An unknown or already deleted integration answers 404.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteIntegration" method="delete" path="/v1/integrations/{integration_id}" -->
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
import org.noresponse.models.operations.DeleteIntegrationResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteIntegrationResponse res = sdk.integrations().delete()
                .integrationId("int_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                                                                             | Type                                                                                                                                  | Required                                                                                                                              | Description                                                                                                                           | Example                                                                                                                               |
| ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `integrationId`                                                                                                                       | *String*                                                                                                                              | :heavy_check_mark:                                                                                                                    | Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another workspace's id, answers 404. | int_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                        |

### Response

**[DeleteIntegrationResponse](../../models/operations/DeleteIntegrationResponse.md)**

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

## rotate

For Auth0, send {} and the answer's secret is the new shared secret, shown once. For WorkOS, regenerate the Actions secret in the WorkOS dashboard and send it as signing_secret. The replaced secret is still accepted for 24 hours (previous_secret_expires_at). Requires the integrations scope. Not idempotent: a retry after a lost answer rotates again.

### Example Usage: auth0

<!-- UsageSnippet language="java" operationID="rotateIntegrationSecret" method="post" path="/v1/integrations/{integration_id}/rotate" example="auth0" -->
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
import org.noresponse.models.operations.RotateIntegrationSecretResponse;
import org.noresponse.models.shared.IntegrationRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RotateIntegrationSecretResponse res = sdk.integrations().rotate()
                .integrationId("int_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(IntegrationRotationRequest.builder()
                    .build())
                .call();

        if (res.integration().isPresent()) {
            System.out.println(res.integration().get());
        }
    }
}
```
### Example Usage: workos

<!-- UsageSnippet language="java" operationID="rotateIntegrationSecret" method="post" path="/v1/integrations/{integration_id}/rotate" example="workos" -->
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
import org.noresponse.models.operations.RotateIntegrationSecretResponse;
import org.noresponse.models.shared.IntegrationRotationRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RotateIntegrationSecretResponse res = sdk.integrations().rotate()
                .integrationId("int_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(IntegrationRotationRequest.builder()
                    .signingSecret("wos_action_secret_Z9cD1eF5gH0jL4sP")
                    .build())
                .call();

        if (res.integration().isPresent()) {
            System.out.println(res.integration().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                             | Type                                                                                                                                  | Required                                                                                                                              | Description                                                                                                                           | Example                                                                                                                               |
| ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `integrationId`                                                                                                                       | *String*                                                                                                                              | :heavy_check_mark:                                                                                                                    | Path segment, matched case-sensitively. A value that does not match the integration_id shape, or another workspace's id, answers 404. | int_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                        |
| `body`                                                                                                                                | [IntegrationRotationRequest](../../models/shared/IntegrationRotationRequest.md)                                                       | :heavy_check_mark:                                                                                                                    | N/A                                                                                                                                   |                                                                                                                                       |

### Response

**[RotateIntegrationSecretResponse](../../models/operations/RotateIntegrationSecretResponse.md)**

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