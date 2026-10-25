package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RevokeKeyRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    /**
     * The key's id. A key of another workspace answers 404, as does a key of the other environment to an
     * API key.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=key_id")
    private String keyId;

    @JsonCreator
    public RevokeKeyRequest(@Nonnull String workspaceId, @Nonnull String keyId) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.keyId = Optional.ofNullable(keyId).orElseThrow(() -> new IllegalArgumentException("keyId cannot be null"));
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    /**
     * The key's id. A key of another workspace answers 404, as does a key of the other environment to an
     * API key.
     */
    public String keyId() {
        return this.keyId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public RevokeKeyRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    /**
     * The key's id. A key of another workspace answers 404, as does a key of the other environment to an
     * API key.
     */
    public RevokeKeyRequest withKeyId(@Nonnull String keyId) {
        this.keyId = Utils.checkNotNull(keyId, "keyId");
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
        RevokeKeyRequest other = (RevokeKeyRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.keyId, other.keyId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, keyId);
    }

    @Override
    public String toString() {
        return Utils.toString(RevokeKeyRequest.class, "workspaceId", workspaceId, "keyId", keyId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String keyId;

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
         * The key's id. A key of another workspace answers 404, as does a key of the other environment to an
         * API key.
         */
        public Builder keyId(@Nonnull String keyId) {
            this.keyId = Utils.checkNotNull(keyId, "keyId");
            return this;
        }

        public RevokeKeyRequest build() {
            return new RevokeKeyRequest(workspaceId, keyId);
        }
    }
}
