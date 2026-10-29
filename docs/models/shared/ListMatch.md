# ListMatch

The list action captured at the first answer. Requires revision first. Older answers without captured list state do not match, including none.

## Example Usage

```java
import org.noresponse.models.shared.ListMatch;

ListMatch value = ListMatch.ALLOW;
```


## Values

| Name    | Value   |
| ------- | ------- |
| `ALLOW` | allow   |
| `BLOCK` | block   |
| `NONE`  | none    |