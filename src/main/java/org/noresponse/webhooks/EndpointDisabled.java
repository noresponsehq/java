package org.noresponse.webhooks;

/**
 * Another endpoint of this environment was disabled after failing continuously
 * for 72 hours. It receives nothing more until it is verified again.
 * {@link #failingSince()} is when its deliveries started failing.
 */
public final class EndpointDisabled {
    private final String endpointId;
    private final String failingSince;
    private final String disabledAt;

    public EndpointDisabled(String endpointId, String failingSince, String disabledAt) {
        this.endpointId = endpointId;
        this.failingSince = failingSince;
        this.disabledAt = disabledAt;
    }

    public String endpointId() {
        return endpointId;
    }

    public String failingSince() {
        return failingSince;
    }

    public String disabledAt() {
        return disabledAt;
    }
}
