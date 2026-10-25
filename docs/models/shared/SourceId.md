# SourceId

A NoResponse source: github (GitHub accounts), phone (the phone lookup), breach (breach exposure), rdap (the email domain's registration), mx (the email domain's mail servers) or ipintel (IP intelligence).

## Example Usage

```java
import org.noresponse.models.shared.SourceId;

SourceId value = SourceId.GITHUB;

// Open enum: use .of() to create instances from custom string values
SourceId custom = SourceId.of("custom_value");
```


## Values

| Name      | Value     |
| --------- | --------- |
| `GITHUB`  | github    |
| `PHONE`   | phone     |
| `BREACH`  | breach    |
| `RDAP`    | rdap      |
| `MX`      | mx        |
| `IPINTEL` | ipintel   |