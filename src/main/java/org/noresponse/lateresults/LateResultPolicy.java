package org.noresponse.lateresults;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The action for each account decision (allow, step_up, review, block). A decision you leave out does
 * nothing.
 */
public final class LateResultPolicy {
    /** Revoke sessions on review and block; do nothing on allow or step_up. Banning is opt-in. */
    public static final LateResultPolicy DEFAULT = new LateResultPolicy(Map.of(
            "allow", LateResultAction.NONE,
            "step_up", LateResultAction.NONE,
            "review", LateResultAction.REVOKE_SESSIONS,
            "block", LateResultAction.REVOKE_SESSIONS));

    private final Map<String, LateResultAction> actions;

    private LateResultPolicy(Map<String, LateResultAction> actions) {
        this.actions = Collections.unmodifiableMap(new HashMap<>(actions));
    }

    /** A policy from typed actions, keyed by decision. */
    public static LateResultPolicy of(Map<String, LateResultAction> actions) {
        return new LateResultPolicy(actions);
    }

    /**
     * A policy from wire strings, keyed by decision.
     *
     * @throws IllegalArgumentException for an action other than none, revoke_sessions or ban
     */
    public static LateResultPolicy fromWire(Map<String, String> actions) {
        Map<String, LateResultAction> parsed = new HashMap<>();
        actions.forEach((decision, action) -> parsed.put(decision, LateResultAction.fromWire(action)));
        return new LateResultPolicy(parsed);
    }

    /** The action for a decision; NONE when the policy leaves it out. */
    public LateResultAction actionFor(String decision) {
        LateResultAction action = actions.get(decision);
        return action == null ? LateResultAction.NONE : action;
    }

    public Map<String, LateResultAction> asMap() {
        return actions;
    }
}
