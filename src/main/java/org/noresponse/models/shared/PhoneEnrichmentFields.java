package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class PhoneEnrichmentFields {

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("phone")
    private String phone;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name")
    private String name;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("carrier")
    private String carrier;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("line_type")
    private String lineType;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("entity_type")
    private String entityType;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country_code")
    private String countryCode;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("country")
    private String country;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("state")
    private String state;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private String city;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("location")
    private String location;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("national_format")
    private String nationalFormat;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("job_title")
    private String jobTitle;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("company_name")
    private String companyName;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("email")
    private String email;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("website")
    private String website;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("first_call_at")
    private String firstCallAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("last_call_at")
    private String lastCallAt;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("matched_name")
    private String matchedName;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("alternate_names")
    private List<AlternateNameUnion> alternateNames;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("name_candidates")
    private List<NameCandidateUnion> nameCandidates;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("timezones")
    private List<TimezoneUnion> timezones;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("emails")
    private List<EmailUnion> emails;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("websites")
    private List<WebsiteUnion> websites;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("locations")
    private List<LocationUnion> locations;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("social_profiles")
    private List<SocialProfileUnion> socialProfiles;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("report_count")
    private Long reportCount;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("tag_count")
    private Long tagCount;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("total_calls")
    private Long totalCalls;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("calls_60_days")
    private Long calls60Days;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reports_60_days")
    private Long reports60Days;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("searches_60_days")
    private Long searches60Days;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("comment_count")
    private Long commentCount;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("names_count")
    private Long namesCount;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("spam_report_count")
    private Long spamReportCount;

    @JsonCreator
    public PhoneEnrichmentFields(
            @JsonProperty("phone") @Nullable String phone,
            @JsonProperty("name") @Nullable String name,
            @JsonProperty("carrier") @Nullable String carrier,
            @JsonProperty("line_type") @Nullable String lineType,
            @JsonProperty("entity_type") @Nullable String entityType,
            @JsonProperty("country_code") @Nullable String countryCode,
            @JsonProperty("country") @Nullable String country,
            @JsonProperty("state") @Nullable String state,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("location") @Nullable String location,
            @JsonProperty("national_format") @Nullable String nationalFormat,
            @JsonProperty("job_title") @Nullable String jobTitle,
            @JsonProperty("company_name") @Nullable String companyName,
            @JsonProperty("email") @Nullable String email,
            @JsonProperty("website") @Nullable String website,
            @JsonProperty("first_call_at") @Nullable String firstCallAt,
            @JsonProperty("last_call_at") @Nullable String lastCallAt,
            @JsonProperty("matched_name") @Nullable String matchedName,
            @JsonProperty("alternate_names") @Nullable List<AlternateNameUnion> alternateNames,
            @JsonProperty("name_candidates") @Nullable List<NameCandidateUnion> nameCandidates,
            @JsonProperty("timezones") @Nullable List<TimezoneUnion> timezones,
            @JsonProperty("emails") @Nullable List<EmailUnion> emails,
            @JsonProperty("websites") @Nullable List<WebsiteUnion> websites,
            @JsonProperty("locations") @Nullable List<LocationUnion> locations,
            @JsonProperty("social_profiles") @Nullable List<SocialProfileUnion> socialProfiles,
            @JsonProperty("report_count") @Nullable Long reportCount,
            @JsonProperty("tag_count") @Nullable Long tagCount,
            @JsonProperty("total_calls") @Nullable Long totalCalls,
            @JsonProperty("calls_60_days") @Nullable Long calls60Days,
            @JsonProperty("reports_60_days") @Nullable Long reports60Days,
            @JsonProperty("searches_60_days") @Nullable Long searches60Days,
            @JsonProperty("comment_count") @Nullable Long commentCount,
            @JsonProperty("names_count") @Nullable Long namesCount,
            @JsonProperty("spam_report_count") @Nullable Long spamReportCount) {
        this.phone = phone;
        this.name = name;
        this.carrier = carrier;
        this.lineType = lineType;
        this.entityType = entityType;
        this.countryCode = countryCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.location = location;
        this.nationalFormat = nationalFormat;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.email = email;
        this.website = website;
        this.firstCallAt = firstCallAt;
        this.lastCallAt = lastCallAt;
        this.matchedName = matchedName;
        this.alternateNames = alternateNames;
        this.nameCandidates = nameCandidates;
        this.timezones = timezones;
        this.emails = emails;
        this.websites = websites;
        this.locations = locations;
        this.socialProfiles = socialProfiles;
        this.reportCount = reportCount;
        this.tagCount = tagCount;
        this.totalCalls = totalCalls;
        this.calls60Days = calls60Days;
        this.reports60Days = reports60Days;
        this.searches60Days = searches60Days;
        this.commentCount = commentCount;
        this.namesCount = namesCount;
        this.spamReportCount = spamReportCount;
    }

    public PhoneEnrichmentFields() {
        this(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Optional<String> phone() {
        return Optional.ofNullable(this.phone);
    }

    public Optional<String> name() {
        return Optional.ofNullable(this.name);
    }

    public Optional<String> carrier() {
        return Optional.ofNullable(this.carrier);
    }

    public Optional<String> lineType() {
        return Optional.ofNullable(this.lineType);
    }

    public Optional<String> entityType() {
        return Optional.ofNullable(this.entityType);
    }

    public Optional<String> countryCode() {
        return Optional.ofNullable(this.countryCode);
    }

    public Optional<String> country() {
        return Optional.ofNullable(this.country);
    }

    public Optional<String> state() {
        return Optional.ofNullable(this.state);
    }

    public Optional<String> city() {
        return Optional.ofNullable(this.city);
    }

    public Optional<String> location() {
        return Optional.ofNullable(this.location);
    }

    public Optional<String> nationalFormat() {
        return Optional.ofNullable(this.nationalFormat);
    }

    public Optional<String> jobTitle() {
        return Optional.ofNullable(this.jobTitle);
    }

    public Optional<String> companyName() {
        return Optional.ofNullable(this.companyName);
    }

    public Optional<String> email() {
        return Optional.ofNullable(this.email);
    }

    public Optional<String> website() {
        return Optional.ofNullable(this.website);
    }

    public Optional<String> firstCallAt() {
        return Optional.ofNullable(this.firstCallAt);
    }

    public Optional<String> lastCallAt() {
        return Optional.ofNullable(this.lastCallAt);
    }

    public Optional<String> matchedName() {
        return Optional.ofNullable(this.matchedName);
    }

    public Optional<List<AlternateNameUnion>> alternateNames() {
        return Optional.ofNullable(this.alternateNames);
    }

    public Optional<List<NameCandidateUnion>> nameCandidates() {
        return Optional.ofNullable(this.nameCandidates);
    }

    public Optional<List<TimezoneUnion>> timezones() {
        return Optional.ofNullable(this.timezones);
    }

    public Optional<List<EmailUnion>> emails() {
        return Optional.ofNullable(this.emails);
    }

    public Optional<List<WebsiteUnion>> websites() {
        return Optional.ofNullable(this.websites);
    }

    public Optional<List<LocationUnion>> locations() {
        return Optional.ofNullable(this.locations);
    }

    public Optional<List<SocialProfileUnion>> socialProfiles() {
        return Optional.ofNullable(this.socialProfiles);
    }

    public Optional<Long> reportCount() {
        return Optional.ofNullable(this.reportCount);
    }

    public Optional<Long> tagCount() {
        return Optional.ofNullable(this.tagCount);
    }

    public Optional<Long> totalCalls() {
        return Optional.ofNullable(this.totalCalls);
    }

    public Optional<Long> calls60Days() {
        return Optional.ofNullable(this.calls60Days);
    }

    public Optional<Long> reports60Days() {
        return Optional.ofNullable(this.reports60Days);
    }

    public Optional<Long> searches60Days() {
        return Optional.ofNullable(this.searches60Days);
    }

    public Optional<Long> commentCount() {
        return Optional.ofNullable(this.commentCount);
    }

    public Optional<Long> namesCount() {
        return Optional.ofNullable(this.namesCount);
    }

    public Optional<Long> spamReportCount() {
        return Optional.ofNullable(this.spamReportCount);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PhoneEnrichmentFields withPhone(@Nullable String phone) {
        this.phone = phone;
        return this;
    }

    public PhoneEnrichmentFields withName(@Nullable String name) {
        this.name = name;
        return this;
    }

    public PhoneEnrichmentFields withCarrier(@Nullable String carrier) {
        this.carrier = carrier;
        return this;
    }

    public PhoneEnrichmentFields withLineType(@Nullable String lineType) {
        this.lineType = lineType;
        return this;
    }

    public PhoneEnrichmentFields withEntityType(@Nullable String entityType) {
        this.entityType = entityType;
        return this;
    }

    public PhoneEnrichmentFields withCountryCode(@Nullable String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public PhoneEnrichmentFields withCountry(@Nullable String country) {
        this.country = country;
        return this;
    }

    public PhoneEnrichmentFields withState(@Nullable String state) {
        this.state = state;
        return this;
    }

    public PhoneEnrichmentFields withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    public PhoneEnrichmentFields withLocation(@Nullable String location) {
        this.location = location;
        return this;
    }

    public PhoneEnrichmentFields withNationalFormat(@Nullable String nationalFormat) {
        this.nationalFormat = nationalFormat;
        return this;
    }

    public PhoneEnrichmentFields withJobTitle(@Nullable String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public PhoneEnrichmentFields withCompanyName(@Nullable String companyName) {
        this.companyName = companyName;
        return this;
    }

    public PhoneEnrichmentFields withEmail(@Nullable String email) {
        this.email = email;
        return this;
    }

    public PhoneEnrichmentFields withWebsite(@Nullable String website) {
        this.website = website;
        return this;
    }

    public PhoneEnrichmentFields withFirstCallAt(@Nullable String firstCallAt) {
        this.firstCallAt = firstCallAt;
        return this;
    }

    public PhoneEnrichmentFields withLastCallAt(@Nullable String lastCallAt) {
        this.lastCallAt = lastCallAt;
        return this;
    }

    public PhoneEnrichmentFields withMatchedName(@Nullable String matchedName) {
        this.matchedName = matchedName;
        return this;
    }

    public PhoneEnrichmentFields withAlternateNames(@Nullable List<AlternateNameUnion> alternateNames) {
        this.alternateNames = alternateNames;
        return this;
    }

    public PhoneEnrichmentFields withNameCandidates(@Nullable List<NameCandidateUnion> nameCandidates) {
        this.nameCandidates = nameCandidates;
        return this;
    }

    public PhoneEnrichmentFields withTimezones(@Nullable List<TimezoneUnion> timezones) {
        this.timezones = timezones;
        return this;
    }

    public PhoneEnrichmentFields withEmails(@Nullable List<EmailUnion> emails) {
        this.emails = emails;
        return this;
    }

    public PhoneEnrichmentFields withWebsites(@Nullable List<WebsiteUnion> websites) {
        this.websites = websites;
        return this;
    }

    public PhoneEnrichmentFields withLocations(@Nullable List<LocationUnion> locations) {
        this.locations = locations;
        return this;
    }

    public PhoneEnrichmentFields withSocialProfiles(@Nullable List<SocialProfileUnion> socialProfiles) {
        this.socialProfiles = socialProfiles;
        return this;
    }

    public PhoneEnrichmentFields withReportCount(@Nullable Long reportCount) {
        this.reportCount = reportCount;
        return this;
    }

    public PhoneEnrichmentFields withTagCount(@Nullable Long tagCount) {
        this.tagCount = tagCount;
        return this;
    }

    public PhoneEnrichmentFields withTotalCalls(@Nullable Long totalCalls) {
        this.totalCalls = totalCalls;
        return this;
    }

    public PhoneEnrichmentFields withCalls60Days(@Nullable Long calls60Days) {
        this.calls60Days = calls60Days;
        return this;
    }

    public PhoneEnrichmentFields withReports60Days(@Nullable Long reports60Days) {
        this.reports60Days = reports60Days;
        return this;
    }

    public PhoneEnrichmentFields withSearches60Days(@Nullable Long searches60Days) {
        this.searches60Days = searches60Days;
        return this;
    }

    public PhoneEnrichmentFields withCommentCount(@Nullable Long commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public PhoneEnrichmentFields withNamesCount(@Nullable Long namesCount) {
        this.namesCount = namesCount;
        return this;
    }

    public PhoneEnrichmentFields withSpamReportCount(@Nullable Long spamReportCount) {
        this.spamReportCount = spamReportCount;
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
        PhoneEnrichmentFields other = (PhoneEnrichmentFields) o;
        return Utils.enhancedDeepEquals(this.phone, other.phone)
                && Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.carrier, other.carrier)
                && Utils.enhancedDeepEquals(this.lineType, other.lineType)
                && Utils.enhancedDeepEquals(this.entityType, other.entityType)
                && Utils.enhancedDeepEquals(this.countryCode, other.countryCode)
                && Utils.enhancedDeepEquals(this.country, other.country)
                && Utils.enhancedDeepEquals(this.state, other.state)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.location, other.location)
                && Utils.enhancedDeepEquals(this.nationalFormat, other.nationalFormat)
                && Utils.enhancedDeepEquals(this.jobTitle, other.jobTitle)
                && Utils.enhancedDeepEquals(this.companyName, other.companyName)
                && Utils.enhancedDeepEquals(this.email, other.email)
                && Utils.enhancedDeepEquals(this.website, other.website)
                && Utils.enhancedDeepEquals(this.firstCallAt, other.firstCallAt)
                && Utils.enhancedDeepEquals(this.lastCallAt, other.lastCallAt)
                && Utils.enhancedDeepEquals(this.matchedName, other.matchedName)
                && Utils.enhancedDeepEquals(this.alternateNames, other.alternateNames)
                && Utils.enhancedDeepEquals(this.nameCandidates, other.nameCandidates)
                && Utils.enhancedDeepEquals(this.timezones, other.timezones)
                && Utils.enhancedDeepEquals(this.emails, other.emails)
                && Utils.enhancedDeepEquals(this.websites, other.websites)
                && Utils.enhancedDeepEquals(this.locations, other.locations)
                && Utils.enhancedDeepEquals(this.socialProfiles, other.socialProfiles)
                && Utils.enhancedDeepEquals(this.reportCount, other.reportCount)
                && Utils.enhancedDeepEquals(this.tagCount, other.tagCount)
                && Utils.enhancedDeepEquals(this.totalCalls, other.totalCalls)
                && Utils.enhancedDeepEquals(this.calls60Days, other.calls60Days)
                && Utils.enhancedDeepEquals(this.reports60Days, other.reports60Days)
                && Utils.enhancedDeepEquals(this.searches60Days, other.searches60Days)
                && Utils.enhancedDeepEquals(this.commentCount, other.commentCount)
                && Utils.enhancedDeepEquals(this.namesCount, other.namesCount)
                && Utils.enhancedDeepEquals(this.spamReportCount, other.spamReportCount);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                phone,
                name,
                carrier,
                lineType,
                entityType,
                countryCode,
                country,
                state,
                city,
                location,
                nationalFormat,
                jobTitle,
                companyName,
                email,
                website,
                firstCallAt,
                lastCallAt,
                matchedName,
                alternateNames,
                nameCandidates,
                timezones,
                emails,
                websites,
                locations,
                socialProfiles,
                reportCount,
                tagCount,
                totalCalls,
                calls60Days,
                reports60Days,
                searches60Days,
                commentCount,
                namesCount,
                spamReportCount);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PhoneEnrichmentFields.class,
                "phone",
                phone,
                "name",
                name,
                "carrier",
                carrier,
                "lineType",
                lineType,
                "entityType",
                entityType,
                "countryCode",
                countryCode,
                "country",
                country,
                "state",
                state,
                "city",
                city,
                "location",
                location,
                "nationalFormat",
                nationalFormat,
                "jobTitle",
                jobTitle,
                "companyName",
                companyName,
                "email",
                email,
                "website",
                website,
                "firstCallAt",
                firstCallAt,
                "lastCallAt",
                lastCallAt,
                "matchedName",
                matchedName,
                "alternateNames",
                alternateNames,
                "nameCandidates",
                nameCandidates,
                "timezones",
                timezones,
                "emails",
                emails,
                "websites",
                websites,
                "locations",
                locations,
                "socialProfiles",
                socialProfiles,
                "reportCount",
                reportCount,
                "tagCount",
                tagCount,
                "totalCalls",
                totalCalls,
                "calls60Days",
                calls60Days,
                "reports60Days",
                reports60Days,
                "searches60Days",
                searches60Days,
                "commentCount",
                commentCount,
                "namesCount",
                namesCount,
                "spamReportCount",
                spamReportCount);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String phone;

        private String name;

        private String carrier;

        private String lineType;

        private String entityType;

        private String countryCode;

        private String country;

        private String state;

        private String city;

        private String location;

        private String nationalFormat;

        private String jobTitle;

        private String companyName;

        private String email;

        private String website;

        private String firstCallAt;

        private String lastCallAt;

        private String matchedName;

        private List<AlternateNameUnion> alternateNames;

        private List<NameCandidateUnion> nameCandidates;

        private List<TimezoneUnion> timezones;

        private List<EmailUnion> emails;

        private List<WebsiteUnion> websites;

        private List<LocationUnion> locations;

        private List<SocialProfileUnion> socialProfiles;

        private Long reportCount;

        private Long tagCount;

        private Long totalCalls;

        private Long calls60Days;

        private Long reports60Days;

        private Long searches60Days;

        private Long commentCount;

        private Long namesCount;

        private Long spamReportCount;

        private Builder() {
            // force use of static builder() method
        }

        public Builder phone(@Nullable String phone) {
            this.phone = phone;
            return this;
        }

        public Builder name(@Nullable String name) {
            this.name = name;
            return this;
        }

        public Builder carrier(@Nullable String carrier) {
            this.carrier = carrier;
            return this;
        }

        public Builder lineType(@Nullable String lineType) {
            this.lineType = lineType;
            return this;
        }

        public Builder entityType(@Nullable String entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder countryCode(@Nullable String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder country(@Nullable String country) {
            this.country = country;
            return this;
        }

        public Builder state(@Nullable String state) {
            this.state = state;
            return this;
        }

        public Builder city(@Nullable String city) {
            this.city = city;
            return this;
        }

        public Builder location(@Nullable String location) {
            this.location = location;
            return this;
        }

        public Builder nationalFormat(@Nullable String nationalFormat) {
            this.nationalFormat = nationalFormat;
            return this;
        }

        public Builder jobTitle(@Nullable String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder companyName(@Nullable String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder email(@Nullable String email) {
            this.email = email;
            return this;
        }

        public Builder website(@Nullable String website) {
            this.website = website;
            return this;
        }

        public Builder firstCallAt(@Nullable String firstCallAt) {
            this.firstCallAt = firstCallAt;
            return this;
        }

        public Builder lastCallAt(@Nullable String lastCallAt) {
            this.lastCallAt = lastCallAt;
            return this;
        }

        public Builder matchedName(@Nullable String matchedName) {
            this.matchedName = matchedName;
            return this;
        }

        public Builder alternateNames(@Nullable List<AlternateNameUnion> alternateNames) {
            this.alternateNames = alternateNames;
            return this;
        }

        public Builder nameCandidates(@Nullable List<NameCandidateUnion> nameCandidates) {
            this.nameCandidates = nameCandidates;
            return this;
        }

        public Builder timezones(@Nullable List<TimezoneUnion> timezones) {
            this.timezones = timezones;
            return this;
        }

        public Builder emails(@Nullable List<EmailUnion> emails) {
            this.emails = emails;
            return this;
        }

        public Builder websites(@Nullable List<WebsiteUnion> websites) {
            this.websites = websites;
            return this;
        }

        public Builder locations(@Nullable List<LocationUnion> locations) {
            this.locations = locations;
            return this;
        }

        public Builder socialProfiles(@Nullable List<SocialProfileUnion> socialProfiles) {
            this.socialProfiles = socialProfiles;
            return this;
        }

        public Builder reportCount(@Nullable Long reportCount) {
            this.reportCount = reportCount;
            return this;
        }

        public Builder tagCount(@Nullable Long tagCount) {
            this.tagCount = tagCount;
            return this;
        }

        public Builder totalCalls(@Nullable Long totalCalls) {
            this.totalCalls = totalCalls;
            return this;
        }

        public Builder calls60Days(@Nullable Long calls60Days) {
            this.calls60Days = calls60Days;
            return this;
        }

        public Builder reports60Days(@Nullable Long reports60Days) {
            this.reports60Days = reports60Days;
            return this;
        }

        public Builder searches60Days(@Nullable Long searches60Days) {
            this.searches60Days = searches60Days;
            return this;
        }

        public Builder commentCount(@Nullable Long commentCount) {
            this.commentCount = commentCount;
            return this;
        }

        public Builder namesCount(@Nullable Long namesCount) {
            this.namesCount = namesCount;
            return this;
        }

        public Builder spamReportCount(@Nullable Long spamReportCount) {
            this.spamReportCount = spamReportCount;
            return this;
        }

        public PhoneEnrichmentFields build() {
            return new PhoneEnrichmentFields(
                    phone,
                    name,
                    carrier,
                    lineType,
                    entityType,
                    countryCode,
                    country,
                    state,
                    city,
                    location,
                    nationalFormat,
                    jobTitle,
                    companyName,
                    email,
                    website,
                    firstCallAt,
                    lastCallAt,
                    matchedName,
                    alternateNames,
                    nameCandidates,
                    timezones,
                    emails,
                    websites,
                    locations,
                    socialProfiles,
                    reportCount,
                    tagCount,
                    totalCalls,
                    calls60Days,
                    reports60Days,
                    searches60Days,
                    commentCount,
                    namesCount,
                    spamReportCount);
        }
    }
}
