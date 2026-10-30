package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * IdentifierExpression
 *
 * <p>A bounded AND/OR expression over submitted identifiers. Required scope assessment means the entire
 * expression must match one retained assessment. Scope account permits each term to match a different
 * retained assessment of the same account and is accepted only by account search.
 *
 * <p>Exactly one of term, and or or is allowed at every node. Groups contain 2 to 8 operands, with at
 * most 3 nested group levels and 8 raw term occurrences over the entire expression. A root term is
 * allowed.
 *
 * <p>Duplicate terms with the same kind, match rule, namespace and normalized comparison are idempotent,
 * but count toward the raw limit. Match rules default to exact and preserve the existing identifier
 * contract. Only retained submitted identifiers can satisfy a term.
 *
 * <p>Unknown fields, unsupported scopes and malformed or oversized expressions return 400
 * invalid_request.
 */
public class IdentifierExpression {
    /**
     * assessment requires one submission to satisfy the complete expression. account permits witnesses
     * across retained submissions of one account and is supported only by POST /v1/accounts/search. No
     * default: select scope explicitly.
     */
    @JsonProperty("scope")
    private ScopeEnum scope;

    @JsonProperty("expression")
    private IdentifierExpressionNode expression;

    @JsonCreator
    public IdentifierExpression(
            @JsonProperty("scope") @Nonnull ScopeEnum scope,
            @JsonProperty("expression") @Nonnull IdentifierExpressionNode expression) {
        this.scope = Optional.ofNullable(scope).orElseThrow(() -> new IllegalArgumentException("scope cannot be null"));
        this.expression = Optional.ofNullable(expression)
                .orElseThrow(() -> new IllegalArgumentException("expression cannot be null"));
    }

    /**
     * assessment requires one submission to satisfy the complete expression. account permits witnesses
     * across retained submissions of one account and is supported only by POST /v1/accounts/search. No
     * default: select scope explicitly.
     */
    public ScopeEnum scope() {
        return this.scope;
    }

    public IdentifierExpressionNode expression() {
        return this.expression;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * assessment requires one submission to satisfy the complete expression. account permits witnesses
     * across retained submissions of one account and is supported only by POST /v1/accounts/search. No
     * default: select scope explicitly.
     */
    public IdentifierExpression withScope(@Nonnull ScopeEnum scope) {
        this.scope = Utils.checkNotNull(scope, "scope");
        return this;
    }

    public IdentifierExpression withExpression(@Nonnull IdentifierExpressionNode expression) {
        this.expression = Utils.checkNotNull(expression, "expression");
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
        IdentifierExpression other = (IdentifierExpression) o;
        return Utils.enhancedDeepEquals(this.scope, other.scope)
                && Utils.enhancedDeepEquals(this.expression, other.expression);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(scope, expression);
    }

    @Override
    public String toString() {
        return Utils.toString(IdentifierExpression.class, "scope", scope, "expression", expression);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ScopeEnum scope;

        private IdentifierExpressionNode expression;

        private Builder() {
            // force use of static builder() method
        }

        /**
         * assessment requires one submission to satisfy the complete expression. account permits witnesses
         * across retained submissions of one account and is supported only by POST /v1/accounts/search. No
         * default: select scope explicitly.
         */
        public Builder scope(@Nonnull ScopeEnum scope) {
            this.scope = Utils.checkNotNull(scope, "scope");
            return this;
        }

        public Builder expression(@Nonnull IdentifierExpressionNode expression) {
            this.expression = Utils.checkNotNull(expression, "expression");
            return this;
        }

        public IdentifierExpression build() {
            return new IdentifierExpression(scope, expression);
        }
    }
}
