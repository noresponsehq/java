# Assessments

## Overview

Signup and gateway assessments.

### Available Operations

* [create](#create) - Assess identifiers for a signup or gateway decision.
* [get](#get) - Poll the authoritative assessment of one assessment.
* [recordOutcome](#recordoutcome) - Report what was later learned about an assessed account.
* [query](#query) - Page through one account's unexpired assessments.

## create

Returns within the deadline (3000 ms at most) with a complete, partial or pending assessment: 200 carries complete or partial, 202 carries pending with Location and Retry-After. A pending answer has no recommendation; apply your own fallback for pending, 429, 503 or a timeout. risk.block says when your block threshold was reached. Requires the assess scope. Idempotency-Key is required and is bound to the body for 24 hours: the same key with the same body replays the latest assessment with Idempotent-Replayed: true and starts no new work; the same key with a different body answers 409 idempotency_key_reused. response_mode defaults to standard; detailed needs the detailed scope.

### Example Usage: complete

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="complete" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestClientContext;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestIssuedCredential;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;
import org.noresponse.models.shared.AuthContext;
import org.noresponse.models.shared.AuthContextMfa;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .username("new.user")
                        .phone("+61412345678")
                        .name("Alex Example")
                        .ip("203.0.113.45")
                        .build())
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("AU")
                        .build())
                    .requestId("req-2026-10-14-0001")
                    .clientContext(AssessmentRequestClientContext.builder()
                        .timezone("Australia/Sydney")
                        .locale("en-AU")
                        .appVersion("4.12.0")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Safari/537.36")
                        .deviceId("dev_5c1e8a")
                        .sessionId("sess_9b2d41")
                        .clientObservedAt("2026-10-14T08:59:58.000Z")
                        .build())
                    .authContext(AuthContext.builder()
                        .provider("clerk")
                        .issuer("https://example-app.clerk.accounts.dev")
                        .subject("user_2EXAMPLE")
                        .verified(false)
                        .audience("https://app.example.com")
                        .audiences(List.of(
                            "https://app.example.com"))
                        .sessionId("sess_2EXAMPLE")
                        .organizationId("org_2EXAMPLE")
                        .tenantId("tenant_2EXAMPLE")
                        .tokenId("2EXAMPLEJTI")
                        .issuedAt("2026-10-14T08:59:40.000Z")
                        .expiresAt("2026-10-14T09:00:40.000Z")
                        .sessionCreatedAt("2026-10-14T08:57:00.000Z")
                        .authTime("2026-10-14T08:55:00.000Z")
                        .accountCreatedAt("2026-10-14T08:56:00.000Z")
                        .mfa(AuthContextMfa.builder()
                            .methods(List.of(
                                "totp"))
                            .build())
                        .roles(List.of(
                            "admin"))
                        .scopes(List.of(
                            "org:sys_profile:manage"))
                        .build())
                    .issuedCredential(AssessmentRequestIssuedCredential.builder()
                        .sha256("2bdbf5c72f2a36df0ef40fb92203612274d4546f56dba946070a6a79c2401a1b")
                        .build())
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: detailed

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="detailed" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestClientContext;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestIssuedCredential;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;
import org.noresponse.models.shared.AuthContext;
import org.noresponse.models.shared.AuthContextMfa;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .username("new.user")
                        .phone("+61412345678")
                        .name("Alex Example")
                        .ip("203.0.113.45")
                        .build())
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("AU")
                        .build())
                    .requestId("req-2026-10-14-0001")
                    .clientContext(AssessmentRequestClientContext.builder()
                        .timezone("Australia/Sydney")
                        .locale("en-AU")
                        .appVersion("4.12.0")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Safari/537.36")
                        .deviceId("dev_5c1e8a")
                        .sessionId("sess_9b2d41")
                        .clientObservedAt("2026-10-14T08:59:58.000Z")
                        .build())
                    .authContext(AuthContext.builder()
                        .provider("clerk")
                        .issuer("https://example-app.clerk.accounts.dev")
                        .subject("user_2EXAMPLE")
                        .verified(false)
                        .audience("https://app.example.com")
                        .audiences(List.of(
                            "https://app.example.com"))
                        .sessionId("sess_2EXAMPLE")
                        .organizationId("org_2EXAMPLE")
                        .tenantId("tenant_2EXAMPLE")
                        .tokenId("2EXAMPLEJTI")
                        .issuedAt("2026-10-14T08:59:40.000Z")
                        .expiresAt("2026-10-14T09:00:40.000Z")
                        .sessionCreatedAt("2026-10-14T08:57:00.000Z")
                        .authTime("2026-10-14T08:55:00.000Z")
                        .accountCreatedAt("2026-10-14T08:56:00.000Z")
                        .mfa(AuthContextMfa.builder()
                            .methods(List.of(
                                "totp"))
                            .build())
                        .roles(List.of(
                            "admin"))
                        .scopes(List.of(
                            "org:sys_profile:manage"))
                        .build())
                    .issuedCredential(AssessmentRequestIssuedCredential.builder()
                        .sha256("2bdbf5c72f2a36df0ef40fb92203612274d4546f56dba946070a6a79c2401a1b")
                        .build())
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: full

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="full" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentFlow;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .username("new_user_42")
                        .phone("+447700900123")
                        .build())
                    .flow(AssessmentFlow.SIGNUP)
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("GB")
                        .build())
                    .requestId("req-2026-10-14-0001")
                    .deadlineMs(300L)
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: minimal

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="minimal" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentFlow;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .build())
                    .flow(AssessmentFlow.SIGNUP)
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: name_and_ip

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="name_and_ip" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentFlow;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;
import org.noresponse.models.shared.ResponseMode;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_8412")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("jane.doe@example.com")
                        .username("janedoe")
                        .phone("+14155550123")
                        .name("Jane A. Doe")
                        .ip("73.162.18.45")
                        .build())
                    .flow(AssessmentFlow.SIGNUP)
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("US")
                        .build())
                    .requestId("req_01")
                    .deadlineMs(500L)
                    .responseMode(ResponseMode.STANDARD)
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: partial

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="partial" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestClientContext;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestIssuedCredential;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;
import org.noresponse.models.shared.AuthContext;
import org.noresponse.models.shared.AuthContextMfa;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .username("new.user")
                        .phone("+61412345678")
                        .name("Alex Example")
                        .ip("203.0.113.45")
                        .build())
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("AU")
                        .build())
                    .requestId("req-2026-10-14-0001")
                    .clientContext(AssessmentRequestClientContext.builder()
                        .timezone("Australia/Sydney")
                        .locale("en-AU")
                        .appVersion("4.12.0")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Safari/537.36")
                        .deviceId("dev_5c1e8a")
                        .sessionId("sess_9b2d41")
                        .clientObservedAt("2026-10-14T08:59:58.000Z")
                        .build())
                    .authContext(AuthContext.builder()
                        .provider("clerk")
                        .issuer("https://example-app.clerk.accounts.dev")
                        .subject("user_2EXAMPLE")
                        .verified(false)
                        .audience("https://app.example.com")
                        .audiences(List.of(
                            "https://app.example.com"))
                        .sessionId("sess_2EXAMPLE")
                        .organizationId("org_2EXAMPLE")
                        .tenantId("tenant_2EXAMPLE")
                        .tokenId("2EXAMPLEJTI")
                        .issuedAt("2026-10-14T08:59:40.000Z")
                        .expiresAt("2026-10-14T09:00:40.000Z")
                        .sessionCreatedAt("2026-10-14T08:57:00.000Z")
                        .authTime("2026-10-14T08:55:00.000Z")
                        .accountCreatedAt("2026-10-14T08:56:00.000Z")
                        .mfa(AuthContextMfa.builder()
                            .methods(List.of(
                                "totp"))
                            .build())
                        .roles(List.of(
                            "admin"))
                        .scopes(List.of(
                            "org:sys_profile:manage"))
                        .build())
                    .issuedCredential(AssessmentRequestIssuedCredential.builder()
                        .sha256("2bdbf5c72f2a36df0ef40fb92203612274d4546f56dba946070a6a79c2401a1b")
                        .build())
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: standard

