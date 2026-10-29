package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.CustomerExportRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CreateCustomerExportRequest {
    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=workspace_id")
    private String workspaceId;

    @NoResponseMetadata("header:style=simple,explode=false,name=Idempotency-Key")
    private String idempotencyKey;

    @NoResponseMetadata("request:mediaType=application/json")
    private CustomerExportRequest body;

    @JsonCreator
    public CreateCustomerExportRequest(
            @Nonnull String workspaceId, @Nullable String idempotencyKey, @Nonnull CustomerExportRequest body) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.idempotencyKey = idempotencyKey;
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public CreateCustomerExportRequest(@Nonnull String workspaceId, @Nonnull CustomerExportRequest body) {
        this(workspaceId, null, body);
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public Optional<String> idempotencyKey() {
        return Optional.ofNullable(this.idempotencyKey);
    }

    public CustomerExportRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Your workspace id, as each key lists it in workspace_id. Another workspace, a malformed id and a
     * test environment's id answer 404.
     */
    public CreateCustomerExportRequest withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public CreateCustomerExportRequest withIdempotencyKey(@Nullable String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }

    public CreateCustomerExportRequest withBody(@Nonnull CustomerExportRequest body) {
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
        CreateCustomerExportRequest other = (CreateCustomerExportRequest) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.idempotencyKey, other.idempotencyKey)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, idempotencyKey, body);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CreateCustomerExportRequest.class,
                "workspaceId",
                workspaceId,
                "idempotencyKey",
                idempotencyKey,
                "body",
                body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private String idempotencyKey;

        private CustomerExportRequest body;

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

        public Builder idempotencyKey(@Nullable String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        public Builder body(@Nonnull CustomerExportRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CreateCustomerExportRequest build() {
            return new CreateCustomerExportRequest(workspaceId, idempotencyKey, body);
        }
    }
}
