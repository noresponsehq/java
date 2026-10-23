package org.noresponse.lateresults;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Auth0 Management API. {@code revokeSessions} deletes every session (DELETE /users/{id}/sessions)
 * and refresh token (DELETE /users/{id}/refresh-tokens). {@code ban} first sets blocked: true
 * (PATCH /users/{id}), then does the same. The token needs update:users, delete:sessions,
 * delete:refresh_tokens, read:device_credentials and delete:device_credentials. A 404 on any call
 * (user already gone) counts as done, so a repeated delivery is safe.
 *
 * <p>Auth0 offers the sessions and refresh-token endpoints on Enterprise plans only; on other plans
 * they answer 403 feature_not_enabled. Then refresh tokens are deleted one by one through
 * /device-credentials (types refresh_token and rotating_refresh_token), which every plan has. A
 * login session cannot be ended, so {@code revokeSessions} blocks the user before deleting the
 * refresh tokens, and {@link #act} reports BAN. Unblock the user in the dashboard to undo it. Any
 * other refusal, such as 403 insufficient_scope, throws.
 */
public final class Auth0Actions implements LateResultProvider {
    private static final int PAGE = 100;

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
        revoke(userId);
    }

    @Override
    public void ban(String userId) {
        String user = ProviderCaller.segment(userId);
        block(user);
        // The block already ends a session the plan cannot delete.
        deleteSessions(user);
        deleteRefreshTokens(userId, user);
    }

    /** Bans or revokes and returns the action that ran: BAN when revoking had to block the user. */
    @Override
    public LateResultAction act(LateResultAction action, String userId) {
        if (action == LateResultAction.BAN) {
            ban(userId);
            return LateResultAction.BAN;
        }
        return revoke(userId);
    }

    private LateResultAction revoke(String userId) {
        String user = ProviderCaller.segment(userId);
        boolean ended = deleteSessions(user);
        // Without the sessions API a login session survives; blocking is the only way to end it.
        if (!ended) {
            block(user);
        }
        deleteRefreshTokens(userId, user);
        return ended ? LateResultAction.REVOKE_SESSIONS : LateResultAction.BAN;
    }

    /** Auth0 answers 403 feature_not_enabled when the tenant's plan does not include an endpoint. */
    private static boolean notOnPlan(ProviderActionException e) {
        return e.status() == 403 && "feature_not_enabled".equals(e.code());
    }

    private void block(String user) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("blocked", true);
        caller.call("PATCH", "/users/" + user, null, body, true);
    }

    /** End every session; false when the plan has no sessions API. */
    private boolean deleteSessions(String user) {
        try {
            caller.call("DELETE", "/users/" + user + "/sessions", null, null, true);
            return true;
        } catch (ProviderActionException e) {
            if (notOnPlan(e)) {
                return false;
            }
            throw e;
        }
    }

    private void deleteRefreshTokens(String userId, String user) {
        try {
            caller.call("DELETE", "/users/" + user + "/refresh-tokens", null, null, true);
            return;
        } catch (ProviderActionException e) {
            if (!notOnPlan(e)) {
                throw e;
            }
        }
        // Collect every id before deleting any, so the pages do not shift underneath.
        List<String> ids = new ArrayList<>();
        for (String type : new String[] {"refresh_token", "rotating_refresh_token"}) {
            for (int page = 0; page < 10; page++) {
                JsonNode body = caller.call("GET", "/device-credentials", ProviderCaller.query("user_id", userId, "type",
                        type, "page", Integer.toString(page), "per_page", Integer.toString(PAGE)), null, true);
                int listed = body != null && body.isArray() ? body.size() : 0;
                for (int i = 0; i < listed; i++) {
                    JsonNode id = body.get(i).path("id");
                    if (id.isTextual() && !id.asText().isEmpty()) {
                        ids.add(id.asText());
                    }
                }
                if (listed < PAGE) {
                    break;
                }
            }
        }
        for (String id : ids) {
            caller.call("DELETE", "/device-credentials/" + ProviderCaller.encode(id), null, null, true);
        }
    }
}
