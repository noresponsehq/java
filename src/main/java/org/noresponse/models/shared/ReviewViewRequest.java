package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ReviewViewRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("shared")
    private boolean shared;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("expected_version")
    private Long expectedVersion;

    @JsonProperty("filter")
    private Filter filter;

    @JsonCreator
    public ReviewViewRequest(
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("shared") boolean shared,
            @JsonProperty("expected_version") @Nullable Long expectedVersion,
            @JsonProperty("filter") @Nonnull Filter filter) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.shared = shared;
        this.expectedVersion = expectedVersion;
        this.filter =
                Optional.ofNullable(filter).orElseThrow(() -> new IllegalArgumentException("filter cannot be null"));
    }

    public ReviewViewRequest(@Nonnull String name, boolean shared, @Nonnull Filter filter) {
        this(name, shared, null, filter);
    }

    public String name() {
        return this.name;
    }

    public boolean shared() {
        return this.shared;
    }

    public Optional<Long> expectedVersion() {
        return Optional.ofNullable(this.expectedVersion);
    }

    public Filter filter() {
        return this.filter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewViewRequest withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    public ReviewViewRequest withShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public ReviewViewRequest withExpectedVersion(@Nullable Long expectedVersion) {
        this.expectedVersion = expectedVersion;
        return this;
    }

    public ReviewViewRequest withFilter(@Nonnull Filter filter) {
        this.filter = Utils.checkNotNull(filter, "filter");
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
        ReviewViewRequest other = (ReviewViewRequest) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.shared, other.shared)
                && Utils.enhancedDeepEquals(this.expectedVersion, other.expectedVersion)
                && Utils.enhancedDeepEquals(this.filter, other.filter);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, shared, expectedVersion, filter);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewViewRequest.class,
                "name",
                name,
                "shared",
                shared,
                "expectedVersion",
                expectedVersion,
                "filter",
                filter);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private boolean shared;

        private Long expectedVersion;

        private Filter filter;

        private Builder() {
            // force use of static builder() method
        }

        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        public Builder shared(boolean shared) {
            this.shared = shared;
            return this;
        }

        public Builder expectedVersion(@Nullable Long expectedVersion) {
            this.expectedVersion = expectedVersion;
            return this;
        }

        public Builder filter(@Nonnull Filter filter) {
            this.filter = Utils.checkNotNull(filter, "filter");
            return this;
        }

        public ReviewViewRequest build() {
            return new ReviewViewRequest(name, shared, expectedVersion, filter);
        }
    }
}
