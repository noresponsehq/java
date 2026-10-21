# AuthenticationErrorCode

Machine codes an HTTP 401 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.AuthenticationErrorCode;

AuthenticationErrorCode value = AuthenticationErrorCode.UNAUTHORIZED;

// Open enum: use .of() to create instances from custom string values
AuthenticationErrorCode custom = AuthenticationErrorCode.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `UNAUTHORIZED` | unauthorized   |