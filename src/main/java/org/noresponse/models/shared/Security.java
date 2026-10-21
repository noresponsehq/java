package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.HasSecurity;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class Security implements HasSecurity {

    @NoResponseMetadata("security:scheme=true,type=http,subtype=bearer,name=Authorization")
    private String apiKey;

    @JsonCreator
    public Security(@Nonnull String apiKey) {
        this.apiKey =
                Optional.ofNullable(apiKey).orElseThrow(() -> new IllegalArgumentException("apiKey cannot be null"));
    }

    public String apiKey() {
        return this.apiKey;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Security withApiKey(@Nonnull String apiKey) {
        this.apiKey = Utils.checkNotNull(apiKey, "apiKey");
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
        Security other = (Security) o;
        return Utils.enhancedDeepEquals(this.apiKey, other.apiKey);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(apiKey);
    }

    @Override
    public String toString() {
        return Utils.toString(Security.class, "apiKey", apiKey);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String apiKey;

        private Builder() {
            // force use of static builder() method
        }

        public Builder apiKey(@Nonnull String apiKey) {
            this.apiKey = Utils.checkNotNull(apiKey, "apiKey");
            return this;
        }

        public Security build() {
            return new Security(apiKey);
        }
    }
}
