# ListEntryKind

email matches one address, whatever the case of its local part. email_domain matches every address of the registrable domain and of its subdomains.

## Example Usage

```java
import org.noresponse.models.shared.ListEntryKind;

ListEntryKind value = ListEntryKind.EMAIL;

// Open enum: use .of() to create instances from custom string values
ListEntryKind custom = ListEntryKind.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `EMAIL`        | email          |
| `EMAIL_DOMAIN` | email_domain   |