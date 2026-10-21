package org.noresponse.models.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.InputStream;
import java.lang.Deprecated;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Throwable;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.noresponse.models.shared.AuthenticationErrorCode;
import org.noresponse.models.shared.ProblemInvalidParam;
import org.noresponse.utils.Blob;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

@SuppressWarnings("serial")
public class AuthenticationError extends NoResponseException {

    @Nullable
    private final Data data;

    @Nullable
    private final Throwable deserializationException;

    public AuthenticationError(
            int code,
            byte[] body,
            HttpResponse<?> rawResponse,
            @Nullable Data data,
            @Nullable Throwable deserializationException) {
        super("API error occurred", code, body, rawResponse, null);
        this.data = data;
        this.deserializationException = deserializationException;
    }

    /**
     * Parse a response into an instance of AuthenticationError. If deserialization of the response body fails,
     * the resulting AuthenticationError instance will have a null data() value and a non-null deserializationException().
     */
    public static AuthenticationError from(HttpResponse<InputStream> response) {
        byte[] bytes;
        try {
            bytes = Utils.extractByteArrayFromBody(response);
        } catch (Exception e) {
            return new AuthenticationError(response.statusCode(), null, response, null, e);
        }
        try {
            Data data = Utils.mapper().readValue(bytes, Data.class);
            return new AuthenticationError(response.statusCode(), bytes, response, data, null);
        } catch (Exception e) {
            return new AuthenticationError(response.statusCode(), bytes, response, null, e);
        }
    }

    /**
     * Parse a response into an instance of AuthenticationError asynchronously. If deserialization of the response body fails,
     * the resulting AuthenticationError instance will have a null data() value and a non-null deserializationException().
     */
    public static CompletableFuture<AuthenticationError> fromAsync(HttpResponse<Blob> response) {
        return response.body().toByteArray().handle((bytes, err) -> {
            // if a body read error occurs, we want to transform the exception
            if (err != null) {
                throw new AsyncAPIStatusException(
                        "Error reading response body: " + err.getMessage(), response.statusCode(), null, response, err);
            }

            try {
                return new AuthenticationError(
                        response.statusCode(),
                        bytes,
                        response,
                        Utils.mapper().readValue(bytes, new TypeReference<Data>() {}),
                        null);
            } catch (Exception e) {
                return new AuthenticationError(response.statusCode(), bytes, response, null, e);
            }
        });
    }

    @Deprecated
    public Optional<String> type() {
        return data().map(Data::type);
    }

    @Deprecated
    public Optional<String> title() {
        return data().map(Data::title);
    }

    @Deprecated
    public Optional<String> detail() {
        return data().map(Data::detail);
    }

    @Deprecated
    public Optional<String> instance() {
        return data().flatMap(Data::instance);
    }

    @Deprecated
    public Optional<String> requestId() {
        return data().map(Data::requestId);
    }

    @Deprecated
    public Optional<List<ProblemInvalidParam>> invalidParams() {
        return data().flatMap(Data::invalidParams);
    }

    public Optional<Data> data() {
        return Optional.ofNullable(data);
    }

    /**
     * Returns the exception if an error occurs while deserializing the response body.
     */
    public Optional<Throwable> deserializationException() {
        return Optional.ofNullable(deserializationException);
    }
    /**
     * Data
     *
     * <p>HTTP 401 problem document.
     */
    public static class Data {

        @JsonProperty("type")
        private String type;

        @JsonProperty("title")
        private String title;

        @JsonProperty("status")
        private long status;

        @JsonProperty("detail")
        private String detail;

        @JsonInclude(Include.NON_ABSENT)
        @JsonProperty("instance")
        private String instance;

        /**
         * Machine codes an HTTP 401 answer can carry.
         */
        @JsonProperty("code")
        private AuthenticationErrorCode code;

        @JsonProperty("request_id")
        private String requestId;

        @JsonInclude(Include.NON_ABSENT)
        @JsonProperty("invalid_params")
        private List<ProblemInvalidParam> invalidParams;

        @JsonCreator
        public Data(
                @JsonProperty("type") @Nonnull String type,
                @JsonProperty("title") @Nonnull String title,
                @JsonProperty("detail") @Nonnull String detail,
                @JsonProperty("instance") @Nullable String instance,
                @JsonProperty("code") @Nonnull AuthenticationErrorCode code,
                @JsonProperty("request_id") @Nonnull String requestId,
                @JsonProperty("invalid_params") @Nullable List<ProblemInvalidParam> invalidParams) {
            this.type =
                    Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("type cannot be null"));
            this.title =
                    Optional.ofNullable(title).orElseThrow(() -> new IllegalArgumentException("title cannot be null"));
            this.status = Builder._SINGLETON_VALUE_Status.value();
            this.detail = Optional.ofNullable(detail)
                    .orElseThrow(() -> new IllegalArgumentException("detail cannot be null"));
            this.instance = instance;
            this.code =
                    Optional.ofNullable(code).orElseThrow(() -> new IllegalArgumentException("code cannot be null"));
            this.requestId = Optional.ofNullable(requestId)
                    .orElseThrow(() -> new IllegalArgumentException("requestId cannot be null"));
            this.invalidParams = invalidParams;
        }

