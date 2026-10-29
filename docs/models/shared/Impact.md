# Impact

The selected reason's served impact band. Requires reason.

## Example Usage

```java
import org.noresponse.models.shared.Impact;

Impact value = Impact.NONE;

// Open enum: use .of() to create instances from custom string values
Impact custom = Impact.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `NONE`   | none     |
| `LOW`    | low      |
| `MEDIUM` | medium   |
| `HIGH`   | high     |