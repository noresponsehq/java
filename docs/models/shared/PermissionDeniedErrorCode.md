# PermissionDeniedErrorCode

Machine codes an HTTP 403 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.PermissionDeniedErrorCode;

PermissionDeniedErrorCode value = PermissionDeniedErrorCode.WORKSPACE_FORBIDDEN;

// Open enum: use .of() to create instances from custom string values
PermissionDeniedErrorCode custom = PermissionDeniedErrorCode.of("custom_value");
```


## Values

| Name                  | Value                 |
| --------------------- | --------------------- |
| `WORKSPACE_FORBIDDEN` | workspace_forbidden   |