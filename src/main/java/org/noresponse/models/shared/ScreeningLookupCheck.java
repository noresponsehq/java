package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ScreeningLookupCheck
 *
 * <p>What came of one lane of the lookups for one identifier.
 */
public class ScreeningLookupCheck {

    @JsonProperty("identifier")
    private IdentifierSearchKind identifier;

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    @JsonProperty("lane")
    private Lane lane;

    /**
     * found: a source found the identifier. not_found: every source answered and none found it. partial:
     * the sources that answered found nothing and the others did not answer.
     *
     * <p>not_covered: no source answered. A lookup that failed reads partial or not_covered, not not_found.
     */
    @JsonProperty("status")
    private ScreeningLookupCheckStatus status;

    /**
     * Sources asked: none when no source could be, as when the source is stopped or your workspace refuses
     * it.
     */
    @JsonProperty("sources")
    private long sources;

    /**
     * Sources that gave a definite answer.
     */
    @JsonProperty("answered")
    private long answered;

    /**
     * Sources that found the identifier.
     */
    @JsonProperty("found")
    private long found;

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("reason")
    private FailureReason reason;

    @JsonCreator
    public ScreeningLookupCheck(
            @JsonProperty("identifier") @Nonnull IdentifierSearchKind identifier,
            @JsonProperty("lane") @Nonnull Lane lane,
            @JsonProperty("status") @Nonnull ScreeningLookupCheckStatus status,
            @JsonProperty("sources") long sources,
            @JsonProperty("answered") long answered,
            @JsonProperty("found") long found,
            @JsonProperty("reason") @Nullable FailureReason reason) {
        this.identifier = Optional.ofNullable(identifier)
                .orElseThrow(() -> new IllegalArgumentException("identifier cannot be null"));
        this.lane = Optional.ofNullable(lane).orElseThrow(() -> new IllegalArgumentException("lane cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.sources = sources;
        this.answered = answered;
        this.found = found;
        this.reason = reason;
    }

    public ScreeningLookupCheck(
            @Nonnull IdentifierSearchKind identifier,
            @Nonnull Lane lane,
            @Nonnull ScreeningLookupCheckStatus status,
            long sources,
            long answered,
            long found) {
        this(identifier, lane, status, sources, answered, found, null);
    }

    public IdentifierSearchKind identifier() {
        return this.identifier;
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public Lane lane() {
        return this.lane;
    }

    /**
     * found: a source found the identifier. not_found: every source answered and none found it. partial:
     * the sources that answered found nothing and the others did not answer.
     *
     * <p>not_covered: no source answered. A lookup that failed reads partial or not_covered, not not_found.
     */
    public ScreeningLookupCheckStatus status() {
        return this.status;
    }

    /**
     * Sources asked: none when no source could be, as when the source is stopped or your workspace refuses
     * it.
     */
    public long sources() {
        return this.sources;
    }

    /**
     * Sources that gave a definite answer.
     */
    public long answered() {
        return this.answered;
    }

    /**
     * Sources that found the identifier.
     */
    public long found() {
        return this.found;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public Optional<FailureReason> reason() {
        return Optional.ofNullable(this.reason);
    }

    public static Builder builder() {
        return new Builder();
    }

    public ScreeningLookupCheck withIdentifier(@Nonnull IdentifierSearchKind identifier) {
        this.identifier = Utils.checkNotNull(identifier, "identifier");
        return this;
    }

    /**
     * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
     * email domain's registration) and ip.
     */
    public ScreeningLookupCheck withLane(@Nonnull Lane lane) {
        this.lane = Utils.checkNotNull(lane, "lane");
        return this;
    }

    /**
     * found: a source found the identifier. not_found: every source answered and none found it. partial:
     * the sources that answered found nothing and the others did not answer.
     *
     * <p>not_covered: no source answered. A lookup that failed reads partial or not_covered, not not_found.
     */
    public ScreeningLookupCheck withStatus(@Nonnull ScreeningLookupCheckStatus status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * Sources asked: none when no source could be, as when the source is stopped or your workspace refuses
     * it.
     */
    public ScreeningLookupCheck withSources(long sources) {
        this.sources = sources;
        return this;
    }

    /**
     * Sources that gave a definite answer.
     */
    public ScreeningLookupCheck withAnswered(long answered) {
        this.answered = answered;
        return this;
    }

    /**
     * Sources that found the identifier.
     */
    public ScreeningLookupCheck withFound(long found) {
        this.found = found;
        return this;
    }

    /**
     * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
     * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
     * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
     */
    public ScreeningLookupCheck withReason(@Nullable FailureReason reason) {
        this.reason = reason;
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
        ScreeningLookupCheck other = (ScreeningLookupCheck) o;
        return Utils.enhancedDeepEquals(this.identifier, other.identifier)
                && Utils.enhancedDeepEquals(this.lane, other.lane)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.sources, other.sources)
                && Utils.enhancedDeepEquals(this.answered, other.answered)
                && Utils.enhancedDeepEquals(this.found, other.found)
                && Utils.enhancedDeepEquals(this.reason, other.reason);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(identifier, lane, status, sources, answered, found, reason);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ScreeningLookupCheck.class,
                "identifier",
                identifier,
                "lane",
                lane,
                "status",
                status,
                "sources",
                sources,
                "answered",
                answered,
                "found",
                found,
                "reason",
                reason);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private IdentifierSearchKind identifier;

        private Lane lane;

        private ScreeningLookupCheckStatus status;

        private long sources;

        private long answered;

        private long found;

        private FailureReason reason;

        private Builder() {
            // force use of static builder() method
        }

        public Builder identifier(@Nonnull IdentifierSearchKind identifier) {
            this.identifier = Utils.checkNotNull(identifier, "identifier");
            return this;
        }

        /**
         * An evidence lane: accounts (account presence for email and username), phone, breach, domain (the
         * email domain's registration) and ip.
         */
        public Builder lane(@Nonnull Lane lane) {
            this.lane = Utils.checkNotNull(lane, "lane");
            return this;
        }

        /**
         * found: a source found the identifier. not_found: every source answered and none found it. partial:
         * the sources that answered found nothing and the others did not answer.
         *
         * <p>not_covered: no source answered. A lookup that failed reads partial or not_covered, not not_found.
         */
        public Builder status(@Nonnull ScreeningLookupCheckStatus status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * Sources asked: none when no source could be, as when the source is stopped or your workspace refuses
         * it.
         */
        public Builder sources(long sources) {
            this.sources = sources;
            return this;
        }

        /**
         * Sources that gave a definite answer.
         */
        public Builder answered(long answered) {
            this.answered = answered;
            return this;
        }

        /**
         * Sources that found the identifier.
         */
        public Builder found(long found) {
            this.found = found;
            return this;
        }

        /**
         * Why a signal or source did not produce a definite found/not_found. timed_out means the source missed
         * the request deadline; a continuation may still settle it. deferred means the (source, identifier)
         * pair is async-only and was not attempted on the synchronous path; a continuation settles it.
         */
        public Builder reason(@Nullable FailureReason reason) {
            this.reason = reason;
            return this;
        }

        public ScreeningLookupCheck build() {
            return new ScreeningLookupCheck(identifier, lane, status, sources, answered, found, reason);
        }
    }
}
