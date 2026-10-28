# Accounts

## Overview

Your accounts, each with one current risk, and their assessment history.

### Available Operations

* [listChanged](#listchanged) - Poll the accounts whose risk changed.
* [get](#get) - Read an account's one current risk.
* [delete](#delete) - Erase an account and its history.
* [listAssessments](#listassessments) - Page through one account's unexpired assessments.
* [recordEnforcement](#recordenforcement) - Record what you did to an account in your identity provider.
* [recordOutcome](#recordoutcome) - Record or correct an account outcome
* [listOutcomes](#listoutcomes) - List retained account feedback
* [retrieveDecision](#retrievedecision) - Retrieve an exact historical account decision
* [associateCredential](#associatecredential) - Associate a customer-issued API key
* [listCredentials](#listcredentials) - List issued credential associations
* [retireCredential](#retirecredential) - Retire an issued credential association
* [export](#export) - Export an account

## listChanged

Your accounts whose risk changed, oldest change first, each as accounts.get answers it. Every page carries next_cursor: pass it back as changed_since to read the changes made after that page; without changed_since the list starts from the oldest change. When nothing changed, items is empty and next_cursor is the cursor you sent, so poll by calling again with the latest next_cursor. An account appears again each time its risk changes, with a higher risk_version; keep the copy with the highest. unenforced=true lists only accounts with a decision other than allow for some flow and no applied enforcement at their current risk_version; such a page can hold fewer than limit items, or none, while later changes exist, so keep polling with next_cursor. Requires the history scope. Read-only and safe to retry. A tampered or foreign cursor answers 400 invalid_cursor.

### Example Usage: caughtUp

<!-- UsageSnippet language="java" operationID="listAccounts" method="get" path="/v1/accounts" example="caughtUp" -->
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
import org.noresponse.models.operations.ListAccountsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAccountsResponse res = sdk.accounts().listChanged()
                .limit(50L)
                .unenforced(false)
                .call();

        if (res.accountList().isPresent()) {
            System.out.println(res.accountList().get());
        }
    }
}
```
### Example Usage: page

<!-- UsageSnippet language="java" operationID="listAccounts" method="get" path="/v1/accounts" example="page" -->
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
import org.noresponse.models.operations.ListAccountsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAccountsResponse res = sdk.accounts().listChanged()
                .limit(50L)
                .unenforced(false)
                .call();

        if (res.accountList().isPresent()) {
            System.out.println(res.accountList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                               | Type                                                                                                                                                                                    | Required                                                                                                                                                                                | Description                                                                                                                                                                             |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `changedSince`                                                                                                                                                                          | @Nullable *String*                                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                      | next_cursor from an earlier page, unchanged. Absent, the list starts from the oldest change.                                                                                            |
| `limit`                                                                                                                                                                                 | @Nullable *long*                                                                                                                                                                        | :heavy_minus_sign:                                                                                                                                                                      | Most accounts per page.                                                                                                                                                                 |
| `unenforced`                                                                                                                                                                            | @Nullable *boolean*                                                                                                                                                                     | :heavy_minus_sign:                                                                                                                                                                      | true lists only accounts with no applied enforcement at their current risk_version that have a decision other than allow for some flow or a hold no later applied enforcement resolved. |

### Response

**[ListAccountsResponse](../../models/operations/ListAccountsResponse.md)**

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

An account is the account_id you send with your assessments. Its current risk is the risk of its latest assessment with a decision (status complete or partial), scored as GET /v1/assessments/{assessment_id} scores that assessment, with a decision for each flow; risk.decision is the decision for the flow of that assessment. Every assessment answers with this same account. Every assessment updates its account: once an assessment completes, the account carries its risk. Requires the history scope. Read-only and safe to retry. An account with no unexpired assessment that has a decision answers 404 not_found, and a malformed account_id answers 400 invalid_request.

### Example Usage: blocked

<!-- UsageSnippet language="java" operationID="getAccount" method="get" path="/v1/accounts/{account_id}" example="blocked" -->
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
import org.noresponse.models.operations.GetAccountResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAccountResponse res = sdk.accounts().get()
                .accountId("acct_7f3a9c")
                .call();

        if (res.account().isPresent()) {
            System.out.println(res.account().get());
        }
    }
}
```
### Example Usage: current

<!-- UsageSnippet language="java" operationID="getAccount" method="get" path="/v1/accounts/{account_id}" example="current" -->
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
import org.noresponse.models.operations.GetAccountResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAccountResponse res = sdk.accounts().get()
                .accountId("acct_7f3a9c")
                .call();

        if (res.account().isPresent()) {
            System.out.println(res.account().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |

### Response

**[GetAccountResponse](../../models/operations/GetAccountResponse.md)**

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

Erases the account in the environment of your key, for an erasure request from your user: every assessment of it, with its revisions, evidence and outcomes, and the identity-graph marks its outcomes set. An assessment of the account begun before the call is refused or stopped. Afterwards the account and each erased assessment answer 404 and the history is empty. When the call deleted at least one assessment, an account.deleted event goes to your verified webhook endpoints. Requires the history scope. Idempotent and safe to retry: a repeat, or an account with nothing to erase, answers 200 with assessments_deleted 0 and sends no event. The account_id can be assessed again later, as a new account.

### Example Usage: erased

<!-- UsageSnippet language="java" operationID="deleteAccount" method="delete" path="/v1/accounts/{account_id}" example="erased" -->
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
import org.noresponse.models.operations.DeleteAccountResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteAccountResponse res = sdk.accounts().delete()
                .accountId("acct_7f3a9c")
                .call();

        if (res.accountDeletion().isPresent()) {
            System.out.println(res.accountDeletion().get());
        }
    }
}
```
### Example Usage: repeat

<!-- UsageSnippet language="java" operationID="deleteAccount" method="delete" path="/v1/accounts/{account_id}" example="repeat" -->
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
import org.noresponse.models.operations.DeleteAccountResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteAccountResponse res = sdk.accounts().delete()
                .accountId("acct_7f3a9c")
                .call();

        if (res.accountDeletion().isPresent()) {
            System.out.println(res.accountDeletion().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |

### Response

**[DeleteAccountResponse](../../models/operations/DeleteAccountResponse.md)**

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

## listAssessments

The account's history: the same page as POST /v1/assessments/query for this account_id, newest first, each at its latest assessment_version. Requires the history scope. Read-only and safe to retry. An account with no assessments answers 200 with empty items. The cursor is opaque and bound to your workspace and the account; a tampered or expired cursor answers 400 invalid_cursor. response_mode=detailed needs the detailed scope. Each item's identifiers holds the identifiers its assessment was sent, exactly as received. The account_id travels in the URL; use POST /v1/assessments/query to keep it out of access logs.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAccountAssessments" method="get" path="/v1/accounts/{account_id}/assessments" example="page" -->
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
import org.noresponse.models.operations.ListAccountAssessmentsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();


        sdk.accounts().listAssessments()
                .accountId("acct_7f3a9c")
                .limit(20L)
                .callAsStream()
                .forEach((ListAccountAssessmentsResponse item) -> {
                   // handle page
                });


    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `limit`                                                                                                                                                                                                            | @Nullable *long*                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                 | Items per page.                                                                                                                                                                                                    |                                                                                                                                                                                                                    |
| `cursor`                                                                                                                                                                                                           | @Nullable *String*                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                 | next_cursor from the previous page, unchanged.                                                                                                                                                                     |                                                                                                                                                                                                                    |
| `responseMode`                                                                                                                                                                                                     | @Nullable [ResponseMode](../../models/shared/ResponseMode.md)                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                                                 | Optional standard or separately authorised detailed customer view; raw is rejected.                                                                                                                                |                                                                                                                                                                                                                    |

### Response

**[ListAccountAssessmentsResponse](../../models/operations/ListAccountAssessmentsResponse.md)**

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

## recordEnforcement

Records what you did to the account in your identity provider for one flow's decision at one risk_version: the action (or none), the account's state before it, the provider and whether it was applied or failed. It is your action, not an outcome, and it leaves the account's risk unchanged. A record is kept once per account, risk_version, flow and action: the first report answers 201, and a repeat answers 200 with the stored record unchanged, so retries are safe without an Idempotency-Key. An applied report after a failed one replaces the failed record, keeping its id and created_at, and answers 201. A risk_version older than the account's current one is accepted and stored, but only an applied record at the current risk_version takes the account out of listChanged with unenforced=true; a newer one answers 409 risk_version_ahead. An account with no current risk answers 404 not_found. error_code is allowed only with status failed. Requires the history scope.

### Example Usage: applied

<!-- UsageSnippet language="java" operationID="recordEnforcement" method="post" path="/v1/accounts/{account_id}/enforcements" example="applied" -->
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
import org.noresponse.models.operations.RecordEnforcementResponse;
import org.noresponse.models.shared.EnforcementAction;
import org.noresponse.models.shared.EnforcementProvider;
import org.noresponse.models.shared.EnforcementRequest;
import org.noresponse.models.shared.EnforcementState;
import org.noresponse.models.shared.EnforcementStatus;
import org.noresponse.models.shared.Flow;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordEnforcementResponse res = sdk.accounts().recordEnforcement()
                .accountId("acct_7f3a9c")
                .body(EnforcementRequest.builder()
                    .riskVersion(3L)
                    .flow(Flow.ACCOUNT_CREATION)
                    .action(EnforcementAction.RESTRICT)
                    .stateBefore(EnforcementState.PROVISIONAL)
                    .status(EnforcementStatus.APPLIED)
                    .provider(EnforcementProvider.CLERK)
                    .build())
                .call();

        if (res.enforcement().isPresent()) {
            System.out.println(res.enforcement().get());
        }
    }
}
```
### Example Usage: failed

<!-- UsageSnippet language="java" operationID="recordEnforcement" method="post" path="/v1/accounts/{account_id}/enforcements" example="failed" -->
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
import org.noresponse.models.operations.RecordEnforcementResponse;
import org.noresponse.models.shared.EnforcementAction;
import org.noresponse.models.shared.EnforcementProvider;
import org.noresponse.models.shared.EnforcementRequest;
import org.noresponse.models.shared.EnforcementState;
import org.noresponse.models.shared.EnforcementStatus;
import org.noresponse.models.shared.Flow;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordEnforcementResponse res = sdk.accounts().recordEnforcement()
                .accountId("acct_7f3a9c")
                .body(EnforcementRequest.builder()
                    .riskVersion(3L)
                    .flow(Flow.ACCOUNT_CREATION)
                    .action(EnforcementAction.RESTRICT)
                    .stateBefore(EnforcementState.PROVISIONAL)
                    .status(EnforcementStatus.FAILED)
                    .provider(EnforcementProvider.CLERK)
                    .errorCode("clerk.rate_limited")
                    .build())
                .call();

        if (res.enforcement().isPresent()) {
            System.out.println(res.enforcement().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `body`                                                                                                                                                                                                             | [EnforcementRequest](../../models/shared/EnforcementRequest.md)                                                                                                                                                    | :heavy_check_mark:                                                                                                                                                                                                 | N/A                                                                                                                                                                                                                |                                                                                                                                                                                                                    |

### Response

**[RecordEnforcementResponse](../../models/operations/RecordEnforcementResponse.md)**

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

Record a factual report, not an enforcement action. A known outcome replaces the effective report. unknown alone records uncertainty without changing it; unknown with supersedes explicitly retracts it. supersedes must name the current effective report or the request answers 409 outcome_conflict. Reports remain append-only. Idempotency-Key is required. observed_at defaults to receipt time and cannot be over five minutes ahead.

### Example Usage

<!-- UsageSnippet language="java" operationID="recordAccountOutcome" method="post" path="/v1/accounts/{account_id}/outcomes" -->
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
import org.noresponse.models.operations.RecordAccountOutcomeResponse;
import org.noresponse.models.shared.AccountOutcomeRequest;
import org.noresponse.models.shared.Outcome;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RecordAccountOutcomeResponse res = sdk.accounts().recordOutcome()
                .accountId("acct_7f3a9c")
                .idempotencyKey("account-acct_7f3a9c")
                .body(AccountOutcomeRequest.builder()
                    .outcome(Outcome.UNKNOWN)
                    .observedAt("2026-01-01T00:00:00Z")
                    .externalRef("case_123")
                    .supersedes("out_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .build())
                .call();

        if (res.accountOutcome().isPresent()) {
            System.out.println(res.accountOutcome().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `idempotencyKey`                                                                                                                                                                                                   | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the workspace principal and bound to the canonical body for 24 hours.                                                  | account-acct_7f3a9c                                                                                                                                                                                                |
| `body`                                                                                                                                                                                                             | [AccountOutcomeRequest](../../models/shared/AccountOutcomeRequest.md)                                                                                                                                              | :heavy_check_mark:                                                                                                                                                                                                 | N/A                                                                                                                                                                                                                |                                                                                                                                                                                                                    |

### Response

**[RecordAccountOutcomeResponse](../../models/operations/RecordAccountOutcomeResponse.md)**

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

## listOutcomes

List retained account reports, newest first. The page-level effective outcome id names the current report. Each receipt preserves the effective report and risk version from its original write. Page with the returned cursor.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAccountOutcomes" method="get" path="/v1/accounts/{account_id}/outcomes" -->
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
import org.noresponse.models.operations.ListAccountOutcomesResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAccountOutcomesResponse res = sdk.accounts().listOutcomes()
                .accountId("acct_7f3a9c")
                .limit(20L)
                .call();

        if (res.accountOutcomePage().isPresent()) {
            System.out.println(res.accountOutcomePage().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `limit`                                                                                                                                                                                                            | @Nullable *long*                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                 | Items per page.                                                                                                                                                                                                    |                                                                                                                                                                                                                    |
| `cursor`                                                                                                                                                                                                           | @Nullable *String*                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                 | next_cursor from the previous page, unchanged.                                                                                                                                                                     |                                                                                                                                                                                                                    |

### Response

**[ListAccountOutcomesResponse](../../models/operations/ListAccountOutcomesResponse.md)**

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

## retrieveDecision

Retrieve the account decision at one recorded risk version. The answer uses the original assessment and account-specific risk inputs. Earlier versions that were not captured return 404. The assessment version, policy version and account calculation version identify the decision.

### Example Usage

<!-- UsageSnippet language="java" operationID="getAccountDecision" method="get" path="/v1/accounts/{account_id}/decisions/{risk_version}" -->
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
import org.noresponse.models.operations.GetAccountDecisionResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetAccountDecisionResponse res = sdk.accounts().retrieveDecision()
                .accountId("acct_7f3a9c")
                .riskVersion(269840L)
                .call();

        if (res.accountDecisionSnapshot().isPresent()) {
            System.out.println(res.accountDecisionSnapshot().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `riskVersion`                                                                                                                                                                                                      | *long*                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                 | The canonical decimal risk_version originally served.                                                                                                                                                              |                                                                                                                                                                                                                    |

### Response

**[GetAccountDecisionResponse](../../models/operations/GetAccountDecisionResponse.md)**

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

## associateCredential

Associate the SHA-256 of a high-entropy key issued by your service. Send the hash instead of the key. Repeating a hash returns its original association, including its retirement state. A hash associated with another account returns a conflict.

### Example Usage

<!-- UsageSnippet language="java" operationID="associateCredential" method="post" path="/v1/accounts/{account_id}/credentials" -->
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
import org.noresponse.models.operations.AssociateCredentialResponse;
import org.noresponse.models.shared.CredentialRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AssociateCredentialResponse res = sdk.accounts().associateCredential()
                .accountId("acct_7f3a9c")
                .body(CredentialRequest.builder()
                    .sha256("5da2158606a4cb396280c842b578872bb64b96c4eb80dc02e6fb4e0e864b77c6")
                    .build())
                .call();

        if (res.credentialAssociation().isPresent()) {
            System.out.println(res.credentialAssociation().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `body`                                                                                                                                                                                                             | [CredentialRequest](../../models/shared/CredentialRequest.md)                                                                                                                                                      | :heavy_check_mark:                                                                                                                                                                                                 | N/A                                                                                                                                                                                                                |                                                                                                                                                                                                                    |

### Response

**[AssociateCredentialResponse](../../models/operations/AssociateCredentialResponse.md)**

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

## listCredentials

Retained credential associations, ordered by id. Page with the authenticated cursor. Associations are removed on account erasure.

### Example Usage

<!-- UsageSnippet language="java" operationID="listAccountCredentials" method="get" path="/v1/accounts/{account_id}/credentials" -->
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
import org.noresponse.models.operations.ListAccountCredentialsResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListAccountCredentialsResponse res = sdk.accounts().listCredentials()
                .accountId("acct_7f3a9c")
                .limit(20L)
                .call();

        if (res.credentialPage().isPresent()) {
            System.out.println(res.credentialPage().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `limit`                                                                                                                                                                                                            | @Nullable *long*                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                 | Items per page.                                                                                                                                                                                                    |                                                                                                                                                                                                                    |
| `cursor`                                                                                                                                                                                                           | @Nullable *String*                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                 | next_cursor from the previous page, unchanged.                                                                                                                                                                     |                                                                                                                                                                                                                    |

### Response

**[ListAccountCredentialsResponse](../../models/operations/ListAccountCredentialsResponse.md)**

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

## retireCredential

Mark this association retired, idempotently. This does not revoke the actual key. Requires delete scope.

### Example Usage

<!-- UsageSnippet language="java" operationID="retireAccountCredential" method="delete" path="/v1/accounts/{account_id}/credentials/{credential_id}" -->
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
import org.noresponse.models.operations.RetireAccountCredentialResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        RetireAccountCredentialResponse res = sdk.accounts().retireCredential()
                .accountId("acct_7f3a9c")
                .credentialId("<id>")
                .call();

        if (res.credentialAssociation().isPresent()) {
            System.out.println(res.credentialAssociation().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `credentialId`                                                                                                                                                                                                     | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | N/A                                                                                                                                                                                                                |                                                                                                                                                                                                                    |

### Response

**[RetireAccountCredentialResponse](../../models/operations/RetireAccountCredentialResponse.md)**

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

## export

Retrieve one consistent snapshot of retained customer-accessible records, including assessment revisions, account decisions, feedback, key associations, enforcements and request diagnostics. Each assessment revision's detail.identifiers holds the identifiers that assessment was sent, exactly as received. Secrets, restricted source information, other customers' data and legacy free-text notes are excluded. The same standard/detailed permissions apply. An export exceeding 10000 records or 8 MiB returns export_too_large without a partial result. Historical decisions that were not captured are identified as an exclusion.

### Example Usage

<!-- UsageSnippet language="java" operationID="exportAccount" method="get" path="/v1/accounts/{account_id}/export" -->
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
import org.noresponse.models.operations.ExportAccountResponse;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ExportAccountResponse res = sdk.accounts().export()
                .accountId("acct_7f3a9c")
                .call();

        if (res.accountExport().isPresent()) {
            System.out.println(res.accountExport().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                          | Type                                                                                                                                                                                                               | Required                                                                                                                                                                                                           | Description                                                                                                                                                                                                        | Example                                                                                                                                                                                                            |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                        | *String*                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                 | The account_id the workspace sends with its assessments, as one path segment (percent-encode '\|' and ':' if the client requires it). A value that does not match the account_id shape answers 400 invalid_request. | acct_7f3a9c                                                                                                                                                                                                        |
| `responseMode`                                                                                                                                                                                                     | @Nullable [ResponseMode](../../models/shared/ResponseMode.md)                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                                                 | standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.                                                                |                                                                                                                                                                                                                    |

### Response

**[ExportAccountResponse](../../models/operations/ExportAccountResponse.md)**

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