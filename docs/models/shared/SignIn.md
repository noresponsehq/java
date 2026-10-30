# SignIn

## Example Usage

```java
import org.noresponse.models.shared.SignIn;

SignIn value = SignIn.PASSWORD;

// Open enum: use .of() to create instances from custom string values
SignIn custom = SignIn.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `PASSWORD`  | password    |
| `GOOGLE`    | google      |
| `FACEBOOK`  | facebook    |
| `APPLE`     | apple       |
| `MICROSOFT` | microsoft   |
| `SSO`       | sso         |
| `OTHER`     | other       |