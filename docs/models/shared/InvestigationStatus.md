# InvestigationStatus

## Example Usage

```java
import org.noresponse.models.shared.InvestigationStatus;

InvestigationStatus value = InvestigationStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
InvestigationStatus custom = InvestigationStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `RUNNING`   | running     |
| `COMPLETED` | completed   |
| `PARTIAL`   | partial     |
| `FAILED`    | failed      |
| `CANCELLED` | cancelled   |
| `UNKNOWN`   | unknown     |