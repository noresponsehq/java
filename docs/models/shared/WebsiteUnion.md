# WebsiteUnion


## Supported Types

### `String`

```java
WebsiteUnion value = WebsiteUnion.of("<value>");
```

### `double`

```java
WebsiteUnion value = WebsiteUnion.of(2785.03);
```

### `boolean`

```java
WebsiteUnion value = WebsiteUnion.of(true);
```

### [`Website`](../../models/shared/Website.md)

```java
WebsiteUnion value = WebsiteUnion.of(Website.builder()
    .build());
```

### `List<Object>`

```java
WebsiteUnion value = WebsiteUnion.of(List.of(
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
} else if (value.website().isPresent()) {
    org.noresponse.models.shared.Website websiteValue = value.website().get();
    // Handle website variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
