package org.noresponse.lateresults;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

/**
 * Clerk Backend API. {@code revokeSessions} lists the user's active sessions
 * (GET /sessions?user_id&amp;status=active) and revokes each (POST /sessions/{id}/revoke).
 * {@code ban} is POST /users/{id}/ban, which revokes every session and blocks sign-in. A 404 on
 * any call (user or session already gone) counts as done, so a repeated delivery is safe.
 */
public final class ClerkActions implements LateResultProvider {
    public static final String DEFAULT_BASE_URL = "https://api.clerk.com/v1";
    private static final int PAGE = 100;

    private final ProviderCaller caller;

    /** @param secretKey the Clerk secret key (sk_...) */
    public ClerkActions(String secretKey) {
        this(secretKey, null, null);
    }

    /**
     * @param baseUrl null for {@link #DEFAULT_BASE_URL}; set it for tests
     * @param client null for a default {@link HttpClient}
     */
    public ClerkActions(String secretKey, String baseUrl, HttpClient client) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("ClerkActions needs secretKey");
        }
        this.caller = new ProviderCaller("clerk", baseUrl != null ? baseUrl : DEFAULT_BASE_URL,
                "Bearer " + secretKey, client);
    }

    @Override
    public String name() {
        return "clerk";
    }

    @Override
    public void revokeSessions(String userId) {
        ProviderCaller.segment(userId);
        List<String> active = new ArrayList<>();
        int[] count = new int[1];
        for (int offset = 0; offset < PAGE * 1000; offset += PAGE) {
            active.addAll(ProviderCaller.activeIds(caller.call("GET", "/sessions", ProviderCaller.query(
                    "user_id", userId, "status", "active", "limit", String.valueOf(PAGE),
                    "offset", String.valueOf(offset)), null, true), count));
            if (count[0] < PAGE) {
                break;
            }
        }
        for (String id : active) {
            caller.call("POST", "/sessions/" + ProviderCaller.encode(id) + "/revoke", null, null, true);
        }
    }

    @Override
    public void ban(String userId) {
        caller.call("POST", "/users/" + ProviderCaller.segment(userId) + "/ban", null, null, true);
    }
}
