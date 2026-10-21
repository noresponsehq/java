# PermissionDeniedErrorCode

Machine codes an HTTP 403 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.PermissionDeniedErrorCode;

PermissionDeniedErrorCode value = PermissionDeniedErrorCode.TENANT_FORBIDDEN;

// Open enum: use .of() to create instances from custom string values
PermissionDeniedErrorCode custom = PermissionDeniedErrorCode.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `TENANT_FORBIDDEN` | tenant_forbidden   |