<!-- UsageSnippet language="java" operationID="createAssessment" method="post" path="/v1/assessments" example="standard" -->
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
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestClientContext;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.AssessmentRequestIssuedCredential;
import org.noresponse.models.shared.AssessmentRequestPolicyContext;
import org.noresponse.models.shared.AuthContext;
import org.noresponse.models.shared.AuthContextMfa;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateAssessmentResponse res = sdk.assessments().create()
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentRequest.builder()
                    .accountId("acct_7f3a9c")
                    .identifiers(AssessmentRequestIdentifiers.builder()
                        .email("new.user@example.com")
                        .username("new.user")
                        .phone("+61412345678")
                        .name("Alex Example")
                        .ip("203.0.113.45")
                        .build())
                    .policyContext(AssessmentRequestPolicyContext.builder()
                        .region("AU")
                        .build())
                    .requestId("req-2026-10-14-0001")
                    .clientContext(AssessmentRequestClientContext.builder()
                        .timezone("Australia/Sydney")
                        .locale("en-AU")
                        .appVersion("4.12.0")
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0 Safari/537.36")
                        .deviceId("dev_5c1e8a")
                        .sessionId("sess_9b2d41")
                        .clientObservedAt("2026-10-14T08:59:58.000Z")
                        .build())
                    .authContext(AuthContext.builder()
                        .provider("clerk")
                        .issuer("https://example-app.clerk.accounts.dev")
                        .subject("user_2EXAMPLE")
                        .verified(false)
                        .audience("https://app.example.com")
                        .audiences(List.of(
                            "https://app.example.com"))
                        .sessionId("sess_2EXAMPLE")
                        .organizationId("org_2EXAMPLE")
                        .tenantId("tenant_2EXAMPLE")
                        .tokenId("2EXAMPLEJTI")
                        .issuedAt("2026-10-14T08:59:40.000Z")
                        .expiresAt("2026-10-14T09:00:40.000Z")
                        .sessionCreatedAt("2026-10-14T08:57:00.000Z")
                        .authTime("2026-10-14T08:55:00.000Z")
                        .accountCreatedAt("2026-10-14T08:56:00.000Z")
                        .mfa(AuthContextMfa.builder()
                            .methods(List.of(
                                "totp"))
                            .build())
                        .roles(List.of(
                            "admin"))
                        .scopes(List.of(
                            "org:sys_profile:manage"))
                        .build())
                    .issuedCredential(AssessmentRequestIssuedCredential.builder()
                        .sha256("2bdbf5c72f2a36df0ef40fb92203612274d4546f56dba946070a6a79c2401a1b")
                        .build())
                    .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                      | Type                                                                                                                                                           | Required                                                                                                                                                       | Description                                                                                                                                                    | Example                                                                                                                                                        |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `idempotencyKey`                                                                                                                                               | *String*                                                                                                                                                       | :heavy_check_mark:                                                                                                                                             | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the tenant principal and bound to the canonical body for 24 hours. | signup-acct_7f3a9c                                                                                                                                             |
