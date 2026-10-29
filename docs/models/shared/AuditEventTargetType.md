# AuditEventTargetType

The kind of resource the change acted on.

## Example Usage

```java
import org.noresponse.models.shared.AuditEventTargetType;

AuditEventTargetType value = AuditEventTargetType.WORKSPACE;

// Open enum: use .of() to create instances from custom string values
AuditEventTargetType custom = AuditEventTargetType.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `WORKSPACE`        | workspace          |
| `MEMBER`           | member             |
| `INVITE`           | invite             |
| `KEY`              | key                |
| `SETTING`          | setting            |
| `WEBHOOK_ENDPOINT` | webhook_endpoint   |
| `INTEGRATION`      | integration        |
| `BILLING`          | billing            |
| `REVIEW`           | review             |