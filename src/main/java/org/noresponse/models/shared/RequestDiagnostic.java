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
 * RequestDiagnostic
 *
 * <p>One authenticated HTTP attempt. An attempt id is independent of the caller correlation id. No raw
 * body, path parameter, credential or source payload is retained.
 *
 * <p>write_complete means the server wrote/flushed its response, not proof the client processed it.
 * Collection is bounded best effort; ii_signup_request_diagnostic_failures_total exposes gaps.
 * Retained for seven days or the shorter environment retention.
 */
public class RequestDiagnostic {

    @JsonProperty("id")
    private String id;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("route")
    private String route;

    @JsonProperty("http_status")
    private long httpStatus;

    @JsonProperty("duration_ms")
    private long durationMs;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("idempotent_replayed")
    private boolean idempotentReplayed;

    @JsonProperty("write_complete")
    private boolean writeComplete;

    /**
     * The decision prepared for one HTTP attempt. Its account field preserves the account decision
     * returned for that attempt, even after subsequent changes.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("decision")
    private DecisionReceipt decision;

    @JsonCreator
    public RequestDiagnostic(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("request_id") @Nonnull String requestId,
            @JsonProperty("route") @Nonnull String route,
            @JsonProperty("http_status") long httpStatus,
            @JsonProperty("duration_ms") long durationMs,
            @JsonProperty("started_at") @Nonnull String startedAt,
            @JsonProperty("completed_at") @Nonnull String completedAt,
            @JsonProperty("expires_at") @Nonnull String expiresAt,
            @JsonProperty("error_code") @Nullable String errorCode,
            @JsonProperty("idempotent_replayed") boolean idempotentReplayed,
            @JsonProperty("write_complete") boolean writeComplete,
            @JsonProperty("decision") @Nullable DecisionReceipt decision) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.requestId = Optional.ofNullable(requestId)
                .orElseThrow(() -> new IllegalArgumentException("requestId cannot be null"));
        this.route = Optional.ofNullable(route).orElseThrow(() -> new IllegalArgumentException("route cannot be null"));
        this.httpStatus = httpStatus;
        this.durationMs = durationMs;
        this.startedAt = Optional.ofNullable(startedAt)
                .orElseThrow(() -> new IllegalArgumentException("startedAt cannot be null"));
        this.completedAt = Optional.ofNullable(completedAt)
                .orElseThrow(() -> new IllegalArgumentException("completedAt cannot be null"));
        this.expiresAt = Optional.ofNullable(expiresAt)
                .orElseThrow(() -> new IllegalArgumentException("expiresAt cannot be null"));
        this.errorCode = errorCode;
        this.idempotentReplayed = idempotentReplayed;
        this.writeComplete = writeComplete;
        this.decision = decision;
    }

    public RequestDiagnostic(
            @Nonnull String id,
            @Nonnull String requestId,
            @Nonnull String route,
            long httpStatus,
            long durationMs,
            @Nonnull String startedAt,
            @Nonnull String completedAt,
            @Nonnull String expiresAt,
            boolean idempotentReplayed,
            boolean writeComplete) {
        this(
                id,
                requestId,
                route,
                httpStatus,
                durationMs,
                startedAt,
                completedAt,
                expiresAt,
                null,
                idempotentReplayed,
                writeComplete,
                null);
    }

    public String id() {
        return this.id;
    }

    public String requestId() {
        return this.requestId;
    }

    public String route() {
        return this.route;
    }

    public long httpStatus() {
        return this.httpStatus;
    }

    public long durationMs() {
        return this.durationMs;
    }

    public String startedAt() {
        return this.startedAt;
    }

    public String completedAt() {
        return this.completedAt;
    }

    public String expiresAt() {
        return this.expiresAt;
    }

    public Optional<String> errorCode() {
        return Optional.ofNullable(this.errorCode);
    }

    public boolean idempotentReplayed() {
        return this.idempotentReplayed;
    }

    public boolean writeComplete() {
        return this.writeComplete;
    }

    /**
     * The decision prepared for one HTTP attempt. Its account field preserves the account decision
     * returned for that attempt, even after subsequent changes.
     */
    public Optional<DecisionReceipt> decision() {
        return Optional.ofNullable(this.decision);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RequestDiagnostic withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public RequestDiagnostic withRequestId(@Nonnull String requestId) {
        this.requestId = Utils.checkNotNull(requestId, "requestId");
        return this;
    }

    public RequestDiagnostic withRoute(@Nonnull String route) {
        this.route = Utils.checkNotNull(route, "route");
        return this;
    }

    public RequestDiagnostic withHttpStatus(long httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public RequestDiagnostic withDurationMs(long durationMs) {
        this.durationMs = durationMs;
        return this;
    }

    public RequestDiagnostic withStartedAt(@Nonnull String startedAt) {
        this.startedAt = Utils.checkNotNull(startedAt, "startedAt");
        return this;
    }

    public RequestDiagnostic withCompletedAt(@Nonnull String completedAt) {
        this.completedAt = Utils.checkNotNull(completedAt, "completedAt");
        return this;
    }

    public RequestDiagnostic withExpiresAt(@Nonnull String expiresAt) {
        this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
        return this;
    }

    public RequestDiagnostic withErrorCode(@Nullable String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public RequestDiagnostic withIdempotentReplayed(boolean idempotentReplayed) {
        this.idempotentReplayed = idempotentReplayed;
        return this;
    }

    public RequestDiagnostic withWriteComplete(boolean writeComplete) {
        this.writeComplete = writeComplete;
        return this;
    }

    /**
     * The decision prepared for one HTTP attempt. Its account field preserves the account decision
     * returned for that attempt, even after subsequent changes.
     */
    public RequestDiagnostic withDecision(@Nullable DecisionReceipt decision) {
        this.decision = decision;
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
        RequestDiagnostic other = (RequestDiagnostic) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.requestId, other.requestId)
                && Utils.enhancedDeepEquals(this.route, other.route)
                && Utils.enhancedDeepEquals(this.httpStatus, other.httpStatus)
                && Utils.enhancedDeepEquals(this.durationMs, other.durationMs)
                && Utils.enhancedDeepEquals(this.startedAt, other.startedAt)
                && Utils.enhancedDeepEquals(this.completedAt, other.completedAt)
                && Utils.enhancedDeepEquals(this.expiresAt, other.expiresAt)
                && Utils.enhancedDeepEquals(this.errorCode, other.errorCode)
                && Utils.enhancedDeepEquals(this.idempotentReplayed, other.idempotentReplayed)
                && Utils.enhancedDeepEquals(this.writeComplete, other.writeComplete)
                && Utils.enhancedDeepEquals(this.decision, other.decision);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id,
                requestId,
                route,
                httpStatus,
                durationMs,
                startedAt,
                completedAt,
                expiresAt,
                errorCode,
                idempotentReplayed,
                writeComplete,
                decision);
    }

    @Override
    public String toString() {
        return Utils.toString(
                RequestDiagnostic.class,
                "id",
                id,
                "requestId",
                requestId,
                "route",
                route,
                "httpStatus",
                httpStatus,
                "durationMs",
                durationMs,
                "startedAt",
                startedAt,
                "completedAt",
                completedAt,
                "expiresAt",
                expiresAt,
                "errorCode",
                errorCode,
                "idempotentReplayed",
                idempotentReplayed,
                "writeComplete",
                writeComplete,
                "decision",
                decision);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String requestId;

        private String route;

        private long httpStatus;

        private long durationMs;

        private String startedAt;

        private String completedAt;

        private String expiresAt;

        private String errorCode;

        private boolean idempotentReplayed;

        private boolean writeComplete;

        private DecisionReceipt decision;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder requestId(@Nonnull String requestId) {
            this.requestId = Utils.checkNotNull(requestId, "requestId");
            return this;
        }

        public Builder route(@Nonnull String route) {
            this.route = Utils.checkNotNull(route, "route");
            return this;
        }

        public Builder httpStatus(long httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder durationMs(long durationMs) {
            this.durationMs = durationMs;
            return this;
        }

        public Builder startedAt(@Nonnull String startedAt) {
            this.startedAt = Utils.checkNotNull(startedAt, "startedAt");
            return this;
        }

        public Builder completedAt(@Nonnull String completedAt) {
            this.completedAt = Utils.checkNotNull(completedAt, "completedAt");
            return this;
        }

        public Builder expiresAt(@Nonnull String expiresAt) {
            this.expiresAt = Utils.checkNotNull(expiresAt, "expiresAt");
            return this;
        }

        public Builder errorCode(@Nullable String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder idempotentReplayed(boolean idempotentReplayed) {
            this.idempotentReplayed = idempotentReplayed;
            return this;
        }

        public Builder writeComplete(boolean writeComplete) {
            this.writeComplete = writeComplete;
            return this;
        }

        /**
         * The decision prepared for one HTTP attempt. Its account field preserves the account decision
         * returned for that attempt, even after subsequent changes.
         */
        public Builder decision(@Nullable DecisionReceipt decision) {
            this.decision = decision;
            return this;
        }

        public RequestDiagnostic build() {
            return new RequestDiagnostic(
                    id,
                    requestId,
                    route,
                    httpStatus,
                    durationMs,
                    startedAt,
                    completedAt,
                    expiresAt,
                    errorCode,
                    idempotentReplayed,
                    writeComplete,
                    decision);
        }
    }
}
