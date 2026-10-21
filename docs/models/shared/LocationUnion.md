# LocationUnion


## Supported Types

### `String`

```java
LocationUnion value = LocationUnion.of("<value>");
```

### `double`

```java
LocationUnion value = LocationUnion.of(2785.03);
```

### `boolean`

```java
LocationUnion value = LocationUnion.of(true);
```

### [`Location`](../../models/shared/Location.md)

```java
LocationUnion value = LocationUnion.of(Location.builder()
    .build());
```

### `List<Object>`

```java
LocationUnion value = LocationUnion.of(List.of(
    "<value 1>",
    "<value 2>"));
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
} else if (value.location().isPresent()) {
    org.noresponse.models.shared.Location locationValue = value.location().get();
    // Handle location variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
