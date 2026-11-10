# AttendeeRole

The attendee's role at the event: guest (a ticket holder), crew, vendor or press. Leave it out when you do not know it: an attendee without one has no role, not guest. A later row naming the same attendee replaces it, and a change of role alone keeps the attendee's screening results.

## Example Usage

```java
import org.noresponse.models.shared.AttendeeRole;

AttendeeRole value = AttendeeRole.GUEST;

// Open enum: use .of() to create instances from custom string values
AttendeeRole custom = AttendeeRole.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `GUEST`  | guest    |
| `CREW`   | crew     |
| `VENDOR` | vendor   |
| `PRESS`  | press    |