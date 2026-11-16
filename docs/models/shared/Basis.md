# Basis

stated: read from a record. inferred: reasoned from several facts. absence: every source answered and none found it.

## Example Usage

```java
import org.noresponse.models.shared.Basis;

Basis value = Basis.STATED;

// Open enum: use .of() to create instances from custom string values
Basis custom = Basis.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `STATED`   | stated     |
| `INFERRED` | inferred   |
| `ABSENCE`  | absence    |