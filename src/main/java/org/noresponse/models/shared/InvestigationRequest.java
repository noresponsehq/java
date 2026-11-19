package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * InvestigationRequest
 *
 * <p>Runs a new investigation against one retained workspace person.
 */
public class InvestigationRequest {

    @JsonProperty("depth")
    private ScreeningDepth depth;

    @JsonProperty("person_id")
    private String personId;

    /**
     * A change to enrichment. A field left out keeps the value in force; max_spend or deadline null
     * removes the bound.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("enrichment")
    private EnrichmentChange enrichment;

    @JsonCreator
    public InvestigationRequest(
            @JsonProperty("depth") @Nonnull ScreeningDepth depth,
            @JsonProperty("person_id") @Nonnull String personId,
            @JsonProperty("enrichment") @Nullable EnrichmentChange enrichment) {
        this.depth = Optional.ofNullable(depth).orElseThrow(() -> new IllegalArgumentException("depth cannot be null"));
        this.personId = Optional.ofNullable(personId)
                .orElseThrow(() -> new IllegalArgumentException("personId cannot be null"));
        this.enrichment = enrichment;
    }

    public InvestigationRequest(@Nonnull ScreeningDepth depth, @Nonnull String personId) {
        this(depth, personId, null);
    }

    public ScreeningDepth depth() {
        return this.depth;
    }

    public String personId() {
        return this.personId;
    }

    /**
     * A change to enrichment. A field left out keeps the value in force; max_spend or deadline null
     * removes the bound.
     */
    public Optional<EnrichmentChange> enrichment() {
        return Optional.ofNullable(this.enrichment);
    }

    public static Builder builder() {
        return new Builder();
    }

    public InvestigationRequest withDepth(@Nonnull ScreeningDepth depth) {
        this.depth = Utils.checkNotNull(depth, "depth");
        return this;
    }

    public InvestigationRequest withPersonId(@Nonnull String personId) {
        this.personId = Utils.checkNotNull(personId, "personId");
        return this;
    }

    /**
     * A change to enrichment. A field left out keeps the value in force; max_spend or deadline null
     * removes the bound.
     */
    public InvestigationRequest withEnrichment(@Nullable EnrichmentChange enrichment) {
        this.enrichment = enrichment;
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
        InvestigationRequest other = (InvestigationRequest) o;
        return Utils.enhancedDeepEquals(this.depth, other.depth)
                && Utils.enhancedDeepEquals(this.personId, other.personId)
                && Utils.enhancedDeepEquals(this.enrichment, other.enrichment);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(depth, personId, enrichment);
    }

    @Override
    public String toString() {
        return Utils.toString(
                InvestigationRequest.class, "depth", depth, "personId", personId, "enrichment", enrichment);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningDepth depth;

        private String personId;

        private EnrichmentChange enrichment;

        private Builder() {
            // force use of static builder() method
        }

        public Builder depth(@Nonnull ScreeningDepth depth) {
            this.depth = Utils.checkNotNull(depth, "depth");
            return this;
        }

        public Builder personId(@Nonnull String personId) {
            this.personId = Utils.checkNotNull(personId, "personId");
            return this;
        }

        /**
         * A change to enrichment. A field left out keeps the value in force; max_spend or deadline null
         * removes the bound.
         */
        public Builder enrichment(@Nullable EnrichmentChange enrichment) {
            this.enrichment = enrichment;
            return this;
        }

        public InvestigationRequest build() {
            return new InvestigationRequest(depth, personId, enrichment);
        }
    }
}
