# PersonRole

The person's role in the group: guest (a ticket holder), crew, vendor or press. Leave it out when you do not know it: a person without one has no role, not guest. A later row naming the same person replaces it, and a change of role alone keeps the person's screening results.

## Example Usage

```java
import org.noresponse.models.shared.PersonRole;

PersonRole value = PersonRole.GUEST;

// Open enum: use .of() to create instances from custom string values
PersonRole custom = PersonRole.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `GUEST`  | guest    |
| `CREW`   | crew     |
| `VENDOR` | vendor   |
| `PRESS`  | press    |