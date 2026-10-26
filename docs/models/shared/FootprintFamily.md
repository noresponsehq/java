# FootprintFamily

The source-neutral kind of evidence: account_presence (an account found on a site), auth_provider (the account at your auth provider), breach (breach and credential tables), breach_abuse (abuse and fraud communities' tables) and code_hosting (a code hosting profile).

## Example Usage

```java
import org.noresponse.models.shared.FootprintFamily;

FootprintFamily value = FootprintFamily.ACCOUNT_PRESENCE;

// Open enum: use .of() to create instances from custom string values
FootprintFamily custom = FootprintFamily.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ACCOUNT_PRESENCE` | account_presence   |
| `AUTH_PROVIDER`    | auth_provider      |
| `BREACH`           | breach             |
| `BREACH_ABUSE`     | breach_abuse       |
| `CODE_HOSTING`     | code_hosting       |