# Access

public profiles were read; a private or locked one exists but could not be read.

## Example Usage

```java
import org.noresponse.models.shared.Access;

Access value = Access.PUBLIC;

// Open enum: use .of() to create instances from custom string values
Access custom = Access.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `PUBLIC`  | public    |
| `PRIVATE` | private   |
| `LOCKED`  | locked    |