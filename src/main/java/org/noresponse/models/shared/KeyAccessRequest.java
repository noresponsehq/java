package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class KeyAccessRequest {
    /**
     * Explicit restrictions that override scope grants. An empty list restores the access supplied by the
     * scopes. Rotation preserves these restrictions.
     */
    @JsonProperty("denied_actions")
    private List<KeyAccessRequestDeniedAction> deniedActions;

    @JsonCreator
    public KeyAccessRequest(@JsonProperty("denied_actions") @Nonnull List<KeyAccessRequestDeniedAction> deniedActions) {
        this.deniedActions = Optional.ofNullable(deniedActions)
                .orElseThrow(() -> new IllegalArgumentException("deniedActions cannot be null"));
    }

    /**
     * Explicit restrictions that override scope grants. An empty list restores the access supplied by the
     * scopes. Rotation preserves these restrictions.
     */
    public List<KeyAccessRequestDeniedAction> deniedActions() {
        return this.deniedActions;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Explicit restrictions that override scope grants. An empty list restores the access supplied by the
     * scopes. Rotation preserves these restrictions.
     */
    public KeyAccessRequest withDeniedActions(@Nonnull List<KeyAccessRequestDeniedAction> deniedActions) {
        this.deniedActions = Utils.checkNotNull(deniedActions, "deniedActions");
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
        KeyAccessRequest other = (KeyAccessRequest) o;
        return Utils.enhancedDeepEquals(this.deniedActions, other.deniedActions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(deniedActions);
    }

    @Override
    public String toString() {
        return Utils.toString(KeyAccessRequest.class, "deniedActions", deniedActions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<KeyAccessRequestDeniedAction> deniedActions;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * Explicit restrictions that override scope grants. An empty list restores the access supplied by the
         * scopes. Rotation preserves these restrictions.
         */
        public Builder deniedActions(@Nonnull List<KeyAccessRequestDeniedAction> deniedActions) {
            this.deniedActions = Utils.checkNotNull(deniedActions, "deniedActions");
            return this;
        }

        public KeyAccessRequest build() {
            return new KeyAccessRequest(deniedActions);
        }
    }
}
