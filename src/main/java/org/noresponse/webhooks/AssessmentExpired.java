package org.noresponse.webhooks;

import java.util.Objects;

/**
 * The final event for an assessment: it has expired and get now answers 404.
 * {@link #assessmentVersion()} is the last served version; expiry is not a revision.
 */
public final class AssessmentExpired {
    private final String assessmentId;
    private final String accountId;
    private final long assessmentVersion;

    public AssessmentExpired(String assessmentId, String accountId, long assessmentVersion) {
        this.assessmentId = assessmentId;
        this.accountId = accountId;
        this.assessmentVersion = assessmentVersion;
    }

    public String assessmentId() {
        return assessmentId;
    }

    public String accountId() {
        return accountId;
    }

    public long assessmentVersion() {
        return assessmentVersion;
    }

    public String status() {
        return "expired";
    }

    public String changeReason() {
        return "expired";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AssessmentExpired)) {
            return false;
        }
        AssessmentExpired other = (AssessmentExpired) o;
        return assessmentId.equals(other.assessmentId) && accountId.equals(other.accountId)
                && assessmentVersion == other.assessmentVersion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assessmentId, accountId, assessmentVersion);
    }
}
