package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * BreachSummary
 *
 * <p>Breach facts for one selector. found is the one required member: false is a definite 'in no breach
 * table'. Every other member is optional, and absent makes no claim.
 *
 * <p>No member carries a breach value, password or hash, and unknown members are refused. truncated means
 * the value matched more tables or records than the lookup keeps, so absence-based claims (a missing
 * name match, for example) are withheld; a truncated result is always found.
 */
public class BreachSummary {

    @JsonProperty("found")
    private boolean found;

    /**
     * Distinct breach tables the value appears in.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("breach_count")
    private Long breachCount;

    /**
     * Year of the oldest dated table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("first_year")
    private Long firstYear;

    /**
     * Year of the newest dated table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_year")
    private Long lastYear;

    /**
     * The union of data kinds the matching tables held, without the values.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("exposed")
    private List<BreachExposed> exposed;

    /**
     * The value is in a credential_list or stealer_log table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("in_credential_lists")
    private Boolean inCredentialLists;

    /**
     * A plaintext password sat next to the value in a table dated two years ago or less, or the value is
     * in a stealer_log table.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("recent_password_exposure")
    private Boolean recentPasswordExposure;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("truncated")
    private Boolean truncated;

    @JsonCreator
    public BreachSummary(
            @JsonProperty("found") boolean found,
            @JsonProperty("breach_count") @Nullable Long breachCount,
            @JsonProperty("first_year") @Nullable Long firstYear,
            @JsonProperty("last_year") @Nullable Long lastYear,
            @JsonProperty("exposed") @Nullable List<BreachExposed> exposed,
            @JsonProperty("in_credential_lists") @Nullable Boolean inCredentialLists,
            @JsonProperty("recent_password_exposure") @Nullable Boolean recentPasswordExposure,
            @JsonProperty("truncated") @Nullable Boolean truncated) {
        this.found = found;
        this.breachCount = breachCount;
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.exposed = exposed;
        this.inCredentialLists = inCredentialLists;
        this.recentPasswordExposure = recentPasswordExposure;
        this.truncated = truncated;
    }

    public BreachSummary(boolean found) {
        this(found, null, null, null, null, null, null, null);
    }

    public boolean found() {
        return this.found;
    }

    /**
     * Distinct breach tables the value appears in.
     */
    public Optional<Long> breachCount() {
        return Optional.ofNullable(this.breachCount);
    }

    /**
     * Year of the oldest dated table.
     */
    public Optional<Long> firstYear() {
        return Optional.ofNullable(this.firstYear);
    }

    /**
     * Year of the newest dated table.
     */
    public Optional<Long> lastYear() {
        return Optional.ofNullable(this.lastYear);
    }

    /**
     * The union of data kinds the matching tables held, without the values.
     */
    public Optional<List<BreachExposed>> exposed() {
        return Optional.ofNullable(this.exposed);
    }

    /**
     * The value is in a credential_list or stealer_log table.
     */
    public Optional<Boolean> inCredentialLists() {
        return Optional.ofNullable(this.inCredentialLists);
    }

    /**
     * A plaintext password sat next to the value in a table dated two years ago or less, or the value is
     * in a stealer_log table.
     */
    public Optional<Boolean> recentPasswordExposure() {
        return Optional.ofNullable(this.recentPasswordExposure);
    }

    public Optional<Boolean> truncated() {
        return Optional.ofNullable(this.truncated);
    }

    public static Builder builder() {
        return new Builder();
    }

    public BreachSummary withFound(boolean found) {
        this.found = found;
        return this;
    }

    /**
     * Distinct breach tables the value appears in.
     */
    public BreachSummary withBreachCount(@Nullable Long breachCount) {
        this.breachCount = breachCount;
        return this;
    }

    /**
     * Year of the oldest dated table.
     */
    public BreachSummary withFirstYear(@Nullable Long firstYear) {
        this.firstYear = firstYear;
        return this;
    }

    /**
     * Year of the newest dated table.
     */
    public BreachSummary withLastYear(@Nullable Long lastYear) {
        this.lastYear = lastYear;
        return this;
    }

