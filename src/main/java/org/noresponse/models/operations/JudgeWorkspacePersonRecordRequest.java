package org.noresponse.models.operations;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.models.shared.RecordVerdictRequest;
import org.noresponse.utils.NoResponseMetadata;
import org.noresponse.utils.Utils;

public class JudgeWorkspacePersonRecordRequest {

    @NoResponseMetadata("pathParam:style=simple,explode=false,name=person_id")
    private String personId;

    @NoResponseMetadata("request:mediaType=application/json")
    private RecordVerdictRequest body;

    @JsonCreator
    public JudgeWorkspacePersonRecordRequest(@Nonnull String personId, @Nonnull RecordVerdictRequest body) {
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.body = Optional.ofNullable(body).orElseThrow(() -> new IllegalArgumentException("body cannot be null"));
    }

    public String personId() {
        return this.personId;
    }

    public RecordVerdictRequest body() {
        return this.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public JudgeWorkspacePersonRecordRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    public JudgeWorkspacePersonRecordRequest withBody(@Nonnull RecordVerdictRequest body) {
        this.body = Utils.checkNotNull(body, "body");
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
        JudgeWorkspacePersonRecordRequest other = (JudgeWorkspacePersonRecordRequest) o;
        return Utils.enhancedDeepEquals(this.personId, other.personId) && Utils.enhancedDeepEquals(this.body, other.body);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(personId, body);
    }

    @Override
    public String toString() {
        return Utils.toString(JudgeWorkspacePersonRecordRequest.class, "personId", personId, "body", body);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String personId;

        private RecordVerdictRequest body;

        private Builder() {
            // force use of static builder() method
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        public Builder body(@Nonnull RecordVerdictRequest body) {
            this.body = Utils.checkNotNull(body, "body");
            return this;
        }

        public JudgeWorkspacePersonRecordRequest build() {
            return new JudgeWorkspacePersonRecordRequest(personId, body);
        }
    }
}
