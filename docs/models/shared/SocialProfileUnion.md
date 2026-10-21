# SocialProfileUnion


## Supported Types

### `String`

```java
SocialProfileUnion value = SocialProfileUnion.of("<value>");
```

### `double`

```java
SocialProfileUnion value = SocialProfileUnion.of(2785.03);
```

### `boolean`

```java
SocialProfileUnion value = SocialProfileUnion.of(true);
```

### [`SocialProfile`](../../models/shared/SocialProfile.md)

```java
SocialProfileUnion value = SocialProfileUnion.of(SocialProfile.builder()
    .build());
```

### `List<Object>`

```java
SocialProfileUnion value = SocialProfileUnion.of(List.of(
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
} else if (value.socialProfile().isPresent()) {
    org.noresponse.models.shared.SocialProfile socialProfileValue = value.socialProfile().get();
    // Handle socialProfile variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
