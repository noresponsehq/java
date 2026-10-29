package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * WorkspaceOperationsSummary
 *
 * <p>Three separately defined populations with independent denominators. Interpret each section within
 * its own population; their totals do not form a conversion funnel. All counts and retention come from
 * one snapshot.
 *
 * <p>Requests above the population or query budget are refused without truncation.
 */
public class WorkspaceOperationsSummary {
    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonProperty("as_of")
    private String asOf;

    @JsonProperty("retained_from")
    private String retainedFrom;

    @JsonProperty("retention_days")
    private long retentionDays;

    @JsonProperty("row_budget")
    private long rowBudget;

    @JsonProperty("records_read")
    private long recordsRead;

    @JsonProperty("truncated")
    private boolean truncated;

    /**
     * Accounts whose current completed assessment was created in the selected window and remains retained
     * at as_of. The effective account feedback head is observed at as_of, including explicit unknown
     * reports. Customer-reported labels and unlabelled accounts have separate counts.
     *
     * <p>Superseded labels are excluded.
     */
    @JsonProperty("outcomes")
    private Outcomes outcomes;

    /**
     * Retained customer enforcement report rows created in [from,to), one row per
     * account/risk_version/flow/action. A later applied report can replace failed at the original
     * created_at; retries are not additional attempts here. applied_coverage divides reported_applied by
     * report rows, including explicit action none; it is not independently verified execution, all
     * requested actions, or prevented fraud.
     *
     * <p>Unreported actions are unknown.
     */
    @JsonProperty("enforcements")
    private Enforcements enforcements;

    /**
     * Reviews created in [from,to) whose review and current account assessment remain retained. Status and
     * assignment are current at as_of, not a transition history. label_coverage numerator is resolved
     * reviews labeled benign or abuse; denominator is resolved reviews.
     *
     * <p>Inconclusive is not a known outcome. Review conclusions do not change account feedback or
     * enforcement.
     */
    @JsonProperty("reviews")
    private Reviews reviews;

    @JsonCreator
    public WorkspaceOperationsSummary(
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to,
            @JsonProperty("as_of") @Nonnull String asOf,
            @JsonProperty("retained_from") @Nonnull String retainedFrom,
            @JsonProperty("retention_days") long retentionDays,
            @JsonProperty("records_read") long recordsRead,
            @JsonProperty("outcomes") @Nonnull Outcomes outcomes,
            @JsonProperty("enforcements") @Nonnull Enforcements enforcements,
            @JsonProperty("reviews") @Nonnull Reviews reviews) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
        this.asOf = Optional.ofNullable(asOf).orElseThrow(() -> new IllegalArgumentException("asOf cannot be null"));
        this.retainedFrom = Optional.ofNullable(retainedFrom)
                .orElseThrow(() -> new IllegalArgumentException("retainedFrom cannot be null"));
        this.retentionDays = retentionDays;
        this.rowBudget = Builder._SINGLETON_VALUE_RowBudget.value();
        this.recordsRead = recordsRead;
        this.truncated = Builder._SINGLETON_VALUE_Truncated.value();
        this.outcomes = Optional.ofNullable(outcomes)
                .orElseThrow(() -> new IllegalArgumentException("outcomes cannot be null"));
        this.enforcements = Optional.ofNullable(enforcements)
                .orElseThrow(() -> new IllegalArgumentException("enforcements cannot be null"));
        this.reviews =
                Optional.ofNullable(reviews).orElseThrow(() -> new IllegalArgumentException("reviews cannot be null"));
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public AuditSettingsEnvironment environment() {
        return this.environment;
    }

    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public String asOf() {
        return this.asOf;
    }

    public String retainedFrom() {
        return this.retainedFrom;
    }

    public long retentionDays() {
        return this.retentionDays;
    }

    public long rowBudget() {
        return this.rowBudget;
    }

    public long recordsRead() {
        return this.recordsRead;
    }

    public boolean truncated() {
        return this.truncated;
    }

