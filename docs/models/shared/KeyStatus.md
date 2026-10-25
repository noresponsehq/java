# KeyStatus

active: the key authenticates. expired: a rotation's overlap window has ended (expires_at). revoked: the key was revoked (revoked_at) and answers 401.

## Example Usage

```java
import org.noresponse.models.shared.KeyStatus;

KeyStatus value = KeyStatus.ACTIVE;

// Open enum: use .of() to create instances from custom string values
KeyStatus custom = KeyStatus.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `ACTIVE`  | active    |
| `EXPIRED` | expired   |
| `REVOKED` | revoked   |