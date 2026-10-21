# RiskConfidence

How much of the planned evidence answered; it says nothing about risk.

## Example Usage

```java
import org.noresponse.models.shared.RiskConfidence;

RiskConfidence value = RiskConfidence.LOW;

// Open enum: use .of() to create instances from custom string values
RiskConfidence custom = RiskConfidence.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `LOW`    | low      |
| `MEDIUM` | medium   |
| `HIGH`   | high     |