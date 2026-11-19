package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

public class PersonChange {

    @JsonProperty("id")
    private String id;

    /**
     * account_added and account_removed: an account or profile appeared or disappeared. breach_added and
     * breach_removed: a breach record appeared or disappeared. identifier_added and identifier_removed: an
     * email, phone, username, name or account id on an account the person already held.
     *
     * <p>field_changed: another field on such an account. finding_added: a new finding. posts_added: new
     * posts, counted in count.
     */
    @JsonProperty("kind")
    private PersonChangeKind kind;

    /**
     * One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile
     * fields and findings are medium, new posts are low.
     */
    @JsonProperty("significance")
    private Significance significance;

    /**
     * The connector, site or platform the change came from.
     */
    @JsonProperty("source")
    private String source;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("field")
    private String field;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("before")
    private String before;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("after")
    private String after;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("count")
    private Long count;

    /**
     * The investigation that found the change.
     */
    @JsonProperty("investigation_id")
    private String investigationId;

    /**
     * The earlier investigation it was compared with.
     */
    @JsonProperty("previous_investigation_id")
    private String previousInvestigationId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("reviewed_at")
    private JsonNullable<String> reviewedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reviewed_by")
    private String reviewedBy;

    @JsonCreator
    public PersonChange(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("kind") @Nonnull PersonChangeKind kind,
            @JsonProperty("significance") @Nonnull Significance significance,
            @JsonProperty("source") @Nonnull String source,
            @JsonProperty("field") @Nullable String field,
            @JsonProperty("before") @Nullable String before,
            @JsonProperty("after") @Nullable String after,
            @JsonProperty("count") @Nullable Long count,
            @JsonProperty("investigation_id") @Nonnull String investigationId,
            @JsonProperty("previous_investigation_id") @Nonnull String previousInvestigationId,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("reviewed_at") @Nullable String reviewedAt,
            @JsonProperty("reviewed_by") @Nullable String reviewedBy) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.significance = Optional.ofNullable(significance)
                .orElseThrow(() -> new IllegalArgumentException("significance cannot be null"));
        this.source =
                Optional.ofNullable(source).orElseThrow(() -> new IllegalArgumentException("source cannot be null"));
        this.field = field;
        this.before = before;
        this.after = after;
        this.count = count;
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
        this.previousInvestigationId = Optional.ofNullable(previousInvestigationId)
                .orElseThrow(() -> new IllegalArgumentException("previousInvestigationId cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.reviewedAt = JsonNullable.of(reviewedAt);
        this.reviewedBy = reviewedBy;
    }

    public PersonChange(
            @Nonnull String id,
            @Nonnull PersonChangeKind kind,
            @Nonnull Significance significance,
            @Nonnull String source,
            @Nonnull String investigationId,
            @Nonnull String previousInvestigationId,
            @Nonnull String createdAt) {
        this(
                id,
                kind,
                significance,
                source,
                null,
                null,
                null,
                null,
                investigationId,
                previousInvestigationId,
                createdAt,
                null,
                null);
    }

    public String id() {
        return this.id;
    }

    /**
     * account_added and account_removed: an account or profile appeared or disappeared. breach_added and
     * breach_removed: a breach record appeared or disappeared. identifier_added and identifier_removed: an
     * email, phone, username, name or account id on an account the person already held.
     *
     * <p>field_changed: another field on such an account. finding_added: a new finding. posts_added: new
     * posts, counted in count.
     */
    public PersonChangeKind kind() {
        return this.kind;
    }

    /**
     * One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile
     * fields and findings are medium, new posts are low.
     */
    public Significance significance() {
        return this.significance;
    }

    /**
     * The connector, site or platform the change came from.
     */
    public String source() {
        return this.source;
    }

    public Optional<String> field() {
        return Optional.ofNullable(this.field);
    }

    public Optional<String> before() {
        return Optional.ofNullable(this.before);
    }

    public Optional<String> after() {
        return Optional.ofNullable(this.after);
    }

    public Optional<Long> count() {
        return Optional.ofNullable(this.count);
    }

    /**
     * The investigation that found the change.
     */
    public String investigationId() {
        return this.investigationId;
    }

    /**
     * The earlier investigation it was compared with.
     */
    public String previousInvestigationId() {
        return this.previousInvestigationId;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public JsonNullable<String> reviewedAt() {
        return this.reviewedAt;
    }

    public Optional<String> reviewedBy() {
        return Optional.ofNullable(this.reviewedBy);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PersonChange withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * account_added and account_removed: an account or profile appeared or disappeared. breach_added and
     * breach_removed: a breach record appeared or disappeared. identifier_added and identifier_removed: an
     * email, phone, username, name or account id on an account the person already held.
     *
     * <p>field_changed: another field on such an account. finding_added: a new finding. posts_added: new
     * posts, counted in count.
     */
    public PersonChange withKind(@Nonnull PersonChangeKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile
     * fields and findings are medium, new posts are low.
     */
    public PersonChange withSignificance(@Nonnull Significance significance) {
        this.significance = Utils.checkNotNull(significance, "significance");
        return this;
    }

    /**
     * The connector, site or platform the change came from.
     */
    public PersonChange withSource(@Nonnull String source) {
        this.source = Utils.checkNotNull(source, "source");
        return this;
    }

    public PersonChange withField(@Nullable String field) {
        this.field = field;
        return this;
    }

    public PersonChange withBefore(@Nullable String before) {
        this.before = before;
        return this;
    }

    public PersonChange withAfter(@Nullable String after) {
        this.after = after;
        return this;
    }

    public PersonChange withCount(@Nullable Long count) {
        this.count = count;
        return this;
    }

    /**
     * The investigation that found the change.
     */
    public PersonChange withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
        return this;
    }

    /**
     * The earlier investigation it was compared with.
     */
    public PersonChange withPreviousInvestigationId(@Nonnull String previousInvestigationId) {
        this.previousInvestigationId = Utils.checkNotNull(previousInvestigationId, "previousInvestigationId");
        return this;
    }

    public PersonChange withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public PersonChange withReviewedAt(@Nullable String reviewedAt) {
        this.reviewedAt = JsonNullable.of(reviewedAt);
        return this;
    }

    public PersonChange withReviewedBy(@Nullable String reviewedBy) {
        this.reviewedBy = reviewedBy;
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
        PersonChange other = (PersonChange) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.significance, other.significance)
                && Utils.enhancedDeepEquals(this.source, other.source)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.before, other.before)
                && Utils.enhancedDeepEquals(this.after, other.after)
                && Utils.enhancedDeepEquals(this.count, other.count)
                && Utils.enhancedDeepEquals(this.investigationId, other.investigationId)
                && Utils.enhancedDeepEquals(this.previousInvestigationId, other.previousInvestigationId)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.reviewedAt, other.reviewedAt)
                && Utils.enhancedDeepEquals(this.reviewedBy, other.reviewedBy);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                kind,
                significance,
                source,
                field,
                before,
                after,
                count,
                investigationId,
                previousInvestigationId,
                createdAt,
                reviewedAt,
                reviewedBy);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonChange.class,
                "id",
                id,
                "kind",
                kind,
                "significance",
                significance,
                "source",
                source,
                "field",
                field,
                "before",
                before,
                "after",
                after,
                "count",
                count,
                "investigationId",
                investigationId,
                "previousInvestigationId",
                previousInvestigationId,
                "createdAt",
                createdAt,
                "reviewedAt",
                reviewedAt,
                "reviewedBy",
                reviewedBy);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private PersonChangeKind kind;

        private Significance significance;

        private String source;

        private String field;

        private String before;

        private String after;

        private Long count;

        private String investigationId;

        private String previousInvestigationId;

        private String createdAt;

        private String reviewedAt;

        private String reviewedBy;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * account_added and account_removed: an account or profile appeared or disappeared. breach_added and
         * breach_removed: a breach record appeared or disappeared. identifier_added and identifier_removed: an
         * email, phone, username, name or account id on an account the person already held.
         *
         * <p>field_changed: another field on such an account. finding_added: a new finding. posts_added: new
         * posts, counted in count.
         */
        public Builder kind(@Nonnull PersonChangeKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * One rule ranks every change: identity changes (accounts, breaches, identifiers) are high, profile
         * fields and findings are medium, new posts are low.
         */
        public Builder significance(@Nonnull Significance significance) {
            this.significance = Utils.checkNotNull(significance, "significance");
            return this;
        }

        /**
         * The connector, site or platform the change came from.
         */
        public Builder source(@Nonnull String source) {
            this.source = Utils.checkNotNull(source, "source");
            return this;
        }

        public Builder field(@Nullable String field) {
            this.field = field;
            return this;
        }

        public Builder before(@Nullable String before) {
            this.before = before;
            return this;
        }

        public Builder after(@Nullable String after) {
            this.after = after;
            return this;
        }

        public Builder count(@Nullable Long count) {
            this.count = count;
            return this;
        }

        /**
         * The investigation that found the change.
         */
        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        /**
         * The earlier investigation it was compared with.
         */
        public Builder previousInvestigationId(@Nonnull String previousInvestigationId) {
            this.previousInvestigationId = Utils.checkNotNull(previousInvestigationId, "previousInvestigationId");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder reviewedAt(@Nullable String reviewedAt) {
            this.reviewedAt = reviewedAt;
            return this;
        }

        public Builder reviewedBy(@Nullable String reviewedBy) {
            this.reviewedBy = reviewedBy;
            return this;
        }

        public PersonChange build() {
            return new PersonChange(
                    id,
                    kind,
                    significance,
                    source,
                    field,
                    before,
                    after,
                    count,
                    investigationId,
                    previousInvestigationId,
                    createdAt,
                    reviewedAt,
                    reviewedBy);
        }
    }
}
