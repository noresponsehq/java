# OwnerType

The credential namespace of the creator. An external user ID equal to an API-key label does not grant ownership of that key's private view.

## Example Usage

```java
import org.noresponse.models.shared.OwnerType;

OwnerType value = OwnerType.USER;

// Open enum: use .of() to create instances from custom string values
OwnerType custom = OwnerType.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `USER`    | user      |
| `API_KEY` | api_key   |