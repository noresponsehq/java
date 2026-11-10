# ScreeningPlatformCoverageStatus

covered read at least one profile; none_found looked and found no account; inaccessible found accounts that are private or locked; failed could not finish looking; unavailable has no resolver or collector enabled.

## Example Usage

```java
import org.noresponse.models.shared.ScreeningPlatformCoverageStatus;

ScreeningPlatformCoverageStatus value = ScreeningPlatformCoverageStatus.COVERED;

// Open enum: use .of() to create instances from custom string values
ScreeningPlatformCoverageStatus custom = ScreeningPlatformCoverageStatus.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `COVERED`      | covered        |
| `NONE_FOUND`   | none_found     |
| `INACCESSIBLE` | inaccessible   |
| `FAILED`       | failed         |
| `UNAVAILABLE`  | unavailable    |