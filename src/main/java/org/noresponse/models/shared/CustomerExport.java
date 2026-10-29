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
 * CustomerExport
 *
 * <p>Request an asynchronous customer export.
 */
public class CustomerExport {

    @JsonProperty("id")
    private String id;

    @JsonProperty("kind")
    private CustomerExportRequestKind kind;

    @JsonProperty("environment")
    private Environment environment;

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    @JsonProperty("response_mode")
    private ResponseMode responseMode;

    @JsonProperty("status")
    private CustomerExportStatus status;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("snapshot_at")
    private String snapshotAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("finished_at")
    private String finishedAt;

    @JsonProperty("records")
    private long records;

    @JsonProperty("bytes")
    private long bytes;

    @JsonProperty("chunks")
    private long chunks;

    @JsonProperty("attempts")
    private long attempts;

    @JsonProperty("generation")
    private long generation;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("request_sha256")
    private String requestSha256;

    @JsonCreator
    public CustomerExport(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("kind") @Nonnull CustomerExportRequestKind kind,
            @JsonProperty("environment") @Nonnull Environment environment,
            @JsonProperty("response_mode") @Nonnull ResponseMode responseMode,
            @JsonProperty("status") @Nonnull CustomerExportStatus status,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("snapshot_at") @Nullable String snapshotAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("finished_at") @Nullable String finishedAt,
            @JsonProperty("records") long records,
            @JsonProperty("bytes") long bytes,
            @JsonProperty("chunks") long chunks,
            @JsonProperty("attempts") long attempts,
            @JsonProperty("generation") long generation,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("request_sha256") @Nonnull String requestSha256) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.responseMode = Optional.ofNullable(responseMode)
                .orElseThrow(() -> new IllegalArgumentException("responseMode cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.snapshotAt = snapshotAt;
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.finishedAt = finishedAt;
        this.records = records;
        this.bytes = bytes;
        this.chunks = chunks;
        this.attempts = attempts;
        this.generation = generation;
        this.errorCode = errorCode;
        this.requestSha256 = Optional.ofNullable(requestSha256)
                .orElseThrow(() -> new IllegalArgumentException("requestSha256 cannot be null"));
    }

    public CustomerExport(
            @Nonnull String id,
            @Nonnull CustomerExportRequestKind kind,
            @Nonnull Environment environment,
            @Nonnull ResponseMode responseMode,
            @Nonnull CustomerExportStatus status,
            @Nonnull String createdAt,
            @Nonnull String expiresAt,
            long records,
            long bytes,
            long chunks,
            long attempts,
            long generation,
            @Nonnull String requestSha256) {
        this(
                id,
                kind,
                environment,
                responseMode,
                status,
                createdAt,
                null,
                expiresAt,
                null,
                records,
                bytes,
                chunks,
                attempts,
                generation,
                null,
                requestSha256);
    }

    public String id() {
        return this.id;
    }

    public CustomerExportRequestKind kind() {
        return this.kind;
    }

    public Environment environment() {
        return this.environment;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public ResponseMode responseMode() {
        return this.responseMode;
    }

    public CustomerExportStatus status() {
        return this.status;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public Optional<String> snapshotAt() {
        return Optional.ofNullable(this.snapshotAt);
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    public Optional<String> finishedAt() {
        return Optional.ofNullable(this.finishedAt);
    }

    public long records() {
        return this.records;
    }

    public long bytes() {
        return this.bytes;
    }

    public long chunks() {
        return this.chunks;
    }

    public long attempts() {
        return this.attempts;
    }

    public long generation() {
        return this.generation;
    }

    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public String requestSha256() {
        return this.requestSha256;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerExport withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public CustomerExport withKind(@Nonnull CustomerExportRequestKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public CustomerExport withEnvironment(@Nonnull Environment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    /**
     * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
     * the key has detailed scope. There is no raw mode.
     */
    public CustomerExport withResponseMode(@Nonnull ResponseMode responseMode) {
        this.responseMode = Utils.checkNotNull(responseMode, "responseMode");
        return this;
    }

    public CustomerExport withStatus(@Nonnull CustomerExportStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public CustomerExport withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public CustomerExport withSnapshotAt(@Nullable String snapshotAt) {
        this.snapshotAt = snapshotAt;
        return this;
    }

    public CustomerExport withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    public CustomerExport withFinishedAt(@Nullable String finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    public CustomerExport withRecords(long records) {
        this.records = records;
        return this;
    }

    public CustomerExport withBytes(long bytes) {
        this.bytes = bytes;
        return this;
    }

    public CustomerExport withChunks(long chunks) {
        this.chunks = chunks;
        return this;
    }

    public CustomerExport withAttempts(long attempts) {
        this.attempts = attempts;
        return this;
    }

    public CustomerExport withGeneration(long generation) {
        this.generation = generation;
        return this;
    }

    public CustomerExport withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public CustomerExport withRequestSha256(@Nonnull String requestSha256) {
        this.requestSha256 = Utils.checkNotNull(requestSha256, "requestSha256");
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
        CustomerExport other = (CustomerExport) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.snapshotAt, other.snapshotAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.finishedAt, other.finishedAt)
                && Utils.enhancedDeepEquals(this.records, other.records)
                && Utils.enhancedDeepEquals(this.bytes, other.bytes)
                && Utils.enhancedDeepEquals(this.chunks, other.chunks)
                && Utils.enhancedDeepEquals(this.attempts, other.attempts)
                && Utils.enhancedDeepEquals(this.generation, other.generation)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.requestSha256, other.requestSha256);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                kind,
                environment,
                responseMode,
                status,
                createdAt,
                snapshotAt,
                expiresAt,
                finishedAt,
                records,
                bytes,
                chunks,
                attempts,
                generation,
                errorCode,
                requestSha256);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CustomerExport.class,
                "id",
                id,
                "kind",
                kind,
                "environment",
                environment,
                "responseMode",
                responseMode,
                "status",
                status,
                "createdAt",
                createdAt,
                "snapshotAt",
                snapshotAt,
                "expiresAt",
                expiresAt,
                "finishedAt",
                finishedAt,
                "records",
                records,
                "bytes",
                bytes,
                "chunks",
                chunks,
                "attempts",
                attempts,
                "generation",
                generation,
                "errorCode",
                errorCode,
                "requestSha256",
                requestSha256);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private CustomerExportRequestKind kind;

        private Environment environment;

        private ResponseMode responseMode;

        private CustomerExportStatus status;

        private String createdAt;

        private String snapshotAt;

        private String expiresAt;

        private String finishedAt;

        private long records;

        private long bytes;

        private long chunks;

        private long attempts;

        private long generation;

        private String errorCode;

        private String requestSha256;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder kind(@Nonnull CustomerExportRequestKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder environment(@Nonnull Environment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        /**
         * standard returns the consolidated risk response; detailed adds merged provider-neutral facts when
         * the key has detailed scope. There is no raw mode.
         */
        public Builder responseMode(@Nonnull ResponseMode responseMode) {
            this.responseMode = Utils.checkNotNull(responseMode, "responseMode");
            return this;
        }

        public Builder status(@Nonnull CustomerExportStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder snapshotAt(@Nullable String snapshotAt) {
            this.snapshotAt = snapshotAt;
            return this;
        }

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public Builder finishedAt(@Nullable String finishedAt) {
            this.finishedAt = finishedAt;
            return this;
        }

        public Builder records(long records) {
            this.records = records;
            return this;
        }

        public Builder bytes(long bytes) {
            this.bytes = bytes;
            return this;
        }

        public Builder chunks(long chunks) {
            this.chunks = chunks;
            return this;
        }

        public Builder attempts(long attempts) {
            this.attempts = attempts;
            return this;
        }

        public Builder generation(long generation) {
            this.generation = generation;
            return this;
        }

        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder requestSha256(@Nonnull String requestSha256) {
            this.requestSha256 = Utils.checkNotNull(requestSha256, "requestSha256");
            return this;
        }

        public CustomerExport build() {
            return new CustomerExport(
                    id,
                    kind,
                    environment,
                    responseMode,
                    status,
                    createdAt,
                    snapshotAt,
                    expiresAt,
                    finishedAt,
                    records,
                    bytes,
                    chunks,
                    attempts,
                    generation,
                    errorCode,
                    requestSha256);
        }
    }
}
