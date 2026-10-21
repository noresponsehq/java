# RateLimitErrorCode

Machine codes an HTTP 429 answer can carry.

## Example Usage

```java
import org.noresponse.models.shared.RateLimitErrorCode;

RateLimitErrorCode value = RateLimitErrorCode.RATE_LIMITED;

// Open enum: use .of() to create instances from custom string values
RateLimitErrorCode custom = RateLimitErrorCode.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `RATE_LIMITED` | rate_limited   |