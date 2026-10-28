package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class GetAccountDecisionRequest {
    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=account_id")
    private String accountId;

    /**
     * The canonical decimal risk_version originally served.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=risk_version")
    private long riskVersion;

    @JsonCreator
    public GetAccountDecisionRequest(@Nonnull String accountId, long riskVersion) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.riskVersion = riskVersion;
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public String accountId() {
        return this.accountId;
    }

    /**
     * The canonical decimal risk_version originally served.
     */
    public long riskVersion() {
        return this.riskVersion;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public GetAccountDecisionRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * The canonical decimal risk_version originally served.
     */
    public GetAccountDecisionRequest withRiskVersion(long riskVersion) {
        this.riskVersion = riskVersion;
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
        GetAccountDecisionRequest other = (GetAccountDecisionRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.riskVersion, other.riskVersion);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, riskVersion);
    }

    @Override
    public String toString() {
        return Utils.toString(GetAccountDecisionRequest.class, "accountId", accountId, "riskVersion", riskVersion);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private long riskVersion;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
         * ':' if the client requires it). A value that does not match the account_id shape answers 400
         * invalid_request.
         */
        public Builder accountId(@Nonnull String accountId) {
            this.accountId = Utils.checkNotNull(accountId, "accountId");
            return this;
        }

        /**
         * The canonical decimal risk_version originally served.
         */
        public Builder riskVersion(long riskVersion) {
            this.riskVersion = riskVersion;
            return this;
        }

        public GetAccountDecisionRequest build() {
            return new GetAccountDecisionRequest(accountId, riskVersion);
        }
    }
}
