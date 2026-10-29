# Namespace

Required for username: the username submitted by this customer in this workspace/environment. It does not name or join a source platform. Omit for email and phone.

## Example Usage

```java
import org.noresponse.models.shared.Namespace;

Namespace value = Namespace.CUSTOMER;

// Open enum: use .of() to create instances from custom string values
Namespace custom = Namespace.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `CUSTOMER` | customer   |