package org.noresponse.lateresults;

import java.util.Optional;
import org.noresponse.webhooks.WebhookEvent;

/** The outcome of {@link LateResults#handleLateResult}. */
public final class LateResult {
    private final WebhookEvent event;
    private final LateResultAction action;
    private final String userId;

    LateResult(WebhookEvent event, LateResultAction action, String userId) {
        this.event = event;
        this.action = action;
        this.userId = userId;
    }

    /** The verified event. Answer an endpoint.verification with {@code Webhook.verificationResponse(event)}. */
    public WebhookEvent event() {
        return event;
    }

    /** The action taken. NONE when the policy, the event or the user id called for nothing. */
    public LateResultAction action() {
        return action;
    }

    /** The provider user id acted on, when an action was taken. */
    public Optional<String> userId() {
        return Optional.ofNullable(userId);
    }
}
