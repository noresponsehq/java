# KeyDeniedAction

## Example Usage

```java
import org.noresponse.models.shared.KeyDeniedAction;

KeyDeniedAction value = KeyDeniedAction.EVIDENCE_READ;

// Open enum: use .of() to create instances from custom string values
KeyDeniedAction custom = KeyDeniedAction.of("custom_value");
```


## Values

| Name             | Value            |
| ---------------- | ---------------- |
| `EVIDENCE_READ`  | evidence.read    |
| `EXPORTS_READ`   | exports.read     |
| `POLICY_PUBLISH` | policy.publish   |