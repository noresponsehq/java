package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum AssessmentAggregateRequestMetric {
    COUNT("count"),
    SCORE_HISTOGRAM("score_histogram"),
    SCORE_OUTCOMES("score_outcomes"),
    LATE_FLIPS("late_flips");

    @JsonValue
    private final String value;

    AssessmentAggregateRequestMetric(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Optional<AssessmentAggregateRequestMetric> fromValue(String value) {
        for (AssessmentAggregateRequestMetric o : AssessmentAggregateRequestMetric.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}
