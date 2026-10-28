package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * RequestDiagnosticPage
 *
 * <p>Authenticated attempts in a bounded interval, newest first. Keep the same filters when paging.
 */
public class RequestDiagnosticPage {

    @JsonProperty("items")
    private List<RequestDiagnostic> items;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public RequestDiagnosticPage(
            @JsonProperty("items") @Nonnull List<RequestDiagnostic> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
    }

    public RequestDiagnosticPage(@Nonnull List<RequestDiagnostic> items) {
        this(items, null);
    }

    public List<RequestDiagnostic> items() {
        return this.items;
    }

    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    public RequestDiagnosticPage withItems(@Nonnull List<RequestDiagnostic> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    public RequestDiagnosticPage withNextCursor(@Nullable String nextCursor) {
        this.nextCursor = nextCursor;
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
        RequestDiagnosticPage other = (RequestDiagnosticPage) o;
        return Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(RequestDiagnosticPage.class, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private List<RequestDiagnostic> items;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        public Builder items(@Nonnull List<RequestDiagnostic> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public RequestDiagnosticPage build() {
            return new RequestDiagnosticPage(items, nextCursor);
        }
    }
}
