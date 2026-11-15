package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ScreeningCancelRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class CancelInvestigationRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=investigation_id")
    private String investigationId;

    @NoResponseMetadata("request:mediaType=application/json")
    private ScreeningCancelRequest body;

    @JsonCreator
    public CancelInvestigationRequest(@Nonnull String investigationId, @Nonnull ScreeningCancelRequest body) {
        this.investigationId = Optional.ofNullable(investigationId)
                .orElseThrow(() -> new IllegalArgumentException("investigationId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String investigationId() {
        return this.investigationId;
    }

    public ScreeningCancelRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CancelInvestigationRequest withInvestigationId(@Nonnull String investigationId) {
        this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
        return this;
    }

    public CancelInvestigationRequest withBody(@Nonnull ScreeningCancelRequest body) {
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
        CancelInvestigationRequest other = (CancelInvestigationRequest) o;
        return Utils.enhancedDeepEquals(this.investigationId, other.investigationId)
                && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(investigationId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(CancelInvestigationRequest.class, "investigationId", investigationId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String investigationId;

        private ScreeningCancelRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder investigationId(@Nonnull String investigationId) {
            this.investigationId = Utils.checkNotNull(investigationId, "investigationId");
            return this;
        }

        public Builder body(@Nonnull ScreeningCancelRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public CancelInvestigationRequest build() {
            return new CancelInvestigationRequest(investigationId, body);
        }
    }
}
