# RecordConflictSignal

anchors_disagree: records found by two of the identifiers you gave clash with each other, so those identifiers may belong to different people.

## Example Usage

```java
import org.noresponse.models.shared.RecordConflictSignal;

RecordConflictSignal value = RecordConflictSignal.ANCHORS_DISAGREE;

// Open enum: use .of() to create instances from custom string values
RecordConflictSignal custom = RecordConflictSignal.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `ANCHORS_DISAGREE` | anchors_disagree   |