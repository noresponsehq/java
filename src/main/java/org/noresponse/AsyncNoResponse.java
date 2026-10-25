package org.noresponse;

import org.noresponse.utils.Headers;

/**
 * NoResponse API: Server-side risk assessment of account creation, login and purchase: a
 * recommendation, polling and signed webhooks.
 *
 * <p>Assess an account creation, login or purchase and get back the account's one current risk, poll a
 * pending assessment, page through an account's history, record what you later learned, report usage
 * of the API keys you issue to your accounts, rotate your API key, manage your webhook endpoints, and
 * register hosted endpoints that answer your WorkOS, Auth0 or Supabase sign-up and sign-in hooks.
 * Every response carries X-Request-ID; every error is an RFC 9457 problem document. Keep the API key
 * on your server.
 */
public class AsyncNoResponse {
    private static final Headers _headers = Headers.EMPTY;
    /**
     * Assessments of account creation, login and purchase.
     */
    private final AsyncAssessments assessments;
    /**
     * Your accounts, each with one current risk, and their assessment history.
     */
    private final AsyncAccounts accounts;

    private final AsyncKeyUsage keyUsage;
    /**
     * Your own API keys.
     */
    private final AsyncKeys keys;
    /**
     * Hosted endpoints that answer your sign-up and sign-in hooks.
     */
    private final AsyncIntegrations integrations;
    /**
     * Your webhook endpoints: register, list, rotate signing keys, send test events and delete.
     */
    private final AsyncWebhookEndpoints webhookEndpoints;

    private final AsyncSettings settings;
    /**
     * Assessments of account creation, login and purchase.
     */
    public AsyncAssessments assessments() {
        return assessments;
    }
    /**
     * Your accounts, each with one current risk, and their assessment history.
     */
    public AsyncAccounts accounts() {
        return accounts;
    }

    public AsyncKeyUsage keyUsage() {
        return keyUsage;
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
    /**
     * Your webhook endpoints: register, list, rotate signing keys, send test events and delete.
     */
    public AsyncWebhookEndpoints webhookEndpoints() {
        return webhookEndpoints;
    }

    public AsyncSettings settings() {
        return settings;
    }

    private SDKConfiguration sdkConfiguration;
    private final NoResponse syncSDK;

    AsyncNoResponse(NoResponse syncSDK, SDKConfiguration sdkConfiguration) {
        this.syncSDK = syncSDK;
        this.sdkConfiguration = sdkConfiguration;
        this.assessments = new AsyncAssessments(syncSDK.assessments(), sdkConfiguration);
        this.accounts = new AsyncAccounts(syncSDK.accounts(), sdkConfiguration);
        this.keyUsage = new AsyncKeyUsage(syncSDK.keyUsage(), sdkConfiguration);
        this.keys = new AsyncKeys(syncSDK.keys(), sdkConfiguration);
        this.integrations = new AsyncIntegrations(syncSDK.integrations(), sdkConfiguration);
        this.webhookEndpoints = new AsyncWebhookEndpoints(syncSDK.webhookEndpoints(), sdkConfiguration);
        this.settings = new AsyncSettings(syncSDK.settings(), sdkConfiguration);
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
