package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * WorkspaceOperationsSummaryRequest
 *
 * <p>Read a past [from,to) interval of at most 31 days. The complete retained populations must fit a
 * combined 100000-row budget and a 1500ms SQL statement budget. No pagination or sampled totals;
 * choose a shorter interval after a row-budget refusal.
 */
public class WorkspaceOperationsSummaryRequest {

    @JsonProperty("environment")
    private AuditSettingsEnvironment environment;

    @JsonProperty("from")
    private String from;

    @JsonProperty("to")
    private String to;

    @JsonCreator
    public WorkspaceOperationsSummaryRequest(
            @JsonProperty("environment") @Nonnull AuditSettingsEnvironment environment,
            @JsonProperty("from") @Nonnull String from,
            @JsonProperty("to") @Nonnull String to) {
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.from = Optional.ofNullable(from).orElseThrow(() -> new IllegalArgumentException("from cannot be null"));
        this.to = Optional.ofNullable(to).orElseThrow(() -> new IllegalArgumentException("to cannot be null"));
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

    public static Builder builder() {
        return new Builder();
    }

    public WorkspaceOperationsSummaryRequest withEnvironment(@Nonnull AuditSettingsEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public WorkspaceOperationsSummaryRequest withFrom(@Nonnull String from) {
        this.from = Utils.checkNotNull(from, "from");
        return this;
    }

    public WorkspaceOperationsSummaryRequest withTo(@Nonnull String to) {
        this.to = Utils.checkNotNull(to, "to");
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
        WorkspaceOperationsSummaryRequest other = (WorkspaceOperationsSummaryRequest) o;
        return Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(environment, from, to);
    }

    @Override
    public String toString() {
        return Utils.toString(
                WorkspaceOperationsSummaryRequest.class, "environment", environment, "from", from, "to", to);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AuditSettingsEnvironment environment;

        private String from;

        private String to;

        private Builder() {
            // force use of static builder() method
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

        public WorkspaceOperationsSummaryRequest build() {
            return new WorkspaceOperationsSummaryRequest(environment, from, to);
        }
    }
}
