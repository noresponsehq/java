# Certainty

confirmed: a linked record or the searched identifier states it directly. likely: a strong inference. possible: a weak inference.

## Example Usage

```java
import org.noresponse.models.shared.Certainty;

Certainty value = Certainty.CONFIRMED;

// Open enum: use .of() to create instances from custom string values
Certainty custom = Certainty.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `CONFIRMED` | confirmed   |
| `LIKELY`    | likely      |
| `POSSIBLE`  | possible    |