# MemberDeniedAction

## Example Usage

```java
import org.noresponse.models.shared.MemberDeniedAction;

MemberDeniedAction value = MemberDeniedAction.EVIDENCE_READ;

// Open enum: use .of() to create instances from custom string values
MemberDeniedAction custom = MemberDeniedAction.of("custom_value");
```


## Values

| Name             | Value            |
| ---------------- | ---------------- |
| `EVIDENCE_READ`  | evidence.read    |
| `EXPORTS_READ`   | exports.read     |
| `POLICY_PUBLISH` | policy.publish   |