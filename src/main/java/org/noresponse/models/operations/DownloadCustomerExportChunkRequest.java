package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DownloadCustomerExportChunkRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=export_id")
    private String exportId;

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=chunk_index")
    private long chunkIndex;

    @JsonCreator
    public DownloadCustomerExportChunkRequest(@Nonnull String workspaceId, @Nonnull String exportId, long chunkIndex) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.exportId = Optional.ofNullable(exportId)
                .orElseThrow(() -> new IllegalArgumentException("exportId cannot be null"));
        this.chunkIndex = chunkIndex;
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

    public long chunkIndex() {
        return this.chunkIndex;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public DownloadCustomerExportChunkRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public DownloadCustomerExportChunkRequest withExportId(@Nonnull String exportId) {
        this.exportId = Utils.checkNotNull(exportId, "exportId");
        return this;
    }

    public DownloadCustomerExportChunkRequest withChunkIndex(long chunkIndex) {
        this.chunkIndex = chunkIndex;
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
        DownloadCustomerExportChunkRequest other = (DownloadCustomerExportChunkRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.exportId, other.exportId)
                && Utils.enhancedDeepEquals(this.chunkIndex, other.chunkIndex);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, exportId, chunkIndex);
    }

    @Override
    public String toString() {
        return Utils.toString(
                DownloadCustomerExportChunkRequest.class,
                "workspaceId",
                workspaceId,
                "exportId",
                exportId,
                "chunkIndex",
                chunkIndex);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String exportId;

        private long chunkIndex;

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

        public Builder chunkIndex(long chunkIndex) {
            this.chunkIndex = chunkIndex;
            return this;
        }

        public DownloadCustomerExportChunkRequest build() {
            return new DownloadCustomerExportChunkRequest(workspaceId, exportId, chunkIndex);
        }
    }
}
