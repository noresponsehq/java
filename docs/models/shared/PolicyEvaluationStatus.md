# PolicyEvaluationStatus

## Example Usage

```java
import org.noresponse.models.shared.PolicyEvaluationStatus;

PolicyEvaluationStatus value = PolicyEvaluationStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
PolicyEvaluationStatus custom = PolicyEvaluationStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `RUNNING`   | running     |
| `COMPLETED` | completed   |
| `FAILED`    | failed      |
| `CANCELLED` | cancelled   |