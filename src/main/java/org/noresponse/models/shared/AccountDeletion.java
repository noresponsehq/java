package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AccountDeletion
 *
 * <p>Body of the 200 answer to DELETE /v1/accounts/{account_id}: the calling workspace's account was
 * erased in the environment of the calling key. Every assessment of the account went, with its
 * revisions, evidence, outcomes and the identity-graph marks its outcomes set, and an assessment that
 * was still running when the call arrived is stopped. Afterwards GET /v1/accounts/{account_id} and GET
 * /v1/assessments/{assessment_id} of each erased assessment answer 404, and the history page is empty.
 *
 * <p>The call is idempotent: a repeat, or an account with nothing to erase, answers 200 with
 * assessments_deleted 0. The account_id can be assessed again later, as a new account.
 */
public class AccountDeletion {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * The account_id that was erased.
     */
    @JsonProperty("id")
    private String id;

    @JsonProperty("deleted")
    private boolean deleted;

    /**
     * How many assessments this call deleted. 0 when the account had none left, so a repeat answers 0.
     */
    @JsonProperty("assessments_deleted")
    private long assessmentsDeleted;

    /**
     * When the erasure took effect. An assessment of the account begun before this time is refused or
     * stopped.
     */
    @JsonProperty("deleted_at")
    private String deletedAt;

    /**
     * Receipt for GET /v1/erasures/{erasure_id}, when durable storage issued one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("erasure_id")
    private String erasureId;

    @JsonCreator
    public AccountDeletion(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("assessments_deleted") long assessmentsDeleted,
            @JsonProperty("deleted_at") @Nonnull String deletedAt,
            @JsonProperty("erasure_id") @Nullable String erasureId) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.deleted = Builder._SINGLETON_VALUE_Deleted.value();
        this.assessmentsDeleted = assessmentsDeleted;
        this.deletedAt = Optional.ofNullable(deletedAt)
                .orElseThrow(() -> new IllegalArgumentException("deletedAt cannot be null"));
        this.erasureId = erasureId;
    }

    public AccountDeletion(@Nonnull String id, long assessmentsDeleted, @Nonnull String deletedAt) {
        this(id, assessmentsDeleted, deletedAt, null);
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * The account_id that was erased.
     */
    public String id() {
        return this.id;
    }

    public boolean deleted() {
        return this.deleted;
    }

    /**
     * How many assessments this call deleted. 0 when the account had none left, so a repeat answers 0.
     */
    public long assessmentsDeleted() {
        return this.assessmentsDeleted;
    }

    /**
     * When the erasure took effect. An assessment of the account begun before this time is refused or
     * stopped.
     */
    public String deletedAt() {
        return this.deletedAt;
    }

    /**
     * Receipt for GET /v1/erasures/{erasure_id}, when durable storage issued one.
     */
    public Optional<String> erasureId() {
        return Optional.ofNullable(this.erasureId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id that was erased.
     */
    public AccountDeletion withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * How many assessments this call deleted. 0 when the account had none left, so a repeat answers 0.
     */
    public AccountDeletion withAssessmentsDeleted(long assessmentsDeleted) {
        this.assessmentsDeleted = assessmentsDeleted;
        return this;
    }

    /**
     * When the erasure took effect. An assessment of the account begun before this time is refused or
     * stopped.
     */
    public AccountDeletion withDeletedAt(@Nonnull String deletedAt) {
        this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
        return this;
    }

    /**
     * Receipt for GET /v1/erasures/{erasure_id}, when durable storage issued one.
     */
    public AccountDeletion withErasureId(@Nullable String erasureId) {
        this.erasureId = erasureId;
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
        AccountDeletion other = (AccountDeletion) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.deleted, other.deleted)
                && Utils.enhancedDeepEquals(this.assessmentsDeleted, other.assessmentsDeleted)
                && Utils.enhancedDeepEquals(this.deletedAt, other.deletedAt)
                && Utils.enhancedDeepEquals(this.erasureId, other.erasureId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, id, deleted, assessmentsDeleted, deletedAt, erasureId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AccountDeletion.class,
                "schemaVersion",
                schemaVersion,
                "id",
                id,
                "deleted",
                deleted,
                "assessmentsDeleted",
                assessmentsDeleted,
                "deletedAt",
                deletedAt,
                "erasureId",
                erasureId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private long assessmentsDeleted;

        private String deletedAt;

        private String erasureId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id that was erased.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * How many assessments this call deleted. 0 when the account had none left, so a repeat answers 0.
         */
        public Builder assessmentsDeleted(long assessmentsDeleted) {
            this.assessmentsDeleted = assessmentsDeleted;
            return this;
        }

        /**
         * When the erasure took effect. An assessment of the account begun before this time is refused or
         * stopped.
         */
        public Builder deletedAt(@Nonnull String deletedAt) {
            this.deletedAt = Utils.checkNotNull(deletedAt, "deletedAt");
            return this;
        }

        /**
         * Receipt for GET /v1/erasures/{erasure_id}, when durable storage issued one.
         */
        public Builder erasureId(@Nullable String erasureId) {
            this.erasureId = erasureId;
            return this;
        }

        public AccountDeletion build() {
            return new AccountDeletion(id, assessmentsDeleted, deletedAt, erasureId);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_Deleted =
                new LazySingletonValue<>("deleted", "true", new TypeReference<Boolean>() {});
    }
}
