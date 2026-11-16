package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ProfileLead {
    /**
     * A value copied exactly from a linked record that the person was not searched by.
     */
    @JsonProperty("value")
    private String value;

    @JsonProperty("kind")
    private String kind;

    @JsonProperty("why")
    private String why;

    @JsonProperty("evidence")
    private List<ProfileCitation> evidence;

    @JsonCreator
    public ProfileLead(
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("kind") @Nonnull String kind,
            @JsonProperty("why") @Nonnull String why,
            @JsonProperty("evidence") @Nonnull List<ProfileCitation> evidence) {
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.why = Optional.ofNullable(why).orElseThrow(() -> new IllegalArgumentException("why cannot be null"));
        this.evidence = Optional.ofNullable(evidence)
                .orElseThrow(() -> new IllegalArgumentException("evidence cannot be null"));
    }

    /**
     * A value copied exactly from a linked record that the person was not searched by.
     */
    public String value() {
        return this.value;
    }

    public String kind() {
        return this.kind;
    }

    public String why() {
        return this.why;
    }

    public List<ProfileCitation> evidence() {
        return this.evidence;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A value copied exactly from a linked record that the person was not searched by.
     */
    public ProfileLead withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    public ProfileLead withKind(@Nonnull String kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ProfileLead withWhy(@Nonnull String why) {
        this.why = Utils.checkNotNull(why, "why");
        return this;
    }

    public ProfileLead withEvidence(@Nonnull List<ProfileCitation> evidence) {
        this.evidence = Utils.checkNotNull(evidence, "evidence");
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
        ProfileLead other = (ProfileLead) o;
        return Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.why, other.why)
                && Utils.enhancedDeepEquals(this.evidence, other.evidence);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(value, kind, why, evidence);
    }

    @Override
    public String toString() {
        return Utils.toString(ProfileLead.class, "value", value, "kind", kind, "why", why, "evidence", evidence);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String value;

        private String kind;

        private String why;

        private List<ProfileCitation> evidence;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * A value copied exactly from a linked record that the person was not searched by.
         */
        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        public Builder kind(@Nonnull String kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder why(@Nonnull String why) {
            this.why = Utils.checkNotNull(why, "why");
            return this;
        }

        public Builder evidence(@Nonnull List<ProfileCitation> evidence) {
            this.evidence = Utils.checkNotNull(evidence, "evidence");
            return this;
        }

        public ProfileLead build() {
            return new ProfileLead(value, kind, why, evidence);
        }
    }
}
