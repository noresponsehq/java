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

public class FootprintCoherence {
    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("birth_date")
    private AttributeAgreement birthDate;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("gender")
    private AttributeAgreement gender;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("nationality")
    private AttributeAgreement nationality;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private AttributeAgreement country;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private AttributeAgreement city;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("zip")
    private AttributeAgreement zip;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private AttributeAgreement name;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("company")
    private AttributeAgreement company;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("website")
    private AttributeAgreement website;

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("social")
    private AttributeAgreement social;

    @JsonCreator
    public FootprintCoherence(
            @JsonProperty("birth_date") @Nullable AttributeAgreement birthDate,
            @JsonProperty("gender") @Nullable AttributeAgreement gender,
            @JsonProperty("nationality") @Nullable AttributeAgreement nationality,
            @JsonProperty("country") @Nullable AttributeAgreement country,
            @JsonProperty("city") @Nullable AttributeAgreement city,
            @JsonProperty("zip") @Nullable AttributeAgreement zip,
            @JsonProperty("name") @Nullable AttributeAgreement name,
            @JsonProperty("company") @Nullable AttributeAgreement company,
            @JsonProperty("website") @Nullable AttributeAgreement website,
            @JsonProperty("social") @Nullable AttributeAgreement social) {
        this.birthDate = birthDate;
        this.gender = gender;
        this.nationality = nationality;
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.name = name;
        this.company = company;
        this.website = website;
        this.social = social;
    }

    public FootprintCoherence() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> birthDate() {
        return Optional.ofNullable(this.birthDate);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> gender() {
        return Optional.ofNullable(this.gender);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> nationality() {
        return Optional.ofNullable(this.nationality);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> country() {
        return Optional.ofNullable(this.country);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> city() {
        return Optional.ofNullable(this.city);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> zip() {
        return Optional.ofNullable(this.zip);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> name() {
        return Optional.ofNullable(this.name);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> company() {
        return Optional.ofNullable(this.company);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> website() {
        return Optional.ofNullable(this.website);
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public Optional<AttributeAgreement> social() {
        return Optional.ofNullable(this.social);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withBirthDate(@Nullable AttributeAgreement birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withGender(@Nullable AttributeAgreement gender) {
        this.gender = gender;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withNationality(@Nullable AttributeAgreement nationality) {
        this.nationality = nationality;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withCountry(@Nullable AttributeAgreement country) {
        this.country = country;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withCity(@Nullable AttributeAgreement city) {
        this.city = city;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withZip(@Nullable AttributeAgreement zip) {
        this.zip = zip;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withName(@Nullable AttributeAgreement name) {
        this.name = name;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withCompany(@Nullable AttributeAgreement company) {
        this.company = company;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withWebsite(@Nullable AttributeAgreement website) {
        this.website = website;
        return this;
    }

    /**
     * How far independent sources agree on one attribute: values is the number of distinct values
     * reported, support the tables and profiles reporting any, and agreement match (one value), partial
     * (the most reported value holds more than half the support) or mismatch. The values themselves are
     * not served.
     */
    public FootprintCoherence withSocial(@Nullable AttributeAgreement social) {
        this.social = social;
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
        FootprintCoherence other = (FootprintCoherence) o;
        return Utils.enhancedDeepEquals(this.birthDate, other.birthDate)
                && Utils.enhancedDeepEquals(this.gender, other.gender)
                && Utils.enhancedDeepEquals(this.nationality, other.nationality)
                && Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.zip, other.zip)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.company, other.company)
                && Utils.enhancedDeepEquals(this.website, other.website)
                && Utils.enhancedDeepEquals(this.social, other.social);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(birthDate, gender, nationality, country, city, zip, name, company, website, social);
    }

    @Override
    public String toString() {
        return Utils.toString(
                FootprintCoherence.class,
                "birthDate",
                birthDate,
                "gender",
                gender,
                "nationality",
                nationality,
                "country",
                country,
                "city",
                city,
                "zip",
                zip,
                "name",
                name,
                "company",
                company,
                "website",
                website,
                "social",
                social);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private AttributeAgreement birthDate;

        private AttributeAgreement gender;

        private AttributeAgreement nationality;

        private AttributeAgreement country;

        private AttributeAgreement city;

        private AttributeAgreement zip;

        private AttributeAgreement name;

        private AttributeAgreement company;

        private AttributeAgreement website;

        private AttributeAgreement social;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder birthDate(@Nullable AttributeAgreement birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder gender(@Nullable AttributeAgreement gender) {
            this.gender = gender;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder nationality(@Nullable AttributeAgreement nationality) {
            this.nationality = nationality;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder country(@Nullable AttributeAgreement country) {
            this.country = country;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder city(@Nullable AttributeAgreement city) {
            this.city = city;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder zip(@Nullable AttributeAgreement zip) {
            this.zip = zip;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder name(@Nullable AttributeAgreement name) {
            this.name = name;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder company(@Nullable AttributeAgreement company) {
            this.company = company;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder website(@Nullable AttributeAgreement website) {
            this.website = website;
            return this;
        }

        /**
         * How far independent sources agree on one attribute: values is the number of distinct values
         * reported, support the tables and profiles reporting any, and agreement match (one value), partial
         * (the most reported value holds more than half the support) or mismatch. The values themselves are
         * not served.
         */
        public Builder social(@Nullable AttributeAgreement social) {
            this.social = social;
            return this;
        }

        public FootprintCoherence build() {
            return new FootprintCoherence(
                    birthDate, gender, nationality, country, city, zip, name, company, website, social);
        }
    }
}
