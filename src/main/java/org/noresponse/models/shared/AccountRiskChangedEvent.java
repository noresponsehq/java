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
 * AccountRiskChangedEvent
 *
 * <p>The account.risk_changed event.
 */
public class AccountRiskChangedEvent implements WebhookEvent {
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
     * Always account.risk_changed.
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
     * The account's decision for at least one flow changed. account is the account as GET
     * /v1/accounts/{account_id} answers it for the revision that made the change: its new score, decision,
     * reasons and decisions_by_flow, and the assessment the risk comes from. previous_decisions_by_flow
     * are the decisions it replaced, so account.decisions_by_flow differs from them for at least one flow,
     * and previous_risk_version is the risk_version they were announced at.
     *
     * <p>On the account's first event no decision was announced before: previous_risk_version is absent and
     * previous_decisions_by_flow is allow for every flow, the decisions an account has before its first
     * event. Events of one account are not ordered: account.risk_version orders them, so keep the account
     * with the highest risk_version and ignore an event whose account is older than the one held.
     */
    @JsonProperty("data")
    private AccountRiskChanged data;

    @JsonCreator
    public AccountRiskChangedEvent(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("created_at") @Nonnull String createdAt,
            @JsonProperty("test") @Nullable Boolean test,
            @JsonProperty("data") @Nonnull AccountRiskChanged data) {
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.createdAt = Optional.ofNullable(createdAt)
                .orElseThrow(() -> new IllegalArgumentException("createdAt cannot be null"));
        this.type = Builder._SINGLETON_VALUE_Type.value();
        this.test = test;
        this.data = Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("data cannot be null"));
    }

    public AccountRiskChangedEvent(@Nonnull String id, @Nonnull String createdAt, @Nonnull AccountRiskChanged data) {
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
     * Always account.risk_changed.
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
     * The account's decision for at least one flow changed. account is the account as GET
     * /v1/accounts/{account_id} answers it for the revision that made the change: its new score, decision,
     * reasons and decisions_by_flow, and the assessment the risk comes from. previous_decisions_by_flow
     * are the decisions it replaced, so account.decisions_by_flow differs from them for at least one flow,
     * and previous_risk_version is the risk_version they were announced at.
     *
     * <p>On the account's first event no decision was announced before: previous_risk_version is absent and
     * previous_decisions_by_flow is allow for every flow, the decisions an account has before its first
     * event. Events of one account are not ordered: account.risk_version orders them, so keep the account
     * with the highest risk_version and ignore an event whose account is older than the one held.
     */
    public AccountRiskChanged data() {
        return this.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The event id, equal to the NR-Webhook-Id header and across redeliveries and every endpoint the event
     * fans out to.
     */
    public AccountRiskChangedEvent withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * When the event was created. Equal across redeliveries; the signing timestamp header is per delivery.
     */
    public AccountRiskChangedEvent withCreatedAt(@Nonnull String createdAt) {
        this.createdAt = Utils.checkNotNull(createdAt, "createdAt");
        return this;
    }

    /**
     * Present, and true, only on an event sent by POST /v1/webhooks/{endpoint_id}/test: its data is a
     * fixed example assessment, not one of the workspace's, so a receiver acknowledges it and applies
     * nothing. Real events have no test member.
     */
    public AccountRiskChangedEvent withTest(@Nullable Boolean test) {
        this.test = test;
        return this;
    }

    /**
     * The account's decision for at least one flow changed. account is the account as GET
     * /v1/accounts/{account_id} answers it for the revision that made the change: its new score, decision,
     * reasons and decisions_by_flow, and the assessment the risk comes from. previous_decisions_by_flow
     * are the decisions it replaced, so account.decisions_by_flow differs from them for at least one flow,
     * and previous_risk_version is the risk_version they were announced at.
     *
     * <p>On the account's first event no decision was announced before: previous_risk_version is absent and
     * previous_decisions_by_flow is allow for every flow, the decisions an account has before its first
     * event. Events of one account are not ordered: account.risk_version orders them, so keep the account
     * with the highest risk_version and ignore an event whose account is older than the one held.
     */
    public AccountRiskChangedEvent withData(@Nonnull AccountRiskChanged data) {
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
        AccountRiskChangedEvent other = (AccountRiskChangedEvent) o;
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
                AccountRiskChangedEvent.class,
                "id",
                id,
                "createdAt",
                createdAt,
                "type",
                type,
                "test",
                test,
                "data",
                data);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private String createdAt;

        private Boolean test;

        private AccountRiskChanged data;

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
         * The account's decision for at least one flow changed. account is the account as GET
         * /v1/accounts/{account_id} answers it for the revision that made the change: its new score, decision,
         * reasons and decisions_by_flow, and the assessment the risk comes from. previous_decisions_by_flow
         * are the decisions it replaced, so account.decisions_by_flow differs from them for at least one flow,
         * and previous_risk_version is the risk_version they were announced at.
         *
         * <p>On the account's first event no decision was announced before: previous_risk_version is absent and
         * previous_decisions_by_flow is allow for every flow, the decisions an account has before its first
         * event. Events of one account are not ordered: account.risk_version orders them, so keep the account
         * with the highest risk_version and ignore an event whose account is older than the one held.
         */
        public Builder data(@Nonnull AccountRiskChanged data) {
            this.data = Utils.checkNotNull(data, "data");
            return this;
        }

        public AccountRiskChangedEvent build() {
            return new AccountRiskChangedEvent(id, createdAt, test, data);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Type =
                new LazySingletonValue<>("type", "\"account.risk_changed\"", new TypeReference<String>() {});
    }
}
