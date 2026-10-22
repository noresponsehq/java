package org.noresponse.lateresults;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** The action for each recommendation (allow, step_up, review). A recommendation you leave out does nothing. */
public final class LateResultPolicy {
    /** Revoke sessions on review; do nothing on allow or step_up. Banning is opt-in. */
    public static final LateResultPolicy DEFAULT = new LateResultPolicy(Map.of(
            "allow", LateResultAction.NONE,
            "step_up", LateResultAction.NONE,
            "review", LateResultAction.REVOKE_SESSIONS));

    private final Map<String, LateResultAction> actions;

    private LateResultPolicy(Map<String, LateResultAction> actions) {
        this.actions = Collections.unmodifiableMap(new HashMap<>(actions));
    }

    /** A policy from typed actions, keyed by recommendation. */
    public static LateResultPolicy of(Map<String, LateResultAction> actions) {
        return new LateResultPolicy(actions);
    }

    /**
     * A policy from wire strings, keyed by recommendation.
     *
     * @throws IllegalArgumentException for an action other than none, revoke_sessions or ban
     */
    public static LateResultPolicy fromWire(Map<String, String> actions) {
        Map<String, LateResultAction> parsed = new HashMap<>();
        actions.forEach((recommendation, action) -> parsed.put(recommendation, LateResultAction.fromWire(action)));
        return new LateResultPolicy(parsed);
    }

    /** The action for a recommendation; NONE when the policy leaves it out. */
    public LateResultAction actionFor(String recommendation) {
        LateResultAction action = actions.get(recommendation);
        return action == null ? LateResultAction.NONE : action;
    }

    public Map<String, LateResultAction> asMap() {
        return actions;
    }
}
