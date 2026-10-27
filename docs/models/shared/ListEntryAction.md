# ListEntryAction

block decides block for the accounts the entry matches; allow decides allow. Block wins when both match.

## Example Usage

```java
import org.noresponse.models.shared.ListEntryAction;

ListEntryAction value = ListEntryAction.BLOCK;

// Open enum: use .of() to create instances from custom string values
ListEntryAction custom = ListEntryAction.of("custom_value");
```


## Values

| Name    | Value   |
| ------- | ------- |
| `BLOCK` | block   |
| `ALLOW` | allow   |