        public Data(
                @Nonnull String type,
                @Nonnull String title,
                @Nonnull String detail,
                @Nonnull AuthenticationErrorCode code,
                @Nonnull String requestId) {
            this(type, title, detail, null, code, requestId, null);
        }

        public String type() {
            return this.type;
        }

        public String title() {
            return this.title;
        }

        public long status() {
            return this.status;
        }

        public String detail() {
            return this.detail;
        }

        public Optional<String> instance() {
            return Optional.ofNullable(this.instance);
        }

        /**
         * Machine codes an HTTP 401 answer can carry.
         */
        public AuthenticationErrorCode code() {
            return this.code;
        }

        public String requestId() {
            return this.requestId;
        }

        public Optional<List<ProblemInvalidParam>> invalidParams() {
            return Optional.ofNullable(this.invalidParams);
        }

        public static Builder builder() {
            return new Builder();
        }

        public Data withType(@Nonnull String type) {
            this.type = Utils.checkNotNull(type, "type");
            return this;
        }

        public Data withTitle(@Nonnull String title) {
            this.title = Utils.checkNotNull(title, "title");
            return this;
        }

        public Data withDetail(@Nonnull String detail) {
            this.detail = Utils.checkNotNull(detail, "detail");
            return this;
        }

        public Data withInstance(@Nullable String instance) {
            this.instance = instance;
            return this;
        }

        /**
         * Machine codes an HTTP 401 answer can carry.
         */
        public Data withCode(@Nonnull AuthenticationErrorCode code) {
            this.code = Utils.checkNotNull(code, "code");
            return this;
        }

        public Data withRequestId(@Nonnull String requestId) {
            this.requestId = Utils.checkNotNull(requestId, "requestId");
            return this;
        }

        public Data withInvalidParams(@Nullable List<ProblemInvalidParam> invalidParams) {
            this.invalidParams = invalidParams;
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
            Data other = (Data) o;
            return Utils.enhancedDeepEquals(this.type, other.type)
                    && Utils.enhancedDeepEquals(this.title, other.title)
                    && Utils.enhancedDeepEquals(this.status, other.status)
                    && Utils.enhancedDeepEquals(this.detail, other.detail)
                    && Utils.enhancedDeepEquals(this.instance, other.instance)
                    && Utils.enhancedDeepEquals(this.code, other.code)
                    && Utils.enhancedDeepEquals(this.requestId, other.requestId)
                    && Utils.enhancedDeepEquals(this.invalidParams, other.invalidParams);
        }

        @Override
        public int hashCode() {
            return Utils.enhancedHash(type, title, status, detail, instance, code, requestId, invalidParams);
        }

        @Override
        public String toString() {
            return Utils.toString(
                    Data.class,
                    "type",
                    type,
                    "title",
                    title,
                    "status",
                    status,
                    "detail",
                    detail,
                    "instance",
                    instance,
                    "code",
                    code,
                    "requestId",
                    requestId,
                    "invalidParams",
                    invalidParams);
        }

        @SuppressWarnings("UnusedReturnValue")
        public static final class Builder {

            private String type;

            private String title;

            private String detail;

            private String instance;

            private AuthenticationErrorCode code;

            private String requestId;

            private List<ProblemInvalidParam> invalidParams;

            private Builder() {
                // force use of static builder() method
            }

            public Builder type(@Nonnull String type) {
                this.type = Utils.checkNotNull(type, "type");
                return this;
            }

            public Builder title(@Nonnull String title) {
                this.title = Utils.checkNotNull(title, "title");
                return this;
            }

            public Builder detail(@Nonnull String detail) {
                this.detail = Utils.checkNotNull(detail, "detail");
                return this;
            }

            public Builder instance(@Nullable String instance) {
                this.instance = instance;
                return this;
            }

            /**
             * Machine codes an HTTP 401 answer can carry.
             */
            public Builder code(@Nonnull AuthenticationErrorCode code) {
                this.code = Utils.checkNotNull(code, "code");
                return this;
            }

            public Builder requestId(@Nonnull String requestId) {
                this.requestId = Utils.checkNotNull(requestId, "requestId");
                return this;
            }

            public Builder invalidParams(@Nullable List<ProblemInvalidParam> invalidParams) {
                this.invalidParams = invalidParams;
                return this;
            }

            public Data build() {
                return new Data(type, title, detail, instance, code, requestId, invalidParams);
            }

            private static final LazySingletonValue<Long> _SINGLETON_VALUE_Status =
                    new LazySingletonValue<>("status", "401", new TypeReference<Long>() {});
        }
    }
}
