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

@JsonDeserialize(using = WebsiteUnion._Deserializer.class)
public class WebsiteUnion {

    @JsonValue
    private final TypedObject value;

    private WebsiteUnion(TypedObject value) {
        this.value = value;
    }

    public static WebsiteUnion of(String value) {
        Utils.checkNotNull(value, "value");
        return new WebsiteUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static WebsiteUnion of(double value) {
        return new WebsiteUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static WebsiteUnion of(boolean value) {
        return new WebsiteUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static WebsiteUnion of(Website value) {
        Utils.checkNotNull(value, "value");
        return new WebsiteUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static WebsiteUnion of(List<Object> value) {
        Utils.checkNotNull(value, "value");
        return new WebsiteUnion(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
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
     * Returns an {@link Optional} containing the value if it is of type {@code Website},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code Website} value, or empty if not of this type
     */
    public Optional<Website> website() {
        if (value.value() instanceof Website) {
            return Optional.of((Website) value.value());
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
        WebsiteUnion other = (WebsiteUnion) o;
        return Utils.enhancedDeepEquals(this.value.value(), other.value.value());
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value.value());
    }

    @SuppressWarnings("serial")
    public static final class _Deserializer extends OneOfDeserializer<WebsiteUnion> {

        public _Deserializer() {
            super(
                    WebsiteUnion.class,
                    false,
                    TypeReferenceWithShape.of(new TypeReference<String>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Double>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Boolean>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<Website>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<List<Object>>() {}, JsonShape.DEFAULT));
        }
    }

    @Override
    public String toString() {
        return Utils.toString(WebsiteUnion.class, "value", value);
    }
}
