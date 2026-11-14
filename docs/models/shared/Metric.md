# Metric

assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per endpoint. key_usage_events: key-usage events accepted. screenings: people a screening recorded a final result for, one per person per screening; a failed person counts only when its lookups were kept.

## Example Usage

```java
import org.noresponse.models.shared.Metric;

Metric value = Metric.ASSESSMENTS;

// Open enum: use .of() to create instances from custom string values
Metric custom = Metric.of("custom_value");
```


## Values

| Name                 | Value                |
| -------------------- | -------------------- |
| `ASSESSMENTS`        | assessments          |
| `WEBHOOK_DELIVERIES` | webhook_deliveries   |
| `KEY_USAGE_EVENTS`   | key_usage_events     |
| `SCREENINGS`         | screenings           |