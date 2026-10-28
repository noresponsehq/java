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

| Name                             | Value                            |
| -------------------------------- | -------------------------------- |
| `IDEMPOTENCY_KEY_REUSED`         | idempotency_key_reused           |
| `KEY_ROTATION_IN_PROGRESS`       | key_rotation_in_progress         |
| `WEBHOOK_ENDPOINT_LIMIT_REACHED` | webhook_endpoint_limit_reached   |
| `WEBHOOK_ENDPOINT_UNVERIFIED`    | webhook_endpoint_unverified      |
| `TOO_MANY_INTEGRATIONS`          | too_many_integrations            |
| `RISK_VERSION_AHEAD`             | risk_version_ahead               |
| `KEY_LIMIT_REACHED`              | key_limit_reached                |
| `SELF_REVOCATION`                | self_revocation                  |
| `LIST_ENTRY_EXISTS`              | list_entry_exists                |
| `LIST_LIMIT_REACHED`             | list_limit_reached               |
| `LIST_ENTRY_TOO_BROAD`           | list_entry_too_broad             |