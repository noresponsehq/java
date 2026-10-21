# ReasonIdentifier

The submitted identifier or client context field a reason concerns; absent when it concerns the whole assessment. Client context is a claimed hint, not a device fingerprint.

## Example Usage

```java
import org.noresponse.models.shared.ReasonIdentifier;

ReasonIdentifier value = ReasonIdentifier.EMAIL;

// Open enum: use .of() to create instances from custom string values
ReasonIdentifier custom = ReasonIdentifier.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `EMAIL`    | email      |
| `USERNAME` | username   |
| `PHONE`    | phone      |
| `NAME`     | name       |
| `IP`       | ip         |
| `CONTEXT`  | context    |