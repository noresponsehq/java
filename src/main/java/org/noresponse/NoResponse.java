package org.noresponse;

import java.lang.String;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import org.noresponse.utils.HTTPClient;
import org.noresponse.utils.Headers;
import org.noresponse.utils.NoResponseHTTPClient;
import org.noresponse.utils.RetryConfig;
import org.noresponse.utils.Utils;

/**
 * NoResponse API: Server-side risk assessment of account creation, login and purchase: a
 * recommendation, polling and signed webhooks.
 *
 * <p>Assess an account creation, login or purchase and get back the account's one current risk, poll a
 * pending assessment, page through an account's history, record what you later learned, report usage
 * of the API keys you issue to your accounts, rotate your API key, manage your webhook endpoints, and
 * register hosted endpoints that answer your WorkOS, Auth0 or Supabase sign-up and sign-in hooks.
 * Every response carries X-Request-ID; every error is an RFC 9457 problem document.
 *
 * <p>Keep the API key on your server.
 */
public class NoResponse {
    private static final Headers _headers = Headers.EMPTY;

    /**
     * SERVERS contains the list of server urls available to the SDK.
     */
    public static final String[] SERVERS = {
        /*
         * Production
         */
        "https://api.noresponse.org",
    };

    /**
     * Assessments of account creation, login and purchase.
     */
    private final Assessments assessments;

    /**
     * Your accounts, each with one current risk, and their assessment history.
     */
    private final Accounts accounts;

    /**
     * Usage of keys issued to your accounts.
     */
    private final KeyUsage keyUsage;

    /**
     * Your own API keys.
     */
    private final Keys keys;

    /**
     * Hosted endpoints that answer your sign-up and sign-in hooks.
     */
    private final Integrations integrations;

    /**
     * Your webhook endpoints: register, list, rotate signing keys, send test events and delete.
     */
    private final WebhookEndpoints webhookEndpoints;

    /**
     * Scoped workspace membership automation.
     */
    private final Members members;

    /**
     * Durable audit history, retention and delivery.
     */
    private final Audit audit;

    /**
     * Recorded usage for your environment.
     */
    private final Usage usage;

    /**
     * Workspace-bound invitation lifecycle.
     */
    private final Invites invites;

    /**
     * Environment policy, history and previews.
     */
    private final Settings settings;

    /**
     * Retained request diagnostics and operational summaries.
     */
    private final Requests requests;

    /**
     * List entries, previews and durable changes.
     */
    private final Lists lists;

    /**
     * Collaborative review cases, comments and linked subjects.
     */
    private final Reviews reviews;

    /**
     * Durable deletion receipts.
     */
    private final Erasures erasures;

    /**
     * Bounded customer export jobs and encrypted artifacts.
     */
    private final Exports exports;

    /**
     * Frozen policy evaluation evidence and reviewed publication.
     */
    private final Evaluations evaluations;

    /**
     * Retained outcomes, enforcement reports and review populations.
     */
    private final Operations operations;

    /**
     * Assessments of account creation, login and purchase.
     */
    public Assessments assessments() {
        return assessments;
    }

    /**
     * Your accounts, each with one current risk, and their assessment history.
     */
    public Accounts accounts() {
        return accounts;
    }

    /**
     * Usage of keys issued to your accounts.
     */
    public KeyUsage keyUsage() {
        return keyUsage;
    }

    /**
     * Your own API keys.
     */
    public Keys keys() {
        return keys;
    }

    /**
     * Hosted endpoints that answer your sign-up and sign-in hooks.
     */
    public Integrations integrations() {
        return integrations;
    }

    /**
     * Your webhook endpoints: register, list, rotate signing keys, send test events and delete.
     */
    public WebhookEndpoints webhookEndpoints() {
        return webhookEndpoints;
    }

    /**
     * Scoped workspace membership automation.
     */
    public Members members() {
        return members;
    }

    /**
     * Durable audit history, retention and delivery.
     */
    public Audit audit() {
        return audit;
    }

    /**
     * Recorded usage for your environment.
     */
    public Usage usage() {
        return usage;
    }

    /**
     * Workspace-bound invitation lifecycle.
     */
    public Invites invites() {
        return invites;
    }

    /**
     * Environment policy, history and previews.
     */
    public Settings settings() {
        return settings;
    }

    /**
     * Retained request diagnostics and operational summaries.
     */
    public Requests requests() {
        return requests;
    }

    /**
     * List entries, previews and durable changes.
     */
    public Lists lists() {
        return lists;
    }

    /**
     * Collaborative review cases, comments and linked subjects.
     */
    public Reviews reviews() {
        return reviews;
    }

    /**
     * Durable deletion receipts.
     */
    public Erasures erasures() {
        return erasures;
    }

    /**
     * Bounded customer export jobs and encrypted artifacts.
     */
    public Exports exports() {
        return exports;
    }

    /**
     * Frozen policy evaluation evidence and reviewed publication.
     */
    public Evaluations evaluations() {
        return evaluations;
    }

    /**
     * Retained outcomes, enforcement reports and review populations.
     */
    public Operations operations() {
        return operations;
    }

    private final AsyncNoResponse asyncSDK;

    /**
     * The Builder class allows the configuration of a new instance of the SDK.
     */
    public static class Builder {

        private final SDKConfiguration sdkConfiguration = new SDKConfiguration();
        private String serverUrl;
        private String server;
        private boolean securityConfigured;

        private Builder() {}

