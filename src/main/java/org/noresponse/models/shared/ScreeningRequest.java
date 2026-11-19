package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.util.List;
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

    /**
     * Deprecated: leave it out. Without it the screening is a full search: every platform a connector
     * serves in the environment when the screening starts, which the screening then lists, each searched
     * only with the identifiers its connector takes. A list sent here still limits the screening until a
     * later contract release removes the field.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("platforms")
    @Deprecated
    private List<String> platforms;

    @JsonCreator
    public ScreeningRequest(
            @JsonProperty("depth") @Nonnull ScreeningDepth depth,
            @JsonProperty("platforms") @Nullable List<String> platforms) {
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.platforms = platforms;
    }

    public ScreeningRequest(@Nonnull ScreeningDepth depth) {
        this(depth, null);
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    /**
     * Deprecated: leave it out. Without it the screening is a full search: every platform a connector
     * serves in the environment when the screening starts, which the screening then lists, each searched
     * only with the identifiers its connector takes. A list sent here still limits the screening until a
     * later contract release removes the field.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @Deprecated
    public Optional<List<String>> platforms() {
        return Optional.ofNullable(this.platforms);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningRequest withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    /**
     * Deprecated: leave it out. Without it the screening is a full search: every platform a connector
     * serves in the environment when the screening starts, which the screening then lists, each searched
     * only with the identifiers its connector takes. A list sent here still limits the screening until a
     * later contract release removes the field.
     *
     * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
     */
    @Deprecated
    public ScreeningRequest withPlatforms(@Nullable List<String> platforms) {
        this.platforms = platforms;
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
        return Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.platforms, other.platforms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(depth, platforms);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningRequest.class, "depth", depth, "platforms", platforms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningDepth depth;

        @Deprecated
        private List<String> platforms;

        private Builder() {
            // force use of static builder() method
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        /**
         * Deprecated: leave it out. Without it the screening is a full search: every platform a connector
         * serves in the environment when the screening starts, which the screening then lists, each searched
         * only with the identifiers its connector takes. A list sent here still limits the screening until a
         * later contract release removes the field.
         *
         * @deprecated field: This will be removed in a future release, please migrate away from it as soon as possible.
         */
        @Deprecated
        public Builder platforms(@Nullable List<String> platforms) {
            this.platforms = platforms;
            return this;
        }

        public ScreeningRequest build() {
            return new ScreeningRequest(depth, platforms);
        }
    }
}
