package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.OneOfDeserializer;
import org.noresponse.utils.TypedObject;
import org.noresponse.utils.Utils;
import org.noresponse.utils.Utils.JsonShape;
import org.noresponse.utils.Utils.TypeReferenceWithShape;

@JsonDeserialize(using = EmailUnion._Deserializer.class)
public class EmailUnion {

    @JsonValue
    private final TypedObject value;

    private EmailUnion(TypedObject value) {
        this.value = value;
    }

    public static EmailUnion of(String value) {
        Utils.checkNotNull(value, "value");
        return new EmailUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static EmailUnion of(double value) {
        return new EmailUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static EmailUnion of(boolean value) {
        return new EmailUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static EmailUnion of(Email value) {
        Utils.checkNotNull(value, "value");
        return new EmailUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static EmailUnion of(List<Object> value) {
        Utils.checkNotNull(value, "value");
        return new EmailUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code String},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code String} value, or empty if not of this type
     */
    public Optional<String> string() {
        if (value.value() instanceof String) {
            return Optional.of((String) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code double},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code double} value, or empty if not of this type
     */
    public Optional<java.lang.Double> asDouble() {
        if (value.value() instanceof java.lang.Double) {
            return Optional.of((java.lang.Double) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code boolean},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code boolean} value, or empty if not of this type
     */
    public Optional<java.lang.Boolean> asBoolean() {
        if (value.value() instanceof java.lang.Boolean) {
            return Optional.of((java.lang.Boolean) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code Email},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code Email} value, or empty if not of this type
     */
    public Optional<Email> email() {
        if (value.value() instanceof Email) {
            return Optional.of((Email) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code List<Object>},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code List<Object>} value, or empty if not of this type
     */
    @SuppressWarnings("unchecked")
    public Optional<List<Object>> arrayOfObject() {
        if (value.value() instanceof List) {
            return Optional.of((List<Object>) value.value());
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
        EmailUnion other = (EmailUnion) o;
        return Utils.enhancedDeepEquals(this.value.value(), other.value.value());
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value.value());
    }

    @SuppressWarnings("serial")
    public static final class _Deserializer extends OneOfDeserializer<EmailUnion> {

        public _Deserializer() {
            super(
                    EmailUnion.class,
                    false,
                    TypeReferenceWithShape.of(new TypeReference<String>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Double>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Boolean>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Email>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<List<Object>>() {}, JsonShape.DEFAULT));
        }
    }

    @Override
    public String toString() {
        return Utils.toString(EmailUnion.class, "value", value);
    }
}
