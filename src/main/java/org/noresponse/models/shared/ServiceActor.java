package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

public class ServiceActor {

    @JsonProperty("type")
    private String type;

    /**
     * The NoResponse service that made the change with no person acting, such as replenisher, which
     * replaces dead platform sessions in NoResponse's own workspace.
     */
    @JsonProperty("name")
    private String name;

    @JsonCreator
    public ServiceActor(@JsonProperty("name") @Nonnull String name) {
        this.type = Builder._SINGLETON_VALUE_Type.value();
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
    }

    public String type() {
        return this.type;
    }

    /**
     * The NoResponse service that made the change with no person acting, such as replenisher, which
     * replaces dead platform sessions in NoResponse's own workspace.
     */
    public String name() {
        return this.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The NoResponse service that made the change with no person acting, such as replenisher, which
     * replaces dead platform sessions in NoResponse's own workspace.
     */
    public ServiceActor withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
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
        ServiceActor other = (ServiceActor) o;
        return Utils.enhancedDeepEquals(this.type, other.type) && Utils.enhancedDeepEquals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(type, name);
    }

    @Override
    public String toString() {
        return Utils.toString(ServiceActor.class, "type", type, "name", name);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The NoResponse service that made the change with no person acting, such as replenisher, which
         * replaces dead platform sessions in NoResponse's own workspace.
         */
        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        public ServiceActor build() {
            return new ServiceActor(name);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Type =
                new LazySingletonValue<>("type", "\"service\"", new TypeReference<String>() {});
    }
}
