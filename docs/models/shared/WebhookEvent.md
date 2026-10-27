# WebhookEvent

The body of every webhook delivery: {id, type, created_at, data}, where type chooses the shape of data. Deliveries are at least once and unordered: deduplicate on id, and keep an assessment only when its detail.assessment_version is newer than the one you hold. GET /v1/assessments/{assessment_id} stays authoritative.


## Supported Types

### Discriminator: `type`

| Value | Type |
| ----- | ---- |
| `"assessment.updated"` | [AssessmentUpdatedEvent](../../models/shared/AssessmentUpdatedEvent.md) |
| `"endpoint.verification"` | [EndpointVerificationEvent](../../models/shared/EndpointVerificationEvent.md) |
| `"account.deleted"` | [AccountDeletedEvent](../../models/shared/AccountDeletedEvent.md) |
| `"workspace.deleted"` | [WorkspaceDeletedEvent](../../models/shared/WorkspaceDeletedEvent.md) |
| `"webhook_endpoint.disabled"` | [WebhookEndpointDisabledEvent](../../models/shared/WebhookEndpointDisabledEvent.md) |
| `"account.risk_changed"` | [AccountRiskChangedEvent](../../models/shared/AccountRiskChangedEvent.md) |

### [`AssessmentUpdatedEvent`](../../models/shared/AssessmentUpdatedEvent.md)

Discriminator value: `"assessment.updated"`

```java
WebhookEvent value = AssessmentUpdatedEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(AssessmentUpdatedData.of(AssessmentExpired.builder()
        .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
        .accountId("acct_7f3a9c")
        .assessmentVersion(386043L)
        .build()))
    .build();
```

**Referred Types:** [AssessmentUpdatedData](../../models/shared/AssessmentUpdatedData.md), [AssessmentExpired](../../models/shared/AssessmentExpired.md)

### [`EndpointVerificationEvent`](../../models/shared/EndpointVerificationEvent.md)

Discriminator value: `"endpoint.verification"`

```java
WebhookEvent value = EndpointVerificationEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(EndpointVerification.builder()
        .endpointId("whe_01JA2Z0A1B2C3D4E5F6G7H8J9K")
        .challenge("q3Jt8mV0x2Yb7Lk9Pz4Rw6Hn1Sd5Fg8Ja0Tc3Ue7Wi4")
        .build())
    .build();
```

**Referred Types:** [EndpointVerification](../../models/shared/EndpointVerification.md)

### [`AccountDeletedEvent`](../../models/shared/AccountDeletedEvent.md)

Discriminator value: `"account.deleted"`

```java
WebhookEvent value = AccountDeletedEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(AccountDeleted.builder()
        .accountId("acct_7f3a9c")
        .assessmentsDeleted(134857L)
        .deletedAt("2026-10-23T09:00:00.000Z")
        .build())
    .build();
```

**Referred Types:** [AccountDeleted](../../models/shared/AccountDeleted.md)

### [`WorkspaceDeletedEvent`](../../models/shared/WorkspaceDeletedEvent.md)

Discriminator value: `"workspace.deleted"`

```java
WebhookEvent value = WorkspaceDeletedEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(WorkspaceDeleted.builder()
        .workspaceId("tnt_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
        .environment(Environment.TEST)
        .deletedAt("2026-10-23T09:00:00.000Z")
        .build())
    .build();
```

**Referred Types:** [WorkspaceDeleted](../../models/shared/WorkspaceDeleted.md), [Environment](../../models/shared/Environment.md)

### [`WebhookEndpointDisabledEvent`](../../models/shared/WebhookEndpointDisabledEvent.md)

Discriminator value: `"webhook_endpoint.disabled"`

```java
WebhookEvent value = WebhookEndpointDisabledEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(WebhookEndpointDisabled.builder()
        .endpointId("whe_01JA2Z0A1B2C3D4E5F6G7H8J9K")
        .failingSince("2026-10-20T09:58:31.000Z")
        .disabledAt("2026-10-23T10:00:00.000Z")
        .build())
    .build();
```

**Referred Types:** [WebhookEndpointDisabled](../../models/shared/WebhookEndpointDisabled.md)

### [`AccountRiskChangedEvent`](../../models/shared/AccountRiskChangedEvent.md)

Discriminator value: `"account.risk_changed"`

