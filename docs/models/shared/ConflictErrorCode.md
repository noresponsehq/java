# ConflictErrorCode

Machine codes an HTTP 409 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.ConflictErrorCode;

ConflictErrorCode value = ConflictErrorCode.IDEMPOTENCY_KEY_REUSED;

// Open enum: use .of() to create instances from custom string values
ConflictErrorCode custom = ConflictErrorCode.of("custom_value");
```


## Values

| Name                       | Value                      |
| -------------------------- | -------------------------- |
| `IDEMPOTENCY_KEY_REUSED`   | idempotency_key_reused     |
| `KEY_ROTATION_IN_PROGRESS` | key_rotation_in_progress   |