package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

public class AuditSettingsRequest {

    @JsonProperty("retention_days")
    private long retentionDays;

    @JsonCreator
    public AuditSettingsRequest(@JsonProperty("retention_days") long retentionDays) {
        this.retentionDays = retentionDays;
    }

    public long retentionDays() {
        return this.retentionDays;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AuditSettingsRequest withRetentionDays(long retentionDays) {
        this.retentionDays = retentionDays;
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
        AuditSettingsRequest other = (AuditSettingsRequest) o;
        return Utils.enhancedDeepEquals(this.retentionDays, other.retentionDays);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(retentionDays);
    }

    @Override
    public String toString() {
        return Utils.toString(AuditSettingsRequest.class, "retentionDays", retentionDays);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private long retentionDays;

        private Builder() {
            // force use of static builder() method
        }

        public Builder retentionDays(long retentionDays) {
            this.retentionDays = retentionDays;
            return this;
        }

        public AuditSettingsRequest build() {
            return new AuditSettingsRequest(retentionDays);
        }
    }
}
