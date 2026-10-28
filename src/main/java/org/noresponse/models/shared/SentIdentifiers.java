package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * SentIdentifiers
 *
 * <p>The identifiers an assessment was sent: each member of the request's identifiers object exactly as
 * received, before the server normalised it (the email domain keeps its case, the name its Unicode
 * form). A member the request did not send is absent. NoResponse keeps them encrypted under the
 * workspace's own key, does not index or log them, and deletes them with the assessment: on DELETE of
 * the assessment or its account, at its expires_at, and with the workspace.
 */
public class SentIdentifiers {
    /**
     * identifiers.email as the request sent it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * identifiers.username as the request sent it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private String username;

    /**
     * identifiers.phone as the request sent it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    /**
     * identifiers.name as the request sent it, before NFC normalisation.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * identifiers.ip as the request sent it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("ip")
    private String ip;

    @JsonCreator
    public SentIdentifiers(
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("username") @Nullable String username,
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("ip") @Nullable String ip) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.name = name;
        this.ip = ip;
    }

    public SentIdentifiers() {
        this(null, null, null, null, null);
    }

    /**
     * identifiers.email as the request sent it.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * identifiers.username as the request sent it.
     */
    public Optional<String> username() {
        return Optional.ofNullable(this.username);
    }

    /**
     * identifiers.phone as the request sent it.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * identifiers.name as the request sent it, before NFC normalisation.
     */
    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * identifiers.ip as the request sent it.
     */
    public Optional<String> ip() {
        return Optional.ofNullable(this.ip);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * identifiers.email as the request sent it.
     */
    public SentIdentifiers withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * identifiers.username as the request sent it.
     */
    public SentIdentifiers withUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    /**
     * identifiers.phone as the request sent it.
     */
    public SentIdentifiers withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * identifiers.name as the request sent it, before NFC normalisation.
     */
    public SentIdentifiers withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * identifiers.ip as the request sent it.
     */
    public SentIdentifiers withIp(@Nullable String ip) {
        this.ip = ip;
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
        SentIdentifiers other = (SentIdentifiers) o;
        return Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.username, other.username)
                && Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.ip, other.ip);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(email, username, phone, name, ip);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SentIdentifiers.class, "email", email, "username", username, "phone", phone, "name", name, "ip", ip);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String email;

        private String username;

        private String phone;

        private String name;

        private String ip;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * identifiers.email as the request sent it.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * identifiers.username as the request sent it.
         */
        public Builder username(@Nullable String username) {
            this.username = username;
            return this;
        }

        /**
         * identifiers.phone as the request sent it.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * identifiers.name as the request sent it, before NFC normalisation.
         */
        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * identifiers.ip as the request sent it.
         */
        public Builder ip(@Nullable String ip) {
            this.ip = ip;
            return this;
        }

        public SentIdentifiers build() {
            return new SentIdentifiers(email, username, phone, name, ip);
        }
    }
}