| `body`                                                                                                                                                         | [AssessmentRequest](../../models/shared/AssessmentRequest.md)                                                                                                  | :heavy_check_mark:                                                                                                                                             | N/A                                                                                                                                                            |                                                                                                                                                                |

### Response

**[CreateAssessmentResponse](../../models/operations/CreateAssessmentResponse.md)**

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

Returns the highest assessment_version. Requires the assess or history scope. While status is pending, or partial with a source still running, the response carries Retry-After; poll no faster than it says. Another tenant's assessment, an unknown or expired assessment and a malformed assessment_id all answer 404 not_found. response_mode=detailed needs the detailed scope.

### Example Usage: complete

<!-- UsageSnippet language="java" operationID="getAssessment" method="get" path="/v1/assessments/{assessment_id}" example="complete" -->
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
import org.noresponse.models.operations.GetAssessmentResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAssessmentResponse res = sdk.assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: detailed

<!-- UsageSnippet language="java" operationID="getAssessment" method="get" path="/v1/assessments/{assessment_id}" example="detailed" -->
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
import org.noresponse.models.operations.GetAssessmentResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAssessmentResponse res = sdk.assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: partial

<!-- UsageSnippet language="java" operationID="getAssessment" method="get" path="/v1/assessments/{assessment_id}" example="partial" -->
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
import org.noresponse.models.operations.GetAssessmentResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAssessmentResponse res = sdk.assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: pending

