# NoResponse Java SDK

The Java client for the [NoResponse API](https://docs.noresponse.org): assess a signup or gateway event, wait for a pending result, page through an account's history, record what you later learned and verify webhooks.

Call it from your server only. The API key must never reach a browser or an app.

## Install

Java 11 or later. The SDK is built by [JitPack](https://jitpack.io/#noresponsehq/java) from the tagged release.

Gradle:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.noresponsehq:java:v2.0.11'
}
```

Maven:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.noresponsehq</groupId>
  <artifactId>java</artifactId>
  <version>v2.0.11</version>
</dependency>
```

## Quick start

```java
import org.noresponse.IdempotencyKey;
import org.noresponse.NoResponse;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.Status;

public class Signup {
    public static void main(String[] args) {
        NoResponse client = NoResponse.builder()
                .apiKey(System.getenv("NORESPONSE_API_KEY"))
                .build();

        CreateAssessmentResponse res = client.assessments().create(
                IdempotencyKey.generate(),
                AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build());

        Assessment assessment = res.assessment().orElseThrow();
        if (Status.PENDING.equals(assessment.status())) {
            // No recommendation yet. Apply your own fallback, then wait for the
            // webhook or call Wait.wait(client, assessment.assessmentId()).
            return;
        }
        System.out.println(assessment.recommendation().get() + " " + assessment.risk().get().score());
    }
}
```

The recommendation is `allow`, `step_up` or `review`. It is advice: your application decides what to do. A `pending` assessment has no recommendation, and a 429, a 503 or a timeout also needs your own fallback.

## Methods

| Call | Does |
|---|---|
| `client.assessments().create(idempotencyKey, request)` | Assess identifiers. Answers 200 (complete or partial) or 202 (pending); `res.statusCode()` tells them apart. |
| `client.assessments().get(assessmentId)` | Fetch the latest version of one assessment. |
| `client.assessments().recordOutcome(assessmentId, idempotencyKey, request)` | Report what you later learned, for example `Outcome.CONFIRMED_ABUSE`. |
| `client.assessments().query(query)` | One page of an account's history. |
| `AllAssessments.stream(client, query)` | A lazy `Stream<Assessment>` over every page. |
| `Wait.wait(client, assessmentId)` | Poll until the assessment is no longer pending, honouring `Retry-After`. |
| `Webhook.constructEvent(payload, headers, secrets)` | Verify a webhook delivery and return the typed event. |
| `client.keys().rotate(request)` | Rotate the API key that signs the request. |

Each method also has a builder form, for example `client.assessments().create().idempotencyKey(key).body(request).call()`, and an asynchronous twin from `client.async()` that returns `CompletableFuture`.

Every response has `statusCode()`, `headers()` (including `X-Request-ID`) and the typed body.

## Idempotency keys

`create` and `recordOutcome` require an idempotency key. The same key with the same body replays the stored answer and starts no new work. The same key with a different body is a 409. Use `IdempotencyKey.generate()` and store the key beside the signup so a retry after a crash sends the same one.

## Errors

Every API failure is a `NoResponseException`, which is a `RuntimeException`. Problem documents are typed and carry the request id:

```java
try {
    client.assessments().get("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM");
} catch (NotFoundException e) {
    System.out.println("no such assessment, request " + e.requestId().orElse("unknown"));
} catch (RateLimitException e) {
    System.out.println("rate limited, request " + e.requestId().orElse("unknown"));
} catch (NoResponseException e) {
    System.out.println("HTTP " + e.code() + " " + e.bodyAsString().orElse(""));
} catch (UncheckedIOException e) {
    // network failure or timeout
}
```

The typed exceptions are `BadRequestException` (400), `AuthenticationException` (401), `PermissionDeniedException` (403), `NotFoundException` (404), `ConflictException` (409) and `RateLimitException` (429), all in `org.noresponse.models.errors`. Any other status is an `APIStatusException`. Each exposes `code()`, `headers()`, `body()` and `rawResponse()`.

## Retries and timeouts

The client retries 408, 429, 5xx and connection errors with exponential backoff and jitter: 500 ms first, 8 s at most, 30 s in total. The same idempotency key is sent each time, so a retry never repeats work. Connections time out after 10 seconds and each request after 60 seconds. Change the retry policy for every call or for one:

```java
NoResponse client = NoResponse.builder()
        .apiKey(System.getenv("NORESPONSE_API_KEY"))
        .retryConfig(RetryConfig.builder()
                .backoff(BackoffStrategy.builder()
                        .initialInterval(200, TimeUnit.MILLISECONDS)
                        .maxInterval(2, TimeUnit.SECONDS)
                        .maxElapsedTime(5, TimeUnit.SECONDS)
                        .baseFactor(2.0)
                        .jitterFactor(0.15)
                        .retryConnectError(true)
                        .build())
                .build())
        .build();

client.assessments().get().assessmentId(id).retryConfig(RetryConfig.noRetries()).call();
```

`RetryConfig` and `BackoffStrategy` are in `org.noresponse.utils`. To set other timeouts or a proxy, pass your own `HTTPClient` to `NoResponse.builder().client(...)`.

## Paging

```java
AssessmentQuery query = AssessmentQuery.builder().accountId("acct_7f3a9c").build();
AllAssessments.stream(client, query)
        .forEach(a -> System.out.println(a.assessmentId() + " " + a.status()));
```

## Webhooks

Pass the raw request body. Re-serialising parsed JSON breaks the signature.

```java
Map<String, String> secrets = Map.of("whk_example0001", System.getenv("NORESPONSE_WEBHOOK_SECRET"));

try {
    WebhookEvent event = Webhook.constructEvent(rawBody, headers, secrets);
    event.assessment().ifPresent(a -> { /* the full assessment, as get returns it */ });
    event.expired().ifPresent(e -> { /* delete what you hold for e.assessmentId() */ });
    event.verification().ifPresent(v -> { /* answer with Webhook.verificationResponse(event) */ });
} catch (VerificationException e) {
    // answer 400 and do not process the delivery
}
```

`Webhook`, `WebhookEvent` and `VerificationException` are in `org.noresponse.webhooks`. Deliveries arrive at least once and unordered. Deduplicate on `event.id()` and keep the highest `assessmentVersion()` per assessment.

## Renamed since 2.0.0

The error classes now follow the Java convention of ending in `Exception`:

| 2.0.0 | Now |
|---|---|
| `BadRequestError` | `BadRequestException` |
| `AuthenticationError` | `AuthenticationException` |
| `PermissionDeniedError` | `PermissionDeniedException` |
| `NotFoundError` | `NotFoundException` |
| `ConflictError` | `ConflictException` |
| `RateLimitError` | `RateLimitException` |

They stay in `org.noresponse.models.errors`. New: `IdempotencyKey.generate()` and `AllAssessments.stream(...)`. Requests now time out after 60 seconds and connections after 10; 2.0.0 had no timeout.
