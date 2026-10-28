# Exclusion

## Example Usage

```java
import org.noresponse.models.shared.Exclusion;

Exclusion value = Exclusion.EXPIRED_OR_ERASED_RECORDS;

// Open enum: use .of() to create instances from custom string values
Exclusion custom = Exclusion.of("custom_value");
```


## Values

| Name                               | Value                              |
| ---------------------------------- | ---------------------------------- |
| `EXPIRED_OR_ERASED_RECORDS`        | expired_or_erased_records          |
| `SECRETS_AND_CREDENTIAL_DIGESTS`   | secrets_and_credential_digests     |
| `PRIVATE_SOURCE_AND_WORK_PAYLOADS` | private_source_and_work_payloads   |
| `CROSS_CUSTOMER_DATA`              | cross_customer_data                |
| `LEGACY_OUTCOME_NOTES`             | legacy_outcome_notes               |
| `UNCAPTURED_HISTORICAL_DECISIONS`  | uncaptured_historical_decisions    |