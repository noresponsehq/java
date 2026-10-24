package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Optional;
import org.noresponse.utils.OneOfDeserializer;
import org.noresponse.utils.TypedObject;
import org.noresponse.utils.Utils;
import org.noresponse.utils.Utils.JsonShape;
import org.noresponse.utils.Utils.TypeReferenceWithShape;

/**
 * AssessmentUpdatedData
 *
 * <p>The assessment, or the minimal notice that it expired.
 */
@JsonDeserialize(using = AssessmentUpdatedData._Deserializer.class)
public class AssessmentUpdatedData {

    @JsonValue
    private final TypedObject value;

    private AssessmentUpdatedData(TypedObject value) {
        this.value = value;
    }

    public static AssessmentUpdatedData of(Assessment value) {
        Utils.checkNotNull(value, "value");
        return new AssessmentUpdatedData(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static AssessmentUpdatedData of(AssessmentExpired value) {
        Utils.checkNotNull(value, "value");
        return new AssessmentUpdatedData(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code Assessment},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code Assessment} value, or empty if not of this type
     */
    public Optional<Assessment> assessment() {
        if (value.value() instanceof Assessment) {
            return Optional.of((Assessment) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code AssessmentExpired},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code AssessmentExpired} value, or empty if not of this type
     */
    public Optional<AssessmentExpired> assessmentExpired() {
        if (value.value() instanceof AssessmentExpired) {
            return Optional.of((AssessmentExpired) value.value());
        }
        return Optional.empty();
    }
    /**
     * Returns an {@link Optional} containing the value as a {@code JsonNode}.
     * This accessor returns the raw JSON when the value doesn't match any of the defined union types.
     *
     * @return an {@link Optional} containing the {@code JsonNode} value, or empty if value matched a known type
     */
    public Optional<JsonNode> asJson() {
        if (value.value() instanceof JsonNode) {
            return Optional.of((JsonNode) value.value());
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssessmentUpdatedData other = (AssessmentUpdatedData) o;
        return Utils.enhancedDeepEquals(this.value.value(), other.value.value());
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value.value());
    }

    @SuppressWarnings("serial")
    public static final class _Deserializer extends OneOfDeserializer<AssessmentUpdatedData> {

        public _Deserializer() {
            super(
                    AssessmentUpdatedData.class,
                    false,
                    TypeReferenceWithShape.of(new TypeReference<Assessment>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<AssessmentExpired>() {}, JsonShape.DEFAULT));
        }
    }

    @Override
    public String toString() {
        return Utils.toString(AssessmentUpdatedData.class, "value", value);
    }
}