    /**
     * The union of data kinds the matching tables held, without the values.
     */
    public BreachSummary withExposed(@Nullable List<BreachExposed> exposed) {
        this.exposed = exposed;
        return this;
    }

    /**
     * The value is in a credential_list or stealer_log table.
     */
    public BreachSummary withInCredentialLists(@Nullable Boolean inCredentialLists) {
        this.inCredentialLists = inCredentialLists;
        return this;
    }

    /**
     * A plaintext password sat next to the value in a table dated two years ago or less, or the value is
     * in a stealer_log table.
     */
    public BreachSummary withRecentPasswordExposure(@Nullable Boolean recentPasswordExposure) {
        this.recentPasswordExposure = recentPasswordExposure;
        return this;
    }

    public BreachSummary withTruncated(@Nullable Boolean truncated) {
        this.truncated = truncated;
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
        BreachSummary other = (BreachSummary) o;
        return Utils.enhancedDeepEquals(this.found, other.found)
                && Utils.enhancedDeepEquals(this.breachCount, other.breachCount)
                && Utils.enhancedDeepEquals(this.firstYear, other.firstYear)
                && Utils.enhancedDeepEquals(this.lastYear, other.lastYear)
                && Utils.enhancedDeepEquals(this.exposed, other.exposed)
                && Utils.enhancedDeepEquals(this.inCredentialLists, other.inCredentialLists)
                && Utils.enhancedDeepEquals(this.recentPasswordExposure, other.recentPasswordExposure)
                && Utils.enhancedDeepEquals(this.truncated, other.truncated);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                found, breachCount, firstYear, lastYear, exposed, inCredentialLists, recentPasswordExposure, truncated);
    }

    @Override
    public String toString() {
        return Utils.toString(
                BreachSummary.class,
                "found",
                found,
                "breachCount",
                breachCount,
                "firstYear",
                firstYear,
                "lastYear",
                lastYear,
                "exposed",
                exposed,
                "inCredentialLists",
                inCredentialLists,
                "recentPasswordExposure",
                recentPasswordExposure,
                "truncated",
                truncated);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private boolean found;

        private Long breachCount;

        private Long firstYear;

        private Long lastYear;

        private List<BreachExposed> exposed;

        private Boolean inCredentialLists;

        private Boolean recentPasswordExposure;

        private Boolean truncated;

        private Builder() {
            // force use of static builder() method
        }

        public Builder found(boolean found) {
            this.found = found;
            return this;
        }

        /**
         * Distinct breach tables the value appears in.
         */
        public Builder breachCount(@Nullable Long breachCount) {
            this.breachCount = breachCount;
            return this;
        }

        /**
         * Year of the oldest dated table.
         */
        public Builder firstYear(@Nullable Long firstYear) {
            this.firstYear = firstYear;
            return this;
        }

        /**
         * Year of the newest dated table.
         */
        public Builder lastYear(@Nullable Long lastYear) {
            this.lastYear = lastYear;
            return this;
        }

        /**
         * The union of data kinds the matching tables held, without the values.
         */
        public Builder exposed(@Nullable List<BreachExposed> exposed) {
            this.exposed = exposed;
            return this;
        }

        /**
         * The value is in a credential_list or stealer_log table.
         */
        public Builder inCredentialLists(@Nullable Boolean inCredentialLists) {
            this.inCredentialLists = inCredentialLists;
            return this;
        }

        /**
         * A plaintext password sat next to the value in a table dated two years ago or less, or the value is
         * in a stealer_log table.
         */
        public Builder recentPasswordExposure(@Nullable Boolean recentPasswordExposure) {
            this.recentPasswordExposure = recentPasswordExposure;
            return this;
        }

        public Builder truncated(@Nullable Boolean truncated) {
            this.truncated = truncated;
            return this;
        }

        public BreachSummary build() {
            return new BreachSummary(
                    found,
                    breachCount,
                    firstYear,
                    lastYear,
                    exposed,
                    inCredentialLists,
                    recentPasswordExposure,
                    truncated);
        }
    }
}