<!-- UsageSnippet language="java" operationID="getAssessment" method="get" path="/v1/assessments/{assessment_id}" example="pending" -->
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
import org.noresponse.models.operations.GetAssessmentResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAssessmentResponse res = sdk.assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```
### Example Usage: standard

<!-- UsageSnippet language="java" operationID="getAssessment" method="get" path="/v1/assessments/{assessment_id}" example="standard" -->
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
import org.noresponse.models.operations.GetAssessmentResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAssessmentResponse res = sdk.assessments().get()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                         | Type                                                                                                              | Required                                                                                                          | Description                                                                                                       | Example                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| `assessmentId`                                                                                                    | *String*                                                                                                          | :heavy_check_mark:                                                                                                | Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400. | asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                    |
| `responseMode`                                                                                                    | @Nullable [ResponseMode](../../models/shared/ResponseMode.md)                                                     | :heavy_minus_sign:                                                                                                | Optional standard or separately authorised detailed customer view; raw is rejected.                               |                                                                                                                   |

### Response

**[GetAssessmentResponse](../../models/operations/GetAssessmentResponse.md)**

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

## recordOutcome

Records what you later learned about an assessed account: confirmed_abuse, account_takeover and chargeback report abuse; false_positive and legitimate withdraw your earlier report; unknown is recorded only. Outcomes are append-only and the latest one wins. Requires the assess scope. Idempotency-Key is required and handled as on POST /v1/assessments: a retry with the same key and body answers the same record with Idempotent-Replayed: true; a different body answers 409. An unknown, expired or foreign assessment answers 404 not_found.

### Example Usage: abuse

<!-- UsageSnippet language="java" operationID="recordAssessmentOutcome" method="post" path="/v1/assessments/{assessment_id}/outcomes" example="abuse" -->
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
import org.noresponse.models.operations.RecordAssessmentOutcomeResponse;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.models.shared.Outcome;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordAssessmentOutcomeResponse res = sdk.assessments().recordOutcome()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentOutcomeRequest.builder()
                    .outcome(Outcome.CONFIRMED_ABUSE)
                    .observedAt("2026-10-20T05:00:00Z")
                    .externalRef("case-123")
                    .notes("Card testing from this account; closed by trust and safety.")
                    .build())
                .call();

        if (res.assessmentOutcome().isPresent()) {
            System.out.println(res.assessmentOutcome().get());
        }
    }
}
```
### Example Usage: recorded

