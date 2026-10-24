# AssessmentUpdatedData

The assessment, or the minimal notice that it expired.


## Supported Types

### [`Assessment`](../../models/shared/Assessment.md)

```java
AssessmentUpdatedData value = AssessmentUpdatedData.of(Assessment.builder()
    .id("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
    .status(Status.PARTIAL)
    .flow(Flow.PURCHASE)
    .depth(Depth.QUICK)
    .detail(AssessmentDetail.builder()
        .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
        .accountId("acct_7f3a9c")
        .status(Status.PENDING)
        .confidence(Confidence.UNAVAILABLE)
        .incompleteSources(List.of())
        .policyVersion("heuristic-2026-10.1")
        .assessmentVersion(220109L)
        .createdAt("2026-10-14T09:00:00.000Z")
        .updatedAt("2026-10-14T09:00:00.412Z")
        .expiresAt("2027-10-14T09:00:00.000Z")
        .timing(AssessmentDetailTiming.builder()
            .deadlineMs(357529L)
            .elapsedMs(13488L)
            .build())
        .requestId("req-2026-10-14-0001")
        .risk(Risk.builder()
            .score(976740L)
            .band(Band.LOW)
            .recommendation(Recommendation.REVIEW)
            .confidence(RiskConfidence.MEDIUM)
            .policyVersion("risk-2026-10.3")
            .reasons(List.of())
            .coverage(RiskCoverage.builder()
                .answered(501619L)
                .failed(72011L)
                .pending(428029L)
                .build())
            .summary(RiskSummary.builder()
                .phone(RiskSummaryPhone.builder()
                    .country("AU")
                    .carrier("Telstra")
                    .build())
                .ip(IpSummary.builder()
                    .country("AU")
                    .build())
                .build())
            .details(RiskDetails.builder()
                .phoneNames(List.of(
                    RiskDetailsPhoneName.builder()
                        .value("Alex Example")
                        .seenBy(525699L)
                        .build()))
                .breaches(List.of(
                    Breach.builder()
                        .name("Example Forum")
                        .kind(BreachKind.SITE_BREACH)
                        .matchedOn(BreachSelector.EMAIL)
                        .domain("forum.example.com")
                        .category("forum")
                        .build()))
                .breachNames(List.of(
                    RiskDetailsBreachName.builder()
                        .value("Alex Example")
                        .seenIn(982972L)
                        .build()))
                .build())
            .build())
        .signals(List.of(
            Signal.builder()
                .type("account_presence")
                .identifier(IdentifierKind.EMAIL)
                .outcome(SignalOutcome.NOT_FOUND)
                .sourceId("github")
                .freshness(Freshness.FRESH)
                .evidenceRef("evr_01JA2Z8K6Q4V7W9XBCDEFGHJKN")
                .observedAt("2026-10-14T09:00:00.210Z")
                .lastCheckedAt("2026-10-14T09:00:00.210Z")
                .enrichment(PhoneEnrichment.builder()
                    .fields(PhoneEnrichmentFields.builder()
                        .phone("+61412345678")
                        .name("Alex Example")
                        .carrier("Telstra")
                        .lineType("mobile")
                        .entityType("person")
                        .countryCode("61")
                        .country("Australia")
                        .state("New South Wales")
                        .city("Sydney")
                        .location("Sydney, New South Wales, Australia")
                        .nationalFormat("0412 345 678")
                        .jobTitle("Engineer")
                        .companyName("Example Pty Ltd")
                        .email("new.user@example.com")
                        .website("https://example.com")
                        .firstCallAt("2026-09-01T00:00:00.000Z")
                        .lastCallAt("2026-10-10T00:00:00.000Z")
                        .matchedName("Alex Example")
                        .alternateNames(List.of(
                            AlternateNameUnion.of("Alex E.")))
                        .nameCandidates(List.of(
                            NameCandidateUnion.of("Alex Example")))
                        .timezones(List.of(
                            TimezoneUnion.of("Australia/Sydney")))
                        .emails(List.of(
                            EmailUnion.of("new.user@example.com")))
                        .websites(List.of(
                            WebsiteUnion.of("https://example.com")))
                        .locations(List.of(
                            LocationUnion.of("Sydney, New South Wales, Australia")))
                        .socialProfiles(List.of(
                            SocialProfileUnion.of("https://example.com/alexexample")))
                        .build())
                    .build())
                .build()))
        .assessmentUrl("/v1/assessments/asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
        .authContext(AssessmentDetailAuthContext.builder()
            .provider("clerk")
            .issuer("https://example-app.clerk.accounts.dev")
            .verified(false)
            .transport(Transport.PROVIDER_HOOK)
            .subject("user_2EXAMPLE")
            .sessionId("sess_2EXAMPLE")
            .organizationId("org_2EXAMPLE")
            .build())
        .build())
    .account(Account.builder()
        .id("acct_7f3a9c")
        .risk(AccountRisk.builder()
            .score(387158L)
            .decision(AccountDecision.REVIEW)
            .reasons(List.of())
            .build())
        .decisionsByFlow(AccountDecisionsByFlow.builder()
            .accountCreation(AccountDecision.ALLOW)
            .login(AccountDecision.ALLOW)
            .purchase(AccountDecision.BLOCK)
            .build())
        .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
        .updatedAt("2026-10-14T09:00:00.412Z")
        .riskVersion(282004L)
        .build())
    .build());
```

