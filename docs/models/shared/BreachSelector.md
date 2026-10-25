# BreachSelector

The submitted email or username whose own search matched the table. A submitted full name is searched as well, and what it finds feeds the identity's reasons rather than this list.

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