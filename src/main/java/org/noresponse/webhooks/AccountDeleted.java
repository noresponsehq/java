package org.noresponse.webhooks;

/**
 * An account you erased with {@code accounts().delete}: delete what you hold for it.
 * {@link #assessmentsDeleted()} is at least 1, and {@link #deletedAt()} equals
 * deleted_at in the delete answer.
 */
public final class AccountDeleted {
    private final String accountId;
    private final long assessmentsDeleted;
    private final String deletedAt;

    public AccountDeleted(String accountId, long assessmentsDeleted, String deletedAt) {
        this.accountId = accountId;
        this.assessmentsDeleted = assessmentsDeleted;
        this.deletedAt = deletedAt;
    }

    public String accountId() {
        return accountId;
    }

    public long assessmentsDeleted() {
        return assessmentsDeleted;
    }

    public String deletedAt() {
        return deletedAt;
    }
}
