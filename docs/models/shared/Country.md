# Country

IP country, phone country, breach countries and policy_context.region together. When the breach records give a country, the timezone and locale countries count too. The IP country of a proxy or hosting network does not count.

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