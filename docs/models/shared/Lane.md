# Lane

An evidence lane: accounts (account presence for email and username), phone, breach, domain (the email domain's registration) and ip.

## Example Usage

```java
import org.noresponse.models.shared.Lane;

Lane value = Lane.ACCOUNTS;

// Open enum: use .of() to create instances from custom string values
Lane custom = Lane.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `ACCOUNTS` | accounts   |
| `PHONE`    | phone      |
| `BREACH`   | breach     |
| `DOMAIN`   | domain     |
| `IP`       | ip         |