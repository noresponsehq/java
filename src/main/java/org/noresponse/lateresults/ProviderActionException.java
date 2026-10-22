package org.noresponse.lateresults;

/**
 * Thrown when the provider's admin API refuses a call. Answer the webhook with a
 * 5xx so NoResponse retries it. The message holds no response body or credential.
 */
public final class ProviderActionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String provider;
    private final int status;

    public ProviderActionException(String provider, int status, String message) {
        super(message);
        this.provider = provider;
        this.status = status;
    }

    public String provider() {
        return provider;
    }

    public int status() {
        return status;
    }
}
