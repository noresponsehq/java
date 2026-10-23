# Flow

The event an assessment is made for: account_creation (a user creates an account), login (a user returns to an existing account) or purchase (a payment or a credit grant). The account's risk is reported with the decision for this flow.

## Example Usage

```java
import org.noresponse.models.shared.Flow;

Flow value = Flow.ACCOUNT_CREATION;

// Open enum: use .of() to create instances from custom string values
Flow custom = Flow.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ACCOUNT_CREATION` | account_creation   |
| `LOGIN`            | login              |
| `PURCHASE`         | purchase           |