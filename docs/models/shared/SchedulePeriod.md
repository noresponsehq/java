# SchedulePeriod

How often the person is investigated again: daily, weekly or monthly, counted from when the schedule was set; off runs no scheduled investigation.

## Example Usage

```java
import org.noresponse.models.shared.SchedulePeriod;

SchedulePeriod value = SchedulePeriod.DAILY;

// Open enum: use .of() to create instances from custom string values
SchedulePeriod custom = SchedulePeriod.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `DAILY`   | daily     |
| `WEEKLY`  | weekly    |
| `MONTHLY` | monthly   |
| `OFF`     | off       |