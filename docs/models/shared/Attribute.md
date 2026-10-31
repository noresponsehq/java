# Attribute

name_gender is the records' gender against the submitted first name's usual one.

## Example Usage

```java
import org.noresponse.models.shared.Attribute;

Attribute value = Attribute.BIRTH_DATE;

// Open enum: use .of() to create instances from custom string values
Attribute custom = Attribute.of("custom_value");
```


## Values

| Name          | Value         |
| ------------- | ------------- |
| `BIRTH_DATE`  | birth_date    |
| `GENDER`      | gender        |
| `NATIONALITY` | nationality   |
| `NAME_GENDER` | name_gender   |