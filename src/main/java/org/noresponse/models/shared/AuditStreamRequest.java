package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class AuditStreamRequest {

    @JsonProperty("endpoint_id")
    private String endpointId;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("actions")
    private List<String> actions;

    @JsonCreator
    public AuditStreamRequest(
            @JsonProperty("endpoint_id") @Nonnull String endpointId,
            @JsonProperty("actions") @Nullable List<String> actions) {
        this.endpointId = Optional.ofNullable(endpointId)
                .orElseThrow(() -> new IllegalArgumentException("endpointId cannot be null"));
        this.actions = actions;
    }

    public AuditStreamRequest(@Nonnull String endpointId) {
        this(endpointId, null);
    }

    public String endpointId() {
        return this.endpointId;
    }

    public Optional<List<String>> actions() {
        return Optional.ofNullable(this.actions);
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuditStreamRequest withEndpointId(@Nonnull String endpointId) {
        this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
        return this;
    }

    public AuditStreamRequest withActions(@Nullable List<String> actions) {
        this.actions = actions;
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
        AuditStreamRequest other = (AuditStreamRequest) o;
        return Utils.enhancedDeepEquals(this.endpointId, other.endpointId)
                && Utils.enhancedDeepEquals(this.actions, other.actions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(endpointId, actions);
    }

    @Override
    public String toString() {
        return Utils.toString(AuditStreamRequest.class, "endpointId", endpointId, "actions", actions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String endpointId;

        private List<String> actions;

        private Builder() {
            // force use of static builder() method
        }

        public Builder endpointId(@Nonnull String endpointId) {
            this.endpointId = Utils.checkNotNull(endpointId, "endpointId");
            return this;
        }

        public Builder actions(@Nullable List<String> actions) {
            this.actions = actions;
            return this;
        }

        public AuditStreamRequest build() {
            return new AuditStreamRequest(endpointId, actions);
        }
    }
}
