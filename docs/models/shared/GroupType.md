# GroupType

What the group's people share: an event, an organisation, a place or another attribute.

## Example Usage

```java
import org.noresponse.models.shared.GroupType;

GroupType value = GroupType.EVENT;

// Open enum: use .of() to create instances from custom string values
GroupType custom = GroupType.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `EVENT`        | event          |
| `ORGANISATION` | organisation   |
| `PLACE`        | place          |
| `CUSTOM`       | custom         |