package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class Filter {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("status")
    private ReviewStatus status;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("priority")
    private Priority priority;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assignee")
    private String assignee;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("unassigned")
    private Boolean unassigned;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("assignee_principal_id")
    private String assigneePrincipalId;

    @JsonCreator
    public Filter(
            @JsonProperty("status") @Nullable ReviewStatus status,
            @JsonProperty("priority") @Nullable Priority priority,
            @JsonProperty("assignee") @Nullable String assignee,
            @JsonProperty("unassigned") @Nullable Boolean unassigned,
            @JsonProperty("assignee_principal_id") @Nullable String assigneePrincipalId) {
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
        this.unassigned = unassigned;
        this.assigneePrincipalId = assigneePrincipalId;
    }

    public Filter() {
        this(null, null, null, null, null);
    }

    public Optional<ReviewStatus> status() {
        return Optional.ofNullable(this.status);
    }

    public Optional<Priority> priority() {
        return Optional.ofNullable(this.priority);
    }

    public Optional<String> assignee() {
        return Optional.ofNullable(this.assignee);
    }

    public Optional<Boolean> unassigned() {
        return Optional.ofNullable(this.unassigned);
    }

    public Optional<String> assigneePrincipalId() {
        return Optional.ofNullable(this.assigneePrincipalId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Filter withStatus(@Nullable ReviewStatus status) {
        this.status = status;
        return this;
    }

    public Filter withPriority(@Nullable Priority priority) {
        this.priority = priority;
        return this;
    }

    public Filter withAssignee(@Nullable String assignee) {
        this.assignee = assignee;
        return this;
    }

    public Filter withUnassigned(@Nullable Boolean unassigned) {
        this.unassigned = unassigned;
        return this;
    }

    public Filter withAssigneePrincipalId(@Nullable String assigneePrincipalId) {
        this.assigneePrincipalId = assigneePrincipalId;
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
        Filter other = (Filter) o;
        return Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.priority, other.priority)
                && Utils.enhancedDeepEquals(this.assignee, other.assignee)
                && Utils.enhancedDeepEquals(this.unassigned, other.unassigned)
                && Utils.enhancedDeepEquals(this.assigneePrincipalId, other.assigneePrincipalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(status, priority, assignee, unassigned, assigneePrincipalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                Filter.class,
                "status",
                status,
                "priority",
                priority,
                "assignee",
                assignee,
                "unassigned",
                unassigned,
                "assigneePrincipalId",
                assigneePrincipalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ReviewStatus status;

        private Priority priority;

        private String assignee;

        private Boolean unassigned;

        private String assigneePrincipalId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder status(@Nullable ReviewStatus status) {
            this.status = status;
            return this;
        }

        public Builder priority(@Nullable Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder assignee(@Nullable String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder unassigned(@Nullable Boolean unassigned) {
            this.unassigned = unassigned;
            return this;
        }

        public Builder assigneePrincipalId(@Nullable String assigneePrincipalId) {
            this.assigneePrincipalId = assigneePrincipalId;
            return this;
        }

        public Filter build() {
            return new Filter(status, priority, assignee, unassigned, assigneePrincipalId);
        }
    }
}
