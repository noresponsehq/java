# DefaultDepth

The depth a request for this flow that names none is served at. Defaults: full for account_creation and purchase, quick for login.

## Example Usage

```java
import org.noresponse.models.shared.DefaultDepth;

DefaultDepth value = DefaultDepth.QUICK;

// Open enum: use .of() to create instances from custom string values
DefaultDepth custom = DefaultDepth.of("custom_value");
```


## Values

| Name    | Value   |
| ------- | ------- |
| `QUICK` | quick   |
| `FULL`  | full    |