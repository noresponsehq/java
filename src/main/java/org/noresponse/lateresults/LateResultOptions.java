package org.noresponse.lateresults;

import java.util.function.BiFunction;
import org.noresponse.models.shared.WebhookEvent;
import org.noresponse.webhookverification.Webhook;

/** Options for {@link LateResults#handleLateResult}. Build with {@link #builder()}. */
public final class LateResultOptions {
    private final LateResultPolicy policy;
    private final BiFunction<String, WebhookEvent, String> userId;
    private final long toleranceSeconds;
    private final Long nowEpochSeconds;

    private LateResultOptions(Builder b) {
        this.policy = b.policy;
        this.userId = b.userId;
        this.toleranceSeconds = b.toleranceSeconds;
        this.nowEpochSeconds = b.nowEpochSeconds;
    }

    public static Builder builder() {
        return new Builder();
    }

    LateResultPolicy policy() {
        return policy;
    }

    BiFunction<String, WebhookEvent, String> userId() {
        return userId;
    }

    long toleranceSeconds() {
        return toleranceSeconds;
    }

    Long nowEpochSeconds() {
        return nowEpochSeconds;
    }

    public static final class Builder {
        private LateResultPolicy policy = LateResultPolicy.DEFAULT;
        private BiFunction<String, WebhookEvent, String> userId;
        private long toleranceSeconds = Webhook.DEFAULT_TOLERANCE_SECONDS;
        private Long nowEpochSeconds;

        /** Account decision to action. Defaults to {@link LateResultPolicy#DEFAULT}. */
        public Builder policy(LateResultPolicy policy) {
            this.policy = policy;
            return this;
        }

        /**
         * The provider user id for the assessment's account id. Defaults to the
         * account id itself. Return null or empty to take no action.
         */
        public Builder userId(BiFunction<String, WebhookEvent, String> resolver) {
            this.userId = resolver;
            return this;
        }

        /** The replay window passed to {@code Webhook.constructEvent}. */
        public Builder toleranceSeconds(long seconds) {
            this.toleranceSeconds = seconds;
            return this;
        }

        /** The clock passed to {@code Webhook.constructEvent}; defaults to now. */
        public Builder nowEpochSeconds(long seconds) {
            this.nowEpochSeconds = seconds;
            return this;
        }

        public LateResultOptions build() {
            return new LateResultOptions(this);
        }
    }
}
