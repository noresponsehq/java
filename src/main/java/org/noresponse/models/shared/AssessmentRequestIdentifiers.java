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
 * AssessmentRequestIdentifiers
 *
 * <p>At least one of email, username or phone. name and ip refine the match and travel beside a lookup
 * identifier; a body with only name or ip is refused with 400.
 */
public class AssessmentRequestIdentifiers {
    /**
     * One address: a 1 to 64 character local part without whitespace, angle brackets, control or invisible
     * characters, '@', and a domain with a letter TLD. Send an internationalised domain in punycode.
     * Nothing is trimmed.
     *
     * <p>The local part keeps its case and the domain is lower-cased; no alias rewriting ('+tag', dots or
     * provider heuristics) is applied.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    /**
     * The handle the account is signing up with: 1 to 64 characters, no whitespace, control or invisible
     * characters, and not a credential hash or token. Echoed as received; compared case-insensitively.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("username")
    private String username;

    /**
     * Strict E.164: '+', then 8 to 15 digits, the first not 0, with no separators. Phone evidence is
     * gathered asynchronously, so a phone-only request answers 202 pending and the phone is listed in
     * incomplete_sources on the synchronous answer.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    /**
     * The full name as the user typed it, 1 to 128 characters with no control or invisible characters. It
     * is searched in breach data and matched against names in the evidence. A row holding the signup's own
     * email or username confirms the name; rows sharing another identifier add to the identity's history.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    /**
     * The account_creation's client IP address: an IPv4 dotted quad without leading zeros, or an IPv6
     * address in its RFC 5952 text form (lower case, no leading zeros, the longest zero run compressed).
     * Send an IPv4 client as a dotted quad, not IPv4-mapped. No zone, port or prefix.
     *
     * <p>The server refuses a non-canonical IPv6 spelling, and every private, loopback, link-local,
     * multicast, documentation or otherwise reserved address (the IANA special-purpose registries), with
     * 400: only a public address says anything about a signup.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("ip")
    private String ip;

    @JsonCreator
    public AssessmentRequestIdentifiers(
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

    public AssessmentRequestIdentifiers() {
        this(null, null, null, null, null);
    }

    /**
     * One address: a 1 to 64 character local part without whitespace, angle brackets, control or invisible
     * characters, '@', and a domain with a letter TLD. Send an internationalised domain in punycode.
     * Nothing is trimmed.
     *
     * <p>The local part keeps its case and the domain is lower-cased; no alias rewriting ('+tag', dots or
     * provider heuristics) is applied.
     */
    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    /**
     * The handle the account is signing up with: 1 to 64 characters, no whitespace, control or invisible
     * characters, and not a credential hash or token. Echoed as received; compared case-insensitively.
     */
    public Optional<String> username() {
        return Optional.ofNullable(this.username);
    }

