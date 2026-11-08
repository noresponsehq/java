# HighestBand

## Example Usage

```java
import org.noresponse.models.shared.HighestBand;

HighestBand value = HighestBand.CRITICAL;

// Open enum: use .of() to create instances from custom string values
HighestBand custom = HighestBand.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CRITICAL` | critical   |
| `HIGH`     | high       |
| `MODERATE` | moderate   |
| `LOW`      | low        |
| `INFO`     | info       |