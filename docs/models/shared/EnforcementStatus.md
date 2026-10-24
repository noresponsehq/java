# EnforcementStatus

applied when the identity provider accepted the action, failed when it refused it or could not be reached. Only applied counts as enforced.

## Example Usage

```java
import org.noresponse.models.shared.EnforcementStatus;

EnforcementStatus value = EnforcementStatus.APPLIED;

// Open enum: use .of() to create instances from custom string values
EnforcementStatus custom = EnforcementStatus.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `APPLIED` | applied   |
| `FAILED`  | failed    |