        /**
         * Allows the default HTTP client to be overridden with a custom implementation.
         *
         * @param client The HTTP client to use for all requests.
         * @return The builder instance.
         */
        public Builder client(HTTPClient client) {
            this.sdkConfiguration.setClient(client);
            return this;
        }
        /**
         * Configures the SDK security to use the provided secret.
         *
         * @param apiKey The secret to use for all requests.
         * @return The builder instance.
         */
        public Builder apiKey(String apiKey) {
            this.securityConfigured = true;
            this.sdkConfiguration.setSecuritySource(
                    SecuritySource.of(
                            org.noresponse.models.shared.Security.builder()
                                    .apiKey(apiKey)
                                    .build()));
            return this;
        }

        /**
         * Configures the SDK to use a custom security source.
         *
         * @param securitySource The security source to use for all requests.
         * @return The builder instance.
         */
        public Builder securitySource(SecuritySource securitySource) {
            Utils.checkNotNull(securitySource, "securitySource");
            this.securityConfigured = true;
            this.sdkConfiguration.setSecuritySource(securitySource);
            return this;
        }

        /**
         * Overrides the default server URL.
         *
         * @param serverUrl The server URL to use for all requests.
         * @return The builder instance.
         */
        public Builder serverURL(String serverUrl) {
            this.serverUrl = serverUrl;
            return this;
        }

        /**
         * Overrides the default server URL with a templated URL populated with the provided parameters.
         *
         * @param serverUrl The server URL to use for all requests.
         * @param params The parameters to use when templating the URL.
         * @return The builder instance.
         */
        public Builder serverURL(String serverUrl, Map<String, String> params) {
            this.serverUrl = Utils.templateUrl(serverUrl, params);
            return this;
        }

        /**
         * Overrides the default server by index.
         *
         * @param serverIdx The server to use for all requests.
         * @return The builder instance.
         */
        public Builder serverIndex(int serverIdx) {
            this.sdkConfiguration.setServerIdx(serverIdx);
            this.serverUrl = SERVERS[serverIdx];
            return this;
        }

        /**
         * Overrides the default configuration for retries
         *
         * @param retryConfig The retry configuration to use for all requests.
         * @return The builder instance.
         */
        public Builder retryConfig(RetryConfig retryConfig) {
            this.sdkConfiguration.setRetryConfig(Optional.of(retryConfig));
            return this;
        }

        /**
         * Enables debug logging for HTTP requests and responses, including JSON body content.
         * <p>
         * Convenience method that calls {@link HTTPClient#enableDebugLogging(boolean)}.
         * {@link NoResponseHTTPClient} honors this setting. If you are using a custom HTTP client,
         * it is up to the custom client to honor this setting.
         * </p>
         *
         * @param enabled Whether to enable debug logging.
         * @return The builder instance.
         */
        public Builder enableHTTPDebugLogging(boolean enabled) {
            this.sdkConfiguration.client().enableDebugLogging(enabled);
            return this;
        }

        // Visible for testing, may be accessed via reflection in tests
        Builder _hooks(org.noresponse.utils.Hooks hooks) {
            sdkConfiguration.setHooks(hooks);
            return this;
        }

        // Visible for testing, may be accessed via reflection in tests
        Builder _hooks(Consumer<? super org.noresponse.utils.Hooks> consumer) {
            consumer.accept(sdkConfiguration.hooks());
            return this;
        }

        /**
         * Builds a new instance of the SDK.
         *
         * @return The SDK instance.
         */
        public NoResponse build() {
            if (serverUrl == null || serverUrl.isBlank()) {
                serverUrl = SERVERS[0];
                sdkConfiguration.setServerIdx(0);
            }
            sdkConfiguration.setServerUrl(serverUrl);
            if (!securityConfigured) {
                String envSecurityValue = System.getenv("NORESPONSE_API_KEY");
                if (envSecurityValue != null && !envSecurityValue.isEmpty()) {
                    sdkConfiguration.setSecuritySource(
                            SecuritySource.of(
                                    org.noresponse.models.shared.Security.builder()
                                            .apiKey(envSecurityValue)
                                            .build()));
                }
            }
            return new NoResponse(sdkConfiguration);
        }
    }

    /**
     * Get a new instance of the SDK builder to configure a new instance of the SDK.
     *
     * @return The SDK builder instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    private NoResponse(SDKConfiguration sdkConfiguration) {
        sdkConfiguration.initialize();
        this.assessments = new Assessments(sdkConfiguration);
        this.accounts = new Accounts(sdkConfiguration);
        this.keyUsage = new KeyUsage(sdkConfiguration);
        this.keys = new Keys(sdkConfiguration);
        this.integrations = new Integrations(sdkConfiguration);
        this.webhookEndpoints = new WebhookEndpoints(sdkConfiguration);
        this.members = new Members(sdkConfiguration);
        this.audit = new Audit(sdkConfiguration);
        this.usage = new Usage(sdkConfiguration);
        this.invites = new Invites(sdkConfiguration);
        this.settings = new Settings(sdkConfiguration);
        this.requests = new Requests(sdkConfiguration);
        this.lists = new Lists(sdkConfiguration);
        this.reviews = new Reviews(sdkConfiguration);
        this.erasures = new Erasures(sdkConfiguration);
        this.exports = new Exports(sdkConfiguration);
        this.evaluations = new Evaluations(sdkConfiguration);
        this.operations = new Operations(sdkConfiguration);
        sdkConfiguration = sdkConfiguration.hooks().sdkInit(sdkConfiguration);
        this.asyncSDK = new AsyncNoResponse(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncNoResponse async() {
        return asyncSDK;
    }
}