    /**
     * Accounts whose current completed assessment was created in the selected window and remains retained
     * at as_of. The effective account feedback head is observed at as_of, including explicit unknown
     * reports. Customer-reported labels and unlabelled accounts have separate counts.
     *
     * <p>Superseded labels are excluded.
     */
    public Outcomes outcomes() {
        return this.outcomes;
    }

    /**
     * Retained customer enforcement report rows created in [from,to), one row per
     * account/risk_version/flow/action. A later applied report can replace failed at the original
     * created_at; retries are not additional attempts here. applied_coverage divides reported_applied by
     * report rows, including explicit action none; it is not independently verified execution, all
     * requested actions, or prevented fraud.
     *
     * <p>Unreported actions are unknown.
     */
    public Enforcements enforcements() {
        return this.enforcements;
    }

    /**
     * Reviews created in [from,to) whose review and current account assessment remain retained. Status and
     * assignment are current at as_of, not a transition history. label_coverage numerator is resolved
     * reviews labeled benign or abuse; denominator is resolved reviews.
     *
     * <p>Inconclusive is not a known outcome. Review conclusions do not change account feedback or
     * enforcement.
     */
    public Reviews reviews() {
        return this.reviews;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public WorkspaceOperationsSummary withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public WorkspaceOperationsSummary withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public WorkspaceOperationsSummary withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public WorkspaceOperationsSummary withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
        return this;
    }

    public WorkspaceOperationsSummary withAsOf(@Nonnull String asOf) {
        this.asOf = Utils.checkNotNull(asOf, "asOf");
        return this;
    }

    public WorkspaceOperationsSummary withRetainedFrom(@Nonnull String retainedFrom) {
        this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
        return this;
    }

    public WorkspaceOperationsSummary withRetentionDays(long retentionDays) {
        this.retentionDays = retentionDays;
        return this;
    }

    public WorkspaceOperationsSummary withRecordsRead(long recordsRead) {
        this.recordsRead = recordsRead;
        return this;
    }

    /**
     * Accounts whose current completed assessment was created in the selected window and remains retained
     * at as_of. The effective account feedback head is observed at as_of, including explicit unknown
     * reports. Customer-reported labels and unlabelled accounts have separate counts.
     *
     * <p>Superseded labels are excluded.
     */
    public WorkspaceOperationsSummary withOutcomes(@Nonnull Outcomes outcomes) {
        this.outcomes = Utils.checkNotNull(outcomes, "outcomes");
        return this;
    }

    /**
     * Retained customer enforcement report rows created in [from,to), one row per
     * account/risk_version/flow/action. A later applied report can replace failed at the original
     * created_at; retries are not additional attempts here. applied_coverage divides reported_applied by
     * report rows, including explicit action none; it is not independently verified execution, all
     * requested actions, or prevented fraud.
     *
     * <p>Unreported actions are unknown.
     */
    public WorkspaceOperationsSummary withEnforcements(@Nonnull Enforcements enforcements) {
        this.enforcements = Utils.checkNotNull(enforcements, "enforcements");
        return this;
    }

