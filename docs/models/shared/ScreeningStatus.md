# ScreeningStatus

partial finished with some attendees or platforms not covered; attendees_failed and attendees_partial count them.

## Example Usage

```java
import org.noresponse.models.shared.ScreeningStatus;

ScreeningStatus value = ScreeningStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
ScreeningStatus custom = ScreeningStatus.of("custom_value");
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