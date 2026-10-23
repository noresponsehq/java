package org.noresponse;

import java.util.stream.Stream;
import org.noresponse.models.shared.AssessmentDetail;
import org.noresponse.models.shared.AssessmentQuery;

/** Every assessment of one account, page by page. Hand-written beside the generated client. */
public final class AllAssessments {
    private AllAssessments() {
    }

    /**
     * A lazy stream over every unexpired assessment of one account, newest first. Pages are fetched
     * as the stream is consumed, so stopping early fetches no further pages.
     */
    public static Stream<AssessmentDetail> stream(NoResponse client, AssessmentQuery query) {
        return client.assessments().query().request(query).callAsStream()
                .flatMap(page -> page.assessmentList().map(list -> list.items().stream()).orElseGet(Stream::empty));
    }
}
