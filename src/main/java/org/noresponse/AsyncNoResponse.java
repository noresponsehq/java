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
    /**
     * Usage of keys issued to your accounts.
     */
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
    /**
     * Scoped workspace membership automation.
     */
    private final AsyncMembers members;
    /**
     * Durable audit history, retention and delivery.
     */
    private final AsyncAudit audit;
    /**
     * Recorded usage for your environment.
     */
    private final AsyncUsage usage;
    /**
     * Workspace-bound invitation lifecycle.
     */
    private final AsyncInvites invites;
    /**
     * Environment policy, history and previews.
     */
    private final AsyncSettings settings;
    /**
     * Retained request diagnostics and operational summaries.
     */
    private final AsyncRequests requests;
    /**
     * List entries, previews and durable changes.
     */
    private final AsyncLists lists;
    /**
     * Collaborative review cases, comments and linked subjects.
     */
    private final AsyncReviews reviews;
    /**
     * Durable deletion receipts.
     */
    private final AsyncErasures erasures;
    /**
     * Bounded customer export jobs and encrypted artifacts.
     */
    private final AsyncExports exports;
    /**
     * Frozen policy evaluation evidence and reviewed publication.
     */
    private final AsyncEvaluations evaluations;
    /**
     * Retained outcomes, enforcement reports and review populations.
     */
    private final AsyncOperations operations;
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
    /**
     * Usage of keys issued to your accounts.
     */
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
    /**
     * Scoped workspace membership automation.
     */
    public AsyncMembers members() {
        return members;
    }
    /**
     * Durable audit history, retention and delivery.
     */
    public AsyncAudit audit() {
        return audit;
    }
    /**
     * Recorded usage for your environment.
     */
    public AsyncUsage usage() {
        return usage;
    }
    /**
     * Workspace-bound invitation lifecycle.
     */
    public AsyncInvites invites() {
        return invites;
    }
    /**
     * Environment policy, history and previews.
     */
    public AsyncSettings settings() {
        return settings;
    }
    /**
     * Retained request diagnostics and operational summaries.
     */
    public AsyncRequests requests() {
        return requests;
    }
    /**
     * List entries, previews and durable changes.
     */
    public AsyncLists lists() {
        return lists;
    }
    /**
     * Collaborative review cases, comments and linked subjects.
     */
    public AsyncReviews reviews() {
        return reviews;
    }
    /**
     * Durable deletion receipts.
     */
    public AsyncErasures erasures() {
        return erasures;
    }
    /**
     * Bounded customer export jobs and encrypted artifacts.
     */
    public AsyncExports exports() {
        return exports;
    }
    /**
     * Frozen policy evaluation evidence and reviewed publication.
     */
    public AsyncEvaluations evaluations() {
        return evaluations;
    }
    /**
     * Retained outcomes, enforcement reports and review populations.
     */
    public AsyncOperations operations() {
        return operations;
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
        this.members = new AsyncMembers(syncSDK.members(), sdkConfiguration);
        this.audit = new AsyncAudit(syncSDK.audit(), sdkConfiguration);
        this.usage = new AsyncUsage(syncSDK.usage(), sdkConfiguration);
        this.invites = new AsyncInvites(syncSDK.invites(), sdkConfiguration);
        this.settings = new AsyncSettings(syncSDK.settings(), sdkConfiguration);
        this.requests = new AsyncRequests(syncSDK.requests(), sdkConfiguration);
        this.lists = new AsyncLists(syncSDK.lists(), sdkConfiguration);
        this.reviews = new AsyncReviews(syncSDK.reviews(), sdkConfiguration);
        this.erasures = new AsyncErasures(syncSDK.erasures(), sdkConfiguration);
        this.exports = new AsyncExports(syncSDK.exports(), sdkConfiguration);
        this.evaluations = new AsyncEvaluations(syncSDK.evaluations(), sdkConfiguration);
        this.operations = new AsyncOperations(syncSDK.operations(), sdkConfiguration);
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
