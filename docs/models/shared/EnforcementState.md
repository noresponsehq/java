# EnforcementState

The account's state in the workspace's identity provider just before the action: onboarding, provisional (created, not yet activated), active, restricted or rejected.

## Example Usage

```java
import org.noresponse.models.shared.EnforcementState;

EnforcementState value = EnforcementState.ONBOARDING;

// Open enum: use .of() to create instances from custom string values
EnforcementState custom = EnforcementState.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `ONBOARDING`  | onboarding    |
| `PROVISIONAL` | provisional   |
| `ACTIVE`      | active        |
| `RESTRICTED`  | restricted    |
| `REJECTED`    | rejected      |