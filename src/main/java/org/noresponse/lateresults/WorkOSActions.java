package org.noresponse.lateresults;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * WorkOS User Management. {@code revokeSessions} pages through
 * GET /user_management/users/{id}/sessions and revokes each active one with
 * POST /user_management/sessions/revoke. WorkOS has no block or ban, so {@code ban} revokes the
 * sessions and then deletes the user (DELETE /user_management/users/{id}). Deletion is permanent.
 * A 404 on any call (user or session already gone) counts as done, so a repeated delivery is safe.
 */
public final class WorkOSActions implements LateResultProvider {
    public static final String DEFAULT_BASE_URL = "https://api.workos.com";

    private final ProviderCaller caller;

    /** @param apiKey the WorkOS API key (sk_...) */
    public WorkOSActions(String apiKey) {
        this(apiKey, null, null);
    }

    /**
     * @param baseUrl null for {@link #DEFAULT_BASE_URL}; set it for tests
     * @param client null for a default {@link HttpClient}
     */
    public WorkOSActions(String apiKey, String baseUrl, HttpClient client) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("WorkOSActions needs apiKey");
        }
        this.caller = new ProviderCaller("workos", baseUrl != null ? baseUrl : DEFAULT_BASE_URL,
                "Bearer " + apiKey, client);
    }

    @Override
    public String name() {
        return "workos";
    }

    @Override
    public void revokeSessions(String userId) {
        String user = ProviderCaller.segment(userId);
        List<String> active = new ArrayList<>();
        int[] count = new int[1];
        String after = null;
        for (int round = 0; round < 1000; round++) {
            Map<String, String> query = new LinkedHashMap<>();
            query.put("limit", "100");
            if (after != null) {
                query.put("after", after);
            }
            JsonNode body = caller.call("GET", "/user_management/users/" + user + "/sessions", query, null, true);
            active.addAll(ProviderCaller.activeIds(body, count));
            JsonNode next = body == null ? null : body.path("list_metadata").path("after");
            if (next == null || !next.isTextual() || next.asText().isEmpty()) {
                break;
            }
            after = next.asText();
        }
        for (String id : active) {
            Map<String, String> payload = new LinkedHashMap<>();
            payload.put("session_id", id);
            caller.call("POST", "/user_management/sessions/revoke", null, payload, true);
        }
    }

    /** Revokes the sessions, then deletes the user permanently. A 404 on the delete counts as done. */
    @Override
    public void ban(String userId) {
        revokeSessions(userId);
        caller.call("DELETE", "/user_management/users/" + ProviderCaller.segment(userId), null, null, true);
    }
}