```java
WebhookEvent value = AccountRiskChangedEvent.builder()
    .id("evt_01JA2ZB4N5P6Q7R8S9T0VWXYZ1")
    .createdAt("2026-10-14T09:00:02.000Z")
    .data(AccountRiskChanged.builder()
        .account(Account.builder()
            .id("acct_7f3a9c")
            .risk(AccountRisk.builder()
                .score(945488L)
                .decision(AccountDecision.HELD)
                .reasons(List.of(
                    AccountRiskReason.builder()
                        .code(ReasonCode.SHARED_IP)
                        .direction(Direction.RAISES)
                        .impact(Impact.LOW)
                        .listEntryId("lst_01JA2ZE4K5M6N7P8Q9R0S1T2V3")
                        .build()))
                .build())
            .decisionsByFlow(AccountDecisionsByFlow.builder()
                .accountCreation(AccountDecision.HELD)
                .login(AccountDecision.BLOCK)
                .purchase(AccountDecision.REVIEW)
                .build())
            .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
            .updatedAt("2026-10-14T09:00:00.412Z")
            .riskVersion(99216L)
            .build())
        .previousDecisionsByFlow(AccountDecisionsByFlow.builder()
            .accountCreation(AccountDecision.BLOCK)
            .login(AccountDecision.REVIEW)
            .purchase(AccountDecision.ALLOW)
            .build())
        .build())
    .build();
```

**Referred Types:**

- [AccountRiskChanged](../../models/shared/AccountRiskChanged.md)
- [Account](../../models/shared/Account.md)
- [AccountRisk](../../models/shared/AccountRisk.md)
- [AccountDecision](../../models/shared/AccountDecision.md)
- [AccountRiskReason](../../models/shared/AccountRiskReason.md)
- [ReasonCode](../../models/shared/ReasonCode.md)
- [Direction](../../models/shared/Direction.md)
- [Impact](../../models/shared/Impact.md)
- [AccountDecisionsByFlow](../../models/shared/AccountDecisionsByFlow.md)

## Consumption Patterns

### Java 11+ (Discriminator Switch)

```java
switch (value.type()) {
    case "assessment.updated":
        // Handle assessment.updated discriminator variant
        break;
    case "endpoint.verification":
        // Handle endpoint.verification discriminator variant
        break;
    case "account.deleted":
        // Handle account.deleted discriminator variant
        break;
    case "workspace.deleted":
        // Handle workspace.deleted discriminator variant
        break;
    case "webhook_endpoint.disabled":
        // Handle webhook_endpoint.disabled discriminator variant
        break;
    case "account.risk_changed":
        // Handle account.risk_changed discriminator variant
        break;
    default:
        // Handle unknown discriminator variant
}
```

### Java 16+ (Instanceof Pattern Matching)

```java
if (value instanceof AssessmentUpdatedEvent assessmentUpdatedEvent) {
    // Handle AssessmentUpdatedEvent variant
} else if (value instanceof EndpointVerificationEvent endpointVerificationEvent) {
    // Handle EndpointVerificationEvent variant
} else if (value instanceof AccountDeletedEvent accountDeletedEvent) {
    // Handle AccountDeletedEvent variant
} else if (value instanceof WorkspaceDeletedEvent workspaceDeletedEvent) {
    // Handle WorkspaceDeletedEvent variant
} else if (value instanceof WebhookEndpointDisabledEvent webhookEndpointDisabledEvent) {
    // Handle WebhookEndpointDisabledEvent variant
} else if (value instanceof AccountRiskChangedEvent accountRiskChangedEvent) {
    // Handle AccountRiskChangedEvent variant
} else {
    // Handle unknown discriminator variant
}
```

### Java 21+ (Type Pattern Switch)

```java
switch (value) {
    case AssessmentUpdatedEvent assessmentUpdatedEvent -> {
        // Handle AssessmentUpdatedEvent variant
    }
    case EndpointVerificationEvent endpointVerificationEvent -> {
        // Handle EndpointVerificationEvent variant
    }
    case AccountDeletedEvent accountDeletedEvent -> {
        // Handle AccountDeletedEvent variant
    }
    case WorkspaceDeletedEvent workspaceDeletedEvent -> {
        // Handle WorkspaceDeletedEvent variant
    }
    case WebhookEndpointDisabledEvent webhookEndpointDisabledEvent -> {
        // Handle WebhookEndpointDisabledEvent variant
    }
    case AccountRiskChangedEvent accountRiskChangedEvent -> {
        // Handle AccountRiskChangedEvent variant
    }
    default -> {
        // Handle unknown discriminator variant
    }
}
```
