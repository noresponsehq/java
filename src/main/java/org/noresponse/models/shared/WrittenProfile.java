package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;
import org.openapitools.jackson.nullable.JsonNullable;

public class WrittenProfile {
    /**
     * queued: the profile is being written. written: summary, statements and leads hold it. failed: it
     * could not be written; error_code says why and no content is returned.
     */
    @JsonProperty("status")
    private WrittenProfileStatus status;

    /**
     * The investigation whose evidence the profile is written from.
     */
    @JsonProperty("investigation_id")
    private String investigationId;

    /**
     * Present only when status is failed. no_writer: no profile writer serves this environment. refused:
     * the model refused.
     *
     * <p>not_written: the answer was not a profile, or no statement survived grounding. uncited_statement: a
     * statement or lead cited a record the person does not hold. not_answered: the writer did not answer.
     *
     * <p>worker_attempts_exhausted: the worker stopped retrying.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private WrittenProfileErrorCode errorCode;

    @JsonProperty("queued_at")
    private String queuedAt;

    @JsonInclude(Include.ALWAYS)
    @JsonProperty("finished_at")
    private JsonNullable<String> finishedAt;

    /**
     * Present when written: a few plain sentences with the most useful picture first.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("summary")
    private String summary;

    /**
     * Each fact or inference with its certainty and the evidence it cites. Statements that cited evidence
     * the writer was not sent are dropped before storage.
     */
    @JsonProperty("statements")
    private List<ProfileStatement> statements;

    /**
     * Values worth searching next, each held by a linked record.
     */
    @JsonProperty("leads")
    private List<ProfileLead> leads;

    @JsonCreator
    public WrittenProfile(
            @JsonProperty("status") @Nonnull WrittenProfileStatus status,
            @JsonProperty("investigation_id") @Nonnull String investigationId,
            @JsonProperty("error_code") @Nullable WrittenProfileErrorCode errorCode,
            @JsonProperty("queued_at") @Nonnull String queuedAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("summary") @Nullable String summary,
            @JsonProperty("statements") @Nonnull List<ProfileStatement> statements,
            @JsonProperty("leads") @Nonnull List<ProfileLead> leads) {
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
        this.errorCode = errorCode;
        this.queuedAt = Optional.ofNullable(queuedAt)
                .orElseThrow(() -> new IllegalArgumentException("queuedAt cannot be null"));
        this.finishedAt = JsonNullable.of(finishedAt);
        this.summary = summary;
        this.statements = Optional.ofNullable(statements)
                .orElseThrow(() -> new IllegalArgumentException("statements cannot be null"));
        this.leads = Optional.ofNullable(leads).orElseThrow(() -> new IllegalArgumentException("leads cannot be null"));
    }

    public WrittenProfile(
            @Nonnull WrittenProfileStatus status,
            @Nonnull String investigationId,
            @Nonnull String queuedAt,
            @Nonnull List<ProfileStatement> statements,
            @Nonnull List<ProfileLead> leads) {
        this(status, investigationId, null, queuedAt, null, null, statements, leads);
    }

    /**
     * queued: the profile is being written. written: summary, statements and leads hold it. failed: it
     * could not be written; error_code says why and no content is returned.
     */
    public WrittenProfileStatus status() {
        return this.status;
    }

    /**
     * The investigation whose evidence the profile is written from.
     */
    public String investigationId() {
        return this.investigationId;
    }

    /**
     * Present only when status is failed. no_writer: no profile writer serves this environment. refused:
     * the model refused.
     *
     * <p>not_written: the answer was not a profile, or no statement survived grounding. uncited_statement: a
     * statement or lead cited a record the person does not hold. not_answered: the writer did not answer.
     *
     * <p>worker_attempts_exhausted: the worker stopped retrying.
     */
    public Optional<WrittenProfileErrorCode> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public String queuedAt() {
        return this.queuedAt;
    }

    public JsonNullable<String> finishedAt() {
        return this.finishedAt;
    }

    /**
     * Present when written: a few plain sentences with the most useful picture first.
     */
    public Optional<String> summary() {
        return Optional.ofNullable(this.summary);
    }

    /**
     * Each fact or inference with its certainty and the evidence it cites. Statements that cited evidence
     * the writer was not sent are dropped before storage.
     */
    public List<ProfileStatement> statements() {
        return this.statements;
    }

