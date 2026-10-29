# Conclusion

## Example Usage

```java
import org.noresponse.models.shared.Conclusion;

Conclusion value = Conclusion.BENIGN;

// Open enum: use .of() to create instances from custom string values
Conclusion custom = Conclusion.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `BENIGN`       | benign         |
| `ABUSE`        | abuse          |
| `INCONCLUSIVE` | inconclusive   |