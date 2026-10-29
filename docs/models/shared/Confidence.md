# Confidence

Evidence coverage: how much of the planned source evidence gave a definite answer. This is not confidence that an identity is genuine, agreement between identifiers, a risk score or a probability. unavailable means no usable evidence answered; a terminal uncovered assessment can have this value and recommend review.

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