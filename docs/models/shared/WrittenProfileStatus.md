# WrittenProfileStatus

queued: the profile is being written. written: summary, statements and leads hold it. failed: it could not be written; error_code says why and no content is returned.

## Example Usage

```java
import org.noresponse.models.shared.WrittenProfileStatus;

WrittenProfileStatus value = WrittenProfileStatus.QUEUED;

// Open enum: use .of() to create instances from custom string values
WrittenProfileStatus custom = WrittenProfileStatus.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `QUEUED`  | queued    |
| `WRITTEN` | written   |
| `FAILED`  | failed    |