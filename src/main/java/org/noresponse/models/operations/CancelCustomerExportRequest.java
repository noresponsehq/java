package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.CustomerExportActionRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CancelCustomerExportRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=export_id")
    private String exportId;

    @NoResponseMetadata("request:mediaType=application/json")
    private CustomerExportActionRequest body;

    @JsonCreator
    public CancelCustomerExportRequest(
            @Nonnull String workspaceId, @Nonnull String exportId, @Nonnull CustomerExportActionRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.exportId = Optional.ofNullable(exportId)
                .orElseThrow(() -> new IllegalArgumentException("exportId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public String exportId() {
        return this.exportId;
    }

    public CustomerExportActionRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public CancelCustomerExportRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public CancelCustomerExportRequest withExportId(@Nonnull String exportId) {
        this.exportId = Utils.checkNotNull(exportId, "exportId");
        return this;
    }

    public CancelCustomerExportRequest withBody(@Nonnull CustomerExportActionRequest body) {
        this.body = Utils.checkNotNull(body, "body");
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
        CancelCustomerExportRequest other = (CancelCustomerExportRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.exportId, other.exportId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, exportId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CancelCustomerExportRequest.class, "workspaceId", workspaceId, "exportId", exportId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String exportId;

        private CustomerExportActionRequest body;

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

        public Builder exportId(@Nonnull String exportId) {
            this.exportId = Utils.checkNotNull(exportId, "exportId");
            return this;
        }

        public Builder body(@Nonnull CustomerExportActionRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CancelCustomerExportRequest build() {
            return new CancelCustomerExportRequest(workspaceId, exportId, body);
        }
    }
}
