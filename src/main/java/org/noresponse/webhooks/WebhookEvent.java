package org.noresponse.webhooks;

import java.util.Optional;
import org.noresponse.models.shared.Assessment;

/**
 * A verified delivery. Deliveries are at least once and unordered: deduplicate
 * on {@link #id()} and keep the highest assessment version per assessment.
 *
 * <p>For type {@code assessment.updated}, {@link #assessment()} holds the
 * assessment exactly as {@code assessments().get} returns it, or
 * {@link #expired()} holds the terminal expiry notice. For
 * {@code endpoint.verification}, {@link #verification()} holds the challenge.
 *
 * <p>{@link #isTest()} is true on an event sent by
 * {@code webhookEndpoints().sendTestEvent()}: acknowledge it and apply nothing.
 */
public final class WebhookEvent {
    private final String id;
    private final String type;
    private final String createdAt;
    private final Object data;
    private final boolean test;

    WebhookEvent(String id, String type, String createdAt, Object data) {
        this(id, type, createdAt, data, false);
    }

    WebhookEvent(String id, String type, String createdAt, Object data, boolean test) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.data = data;
        this.test = test;
    }

    public String id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String createdAt() {
        return createdAt;
    }

    public Optional<Assessment> assessment() {
        return data instanceof Assessment ? Optional.of((Assessment) data) : Optional.empty();
    }

    /** Present for the terminal expiry event: delete what you hold and fetch nothing. */
    public Optional<AssessmentExpired> expired() {
        return data instanceof AssessmentExpired ? Optional.of((AssessmentExpired) data) : Optional.empty();
    }

    public Optional<EndpointVerification> verification() {
        return data instanceof EndpointVerification ? Optional.of((EndpointVerification) data) : Optional.empty();
    }

    /**
     * True on an event sent by the send-test-event call: data is a fixed example assessment, not
     * one of yours. Acknowledge it and apply nothing.
     */
    public boolean isTest() {
        return test;
    }

    public boolean isExpired() {
        return data instanceof AssessmentExpired;
    }
}
