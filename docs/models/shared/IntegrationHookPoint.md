# IntegrationHookPoint

pre_registration runs before the user exists; post_login runs after a successful sign-in.

## Example Usage

```java
import org.noresponse.models.shared.IntegrationHookPoint;

IntegrationHookPoint value = IntegrationHookPoint.PRE_REGISTRATION;

// Open enum: use .of() to create instances from custom string values
IntegrationHookPoint custom = IntegrationHookPoint.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `PRE_REGISTRATION` | pre_registration   |
| `POST_LOGIN`       | post_login         |