# Agreement

## Example Usage

```java
import org.noresponse.models.shared.Agreement;

Agreement value = Agreement.MATCH;

// Open enum: use .of() to create instances from custom string values
Agreement custom = Agreement.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `MATCH`    | match      |
| `PARTIAL`  | partial    |
| `MISMATCH` | mismatch   |