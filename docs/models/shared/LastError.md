# LastError

## Example Usage

```java
import org.noresponse.models.shared.LastError;

LastError value = LastError.SCREENING_CAP_EXCEEDED;

// Open enum: use .of() to create instances from custom string values
LastError custom = LastError.of("custom_value");
```


## Values

| Name                     | Value                    |
| ------------------------ | ------------------------ |
| `SCREENING_CAP_EXCEEDED` | screening_cap_exceeded   |
| `SCREENING_UNAVAILABLE`  | screening_unavailable    |
| `PERSON_NOT_FOUND`       | person_not_found         |
| `IDEMPOTENCY_KEY_REUSED` | idempotency_key_reused   |