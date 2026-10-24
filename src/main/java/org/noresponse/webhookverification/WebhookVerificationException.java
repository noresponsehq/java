package org.noresponse.webhookverification;

/**
 * Thrown by {@link Webhook#constructEvent} for a delivery that must not be processed.
 * Answer the delivery with a 400 and do not process it.
 */
public final class WebhookVerificationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Why the delivery was refused. */
    public enum Code {
        MISSING_HEADER("missing_header"),
        DUPLICATE_HEADER("duplicate_header"),
        INVALID_TIMESTAMP("invalid_timestamp"),
        UNKNOWN_KEY("unknown_key"),
        INVALID_SIGNATURE("invalid_signature"),
        INVALID_PAYLOAD("invalid_payload");

        private final String value;

        Code(String value) {
            this.value = value;
        }

        /** The code's wire name, such as {@code missing_header}. */

        public String value() {
            return value;
        }
    }

    private final Code code;

    WebhookVerificationException(Code code, String message) {
        super("webhook verification failed: " + message + " (" + code.value() + ")");
        this.code = code;
    }

    public Code code() {
        return code;
    }
}
