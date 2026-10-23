package org.noresponse.lateresults;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * A provider for an app with its own login, or for trying the handler before you wire up a real
 * one. {@code revokeSessions} and {@code ban} each log one line saying what they would do and call
 * nothing: no request leaves your process. Replace it with your own {@link LateResultProvider} once
 * you know what revoking and banning mean in your app.
 */
public final class LogOnlyActions implements LateResultProvider {
    private static final System.Logger LOGGER = System.getLogger("org.noresponse.lateresults");

    private final Consumer<String> log;

    /** Logs each line at INFO on the {@code org.noresponse.lateresults} {@link System.Logger}. */
    public LogOnlyActions() {
        this(message -> LOGGER.log(System.Logger.Level.INFO, message));
    }

    /** @param log receives one line per action the handler would take */
    public LogOnlyActions(Consumer<String> log) {
        this.log = Objects.requireNonNull(log, "log");
    }

    @Override
    public String name() {
        return "log_only";
    }

    @Override
    public void revokeSessions(String userId) {
        ProviderCaller.segment(userId);
        log.accept("NoResponse late result (log only): would revoke every session of user " + userId);
    }

    @Override
    public void ban(String userId) {
        ProviderCaller.segment(userId);
        log.accept("NoResponse late result (log only): would ban user " + userId + " and end their sessions");
    }
}
