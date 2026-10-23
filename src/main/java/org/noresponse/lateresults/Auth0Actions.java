package org.noresponse.lateresults;

import java.net.http.HttpClient;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Auth0 Management API. {@code revokeSessions} deletes every session (DELETE /users/{id}/sessions)
 * and refresh token (DELETE /users/{id}/refresh-tokens). {@code ban} first sets blocked: true
 * (PATCH /users/{id}), then does the same. The token needs update:users, delete:sessions and
 * delete:refresh_tokens. A 404 on any call (user already gone) counts as done, so a repeated
 * delivery is safe.
 */
public final class Auth0Actions implements LateResultProvider {
    private final ProviderCaller caller;

    /**
     * @param domain your Auth0 tenant domain, such as example.eu.auth0.com; the Management API is
     *     https://{domain}/api/v2
     * @param token a Management API access token
     */
    public Auth0Actions(String domain, String token) {
        this(domain, null, token, null);
    }

    /**
     * @param domain the tenant domain; may be null when baseUrl is set
     * @param baseUrl the Management API base URL in place of domain; set it for tests
     * @param client null for a default {@link HttpClient}
     */
    public Auth0Actions(String domain, String baseUrl, String token, HttpClient client) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Auth0Actions needs token");
        }
        String base = baseUrl != null ? baseUrl : domain != null && !domain.isEmpty() ? "https://" + domain + "/api/v2" : null;
        if (base == null) {
            throw new IllegalArgumentException("Auth0Actions needs domain or baseUrl");
        }
        this.caller = new ProviderCaller("auth0", base, "Bearer " + token, client);
    }

    @Override
    public String name() {
        return "auth0";
    }

    @Override
    public void revokeSessions(String userId) {
        String user = ProviderCaller.segment(userId);
        caller.call("DELETE", "/users/" + user + "/sessions", null, null, true);
        caller.call("DELETE", "/users/" + user + "/refresh-tokens", null, null, true);
    }

    @Override
    public void ban(String userId) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("blocked", true);
        caller.call("PATCH", "/users/" + ProviderCaller.segment(userId), null, body, true);
        revokeSessions(userId);
    }
}