    /**
     * Strict E.164: '+', then 8 to 15 digits, the first not 0, with no separators. Phone evidence is
     * gathered asynchronously, so a phone-only request answers 202 pending and the phone is listed in
     * incomplete_sources on the synchronous answer.
     */
    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    /**
     * The full name as the user typed it, 1 to 128 characters with no control or invisible characters. It
     * is searched in breach data and matched against names in the evidence. A row holding the signup's own
     * email or username confirms the name; rows sharing another identifier add to the identity's history.
     */
    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * The account_creation's client IP address: an IPv4 dotted quad without leading zeros, or an IPv6
     * address in its RFC 5952 text form (lower case, no leading zeros, the longest zero run compressed).
     * Send an IPv4 client as a dotted quad, not IPv4-mapped. No zone, port or prefix.
     *
     * <p>The server refuses a non-canonical IPv6 spelling, and every private, loopback, link-local,
     * multicast, documentation or otherwise reserved address (the IANA special-purpose registries), with
     * 400: only a public address says anything about a signup.
     */
    public Optional<String> ip() {
        return Optional.ofNullable(this.ip);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * One address: a 1 to 64 character local part without whitespace, angle brackets, control or invisible
     * characters, '@', and a domain with a letter TLD. Send an internationalised domain in punycode.
     * Nothing is trimmed.
     *
     * <p>The local part keeps its case and the domain is lower-cased; no alias rewriting ('+tag', dots or
     * provider heuristics) is applied.
     */
    public AssessmentRequestIdentifiers withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    /**
     * The handle the account is signing up with: 1 to 64 characters, no whitespace, control or invisible
     * characters, and not a credential hash or token. Echoed as received; compared case-insensitively.
     */
    public AssessmentRequestIdentifiers withUsername(@Nullable String username) {
        this.username = username;
        return this;
    }

    /**
     * Strict E.164: '+', then 8 to 15 digits, the first not 0, with no separators. Phone evidence is
     * gathered asynchronously, so a phone-only request answers 202 pending and the phone is listed in
     * incomplete_sources on the synchronous answer.
     */
    public AssessmentRequestIdentifiers withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * The full name as the user typed it, 1 to 128 characters with no control or invisible characters. It
     * is searched in breach data and matched against names in the evidence. A row holding the signup's own
     * email or username confirms the name; rows sharing another identifier add to the identity's history.
     */
    public AssessmentRequestIdentifiers withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    /**
     * The account_creation's client IP address: an IPv4 dotted quad without leading zeros, or an IPv6
     * address in its RFC 5952 text form (lower case, no leading zeros, the longest zero run compressed).
     * Send an IPv4 client as a dotted quad, not IPv4-mapped. No zone, port or prefix.
     *
     * <p>The server refuses a non-canonical IPv6 spelling, and every private, loopback, link-local,
     * multicast, documentation or otherwise reserved address (the IANA special-purpose registries), with
     * 400: only a public address says anything about a signup.
     */
    public AssessmentRequestIdentifiers withIp(@Nullable String ip) {
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
        AssessmentRequestIdentifiers other = (AssessmentRequestIdentifiers) o;
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
                AssessmentRequestIdentifiers.class,
                "email",
                email,
                "username",
                username,
                "phone",
                phone,
                "name",
                name,
                "ip",
                ip);
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
         * One address: a 1 to 64 character local part without whitespace, angle brackets, control or invisible
         * characters, '@', and a domain with a letter TLD. Send an internationalised domain in punycode.
         * Nothing is trimmed.
         *
         * <p>The local part keeps its case and the domain is lower-cased; no alias rewriting ('+tag', dots or
         * provider heuristics) is applied.
         */
        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        /**
         * The handle the account is signing up with: 1 to 64 characters, no whitespace, control or invisible
         * characters, and not a credential hash or token. Echoed as received; compared case-insensitively.
         */
        public Builder username(@Nullable String username) {
            this.username = username;
            return this;
        }

        /**
         * Strict E.164: '+', then 8 to 15 digits, the first not 0, with no separators. Phone evidence is
         * gathered asynchronously, so a phone-only request answers 202 pending and the phone is listed in
         * incomplete_sources on the synchronous answer.
         */
        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * The full name as the user typed it, 1 to 128 characters with no control or invisible characters. It
         * is searched in breach data and matched against names in the evidence. A row holding the signup's own
         * email or username confirms the name; rows sharing another identifier add to the identity's history.
         */
        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        /**
         * The account_creation's client IP address: an IPv4 dotted quad without leading zeros, or an IPv6
         * address in its RFC 5952 text form (lower case, no leading zeros, the longest zero run compressed).
         * Send an IPv4 client as a dotted quad, not IPv4-mapped. No zone, port or prefix.
         *
         * <p>The server refuses a non-canonical IPv6 spelling, and every private, loopback, link-local,
         * multicast, documentation or otherwise reserved address (the IANA special-purpose registries), with
         * 400: only a public address says anything about a signup.
         */
        public Builder ip(@Nullable String ip) {
            this.ip = ip;
            return this;
        }

        public AssessmentRequestIdentifiers build() {
            return new AssessmentRequestIdentifiers(email, username, phone, name, ip);
        }
    }
}
