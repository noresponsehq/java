package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningRequest
 *
 * <p>Screens the group's people no screening has reached.
 */
public class ScreeningRequest {

    @JsonProperty("depth")
    private ScreeningDepth depth;

    @JsonCreator
    public ScreeningRequest(@JsonProperty("depth") @Nonnull ScreeningDepth depth) {
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningRequest withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
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
        ScreeningRequest other = (ScreeningRequest) o;
        return Utils.enhancedDeepEquals(this.depth, other.depth);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(depth);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningRequest.class, "depth", depth);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningDepth depth;

        private Builder() {
            // force use of static builder() method
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        public ScreeningRequest build() {
            return new ScreeningRequest(depth);
        }
    }
}
