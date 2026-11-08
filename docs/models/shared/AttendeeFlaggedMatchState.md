# AttendeeFlaggedMatchState

How sure the screening is that the accounts it read are the attendee's. A finding on a possible match is at most moderate, on a probable match at most high.

## Example Usage

```java
import org.noresponse.models.shared.AttendeeFlaggedMatchState;

AttendeeFlaggedMatchState value = AttendeeFlaggedMatchState.CONFIRMED;
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `CONFIRMED`    | confirmed      |
| `PROBABLE`     | probable       |
| `POSSIBLE`     | possible       |
| `NONE_FOUND`   | none_found     |
| `INACCESSIBLE` | inaccessible   |