# KeyEnvironment

The environment the key belongs to. A live key assesses with live sources; a test key answers from the deterministic simulator.

## Example Usage

```java
import org.noresponse.models.shared.KeyEnvironment;

KeyEnvironment value = KeyEnvironment.LIVE;

// Open enum: use .of() to create instances from custom string values
KeyEnvironment custom = KeyEnvironment.of("custom_value");
```


## Values

| Name   | Value  |
| ------ | ------ |
| `LIVE` | live   |
| `TEST` | test   |