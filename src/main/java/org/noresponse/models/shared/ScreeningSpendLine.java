package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningSpendLine
 *
 * <p>What one implementation cost.
 */
public class ScreeningSpendLine {
    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    @JsonProperty("implementation")
    private String implementation;

    @JsonProperty("role")
    private ScreeningSpendLineRole role;

    @JsonProperty("spend_minor")
    private long spendMinor;

    @JsonCreator
    public ScreeningSpendLine(
            @JsonProperty("implementation") @Nonnull String implementation,
            @JsonProperty("role") @Nonnull ScreeningSpendLineRole role,
            @JsonProperty("spend_minor") long spendMinor) {
        this.implementation = Optional.ofNullable(implementation)
                .orElseThrow(() -> new IllegalArgumentException("implementation cannot be null"));
        this.role = Optional.ofNullable(role).orElseThrow(() -> new IllegalArgumentException("role cannot be null"));
        this.spendMinor = spendMinor;
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public String implementation() {
        return this.implementation;
    }

    public ScreeningSpendLineRole role() {
        return this.role;
    }

    public long spendMinor() {
        return this.spendMinor;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public ScreeningSpendLine withImplementation(@Nonnull String implementation) {
        this.implementation = Utils.checkNotNull(implementation, "implementation");
        return this;
    }

    public ScreeningSpendLine withRole(@Nonnull ScreeningSpendLineRole role) {
        this.role = Utils.checkNotNull(role, "role");
        return this;
    }

    public ScreeningSpendLine withSpendMinor(long spendMinor) {
        this.spendMinor = spendMinor;
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
        ScreeningSpendLine other = (ScreeningSpendLine) o;
        return Utils.enhancedDeepEquals(this.implementation, other.implementation)
                && Utils.enhancedDeepEquals(this.role, other.role)
                && Utils.enhancedDeepEquals(this.spendMinor, other.spendMinor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(implementation, role, spendMinor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningSpendLine.class, "implementation", implementation, "role", role, "spendMinor", spendMinor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String implementation;

        private ScreeningSpendLineRole role;

        private long spendMinor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The resolver, collector or analyser that produced a record, by its catalogue name.
         */
        public Builder implementation(@Nonnull String implementation) {
            this.implementation = Utils.checkNotNull(implementation, "implementation");
            return this;
        }

        public Builder role(@Nonnull ScreeningSpendLineRole role) {
            this.role = Utils.checkNotNull(role, "role");
            return this;
        }

        public Builder spendMinor(long spendMinor) {
            this.spendMinor = spendMinor;
            return this;
        }

        public ScreeningSpendLine build() {
            return new ScreeningSpendLine(implementation, role, spendMinor);
        }
    }
}
