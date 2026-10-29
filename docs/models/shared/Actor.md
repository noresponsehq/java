# Actor


## Supported Types

### [`UserActor`](../../models/shared/UserActor.md)

```java
Actor value = Actor.of(UserActor.builder()
    .externalUserId("usr_customer_admin")
    .consoleCredentialId("01JA2Z8K6Q4V7W9XBCDEFGHJKM")
    .principalId("mem_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
    .build());
```

### [`KeyActor`](../../models/shared/KeyActor.md)

```java
Actor value = Actor.of(KeyActor.builder()
    .keyId("01JA2Z8K6Q4V7W9XBCDEFGHJKM")
    .build());
```

## Consumption Patterns

### Java 11+ (Accessor Methods)

```java
if (value.userActor().isPresent()) {
    org.noresponse.models.shared.UserActor userActorValue = value.userActor().get();
    // Handle userActor variant
} else if (value.keyActor().isPresent()) {
    org.noresponse.models.shared.KeyActor keyActorValue = value.keyActor().get();
    // Handle keyActor variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
