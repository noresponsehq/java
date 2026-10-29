package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class UserActor {

    @JsonProperty("type")
    private String type;

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    @JsonProperty("external_user_id")
    private String externalUserId;

    /**
     * The public id of the console credential the user acted through.
     */
    @JsonProperty("console_credential_id")
    private String consoleCredentialId;

    /**
     * Stable workspace principal id, retained across identity-provider subject migration. Absent for older
     * events.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("principal_id")
    private String principalId;

    @JsonCreator
    public UserActor(
            @JsonProperty("external_user_id") @Nonnull String externalUserId,
            @JsonProperty("console_credential_id") @Nonnull String consoleCredentialId,
            @JsonProperty("principal_id") @Nullable String principalId) {
        this.type = Builder._SINGLETON_VALUE_Type.value();
        this.externalUserId = Optional.ofNullable(externalUserId)
                .orElseThrow(() -> new IllegalArgumentException("externalUserId cannot be null"));
        this.consoleCredentialId = Optional.ofNullable(consoleCredentialId)
                .orElseThrow(() -> new IllegalArgumentException("consoleCredentialId cannot be null"));
        this.principalId = principalId;
    }

    public UserActor(@Nonnull String externalUserId, @Nonnull String consoleCredentialId) {
        this(externalUserId, consoleCredentialId, null);
    }

    public String type() {
        return this.type;
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public String externalUserId() {
        return this.externalUserId;
    }

    /**
     * The public id of the console credential the user acted through.
     */
    public String consoleCredentialId() {
        return this.consoleCredentialId;
    }

    /**
     * Stable workspace principal id, retained across identity-provider subject migration. Absent for older
     * events.
     */
    public Optional<String> principalId() {
        return Optional.ofNullable(this.principalId);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
     * characters with no space, compared exactly.
     */
    public UserActor withExternalUserId(@Nonnull String externalUserId) {
        this.externalUserId = Utils.checkNotNull(externalUserId, "externalUserId");
        return this;
    }

    /**
     * The public id of the console credential the user acted through.
     */
    public UserActor withConsoleCredentialId(@Nonnull String consoleCredentialId) {
        this.consoleCredentialId = Utils.checkNotNull(consoleCredentialId, "consoleCredentialId");
        return this;
    }

    /**
     * Stable workspace principal id, retained across identity-provider subject migration. Absent for older
     * events.
     */
    public UserActor withPrincipalId(@Nullable String principalId) {
        this.principalId = principalId;
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
        UserActor other = (UserActor) o;
        return Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.externalUserId, other.externalUserId)
                && Utils.enhancedDeepEquals(this.consoleCredentialId, other.consoleCredentialId)
                && Utils.enhancedDeepEquals(this.principalId, other.principalId);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(type, externalUserId, consoleCredentialId, principalId);
    }

    @Override
    public String toString() {
        return Utils.toString(
                UserActor.class,
                "type",
                type,
                "externalUserId",
                externalUserId,
                "consoleCredentialId",
                consoleCredentialId,
                "principalId",
                principalId);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String externalUserId;

        private String consoleCredentialId;

        private String principalId;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The user's id at the identity provider the console signs people in with: 1 to 256 printable ASCII
         * characters with no space, compared exactly.
         */
        public Builder externalUserId(@Nonnull String externalUserId) {
            this.externalUserId = Utils.checkNotNull(externalUserId, "externalUserId");
            return this;
        }

        /**
         * The public id of the console credential the user acted through.
         */
        public Builder consoleCredentialId(@Nonnull String consoleCredentialId) {
            this.consoleCredentialId = Utils.checkNotNull(consoleCredentialId, "consoleCredentialId");
            return this;
        }

        /**
         * Stable workspace principal id, retained across identity-provider subject migration. Absent for older
         * events.
         */
        public Builder principalId(@Nullable String principalId) {
            this.principalId = principalId;
            return this;
        }

        public UserActor build() {
            return new UserActor(externalUserId, consoleCredentialId, principalId);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Type =
                new LazySingletonValue<>("type", "\"user\"", new TypeReference<String>() {});
    }
}
