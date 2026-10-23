# Failure

Why the attempt failed: the URL is no longer valid, the host did not resolve, it resolved to an address that is not public, no answer within 5 seconds, the connection failed, a 3xx (redirects are not followed) or another non-2xx status. Absent for a delivered attempt.

## Example Usage

```java
import org.noresponse.models.shared.Failure;

Failure value = Failure.INVALID_URL;

// Open enum: use .of() to create instances from custom string values
Failure custom = Failure.of("custom_value");
```


## Values

| Name                | Value               |
| ------------------- | ------------------- |
| `INVALID_URL`       | invalid_url         |
| `RESOLVE_FAILED`    | resolve_failed      |
| `ADDRESS_REFUSED`   | address_refused     |
| `TIMEOUT`           | timeout             |
| `CONNECTION_FAILED` | connection_failed   |
| `REDIRECT`          | redirect            |
| `NON2XX`            | non_2xx             |