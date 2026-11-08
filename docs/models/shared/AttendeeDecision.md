# AttendeeDecision

## Example Usage

```java
import org.noresponse.models.shared.AttendeeDecision;

AttendeeDecision value = AttendeeDecision.CLEARED;

// Open enum: use .of() to create instances from custom string values
AttendeeDecision custom = AttendeeDecision.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CLEARED`   | cleared     |
| `WATCH`     | watch       |
| `ESCALATED` | escalated   |