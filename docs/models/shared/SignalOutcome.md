# SignalOutcome

Per-signal outcome. A source that timed out, was blocked or errored reports blocked, unavailable or inconclusive with a reason and is listed in incomplete_sources; not_found is a definite absence.

## Example Usage

```java
import org.noresponse.models.shared.SignalOutcome;

SignalOutcome value = SignalOutcome.FOUND;

// Open enum: use .of() to create instances from custom string values
SignalOutcome custom = SignalOutcome.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `FOUND`        | found          |
| `NOT_FOUND`    | not_found      |
| `INCONCLUSIVE` | inconclusive   |
| `BLOCKED`      | blocked        |
| `UNAVAILABLE`  | unavailable    |