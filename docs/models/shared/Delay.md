# Delay

How long after the assessment's creation the change was made.

## Example Usage

```java
import org.noresponse.models.shared.Delay;

Delay value = Delay.UNDER10S;

// Open enum: use .of() to create instances from custom string values
Delay custom = Delay.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `UNDER10S` | under_10s  |
| `UNDER1M`  | under_1m   |
| `UNDER10M` | under_10m  |
| `UNDER1H`  | under_1h   |
| `UNDER1D`  | under_1d   |
| `OVER1D`   | over_1d    |