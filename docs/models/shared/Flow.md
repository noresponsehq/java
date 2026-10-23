# Flow

The customer event an assessment is made for: signup (a new account), login (a returning sign-in) or purchase (a payment or credit grant). The account's risk is reported with the decision for this flow.

## Example Usage

```java
import org.noresponse.models.shared.Flow;

Flow value = Flow.SIGNUP;

// Open enum: use .of() to create instances from custom string values
Flow custom = Flow.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `SIGNUP`   | signup     |
| `LOGIN`    | login      |
| `PURCHASE` | purchase   |