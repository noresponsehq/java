# ProfileMatchState

How sure the screening is that a profile is the attendee's, from its evidence: confirmed is a handle on the attendee row, or two or more hard identifiers (email, phone, a supplied handle, or a fact an existing source returned); probable is one identifier beyond the name; possible is the name alone.

## Example Usage

```java
import org.noresponse.models.shared.ProfileMatchState;

ProfileMatchState value = ProfileMatchState.CONFIRMED;

// Open enum: use .of() to create instances from custom string values
ProfileMatchState custom = ProfileMatchState.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CONFIRMED` | confirmed   |
| `PROBABLE`  | probable    |
| `POSSIBLE`  | possible    |