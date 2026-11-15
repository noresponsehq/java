package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetInvestigationRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=investigation_id")
    private String investigationId;

    @JsonCreator
    public GetInvestigationRequest(@Nonnull String investigationId) {
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
    }

    public String investigationId() {
        return this.investigationId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public GetInvestigationRequest withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
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
        GetInvestigationRequest other = (GetInvestigationRequest) o;
        return Utils.enhancedDeepEquals(this.investigationId, other.investigationId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(investigationId);
    }

    @Override
    public String toString() {
        return Utils.toString(GetInvestigationRequest.class, "investigationId", investigationId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String investigationId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        public GetInvestigationRequest build() {
            return new GetInvestigationRequest(investigationId);
        }
    }
}
