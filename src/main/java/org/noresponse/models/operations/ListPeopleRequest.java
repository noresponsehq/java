package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.KeyEnvironment;
import org.noresponse.models.shared.ListPeopleBand;
import org.noresponse.models.shared.ListPeopleDecision;
import org.noresponse.models.shared.ListPeopleRole;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListPeopleRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=environment")
    private KeyEnvironment environment;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=group_id")
    private String groupId;

    /**
     * At most this many people.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * Read people after this one.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=after")
    private String after;

    /**
     * Only people whose highest finding has this band; none for people with no finding.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=band")
    private ListPeopleBand band;

    /**
     * Only people with this decision; undecided for none yet.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=decision")
    private ListPeopleDecision decision;

    /**
     * Only people with this role; unstated for people whose role was not given.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=role")
    private ListPeopleRole role;

    @JsonCreator
    public ListPeopleRequest(
            @Nonnull String workspaceId,
            @Nonnull KeyEnvironment environment,
            @Nonnull String groupId,
            @Nullable Long limit,
            @Nullable String after,
            @Nullable ListPeopleBand band,
            @Nullable ListPeopleDecision decision,
            @Nullable ListPeopleRole role) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.environment = Optional.ofNullable(environment)
                .orElseThrow(() -> new IllegalArgumentException("environment cannot be null"));
        this.groupId =
                Optional.ofNullable(groupId).orElseThrow(() -> new IllegalArgumentException("groupId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.after = after;
        this.band = band;
        this.decision = decision;
        this.role = role;
    }

    public ListPeopleRequest(
            @Nonnull String workspaceId, @Nonnull KeyEnvironment environment, @Nonnull String groupId) {
        this(workspaceId, environment, groupId, null, null, null, null, null);
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public KeyEnvironment environment() {
        return this.environment;
    }

    public String groupId() {
        return this.groupId;
    }

    /**
     * At most this many people.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * Read people after this one.
     */
    public Optional<String> after() {
        return Optional.ofNullable(this.after);
    }

    /**
     * Only people whose highest finding has this band; none for people with no finding.
     */
    public Optional<ListPeopleBand> band() {
        return Optional.ofNullable(this.band);
    }

    /**
     * Only people with this decision; undecided for none yet.
     */
    public Optional<ListPeopleDecision> decision() {
        return Optional.ofNullable(this.decision);
    }

    /**
     * Only people with this role; unstated for people whose role was not given.
     */
    public Optional<ListPeopleRole> role() {
        return Optional.ofNullable(this.role);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public ListPeopleRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The environment: live or test. Each has its own settings. Any other value answers 400.
     *
     * <p>An API key reads and changes only its own environment's; the other answers 404.
     */
    public ListPeopleRequest withEnvironment(@Nonnull KeyEnvironment environment) {
        this.environment = Utils.checkNotNull(environment, "environment");
        return this;
    }

    public ListPeopleRequest withGroupId(@Nonnull String groupId) {
        this.groupId = Utils.checkNotNull(groupId, "groupId");
        return this;
    }

    /**
     * At most this many people.
     */
    public ListPeopleRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Read people after this one.
     */
    public ListPeopleRequest withAfter(@Nullable String after) {
        this.after = after;
        return this;
    }

    /**
     * Only people whose highest finding has this band; none for people with no finding.
     */
    public ListPeopleRequest withBand(@Nullable ListPeopleBand band) {
        this.band = band;
        return this;
    }

    /**
     * Only people with this decision; undecided for none yet.
     */
    public ListPeopleRequest withDecision(@Nullable ListPeopleDecision decision) {
        this.decision = decision;
        return this;
    }

    /**
     * Only people with this role; unstated for people whose role was not given.
     */
    public ListPeopleRequest withRole(@Nullable ListPeopleRole role) {
        this.role = role;
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
        ListPeopleRequest other = (ListPeopleRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.environment, other.environment)
                && Utils.enhancedDeepEquals(this.groupId, other.groupId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.after, other.after)
                && Utils.enhancedDeepEquals(this.band, other.band)
                && Utils.enhancedDeepEquals(this.decision, other.decision)
                && Utils.enhancedDeepEquals(this.role, other.role);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, environment, groupId, limit, after, band, decision, role);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListPeopleRequest.class,
                "workspaceId",
                workspaceId,
                "environment",
                environment,
                "groupId",
                groupId,
                "limit",
                limit,
                "after",
                after,
                "band",
                band,
                "decision",
                decision,
                "role",
                role);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private KeyEnvironment environment;

        private String groupId;

        private Long limit;

        private String after;

        private ListPeopleBand band;

        private ListPeopleDecision decision;

        private ListPeopleRole role;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
         * test environment's id answer 404.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        /**
         * The environment: live or test. Each has its own settings. Any other value answers 400.
         *
         * <p>An API key reads and changes only its own environment's; the other answers 404.
         */
        public Builder environment(@Nonnull KeyEnvironment environment) {
            this.environment = Utils.checkNotNull(environment, "environment");
            return this;
        }

        public Builder groupId(@Nonnull String groupId) {
            this.groupId = Utils.checkNotNull(groupId, "groupId");
            return this;
        }

        /**
         * At most this many people.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Read people after this one.
         */
        public Builder after(@Nullable String after) {
            this.after = after;
            return this;
        }

        /**
         * Only people whose highest finding has this band; none for people with no finding.
         */
        public Builder band(@Nullable ListPeopleBand band) {
            this.band = band;
            return this;
        }

        /**
         * Only people with this decision; undecided for none yet.
         */
        public Builder decision(@Nullable ListPeopleDecision decision) {
            this.decision = decision;
            return this;
        }

        /**
         * Only people with this role; unstated for people whose role was not given.
         */
        public Builder role(@Nullable ListPeopleRole role) {
            this.role = role;
            return this;
        }

        public ListPeopleRequest build() {
            return new ListPeopleRequest(workspaceId, environment, groupId, limit, after, band, decision, role);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "100", new TypeReference<Long>() {});
    }
}
