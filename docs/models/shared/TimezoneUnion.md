# TimezoneUnion


## Supported Types

### `String`

```java
TimezoneUnion value = TimezoneUnion.of("example");
```

### `double`

```java
TimezoneUnion value = TimezoneUnion.of(2785.03);
```

### `boolean`

```java
TimezoneUnion value = TimezoneUnion.of(true);
```

### [`Timezone`](../../models/shared/Timezone.md)

```java
TimezoneUnion value = TimezoneUnion.of(Timezone.builder()
    .build());
```

### `List<Object>`

```java
TimezoneUnion value = TimezoneUnion.of(List.of(
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
} else if (value.timezone().isPresent()) {
    org.noresponse.models.shared.Timezone timezoneValue = value.timezone().get();
    // Handle timezone variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
