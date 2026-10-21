# BreachSelector

The submitted identifier a breach table matched on: email or username. Phone and name are not breach selectors.

## Example Usage

```java
import org.noresponse.models.shared.BreachSelector;

BreachSelector value = BreachSelector.EMAIL;

// Open enum: use .of() to create instances from custom string values
BreachSelector custom = BreachSelector.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `EMAIL`    | email      |
| `USERNAME` | username   |
| `IP`       | ip         |