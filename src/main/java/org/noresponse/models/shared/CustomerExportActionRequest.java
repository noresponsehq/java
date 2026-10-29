package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.lang.Override;
import java.lang.String;
import org.noresponse.utils.Utils;

/**
 * CustomerExportActionRequest
 *
 * <p>Send an empty JSON object to cancel or retry a customer export.
 */
public class CustomerExportActionRequest {
    @JsonCreator
    public CustomerExportActionRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash();
    }

    @Override
    public String toString() {
        return Utils.toString(CustomerExportActionRequest.class);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Builder() {
            // force use of static builder() method
        }

        public CustomerExportActionRequest build() {
            return new CustomerExportActionRequest();
        }
    }
}
