# AttendeeBatchItemOutcome

unchanged: the row matched an attendee with the same details.

## Example Usage

```java
import org.noresponse.models.shared.AttendeeBatchItemOutcome;

AttendeeBatchItemOutcome value = AttendeeBatchItemOutcome.CREATED;

// Open enum: use .of() to create instances from custom string values
AttendeeBatchItemOutcome custom = AttendeeBatchItemOutcome.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CREATED`   | created     |
| `UPDATED`   | updated     |
| `UNCHANGED` | unchanged   |