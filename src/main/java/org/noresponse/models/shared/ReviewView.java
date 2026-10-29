package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ReviewView {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private String owner;

    /**
     * The credential namespace of the creator. An external user ID equal to an API-key label does not
     * grant ownership of that key's private view.
     */
    @JsonProperty("owner_type")
    private OwnerType ownerType;

    @JsonProperty("shared")
    private boolean shared;

    @JsonProperty("version")
    private long version;

    @JsonProperty("filter")
    private Filter filter;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Immutable member ID or API key ID, interpreted with owner_type. Ownership survives changes to an
     * external user identity.
     */
    @JsonProperty("owner_principal_id")
    private String ownerPrincipalId;

    @JsonCreator
    public ReviewView(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("owner") @Nonnull String owner,
            @JsonProperty("owner_type") @Nonnull OwnerType ownerType,
            @JsonProperty("shared") boolean shared,
            @JsonProperty("version") long version,
            @JsonProperty("filter") @Nonnull Filter filter,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("updated_at") @Nonnull String updatedAt,
            @JsonProperty("owner_principal_id") @Nonnull String ownerPrincipalId) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.owner = Optional.ofNullable(owner).orElseThrow(() -> new IllegalArgumentException("owner cannot be null"));
        this.ownerType = Optional.ofNullable(ownerType)
                .orElseThrow(() -> new IllegalArgumentException("ownerType cannot be null"));
        this.shared = shared;
        this.version = version;
        this.filter =
                Optional.ofNullable(filter).orElseThrow(() -> new IllegalArgumentException("filter cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.updatedAt = Optional.ofNullable(updatedAt)
                .orElseThrow(() -> new IllegalArgumentException("updatedAt cannot be null"));
        this.ownerPrincipalId = Optional.ofNullable(ownerPrincipalId)
                .orElseThrow(() -> new IllegalArgumentException("ownerPrincipalId cannot be null"));
    }

    public String id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String owner() {
        return this.owner;
    }

    /**
     * The credential namespace of the creator. An external user ID equal to an API-key label does not
     * grant ownership of that key's private view.
     */
    public OwnerType ownerType() {
        return this.ownerType;
    }

    public boolean shared() {
        return this.shared;
    }

    public long version() {
        return this.version;
    }

    public Filter filter() {
        return this.filter;
    }

    public String createdAt() {
        return this.createdAt;
    }

    public String updatedAt() {
        return this.updatedAt;
    }

    /**
     * Immutable member ID or API key ID, interpreted with owner_type. Ownership survives changes to an
     * external user identity.
     */
    public String ownerPrincipalId() {
        return this.ownerPrincipalId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ReviewView withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    public ReviewView withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    public ReviewView withOwner(@Nonnull String owner) {
        this.owner = Utils.checkNotNull(owner, "owner");
        return this;
    }

    /**
     * The credential namespace of the creator. An external user ID equal to an API-key label does not
     * grant ownership of that key's private view.
     */
    public ReviewView withOwnerType(@Nonnull OwnerType ownerType) {
        this.ownerType = Utils.checkNotNull(ownerType, "ownerType");
        return this;
    }

    public ReviewView withShared(boolean shared) {
        this.shared = shared;
        return this;
    }

    public ReviewView withVersion(long version) {
        this.version = version;
        return this;
    }

    public ReviewView withFilter(@Nonnull Filter filter) {
        this.filter = Utils.checkNotNull(filter, "filter");
        return this;
    }

    public ReviewView withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    public ReviewView withUpdatedAt(@Nonnull String updatedAt) {
        this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
        return this;
    }

    /**
     * Immutable member ID or API key ID, interpreted with owner_type. Ownership survives changes to an
     * external user identity.
     */
    public ReviewView withOwnerPrincipalId(@Nonnull String ownerPrincipalId) {
        this.ownerPrincipalId = Utils.checkNotNull(ownerPrincipalId, "ownerPrincipalId");
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
        ReviewView other = (ReviewView) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.owner, other.owner)
                && Utils.enhancedDeepEquals(this.ownerType, other.ownerType)
                && Utils.enhancedDeepEquals(this.shared, other.shared)
                && Utils.enhancedDeepEquals(this.version, other.version)
                && Utils.enhancedDeepEquals(this.filter, other.filter)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.updatedAt, other.updatedAt)
                && Utils.enhancedDeepEquals(this.ownerPrincipalId, other.ownerPrincipalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                id, name, owner, ownerType, shared, version, filter, createdAt, updatedAt, ownerPrincipalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ReviewView.class,
                "id",
                id,
                "name",
                name,
                "owner",
                owner,
                "ownerType",
                ownerType,
                "shared",
                shared,
                "version",
                version,
                "filter",
                filter,
                "createdAt",
                createdAt,
                "updatedAt",
                updatedAt,
                "ownerPrincipalId",
                ownerPrincipalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String name;

        private String owner;

        private OwnerType ownerType;

        private boolean shared;

        private long version;

        private Filter filter;

        private String createdAt;

        private String updatedAt;

        private String ownerPrincipalId;

        private Builder() {
            // force use of static builder() method
        }

        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        public Builder owner(@Nonnull String owner) {
            this.owner = Utils.checkNotNull(owner, "owner");
            return this;
        }

        /**
         * The credential namespace of the creator. An external user ID equal to an API-key label does not
         * grant ownership of that key's private view.
         */
        public Builder ownerType(@Nonnull OwnerType ownerType) {
            this.ownerType = Utils.checkNotNull(ownerType, "ownerType");
            return this;
        }

        public Builder shared(boolean shared) {
            this.shared = shared;
            return this;
        }

        public Builder version(long version) {
            this.version = version;
            return this;
        }

        public Builder filter(@Nonnull Filter filter) {
            this.filter = Utils.checkNotNull(filter, "filter");
            return this;
        }

        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        public Builder updatedAt(@Nonnull String updatedAt) {
            this.updatedAt = Utils.checkNotNull(updatedAt, "updatedAt");
            return this;
        }

        /**
         * Immutable member ID or API key ID, interpreted with owner_type. Ownership survives changes to an
         * external user identity.
         */
        public Builder ownerPrincipalId(@Nonnull String ownerPrincipalId) {
            this.ownerPrincipalId = Utils.checkNotNull(ownerPrincipalId, "ownerPrincipalId");
            return this;
        }

        public ReviewView build() {
            return new ReviewView(
                    id, name, owner, ownerType, shared, version, filter, createdAt, updatedAt, ownerPrincipalId);
        }
    }
}
