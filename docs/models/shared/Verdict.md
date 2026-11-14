# Verdict

A reviewer's overrule (POST .../people/{person_id}/records), which decides joined whatever the likelihood and sticks for as long as a lookup finds the same record. Absent when nobody overruled it.

## Example Usage

```java
import org.noresponse.models.shared.Verdict;

Verdict value = Verdict.JOINED;

// Open enum: use .of() to create instances from custom string values
Verdict custom = Verdict.of("custom_value");
```


## Values

| Name       | Value      |
| ---------- | ---------- |
| `JOINED`   | joined     |
| `REJECTED` | rejected   |