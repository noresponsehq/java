# NameCandidateUnion


## Supported Types

### `String`

```java
NameCandidateUnion value = NameCandidateUnion.of("example");
```

### `double`

```java
NameCandidateUnion value = NameCandidateUnion.of(2785.03);
```

### `boolean`

```java
NameCandidateUnion value = NameCandidateUnion.of(true);
```

### [`NameCandidate`](../../models/shared/NameCandidate.md)

```java
NameCandidateUnion value = NameCandidateUnion.of(NameCandidate.builder()
    .build());
```

### `List<Object>`

```java
NameCandidateUnion value = NameCandidateUnion.of(List.of(
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
} else if (value.nameCandidate().isPresent()) {
    org.noresponse.models.shared.NameCandidate nameCandidateValue = value.nameCandidate().get();
    // Handle nameCandidate variant
} else if (value.arrayOfObject().isPresent()) {
    java.util.List<java.lang.Object> arrayOfObjectValue = value.arrayOfObject().get();
    // Handle arrayOfObject variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
