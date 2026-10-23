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

public class AssessmentDeletionAccount {
    /**
     * The account_id of the deleted assessment.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The assessment the account's current risk now comes from. Absent when the account has no assessment
     * with a decision left, and GET /v1/accounts/{account_id} then answers 404.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("current_assessment_id")
    private String currentAssessmentId;

    @JsonCreator
    public AssessmentDeletionAccount(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("current_assessment_id") @Nullable String currentAssessmentId) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.currentAssessmentId = currentAssessmentId;
    }

    public AssessmentDeletionAccount(@Nonnull String id) {
        this(id, null);
    }

    /**
     * The account_id of the deleted assessment.
     */
    public String id() {
        return this.id;
    }

    /**
     * The assessment the account's current risk now comes from. Absent when the account has no assessment
     * with a decision left, and GET /v1/accounts/{account_id} then answers 404.
     */
    public Optional<String> currentAssessmentId() {
        return Optional.ofNullable(this.currentAssessmentId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id of the deleted assessment.
     */
    public AssessmentDeletionAccount withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * The assessment the account's current risk now comes from. Absent when the account has no assessment
     * with a decision left, and GET /v1/accounts/{account_id} then answers 404.
     */
    public AssessmentDeletionAccount withCurrentAssessmentId(@Nullable String currentAssessmentId) {
        this.currentAssessmentId = currentAssessmentId;
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
        AssessmentDeletionAccount other = (AssessmentDeletionAccount) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.currentAssessmentId, other.currentAssessmentId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, currentAssessmentId);
    }

    @Override
    public String toString() {
        return Utils.toString(AssessmentDeletionAccount.class, "id", id, "currentAssessmentId", currentAssessmentId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String currentAssessmentId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id of the deleted assessment.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * The assessment the account's current risk now comes from. Absent when the account has no assessment
         * with a decision left, and GET /v1/accounts/{account_id} then answers 404.
         */
        public Builder currentAssessmentId(@Nullable String currentAssessmentId) {
            this.currentAssessmentId = currentAssessmentId;
            return this;
        }

        public AssessmentDeletionAccount build() {
            return new AssessmentDeletionAccount(id, currentAssessmentId);
        }
    }
}
