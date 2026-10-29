# Reviews

## Overview

Collaborative review cases, comments and linked subjects.

### Available Operations

* [list](#list) - List retained account reviews.
* [create](#create) - Open a review for a retained account.
* [get](#get) - Read an account review.
* [update](#update) - Assign, prioritize or reopen a guarded review.
* [resolve](#resolve) - Resolve a review against an exact account risk version.
* [listComments](#listcomments) - Read retained review comments.
* [comment](#comment) - Append a comment against the current review version.
* [listResolutions](#listresolutions) - Read immutable review resolution history.
* [listViews](#listviews) - List your private and shared review filters.
* [createView](#createview) - Save a tenant-local review filter.
* [updateView](#updateview) - Replace your saved filter with optimistic concurrency.
* [deleteView](#deleteview) - Delete your saved filter with optimistic concurrency.
* [export](#export) - Export an account review
* [listSubjects](#listsubjects) - List retained subjects linked to a review.
* [addSubject](#addsubject) - Link retained tenant-local evidence to an investigation.
* [deleteSubject](#deletesubject) - Remove a linked subject using the current review version.

## list

List retained investigation reviews using filters and an authenticated cursor. Optional account_id selects one exact account in this environment before pagination. All filters are bound into the cursor.

### Example Usage

<!-- UsageSnippet language="java" operationID="listReviews" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}" -->
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
import org.noresponse.models.operations.ListReviewsRequest;
import org.noresponse.models.operations.ListReviewsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListReviewsRequest req = ListReviewsRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .build();


        sdk.reviews().list()
                .request(req)
                .callAsStream()
                .forEach((ListReviewsResponse item) -> {
                   // handle page
                });


    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `request`                                                           | [ListReviewsRequest](../../models/operations/ListReviewsRequest.md) | :heavy_check_mark:                                                  | The request object to use for the request.                          |

### Response

**[ListReviewsResponse](../../models/operations/ListReviewsResponse.md)**

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

Open a review for one account. An account can have one active review in its environment.

### Example Usage

<!-- UsageSnippet language="java" operationID="createReview" method="post" path="/v1/workspaces/{workspace_id}/reviews/{environment}" -->
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
import org.noresponse.models.operations.CreateReviewResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewCreateRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateReviewResponse res = sdk.reviews().create()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .body(ReviewCreateRequest.builder()
                    .accountId("acct_7f3a9c")
                    .assignee("usr_customer_admin")
                    .build())
                .call();

        if (res.review().isPresent()) {
            System.out.println(res.review().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewCreateRequest](../../models/shared/ReviewCreateRequest.md)                                                                                                      | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[CreateReviewResponse](../../models/operations/CreateReviewResponse.md)**

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

Read a retained review and its current version.

### Example Usage

<!-- UsageSnippet language="java" operationID="getReview" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}" -->
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
import org.noresponse.models.operations.GetReviewResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        GetReviewResponse res = sdk.reviews().get()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .call();

        if (res.review().isPresent()) {
            System.out.println(res.review().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[GetReviewResponse](../../models/operations/GetReviewResponse.md)**

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

Change review status, priority or assignment after checking the expected version.

### Example Usage

<!-- UsageSnippet language="java" operationID="updateReview" method="patch" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}" -->
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
import org.noresponse.models.operations.UpdateReviewResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewUpdateRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateReviewResponse res = sdk.reviews().update()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .body(ReviewUpdateRequest.builder()
                    .expectedVersion(14577L)
                    .assignee("usr_customer_admin")
                    .build())
                .call();

        if (res.review().isPresent()) {
            System.out.println(res.review().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewUpdateRequest](../../models/shared/ReviewUpdateRequest.md)                                                                                                      | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[UpdateReviewResponse](../../models/operations/UpdateReviewResponse.md)**

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

## resolve

Resolve a review against the exact current review and account decision versions. A resolution does not report a factual outcome or execute enforcement.

### Example Usage

<!-- UsageSnippet language="java" operationID="resolveReview" method="post" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/resolve" -->
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
import org.noresponse.models.operations.ResolveReviewResponse;
import org.noresponse.models.shared.Conclusion;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewResolveRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ResolveReviewResponse res = sdk.reviews().resolve()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .reviewId("<id>")
                .body(ReviewResolveRequest.builder()
                    .expectedVersion(902778L)
                    .expectedRiskVersion(385930L)
                    .conclusion(Conclusion.BENIGN)
                    .note("Reviewed the retained account evidence and customer report.")
                    .build())
                .call();

        if (res.review().isPresent()) {
            System.out.println(res.review().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewResolveRequest](../../models/shared/ReviewResolveRequest.md)                                                                                                    | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[ResolveReviewResponse](../../models/operations/ResolveReviewResponse.md)**

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

## listComments

Page through retained review comments.

### Example Usage

<!-- UsageSnippet language="java" operationID="listReviewComments" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/comments" -->
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
import org.noresponse.models.operations.ListReviewCommentsRequest;
import org.noresponse.models.operations.ListReviewCommentsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListReviewCommentsRequest req = ListReviewCommentsRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .build();


        sdk.reviews().listComments()
                .request(req)
                .callAsStream()
                .forEach((ListReviewCommentsResponse item) -> {
                   // handle page
                });


    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `request`                                                                         | [ListReviewCommentsRequest](../../models/operations/ListReviewCommentsRequest.md) | :heavy_check_mark:                                                                | The request object to use for the request.                                        |

### Response

**[ListReviewCommentsResponse](../../models/operations/ListReviewCommentsResponse.md)**

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

## comment

Append a comment after checking the expected review version.

### Example Usage

<!-- UsageSnippet language="java" operationID="commentReview" method="post" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/comments" -->
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
import org.noresponse.models.operations.CommentReviewResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewCommentRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CommentReviewResponse res = sdk.reviews().comment()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .body(ReviewCommentRequest.builder()
                    .expectedVersion(181980L)
                    .body("Reviewed the retained account evidence and customer report.")
                    .build())
                .call();

        if (res.reviewComment().isPresent()) {
            System.out.println(res.reviewComment().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewCommentRequest](../../models/shared/ReviewCommentRequest.md)                                                                                                    | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[CommentReviewResponse](../../models/operations/CommentReviewResponse.md)**

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

## listResolutions

Page through immutable review resolutions.

### Example Usage

<!-- UsageSnippet language="java" operationID="listReviewResolutions" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/resolutions" -->
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
import org.noresponse.models.operations.ListReviewResolutionsRequest;
import org.noresponse.models.operations.ListReviewResolutionsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListReviewResolutionsRequest req = ListReviewResolutionsRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .build();

        ListReviewResolutionsResponse res = sdk.reviews().listResolutions()
                .request(req)
                .call();

        if (res.reviewResolutionList().isPresent()) {
            System.out.println(res.reviewResolutionList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `request`                                                                               | [ListReviewResolutionsRequest](../../models/operations/ListReviewResolutionsRequest.md) | :heavy_check_mark:                                                                      | The request object to use for the request.                                              |

### Response

**[ListReviewResolutionsResponse](../../models/operations/ListReviewResolutionsResponse.md)**

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

## listViews

List private and shared saved review filters visible to you.

### Example Usage

<!-- UsageSnippet language="java" operationID="listReviewViews" method="get" path="/v1/workspaces/{workspace_id}/review_views/{environment}" -->
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
import org.noresponse.models.operations.ListReviewViewsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListReviewViewsResponse res = sdk.reviews().listViews()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .call();

        if (res.reviewViewList().isPresent()) {
            System.out.println(res.reviewViewList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |

### Response

**[ListReviewViewsResponse](../../models/operations/ListReviewViewsResponse.md)**

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

## createView

Save a private or shared review filter.

### Example Usage

<!-- UsageSnippet language="java" operationID="createReviewView" method="post" path="/v1/workspaces/{workspace_id}/review_views/{environment}" -->
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
import org.noresponse.models.operations.CreateReviewViewResponse;
import org.noresponse.models.shared.Filter;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        CreateReviewViewResponse res = sdk.reviews().createView()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .body(ReviewViewRequest.builder()
                    .name("High priority reviews")
                    .shared(true)
                    .filter(Filter.builder()
                        .accountId("acct_7f3a9c")
                        .assignee("usr_customer_admin")
                        .assigneePrincipalId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                        .build())
                    .build())
                .call();

        if (res.reviewView().isPresent()) {
            System.out.println(res.reviewView().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewViewRequest](../../models/shared/ReviewViewRequest.md)                                                                                                          | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[CreateReviewViewResponse](../../models/operations/CreateReviewViewResponse.md)**

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

## updateView

Update your saved review filter after checking its expected version.

### Example Usage

<!-- UsageSnippet language="java" operationID="updateReviewView" method="patch" path="/v1/workspaces/{workspace_id}/review_views/{environment}/{view_id}" -->
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
import org.noresponse.models.operations.UpdateReviewViewResponse;
import org.noresponse.models.shared.Filter;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        UpdateReviewViewResponse res = sdk.reviews().updateView()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .viewId("<id>")
                .body(ReviewViewRequest.builder()
                    .name("High priority reviews")
                    .shared(false)
                    .filter(Filter.builder()
                        .accountId("acct_7f3a9c")
                        .assignee("usr_customer_admin")
                        .assigneePrincipalId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                        .build())
                    .build())
                .call();

        if (res.reviewView().isPresent()) {
            System.out.println(res.reviewView().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `viewId`                                                                                                                                                               | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewViewRequest](../../models/shared/ReviewViewRequest.md)                                                                                                          | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[UpdateReviewViewResponse](../../models/operations/UpdateReviewViewResponse.md)**

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

## deleteView

Delete your saved review filter after checking its expected version.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteReviewView" method="delete" path="/v1/workspaces/{workspace_id}/review_views/{environment}/{view_id}" -->
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
import org.noresponse.models.operations.DeleteReviewViewResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewDeleteRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteReviewViewResponse res = sdk.reviews().deleteView()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .viewId("<id>")
                .body(ReviewViewDeleteRequest.builder()
                    .expectedVersion(165419L)
                    .build())
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
| `viewId`                                                                                                                                                               | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewViewDeleteRequest](../../models/shared/ReviewViewDeleteRequest.md)                                                                                              | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[DeleteReviewViewResponse](../../models/operations/DeleteReviewViewResponse.md)**

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

Export retained review details, comments, resolutions and linked subjects. Requires current reviews.read and exports.read; API keys need management together with exports, history or audit_manage. The audit event commits before any bytes are returned. Oversized exports are refused without truncation; use the bounded collection APIs.

### Example Usage

<!-- UsageSnippet language="java" operationID="exportReview" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/export" -->
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
import org.noresponse.models.operations.ExportReviewResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ExportReviewResponse res = sdk.reviews().export()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.TEST)
                .reviewId("<id>")
                .call();

        if (res.export().isPresent()) {
            System.out.println(res.export().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[ExportReviewResponse](../../models/operations/ExportReviewResponse.md)**

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

## listSubjects

List retained subjects linked to this review. A link supplies investigation context and does not prove a relationship or change account risk.

### Example Usage

<!-- UsageSnippet language="java" operationID="listReviewSubjects" method="get" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/subjects" -->
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
import org.noresponse.models.operations.ListReviewSubjectsResponse;
import org.noresponse.models.shared.KeyEnvironment;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        ListReviewSubjectsResponse res = sdk.reviews().listSubjects()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .reviewId("<id>")
                .call();

        if (res.reviewSubjectList().isPresent()) {
            System.out.println(res.reviewSubjectList().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[ListReviewSubjectsResponse](../../models/operations/ListReviewSubjectsResponse.md)**

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

## addSubject

Link an account, exact assessment revision or issued-key association within this environment. Supply the expected review version. A review permits 100 links. Erasure removes links to erased subjects. This action records context without creating feedback or enforcement.

### Example Usage

<!-- UsageSnippet language="java" operationID="addReviewSubject" method="post" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/subjects" -->
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
import org.noresponse.models.operations.AddReviewSubjectResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewSubjectKind;
import org.noresponse.models.shared.ReviewSubjectRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        AddReviewSubjectResponse res = sdk.reviews().addSubject()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .reviewId("<id>")
                .body(ReviewSubjectRequest.builder()
                    .kind(ReviewSubjectKind.ACCOUNT)
                    .accountId("acct_7f3a9c")
                    .reason("Linked during review of the account evidence.")
                    .expectedVersion(691733L)
                    .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .credentialId("cred_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                    .build())
                .call();

        if (res.reviewSubjectChange().isPresent()) {
            System.out.println(res.reviewSubjectChange().get());
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                              | Type                                                                                                                                                                   | Required                                                                                                                                                               | Description                                                                                                                                                            | Example                                                                                                                                                                |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `workspaceId`                                                                                                                                                          | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a test environment's id answer 404.                                     | tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM                                                                                                                                         |
| `environment`                                                                                                                                                          | [KeyEnvironment](../../models/shared/KeyEnvironment.md)                                                                                                                | :heavy_check_mark:                                                                                                                                                     | The environment: live or test. Each has its own settings. Any other value answers 400. An API key reads and changes only its own environment's; the other answers 404. |                                                                                                                                                                        |
| `reviewId`                                                                                                                                                             | *String*                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |
| `body`                                                                                                                                                                 | [ReviewSubjectRequest](../../models/shared/ReviewSubjectRequest.md)                                                                                                    | :heavy_check_mark:                                                                                                                                                     | N/A                                                                                                                                                                    |                                                                                                                                                                        |

### Response

**[AddReviewSubjectResponse](../../models/operations/AddReviewSubjectResponse.md)**

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

## deleteSubject

Remove a subject link with the expected review version. The linked account, assessment or key association remains unchanged.

### Example Usage

<!-- UsageSnippet language="java" operationID="deleteReviewSubject" method="delete" path="/v1/workspaces/{workspace_id}/reviews/{environment}/{review_id}/subjects/{subject_id}" -->
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
import org.noresponse.models.operations.DeleteReviewSubjectRequest;
import org.noresponse.models.operations.DeleteReviewSubjectResponse;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ReviewViewDeleteRequest;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
            .build();

        DeleteReviewSubjectRequest req = DeleteReviewSubjectRequest.builder()
                .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .environment(KeyEnvironment.LIVE)
                .reviewId("<id>")
                .subjectId("rvs_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
                .body(ReviewViewDeleteRequest.builder()
                    .expectedVersion(299624L)
                    .build())
                .build();

        DeleteReviewSubjectResponse res = sdk.reviews().deleteSubject()
                .request(req)
                .call();

        if (res.review().isPresent()) {
            System.out.println(res.review().get());
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `request`                                                                           | [DeleteReviewSubjectRequest](../../models/operations/DeleteReviewSubjectRequest.md) | :heavy_check_mark:                                                                  | The request object to use for the request.                                          |

### Response

**[DeleteReviewSubjectResponse](../../models/operations/DeleteReviewSubjectResponse.md)**

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