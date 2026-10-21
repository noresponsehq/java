# IdentifierKind

Which submitted identifier a signal concerns. ip is a selector of breach_exposure only.

## Example Usage

```java
import org.noresponse.models.shared.IdentifierKind;

IdentifierKind value = IdentifierKind.EMAIL;

// Open enum: use .of() to create instances from custom string values
IdentifierKind custom = IdentifierKind.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `EMAIL`    | email      |
| `USERNAME` | username   |
| `PHONE`    | phone      |
| `IP`       | ip         |