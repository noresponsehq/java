package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.ResponseMode;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class ListAccountAssessmentsRequest {
    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    @NoResponseMetadata("pathParam:style=simple,explode=false,name=account_id")
    private String accountId;

    /**
     * Items per page.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=limit")
    private Long limit;

    /**
     * next_cursor from the previous page, unchanged.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=cursor")
    private String cursor;

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    @NoResponseMetadata("queryParam:style=form,explode=true,name=response_mode")
    private ResponseMode responseMode;

    @JsonCreator
    public ListAccountAssessmentsRequest(
            @Nonnull String accountId,
            @Nullable Long limit,
            @Nullable String cursor,
            @Nullable ResponseMode responseMode) {
        this.accountId = Optional.ofNullable(accountId)
                .orElseThrow(() -> new IllegalArgumentException("accountId cannot be null"));
        this.limit = Optional.ofNullable(limit).orElse(Builder._SINGLETON_VALUE_Limit.value());
        this.cursor = cursor;
        this.responseMode = responseMode;
    }

    public ListAccountAssessmentsRequest(@Nonnull String accountId) {
        this(accountId, null, null, null);
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
     * Items per page.
     */
    public Optional<Long> limit() {
        return Optional.ofNullable(this.limit);
    }

    /**
     * next_cursor from the previous page, unchanged.
     */
    public Optional<String> cursor() {
        return Optional.ofNullable(this.cursor);
    }

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    public Optional<ResponseMode> responseMode() {
        return Optional.ofNullable(this.responseMode);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The account_id the workspace sends with its assessments, as one path segment (percent-encode '|' and
     * ':' if the client requires it). A value that does not match the account_id shape answers 400
     * invalid_request.
     */
    public ListAccountAssessmentsRequest withAccountId(@Nonnull String accountId) {
        this.accountId = Utils.checkNotNull(accountId, "accountId");
        return this;
    }

    /**
     * Items per page.
     */
    public ListAccountAssessmentsRequest withLimit(@Nullable Long limit) {
        this.limit = limit;
        return this;
    }

    /**
     * next_cursor from the previous page, unchanged.
     */
    public ListAccountAssessmentsRequest withCursor(@Nullable String cursor) {
        this.cursor = cursor;
        return this;
    }

    /**
     * Optional standard or separately authorised detailed customer view; raw is rejected.
     */
    public ListAccountAssessmentsRequest withResponseMode(@Nullable ResponseMode responseMode) {
        this.responseMode = responseMode;
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
        ListAccountAssessmentsRequest other = (ListAccountAssessmentsRequest) o;
        return Utils.enhancedDeepEquals(this.accountId, other.accountId)
                && Utils.enhancedDeepEquals(this.limit, other.limit)
                && Utils.enhancedDeepEquals(this.cursor, other.cursor)
                && Utils.enhancedDeepEquals(this.responseMode, other.responseMode);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(accountId, limit, cursor, responseMode);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListAccountAssessmentsRequest.class,
                "accountId",
                accountId,
                "limit",
                limit,
                "cursor",
                cursor,
                "responseMode",
                responseMode);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String accountId;

        private Long limit;

        private String cursor;

        private ResponseMode responseMode;

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
         * Items per page.
         */
        public Builder limit(@Nullable Long limit) {
            this.limit = limit;
            return this;
        }

        /**
         * next_cursor from the previous page, unchanged.
         */
        public Builder cursor(@Nullable String cursor) {
            this.cursor = cursor;
            return this;
        }

        /**
         * Optional standard or separately authorised detailed customer view; raw is rejected.
         */
        public Builder responseMode(@Nullable ResponseMode responseMode) {
            this.responseMode = responseMode;
            return this;
        }

        public ListAccountAssessmentsRequest build() {
            return new ListAccountAssessmentsRequest(accountId, limit, cursor, responseMode);
        }

        private static final LazySingletonValue<Long> _SINGLETON_VALUE_Limit =
                new LazySingletonValue<>("limit", "20", new TypeReference<Long>() {});
    }
}
