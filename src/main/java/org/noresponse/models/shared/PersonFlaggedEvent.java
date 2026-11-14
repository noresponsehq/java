package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * PersonFlaggedEvent
 *
 * <p>The person.flagged event.
 */
public class PersonFlaggedEvent implements WebhookEvent {
    /**
     * The event id, equal to the NR-Webhook-Id header and across redeliveries and every endpoint the event
     * fans out to.
     */
    @JsonProperty("id")
    private String id;

    /**
     * When the event was created. Equal across redeliveries; the signing timestamp header is per delivery.
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Always person.flagged.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Present, and true, only on an event sent by POST /v1/webhooks/{endpoint_id}/test: its data is a
     * fixed example assessment, not one of the workspace's, so a receiver acknowledges it and applies
     * nothing. Real events have no test member.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("test")
    private Boolean test;

    /**
     * A screening recorded a finding on a person. It says which person and how severe, not what they
     * posted, who they are or why: GET the person report for the findings. Sent once per screening that
     * flags the person, so a re-screen of someone on watch announces them again.
     */
    @JsonProperty("data")
    private PersonFlagged data;

    @JsonCreator
    public PersonFlaggedEvent(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("test") @Nullable Boolean test,
            @JsonProperty("data") @Nonnull PersonFlagged data) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.type = Builder._SINGLETON_VALUE_Type.value();
        this.test = test;
        this.data = Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("data cannot be null"));
    }

    public PersonFlaggedEvent(@Nonnull String id, @Nonnull String createdAt, @Nonnull PersonFlagged data) {
        this(id, createdAt, null, data);
    }

    /**
     * The event id, equal to the NR-Webhook-Id header and across redeliveries and every endpoint the event
     * fans out to.
     */
    public String id() {
        return this.id;
    }

    /**
     * When the event was created. Equal across redeliveries; the signing timestamp header is per delivery.
     */
    public String createdAt() {
        return this.createdAt;
    }

    /**
     * Always person.flagged.
     */
    @Override
    public String type() {
        return Utils.discriminatorToString(type);
    }

    /**
     * Present, and true, only on an event sent by POST /v1/webhooks/{endpoint_id}/test: its data is a
     * fixed example assessment, not one of the workspace's, so a receiver acknowledges it and applies
     * nothing. Real events have no test member.
     */
    public Optional<Boolean> test() {
        return Optional.ofNullable(this.test);
    }

    /**
     * A screening recorded a finding on a person. It says which person and how severe, not what they
     * posted, who they are or why: GET the person report for the findings. Sent once per screening that
     * flags the person, so a re-screen of someone on watch announces them again.
     */
    public PersonFlagged data() {
        return this.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event id, equal to the NR-Webhook-Id header and across redeliveries and every endpoint the event
     * fans out to.
     */
    public PersonFlaggedEvent withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * When the event was created. Equal across redeliveries; the signing timestamp header is per delivery.
     */
    public PersonFlaggedEvent withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * Present, and true, only on an event sent by POST /v1/webhooks/{endpoint_id}/test: its data is a
     * fixed example assessment, not one of the workspace's, so a receiver acknowledges it and applies
     * nothing. Real events have no test member.
     */
    public PersonFlaggedEvent withTest(@Nullable Boolean test) {
        this.test = test;
        return this;
    }

    /**
     * A screening recorded a finding on a person. It says which person and how severe, not what they
     * posted, who they are or why: GET the person report for the findings. Sent once per screening that
     * flags the person, so a re-screen of someone on watch announces them again.
     */
    public PersonFlaggedEvent withData(@Nonnull PersonFlagged data) {
        this.data = Utils.checkNotNull(data, "data");
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
        PersonFlaggedEvent other = (PersonFlaggedEvent) o;
        return Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.createdAt, other.createdAt)
                && Utils.enhancedDeepEquals(this.type, other.type)
                && Utils.enhancedDeepEquals(this.test, other.test)
                && Utils.enhancedDeepEquals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(id, createdAt, type, test, data);
    }

    @Override
    public String toString() {
        return Utils.toString(
                PersonFlaggedEvent.class, "id", id, "createdAt", createdAt, "type", type, "test", test, "data", data);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String createdAt;

        private Boolean test;

        private PersonFlagged data;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The event id, equal to the NR-Webhook-Id header and across redeliveries and every endpoint the event
         * fans out to.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * When the event was created. Equal across redeliveries; the signing timestamp header is per delivery.
         */
        public Builder createdAt(@Nonnull String createdAt) {
            this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
            return this;
        }

        /**
         * Present, and true, only on an event sent by POST /v1/webhooks/{endpoint_id}/test: its data is a
         * fixed example assessment, not one of the workspace's, so a receiver acknowledges it and applies
         * nothing. Real events have no test member.
         */
        public Builder test(@Nullable Boolean test) {
            this.test = test;
            return this;
        }

        /**
         * A screening recorded a finding on a person. It says which person and how severe, not what they
         * posted, who they are or why: GET the person report for the findings. Sent once per screening that
         * flags the person, so a re-screen of someone on watch announces them again.
         */
        public Builder data(@Nonnull PersonFlagged data) {
            this.data = Utils.checkNotNull(data, "data");
            return this;
        }

        public PersonFlaggedEvent build() {
            return new PersonFlaggedEvent(id, createdAt, test, data);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Type =
                new LazySingletonValue<>("type", "\"person.flagged\"", new TypeReference<String>() {});
    }
}
