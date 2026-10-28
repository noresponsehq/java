package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

/**
 * AnswerRevision
 *
 * <p>Which answer of each assessment the filters describe: latest, the latest stored revision, or first,
 * the first answer, as served when the assessment was first answered (POST /v1/assessments/aggregate
 * counts first answers). A cell of an aggregate drills down to POST /v1/assessments/search with the
 * same filters, revision first and the cell's bucket as from and to.
 */
public enum AnswerRevision {
    LATEST("latest"),
    FIRST("first");

    @JsonValue
    private final String value;

    AnswerRevision(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<AnswerRevision> fromValue(String value) {
        for (AnswerRevision o : AnswerRevision.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
