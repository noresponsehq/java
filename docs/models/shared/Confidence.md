# Confidence

Ordinal confidence in the recommendation under the current policy: low, medium, high or unavailable.

## Example Usage

```java
import org.noresponse.models.shared.Confidence;

Confidence value = Confidence.LOW;

// Open enum: use .of() to create instances from custom string values
Confidence custom = Confidence.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `LOW`         | low           |
| `MEDIUM`      | medium        |
| `HIGH`        | high          |
| `UNAVAILABLE` | unavailable   |