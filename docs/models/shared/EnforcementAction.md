# EnforcementAction

What the workspace did in its identity provider: revoke_sessions (signed the account out everywhere), restrict (limited what the account can do), unrestrict (lifted an earlier restriction), fail_onboarding (stopped an account that was still onboarding), reject_activation (refused to activate a provisional account), activate (let a held onboarding or provisional account in) or none (decided to take no action).

## Example Usage

```java
import org.noresponse.models.shared.EnforcementAction;

EnforcementAction value = EnforcementAction.REVOKE_SESSIONS;

// Open enum: use .of() to create instances from custom string values
EnforcementAction custom = EnforcementAction.of("custom_value");
```


## Values

| Name                | Value               |
| ------------------- | ------------------- |
| `REVOKE_SESSIONS`   | revoke_sessions     |
| `RESTRICT`          | restrict            |
| `UNRESTRICT`        | unrestrict          |
| `FAIL_ONBOARDING`   | fail_onboarding     |
| `REJECT_ACTIVATION` | reject_activation   |
| `ACTIVATE`          | activate            |
| `NONE`              | none                |