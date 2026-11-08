package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * AttendeeBatchRequest
 *
 * <p>Adds or updates 1 to 500 attendees. A row naming an existing attendee of the event (the same
 * external_id, else the same email, else the same phone) updates it.
 */
public class AttendeeBatchRequest {

    @JsonProperty("attendees")
    private List<AttendeeIdentity> attendees;

    @JsonCreator
    public AttendeeBatchRequest(@JsonProperty("attendees") @Nonnull List<AttendeeIdentity> attendees) {
        this.attendees = Optional.ofNullable(attendees)
                .orElseThrow(() -> new IllegalArgumentException("attendees cannot be null"));
    }

    public List<AttendeeIdentity> attendees() {
        return this.attendees;
    }

    public static Builder builder() {
        return new Builder();
    }

    public AttendeeBatchRequest withAttendees(@Nonnull List<AttendeeIdentity> attendees) {
        this.attendees = Utils.checkNotNull(attendees, "attendees");
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
        AttendeeBatchRequest other = (AttendeeBatchRequest) o;
        return Utils.enhancedDeepEquals(this.attendees, other.attendees);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(attendees);
    }

    @Override
    public String toString() {
        return Utils.toString(AttendeeBatchRequest.class, "attendees", attendees);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<AttendeeIdentity> attendees;

        private Builder() {
            // force use of static builder() method
        }

        public Builder attendees(@Nonnull List<AttendeeIdentity> attendees) {
            this.attendees = Utils.checkNotNull(attendees, "attendees");
            return this;
        }

        public AttendeeBatchRequest build() {
            return new AttendeeBatchRequest(attendees);
        }
    }
}
