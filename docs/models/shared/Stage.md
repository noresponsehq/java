# Stage

## Example Usage

```java
import org.noresponse.models.shared.Stage;

Stage value = Stage.LOOKUP;

// Open enum: use .of() to create instances from custom string values
Stage custom = Stage.of("custom_value");
```


## Values

| Name             | Value            |
| ---------------- | ---------------- |
| `LOOKUP`         | lookup           |
| `LOOKUP_TYPING`  | lookup_typing    |
| `PLATFORMS`      | platforms        |
| `WRITTEN`        | written          |
| `LATE_PLATFORMS` | late_platforms   |
| `LATE_LOOKUP`    | late_lookup      |
| `AI_TYPING`      | ai_typing        |
| `ANALYSIS`       | analysis         |
| `RATING`         | rating           |