    /**
     * Values worth searching next, each held by a linked record.
     */
    public List<ProfileLead> leads() {
        return this.leads;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * queued: the profile is being written. written: summary, statements and leads hold it. failed: it
     * could not be written; error_code says why and no content is returned.
     */
    public WrittenProfile withStatus(@Nonnull WrittenProfileStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The investigation whose evidence the profile is written from.
     */
    public WrittenProfile withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
        return this;
    }

    /**
     * Present only when status is failed. no_writer: no profile writer serves this environment. refused:
     * the model refused.
     *
     * <p>not_written: the answer was not a profile, or no statement survived grounding. uncited_statement: a
     * statement or lead cited a record the person does not hold. not_answered: the writer did not answer.
     *
     * <p>worker_attempts_exhausted: the worker stopped retrying.
     */
    public WrittenProfile withErrorCode(@Nullable WrittenProfileErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public WrittenProfile withQueuedAt(@Nonnull String queuedAt) {
        this.queuedAt = Utils.checkNotNull(queuedAt, "queuedAt");
        return this;
    }

    public WrittenProfile withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = JsonNullable.of(finishedAt);
        return this;
    }

    /**
     * Present when written: a few plain sentences with the most useful picture first.
     */
    public WrittenProfile withSummary(@Nullable String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * Each fact or inference with its certainty and the evidence it cites. Statements that cited evidence
     * the writer was not sent are dropped before storage.
     */
    public WrittenProfile withStatements(@Nonnull List<ProfileStatement> statements) {
        this.statements = Utils.checkNotNull(statements, "statements");
        return this;
    }

    /**
     * Values worth searching next, each held by a linked record.
     */
    public WrittenProfile withLeads(@Nonnull List<ProfileLead> leads) {
        this.leads = Utils.checkNotNull(leads, "leads");
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
        WrittenProfile other = (WrittenProfile) o;
        return Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.investigationId, other.investigationId)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.queuedAt, other.queuedAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.summary, other.summary)
                && Utils.enhancedDeepEquals(this.statements, other.statements)
                && Utils.enhancedDeepEquals(this.leads, other.leads);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                status, investigationId, errorCode, queuedAt, finishedAt, summary, statements, leads);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WrittenProfile.class,
                "status",
                status,
                "investigationId",
                investigationId,
                "errorCode",
                errorCode,
                "queuedAt",
                queuedAt,
                "finishedAt",
                finishedAt,
                "summary",
                summary,
                "statements",
                statements,
                "leads",
                leads);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private WrittenProfileStatus status;

        private String investigationId;

        private WrittenProfileErrorCode errorCode;

        private String queuedAt;

        private String finishedAt;

        private String summary;

        private List<ProfileStatement> statements;

        private List<ProfileLead> leads;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * queued: the profile is being written. written: summary, statements and leads hold it. failed: it
         * could not be written; error_code says why and no content is returned.
         */
        public Builder status(@Nonnull WrittenProfileStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * The investigation whose evidence the profile is written from.
         */
        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        /**
         * Present only when status is failed. no_writer: no profile writer serves this environment. refused:
         * the model refused.
         *
         * <p>not_written: the answer was not a profile, or no statement survived grounding. uncited_statement: a
         * statement or lead cited a record the person does not hold. not_answered: the writer did not answer.
         *
         * <p>worker_attempts_exhausted: the worker stopped retrying.
         */
        public Builder errorCode(@Nullable WrittenProfileErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder queuedAt(@Nonnull String queuedAt) {
            this.queuedAt = Utils.checkNotNull(queuedAt, "queuedAt");
            return this;
        }

        public Builder finishedAt(@Nullable String finishedAt) {
            this.finishedAt = finishedAt;
            return this;
        }

        /**
         * Present when written: a few plain sentences with the most useful picture first.
         */
        public Builder summary(@Nullable String summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Each fact or inference with its certainty and the evidence it cites. Statements that cited evidence
         * the writer was not sent are dropped before storage.
         */
        public Builder statements(@Nonnull List<ProfileStatement> statements) {
            this.statements = Utils.checkNotNull(statements, "statements");
            return this;
        }

        /**
         * Values worth searching next, each held by a linked record.
         */
        public Builder leads(@Nonnull List<ProfileLead> leads) {
            this.leads = Utils.checkNotNull(leads, "leads");
            return this;
        }

        public WrittenProfile build() {
            return new WrittenProfile(
                    status, investigationId, errorCode, queuedAt, finishedAt, summary, statements, leads);
        }
    }
}
