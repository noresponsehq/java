package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * Principal
 *
 * <p>A person the event protects.
 */
public class Principal {
    /**
     * The principal's name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Other names the principal goes by, at most 10.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("aliases")
    private List<String> aliases;

    @JsonCreator
    public Principal(
            @JsonProperty("name") @Nonnull String name, @JsonProperty("aliases") @Nullable List<String> aliases) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.aliases = aliases;
    }

    public Principal(@Nonnull String name) {
        this(name, null);
    }

    /**
     * The principal's name.
     */
    public String name() {
        return this.name;
    }

    /**
     * Other names the principal goes by, at most 10.
     */
    public Optional<List<String>> aliases() {
        return Optional.ofNullable(this.aliases);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The principal's name.
     */
    public Principal withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * Other names the principal goes by, at most 10.
     */
    public Principal withAliases(@Nullable List<String> aliases) {
        this.aliases = aliases;
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
        Principal other = (Principal) o;
        return Utils.enhancedDeepEquals(this.name, other.name) && Utils.enhancedDeepEquals(this.aliases, other.aliases);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, aliases);
    }

    @Override
    public String toString() {
        return Utils.toString(Principal.class, "name", name, "aliases", aliases);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private List<String> aliases;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The principal's name.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * Other names the principal goes by, at most 10.
         */
        public Builder aliases(@Nullable List<String> aliases) {
            this.aliases = aliases;
            return this;
        }

        public Principal build() {
            return new Principal(name, aliases);
        }
    }
}
