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
 * ScreeningMatchEvidence
 *
 * <p>One identifier that ties a profile to the person.
 */
public class ScreeningMatchEvidence {
    /**
     * What matched. email, phone, supplied_handle and source_fact are hard identifiers; company, city and
     * school are soft. supplied_handle is a handle on the person row; source_fact is an account a profile
     * found by the person's lookups lists.
     */
    @JsonProperty("kind")
    private ScreeningMatchEvidenceKind kind;

    /**
     * Where the match was seen, such as the profile field that carried it.
     */
    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("detail")
    private String detail;

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    @JsonProperty("resolver")
    private String resolver;

    @JsonCreator
    public ScreeningMatchEvidence(
            @JsonProperty("kind") @Nonnull ScreeningMatchEvidenceKind kind,
            @JsonProperty("detail") @Nullable String detail,
            @JsonProperty("resolver") @Nonnull String resolver) {
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.detail = detail;
        this.resolver = Optional.ofNullable(resolver)
                .orElseThrow(() -> new IllegalArgumentException("resolver cannot be null"));
    }

    public ScreeningMatchEvidence(@Nonnull ScreeningMatchEvidenceKind kind, @Nonnull String resolver) {
        this(kind, null, resolver);
    }

    /**
     * What matched. email, phone, supplied_handle and source_fact are hard identifiers; company, city and
     * school are soft. supplied_handle is a handle on the person row; source_fact is an account a profile
     * found by the person's lookups lists.
     */
    public ScreeningMatchEvidenceKind kind() {
        return this.kind;
    }

    /**
     * Where the match was seen, such as the profile field that carried it.
     */
    public Optional<String> detail() {
        return Optional.ofNullable(this.detail);
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public String resolver() {
        return this.resolver;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * What matched. email, phone, supplied_handle and source_fact are hard identifiers; company, city and
     * school are soft. supplied_handle is a handle on the person row; source_fact is an account a profile
     * found by the person's lookups lists.
     */
    public ScreeningMatchEvidence withKind(@Nonnull ScreeningMatchEvidenceKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    /**
     * Where the match was seen, such as the profile field that carried it.
     */
    public ScreeningMatchEvidence withDetail(@Nullable String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * The resolver, collector or analyser that produced a record, by its catalogue name.
     */
    public ScreeningMatchEvidence withResolver(@Nonnull String resolver) {
        this.resolver = Utils.checkNotNull(resolver, "resolver");
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
        ScreeningMatchEvidence other = (ScreeningMatchEvidence) o;
        return Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.detail, other.detail)
                && Utils.enhancedDeepEquals(this.resolver, other.resolver);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(kind, detail, resolver);
    }

    @Override
    public String toString() {
        return Utils.toString(ScreeningMatchEvidence.class, "kind", kind, "detail", detail, "resolver", resolver);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScreeningMatchEvidenceKind kind;

        private String detail;

        private String resolver;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * What matched. email, phone, supplied_handle and source_fact are hard identifiers; company, city and
         * school are soft. supplied_handle is a handle on the person row; source_fact is an account a profile
         * found by the person's lookups lists.
         */
        public Builder kind(@Nonnull ScreeningMatchEvidenceKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        /**
         * Where the match was seen, such as the profile field that carried it.
         */
        public Builder detail(@Nullable String detail) {
            this.detail = detail;
            return this;
        }

        /**
         * The resolver, collector or analyser that produced a record, by its catalogue name.
         */
        public Builder resolver(@Nonnull String resolver) {
            this.resolver = Utils.checkNotNull(resolver, "resolver");
            return this;
        }

        public ScreeningMatchEvidence build() {
            return new ScreeningMatchEvidence(kind, detail, resolver);
        }
    }
}
