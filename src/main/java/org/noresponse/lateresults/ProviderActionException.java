package org.noresponse.lateresults;

/**
 * Thrown when the provider's admin API refuses a call. Answer the webhook with a
 * 5xx so NoResponse retries it. The message holds no response body or credential.
 */
public final class ProviderActionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String provider;
    private final int status;
    private final String code;

    public ProviderActionException(String provider, int status, String message) {
        this(provider, status, message, null);
    }

    /** @param code the provider's error code from the response body, or null */
    public ProviderActionException(String provider, int status, String message, String code) {
        super(message);
        this.provider = provider;
        this.status = status;
        this.code = code;
    }

    /** The provider's error code from the response body, such as Auth0's errorCode "insufficient_scope", or null. */
    public String code() {
        return code;
    }

    public String provider() {
        return provider;
    }

    public int status() {
        return status;
    }
}
