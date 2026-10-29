# AggregateGroupListMatch

The list action captured with the first answer. none means no matching entry was found. For retained_first_answers an absent field means the older assessment did not capture list state; this remains a separate group.

## Example Usage

```java
import org.noresponse.models.shared.AggregateGroupListMatch;

AggregateGroupListMatch value = AggregateGroupListMatch.BLOCK;

// Open enum: use .of() to create instances from custom string values
AggregateGroupListMatch custom = AggregateGroupListMatch.of("custom_value");
```


## Values

| Name    | Value   |
| ------- | ------- |
| `BLOCK` | block   |
| `ALLOW` | allow   |
| `NONE`  | none    |