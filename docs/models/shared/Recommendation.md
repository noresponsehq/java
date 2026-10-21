# Recommendation

The recommended action: allow, step_up or review. Your application applies it.

## Example Usage

```java
import org.noresponse.models.shared.Recommendation;

Recommendation value = Recommendation.ALLOW;

// Open enum: use .of() to create instances from custom string values
Recommendation custom = Recommendation.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `ALLOW`   | allow     |
| `STEP_UP` | step_up   |
| `REVIEW`  | review    |