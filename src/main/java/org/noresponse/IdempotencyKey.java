package org.noresponse;

import java.util.UUID;

/** Idempotency keys for create and recordOutcome. Hand-written beside the generated client. */
public final class IdempotencyKey {
    private IdempotencyKey() {
    }

    /**
     * A random version 4 UUID, 36 characters from the allowed set. Generate one per logical request
     * and store it beside the signup, so that a retry after a crash sends the same key.
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
