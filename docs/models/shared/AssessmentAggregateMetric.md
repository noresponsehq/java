# AssessmentAggregateMetric

## Example Usage

```java
import org.noresponse.models.shared.AssessmentAggregateMetric;

AssessmentAggregateMetric value = AssessmentAggregateMetric.COUNT;

// Open enum: use .of() to create instances from custom string values
AssessmentAggregateMetric custom = AssessmentAggregateMetric.of("custom_value");
```


## Values

| Name              | Value             |
| ----------------- | ----------------- |
| `COUNT`           | count             |
| `SCORE_HISTOGRAM` | score_histogram   |
| `SCORE_OUTCOMES`  | score_outcomes    |
| `LATE_FLIPS`      | late_flips        |