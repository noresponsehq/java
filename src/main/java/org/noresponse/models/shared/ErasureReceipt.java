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

/**
 * ErasureReceipt
 *
 * <p>Acknowledged propagation of a retained erasure record in the calling environment. Pending means the
 * target has not acknowledged completion. Completion covers these serving-system targets only; backup
 * and point-in-time recovery copies expire according to their own retention policy.
 *
 * <p>This receipt contains no subject id or digest. Workspace teardown revokes customer credentials, so
 * obtain required receipts before teardown.
 */
public class ErasureReceipt {
    /**
     * Opaque, server-issued erasure receipt id. It contains no subject identifier.
     */
    @JsonProperty("id")
    private String id;

    @JsonProperty("kind")
    private ErasureReceiptKind kind;

    @JsonProperty("status")
    private ListChangeState status;

    @JsonProperty("assessments_deleted")
    private long assessmentsDeleted;

    @JsonProperty("requested_at")
    private String requestedAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("targets")
    private List<ErasureReceiptTarget> targets;

    @JsonProperty("backup_policy")
    private String backupPolicy;

    @JsonCreator
    public ErasureReceipt(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("kind") @Nonnull ErasureReceiptKind kind,
            @JsonProperty("status") @Nonnull ListChangeState status,
            @JsonProperty("assessments_deleted") long assessmentsDeleted,
            @JsonProperty("requested_at") @Nonnull String requestedAt,
            @JsonProperty("completed_at") @Nullable String completedAt,
            @JsonProperty("targets") @Nonnull List<ErasureReceiptTarget> targets,
            @JsonProperty("backup_policy") @Nonnull String backupPolicy) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.assessmentsDeleted = assessmentsDeleted;
        this.requestedAt = Optional.ofNullable(requestedAt)
                .orElseThrow(() -> new IllegalArgumentException("requestedAt cannot be null"));
        this.completedAt = completedAt;
        this.targets =
                Optional.ofNullable(targets).orElseThrow(() -> new IllegalArgumentException("targets cannot be null"));
        this.backupPolicy = Optional.ofNullable(backupPolicy)
                .orElseThrow(() -> new IllegalArgumentException("backupPolicy cannot be null"));
    }

    public ErasureReceipt(
            @Nonnull String id,
            @Nonnull ErasureReceiptKind kind,
            @Nonnull ListChangeState status,
            long assessmentsDeleted,
            @Nonnull String requestedAt,
            @Nonnull List<ErasureReceiptTarget> targets,
            @Nonnull String backupPolicy) {
        this(id, kind, status, assessmentsDeleted, requestedAt, null, targets, backupPolicy);
    }

    /**
     * Opaque, server-issued erasure receipt id. It contains no subject identifier.
     */
    public String id() {
        return this.id;
    }

    public ErasureReceiptKind kind() {
        return this.kind;
    }

    public ListChangeState status() {
        return this.status;
    }

    public long assessmentsDeleted() {
        return this.assessmentsDeleted;
    }

    public String requestedAt() {
        return this.requestedAt;
    }

    public Optional<String> completedAt() {
        return Optional.ofNullable(this.completedAt);
    }

    public List<ErasureReceiptTarget> targets() {
        return this.targets;
    }

    public String backupPolicy() {
        return this.backupPolicy;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Opaque, server-issued erasure receipt id. It contains no subject identifier.
     */
    public ErasureReceipt withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ErasureReceipt withKind(@Nonnull ErasureReceiptKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ErasureReceipt withStatus(@Nonnull ListChangeState status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public ErasureReceipt withAssessmentsDeleted(long assessmentsDeleted) {
        this.assessmentsDeleted = assessmentsDeleted;
        return this;
    }

    public ErasureReceipt withRequestedAt(@Nonnull String requestedAt) {
        this.requestedAt = Utils.checkNotNull(requestedAt, "requestedAt");
        return this;
    }

    public ErasureReceipt withCompletedAt(@Nullable String completedAt) {
        this.completedAt = completedAt;
        return this;
    }

    public ErasureReceipt withTargets(@Nonnull List<ErasureReceiptTarget> targets) {
        this.targets = Utils.checkNotNull(targets, "targets");
        return this;
    }

    public ErasureReceipt withBackupPolicy(@Nonnull String backupPolicy) {
        this.backupPolicy = Utils.checkNotNull(backupPolicy, "backupPolicy");
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
        ErasureReceipt other = (ErasureReceipt) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.assessmentsDeleted, other.assessmentsDeleted)
                && Utils.enhancedDeepEquals(this.requestedAt, other.requestedAt)
                && Utils.enhancedDeepEquals(this.completedAt, other.completedAt)
                && Utils.enhancedDeepEquals(this.targets, other.targets)
                && Utils.enhancedDeepEquals(this.backupPolicy, other.backupPolicy);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, kind, status, assessmentsDeleted, requestedAt, completedAt, targets, backupPolicy);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ErasureReceipt.class,
                "id",
                id,
                "kind",
                kind,
                "status",
                status,
                "assessmentsDeleted",
                assessmentsDeleted,
                "requestedAt",
                requestedAt,
                "completedAt",
                completedAt,
                "targets",
                targets,
                "backupPolicy",
                backupPolicy);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private ErasureReceiptKind kind;

        private ListChangeState status;

        private long assessmentsDeleted;

        private String requestedAt;

        private String completedAt;

        private List<ErasureReceiptTarget> targets;

        private String backupPolicy;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Opaque, server-issued erasure receipt id. It contains no subject identifier.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder kind(@Nonnull ErasureReceiptKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder status(@Nonnull ListChangeState status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder assessmentsDeleted(long assessmentsDeleted) {
            this.assessmentsDeleted = assessmentsDeleted;
            return this;
        }

        public Builder requestedAt(@Nonnull String requestedAt) {
            this.requestedAt = Utils.checkNotNull(requestedAt, "requestedAt");
            return this;
        }

        public Builder completedAt(@Nullable String completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder targets(@Nonnull List<ErasureReceiptTarget> targets) {
            this.targets = Utils.checkNotNull(targets, "targets");
            return this;
        }

        public Builder backupPolicy(@Nonnull String backupPolicy) {
            this.backupPolicy = Utils.checkNotNull(backupPolicy, "backupPolicy");
            return this;
        }

        public ErasureReceipt build() {
            return new ErasureReceipt(
                    id, kind, status, assessmentsDeleted, requestedAt, completedAt, targets, backupPolicy);
        }
    }
}
