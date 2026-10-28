# Bucket

The period each count covers, in UTC; a week starts on Monday.

## Example Usage

```java
import org.noresponse.models.shared.Bucket;

Bucket value = Bucket.HOUR;

// Open enum: use .of() to create instances from custom string values
Bucket custom = Bucket.of("custom_value");
```


## Values

| Name   | Value  |
| ------ | ------ |
| `HOUR` | hour   |
| `DAY`  | day    |
| `WEEK` | week   |