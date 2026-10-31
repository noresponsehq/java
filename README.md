<a href="https://noresponse.org"><img src="https://raw.githubusercontent.com/noresponsehq/.github/main/profile/sdk-header.png" alt="NoResponse · Identity Intelligence" width="100%"></a>

# NoResponse for Java

Assess sign-ups, logins and purchases with the NoResponse Identity Intelligence API.

Java 11+ · Gradle and Maven through JitPack.

[Documentation](https://docs.noresponse.org/sdks/java) · [API reference](https://docs.noresponse.org/api-reference) · [Releases](https://github.com/noresponsehq/java/releases)

## Install

[JitPack](https://jitpack.io/#noresponsehq/java) builds the SDK from the tagged release.

Gradle:

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.noresponsehq:java:v10.0.0'
}
```

<details>
<summary>Use Maven instead</summary>

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
  <version>v10.0.0</version>
</dependency>
```

</details>

## Quick start

```java
import org.noresponse.IdempotencyKey;
import org.noresponse.NoResponse;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AccountRisk;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.models.shared.Flow;

public class Signup {
    public static void main(String[] args) {
        NoResponse client = NoResponse.builder()
                .apiKey(System.getenv("NORESPONSE_API_KEY"))
                .build();

        CreateAssessmentResponse res = client.assessments().create(
                IdempotencyKey.generate(),
                AssessmentRequest.builder()
                        .flow(Flow.ACCOUNT_CREATION)
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build());

        Assessment assessment = res.assessment().orElseThrow();
        if (assessment.account().isEmpty()) {
            // The account's first assessment is still pending. Apply your own
            // fallback, then wait for the webhook or call Wait.wait(client, assessment.id()).
            return;
        }
        AccountRisk risk = assessment.account().get().risk();
        System.out.println(risk.decision() + " " + risk.score());
    }
}
```

A builder with no `apiKey(...)` reads the key from `NORESPONSE_API_KEY`, so `NoResponse.builder().build()` works too.

Act on `account().get().risk().decision()`: `allow`, `step_up`, `review` or `block` (the tenant's block threshold was reached), decided for the request's `flow` (`SIGNUP`, `LOGIN` or `PURCHASE`). This assessment's own facts (score, recommendation, signals, `assessmentVersion()`) are under `detail()`. `account()` is empty while the account's first assessment is pending, and a 429, a 503 or a timeout also needs your own fallback.

## Go further

| Task | Guide |
| --- | --- |
| Configure this client and explore its methods | [Java SDK guide](https://docs.noresponse.org/sdks/java) |
| Act when an account's decision changes | [Late results](https://docs.noresponse.org/guides/late-results) |
| Explore request and response fields | [API reference](https://docs.noresponse.org/api-reference) |

<details>
<summary><strong>SDK reference</strong> · configuration, methods, errors and webhooks</summary>

[Methods](#methods) · [Idempotency keys](#idempotency-keys) · [Errors](#errors) · [Retries and timeouts](#retries-and-timeouts) · [Paging](#paging) · [Issued keys](#issued-keys) · [Webhooks](#webhooks) · [Renamed since 2.0.0](#renamed-since-200)

## Methods

| Call | Does |
|---|---|
| `client.assessments().create(idempotencyKey, request)` | Assess identifiers. Answers 200 (complete or partial) or 202 (pending); `res.statusCode()` tells them apart. |
| `client.assessments().get(assessmentId)` | Fetch the latest version of one assessment. |
| `client.assessments().recordOutcome(assessmentId, idempotencyKey, request)` | Report what you later learned, for example `Outcome.CONFIRMED_ABUSE`. |
| `client.assessments().delete(assessmentId)` | Delete one assessment (`history` scope). The answer's account names the assessment its risk now comes from, empty when none is left. A repeat answers 404. |
| `client.assessments().query(query)` | One page of an account's history. |
| `AllAssessments.stream(client, query)` | A lazy `Stream<AssessmentDetail>` over every page. |
| `client.accounts().get(accountId)` | The account's one current risk: score, a decision for each flow and the assessment it comes from (`history` scope). An account with no decided assessment is a 404. |
| `client.accounts().delete(accountId)` | Erase the account and its history in your key's environment (`history` scope). Idempotent: a repeat answers `assessmentsDeleted` 0. An `account.deleted` webhook follows when something was deleted. |
| `client.accounts().listAssessments(accountId)` | One page of the account's history with the account id in the URL. The builder form takes `limit`, `cursor` and `responseMode`. |
| `Wait.wait(client, assessmentId)` | Poll until the assessment is `complete`, honouring `Retry-After`, for up to 30 s. Then a `partial` answer is returned as it stands, and a `pending` one throws `WaitTimeoutException`. |
| `Webhook.constructEvent(payload, headers, secrets)` | Verify a webhook delivery and return the typed event. |
| `client.webhookEndpoints().create(request)` | Register a webhook endpoint (`webhooks` scope). The signing secret is shown once. |
| `client.webhookEndpoints().sendTestEvent(endpointId, request)` | Send an `endpoint.verification` challenge or a sample `assessment.updated` event to the endpoint. |
| `client.webhookEndpoints().listDirect()`, `rotateSigningKey(...)`, `delete(endpointId)` | List endpoints, rotate an endpoint's signing key, delete an endpoint. |
| `client.keys().rotate(request)` | Rotate the API key that signs the request. |
| `client.integrations().create(request)` | Register a hosted WorkOS or Auth0 integration (`integrations` scope). For Auth0 the answer's secret is shown once. Never retried. |
| `client.integrations().listDirect()` | Every integration, oldest first, with no secrets. |
| `client.integrations().delete(integrationId)` | Delete an integration. |
| `client.integrations().rotate(integrationId, body)` | Replace an integration's secret. Never retried. |

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

The client retries 408, 429, 5xx, connection errors and connect timeouts with exponential backoff and jitter: 500 ms first, 8 s at most, 30 s in total. The same idempotency key is sent each time, so a retry never repeats work. Connections time out after 10 seconds and each request after 60 seconds. Change the retry policy for every call or for one:

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

## Issued keys

When your platform issues an API key to the new account, send only its hash, as `issued_credential.sha256`; the key itself never leaves your backend.

```java
String sha256 = IssuedCredential.sha256(issuedKey); // org.noresponse.IssuedCredential
```

## Webhooks

Register your endpoint with a key that has the `webhooks` scope. The signing secret is shown only in this answer, so store it, then send the verification challenge:

```java
WebhookEndpointCreated created = client.webhookEndpoints()
        .create(WebhookEndpointRequest.builder().url("https://app.example.com/webhooks/noresponse").build())
        .webhookEndpointCreated().orElseThrow();
SigningSecret secret = created.signingSecret(); // store secret.keyId() and secret.secret()
String endpointId = created.endpoint().endpointId();
client.webhookEndpoints().sendTestEvent(endpointId,
        WebhookTestRequest.builder().event(WebhookTestRequestEvent.ENDPOINT_VERIFICATION).build()); // webhookTest().verified()
client.webhookEndpoints().sendTestEvent(endpointId,
        WebhookTestRequest.builder().event(WebhookTestRequestEvent.ASSESSMENT_UPDATED).build()); // arrives with test() true
```

These models are in `org.noresponse.models.shared`. `listDirect()`, `rotateSigningKey(...)` (the old key verifies for 7 more days) and `delete(endpointId)` manage the endpoint afterwards.

Pass the raw request body. Re-serialising parsed JSON breaks the signature.

```java
Map<String, String> secrets = Map.of("whk_example0001", System.getenv("NORESPONSE_WEBHOOK_SECRET"));

try {
    WebhookEvent event = Webhook.constructEvent(rawBody, headers, secrets);
    if (event instanceof EndpointVerificationEvent) {
        String challenge = ((EndpointVerificationEvent) event).data().challenge();
        // answer 200 with new WebhookVerificationResponse(challenge) as JSON
    } else if (event instanceof AssessmentUpdatedEvent) {
        AssessmentUpdatedEvent updated = (AssessmentUpdatedEvent) event;
        if (updated.test().orElse(false)) {
            // sendTestEvent's fixed example assessment: answer 2xx and apply nothing
        } else {
            // any other assessment.updated reports progress only: apply nothing
            updated.data().assessmentExpired().ifPresent(e -> { /* delete what you hold for e.assessmentId() */ });
        }
    } else if (event instanceof AccountDeletedEvent) {
        // account.deleted: you erased ((AccountDeletedEvent) event).data().accountId()
    } else if (event instanceof WorkspaceDeletedEvent) {
        // workspace.deleted: the workspace is gone; the last event here
    } else if (event instanceof WebhookEndpointDisabledEvent) {
        // data().endpointId() failed for 72 hours: fix it, verify it again
    } else if (event instanceof AccountRiskChangedEvent) {
        /* account.risk_changed, the one event to act on: a flow's decision changed, at
           data().account().riskVersion(). This verifier alone does not enforce it: use handleRiskChange (below) as this endpoint's receiver. */
    }
} catch (WebhookVerificationException e) {
    // answer 400 and do not process the delivery
}
```

`Webhook` and `WebhookVerificationException` are in `org.noresponse.webhookverification`; `WebhookEvent`, its event classes and `WebhookVerificationResponse` are in `org.noresponse.models.shared`. A header that is absent or has no value is refused with `Code.MISSING_HEADER`, a repeated one with `Code.DUPLICATE_HEADER`. `constructEvent(payload, headers, secrets, tolerance, now)` takes a `Duration` and an `Instant`. Deliveries arrive at least once and unordered. Deduplicate on the event's `id()`, and act on `account.risk_changed` through the account's current risk.


### Late enforcement

`account.risk_changed` only wakes your handler. `LateResults.handleRiskChange` (in `org.noresponse.lateresults`) verifies the delivery with `Webhook.constructEvent`, reads the account's current risk with `accounts().get`, asks your `getAccountState` where the account is in your own flow, and acts on the decision of that state's flow: an `onboarding` account fails onboarding, a `provisional` one is refused activation, an `active` one is restricted on `block` and has its sessions revoked on `review` or `block`. Each action, or `none`, is recorded with `accounts().recordEnforcement` at the `riskVersion` read. The rules and the Clerk, WorkOS and Auth0 calls come from one table shared by every SDK, and nothing deletes a user: WorkOS has no ban on a user, so it writes `noresponse_state` to the user's metadata (gate sign-in on it) and revokes the sessions.

```java
var options = new LateResults.EnforcementOptions(
        LateResults.identityProvider("clerk", new LateResults.IdentityProviderOptions(System.getenv("CLERK_SECRET_KEY"))),
        account -> new LateResults.AccountStateAnswer(states.of(account.id()), null, enforced.of(account.id())))
    .flow("login", new LateResults.FlowPolicy(true)); // unrestrict once login is allow again (opt-in)
LateResults.EnforcementResult result = LateResults.handleRiskChange(client, rawBody, headers, secrets, options);
```

Store `result.riskVersion()` and return it as `enforcedRiskVersion`, so a replayed or older event changes nothing. A provider refusal is recorded as failed and thrown as `ProviderActionException`; answer 5xx so NoResponse delivers it again. To catch up, pass each account from `accounts().listChanged()` with `unenforced` true to `LateResults.enforceAccount`. For your own user store, `LateResults.customProvider(Map.of("restrict", userId -> ...))` runs one handler per action.

A held flow (workspace settings `admission: held`) answers `held` until its investigation completes or `hold_seconds` pass, and the handler records `none` for it. When the final decision arrives as `account.risk_changed`, an `onboarding` or `provisional` account fails onboarding or is refused activation on `review` or `block`. On `allow` the handler records `activate` (no provider call; a custom provider runs its own `activate`), so admit the account when the result's enforcements hold it. An account at `allow` that needs no change, such as an `active` one after a held login, is recorded as `none`. Either way the account leaves the unenforced list. See https://docs.noresponse.org/guides/late-results.

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

</details>

## Combine submitted identifiers

Search accounts and assessments with explicit AND/OR combinations of submitted
email, phone and customer-namespace username terms. Use `IdentifierExpression`
and its required `scope`: `assessment` requires one submission to satisfy the
whole expression; `account` can combine different retained submissions of the
same account and is accepted only by account search. Pass the expression through the generated search request `identifierExpression` field.

Expressions have at most eight raw terms and three group levels. The original
exact, canonical and explicit mailbox matching rules still apply to each term.
Expression results contain indexed `IdentifierTermMatch` records with the
original submitted value and originating assessment, separate from current
account risk. Single-identifier and legacy email filters retain their behavior.

See the [combined-search contract and examples](https://docs.noresponse.org/reference/investigation#combine-submitted-identifiers)
for the expression shape, scope differences, permissions and pagination.

## Support

[Open an issue](https://github.com/noresponsehq/java/issues) and include the request ID from the response or error.
Keep API keys and personal data out of issues.

[Apache-2.0](LICENSE).
