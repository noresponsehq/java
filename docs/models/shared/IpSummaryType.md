# IpSummaryType

The address's network type.

## Example Usage

```java
import org.noresponse.models.shared.IpSummaryType;

IpSummaryType value = IpSummaryType.RESIDENTIAL;

// Open enum: use .of() to create instances from custom string values
IpSummaryType custom = IpSummaryType.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `RESIDENTIAL` | residential   |
| `HOSTING`     | hosting       |
| `VPN`         | vpn           |
| `PROXY`       | proxy         |
| `TOR`         | tor           |
| `MOBILE`      | mobile        |
| `UNKNOWN`     | unknown       |