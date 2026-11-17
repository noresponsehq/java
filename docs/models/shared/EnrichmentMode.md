# EnrichmentMode

off: enrichment starts no search. suggest: enrichment starts no search by itself. automatic: after a workspace person's own investigation, and each time a search on that person finishes, enrichment starts searches by itself.

## Example Usage

```java
import org.noresponse.models.shared.EnrichmentMode;

EnrichmentMode value = EnrichmentMode.OFF;

// Open enum: use .of() to create instances from custom string values
EnrichmentMode custom = EnrichmentMode.of("custom_value");
```


## Values

| Name        | Value       |
| ----------- | ----------- |
| `OFF`       | off         |
| `SUGGEST`   | suggest     |
| `AUTOMATIC` | automatic   |