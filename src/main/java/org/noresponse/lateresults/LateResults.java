package org.noresponse.lateresults;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import org.noresponse.models.shared.Account;
import org.noresponse.models.shared.Assessment;
import org.noresponse.models.shared.Status;
import org.noresponse.webhooks.Webhook;
import org.noresponse.webhooks.WebhookEvent;

/**
 * Late-result handlers. Hand-written; not generated.
 *
 * <p>An assessment can be revised after the account creation was allowed: a slow source settles and the
 * account's decision rises. NoResponse sends the new version as a signed assessment.updated webhook.
 * {@link #handleLateResult} verifies that delivery with {@code Webhook.constructEvent}, picks an
 * action from your policy, and acts through the auth provider's admin API: revoke the user's
 * sessions or ban the user. It never acts on an unverified event or a test event. Every provider
 * call treats a 404 (user or session already gone) as done, so a repeated delivery is safe.
 */
public final class LateResults {
    private LateResults() {
    }

    /** The action the default policy names for a verified event. */
    public static LateResultAction lateResultAction(WebhookEvent event) {
        return lateResultAction(event, LateResultPolicy.DEFAULT);
    }

    /**
     * The action your policy names for a verified event, from the account's decision
     * ({@code account.risk.decision}). Only a served assessment that carries the account can act; a
     * pending assessment and a test event from the send-test-event call never do.
     */
    public static LateResultAction lateResultAction(WebhookEvent event, LateResultPolicy policy) {
        if (!"assessment.updated".equals(event.type()) || event.isTest() || event.isExpired()) {
            return LateResultAction.NONE;
        }
        Optional<Assessment> assessment = event.assessment();
        if (!assessment.isPresent()) {
            return LateResultAction.NONE;
        }
        Assessment a = assessment.get();
        Optional<Account> account = a.account();
        if (Status.PENDING.equals(a.status()) || !account.isPresent()) {
            return LateResultAction.NONE;
        }
        return policy.actionFor(account.get().risk().decision().value());
    }

    public static LateResult handleLateResult(byte[] payload, Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets, LateResultProvider provider) {
        return handleLateResult(payload, headers, secrets, provider, LateResultOptions.builder().build());
    }

    /**
     * Verify a webhook delivery and apply your policy to it.
     *
     * <p>The payload, headers, secrets and tolerance are what {@code Webhook.constructEvent} takes; a
     * delivery that fails verification throws {@code VerificationException} before any provider
     * call. Deduplicate on {@code result.event().id()} and ignore versions older than one you have
     * acted on.
     *
     * @throws ProviderActionException when the provider refuses a call
     */
    public static LateResult handleLateResult(byte[] payload, Map<String, ? extends Collection<String>> headers,
            Map<String, String> secrets, LateResultProvider provider, LateResultOptions options) {
        long now = options.nowEpochSeconds() != null ? options.nowEpochSeconds()
                : java.time.Instant.now().getEpochSecond();
        WebhookEvent event = Webhook.constructEvent(payload, headers, secrets, options.toleranceSeconds(), now);
        LateResultAction action = lateResultAction(event, options.policy());
        if (action == LateResultAction.NONE) {
            return new LateResult(event, LateResultAction.NONE, null);
        }
        String accountId = event.assessment().orElseThrow().detail().accountId();
        String userId = options.userId() != null ? options.userId().apply(accountId, event) : accountId;
        if (userId == null || userId.isEmpty()) {
            return new LateResult(event, LateResultAction.NONE, null);
        }
        LateResultAction ran = provider.act(action, userId);
        return new LateResult(event, ran != null ? ran : action, userId);
    }
}
