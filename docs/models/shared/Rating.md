# Rating

unique: an email or phone, which belongs to one person by design. distinctive: rare enough that a new search on it likely finds one person. common: shared by many people, so a search on it finds strangers. A new search is suggested for unique and distinctive values only; any value can still be searched.

## Example Usage

```java
import org.noresponse.models.shared.Rating;

Rating value = Rating.UNIQUE;

// Open enum: use .of() to create instances from custom string values
Rating custom = Rating.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `UNIQUE`      | unique        |
| `DISTINCTIVE` | distinctive   |
| `COMMON`      | common        |