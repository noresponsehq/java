# Outcome

What the provider later learned about the assessed account. confirmed_abuse, account_takeover and chargeback report abuse; false_positive and legitimate withdraw an earlier abuse report of this tenant for the assessment; unknown is recorded only. Outcomes are append-only and the latest one recorded for an assessment wins.

## Example Usage

```java
import org.noresponse.models.shared.Outcome;

Outcome value = Outcome.CONFIRMED_ABUSE;

// Open enum: use .of() to create instances from custom string values
Outcome custom = Outcome.of("custom_value");
```


## Values

| Name               | Value              |
| ------------------ | ------------------ |
| `CONFIRMED_ABUSE`  | confirmed_abuse    |
| `ACCOUNT_TAKEOVER` | account_takeover   |
| `CHARGEBACK`       | chargeback         |
| `FALSE_POSITIVE`   | false_positive     |
| `LEGITIMATE`       | legitimate         |
| `UNKNOWN`          | unknown            |