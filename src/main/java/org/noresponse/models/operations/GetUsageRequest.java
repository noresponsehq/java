package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetUsageRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The first UTC day, as YYYY-MM-DD.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=from")
    private String from;

    /**
     * The last UTC day, included, as YYYY-MM-DD; no later than today.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=to")
    private String to;

    @JsonCreator
    public GetUsageRequest(@Nonnull String workspaceId, @Nullable String from, @Nullable String to) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.from = from;
        this.to = to;
    }

    public GetUsageRequest(@Nonnull String workspaceId) {
        this(workspaceId, null, null);
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The first UTC day, as YYYY-MM-DD.
     */
    public Optional<String> from() {
        return Optional.ofNullable(this.from);
    }

    /**
     * The last UTC day, included, as YYYY-MM-DD; no later than today.
     */
    public Optional<String> to() {
        return Optional.ofNullable(this.to);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public GetUsageRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The first UTC day, as YYYY-MM-DD.
     */
    public GetUsageRequest withFrom(@Nullable String from) {
        this.from = from;
        return this;
    }

    /**
     * The last UTC day, included, as YYYY-MM-DD; no later than today.
     */
    public GetUsageRequest withTo(@Nullable String to) {
        this.to = to;
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
        GetUsageRequest other = (GetUsageRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.from, other.from)
                && Utils.enhancedDeepEquals(this.to, other.to);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, from, to);
    }

    @Override
    public String toString() {
        return Utils.toString(GetUsageRequest.class, "workspaceId", workspaceId, "from", from, "to", to);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String from;

        private String to;

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
         * The first UTC day, as YYYY-MM-DD.
         */
        public Builder from(@Nullable String from) {
            this.from = from;
            return this;
        }

        /**
         * The last UTC day, included, as YYYY-MM-DD; no later than today.
         */
        public Builder to(@Nullable String to) {
            this.to = to;
            return this;
        }

        public GetUsageRequest build() {
            return new GetUsageRequest(workspaceId, from, to);
        }
    }
}
