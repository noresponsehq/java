# CustomerExportStatus

## Example Usage

```java
import org.noresponse.models.shared.CustomerExportStatus;

CustomerExportStatus value = CustomerExportStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
CustomerExportStatus custom = CustomerExportStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `QUEUED`    | queued      |
| `RUNNING`   | running     |
| `COMPLETED` | completed   |
| `FAILED`    | failed      |
| `CANCELLED` | cancelled   |
| `EXPIRED`   | expired     |