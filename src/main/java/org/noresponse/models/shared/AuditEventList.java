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
 * AuditEventList
 *
 * <p>GET /v1/workspaces/{workspace_id}/audit_events: one page of the workspace's audit log, both
 * environments, newest first. Events are kept for as long as the workspace exists and are deleted with
 * it. next_cursor is present only when another page exists.
 */
public class AuditEventList {
    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    @JsonProperty("workspace_id")
    private String workspaceId;

    @JsonProperty("items")
    private List<AuditEvent> items;

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace it was issued for.
     * Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonCreator
    public AuditEventList(
            @JsonProperty("workspace_id") @Nonnull String workspaceId,
            @JsonProperty("items") @Nonnull List<AuditEvent> items,
            @JsonProperty("next_cursor") @Nullable String nextCursor) {
        this.workspaceId = Optional.ofNullable(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("workspaceId cannot be null"));
        this.items = Optional.ofNullable(items).orElseThrow(() -> new IllegalArgumentException("items cannot be null"));
        this.nextCursor = nextCursor;
    }

    public AuditEventList(@Nonnull String workspaceId, @Nonnull List<AuditEvent> items) {
        this(workspaceId, items, null);
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public String workspaceId() {
        return this.workspaceId;
    }

    public List<AuditEvent> items() {
        return this.items;
    }

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace it was issued for.
     * Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    public Optional<String> nextCursor() {
        return Optional.ofNullable(this.nextCursor);
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The workspace id, which is also its live environment's workspace id; the test environment's is the
     * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
     */
    public AuditEventList withWorkspaceId(@Nonnull String workspaceId) {
        this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
        return this;
    }

    public AuditEventList withItems(@Nonnull List<AuditEvent> items) {
        this.items = Utils.checkNotNull(items, "items");
        return this;
    }

    /**
     * Opaque cursor for the next page, MAC'd by the server and bound to the workspace it was issued for.
     * Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400 invalid_cursor.
     */
    public AuditEventList withNextCursor(@Nullable String nextCursor) {
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
        AuditEventList other = (AuditEventList) o;
        return Utils.enhancedDeepEquals(this.workspaceId, other.workspaceId)
                && Utils.enhancedDeepEquals(this.items, other.items)
                && Utils.enhancedDeepEquals(this.nextCursor, other.nextCursor);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(workspaceId, items, nextCursor);
    }

    @Override
    public String toString() {
        return Utils.toString(
                AuditEventList.class, "workspaceId", workspaceId, "items", items, "nextCursor", nextCursor);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String workspaceId;

        private List<AuditEvent> items;

        private String nextCursor;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The workspace id, which is also its live environment's workspace id; the test environment's is the
         * same id with :test appended. A workspace created through the API is tnt_ and a 26-character ULID.
         */
        public Builder workspaceId(@Nonnull String workspaceId) {
            this.workspaceId = Utils.checkNotNull(workspaceId, "workspaceId");
            return this;
        }

        public Builder items(@Nonnull List<AuditEvent> items) {
            this.items = Utils.checkNotNull(items, "items");
            return this;
        }

        /**
         * Opaque cursor for the next page, MAC'd by the server and bound to the workspace it was issued for.
         * Pass it back unchanged as cursor; a tampered, foreign or expired cursor answers 400 invalid_cursor.
         */
        public Builder nextCursor(@Nullable String nextCursor) {
            this.nextCursor = nextCursor;
            return this;
        }

        public AuditEventList build() {
            return new AuditEventList(workspaceId, items, nextCursor);
        }
    }
}
