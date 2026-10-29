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
 * IdentifierMatch
 *
 * <p>Why this result matched a typed identifier search. value is the original value submitted in
 * assessment_id. Provenance records a customer submission, not a source discovery or verified
 * ownership.
 *
 * <p>Equality is deterministic and has no inferred confidence score. An account result names its newest
 * retained matching assessment, which can differ from latest.
 */
public class IdentifierMatch {

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

    @JsonCreator
    public IdentifierMatch(
            @JsonProperty("kind") @Nonnull IdentifierSearchKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("match_basis") @Nonnull Match matchBasis,
            @JsonProperty("provenance") @Nonnull Provenance provenance,
            @JsonProperty("namespace") @Nullable Namespace namespace,
            @JsonProperty("assessment_id") @Nonnull String assessmentId) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.matchBasis = Optional.ofNullable(matchBasis)
                .orElseThrow(() -> new IllegalArgumentException("matchBasis cannot be null"));
        this.provenance = Optional.ofNullable(provenance)
                .orElseThrow(() -> new IllegalArgumentException("provenance cannot be null"));
        this.namespace = namespace;
        this.assessmentId = Optional.ofNullable(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("assessmentId cannot be null"));
    }

    public IdentifierMatch(
            @Nonnull IdentifierSearchKind kind,
            @Nonnull String value,
            @Nonnull Match matchBasis,
            @Nonnull Provenance provenance,
            @Nonnull String assessmentId) {
        this(kind, value, matchBasis, provenance, null, assessmentId);
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

    public static Builder builder() {
        return new Builder();
    }

    public IdentifierMatch withKind(@Nonnull IdentifierSearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public IdentifierMatch withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * exact compares the original submitted value; canonical uses identifier-specific normalisation;
     * mailbox explicitly selects the email alias rules used by lists.
     */
    public IdentifierMatch withMatchBasis(@Nonnull Match matchBasis) {
        this.matchBasis = Utils.checkNotNull(matchBasis, "matchBasis");
        return this;
    }

    /**
     * Customer-submitted input. It does not mean the customer or NoResponse verified ownership.
     */
    public IdentifierMatch withProvenance(@Nonnull Provenance provenance) {
        this.provenance = Utils.checkNotNull(provenance, "provenance");
        return this;
    }

    /**
     * Required for username: the username submitted by this customer in this workspace/environment. It
     * does not name or join a source platform. Omit for email and phone.
     */
    public IdentifierMatch withNamespace(@Nullable Namespace namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * Opaque, server-assigned assessment id: 'asm_' and a 26-character Crockford base32 ULID. Unique
     * within the workspace; another workspace's assessment id answers 404.
     */
    public IdentifierMatch withAssessmentId(@Nonnull String assessmentId) {
        this.assessmentId = Utils.checkNotNull(assessmentId, "assessmentId");
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
        IdentifierMatch other = (IdentifierMatch) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.matchBasis, other.matchBasis)
                && Utils.enhancedDeepEquals(this.provenance, other.provenance)
                && Utils.enhancedDeepEquals(this.namespace, other.namespace)
                && Utils.enhancedDeepEquals(this.assessmentId, other.assessmentId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, value, matchBasis, provenance, namespace, assessmentId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                IdentifierMatch.class,
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
                assessmentId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearchKind kind;

        private String value;

        private Match matchBasis;

        private Provenance provenance;

        private Namespace namespace;

        private String assessmentId;

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

        public IdentifierMatch build() {
            return new IdentifierMatch(kind, value, matchBasis, provenance, namespace, assessmentId);
        }
    }
}
