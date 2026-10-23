package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.IntegrationRotationRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class RotateIntegrationSecretRequest {
    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=integration_id")
    private String integrationId;

    @NoResponseMetadata("request:mediaType=application/json")
    private IntegrationRotationRequest body;

    @JsonCreator
    public RotateIntegrationSecretRequest(@Nonnull String integrationId, @Nonnull IntegrationRotationRequest body) {
        this.integrationId = Optional.ofNullable(integrationId)
                .orElseThrow(() -> new IllegalArgumentException("integrationId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    public String integrationId() {
        return this.integrationId;
    }

    public IntegrationRotationRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Path segment, matched case-sensitively. A value that does not match the integration_id shape, or
     * another workspace's id, answers 404.
     */
    public RotateIntegrationSecretRequest withIntegrationId(@Nonnull String integrationId) {
        this.integrationId = Utils.checkNotNull(integrationId, "integrationId");
        return this;
    }

    public RotateIntegrationSecretRequest withBody(@Nonnull IntegrationRotationRequest body) {
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
        RotateIntegrationSecretRequest other = (RotateIntegrationSecretRequest) o;
        return Utils.enhancedDeepEquals(this.integrationId, other.integrationId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(integrationId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(RotateIntegrationSecretRequest.class, "integrationId", integrationId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String integrationId;

        private IntegrationRotationRequest body;

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

        public Builder body(@Nonnull IntegrationRotationRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public RotateIntegrationSecretRequest build() {
            return new RotateIntegrationSecretRequest(integrationId, body);
        }
    }
}
