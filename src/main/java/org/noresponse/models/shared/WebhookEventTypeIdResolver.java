package org.noresponse.models.shared;

import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.GenericTypeIdResolver;

/**
 * WebhookEventTypeIdResolver
 *
 * <p>The body of every webhook delivery: {id, type, created_at, data}, where type chooses the shape of
 * data. Deliveries are at least once and unordered: deduplicate on id, and keep an assessment only
 * when its detail.assessment_version is newer than the one you hold. GET
 * /v1/assessments/{assessment_id} stays authoritative.
 */
public class WebhookEventTypeIdResolver extends GenericTypeIdResolver<WebhookEvent> {

    public WebhookEventTypeIdResolver() {
        super(UnknownWebhookEvent.class);
        initializeTypeMap();
    }

    private void initializeTypeMap() {
        registerType("assessment.updated", AssessmentUpdatedEvent.class);
        registerType("endpoint.verification", EndpointVerificationEvent.class);
        registerType("account.deleted", AccountDeletedEvent.class);
        registerType("workspace.deleted", WorkspaceDeletedEvent.class);
        registerType("webhook_endpoint.disabled", WebhookEndpointDisabledEvent.class);
        registerType("account.risk_changed", AccountRiskChangedEvent.class);
        registerType("audit.event", AuditEventEvent.class);
    }

    @Override
    public String idFromValue(Object value) {
        if (value == null) {
            return null;
        }

        // Handle known types by checking if they implement the discriminator method
        if (value instanceof WebhookEvent) {
            WebhookEvent discriminated = (WebhookEvent) value;
            return discriminated.type();
        }

        throw new IllegalArgumentException(
                "Unknown value type: " + value.getClass().getName());
    }

    @Override
    public String getDescForKnownTypeIds() {
        return "WebhookEvent type resolver";
    }
}
