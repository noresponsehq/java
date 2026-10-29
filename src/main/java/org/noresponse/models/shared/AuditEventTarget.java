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

public class AuditEventTarget {
    /**
     * The kind of resource the change acted on.
     */
    @JsonProperty("type")
    private AuditEventTargetType type;

    /**
     * The resource's id, when it has one.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("id")
    private String id;

    @JsonCreator
    public AuditEventTarget(
            @JsonProperty("type") @Nonnull AuditEventTargetType type, @JsonProperty("id") @Nullable String id) {
        this.type = Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("type cannot be null"));
        this.id = id;
    }

    public AuditEventTarget(@Nonnull AuditEventTargetType type) {
        this(type, null);
    }

    /**
     * The kind of resource the change acted on.
     */
    public AuditEventTargetType type() {
        return this.type;
    }

    /**
     * The resource's id, when it has one.
     */
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The kind of resource the change acted on.
     */
    public AuditEventTarget withType(@Nonnull AuditEventTargetType type) {
        this.type = Utils.checkNotNull(type, "type");
        return this;
    }

    /**
     * The resource's id, when it has one.
     */
    public AuditEventTarget withId(@Nullable String id) {
        this.id = id;
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
        AuditEventTarget other = (AuditEventTarget) o;
        return Utils.enhancedDeepEquals(this.type, other.type) && Utils.enhancedDeepEquals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(type, id);
    }

    @Override
    public String toString() {
        return Utils.toString(AuditEventTarget.class, "type", type, "id", id);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AuditEventTargetType type;

        private String id;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The kind of resource the change acted on.
         */
        public Builder type(@Nonnull AuditEventTargetType type) {
            this.type = Utils.checkNotNull(type, "type");
            return this;
        }

        /**
         * The resource's id, when it has one.
         */
        public Builder id(@Nullable String id) {
            this.id = id;
            return this;
        }

        public AuditEventTarget build() {
            return new AuditEventTarget(type, id);
        }
    }
}
