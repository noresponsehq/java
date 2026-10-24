package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.JsonNode;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.UnknownType;

/**
 * UnknownWebhookEvent
 *
 * <p>The body of every webhook delivery: {id, type, created_at, data}, where type chooses the shape of
 * data. Deliveries are at least once and unordered: deduplicate on id, and keep an assessment only
 * when its detail.assessment_version is newer than the one you hold. GET
 * /v1/assessments/{assessment_id} stays authoritative.
 */
public class UnknownWebhookEvent extends UnknownType implements WebhookEvent {

    @JsonCreator
    public UnknownWebhookEvent(JsonNode rawNode) {
        super(rawNode);
    }

    @Override
    public String type() {
        return extractDiscriminator("type").orElse("UNKNOWN");
    }
}
