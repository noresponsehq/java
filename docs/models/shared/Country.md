# Country

IP country, phone country, breach countries and policy_context.region together.

## Example Usage

```java
import org.noresponse.models.shared.Country;

Country value = Country.CONSISTENT;

// Open enum: use .of() to create instances from custom string values
Country custom = Country.of("custom_value");
```


## Values

| Name         | Value        |
| ------------ | ------------ |
| `CONSISTENT` | consistent   |
| `MISMATCH`   | mismatch     |
| `UNKNOWN`    | unknown      |