    /**
     * Reviews created in [from,to) whose review and current account assessment remain retained. Status and
     * assignment are current at as_of, not a transition history. label_coverage numerator is resolved
     * reviews labeled benign or abuse; denominator is resolved reviews.
     *
     * <p>Inconclusive is not a known outcome. Review conclusions do not change account feedback or
     * enforcement.
     */
    public WorkspaceOperationsSummary withReviews(@Nonnull Reviews reviews) {
        this.reviews = Utils.checkNotNull(reviews, "reviews");
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
        WorkspaceOperationsSummary other = (WorkspaceOperationsSummary) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to)
                && Utils.enhancedDeepEquals(this.asOf, other.asOf)
                && Utils.enhancedDeepEquals(this.retainedFrom, other.retainedFrom)
                && Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays)
                && Utils.enhancedDeepEquals(this.rowBudget, other.rowBudget)
                && Utils.enhancedDeepEquals(this.recordsRead, other.recordsRead)
                && Utils.enhancedDeepEquals(this.truncated, other.truncated)
                && Utils.enhancedDeepEquals(this.outcomes, other.outcomes)
                && Utils.enhancedDeepEquals(this.enforcements, other.enforcements)
                && Utils.enhancedDeepEquals(this.reviews, other.reviews);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                workspaceId,
                environment,
                from,
                to,
                asOf,
                retainedFrom,
                retentionDays,
                rowBudget,
                recordsRead,
                truncated,
                outcomes,
                enforcements,
                reviews);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspaceOperationsSummary.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "from",
                from,
                "to",
                to,
                "asOf",
                asOf,
                "retainedFrom",
                retainedFrom,
                "retentionDays",
                retentionDays,
                "rowBudget",
                rowBudget,
                "recordsRead",
                recordsRead,
                "truncated",
                truncated,
                "outcomes",
                outcomes,
                "enforcements",
                enforcements,
                "reviews",
                reviews);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private AuditSettingsEnvironment environment;

        private String from;

        private String to;

        private String asOf;

        private String retainedFrom;

        private long retentionDays;

        private long recordsRead;

        private Outcomes outcomes;

        private Enforcements enforcements;

        private Reviews reviews;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        public Builder environment(@Nonnull AuditSettingsEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder from(@Nonnull String from) {
            this.from = Utils.checkNotNull(from, "from");
            return this;
        }

        public Builder to(@Nonnull String to) {
            this.to = Utils.checkNotNull(to, "to");
            return this;
        }

        public Builder asOf(@Nonnull String asOf) {
            this.asOf = Utils.checkNotNull(asOf, "asOf");
            return this;
        }

        public Builder retainedFrom(@Nonnull String retainedFrom) {
            this.retainedFrom = Utils.checkNotNull(retainedFrom, "retainedFrom");
            return this;
        }

        public Builder retentionDays(long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public Builder recordsRead(long recordsRead) {
            this.recordsRead = recordsRead;
            return this;
        }

        /**
         * Accounts whose current completed assessment was created in the selected window and remains retained
         * at as_of. The effective account feedback head is observed at as_of, including explicit unknown
         * reports. Customer-reported labels and unlabelled accounts have separate counts.
         *
         * <p>Superseded labels are excluded.
         */
        public Builder outcomes(@Nonnull Outcomes outcomes) {
            this.outcomes = Utils.checkNotNull(outcomes, "outcomes");
            return this;
        }

        /**
         * Retained customer enforcement report rows created in [from,to), one row per
         * account/risk_version/flow/action. A later applied report can replace failed at the original
         * created_at; retries are not additional attempts here. applied_coverage divides reported_applied by
         * report rows, including explicit action none; it is not independently verified execution, all
         * requested actions, or prevented fraud.
         *
         * <p>Unreported actions are unknown.
         */
        public Builder enforcements(@Nonnull Enforcements enforcements) {
            this.enforcements = Utils.checkNotNull(enforcements, "enforcements");
            return this;
        }

        /**
         * Reviews created in [from,to) whose review and current account assessment remain retained. Status and
         * assignment are current at as_of, not a transition history. label_coverage numerator is resolved
         * reviews labeled benign or abuse; denominator is resolved reviews.
         *
         * <p>Inconclusive is not a known outcome. Review conclusions do not change account feedback or
         * enforcement.
         */
        public Builder reviews(@Nonnull Reviews reviews) {
            this.reviews = Utils.checkNotNull(reviews, "reviews");
            return this;
        }

        public WorkspaceOperationsSummary build() {
            return new WorkspaceOperationsSummary(
                    workspaceId,
                    environment,
                    from,
                    to,
                    asOf,
                    retainedFrom,
                    retentionDays,
                    recordsRead,
                    outcomes,
                    enforcements,
                    reviews);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_RowBudget =
                new LazySingletonValue<>("row_budget", "100000", new TypeReference<Long>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_Truncated =
                new LazySingletonValue<>("truncated", "false", new TypeReference<Boolean>() {});
    }
}
