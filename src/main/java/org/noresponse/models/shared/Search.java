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

/**
 * Search
 *
 * <p>One search a person started on a value of one of a person's records. Nothing searches again by
 * itself.
 */
public class Search {

    @JsonProperty("search_id")
    private String searchId;

    /**
     * The record whose value led to the search.
     */
    @JsonProperty("record_id")
    private String recordId;

    /**
     * The field of that record that holds the value.
     */
    @JsonProperty("field")
    private String field;

    /**
     * The one breach table searched, when the search was of one table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("table")
    private String table;

    /**
     * The kind of the value searched.
     */
    @JsonProperty("kind")
    private SearchKind kind;

    /**
     * The value searched: the field's cleaned value, else as the source wrote it.
     */
    @JsonProperty("value")
    private String value;

    /**
     * A password or hash search is counting, then counted: rows says how many breach rows hold the value,
     * and nothing runs until a person runs it (POST .../searches/{search_id}/run). Any other search is
     * queued, then running. completed: every source it asked answered.
     *
     * <p>partial: some did not. failed: none answered or none could be asked, which says nothing about
     * whether the value is held anywhere.
     */
    @JsonProperty("status")
    private SearchStatus status;

    /**
     * How many breach rows hold the password or hash, counted before the search runs.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("rows")
    private Long rows;

    /**
     * How many sources the search asked: the breach search and the account and phone lookups that take the
     * value's kind.
     */
    @JsonProperty("sources")
    private long sources;

    /**
     * How many of them gave a definite answer, found or not found.
     */
    @JsonProperty("answered")
    private long answered;

    /**
     * How many records the search found. They are among the person report's lookup records, each match
     * naming this search.
     */
    @JsonProperty("found")
    private long found;

    /**
     * What the search may spend, in US cents, held against the environment's daily screening cap while it
     * runs.
     */
    @JsonProperty("estimated_spend")
    private long estimatedSpend;

    /**
     * What the search spent, in US cents, metered as screening work.
     */
    @JsonProperty("spent")
    private long spent;

