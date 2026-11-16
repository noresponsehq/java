# ProfileCitationKind

record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in lookup.checks.

## Example Usage

```java
import org.noresponse.models.shared.ProfileCitationKind;

ProfileCitationKind value = ProfileCitationKind.RECORD;

// Open enum: use .of() to create instances from custom string values
ProfileCitationKind custom = ProfileCitationKind.of("custom_value");
```


## Values

| Name     | Value    |
| -------- | -------- |
| `RECORD` | record   |
| `POST`   | post     |
| `CHECK`  | check    |