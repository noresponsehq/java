# PersonBatchItemOutcome

unchanged: the row matched a person with the same details.

## Example Usage

```java
import org.noresponse.models.shared.PersonBatchItemOutcome;

PersonBatchItemOutcome value = PersonBatchItemOutcome.CREATED;

// Open enum: use .of() to create instances from custom string values
PersonBatchItemOutcome custom = PersonBatchItemOutcome.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CREATED`   | created     |
| `UPDATED`   | updated     |
| `UNCHANGED` | unchanged   |