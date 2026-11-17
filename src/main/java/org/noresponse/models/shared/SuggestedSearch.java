package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * SuggestedSearch
 *
 * <p>A search enrichment would start: a distinctive or unique value of a likely record, not yet searched
 * for the person. A person starts it with POST .../searches, naming the record and the field.
 */
public class SuggestedSearch {
    /**
     * The lookup record whose value enrichment would search. It is among the report's lookup records.
     */
    @JsonProperty("record_id")
    private String recordId;

    /**
     * The field of that record that holds the value. Send record_id and field to POST .../searches to
     * start the search.
     */
    @JsonProperty("field")
    private String field;

    /**
     * The kind of the value: one of the kinds the environment's enrichment setting names.
     */
    @JsonProperty("kind")
    private SuggestedSearchKind kind;

    /**
     * The likelihood of the record that holds the value, from 0.01 to 0.99. Enrichment suggests a value
     * only from a record more likely than the setting's min_likelihood, the likeliest first.
     */
    @JsonProperty("likelihood")
    private double likelihood;

    @JsonCreator
    public SuggestedSearch(
            @JsonProperty("record_id") @Nonnull String recordId,
            @JsonProperty("field") @Nonnull String field,
            @JsonProperty("kind") @Nonnull SuggestedSearchKind kind,
            @JsonProperty("likelihood") double likelihood) {
        this.recordId = Optional.ofNullable(recordId)
                .orElseThrow(() -> new IllegalArgumentException("recordId cannot be null"));
        this.field = Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException("field cannot be null"));
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.likelihood = likelihood;
    }

    /**
     * The lookup record whose value enrichment would search. It is among the report's lookup records.
     */
    public String recordId() {
        return this.recordId;
    }

    /**
     * The field of that record that holds the value. Send record_id and field to POST .../searches to
     * start the search.
     */
    public String field() {
        return this.field;
    }

    /**
     * The kind of the value: one of the kinds the environment's enrichment setting names.
     */
    public SuggestedSearchKind kind() {
        return this.kind;
    }

    /**
     * The likelihood of the record that holds the value, from 0.01 to 0.99. Enrichment suggests a value
     * only from a record more likely than the setting's min_likelihood, the likeliest first.
     */
    public double likelihood() {
        return this.likelihood;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * The lookup record whose value enrichment would search. It is among the report's lookup records.
     */
    public SuggestedSearch withRecordId(@Nonnull String recordId) {
        this.recordId = Utils.checkNotNull(recordId, "recordId");
        return this;
    }

    /**
     * The field of that record that holds the value. Send record_id and field to POST .../searches to
     * start the search.
     */
    public SuggestedSearch withField(@Nonnull String field) {
        this.field = Utils.checkNotNull(field, "field");
        return this;
    }

    /**
     * The kind of the value: one of the kinds the environment's enrichment setting names.
     */
    public SuggestedSearch withKind(@Nonnull SuggestedSearchKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * The likelihood of the record that holds the value, from 0.01 to 0.99. Enrichment suggests a value
     * only from a record more likely than the setting's min_likelihood, the likeliest first.
     */
    public SuggestedSearch withLikelihood(double likelihood) {
        this.likelihood = likelihood;
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
        SuggestedSearch other = (SuggestedSearch) o;
        return Utils.enhancedDeepEquals(this.recordId, other.recordId)
                && Utils.enhancedDeepEquals(this.field, other.field)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.likelihood, other.likelihood);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(recordId, field, kind, likelihood);
    }

    @Override
    public String toString() {
        return Utils.toString(
                SuggestedSearch.class, "recordId", recordId, "field", field, "kind", kind, "likelihood", likelihood);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String recordId;

        private String field;

        private SuggestedSearchKind kind;

        private double likelihood;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * The lookup record whose value enrichment would search. It is among the report's lookup records.
         */
        public Builder recordId(@Nonnull String recordId) {
            this.recordId = Utils.checkNotNull(recordId, "recordId");
            return this;
        }

        /**
         * The field of that record that holds the value. Send record_id and field to POST .../searches to
         * start the search.
         */
        public Builder field(@Nonnull String field) {
            this.field = Utils.checkNotNull(field, "field");
            return this;
        }

        /**
         * The kind of the value: one of the kinds the environment's enrichment setting names.
         */
        public Builder kind(@Nonnull SuggestedSearchKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * The likelihood of the record that holds the value, from 0.01 to 0.99. Enrichment suggests a value
         * only from a record more likely than the setting's min_likelihood, the likeliest first.
         */
        public Builder likelihood(double likelihood) {
            this.likelihood = likelihood;
            return this;
        }

        public SuggestedSearch build() {
            return new SuggestedSearch(recordId, field, kind, likelihood);
        }
    }
}
