package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentDeletion
 *
 * <p>Body of the 200 answer to DELETE /v1/assessments/{assessment_id}: one of the calling tenant's
 * assessments was deleted, with its versions, evidence, outcomes and the identity-graph marks its
 * outcomes set. Afterwards GET /v1/assessments/{assessment_id} answers 404 and the assessment leaves
 * the account's history. The account is scored again from what remains: account.current_assessment_id
 * names the assessment GET /v1/accounts/{account_id} now serves its risk from, and is absent when the
 * account has no assessment with a decision left.
 *
 * <p>Deleting an assessment that is already gone answers 404.
 */
public class AssessmentDeletion {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * The assessment that was deleted.
     */
    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private boolean deleted;

    @JsonProperty("account")
    private AssessmentDeletionAccount account;

    /**
     * When the deletion took effect.
     */
    @JsonProperty("deleted_at")
    private String deletedAt;

    @JsonCreator
    public AssessmentDeletion(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("account") @Nonnull AssessmentDeletionAccount account,
            @JsonProperty("deleted_at") @Nonnull String deletedAt) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.deleted = Builder._SINGLETON_VALUE_Deleted.value();
        this.account =
                Optional.ofNullable(account).orElseThrow(() -> new IllegalArgumentException("account cannot be null"));
        this.deletedAt = Optional.ofNullable(deletedAt)
                .orElseThrow(() -> new IllegalArgumentException("deletedAt cannot be null"));
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * The assessment that was deleted.
     */
    public String id() {
        return this.id;
    }

    public boolean deleted() {
        return this.deleted;
    }

    public AssessmentDeletionAccount account() {
        return this.account;
    }

    /**
     * When the deletion took effect.
     */
    public String deletedAt() {
        return this.deletedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The assessment that was deleted.
     */
    public AssessmentDeletion withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public AssessmentDeletion withAccount(@Nonnull AssessmentDeletionAccount account) {
        this.account = Utils.checkNotNull(account, "account");
        return this;
    }

    /**
     * When the deletion took effect.
     */
    public AssessmentDeletion withDeletedAt(@Nonnull String deletedAt) {
        this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
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
        AssessmentDeletion other = (AssessmentDeletion) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.deleted, other.deleted)
                && Utils.enhancedDeepEquals(this.account, other.account)
                && Utils.enhancedDeepEquals(this.deletedAt, other.deletedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, id, deleted, account, deletedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentDeletion.class,
                "schemaVersion",
                schemaVersion,
                "id",
                id,
                "deleted",
                deleted,
                "account",
                account,
                "deletedAt",
                deletedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private AssessmentDeletionAccount account;

        private String deletedAt;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The assessment that was deleted.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder account(@Nonnull AssessmentDeletionAccount account) {
            this.account = Utils.checkNotNull(account, "account");
            return this;
        }

        /**
         * When the deletion took effect.
         */
        public Builder deletedAt(@Nonnull String deletedAt) {
            this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
            return this;
        }

        public AssessmentDeletion build() {
            return new AssessmentDeletion(id, account, deletedAt);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_Deleted =
                new LazySingletonValue<>("deleted", "true", new TypeReference<Boolean>() {});
    }
}
