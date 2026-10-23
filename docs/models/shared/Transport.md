# Transport

Where the context was derived: backend (also when the request named no transport), browser, or provider_hook for a hosted provider hook.

## Example Usage

```java
import org.noresponse.models.shared.Transport;

Transport value = Transport.BACKEND;

// Open enum: use .of() to create instances from custom string values
Transport custom = Transport.of("custom_value");
```


## Values

| Name            | Value           |
| --------------- | --------------- |
| `BACKEND`       | backend         |
| `BROWSER`       | browser         |
| `PROVIDER_HOOK` | provider_hook   |