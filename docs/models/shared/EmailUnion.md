# EmailUnion


## Supported Types

### `String`

```java
EmailUnion value = EmailUnion.of("<value>");
```

### `double`

```java
EmailUnion value = EmailUnion.of(2785.03);
```

### `boolean`

```java
EmailUnion value = EmailUnion.of(true);
```

### [`Email`](../../models/shared/Email.md)

```java
EmailUnion value = EmailUnion.of(Email.builder()
    .build());
```

### `List<Object>`

```java
EmailUnion value = EmailUnion.of(List.of(
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
} else if (value.email().isPresent()) {
    org.noresponse.models.shared.Email emailValue = value.email().get();
    // Handle email variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
