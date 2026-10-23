# AccountDecision

block when the score reached your block threshold (risk.block of the assessment), otherwise the assessment's recommendation. Your application applies it.

## Example Usage

```java
import org.noresponse.models.shared.AccountDecision;

AccountDecision value = AccountDecision.ALLOW;

// Open enum: use .of() to create instances from custom string values
AccountDecision custom = AccountDecision.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `ALLOW`   | allow     |
| `STEP_UP` | step_up   |
| `REVIEW`  | review    |
| `BLOCK`   | block     |