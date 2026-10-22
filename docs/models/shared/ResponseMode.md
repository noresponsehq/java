# ResponseMode

standard returns the consolidated risk response; detailed adds merged provider-neutral facts when the key has detailed scope. There is no raw mode.

## Example Usage

```java
import org.noresponse.models.shared.ResponseMode;

ResponseMode value = ResponseMode.STANDARD;

// Open enum: use .of() to create instances from custom string values
ResponseMode custom = ResponseMode.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `STANDARD` | standard   |
| `DETAILED` | detailed   |