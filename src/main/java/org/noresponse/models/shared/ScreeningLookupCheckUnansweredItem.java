package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class ScreeningLookupCheckUnansweredItem {
    /**
     * The id of the connector that did not answer: breach for the breach search.
     */
    @JsonProperty("connector")
    private String connector;

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    @JsonProperty("reason")
    private FailureReason reason;

    @JsonCreator
    public ScreeningLookupCheckUnansweredItem(
            @JsonProperty("connector") @Nonnull String connector,
            @JsonProperty("reason") @Nonnull FailureReason reason) {
        this.connector = Optional.ofNullable(connector)
                .orElseThrow(() -> new IllegalArgumentException("connector cannot be null"));
        this.reason =
                Optional.ofNullable(reason).orElseThrow(() -> new IllegalArgumentException("reason cannot be null"));
    }

    /**
     * The id of the connector that did not answer: breach for the breach search.
     */
    public String connector() {
        return this.connector;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public FailureReason reason() {
        return this.reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The id of the connector that did not answer: breach for the breach search.
     */
    public ScreeningLookupCheckUnansweredItem withConnector(@Nonnull String connector) {
        this.connector = Utils.checkNotNull(connector, "connector");
        return this;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public ScreeningLookupCheckUnansweredItem withReason(@Nonnull FailureReason reason) {
        this.reason = Utils.checkNotNull(reason, "reason");
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
        ScreeningLookupCheckUnansweredItem other = (ScreeningLookupCheckUnansweredItem) o;
        return Utils.enhancedDeepEquals(this.connector, other.connector)
                && Utils.enhancedDeepEquals(this.reason, other.reason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(connector, reason);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningLookupCheckUnansweredItem.class, "connector", connector, "reason", reason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String connector;

        private FailureReason reason;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The id of the connector that did not answer: breach for the breach search.
         */
        public Builder connector(@Nonnull String connector) {
            this.connector = Utils.checkNotNull(connector, "connector");
            return this;
        }

        /**
         * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
         * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
         * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
         */
        public Builder reason(@Nonnull FailureReason reason) {
            this.reason = Utils.checkNotNull(reason, "reason");
            return this;
        }

        public ScreeningLookupCheckUnansweredItem build() {
            return new ScreeningLookupCheckUnansweredItem(connector, reason);
        }
    }
}
