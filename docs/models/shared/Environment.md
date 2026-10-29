# Environment

## Example Usage

```java
import org.noresponse.models.shared.Environment;

Environment value = Environment.LIVE;

// Open enum: use .of() to create instances from custom string values
Environment custom = Environment.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `LIVE`      | live        |
| `TEST`      | test        |
| `WORKSPACE` | workspace   |