    /**
     * Why a failed search found nothing: its sources did not answer, none could be asked, or it could not
     * run before its attempts were spent.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private ErrorCode errorCode;

    /**
     * Who started the search.
     */
    @JsonProperty("requested_by")
    private String requestedBy;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonCreator
    public Search(
            @JsonProperty("search_id") @Nonnull String searchId,
            @JsonProperty("record_id") @Nonnull String recordId,
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("table") @Nullable String table,
            @JsonProperty("kind") @Nonnull SearchKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("status") @Nonnull SearchStatus status,
            @JsonProperty("rows") @Nullable Long rows,
            @JsonProperty("sources") long sources,
            @JsonProperty("answered") long answered,
            @JsonProperty("found") long found,
            @JsonProperty("estimated_spend") long estimatedSpend,
            @JsonProperty("spent") long spent,
            @JsonProperty("error_code") @Nullable ErrorCode errorCode,
            @JsonProperty("requested_by") @Nonnull String requestedBy,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("finished_at") @Nullable String finishedAt) {
        this.searchId = Optional.ofNullable(searchId)
                .orElseThrow(() -> new IllegalArgumentException("searchId cannot be null"));
        this.recordId = Optional.ofNullable(recordId)
                .orElseThrow(() -> new IllegalArgumentException("recordId cannot be null"));
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.table = table;
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.rows = rows;
        this.sources = sources;
        this.answered = answered;
        this.found = found;
        this.estimatedSpend = estimatedSpend;
        this.spent = spent;
        this.errorCode = errorCode;
        this.requestedBy = Optional.ofNullable(requestedBy)
                .orElseThrow(() -> new IllegalArgumentException("requestedBy cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.finishedAt = finishedAt;
    }

    public Search(
            @Nonnull String searchId,
            @Nonnull String recordId,
            @Nonnull String field,
            @Nonnull SearchKind kind,
            @Nonnull String value,
            @Nonnull SearchStatus status,
            long sources,
            long answered,
            long found,
            long estimatedSpend,
            long spent,
            @Nonnull String requestedBy,
            @Nonnull String createdAt) {
        this(
                searchId,
                recordId,
                field,
                null,
                kind,
                value,
                status,
                null,
                sources,
                answered,
                found,
                estimatedSpend,
                spent,
                null,
                requestedBy,
                createdAt,
                null);
    }

    public String searchId() {
        return this.searchId;
    }

    /**
     * The record whose value led to the search.
     */
    public String recordId() {
        return this.recordId;
    }

    /**
     * The field of that record that holds the value.
     */
    public String field() {
        return this.field;
    }

    /**
     * The one breach table searched, when the search was of one table.
     */
    public Optional<String> table() {
        return Optional.ofNullable(this.table);
    }

    /**
     * The kind of the value searched.
     */
    public SearchKind kind() {
        return this.kind;
    }

    /**
     * The value searched: the field's cleaned value, else as the source wrote it.
     */
    public String value() {
        return this.value;
    }

    /**
     * A password or hash search is counting, then counted: rows says how many breach rows hold the value,
     * and nothing runs until a person runs it (POST .../searches/{search_id}/run). Any other search is
     * queued, then running. completed: every source it asked answered.
     *
     * <p>partial: some did not. failed: none answered or none could be asked, which says nothing about
     * whether the value is held anywhere.
     */
    public SearchStatus status() {
        return this.status;
    }

    /**
     * How many breach rows hold the password or hash, counted before the search runs.
     */
    public Optional<Long> rows() {
        return Optional.ofNullable(this.rows);
    }

    /**
     * How many sources the search asked: the breach search and the account and phone lookups that take the
     * value's kind.
     */
    public long sources() {
        return this.sources;
    }

    /**
     * How many of them gave a definite answer, found or not found.
     */
    public long answered() {
        return this.answered;
    }

    /**
     * How many records the search found. They are among the person report's lookup records, each match
     * naming this search.
     */
    public long found() {
        return this.found;
    }

    /**
     * What the search may spend, in US cents, held against the environment's daily screening cap while it
     * runs.
     */
    public long estimatedSpend() {
        return this.estimatedSpend;
    }

    /**
     * What the search spent, in US cents, metered as screening work.
     */
    public long spent() {
        return this.spent;
    }

    /**
     * Why a failed search found nothing: its sources did not answer, none could be asked, or it could not
     * run before its attempts were spent.
     */
    public Optional<ErrorCode> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    /**
     * Who started the search.
     */
    public String requestedBy() {
        return this.requestedBy;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public Optional<String> finishedAt() {
        return Optional.ofNullable(this.finishedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Search withSearchId(@Nonnull String searchId) {
        this.searchId = Utils.checkNotNull(searchId, "searchId");
        return this;
    }

    /**
     * The record whose value led to the search.
     */
    public Search withRecordId(@Nonnull String recordId) {
        this.recordId = Utils.checkNotNull(recordId, "recordId");
        return this;
    }

    /**
     * The field of that record that holds the value.
     */
    public Search withField(@Nonnull String field) {
        this.field = Utils.checkNotNull(field, "field");
        return this;
    }

    /**
     * The one breach table searched, when the search was of one table.
     */
    public Search withTable(@Nullable String table) {
        this.table = table;
        return this;
    }

    /**
     * The kind of the value searched.
     */
    public Search withKind(@Nonnull SearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The value searched: the field's cleaned value, else as the source wrote it.
     */
    public Search withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    /**
     * A password or hash search is counting, then counted: rows says how many breach rows hold the value,
     * and nothing runs until a person runs it (POST .../searches/{search_id}/run). Any other search is
     * queued, then running. completed: every source it asked answered.
     *
     * <p>partial: some did not. failed: none answered or none could be asked, which says nothing about
     * whether the value is held anywhere.
     */
    public Search withStatus(@Nonnull SearchStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * How many breach rows hold the password or hash, counted before the search runs.
     */
    public Search withRows(@Nullable Long rows) {
        this.rows = rows;
        return this;
    }

    /**
     * How many sources the search asked: the breach search and the account and phone lookups that take the
     * value's kind.
     */
    public Search withSources(long sources) {
        this.sources = sources;
        return this;
    }

    /**
     * How many of them gave a definite answer, found or not found.
     */
    public Search withAnswered(long answered) {
        this.answered = answered;
        return this;
    }

    /**
     * How many records the search found. They are among the person report's lookup records, each match
     * naming this search.
     */
    public Search withFound(long found) {
        this.found = found;
        return this;
    }

    /**
     * What the search may spend, in US cents, held against the environment's daily screening cap while it
     * runs.
     */
    public Search withEstimatedSpend(long estimatedSpend) {
        this.estimatedSpend = estimatedSpend;
        return this;
    }

    /**
     * What the search spent, in US cents, metered as screening work.
     */
    public Search withSpent(long spent) {
        this.spent = spent;
        return this;
    }

    /**
     * Why a failed search found nothing: its sources did not answer, none could be asked, or it could not
     * run before its attempts were spent.
     */
    public Search withErrorCode(@Nullable ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * Who started the search.
     */
    public Search withRequestedBy(@Nonnull String requestedBy) {
        this.requestedBy = Utils.checkNotNull(requestedBy, "requestedBy");
        return this;
    }

    public Search withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public Search withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = finishedAt;
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
        Search other = (Search) o;
        return Utils.enhancedDeepEquals(this.searchId, other.searchId)
                && Utils.enhancedDeepEquals(this.recordId, other.recordId)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.table, other.table)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.rows, other.rows)
                && Utils.enhancedDeepEquals(this.sources, other.sources)
                && Utils.enhancedDeepEquals(this.answered, other.answered)
                && Utils.enhancedDeepEquals(this.found, other.found)
                && Utils.enhancedDeepEquals(this.estimatedSpend, other.estimatedSpend)
                && Utils.enhancedDeepEquals(this.spent, other.spent)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.requestedBy, other.requestedBy)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                searchId,
                recordId,
                field,
                table,
                kind,
                value,
                status,
                rows,
                sources,
                answered,
                found,
                estimatedSpend,
                spent,
                errorCode,
                requestedBy,
                createdAt,
                finishedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Search.class,
                "searchId",
                searchId,
                "recordId",
                recordId,
                "field",
                field,
                "table",
                table,
                "kind",
                kind,
                "value",
                value,
                "status",
                status,
                "rows",
                rows,
                "sources",
                sources,
                "answered",
                answered,
                "found",
                found,
                "estimatedSpend",
                estimatedSpend,
                "spent",
                spent,
                "errorCode",
                errorCode,
                "requestedBy",
                requestedBy,
                "createdAt",
                createdAt,
                "finishedAt",
                finishedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String searchId;

        private String recordId;

        private String field;

        private String table;

        private SearchKind kind;

        private String value;

        private SearchStatus status;

        private Long rows;

        private long sources;

        private long answered;

        private long found;

        private long estimatedSpend;

        private long spent;

        private ErrorCode errorCode;

        private String requestedBy;

        private String createdAt;

        private String finishedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder searchId(@Nonnull String searchId) {
            this.searchId = Utils.checkNotNull(searchId, "searchId");
            return this;
        }

        /**
         * The record whose value led to the search.
         */
        public Builder recordId(@Nonnull String recordId) {
            this.recordId = Utils.checkNotNull(recordId, "recordId");
            return this;
        }

        /**
         * The field of that record that holds the value.
         */
        public Builder field(@Nonnull String field) {
            this.field = Utils.checkNotNull(field, "field");
            return this;
        }

        /**
         * The one breach table searched, when the search was of one table.
         */
        public Builder table(@Nullable String table) {
            this.table = table;
            return this;
        }

        /**
         * The kind of the value searched.
         */
        public Builder kind(@Nonnull SearchKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The value searched: the field's cleaned value, else as the source wrote it.
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        /**
         * A password or hash search is counting, then counted: rows says how many breach rows hold the value,
         * and nothing runs until a person runs it (POST .../searches/{search_id}/run). Any other search is
         * queued, then running. completed: every source it asked answered.
         *
         * <p>partial: some did not. failed: none answered or none could be asked, which says nothing about
         * whether the value is held anywhere.
         */
        public Builder status(@Nonnull SearchStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * How many breach rows hold the password or hash, counted before the search runs.
         */
        public Builder rows(@Nullable Long rows) {
            this.rows = rows;
            return this;
        }

        /**
         * How many sources the search asked: the breach search and the account and phone lookups that take the
         * value's kind.
         */
        public Builder sources(long sources) {
            this.sources = sources;
            return this;
        }

        /**
         * How many of them gave a definite answer, found or not found.
         */
        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        /**
         * How many records the search found. They are among the person report's lookup records, each match
         * naming this search.
         */
        public Builder found(long found) {
            this.found = found;
            return this;
        }

        /**
         * What the search may spend, in US cents, held against the environment's daily screening cap while it
         * runs.
         */
        public Builder estimatedSpend(long estimatedSpend) {
            this.estimatedSpend = estimatedSpend;
            return this;
        }

        /**
         * What the search spent, in US cents, metered as screening work.
         */
        public Builder spent(long spent) {
            this.spent = spent;
            return this;
        }

        /**
         * Why a failed search found nothing: its sources did not answer, none could be asked, or it could not
         * run before its attempts were spent.
         */
        public Builder errorCode(@Nullable ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * Who started the search.
         */
        public Builder requestedBy(@Nonnull String requestedBy) {
            this.requestedBy = Utils.checkNotNull(requestedBy, "requestedBy");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder finishedAt(@Nullable String finishedAt) {
            this.finishedAt = finishedAt;
            return this;
        }

        public Search build() {
            return new Search(
                    searchId,
                    recordId,
                    field,
                    table,
                    kind,
                    value,
                    status,
                    rows,
                    sources,
                    answered,
                    found,
                    estimatedSpend,
                    spent,
                    errorCode,
                    requestedBy,
                    createdAt,
                    finishedAt);
        }
    }
}
