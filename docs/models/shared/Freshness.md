# Freshness

Freshness class of the evidence behind a signal. unknown whenever no evidence was obtained.

## Example Usage

```java
import org.noresponse.models.shared.Freshness;

Freshness value = Freshness.FRESH;

// Open enum: use .of() to create instances from custom string values
Freshness custom = Freshness.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `FRESH`   | fresh     |
| `STALE`   | stale     |
| `UNKNOWN` | unknown   |