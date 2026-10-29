# KeyScope

## Example Usage

```java
import org.noresponse.models.shared.KeyScope;

KeyScope value = KeyScope.ASSESS;

// Open enum: use .of() to create instances from custom string values
KeyScope custom = KeyScope.of("custom_value");
```


## Values

| Name             | Value            |
| ---------------- | ---------------- |
| `ASSESS`         | assess           |
| `HISTORY`        | history          |
| `DELETE`         | delete           |
| `WEBHOOKS`       | webhooks         |
| `DETAILED`       | detailed         |
| `INTEGRATIONS`   | integrations     |
| `MANAGEMENT`     | management       |
| `AUDIT`          | audit            |
| `USAGE`          | usage            |
| `INVESTIGATE`    | investigate      |
| `EVIDENCE`       | evidence         |
| `EXPORTS`        | exports          |
| `AUDIT_MANAGE`   | audit_manage     |
| `SETTINGS`       | settings         |
| `POLICY_PUBLISH` | policy_publish   |
| `MEMBERS`        | members          |
| `MEMBERS_MANAGE` | members_manage   |