package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ErasureReceiptTarget {

    @JsonProperty("name")
    private ErasureReceiptTargetName name;

    @JsonProperty("status")
    private ErasureReceiptTargetStatus status;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("completed_at")
    private String completedAt;

    @JsonCreator
    public ErasureReceiptTarget(
            @JsonProperty("name") @Nonnull ErasureReceiptTargetName name,
            @JsonProperty("status") @Nonnull ErasureReceiptTargetStatus status,
            @JsonProperty("completed_at") @Nullable String completedAt) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.completedAt = completedAt;
    }

    public ErasureReceiptTarget(@Nonnull ErasureReceiptTargetName name, @Nonnull ErasureReceiptTargetStatus status) {
        this(name, status, null);
    }

    public ErasureReceiptTargetName name() {
        return this.name;
    }

    public ErasureReceiptTargetStatus status() {
        return this.status;
    }

    public Optional<String> completedAt() {
        return Optional.ofNullable(this.completedAt);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ErasureReceiptTarget withName(@Nonnull ErasureReceiptTargetName name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    public ErasureReceiptTarget withStatus(@Nonnull ErasureReceiptTargetStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    public ErasureReceiptTarget withCompletedAt(@Nullable String completedAt) {
        this.completedAt = completedAt;
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
        ErasureReceiptTarget other = (ErasureReceiptTarget) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.completedAt, other.completedAt);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, status, completedAt);
    }

    @Override
    public String toString() {
        return Utils.toString(ErasureReceiptTarget.class, "name", name, "status", status, "completedAt", completedAt);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ErasureReceiptTargetName name;

        private ErasureReceiptTargetStatus status;

        private String completedAt;

        private Builder() {
            // force use of static builder() method
        }

        public Builder name(@Nonnull ErasureReceiptTargetName name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        public Builder status(@Nonnull ErasureReceiptTargetStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        public Builder completedAt(@Nullable String completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public ErasureReceiptTarget build() {
            return new ErasureReceiptTarget(name, status, completedAt);
        }
    }
}
