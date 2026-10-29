# Population

first_answers uses anonymous counters that can include erased detail. retained_first_answers computes exact intersections over retained first answers within 31 days. Requests exceeding the 100000-record work budget are refused.

## Example Usage

```java
import org.noresponse.models.shared.Population;

Population value = Population.FIRST_ANSWERS;

// Open enum: use .of() to create instances from custom string values
Population custom = Population.of("custom_value");
```


## Values

| Name                     | Value                    |
| ------------------------ | ------------------------ |
| `FIRST_ANSWERS`          | first_answers            |
| `RETAINED_FIRST_ANSWERS` | retained_first_answers   |