# Priority

## Example Usage

```java
import org.noresponse.models.shared.Priority;

Priority value = Priority.LOW;

// Open enum: use .of() to create instances from custom string values
Priority custom = Priority.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `LOW`    | low      |
| `NORMAL` | normal   |
| `HIGH`   | high     |
| `URGENT` | urgent   |