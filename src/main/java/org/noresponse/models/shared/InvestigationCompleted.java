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

public class InvestigationCompleted {

    @JsonProperty("investigation_id")
    private String investigationId;

    @JsonProperty("person_id")
    private String personId;

    @JsonProperty("status")
    private InvestigationCompletedStatus status;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonCreator
    public InvestigationCompleted(
            @JsonProperty("investigation_id") @Nonnull String investigationId,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("status") @Nonnull InvestigationCompletedStatus status,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("finished_at") @Nonnull String finishedAt) {
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.errorCode = errorCode;
        this.finishedAt = Optional.ofNullable(finishedAt)
                .orElseThrow(() -> new IllegalArgumentException("finishedAt cannot be null"));
    }

    public InvestigationCompleted(
            @Nonnull String investigationId,
            @Nonnull String personId,
            @Nonnull InvestigationCompletedStatus status,
            @Nonnull String finishedAt) {
        this(investigationId, personId, status, null, finishedAt);
    }

    public String investigationId() {
        return this.investigationId;
    }

    public String personId() {
        return this.personId;
    }

    public InvestigationCompletedStatus status() {
        return this.status;
    }

    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public String finishedAt() {
        return this.finishedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InvestigationCompleted withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
        return this;
    }

    public InvestigationCompleted withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public InvestigationCompleted withStatus(@Nonnull InvestigationCompletedStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public InvestigationCompleted withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public InvestigationCompleted withFinishedAt(@Nonnull String finishedAt) {
        this.finishedAt = Utils.checkNotNull(finishedAt, "finishedAt");
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
        InvestigationCompleted other = (InvestigationCompleted) o;
        return Utils.enhancedDeepEquals(this.investigationId, other.investigationId)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(investigationId, personId, status, errorCode, finishedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                InvestigationCompleted.class,
                "investigationId",
                investigationId,
                "personId",
                personId,
                "status",
                status,
                "errorCode",
                errorCode,
                "finishedAt",
                finishedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String investigationId;

        private String personId;

        private InvestigationCompletedStatus status;

        private String errorCode;

        private String finishedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder status(@Nonnull InvestigationCompletedStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder finishedAt(@Nonnull String finishedAt) {
            this.finishedAt = Utils.checkNotNull(finishedAt, "finishedAt");
            return this;
        }

        public InvestigationCompleted build() {
            return new InvestigationCompleted(investigationId, personId, status, errorCode, finishedAt);
        }
    }
}
