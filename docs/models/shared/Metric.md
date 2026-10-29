# Metric

assessments: assessments admitted. webhook_deliveries: events sent to endpoints, one per event per endpoint. key_usage_events: key-usage events accepted.

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