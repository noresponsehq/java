package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * ListPreviewRequest
 *
 * <p>Choose an addition or a removal. The preview performs no list, account, audit, outcome or outbox
 * write.
 */
public class ListPreviewRequest {
    /**
     * POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's
     * list and, in the same change, re-decides every account it matches: each sends account.risk_changed
     * when a decision changes. Needs the admin or owner role, or a management key of the environment.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("add")
    private ListEntryRequest add;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("remove")
    private String remove;

    @JsonCreator
    public ListPreviewRequest(
            @JsonProperty("add") @Nullable ListEntryRequest add, @JsonProperty("remove") @Nullable String remove) {
        this.add = add;
        this.remove = remove;
    }

    public ListPreviewRequest() {
        this(null, null);
    }

    /**
     * POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's
     * list and, in the same change, re-decides every account it matches: each sends account.risk_changed
     * when a decision changes. Needs the admin or owner role, or a management key of the environment.
     */
    public Optional<ListEntryRequest> add() {
        return Optional.ofNullable(this.add);
    }

    public Optional<String> remove() {
        return Optional.ofNullable(this.remove);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's
     * list and, in the same change, re-decides every account it matches: each sends account.risk_changed
     * when a decision changes. Needs the admin or owner role, or a management key of the environment.
     */
    public ListPreviewRequest withAdd(@Nullable ListEntryRequest add) {
        this.add = add;
        return this;
    }

    public ListPreviewRequest withRemove(@Nullable String remove) {
        this.remove = remove;
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
        ListPreviewRequest other = (ListPreviewRequest) o;
        return Utils.enhancedDeepEquals(this.add, other.add) && Utils.enhancedDeepEquals(this.remove, other.remove);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(add, remove);
    }

    @Override
    public String toString() {
        return Utils.toString(ListPreviewRequest.class, "add", add, "remove", remove);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ListEntryRequest add;

        private String remove;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * POST /v1/workspaces/{workspace_id}/lists/{environment} body. Adds one entry to the environment's
         * list and, in the same change, re-decides every account it matches: each sends account.risk_changed
         * when a decision changes. Needs the admin or owner role, or a management key of the environment.
         */
        public Builder add(@Nullable ListEntryRequest add) {
            this.add = add;
            return this;
        }

        public Builder remove(@Nullable String remove) {
            this.remove = remove;
            return this;
        }

        public ListPreviewRequest build() {
            return new ListPreviewRequest(add, remove);
        }
    }
}
