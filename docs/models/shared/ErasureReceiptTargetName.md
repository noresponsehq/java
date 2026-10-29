# ErasureReceiptTargetName

## Example Usage

```java
import org.noresponse.models.shared.ErasureReceiptTargetName;

ErasureReceiptTargetName value = ErasureReceiptTargetName.SERVING;

// Open enum: use .of() to create instances from custom string values
ErasureReceiptTargetName custom = ErasureReceiptTargetName.of("custom_value");
```


## Values

| Name                    | Value                   |
| ----------------------- | ----------------------- |
| `SERVING`               | serving                 |
| `OUTBOX`                | outbox                  |
| `CACHE`                 | cache                   |
| `DOWNSTREAM_PROJECTION` | downstream_projection   |