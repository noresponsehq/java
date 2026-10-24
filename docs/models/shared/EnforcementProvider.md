# EnforcementProvider

The identity provider the action ran in; custom for the workspace's own user store.

## Example Usage

```java
import org.noresponse.models.shared.EnforcementProvider;

EnforcementProvider value = EnforcementProvider.CLERK;

// Open enum: use .of() to create instances from custom string values
EnforcementProvider custom = EnforcementProvider.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `CLERK`  | clerk    |
| `WORKOS` | workos   |
| `AUTH0`  | auth0    |
| `CUSTOM` | custom   |