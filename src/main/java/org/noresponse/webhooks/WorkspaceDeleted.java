package org.noresponse.webhooks;

/**
 * The workspace was deleted. Sent once per environment before its endpoints are
 * torn down: the last event this endpoint receives. {@link #environment()} is
 * {@code live} or {@code test}.
 */
public final class WorkspaceDeleted {
    private final String workspaceId;
    private final String environment;
    private final String deletedAt;

    public WorkspaceDeleted(String workspaceId, String environment, String deletedAt) {
        this.workspaceId = workspaceId;
        this.environment = environment;
        this.deletedAt = deletedAt;
    }

    public String workspaceId() {
        return workspaceId;
    }

    public String environment() {
        return environment;
    }

    public String deletedAt() {
        return deletedAt;
    }
}
