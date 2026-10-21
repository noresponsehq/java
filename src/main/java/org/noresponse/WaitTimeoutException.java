package org.noresponse;

import org.noresponse.models.shared.Assessment;

/**
 * The assessment was still pending when the wait ran out. {@link #last()} is
 * the pending assessment from the final poll; the webhook delivers the result
 * when it settles.
 */
public final class WaitTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String assessmentId;
    private final transient Assessment last;

    WaitTimeoutException(String assessmentId, Assessment last) {
        super("Assessment " + assessmentId + " is still pending");
        this.assessmentId = assessmentId;
        this.last = last;
    }

    public String assessmentId() {
        return assessmentId;
    }

    public Assessment last() {
        return last;
    }
}
