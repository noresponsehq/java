package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Double;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PostPlace
 *
 * <p>A place a post names, as the platform shows it.
 */
public class PostPlace {

    @JsonProperty("name")
    private String name;

    /**
     * The platform's own id of the place.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("id")
    private String id;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("address")
    private String address;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("city")
    private String city;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("latitude")
    private Double latitude;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("longitude")
    private Double longitude;

    @JsonCreator
    public PostPlace(
            @JsonProperty("name") @Nonnull String name,
            @JsonProperty("id") @Nullable String id,
            @JsonProperty("address") @Nullable String address,
            @JsonProperty("city") @Nullable String city,
            @JsonProperty("latitude") @Nullable Double latitude,
            @JsonProperty("longitude") @Nullable Double longitude) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("name cannot be null"));
        this.id = id;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PostPlace(@Nonnull String name) {
        this(name, null, null, null, null, null);
    }

    public String name() {
        return this.name;
    }

    /**
     * The platform's own id of the place.
     */
    public Optional<String> id() {
        return Optional.ofNullable(this.id);
    }

    public Optional<String> address() {
        return Optional.ofNullable(this.address);
    }

    public Optional<String> city() {
        return Optional.ofNullable(this.city);
    }

    public Optional<Double> latitude() {
        return Optional.ofNullable(this.latitude);
    }

    public Optional<Double> longitude() {
        return Optional.ofNullable(this.longitude);
    }

    public static Builder builder() {
        return new Builder();
    }

    public PostPlace withName(@Nonnull String name) {
        this.name = Utils.checkNotNull(name, "name");
        return this;
    }

    /**
     * The platform's own id of the place.
     */
    public PostPlace withId(@Nullable String id) {
        this.id = id;
        return this;
    }

    public PostPlace withAddress(@Nullable String address) {
        this.address = address;
        return this;
    }

    public PostPlace withCity(@Nullable String city) {
        this.city = city;
        return this;
    }

    public PostPlace withLatitude(@Nullable Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public PostPlace withLongitude(@Nullable Double longitude) {
        this.longitude = longitude;
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
        PostPlace other = (PostPlace) o;
        return Utils.enhancedDeepEquals(this.name, other.name)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.address, other.address)
                && Utils.enhancedDeepEquals(this.city, other.city)
                && Utils.enhancedDeepEquals(this.latitude, other.latitude)
                && Utils.enhancedDeepEquals(this.longitude, other.longitude);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(name, id, address, city, latitude, longitude);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PostPlace.class,
                "name",
                name,
                "id",
                id,
                "address",
                address,
                "city",
                city,
                "latitude",
                latitude,
                "longitude",
                longitude);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String name;

        private String id;

        private String address;

        private String city;

        private Double latitude;

        private Double longitude;

        private Builder() {
            // force use of static builder() method
        }

        public Builder name(@Nonnull String name) {
            this.name = Utils.checkNotNull(name, "name");
            return this;
        }

        /**
         * The platform's own id of the place.
         */
        public Builder id(@Nullable String id) {
            this.id = id;
            return this;
        }

        public Builder address(@Nullable String address) {
            this.address = address;
            return this;
        }

        public Builder city(@Nullable String city) {
            this.city = city;
            return this;
        }

        public Builder latitude(@Nullable Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(@Nullable Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public PostPlace build() {
            return new PostPlace(name, id, address, city, latitude, longitude);
        }
    }
}
