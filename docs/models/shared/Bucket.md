# Bucket

The period each count covers: an hour, or a day or a week (from Monday) in time_zone. Hour buckets are whole UTC hours in any time zone.

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