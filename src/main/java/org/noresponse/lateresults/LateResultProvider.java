package org.noresponse.lateresults;

/** The two admin actions, for one auth provider. Use {@link ClerkActions}, {@link WorkOSActions} or {@link Auth0Actions}. */
public interface LateResultProvider {
    String name();

    /** End every active session of the user. */
    void revokeSessions(String userId);

    /** Stop the user signing in again and end their sessions. */
    void ban(String userId);

    /**
     * Take {@code action} ({@link LateResultAction#REVOKE_SESSIONS} or {@link LateResultAction#BAN}) and
     * return the action that ran. The default calls {@link #ban} or {@link #revokeSessions} and returns
     * {@code action}. A provider that has to do more than it was asked overrides it: {@link Auth0Actions}
     * returns BAN when the tenant's plan has no sessions API and it blocked the user to end their sessions.
     */
    default LateResultAction act(LateResultAction action, String userId) {
        if (action == LateResultAction.BAN) {
            ban(userId);
        } else {
            revokeSessions(userId);
        }
        return action;
    }
}
