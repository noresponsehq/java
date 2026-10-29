package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetErasureReceiptRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=erasure_id")
    private String erasureId;

    @JsonCreator
    public GetErasureReceiptRequest(@Nonnull String erasureId) {
        this.erasureId = Optional.ofNullable(erasureId)
                .orElseThrow(() -> new IllegalArgumentException("erasureId cannot be null"));
    }

    public String erasureId() {
        return this.erasureId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public GetErasureReceiptRequest withErasureId(@Nonnull String erasureId) {
        this.erasureId = Utils.checkNotNull(erasureId, "erasureId");
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
        GetErasureReceiptRequest other = (GetErasureReceiptRequest) o;
        return Utils.enhancedDeepEquals(this.erasureId, other.erasureId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(erasureId);
    }

    @Override
    public String toString() {
        return Utils.toString(GetErasureReceiptRequest.class, "erasureId", erasureId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String erasureId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder erasureId(@Nonnull String erasureId) {
            this.erasureId = Utils.checkNotNull(erasureId, "erasureId");
            return this;
        }

        public GetErasureReceiptRequest build() {
            return new GetErasureReceiptRequest(erasureId);
        }
    }
}
