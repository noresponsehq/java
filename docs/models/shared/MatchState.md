# MatchState

How sure the latest screening is that it found the attendee's accounts. unscreened until a screening reaches the attendee.

## Example Usage

```java
import org.noresponse.models.shared.MatchState;

MatchState value = MatchState.UNSCREENED;

// Open enum: use .of() to create instances from custom string values
MatchState custom = MatchState.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `UNSCREENED`   | unscreened     |
| `CONFIRMED`    | confirmed      |
| `PROBABLE`     | probable       |
| `POSSIBLE`     | possible       |
| `NONE_FOUND`   | none_found     |
| `INACCESSIBLE` | inaccessible   |