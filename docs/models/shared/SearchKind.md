# SearchKind

The kind of the value searched. A domain search finds the leaked rows of every email address at the domain.

## Example Usage

```java
import org.noresponse.models.shared.SearchKind;

SearchKind value = SearchKind.EMAIL;

// Open enum: use .of() to create instances from custom string values
SearchKind custom = SearchKind.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `EMAIL`       | email         |
| `PHONE`       | phone         |
| `USERNAME`    | username      |
| `PERSON_NAME` | person_name   |
| `PASSWORD`    | password      |
| `HASH`        | hash          |
| `IP`          | ip            |
| `DOMAIN`      | domain        |