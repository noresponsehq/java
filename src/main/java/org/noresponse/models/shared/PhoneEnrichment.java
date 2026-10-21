package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * PhoneEnrichment
 *
 * <p>Facts about the number only: line type, carrier and country. The upstream provider, its scores and
 * its verdicts stay internal.
 */
public class PhoneEnrichment {

    @JsonProperty("fields")
    private PhoneEnrichmentFields fields;

    @JsonCreator
    public PhoneEnrichment(@JsonProperty("fields") @Nonnull PhoneEnrichmentFields fields) {
        this.fields =
                Optional.ofNullable(fields).orElseThrow(() -> new IllegalArgumentException("fields cannot be null"));
    }

    public PhoneEnrichmentFields fields() {
        return this.fields;
    }

    public static Builder builder() {
        return new Builder();
    }

    public PhoneEnrichment withFields(@Nonnull PhoneEnrichmentFields fields) {
        this.fields = Utils.checkNotNull(fields, "fields");
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
        PhoneEnrichment other = (PhoneEnrichment) o;
        return Utils.enhancedDeepEquals(this.fields, other.fields);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(fields);
    }

    @Override
    public String toString() {
        return Utils.toString(PhoneEnrichment.class, "fields", fields);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private PhoneEnrichmentFields fields;

        private Builder() {
            // force use of static builder() method
        }

        public Builder fields(@Nonnull PhoneEnrichmentFields fields) {
            this.fields = Utils.checkNotNull(fields, "fields");
            return this;
        }

        public PhoneEnrichment build() {
            return new PhoneEnrichment(fields);
        }
    }
}
