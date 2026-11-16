package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ProfileStatement {

    @JsonProperty("topic")
    private Topic topic;

    @JsonProperty("text")
    private String text;

    /**
     * confirmed: a linked record or the searched identifier states it directly. likely: a strong
     * inference. possible: a weak inference.
     */
    @JsonProperty("certainty")
    private Certainty certainty;

    /**
     * stated: read from a record. inferred: reasoned from several facts. absence: every source answered
     * and none found it.
     */
    @JsonProperty("basis")
    private Basis basis;

    /**
     * Other explanations to keep open for an inference.
     */
    @JsonProperty("alternatives")
    private List<String> alternatives;

    /**
     * Every record, post or check the statement rests on.
     */
    @JsonProperty("evidence")
    private List<ProfileCitation> evidence;

    @JsonCreator
    public ProfileStatement(
            @JsonProperty("topic") @Nonnull Topic topic,
            @JsonProperty("text") @Nonnull String text,
            @JsonProperty("certainty") @Nonnull Certainty certainty,
            @JsonProperty("basis") @Nonnull Basis basis,
            @JsonProperty("alternatives") @Nonnull List<String> alternatives,
            @JsonProperty("evidence") @Nonnull List<ProfileCitation> evidence) {
        this.topic = Optional.ofNullable(topic).orElseThrow(() -> new IllegalArgumentException("topic cannot be null"));
        this.text = Optional.ofNullable(text).orElseThrow(() -> new IllegalArgumentException("text cannot be null"));
        this.certainty = Optional.ofNullable(certainty)
                .orElseThrow(() -> new IllegalArgumentException("certainty cannot be null"));
        this.basis = Optional.ofNullable(basis).orElseThrow(() -> new IllegalArgumentException("basis cannot be null"));
        this.alternatives = Optional.ofNullable(alternatives)
                .orElseThrow(() -> new IllegalArgumentException("alternatives cannot be null"));
        this.evidence = Optional.ofNullable(evidence)
                .orElseThrow(() -> new IllegalArgumentException("evidence cannot be null"));
    }

    public Topic topic() {
        return this.topic;
    }

    public String text() {
        return this.text;
    }

    /**
     * confirmed: a linked record or the searched identifier states it directly. likely: a strong
     * inference. possible: a weak inference.
     */
    public Certainty certainty() {
        return this.certainty;
    }

    /**
     * stated: read from a record. inferred: reasoned from several facts. absence: every source answered
     * and none found it.
     */
    public Basis basis() {
        return this.basis;
    }

    /**
     * Other explanations to keep open for an inference.
     */
    public List<String> alternatives() {
        return this.alternatives;
    }

    /**
     * Every record, post or check the statement rests on.
     */
    public List<ProfileCitation> evidence() {
        return this.evidence;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProfileStatement withTopic(@Nonnull Topic topic) {
        this.topic = Utils.checkNotNull(topic, "topic");
        return this;
    }

    public ProfileStatement withText(@Nonnull String text) {
        this.text = Utils.checkNotNull(text, "text");
        return this;
    }

    /**
     * confirmed: a linked record or the searched identifier states it directly. likely: a strong
     * inference. possible: a weak inference.
     */
    public ProfileStatement withCertainty(@Nonnull Certainty certainty) {
        this.certainty = Utils.checkNotNull(certainty, "certainty");
        return this;
    }

    /**
     * stated: read from a record. inferred: reasoned from several facts. absence: every source answered
     * and none found it.
     */
    public ProfileStatement withBasis(@Nonnull Basis basis) {
        this.basis = Utils.checkNotNull(basis, "basis");
        return this;
    }

    /**
     * Other explanations to keep open for an inference.
     */
    public ProfileStatement withAlternatives(@Nonnull List<String> alternatives) {
        this.alternatives = Utils.checkNotNull(alternatives, "alternatives");
        return this;
    }

    /**
     * Every record, post or check the statement rests on.
     */
    public ProfileStatement withEvidence(@Nonnull List<ProfileCitation> evidence) {
        this.evidence = Utils.checkNotNull(evidence, "evidence");
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
        ProfileStatement other = (ProfileStatement) o;
        return Utils.enhancedDeepEquals(this.topic, other.topic)
                && Utils.enhancedDeepEquals(this.text, other.text)
                && Utils.enhancedDeepEquals(this.certainty, other.certainty)
                && Utils.enhancedDeepEquals(this.basis, other.basis)
                && Utils.enhancedDeepEquals(this.alternatives, other.alternatives)
                && Utils.enhancedDeepEquals(this.evidence, other.evidence);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(topic, text, certainty, basis, alternatives, evidence);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ProfileStatement.class,
                "topic",
                topic,
                "text",
                text,
                "certainty",
                certainty,
                "basis",
                basis,
                "alternatives",
                alternatives,
                "evidence",
                evidence);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Topic topic;

        private String text;

        private Certainty certainty;

        private Basis basis;

        private List<String> alternatives;

        private List<ProfileCitation> evidence;

        private Builder() {
            // force use of static builder() method
        }

        public Builder topic(@Nonnull Topic topic) {
            this.topic = Utils.checkNotNull(topic, "topic");
            return this;
        }

        public Builder text(@Nonnull String text) {
            this.text = Utils.checkNotNull(text, "text");
            return this;
        }

        /**
         * confirmed: a linked record or the searched identifier states it directly. likely: a strong
         * inference. possible: a weak inference.
         */
        public Builder certainty(@Nonnull Certainty certainty) {
            this.certainty = Utils.checkNotNull(certainty, "certainty");
            return this;
        }

        /**
         * stated: read from a record. inferred: reasoned from several facts. absence: every source answered
         * and none found it.
         */
        public Builder basis(@Nonnull Basis basis) {
            this.basis = Utils.checkNotNull(basis, "basis");
            return this;
        }

        /**
         * Other explanations to keep open for an inference.
         */
        public Builder alternatives(@Nonnull List<String> alternatives) {
            this.alternatives = Utils.checkNotNull(alternatives, "alternatives");
            return this;
        }

        /**
         * Every record, post or check the statement rests on.
         */
        public Builder evidence(@Nonnull List<ProfileCitation> evidence) {
            this.evidence = Utils.checkNotNull(evidence, "evidence");
            return this;
        }

        public ProfileStatement build() {
            return new ProfileStatement(topic, text, certainty, basis, alternatives, evidence);
        }
    }
}
