package org.noresponse;

import org.noresponse.utils.Headers;

/**
 * NoResponse API: Server-side signup and gateway risk assessment: a recommendation, polling and signed
 * webhooks.
 *
 * <p>Assess a signup or gateway event, poll a pending assessment, page through an account's history,
 * record what you later learned, rotate your API key, and register hosted endpoints that answer your
 * WorkOS or Auth0 sign-up and sign-in hooks. Every response carries X-Request-ID; every error is an
 * RFC 9457 problem document. Keep the API key on your server.
 */
public class AsyncNoResponse {
    private static final Headers _headers = Headers.EMPTY;
    /**
     * Signup and gateway assessments.
     */
    private final AsyncAssessments assessments;
    /**
     * Your own API keys.
     */
    private final AsyncKeys keys;
    /**
     * Hosted endpoints that answer your sign-up and sign-in hooks.
     */
    private final AsyncIntegrations integrations;
    /**
     * Signup and gateway assessments.
     */
    public AsyncAssessments assessments() {
        return assessments;
    }
    /**
     * Your own API keys.
     */
    public AsyncKeys keys() {
        return keys;
    }
    /**
     * Hosted endpoints that answer your sign-up and sign-in hooks.
     */
    public AsyncIntegrations integrations() {
        return integrations;
    }

    private SDKConfiguration sdkConfiguration;
    private final NoResponse syncSDK;

    AsyncNoResponse(NoResponse syncSDK, SDKConfiguration sdkConfiguration) {
        this.syncSDK = syncSDK;
        this.sdkConfiguration = sdkConfiguration;
        this.assessments = new AsyncAssessments(syncSDK.assessments(), sdkConfiguration);
        this.keys = new AsyncKeys(syncSDK.keys(), sdkConfiguration);
        this.integrations = new AsyncIntegrations(syncSDK.integrations(), sdkConfiguration);
    }

    /**
     * Switches to the sync SDK.
     *
     * @return The sync SDK
     */
    public NoResponse sync() {
        return syncSDK;
    }
}
