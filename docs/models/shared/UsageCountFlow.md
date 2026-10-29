# UsageCountFlow

assessments only.

## Example Usage

```java
import org.noresponse.models.shared.UsageCountFlow;

UsageCountFlow value = UsageCountFlow.ACCOUNT_CREATION;

// Open enum: use .of() to create instances from custom string values
UsageCountFlow custom = UsageCountFlow.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ACCOUNT_CREATION` | account_creation   |
| `LOGIN`            | login              |
| `PURCHASE`         | purchase           |