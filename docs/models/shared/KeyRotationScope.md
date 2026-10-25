# KeyRotationScope

## Example Usage

```java
import org.noresponse.models.shared.KeyRotationScope;

KeyRotationScope value = KeyRotationScope.ASSESS;

// Open enum: use .of() to create instances from custom string values
KeyRotationScope custom = KeyRotationScope.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `ASSESS`       | assess         |
| `HISTORY`      | history        |
| `DELETE`       | delete         |
| `WEBHOOKS`     | webhooks       |
| `DETAILED`     | detailed       |
| `INTEGRATIONS` | integrations   |
| `MANAGEMENT`   | management     |