# Significance

One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile fields and findings are medium, new posts are low.

## Example Usage

```java
import org.noresponse.models.shared.Significance;

Significance value = Significance.HIGH;

// Open enum: use .of() to create instances from custom string values
Significance custom = Significance.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `HIGH`   | high     |
| `MEDIUM` | medium   |
| `LOW`    | low      |