# Decision

An assessment's own decision: block when its stored risk.block is true, otherwise its recommendation. It is not the account's decision or held-admission state.

## Example Usage

```java
import org.noresponse.models.shared.Decision;

Decision value = Decision.ALLOW;

// Open enum: use .of() to create instances from custom string values
Decision custom = Decision.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `ALLOW`   | allow     |
| `STEP_UP` | step_up   |
| `REVIEW`  | review    |
| `BLOCK`   | block     |