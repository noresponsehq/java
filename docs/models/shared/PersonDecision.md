# PersonDecision

## Example Usage

```java
import org.noresponse.models.shared.PersonDecision;

PersonDecision value = PersonDecision.CLEARED;

// Open enum: use .of() to create instances from custom string values
PersonDecision custom = PersonDecision.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CLEARED`   | cleared     |
| `WATCH`     | watch       |
| `ESCALATED` | escalated   |