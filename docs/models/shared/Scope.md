# Scope

## Example Usage

```java
import org.noresponse.models.shared.Scope;

Scope value = Scope.ASSESS;

// Open enum: use .of() to create instances from custom string values
Scope custom = Scope.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `ASSESS`       | assess         |
| `HISTORY`      | history        |
| `WEBHOOKS`     | webhooks       |
| `DETAILED`     | detailed       |
| `INTEGRATIONS` | integrations   |