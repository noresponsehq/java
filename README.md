# noresponse

Developer-friendly & type-safe Java SDK specifically catered to leverage *noresponse* API.


<br /><br />
> [!IMPORTANT]
> This SDK is not yet ready for production use. Delete this section before > publishing to a package manager.

<!-- Start Summary [summary] -->
## Summary

NoResponse API: Server-side signup and gateway risk assessment: a recommendation, polling and signed webhooks.

Assess a signup or gateway event, poll a pending assessment, page through an account's history, record what you later learned, and rotate your API key. Every response carries X-Request-ID; every error is an RFC 9457 problem document. Keep the API key on your server.
<!-- End Summary [summary] -->

<!-- Start Table of Contents [toc] -->
## Table of Contents
<!-- $toc-max-depth=2 -->
* [noresponse](#noresponse)
  * [SDK Installation](#sdk-installation)
  * [SDK Example Usage](#sdk-example-usage)
  * [Asynchronous Support](#asynchronous-support)
  * [Authentication](#authentication)
  * [Available Resources and Operations](#available-resources-and-operations)
  * [Pagination](#pagination)
  * [Retries](#retries)
  * [Error Handling](#error-handling)
  * [Server Selection](#server-selection)
  * [Custom HTTP Client](#custom-http-client)
  * [Debugging](#debugging)
  * [Jackson Configuration](#jackson-configuration)
* [Development](#development)
  * [Maturity](#maturity)
  * [Contributions](#contributions)

<!-- End Table of Contents [toc] -->

<!-- Start SDK Installation [installation] -->
## SDK Installation

### Getting started

JDK 11 or later is required.

The samples below show how a published SDK artifact is used:

Gradle:
```groovy
implementation 'com.github.noresponsehq:java:2.0.1'
```

Maven:
```xml
<dependency>
    <groupId>com.github.noresponsehq</groupId>
    <artifactId>java</artifactId>
    <version>2.0.1</version>
</dependency>
```

### How to build
After cloning the git repository to your file system you can build the SDK artifact from source to the `build` directory by running `./gradlew build` on *nix systems or `gradlew.bat` on Windows systems.

If you wish to build from source and publish the SDK artifact to your local Maven repository (on your filesystem) then use the following command (after cloning the git repo locally):

On *nix:
```bash
./gradlew publishToMavenLocal -Pskip.signing
```
On Windows:
```bash
gradlew.bat publishToMavenLocal -Pskip.signing
```
<!-- End SDK Installation [installation] -->

<!-- Start SDK Example Usage [usage] -->
## SDK Example Usage

### Example

```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```
#### Asynchronous Call
An asynchronous SDK client is also available that returns a [`CompletableFuture<T>`][comp-fut]. See [Asynchronous Support](#asynchronous-support) for more details on async benefits and reactive library integration.
```java
package hello.world;

import java.util.concurrent.CompletableFuture;
import org.noresponse.AsyncNoResponse;
import org.noresponse.NoResponse;
import org.noresponse.models.operations.async.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) {

        AsyncNoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build()
                .async();

        CompletableFuture<CreateAssessmentResponse> resFut = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        resFut.thenAccept(res -> {
            if (res.assessment().isPresent()) {
                System.out.println(res.assessment().get());
            }
        });
    }
}

```

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html

#### Union Consumption Patterns

When a response field is a union model:

- Discriminated unions: branch on the discriminator (`switch`) and then narrow to the concrete type.
- Non-discriminated unions: use generated accessors (for example `string()`, `asLong()`, `simpleObject()`) to determine the active variant.

For full model-specific examples (including Java 11/16/21 variants), see each union model's **Supported Types** section in the generated model docs.
<!-- End SDK Example Usage [usage] -->

<!-- Start Asynchronous Support [async-support] -->
## Asynchronous Support

The SDK provides comprehensive asynchronous support using Java's [`CompletableFuture<T>`][comp-fut] and [Reactive Streams `Publisher<T>`][reactive-streams] APIs. This design makes no assumptions about your choice of reactive toolkit, allowing seamless integration with any reactive library.

<details>
<summary>Why Use Async?</summary>

Asynchronous operations provide several key benefits:

- **Non-blocking I/O**: Your threads stay free for other work while operations are in flight
- **Better resource utilization**: Handle more concurrent operations with fewer threads
- **Improved scalability**: Build highly responsive applications that can handle thousands of concurrent requests
- **Reactive integration**: Works seamlessly with reactive streams and backpressure handling

</details>

<details>
<summary>Reactive Library Integration</summary>

The SDK returns [Reactive Streams `Publisher<T>`][reactive-streams] instances for operations dealing with streams involving multiple I/O interactions. We use Reactive Streams instead of JDK Flow API to provide broader compatibility with the reactive ecosystem, as most reactive libraries natively support Reactive Streams.

**Why Reactive Streams over JDK Flow?**
- **Broader ecosystem compatibility**: Most reactive libraries (Project Reactor, RxJava, Akka Streams, etc.) natively support Reactive Streams
- **Industry standard**: Reactive Streams is the de facto standard for reactive programming in Java
- **Better interoperability**: Seamless integration without additional adapters for most use cases

**Integration with Popular Libraries:**
- **Project Reactor**: Use `Flux.from(publisher)` to convert to Reactor types
- **RxJava**: Use `Flowable.fromPublisher(publisher)` for RxJava integration
- **Akka Streams**: Use `Source.fromPublisher(publisher)` for Akka Streams integration
- **Vert.x**: Use `ReadStream.fromPublisher(vertx, publisher)` for Vert.x reactive streams
- **Mutiny**: Use `Multi.createFrom().publisher(publisher)` for Quarkus Mutiny integration

**For JDK Flow API Integration:**
If you need JDK Flow API compatibility (e.g., for Quarkus/Mutiny 2), you can use adapters:
```java
// Convert Reactive Streams Publisher to Flow Publisher
Flow.Publisher<T> flowPublisher = FlowAdapters.toFlowPublisher(reactiveStreamsPublisher);

// Convert Flow Publisher to Reactive Streams Publisher
Publisher<T> reactiveStreamsPublisher = FlowAdapters.toPublisher(flowPublisher);
```

For standard single-response operations, the SDK returns `CompletableFuture<T>` for straightforward async execution.

</details>

<details>
<summary>Supported Operations</summary>

Async support is available for:

- **[Server-sent Events](#server-sent-event-streaming)**: Stream real-time events with Reactive Streams `Publisher<T>`
- **[JSONL Streaming](#jsonl-streaming)**: Process streaming JSON lines asynchronously
- **[Pagination](#pagination)**: Iterate through paginated results using `callAsPublisher()` and `callAsPublisherUnwrapped()`
- **[File Uploads](#file-uploads)**: Upload files asynchronously with progress tracking
- **[File Downloads](#file-downloads)**: Download files asynchronously with streaming support
- **[Standard Operations](#example)**: All regular API calls return `CompletableFuture<T>` for async execution

</details>

[comp-fut]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
[reactive-streams]: https://www.reactive-streams.org/
<!-- End Asynchronous Support [async-support] -->

<!-- Start Authentication [security] -->
## Authentication

### Per-Client Security Schemes

This SDK supports the following security scheme globally:

| Name     | Type | Scheme      |
| -------- | ---- | ----------- |
| `apiKey` | http | HTTP Bearer |

To authenticate with the API the `apiKey` parameter must be set when initializing the SDK client instance. For example:
```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```
<!-- End Authentication [security] -->

<!-- Start Available Resources and Operations [operations] -->
## Available Resources and Operations

<details open>
<summary>Available methods</summary>

### [Assessments](docs/sdks/assessments/README.md)

* [create](docs/sdks/assessments/README.md#create) - Assess identifiers for a signup or gateway decision.
* [get](docs/sdks/assessments/README.md#get) - Poll the authoritative assessment of one assessment.
* [recordOutcome](docs/sdks/assessments/README.md#recordoutcome) - Report what was later learned about an assessed account.
* [query](docs/sdks/assessments/README.md#query) - Page through one account's unexpired assessments.

### [Keys](docs/sdks/keys/README.md)

* [rotate](docs/sdks/keys/README.md#rotate) - Rotate the API key that signs the request.

</details>
<!-- End Available Resources and Operations [operations] -->

<!-- Start Pagination [pagination] -->
## Pagination

Some of the endpoints in this SDK support pagination. To use pagination, you can make your SDK calls using the `callAsIterable` or `callAsStream` methods.
For certain operations, you can also use the `callAsStreamUnwrapped` method that streams individual page items directly.

Here's an example depicting the different ways to use pagination:

```java
package hello.world;

import java.lang.Exception;
import java.lang.Iterable;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.QueryAccountAssessmentsResponse;
import org.noresponse.models.shared.AssessmentQuery;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        AssessmentQuery req = AssessmentQuery.builder().accountId("acct_7f3a9c").build();

        var b = sdk.assessments().query();

        // Iterate through all pages using a traditional for-each loop
        // Each iteration returns a complete page response
        Iterable<QueryAccountAssessmentsResponse> iterable = b.callAsIterable();
        for (QueryAccountAssessmentsResponse page : iterable) {
            // handle page
        }

        // Stream through all pages and process individual items
        // callAsStreamUnwrapped() flattens pages into individual items

        // Stream through pages without unwrapping (each item is a complete page)
        b.callAsStream().forEach((QueryAccountAssessmentsResponse page) -> {
            // handle page
        });
    }
}

```
#### Asynchronous Pagination
An asynchronous SDK client is also available for pagination that returns a [`Flow.Publisher<T>`][flow-pub]. For async pagination, you can use `callAsPublisher()` to get pages as a publisher, or `callAsPublisherUnwrapped()` to get individual items directly. See [Asynchronous Support](#asynchronous-support) for more details on async benefits and reactive library integration.
```java
package hello.world;

import org.noresponse.AsyncNoResponse;
import org.noresponse.NoResponse;
import org.noresponse.models.operations.async.QueryAccountAssessmentsResponse;
import org.noresponse.models.shared.AssessmentQuery;
import reactor.core.publisher.Flux;

public class Application {

    public static void main(String[] args) {

        AsyncNoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build()
                .async();

        AssessmentQuery req = AssessmentQuery.builder().accountId("acct_7f3a9c").build();

        var b = sdk.assessments().query();

        // Example using Project Reactor (illustrative) - pages
        Flux<QueryAccountAssessmentsResponse> pageFlux = Flux.from(b.callAsPublisher());
        pageFlux.subscribe(
                page -> System.out.println(page),
                error -> error.printStackTrace(),
                () -> System.out.println("Pagination completed"));
    }
}

```

[flow-pub]: https://docs.oracle.com/javase/9/docs/api/java/util/concurrent/Flow.Publisher.html
<!-- End Pagination [pagination] -->

<!-- Start Retries [retries] -->
## Retries

Some of the endpoints in this SDK support retries. If you use the SDK without any configuration, it will fall back to the default retry strategy provided by the API. However, the default retry strategy can be overridden on a per-operation basis, or across the entire SDK.

To change the default retry strategy for a single API call, you can provide a `RetryConfig` object through the `retryConfig` builder method:
```java
package hello.world;

import java.lang.Exception;
import java.util.concurrent.TimeUnit;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.utils.BackoffStrategy;
import org.noresponse.utils.RetryConfig;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .retryConfig(RetryConfig.builder()
                        .backoff(BackoffStrategy.builder()
                                .initialInterval(1L, TimeUnit.MILLISECONDS)
                                .maxInterval(50L, TimeUnit.MILLISECONDS)
                                .maxElapsedTime(1000L, TimeUnit.MILLISECONDS)
                                .baseFactor(1.1)
                                .jitterFactor(0.15)
                                .retryConnectError(false)
                                .build())
                        .build())
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```

If you'd like to override the default retry strategy for all operations that support retries, you can provide a configuration at SDK initialization:
```java
package hello.world;

import java.lang.Exception;
import java.util.concurrent.TimeUnit;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;
import org.noresponse.utils.BackoffStrategy;
import org.noresponse.utils.RetryConfig;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .retryConfig(RetryConfig.builder()
                        .backoff(BackoffStrategy.builder()
                                .initialInterval(1L, TimeUnit.MILLISECONDS)
                                .maxInterval(50L, TimeUnit.MILLISECONDS)
                                .maxElapsedTime(1000L, TimeUnit.MILLISECONDS)
                                .baseFactor(1.1)
                                .jitterFactor(0.15)
                                .retryConnectError(false)
                                .build())
                        .build())
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```
<!-- End Retries [retries] -->

<!-- Start Error Handling [errors] -->
## Error Handling

Handling errors in this SDK should largely match your expectations. All operations return a response object or raise an exception.


[`NoResponseException`](./src/main/java/models/errors/NoResponseException.java) is the base class for all HTTP error responses. It has the following properties:

| Method           | Type                        | Description                                                              |
| ---------------- | --------------------------- | ------------------------------------------------------------------------ |
| `message()`      | `String`                    | Error message                                                            |
| `code()`         | `int`                       | HTTP response status code eg `404`                                       |
| `headers`        | `Map<String, List<String>>` | HTTP response headers                                                    |
| `body()`         | `byte[]`                    | HTTP body as a byte array. Can be empty array if no body is returned.    |
| `bodyAsString()` | `String`                    | HTTP body as a UTF-8 string. Can be empty string if no body is returned. |
| `rawResponse()`  | `HttpResponse<?>`           | Raw HTTP response (body already read and not available for re-read)      |

### Example
```java
package hello.world;

import java.io.UncheckedIOException;
import java.lang.Exception;
import java.lang.String;
import java.util.Optional;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NoResponseException;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();
        try {

            CreateAssessmentResponse res = sdk.assessments()
                    .create()
                    .idempotencyKey("<value>")
                    .body(AssessmentRequest.builder()
                            .accountId("acct_7f3a9c")
                            .identifiers(AssessmentRequestIdentifiers.builder()
                                    .email("new.user@example.com")
                                    .build())
                            .build())
                    .call();

            if (res.assessment().isPresent()) {
                System.out.println(res.assessment().get());
            }
        } catch (NoResponseException ex) { // all SDK exceptions inherit from NoResponseException

            // ex.ToString() provides a detailed error message including
            // HTTP status code, headers, and error payload (if any)
            System.out.println(ex);

            // Base exception fields
            var rawResponse = ex.rawResponse();
            var headers = ex.headers();
            var contentType = headers.first("Content-Type");
            int statusCode = ex.code();
            Optional<byte[]> responseBody = ex.body();

            // different error subclasses may be thrown
            // depending on the service call
            if (ex instanceof BadRequestError) {
                var e = (BadRequestError) ex;
                // Check error data fields
                e.data().ifPresent(payload -> {
                    String type = payload.type();
                    String title = payload.title();
                    // ...
                });
            }

            // An underlying cause may be provided. If the error payload
            // cannot be deserialized then the deserialization exception
            // will be set as the cause.
            if (ex.getCause() != null) {
                var cause = ex.getCause();
            }
        } catch (UncheckedIOException ex) {
            // handle IO error (connection, timeout, etc)
        }
    }
}

```

### Error Classes
**Primary errors:**
* [`NoResponseException`](./src/main/java/models/errors/NoResponseException.java): The base class for HTTP error responses.
  * [`org.noresponse.models.errors.BadRequestError`](./src/main/java/models/errors/org.noresponse.models.errors.BadRequestError.java): HTTP 400 problem document. Status code `400`.
  * [`org.noresponse.models.errors.AuthenticationError`](./src/main/java/models/errors/org.noresponse.models.errors.AuthenticationError.java): HTTP 401 problem document. Status code `401`.
  * [`org.noresponse.models.errors.PermissionDeniedError`](./src/main/java/models/errors/org.noresponse.models.errors.PermissionDeniedError.java): HTTP 403 problem document. Status code `403`.
  * [`org.noresponse.models.errors.NotFoundError`](./src/main/java/models/errors/org.noresponse.models.errors.NotFoundError.java): HTTP 404 problem document. Status code `404`.
  * [`org.noresponse.models.errors.ConflictError`](./src/main/java/models/errors/org.noresponse.models.errors.ConflictError.java): HTTP 409 problem document. Status code `409`.
  * [`org.noresponse.models.errors.RateLimitError`](./src/main/java/models/errors/org.noresponse.models.errors.RateLimitError.java): HTTP 429 problem document. Status code `429`.

<details><summary>Less common errors (6)</summary>

<br />

**Network errors:**
* `java.io.IOException` (always wrapped by `java.io.UncheckedIOException`). Commonly encountered subclasses of
`IOException` include `java.net.ConnectException`, `java.net.SocketTimeoutException`, `EOFException` (there are
many more subclasses in the JDK platform).

**Inherit from [`NoResponseException`](./src/main/java/models/errors/NoResponseException.java)**:


</details>
<!-- End Error Handling [errors] -->

<!-- Start Server Selection [server] -->
## Server Selection

### Override Server URL Per-Client

The default server can be overridden globally using the `.serverURL(String serverUrl)` builder method when initializing the SDK client instance. For example:
```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .serverURL("https://api.noresponse.org")
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```
<!-- End Server Selection [server] -->

<!-- Start Custom HTTP Client [http-client] -->
## Custom HTTP Client

The Java SDK makes API calls using an `HTTPClient` that wraps the native
[HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html). This
client provides the ability to attach hooks around the request lifecycle that can be used to modify the request or handle
errors and response.

The `HTTPClient` interface allows you to either use the default `NoResponseHTTPClient` that comes with the SDK,
or provide your own custom implementation with customized configuration such as custom executors, SSL context,
connection pools, and other HTTP client settings.

The interface provides synchronous (`send`) methods and asynchronous (`sendAsync`) methods. The `sendAsync` method
is used to power the async SDK methods and returns a `CompletableFuture<HttpResponse<Blob>>` for non-blocking operations.

The following example shows how to add a custom header and handle errors:

```java
import org.noresponse.NoResponse;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.NoResponseHTTPClient;
import org.noresponse.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;

public class Application {
    public static void main(String[] args) {
        // Create a custom HTTP client with hooks
        HTTPClient httpClient = new HTTPClient() {
            private final HTTPClient defaultClient = new NoResponseHTTPClient();
            
            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                // Add custom header and timeout using Utils.copy()
                HttpRequest modifiedRequest = Utils.copy(request)
                    .header("x-custom-header", "custom value")
                    .timeout(Duration.ofSeconds(30))
                    .build();
                    
                try {
                    HttpResponse<InputStream> response = defaultClient.send(modifiedRequest);
                    // Log successful response
                    System.out.println("Request successful: " + response.statusCode());
                    return response;
                } catch (Exception error) {
                    // Log error
                    System.err.println("Request failed: " + error.getMessage());
                    throw error;
                }
            }
        };

        NoResponse sdk = NoResponse.builder()
            .client(httpClient)
            .build();
    }
}
```

<details>
<summary>Custom HTTP Client Configuration</summary>

You can also provide a completely custom HTTP client with your own configuration:

```java
import org.noresponse.NoResponse;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.Blob;
import org.noresponse.utils.ResponseWithBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class Application {
    public static void main(String[] args) {
        // Custom HTTP client with custom configuration
        HTTPClient customHttpClient = new HTTPClient() {
            private final HttpClient client = HttpClient.newBuilder()
                .executor(Executors.newFixedThreadPool(10))
                .connectTimeout(Duration.ofSeconds(30))
                // .sslContext(customSslContext) // Add custom SSL context if needed
                .build();

            @Override
            public HttpResponse<InputStream> send(HttpRequest request) throws IOException, URISyntaxException, InterruptedException {
                return client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            }

            @Override
            public CompletableFuture<HttpResponse<Blob>> sendAsync(HttpRequest request) {
                // Convert response to HttpResponse<Blob> for async operations
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                    .thenApply(resp -> new ResponseWithBody<>(resp, Blob::from));
            }
        };

        NoResponse sdk = NoResponse.builder()
            .client(customHttpClient)
            .build();
    }
}
```

</details>

You can also enable debug logging on the default `NoResponseHTTPClient`:

```java
import org.noresponse.NoResponse;
import org.noresponse.utils.NoResponseHTTPClient;

public class Application {
    public static void main(String[] args) {
        NoResponseHTTPClient httpClient = new NoResponseHTTPClient();
        httpClient.enableDebugLogging(true);

        NoResponse sdk = NoResponse.builder()
            .client(httpClient)
            .build();
    }
}
```
<!-- End Custom HTTP Client [http-client] -->

<!-- Start Debugging [debug] -->
## Debugging

### Debug & Logging

#### SLF4j Logging
This SDK uses [SLF4j](https://www.slf4j.org/) for structured logging across HTTP requests, retries, pagination, streaming, and hooks. SLF4j provides comprehensive visibility into SDK operations.

**Log Levels:**
- **DEBUG**: High-level operations (HTTP requests/responses, retry attempts, page fetches, hook execution, stream lifecycle)
- **TRACE**: Detailed information (request/response bodies, backoff calculations, individual items processed)

**Configuration:**

Add your preferred SLF4j implementation to your project. For example, using Logback:

```gradle
dependencies {
    implementation 'ch.qos.logback:logback-classic:1.4.14'
}
```

Configure logging levels in your `logback.xml`:

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- SDK-wide logging -->
    <logger name="org.noresponse" level="DEBUG"/>
    
    <!-- Component-specific logging -->
    <logger name="org.noresponse.utils.NoResponseHTTPClient" level="DEBUG"/>
    <logger name="org.noresponse.utils.Retries" level="DEBUG"/>
    <logger name="org.noresponse.utils.pagination" level="DEBUG"/>
    <logger name="org.noresponse.utils.Hooks" level="TRACE"/>
    
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

**What Gets Logged:**
- **HTTP Client**: Request/response details, headers (with sensitive headers redacted), bodies (at TRACE level)
- **Retries**: Retry attempts, backoff delays, exhaustion, non-retryable exceptions
- **Pagination**: Page fetches, pagination state, errors
- **Streaming**: Stream initialization, item processing, closure
- **Hooks**: Hook execution counts, operation IDs, exceptions

#### Legacy Debug Logging
For backward compatibility, you can still use the legacy debug logging method:

```java
SDK.builder()
    .enableHTTPDebugLogging(true)
    .build();
```
Example output:
```
Sending request: http://localhost:35123/bearer#global GET
Request headers: {Accept=[application/json], Authorization=[******], Client-Level-Header=[added by client], Idempotency-Key=[some-key], x-noresponse-user-agent=[noresponse-sdk/java 0.0.1 internal 0.1.0 org.openapis.openapi]}
Received response: (GET http://localhost:35123/bearer#global) 200
Response headers: {access-control-allow-credentials=[true], access-control-allow-origin=[*], connection=[keep-alive], content-length=[50], content-type=[application/json], date=[Wed, 09 Apr 2025 01:43:29 GMT], server=[gunicorn/19.9.0]}
Response body:
{
  "authenticated": true, 
  "token": "global"
}
```
__WARNING__: Debug logging should only be used for temporary debugging purposes. Leaving this option on in a production system could expose credentials/secrets in logs. <i>Authorization</i> headers are redacted by default. You can specify additional redacted header names via `NoResponseHTTPClient.setRedactedHeaders`.

__NOTE__: This is a convenience method that calls `HTTPClient.enableDebugLogging()`. The `NoResponseHTTPClient` honors this setting. If you are using a custom HTTP client, it is up to the custom client to honor this setting.


#### JDK HTTP Client Logging
Another option is to set the System property `-Djdk.httpclient.HttpClient.log=all`. However, this option does not log request/response bodies.
<!-- End Debugging [debug] -->

<!-- Start Jackson Configuration [jackson] -->
## Jackson Configuration

The SDK ships with a pre-configured Jackson [`ObjectMapper`][jackson-databind] accessible via
`JSON.getMapper()`. It is set up with type modules, strict deserializers, and the feature flags
needed for full SDK compatibility (including ISO-8601 `OffsetDateTime` serialization):

```java
import org.noresponse.utils.JSON;

String json = JSON.getMapper().writeValueAsString(response);
```

To compose with your own `ObjectMapper`, register the provided `NoresponseJacksonModule`, which
bundles all the same modules and feature flags as a single plug-and-play module:

```java
import org.noresponse.utils.NoresponseJacksonModule;
import com.fasterxml.jackson.databind.ObjectMapper;

ObjectMapper myMapper = new ObjectMapper()
    .registerModule(new NoresponseJacksonModule());

String json = myMapper.writeValueAsString(response);
```

[jackson-databind]: https://github.com/FasterXML/jackson-databind
[jackson-jsr310]: https://github.com/FasterXML/jackson-modules-java8/tree/master/datetime
<!-- End Jackson Configuration [jackson] -->

<!-- Placeholder for Future NoResponse SDK Sections -->

# Development

## Maturity

This SDK is in beta, and there may be breaking changes between versions without a major version update. Therefore, we recommend pinning usage
to a specific package version. This way, you can install the same version each time without breaking changes unless you are intentionally
looking for the latest version.

## Contributions

While we value open-source contributions to this SDK, this library is generated programmatically. Any manual changes added to internal files will be overwritten on the next generation. 
We look forward to hearing your feedback. Feel free to open a PR or an issue with a proof of concept and we'll do our best to include it in a future release. 
