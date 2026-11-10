# FindingBand

How severe a finding is, most severe first.

## Example Usage

```java
import org.noresponse.models.shared.FindingBand;

FindingBand value = FindingBand.CRITICAL;

// Open enum: use .of() to create instances from custom string values
FindingBand custom = FindingBand.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CRITICAL` | critical   |
| `HIGH`     | high       |
| `MODERATE` | moderate   |
| `LOW`      | low        |
| `INFO`     | info       |