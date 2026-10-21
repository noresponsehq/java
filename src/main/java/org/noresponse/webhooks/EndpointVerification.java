package org.noresponse.webhooks;

/** The challenge NoResponse sends when an endpoint is registered. */
public final class EndpointVerification {
    private final String endpointId;
    private final String challenge;

    public EndpointVerification(String endpointId, String challenge) {
        this.endpointId = endpointId;
        this.challenge = challenge;
    }

    public String endpointId() {
        return endpointId;
    }

    public String challenge() {
        return challenge;
    }
}
