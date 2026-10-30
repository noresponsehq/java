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

/**
 * IdentifierTermMatch
 *
 * <p>One satisfied unique term in an identifier expression. term_index is the zero-based depth-first raw
 * leaf position of its first occurrence. Duplicate normalized terms produce one witness.
 *
 * <p>All satisfied unique terms are returned, ordered by term_index, including satisfied terms in an OR
 * branch that was not needed. value preserves the original submitted value in assessment_id. In
 * assessment scope all witnesses belong to the same matching assessment; account search chooses the
 * newest such assessment.
 *
 * <p>In account scope each term uses its newest retained matching assessment, so witnesses may have
 * different assessment IDs. This evidence does not assert ownership or equality between accounts.
 */
public class IdentifierTermMatch {

    @JsonProperty("kind")
    private IdentifierSearchKind kind;

    @JsonProperty("value")
    private String value;

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

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    @JsonProperty("assessment_id")
    private String assessmentId;

    /**
     * Zero-based depth-first position of the first occurrence of this normalized term in the submitted
     * expression.
     */
    @JsonProperty("term_index")
    private long termIndex;

    @JsonCreator
    public IdentifierTermMatch(
            @JsonProperty("kind") @Nonnull IdentifierSearchKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("match_basis") @Nonnull Match matchBasis,
            @JsonProperty("provenance") @Nonnull Provenance provenance,
            @JsonProperty("namespace") @Nullable Namespace namespace,
            @JsonProperty("assessment_id") @Nonnull String assessmentId,
            @JsonProperty("term_index") long termIndex) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.matchBasis = Optional.ofNullable(matchBasis)
                .orElseThrow(() -> new IllegalArgumentException("matchBasis cannot be null"));
        this.provenance = Optional.ofNullable(provenance)
                .orElseThrow(() -> new IllegalArgumentException("provenance cannot be null"));
        this.namespace = namespace;
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
        this.termIndex = termIndex;
    }

    public IdentifierTermMatch(
            @Nonnull IdentifierSearchKind kind,
            @Nonnull String value,
            @Nonnull Match matchBasis,
            @Nonnull Provenance provenance,
            @Nonnull String assessmentId,
            long termIndex) {
        this(kind, value, matchBasis, provenance, null, assessmentId, termIndex);
    }

    public IdentifierSearchKind kind() {
        return this.kind;
    }

    public String value() {
        return this.value;
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

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public String assessmentId() {
        return this.assessmentId;
    }

    /**
     * Zero-based depth-first position of the first occurrence of this normalized term in the submitted
     * expression.
     */
    public long termIndex() {
        return this.termIndex;
    }

    public static Builder builder() {
        return new Builder();
    }

    public IdentifierTermMatch withKind(@Nonnull IdentifierSearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public IdentifierTermMatch withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public IdentifierTermMatch withMatchBasis(@Nonnull Match matchBasis) {
        this.matchBasis = Utils.checkNotNull(matchBasis, "matchBasis");
        return this;
    }

    /**
     * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
     */
    public IdentifierTermMatch withProvenance(@Nonnull Provenance provenance) {
        this.provenance = Utils.checkNotNull(provenance, "provenance");
        return this;
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public IdentifierTermMatch withNamespace(@Nullable Namespace namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public IdentifierTermMatch withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
        return this;
    }

    /**
     * Zero-based depth-first position of the first occurrence of this normalized term in the submitted
     * expression.
     */
    public IdentifierTermMatch withTermIndex(long termIndex) {
        this.termIndex = termIndex;
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
        IdentifierTermMatch other = (IdentifierTermMatch) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.matchBasis, other.matchBasis)
                && Utils.enhancedDeepEquals(this.provenance, other.provenance)
                && Utils.enhancedDeepEquals(this.namespace, other.namespace)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId)
                && Utils.enhancedDeepEquals(this.termIndex, other.termIndex);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, value, matchBasis, provenance, namespace, assessmentId, termIndex);
    }

    @Override
    public String toString() {
        return Utils.toString(
                IdentifierTermMatch.class,
                "kind",
                kind,
                "value",
                value,
                "matchBasis",
                matchBasis,
                "provenance",
                provenance,
                "namespace",
                namespace,
                "assessmentId",
                assessmentId,
                "termIndex",
                termIndex);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearchKind kind;

        private String value;

        private Match matchBasis;

        private Provenance provenance;

        private Namespace namespace;

        private String assessmentId;

        private long termIndex;

        private Builder() {
            // force use of static builder() method
        }

        public Builder kind(@Nonnull IdentifierSearchKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
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

        /**
         * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
         * within the workspace; another workspace's assessment id answers 404.
         */
        public Builder assessmentId(@Nonnull String assessmentId) {
            this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
            return this;
        }

        /**
         * Zero-based depth-first position of the first occurrence of this normalized term in the submitted
         * expression.
         */
        public Builder termIndex(long termIndex) {
            this.termIndex = termIndex;
            return this;
        }

        public IdentifierTermMatch build() {
            return new IdentifierTermMatch(kind, value, matchBasis, provenance, namespace, assessmentId, termIndex);
        }
    }
}
