package org.noresponse.lateresults;

/** The two admin actions, for one auth provider. Use {@link ClerkActions}, {@link WorkOSActions} or {@link Auth0Actions}. */
public interface LateResultProvider {
    String name();

    /** End every active session of the user. */
    void revokeSessions(String userId);

    /** Stop the user signing in again and end their sessions. */
    void ban(String userId);
}
