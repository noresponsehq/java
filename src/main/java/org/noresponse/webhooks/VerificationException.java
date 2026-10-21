package org.noresponse.webhooks;

/**
 * Thrown by {@link Webhook#constructEvent}. Answer 400 and do not process the delivery.
 */
public final class VerificationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /** Why the delivery was refused. */
    public enum Code {
        MISSING_HEADER, DUPLICATE_HEADER, INVALID_TIMESTAMP, UNKNOWN_KEY, INVALID_SIGNATURE, INVALID_PAYLOAD
    }

    private final Code code;

    VerificationException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public Code code() {
        return code;
    }
}
