# WebhookDeliveryAttemptStatus

delivered when the endpoint answered 2xx; failed otherwise, with failure naming why.

## Example Usage

```java
import org.noresponse.models.shared.WebhookDeliveryAttemptStatus;

WebhookDeliveryAttemptStatus value = WebhookDeliveryAttemptStatus.DELIVERED;

// Open enum: use .of() to create instances from custom string values
WebhookDeliveryAttemptStatus custom = WebhookDeliveryAttemptStatus.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `DELIVERED` | delivered   |
| `FAILED`    | failed      |