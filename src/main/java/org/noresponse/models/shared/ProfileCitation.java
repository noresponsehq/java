package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ProfileCitation {
    /**
     * record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in
     * lookup.checks.
     */
    @JsonProperty("kind")
    private ProfileCitationKind kind;

    /**
     * The record or post id. For a check, c followed by its 1-based position in lookup.checks.
     */
    @JsonProperty("id")
    private String id;

    @JsonCreator
    public ProfileCitation(
            @JsonProperty("kind") @Nonnull ProfileCitationKind kind, @JsonProperty("id") @Nonnull String id) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
    }

    /**
     * record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in
     * lookup.checks.
     */
    public ProfileCitationKind kind() {
        return this.kind;
    }

    /**
     * The record or post id. For a check, c followed by its 1-based position in lookup.checks.
     */
    public String id() {
        return this.id;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in
     * lookup.checks.
     */
    public ProfileCitation withKind(@Nonnull ProfileCitationKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The record or post id. For a check, c followed by its 1-based position in lookup.checks.
     */
    public ProfileCitation withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
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
        ProfileCitation other = (ProfileCitation) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind) && Utils.enhancedDeepEquals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, id);
    }

    @Override
    public String toString() {
        return Utils.toString(ProfileCitation.class, "kind", kind, "id", id);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ProfileCitationKind kind;

        private String id;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * record: a record in lookup.records or profiles[].record. post: an item in posts. check: an item in
         * lookup.checks.
         */
        public Builder kind(@Nonnull ProfileCitationKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The record or post id. For a check, c followed by its 1-based position in lookup.checks.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        public ProfileCitation build() {
            return new ProfileCitation(kind, id);
        }
    }
}
