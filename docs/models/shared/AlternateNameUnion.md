# AlternateNameUnion


## Supported Types

### `String`

```java
AlternateNameUnion value = AlternateNameUnion.of("example");
```

### `double`

```java
AlternateNameUnion value = AlternateNameUnion.of(2785.03);
```

### `boolean`

```java
AlternateNameUnion value = AlternateNameUnion.of(true);
```

### [`AlternateName`](../../models/shared/AlternateName.md)

```java
AlternateNameUnion value = AlternateNameUnion.of(AlternateName.builder()
    .build());
```

### `List<Object>`

```java
AlternateNameUnion value = AlternateNameUnion.of(List.of(
    "example",
    "example"));
```

## Consumption Patterns

### Java 11+ (Accessor Methods)

```java
if (value.string().isPresent()) {
    java.lang.String stringValue = value.string().get();
    // Handle string variant
} else if (value.asDouble().isPresent()) {
    java.lang.Double doubleValue = value.asDouble().get();
    // Handle asDouble variant
} else if (value.asBoolean().isPresent()) {
    java.lang.Boolean booleanValue = value.asBoolean().get();
    // Handle asBoolean variant
} else if (value.alternateName().isPresent()) {
    org.noresponse.models.shared.AlternateName alternateNameValue = value.alternateName().get();
    // Handle alternateName variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
