package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class DeleteIntegrationRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=integration_id")
    private String integrationId;

    @JsonCreator
    public DeleteIntegrationRequest(@Nonnull String integrationId) {
        this.integrationId = Optional.ofNullable(integrationId)
                .orElseThrow(() -> new IllegalArgumentException("integrationId cannot be null"));
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    public String integrationId() {
        return this.integrationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    public DeleteIntegrationRequest withIntegrationId(@Nonnull String integrationId) {
        this.integrationId = Utils.checkNotNull(integrationId, "integrationId");
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
        DeleteIntegrationRequest other = (DeleteIntegrationRequest) o;
        return Utils.enhancedDeepEquals(this.integrationId, other.integrationId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(integrationId);
    }

    @Override
    public String toString() {
        return Utils.toString(DeleteIntegrationRequest.class, "integrationId", integrationId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String integrationId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
         * another workspace's id, answers 404.
         */
        public Builder integrationId(@Nonnull String integrationId) {
            this.integrationId = Utils.checkNotNull(integrationId, "integrationId");
            return this;
        }

        public DeleteIntegrationRequest build() {
            return new DeleteIntegrationRequest(integrationId);
        }
    }
}
