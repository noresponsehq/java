# BadRequestErrorCode

Machine codes an HTTP 400 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.BadRequestErrorCode;

BadRequestErrorCode value = BadRequestErrorCode.INVALID_REQUEST;

// Open enum: use .of() to create instances from custom string values
BadRequestErrorCode custom = BadRequestErrorCode.of("custom_value");
```


## Values

| Name                      | Value                     |
| ------------------------- | ------------------------- |
| `INVALID_REQUEST`         | invalid_request           |
| `INVALID_IDEMPOTENCY_KEY` | invalid_idempotency_key   |
| `INVALID_CURSOR`          | invalid_cursor            |
| `WEBHOOK_URL_REFUSED`     | webhook_url_refused       |