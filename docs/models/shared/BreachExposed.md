# BreachExposed

The kind of data a breach table held beside the identifier. password means a plaintext password was present and password_hash means a hash, salt or token was present. The value itself is not stored or returned; the schema has no member for one.

## Example Usage

```java
import org.noresponse.models.shared.BreachExposed;

BreachExposed value = BreachExposed.EMAIL;

// Open enum: use .of() to create instances from custom string values
BreachExposed custom = BreachExposed.of("custom_value");
```


## Values

| Name            | Value           |
| --------------- | --------------- |
| `EMAIL`         | email           |
| `USERNAME`      | username        |
| `NAME`          | name            |
| `PASSWORD`      | password        |
| `PASSWORD_HASH` | password_hash   |
| `PHONE`         | phone           |
| `ADDRESS`       | address         |
| `DOB`           | dob             |
| `IP`            | ip              |
| `PAYMENT`       | payment         |
| `SECURITY_QA`   | security_qa     |