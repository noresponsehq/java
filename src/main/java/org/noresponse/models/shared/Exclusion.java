package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Wrapper for an "open" enum that can handle unknown values from API responses
 * without runtime errors. Instances are immutable singletons with reference equality.
 * Use {@code asEnum()} for switch expressions.
 */
public class Exclusion {

    public static final Exclusion EXPIRED_OR_ERASED_RECORDS = new Exclusion("expired_or_erased_records");
    public static final Exclusion SECRETS_AND_CREDENTIAL_DIGESTS = new Exclusion("secrets_and_credential_digests");
    public static final Exclusion PRIVATE_SOURCE_AND_WORK_PAYLOADS = new Exclusion("private_source_and_work_payloads");
    public static final Exclusion CROSS_CUSTOMER_DATA = new Exclusion("cross_customer_data");
    public static final Exclusion LEGACY_OUTCOME_NOTES = new Exclusion("legacy_outcome_notes");
    public static final Exclusion UNCAPTURED_HISTORICAL_DECISIONS = new Exclusion("uncaptured_historical_decisions");
    public static final Exclusion INVESTIGATOR_REVIEWS_REQUIRE_REVIEW_EXPORT =
            new Exclusion("investigator_reviews_require_review_export");

    // This map will grow whenever a Color gets created with a new
    // unrecognized value (a potential memory leak if the user is not
    // careful). Keep this field lower case to avoid clashing with
    // generated member names which will always be upper cased (Java
    // convention)
    private static final Map<String, Exclusion> values = createValuesMap();
    private static final Map<String, ExclusionEnum> enums = createEnumsMap();

    private final String value;

    private Exclusion(String value) {
        this.value = value;
    }

    /**
     * Returns a Exclusion with the given value. For a specific value the
     * returned object will always be a singleton so reference equality
     * is satisfied when the values are the same.
     *
     * @param value value to be wrapped as Exclusion
     */
    @JsonCreator
    public static Exclusion of(String value) {
        synchronized (Exclusion.class) {
            return values.computeIfAbsent(value, v -> new Exclusion(v));
        }
    }

    @JsonValue
    public String value() {
        return value;
    }

    public Optional<ExclusionEnum> asEnum() {
        return Optional.ofNullable(enums.getOrDefault(value, null));
    }

    public boolean isKnown() {
        return asEnum().isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Exclusion other = (Exclusion) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return "Exclusion [value=" + value + "]";
    }

    // return an array just like an enum
    public static Exclusion[] values() {
        synchronized (Exclusion.class) {
            return values.values().toArray(new Exclusion[] {});
        }
    }

    private static final Map<String, Exclusion> createValuesMap() {
        Map<String, Exclusion> map = new LinkedHashMap<>();
        map.put("expired_or_erased_records", EXPIRED_OR_ERASED_RECORDS);
        map.put("secrets_and_credential_digests", SECRETS_AND_CREDENTIAL_DIGESTS);
        map.put("private_source_and_work_payloads", PRIVATE_SOURCE_AND_WORK_PAYLOADS);
        map.put("cross_customer_data", CROSS_CUSTOMER_DATA);
        map.put("legacy_outcome_notes", LEGACY_OUTCOME_NOTES);
        map.put("uncaptured_historical_decisions", UNCAPTURED_HISTORICAL_DECISIONS);
        map.put("investigator_reviews_require_review_export", INVESTIGATOR_REVIEWS_REQUIRE_REVIEW_EXPORT);
        return map;
    }

    private static final Map<String, ExclusionEnum> createEnumsMap() {
        Map<String, ExclusionEnum> map = new HashMap<>();
        map.put("expired_or_erased_records", ExclusionEnum.EXPIRED_OR_ERASED_RECORDS);
        map.put("secrets_and_credential_digests", ExclusionEnum.SECRETS_AND_CREDENTIAL_DIGESTS);
        map.put("private_source_and_work_payloads", ExclusionEnum.PRIVATE_SOURCE_AND_WORK_PAYLOADS);
        map.put("cross_customer_data", ExclusionEnum.CROSS_CUSTOMER_DATA);
        map.put("legacy_outcome_notes", ExclusionEnum.LEGACY_OUTCOME_NOTES);
        map.put("uncaptured_historical_decisions", ExclusionEnum.UNCAPTURED_HISTORICAL_DECISIONS);
        map.put(
                "investigator_reviews_require_review_export", ExclusionEnum.INVESTIGATOR_REVIEWS_REQUIRE_REVIEW_EXPORT);
        return map;
    }

    public enum ExclusionEnum {

        EXPIRED_OR_ERASED_RECORDS("expired_or_erased_records"),
        SECRETS_AND_CREDENTIAL_DIGESTS("secrets_and_credential_digests"),
        PRIVATE_SOURCE_AND_WORK_PAYLOADS("private_source_and_work_payloads"),
        CROSS_CUSTOMER_DATA("cross_customer_data"),
        LEGACY_OUTCOME_NOTES("legacy_outcome_notes"),
        UNCAPTURED_HISTORICAL_DECISIONS("uncaptured_historical_decisions"),
        INVESTIGATOR_REVIEWS_REQUIRE_REVIEW_EXPORT("investigator_reviews_require_review_export"),
        ;

        private final String value;

        private ExclusionEnum(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
