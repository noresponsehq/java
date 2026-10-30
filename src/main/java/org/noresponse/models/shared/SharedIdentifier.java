package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class SharedIdentifier {

    @JsonProperty("kind")
    private IdentifierSearchKind kind;

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    @JsonProperty("match_basis")
    private Match matchBasis;

    /**
     * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
     */
    @JsonProperty("provenance")
    private Provenance provenance;

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("namespace")
    private Namespace namespace;

    @JsonProperty("account")
    private IdentifierSubmission account;

    @JsonProperty("linked_account")
    private IdentifierSubmission linkedAccount;

    @JsonCreator
    public SharedIdentifier(
            @JsonProperty("kind") @Nonnull IdentifierSearchKind kind,
            @JsonProperty("match_basis") @Nonnull Match matchBasis,
            @JsonProperty("provenance") @Nonnull Provenance provenance,
            @JsonProperty("namespace") @Nullable Namespace namespace,
            @JsonProperty("account") @Nonnull IdentifierSubmission account,
            @JsonProperty("linked_account") @Nonnull IdentifierSubmission linkedAccount) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.matchBasis = Optional.ofNullable(matchBasis)
                .orElseThrow(() -> new IllegalArgumentException("matchBasis cannot be null"));
        this.provenance = Optional.ofNullable(provenance)
                .orElseThrow(() -> new IllegalArgumentException("provenance cannot be null"));
        this.namespace = namespace;
        this.account =
                Optional.ofNullable(account).orElseThrow(() -> new IllegalArgumentException("account cannot be null"));
        this.linkedAccount = Optional.ofNullable(linkedAccount)
                .orElseThrow(() -> new IllegalArgumentException("linkedAccount cannot be null"));
    }

    public SharedIdentifier(
            @Nonnull IdentifierSearchKind kind,
            @Nonnull Match matchBasis,
            @Nonnull Provenance provenance,
            @Nonnull IdentifierSubmission account,
            @Nonnull IdentifierSubmission linkedAccount) {
        this(kind, matchBasis, provenance, null, account, linkedAccount);
    }

    public IdentifierSearchKind kind() {
        return this.kind;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public Match matchBasis() {
        return this.matchBasis;
    }

    /**
     * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
     */
    public Provenance provenance() {
        return this.provenance;
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public Optional<Namespace> namespace() {
        return Optional.ofNullable(this.namespace);
    }

    public IdentifierSubmission account() {
        return this.account;
    }

    public IdentifierSubmission linkedAccount() {
        return this.linkedAccount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public SharedIdentifier withKind(@Nonnull IdentifierSearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public SharedIdentifier withMatchBasis(@Nonnull Match matchBasis) {
        this.matchBasis = Utils.checkNotNull(matchBasis, "matchBasis");
        return this;
    }

    /**
     * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
     */
    public SharedIdentifier withProvenance(@Nonnull Provenance provenance) {
        this.provenance = Utils.checkNotNull(provenance, "provenance");
        return this;
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public SharedIdentifier withNamespace(@Nullable Namespace namespace) {
        this.namespace = namespace;
        return this;
    }

    public SharedIdentifier withAccount(@Nonnull IdentifierSubmission account) {
        this.account = Utils.checkNotNull(account, "account");
        return this;
    }

    public SharedIdentifier withLinkedAccount(@Nonnull IdentifierSubmission linkedAccount) {
        this.linkedAccount = Utils.checkNotNull(linkedAccount, "linkedAccount");
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SharedIdentifier other = (SharedIdentifier) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.matchBasis, other.matchBasis)
                && Utils.enhancedDeepEquals(this.provenance, other.provenance)
                && Utils.enhancedDeepEquals(this.namespace, other.namespace)
                && Utils.enhancedDeepEquals(this.account, other.account)
                && Utils.enhancedDeepEquals(this.linkedAccount, other.linkedAccount);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, matchBasis, provenance, namespace, account, linkedAccount);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SharedIdentifier.class,
                "kind",
                kind,
                "matchBasis",
                matchBasis,
                "provenance",
                provenance,
                "namespace",
                namespace,
                "account",
                account,
                "linkedAccount",
                linkedAccount);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearchKind kind;

        private Match matchBasis;

        private Provenance provenance;

        private Namespace namespace;

        private IdentifierSubmission account;

        private IdentifierSubmission linkedAccount;

        private Builder() {
            // force use of static builder() method
        }

        public Builder kind(@Nonnull IdentifierSearchKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * exact compares the original submitted value; canonical uses identifier-specific normalisation;
         * mailbox explicitly selects the email alias rules used by lists.
         */
        public Builder matchBasis(@Nonnull Match matchBasis) {
            this.matchBasis = Utils.checkNotNull(matchBasis, "matchBasis");
            return this;
        }

        /**
         * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
         */
        public Builder provenance(@Nonnull Provenance provenance) {
            this.provenance = Utils.checkNotNull(provenance, "provenance");
            return this;
        }

        /**
         * Required for username: the username submitted by this customer in this workspace/environment. It
         * does not name or join a source platform. Omit for email and phone.
         */
        public Builder namespace(@Nullable Namespace namespace) {
            this.namespace = namespace;
            return this;
        }

        public Builder account(@Nonnull IdentifierSubmission account) {
            this.account = Utils.checkNotNull(account, "account");
            return this;
        }

        public Builder linkedAccount(@Nonnull IdentifierSubmission linkedAccount) {
            this.linkedAccount = Utils.checkNotNull(linkedAccount, "linkedAccount");
            return this;
        }

        public SharedIdentifier build() {
            return new SharedIdentifier(kind, matchBasis, provenance, namespace, account, linkedAccount);
        }
    }
}
