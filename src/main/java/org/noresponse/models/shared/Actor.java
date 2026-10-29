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

@JsonDeserialize(using = Actor._Deserializer.class)
public class Actor {

    @JsonValue
    private final TypedObject value;

    private Actor(TypedObject value) {
        this.value = value;
    }

    public static Actor of(UserActor value) {
        Utils.checkNotNull(value, "value");
        return new Actor(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    public static Actor of(KeyActor value) {
        Utils.checkNotNull(value, "value");
        return new Actor(TypedObject.of(value, JsonShape.DEFAULT, new TypeReference<>() {}));
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code UserActor},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code UserActor} value, or empty if not of this type
     */
    public Optional<UserActor> userActor() {
        if (value.value() instanceof UserActor) {
            return Optional.of((UserActor) value.value());
        }
        return Optional.empty();
    }

    /**
     * Returns an {@link Optional} containing the value if it is of type {@code KeyActor},
     * otherwise returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the {@code KeyActor} value, or empty if not of this type
     */
    public Optional<KeyActor> keyActor() {
        if (value.value() instanceof KeyActor) {
            return Optional.of((KeyActor) value.value());
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
        Actor other = (Actor) o;
        return Utils.enhancedDeepEquals(this.value.value(), other.value.value());
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value.value());
    }

    @SuppressWarnings("serial")
    public static final class _Deserializer extends OneOfDeserializer<Actor> {

        public _Deserializer() {
            super(
                    Actor.class,
                    false,
                    TypeReferenceWithShape.of(new TypeReference<UserActor>() {}, JsonShape.DEFAULT),
                    TypeReferenceWithShape.of(new TypeReference<KeyActor>() {}, JsonShape.DEFAULT));
        }
    }

    @Override
    public String toString() {
        return Utils.toString(Actor.class, "value", value);
    }
}
