package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * AssessmentRevision
 *
 * <p>Retrieve a retained assessment version with its original decision, policy, flow and depth. This
 * response excludes the current account decision. Current retention and response-mode permissions
 * still apply.
 */
public class AssessmentRevision {
    /**
     * Version of the body shape.
     */
    @JsonProperty("schema_version")
    private String schemaVersion;

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    @JsonProperty("status")
    private Status status;

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    @JsonProperty("flow")
    private Flow flow;

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    @JsonProperty("depth")
    private Depth depth;

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    @JsonProperty("detail")
    private AssessmentDetail detail;

    @JsonCreator
    public AssessmentRevision(
            @JsonProperty("id") @Nonnull String id,
            @JsonProperty("status") @Nonnull Status status,
            @JsonProperty("flow") @Nonnull Flow flow,
            @JsonProperty("depth") @Nonnull Depth depth,
            @JsonProperty("detail") @Nonnull AssessmentDetail detail) {
        this.schemaVersion = Builder._SINGLETON_VALUE_SchemaVersion.value();
        this.id = Optional.ofNullable(id).orElseThrow(() -> new IllegalArgumentException("id cannot be null"));
        this.status =
                Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("status cannot be null"));
        this.flow = Optional.ofNullable(flow).orElseThrow(() -> new IllegalArgumentException("flow cannot be null"));
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.detail =
                Optional.ofNullable(detail).orElseThrow(() -> new IllegalArgumentException("detail cannot be null"));
    }

    /**
     * Version of the body shape.
     */
    public String schemaVersion() {
        return this.schemaVersion;
    }

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    public String id() {
        return this.id;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public Status status() {
        return this.status;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public Flow flow() {
        return this.flow;
    }

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public Depth depth() {
        return this.depth;
    }

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    public AssessmentDetail detail() {
        return this.detail;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * This assessment's id, equal to detail.assessment_id.
     */
    public AssessmentRevision withId(@Nonnull String id) {
        this.id = Utils.checkNotNull(id, "id");
        return this;
    }

    /**
     * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
     * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
     * one lane is still running or did not settle (see incomplete_sources).
     *
     * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
     */
    public AssessmentRevision withStatus(@Nonnull Status status) {
        this.status = Utils.checkNotNull(status, "status");
        return this;
    }

    /**
     * The event an assessment is made for: account_creation (a user creates an account), login (a user
     * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
     * reported with the decision for this flow.
     */
    public AssessmentRevision withFlow(@Nonnull Flow flow) {
        this.flow = Utils.checkNotNull(flow, "flow");
        return this;
    }

    /**
     * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
     * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
     * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
     * way.
     */
    public AssessmentRevision withDepth(@Nonnull Depth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    /**
     * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
     * running and its timing. risk here is this assessment's own score; the account's current risk is the
     * account member of the assessment. risk and recommendation are present for complete and partial;
     * assessment_url is present for pending.
     */
    public AssessmentRevision withDetail(@Nonnull AssessmentDetail detail) {
        this.detail = Utils.checkNotNull(detail, "detail");
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
        AssessmentRevision other = (AssessmentRevision) o;
        return Utils.enhancedDeepEquals(this.schemaVersion, other.schemaVersion)
                && Utils.enhancedDeepEquals(this.id, other.id)
                && Utils.enhancedDeepEquals(this.status, other.status)
                && Utils.enhancedDeepEquals(this.flow, other.flow)
                && Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.detail, other.detail);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(schemaVersion, id, status, flow, depth, detail);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AssessmentRevision.class,
                "schemaVersion",
                schemaVersion,
                "id",
                id,
                "status",
                status,
                "flow",
                flow,
                "depth",
                depth,
                "detail",
                detail);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String id;

        private Status status;

        private Flow flow;

        private Depth depth;

        private AssessmentDetail detail;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * This assessment's id, equal to detail.assessment_id.
         */
        public Builder id(@Nonnull String id) {
            this.id = Utils.checkNotNull(id, "id");
            return this;
        }

        /**
         * complete: final; nothing is still running. A lane that failed or missed its 10 s deadline stays
         * listed in incomplete_sources, and confidence is lower. partial: a recommendation exists and at least
         * one lane is still running or did not settle (see incomplete_sources).
         *
         * <p>pending: nothing usable before the deadline; no recommendation yet, poll or wait for the webhook.
         */
        public Builder status(@Nonnull Status status) {
            this.status = Utils.checkNotNull(status, "status");
            return this;
        }

        /**
         * The event an assessment is made for: account_creation (a user creates an account), login (a user
         * returns to an existing account) or purchase (a payment or a credit grant). The account's risk is
         * reported with the decision for this flow.
         */
        public Builder flow(@Nonnull Flow flow) {
            this.flow = Utils.checkNotNull(flow, "flow");
            return this;
        }

        /**
         * How long the synchronous answer waits for sources. quick waits only for the fast sources, those that
         * answer within 500 ms of ingress (or deadline_ms when lower); full waits for the whole budget, up to
         * 3000 ms. Sources still running at the cut continue asynchronously and update the assessment either
         * way.
         */
        public Builder depth(@Nonnull Depth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        /**
         * One assessment's own facts: its score and recommendation, the signals behind them, the lanes still
         * running and its timing. risk here is this assessment's own score; the account's current risk is the
         * account member of the assessment. risk and recommendation are present for complete and partial;
         * assessment_url is present for pending.
         */
        public Builder detail(@Nonnull AssessmentDetail detail) {
            this.detail = Utils.checkNotNull(detail, "detail");
            return this;
        }

        public AssessmentRevision build() {
            return new AssessmentRevision(id, status, flow, depth, detail);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_SchemaVersion =
                new LazySingletonValue<>("schema_version", "\"1\"", new TypeReference<String>() {});
    }
}
