package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class ReviewQueryRequest {
    /**
     * Only reviews of this exact account in the authenticated workspace and environment. Applied before
     * pagination.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("account_id")
    private String accountId;

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

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("limit")
    private Long limit;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("cursor")
    private String cursor;

    @JsonCreator
    public ReviewQueryRequest(
            @JsonProperty("account_id") @Nullable String accountId,
            @JsonProperty("status") @Nullable ReviewStatus status,
            @JsonProperty("priority") @Nullable Priority priority,
            @JsonProperty("assignee") @Nullable String assignee,
            @JsonProperty("unassigned") @Nullable Boolean unassigned,
            @JsonProperty("assignee_principal_id") @Nullable String assigneePrincipalId,
            @JsonProperty("limit") @Nullable Long limit,
            @JsonProperty("cursor") @Nullable String cursor) {
        this.accountId = accountId;
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
        this.unassigned = unassigned;
        this.assigneePrincipalId = assigneePrincipalId;
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
    }

    public ReviewQueryRequest() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * Only reviews of this exact account in the authenticated workspace and environment. Applied before
     * pagination.
     */
    public Optional<String> accountId() {
        return Optional.ofNullable(this.accountId);
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

    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Only reviews of this exact account in the authenticated workspace and environment. Applied before
     * pagination.
     */
    public ReviewQueryRequest withAccountId(@Nullable String accountId) {
        this.accountId = accountId;
        return this;
    }

    public ReviewQueryRequest withStatus(@Nullable ReviewStatus status) {
        this.status = status;
        return this;
    }

    public ReviewQueryRequest withPriority(@Nullable Priority priority) {
        this.priority = priority;
        return this;
    }

    public ReviewQueryRequest withAssignee(@Nullable String assignee) {
        this.assignee = assignee;
        return this;
    }

    public ReviewQueryRequest withUnassigned(@Nullable Boolean unassigned) {
        this.unassigned = unassigned;
        return this;
    }

    public ReviewQueryRequest withAssigneePrincipalId(@Nullable String assigneePrincipalId) {
        this.assigneePrincipalId = assigneePrincipalId;
        return this;
    }

    public ReviewQueryRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    public ReviewQueryRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
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
        ReviewQueryRequest other = (ReviewQueryRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.priority, other.priority)
                && Utils.enhancedDeepEquals(this.assignee, other.assignee)
                && Utils.enhancedDeepEquals(this.unassigned, other.unassigned)
                && Utils.enhancedDeepEquals(this.assigneePrincipalId, other.assigneePrincipalId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                accountId, status, priority, assignee, unassigned, assigneePrincipalId, limit, cursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewQueryRequest.class,
                "accountId",
                accountId,
                "status",
                status,
                "priority",
                priority,
                "assignee",
                assignee,
                "unassigned",
                unassigned,
                "assigneePrincipalId",
                assigneePrincipalId,
                "limit",
                limit,
                "cursor",
                cursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private ReviewStatus status;

        private Priority priority;

        private String assignee;

        private Boolean unassigned;

        private String assigneePrincipalId;

        private Long limit;

        private String cursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Only reviews of this exact account in the authenticated workspace and environment. Applied before
         * pagination.
         */
        public Builder accountId(@Nullable String accountId) {
            this.accountId = accountId;
            return this;
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

        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        public ReviewQueryRequest build() {
            return new ReviewQueryRequest(
                    accountId, status, priority, assignee, unassigned, assigneePrincipalId, limit, cursor);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "50", new TypeReference<Long>() {});
    }
}
