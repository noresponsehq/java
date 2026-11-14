# ErrorCode

Why a failed search found nothing: its sources did not answer, none could be asked, or it could not run before its attempts were spent.

## Example Usage

```java
import org.noresponse.models.shared.ErrorCode;

ErrorCode value = ErrorCode.NOT_ANSWERED;

// Open enum: use .of() to create instances from custom string values
ErrorCode custom = ErrorCode.of("custom_value");
```


## Values

| Name                        | Value                       |
| --------------------------- | --------------------------- |
| `NOT_ANSWERED`              | not_answered                |
| `NO_SOURCE`                 | no_source                   |
| `WORKER_ATTEMPTS_EXHAUSTED` | worker_attempts_exhausted   |