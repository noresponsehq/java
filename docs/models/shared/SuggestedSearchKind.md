# SuggestedSearchKind

The kind of the value: one of the kinds the environment's enrichment setting names.

## Example Usage

```java
import org.noresponse.models.shared.SuggestedSearchKind;

SuggestedSearchKind value = SuggestedSearchKind.USERNAME;

// Open enum: use .of() to create instances from custom string values
SuggestedSearchKind custom = SuggestedSearchKind.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `USERNAME` | username   |
| `EMAIL`    | email      |
| `PHONE`    | phone      |