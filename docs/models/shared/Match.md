# Match

exact compares the original submitted value; canonical uses identifier-specific normalisation; mailbox explicitly selects the email alias rules used by lists.

## Example Usage

```java
import org.noresponse.models.shared.Match;

Match value = Match.EXACT;

// Open enum: use .of() to create instances from custom string values
Match custom = Match.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `EXACT`     | exact       |
| `CANONICAL` | canonical   |
| `MAILBOX`   | mailbox     |