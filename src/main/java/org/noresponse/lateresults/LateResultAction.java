package org.noresponse.lateresults;

/** What to do to the user. {@link #BAN} also ends every session. */
public enum LateResultAction {
    NONE("none"), REVOKE_SESSIONS("revoke_sessions"), BAN("ban");

    private final String wire;

    LateResultAction(String wire) {
        this.wire = wire;
    }

    /** The wire string: none, revoke_sessions or ban. */
    public String wire() {
        return wire;
    }

    /**
     * Parse a wire string.
     *
     * @throws IllegalArgumentException for any other value
     */
    public static LateResultAction fromWire(String value) {
        for (LateResultAction action : values()) {
            if (action.wire.equals(value)) {
                return action;
            }
        }
        throw new IllegalArgumentException("unknown late result action " + value);
    }
}
