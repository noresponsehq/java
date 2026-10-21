# Band

low 0-29, medium 30-59, high 60-84, critical 85-100.

## Example Usage

```java
import org.noresponse.models.shared.Band;

Band value = Band.LOW;

// Open enum: use .of() to create instances from custom string values
Band custom = Band.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `LOW`      | low        |
| `MEDIUM`   | medium     |
| `HIGH`     | high       |
| `CRITICAL` | critical   |