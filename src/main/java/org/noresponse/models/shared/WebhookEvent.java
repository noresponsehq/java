package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import java.lang.String;

/**
 * WebhookEvent
 *
 * <p>The body of every webhook delivery: {id, type, created_at, data}, where type chooses the shape of
 * data. Deliveries are at least once and unordered: deduplicate on id, and keep an assessment only
 * when its detail.assessment_version is newer than the one you hold. GET
 * /v1/assessments/{assessment_id} stays authoritative.
 */
@JsonTypeInfo(
        use = Id.CUSTOM,
        property = "type",
        include = As.EXISTING_PROPERTY,
        visible = true,
        defaultImpl = UnknownWebhookEvent.class)
@JsonTypeIdResolver(WebhookEventTypeIdResolver.class)
public interface WebhookEvent {

    String type();
}
