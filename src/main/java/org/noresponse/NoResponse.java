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
 * NoResponse API: Server-side signup and gateway risk assessment: a recommendation, polling and signed
 * webhooks.
 *
 * <p>Assess a signup or gateway event, poll a pending assessment, page through an account's history,
 * record what you later learned, and rotate your API key. Every response carries X-Request-ID; every
 * error is an RFC 9457 problem document.
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
     * Signup and gateway assessments.
     */
    private final Assessments assessments;

    /**
     * Your own API keys.
     */
    private final Keys keys;

    /**
     * Signup and gateway assessments.
     */
    public Assessments assessments() {
        return assessments;
    }

    /**
     * Your own API keys.
     */
    public Keys keys() {
        return keys;
    }

    private final AsyncNoResponse asyncSDK;

    /**
     * The Builder class allows the configuration of a new instance of the SDK.
     */
    public static class Builder {

        private final SDKConfiguration sdkConfiguration = new SDKConfiguration();
        private String serverUrl;
        private String server;

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
        this.keys = new Keys(sdkConfiguration);
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
