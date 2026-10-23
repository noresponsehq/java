package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * IpSummary
 *
 * <p>Facts about the submitted IP from the offline database and this workspace's own recent assessments.
 * Every member is optional; absent makes no claim.
 */
public class IpSummary {
    /**
     * ISO 3166-1 alpha-2 country of the address.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private String country;

    /**
     * The address's network type.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("type")
    private IpSummaryType type;

    /**
     * Assessments in this workspace with the same IP in the last 24 hours, this one included. A count
     * only.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("seen_in_workspace")
    private Long seenInWorkspace;

    @JsonCreator
    public IpSummary(
            @JsonProperty("country") @Nullable String country,
            @JsonProperty("type") @Nullable IpSummaryType type,
            @JsonProperty("seen_in_workspace") @Nullable Long seenInWorkspace) {
        this.country = country;
        this.type = type;
        this.seenInWorkspace = seenInWorkspace;
    }

    public IpSummary() {
        this(null, null, null);
    }

    /**
     * ISO 3166-1 alpha-2 country of the address.
     */
    public Optional<String> country() {
        return Optional.ofNullable(this.country);
    }

    /**
     * The address's network type.
     */
    public Optional<IpSummaryType> type() {
        return Optional.ofNullable(this.type);
    }

    /**
     * Assessments in this workspace with the same IP in the last 24 hours, this one included. A count
     * only.
     */
    public Optional<Long> seenInWorkspace() {
        return Optional.ofNullable(this.seenInWorkspace);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * ISO 3166-1 alpha-2 country of the address.
     */
    public IpSummary withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    /**
     * The address's network type.
     */
    public IpSummary withType(@Nullable IpSummaryType type) {
        this.type = type;
        return this;
    }

    /**
     * Assessments in this workspace with the same IP in the last 24 hours, this one included. A count
     * only.
     */
    public IpSummary withSeenInWorkspace(@Nullable Long seenInWorkspace) {
        this.seenInWorkspace = seenInWorkspace;
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
        IpSummary other = (IpSummary) o;
        return Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.seenInWorkspace, other.seenInWorkspace);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(country, type, seenInWorkspace);
    }

    @Override
    public String toString() {
        return Utils.toString(IpSummary.class, "country", country, "type", type, "seenInWorkspace", seenInWorkspace);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String country;

        private IpSummaryType type;

        private Long seenInWorkspace;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * ISO 3166-1 alpha-2 country of the address.
         */
        public Builder country(@Nullable String country) {
            this.country = country;
            return this;
        }

        /**
         * The address's network type.
         */
        public Builder type(@Nullable IpSummaryType type) {
            this.type = type;
            return this;
        }

        /**
         * Assessments in this workspace with the same IP in the last 24 hours, this one included. A count
         * only.
         */
        public Builder seenInWorkspace(@Nullable Long seenInWorkspace) {
            this.seenInWorkspace = seenInWorkspace;
            return this;
        }

        public IpSummary build() {
            return new IpSummary(country, type, seenInWorkspace);
        }
    }
}
