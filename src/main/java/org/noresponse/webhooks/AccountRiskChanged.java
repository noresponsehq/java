package org.noresponse.webhooks;

import org.noresponse.models.shared.Account;
import org.noresponse.models.shared.AccountDecisionsByFlow;

/**
 * The account's decision for at least one flow changed. {@link #account()} is the account exactly
 * as {@code accounts().get} returns it, with its new risk and decisions, and
 * {@link #previousDecisionsByFlow()} holds the decisions it replaced. It is not sent when the score
 * moves within the same decision, nor for an account's first risk.
 */
public final class AccountRiskChanged {
    private final Account account;
    private final AccountDecisionsByFlow previousDecisionsByFlow;

    public AccountRiskChanged(Account account, AccountDecisionsByFlow previousDecisionsByFlow) {
        this.account = account;
        this.previousDecisionsByFlow = previousDecisionsByFlow;
    }

    /** The account as {@code accounts().get} returns it: the new score, decision, reasons and decisions by flow. */
    public Account account() {
        return account;
    }

    /** The decision for each flow before the change, as last announced. */
    public AccountDecisionsByFlow previousDecisionsByFlow() {
        return previousDecisionsByFlow;
    }
}