<!-- UsageSnippet language="java" operationID="recordAssessmentOutcome" method="post" path="/v1/assessments/{assessment_id}/outcomes" example="recorded" -->
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
import org.noresponse.models.operations.RecordAssessmentOutcomeResponse;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.models.shared.Outcome;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordAssessmentOutcomeResponse res = sdk.assessments().recordOutcome()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentOutcomeRequest.builder()
                    .outcome(Outcome.LEGITIMATE)
                    .observedAt("2026-10-14T09:04:30.000Z")
                    .externalRef("case-48213")
                    .notes("Chargeback filed two days after signup.")
                    .build())
                .call();

        if (res.assessmentOutcome().isPresent()) {
            System.out.println(res.assessmentOutcome().get());
        }
    }
}
```
### Example Usage: withdrawn

<!-- UsageSnippet language="java" operationID="recordAssessmentOutcome" method="post" path="/v1/assessments/{assessment_id}/outcomes" example="withdrawn" -->
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
import org.noresponse.models.operations.RecordAssessmentOutcomeResponse;
import org.noresponse.models.shared.AssessmentOutcomeRequest;
import org.noresponse.models.shared.Outcome;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordAssessmentOutcomeResponse res = sdk.assessments().recordOutcome()
                .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .idempotencyKey("signup-acct_7f3a9c")
                .body(AssessmentOutcomeRequest.builder()
                    .outcome(Outcome.FALSE_POSITIVE)
                    .build())
                .call();

        if (res.assessmentOutcome().isPresent()) {
            System.out.println(res.assessmentOutcome().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                      | Type                                                                                                                                                           | Required                                                                                                                                                       | Description                                                                                                                                                    | Example                                                                                                                                                        |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `assessmentId`                                                                                                                                                 | *String*                                                                                                                                                       | :heavy_check_mark:                                                                                                                                             | Path segment, matched case-sensitively. A value that does not match the assessment_id shape answers 404, not 400.                                              | asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                 |
| `idempotencyKey`                                                                                                                                               | *String*                                                                                                                                                       | :heavy_check_mark:                                                                                                                                             | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the tenant principal and bound to the canonical body for 24 hours. | signup-acct_7f3a9c                                                                                                                                             |
| `body`                                                                                                                                                         | [AssessmentOutcomeRequest](../../models/shared/AssessmentOutcomeRequest.md)                                                                                    | :heavy_check_mark:                                                                                                                                             | N/A                                                                                                                                                            |                                                                                                                                                                |

### Response

**[RecordAssessmentOutcomeResponse](../../models/operations/RecordAssessmentOutcomeResponse.md)**

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

## query

History is a POST with account_id in the body so an account identifier stays out of URLs and access logs. Requires the history scope. Read-only and safe to retry. Newest first, each at its latest assessment_version. An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your tenant and the account; a tampered or expired cursor answers 400 invalid_cursor. response_mode=detailed needs the detailed scope.

### Example Usage: firstPage

<!-- UsageSnippet language="java" operationID="queryAccountAssessments" method="post" path="/v1/assessments/query" example="firstPage" -->
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
import org.noresponse.models.operations.QueryAccountAssessmentsResponse;
import org.noresponse.models.shared.AssessmentQuery;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AssessmentQuery req = AssessmentQuery.builder()
                .accountId("acct_7f3a9c")
                .build();


        sdk.assessments().query()
                .callAsStream()
                .forEach((QueryAccountAssessmentsResponse item) -> {
                   // handle page
                });

    }
}
```
### Example Usage: page

<!-- UsageSnippet language="java" operationID="queryAccountAssessments" method="post" path="/v1/assessments/query" example="page" -->
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
import org.noresponse.models.operations.QueryAccountAssessmentsResponse;
import org.noresponse.models.shared.AssessmentQuery;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AssessmentQuery req = AssessmentQuery.builder()
                .accountId("acct_7f3a9c")
                .cursor("eyJvIjoyMH0")
                .build();


        sdk.assessments().query()
                .callAsStream()
                .forEach((QueryAccountAssessmentsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                 | Type                                                      | Required                                                  | Description                                               |
| --------------------------------------------------------- | --------------------------------------------------------- | --------------------------------------------------------- | --------------------------------------------------------- |
| `request`                                                 | [AssessmentQuery](../../models/shared/AssessmentQuery.md) | :heavy_check_mark:                                        | The request object to use for the request.                |

### Response

**[QueryAccountAssessmentsResponse](../../models/operations/QueryAccountAssessmentsResponse.md)**

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