**Referred Types:**

- [Status](../../models/shared/Status.md)
- [Flow](../../models/shared/Flow.md)
- [Depth](../../models/shared/Depth.md)
- [AssessmentDetail](../../models/shared/AssessmentDetail.md)
- [Confidence](../../models/shared/Confidence.md)
- [AssessmentDetailTiming](../../models/shared/AssessmentDetailTiming.md)
- [Risk](../../models/shared/Risk.md)
- [Band](../../models/shared/Band.md)
- [Recommendation](../../models/shared/Recommendation.md)
- [RiskConfidence](../../models/shared/RiskConfidence.md)
- [RiskCoverage](../../models/shared/RiskCoverage.md)
- [RiskSummary](../../models/shared/RiskSummary.md)
- [RiskSummaryPhone](../../models/shared/RiskSummaryPhone.md)
- [IpSummary](../../models/shared/IpSummary.md)
- [RiskDetails](../../models/shared/RiskDetails.md)
- [RiskDetailsPhoneName](../../models/shared/RiskDetailsPhoneName.md)
- [Breach](../../models/shared/Breach.md)
- [BreachKind](../../models/shared/BreachKind.md)
- [BreachSelector](../../models/shared/BreachSelector.md)
- [RiskDetailsBreachName](../../models/shared/RiskDetailsBreachName.md)
- [Signal](../../models/shared/Signal.md)
- [IdentifierKind](../../models/shared/IdentifierKind.md)
- [SignalOutcome](../../models/shared/SignalOutcome.md)
- [Freshness](../../models/shared/Freshness.md)
- [PhoneEnrichment](../../models/shared/PhoneEnrichment.md)
- [PhoneEnrichmentFields](../../models/shared/PhoneEnrichmentFields.md)
- [AlternateNameUnion](../../models/shared/AlternateNameUnion.md)
- [NameCandidateUnion](../../models/shared/NameCandidateUnion.md)
- [TimezoneUnion](../../models/shared/TimezoneUnion.md)
- [EmailUnion](../../models/shared/EmailUnion.md)
- [WebsiteUnion](../../models/shared/WebsiteUnion.md)
- [LocationUnion](../../models/shared/LocationUnion.md)
- [SocialProfileUnion](../../models/shared/SocialProfileUnion.md)
- [AssessmentDetailAuthContext](../../models/shared/AssessmentDetailAuthContext.md)
- [Transport](../../models/shared/Transport.md)
- [Account](../../models/shared/Account.md)
- [AccountRisk](../../models/shared/AccountRisk.md)
- [AccountDecision](../../models/shared/AccountDecision.md)
- [AccountDecisionsByFlow](../../models/shared/AccountDecisionsByFlow.md)

### [`AssessmentExpired`](../../models/shared/AssessmentExpired.md)

```java
AssessmentUpdatedData value = AssessmentUpdatedData.of(AssessmentExpired.builder()
    .assessmentId("asm_01JA2Z8K6Q4V7W9XBCDEFGHJKM")
    .accountId("acct_7f3a9c")
    .assessmentVersion(710941L)
    .build());
```

## Consumption Patterns

### Java 11+ (Accessor Methods)

```java
if (value.assessment().isPresent()) {
    org.noresponse.models.shared.Assessment sessmentValue = value.assessment().get();
    // Handle assessment variant
} else if (value.assessmentExpired().isPresent()) {
    org.noresponse.models.shared.AssessmentExpired sessmentExpiredValue = value.assessmentExpired().get();
    // Handle assessmentExpired variant
} else if (value.asJson().isPresent()) {
    com.fasterxml.jackson.databind.JsonNode raw = value.asJson().get();
    // Handle unknown variant fallback
}
```
