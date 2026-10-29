package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.noresponse.utils.LazySingletonValue;
import org.noresponse.utils.Utils;

/**
 * ListImpact
 *
 * <p>List override changes among the newest 5000 matching accounts. These counts describe override
 * inputs. Customer enforcement and eventual outcomes remain separate.
 *
 * <p>The response reports whether the matching population was truncated.
 */
public class ListImpact {

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("kind")
    private ListImpactKind kind;

    @JsonProperty("value")
    private String value;

    @JsonProperty("action")
    private Action action;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("remove")
    private String remove;

    @JsonProperty("matched_accounts")
    private long matchedAccounts;

    @JsonProperty("changed_overrides")
    private long changedOverrides;

    @JsonProperty("truncated")
    private boolean truncated;

    @JsonProperty("historical_population_known")
    private boolean historicalPopulationKnown;

    @JsonProperty("transitions")
    private List<ListImpactTransition> transitions;

    @JsonCreator
    public ListImpact(
            @JsonProperty("kind") @Nonnull ListImpactKind kind,
            @JsonProperty("value") @Nonnull String value,
            @JsonProperty("action") @Nonnull Action action,
            @JsonProperty("remove") @Nullable String remove,
            @JsonProperty("matched_accounts") long matchedAccounts,
            @JsonProperty("changed_overrides") long changedOverrides,
            @JsonProperty("truncated") boolean truncated,
            @JsonProperty("transitions") @Nonnull List<ListImpactTransition> transitions) {
        this.scope = Builder._SINGLETON_VALUE_Scope.value();
        this.kind = Optional.ofNullable(kind).orElseThrow(() -> new IllegalArgumentException("kind cannot be null"));
        this.value = Optional.ofNullable(value).orElseThrow(() -> new IllegalArgumentException("value cannot be null"));
        this.action =
                Optional.ofNullable(action).orElseThrow(() -> new IllegalArgumentException("action cannot be null"));
        this.remove = remove;
        this.matchedAccounts = matchedAccounts;
        this.changedOverrides = changedOverrides;
        this.truncated = truncated;
        this.historicalPopulationKnown = Builder._SINGLETON_VALUE_HistoricalPopulationKnown.value();
        this.transitions = Optional.ofNullable(transitions)
                .orElseThrow(() -> new IllegalArgumentException("transitions cannot be null"));
    }

    public ListImpact(
            @Nonnull ListImpactKind kind,
            @Nonnull String value,
            @Nonnull Action action,
            long matchedAccounts,
            long changedOverrides,
            boolean truncated,
            @Nonnull List<ListImpactTransition> transitions) {
        this(kind, value, action, null, matchedAccounts, changedOverrides, truncated, transitions);
    }

    public String scope() {
        return this.scope;
    }

    public ListImpactKind kind() {
        return this.kind;
    }

    public String value() {
        return this.value;
    }

    public Action action() {
        return this.action;
    }

    public Optional<String> remove() {
        return Optional.ofNullable(this.remove);
    }

    public long matchedAccounts() {
        return this.matchedAccounts;
    }

    public long changedOverrides() {
        return this.changedOverrides;
    }

    public boolean truncated() {
        return this.truncated;
    }

    public boolean historicalPopulationKnown() {
        return this.historicalPopulationKnown;
    }

    public List<ListImpactTransition> transitions() {
        return this.transitions;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ListImpact withKind(@Nonnull ListImpactKind kind) {
        this.kind = Utils.checkNotNull(kind, "kind");
        return this;
    }

    public ListImpact withValue(@Nonnull String value) {
        this.value = Utils.checkNotNull(value, "value");
        return this;
    }

    public ListImpact withAction(@Nonnull Action action) {
        this.action = Utils.checkNotNull(action, "action");
        return this;
    }

    public ListImpact withRemove(@Nullable String remove) {
        this.remove = remove;
        return this;
    }

    public ListImpact withMatchedAccounts(long matchedAccounts) {
        this.matchedAccounts = matchedAccounts;
        return this;
    }

    public ListImpact withChangedOverrides(long changedOverrides) {
        this.changedOverrides = changedOverrides;
        return this;
    }

    public ListImpact withTruncated(boolean truncated) {
        this.truncated = truncated;
        return this;
    }

    public ListImpact withTransitions(@Nonnull List<ListImpactTransition> transitions) {
        this.transitions = Utils.checkNotNull(transitions, "transitions");
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
        ListImpact other = (ListImpact) o;
        return Utils.enhancedDeepEquals(this.scope, other.scope)
                && Utils.enhancedDeepEquals(this.kind, other.kind)
                && Utils.enhancedDeepEquals(this.value, other.value)
                && Utils.enhancedDeepEquals(this.action, other.action)
                && Utils.enhancedDeepEquals(this.remove, other.remove)
                && Utils.enhancedDeepEquals(this.matchedAccounts, other.matchedAccounts)
                && Utils.enhancedDeepEquals(this.changedOverrides, other.changedOverrides)
                && Utils.enhancedDeepEquals(this.truncated, other.truncated)
                && Utils.enhancedDeepEquals(this.historicalPopulationKnown, other.historicalPopulationKnown)
                && Utils.enhancedDeepEquals(this.transitions, other.transitions);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(
                scope,
                kind,
                value,
                action,
                remove,
                matchedAccounts,
                changedOverrides,
                truncated,
                historicalPopulationKnown,
                transitions);
    }

    @Override
    public String toString() {
        return Utils.toString(
                ListImpact.class,
                "scope",
                scope,
                "kind",
                kind,
                "value",
                value,
                "action",
                action,
                "remove",
                remove,
                "matchedAccounts",
                matchedAccounts,
                "changedOverrides",
                changedOverrides,
                "truncated",
                truncated,
                "historicalPopulationKnown",
                historicalPopulationKnown,
                "transitions",
                transitions);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private ListImpactKind kind;

        private String value;

        private Action action;

        private String remove;

        private long matchedAccounts;

        private long changedOverrides;

        private boolean truncated;

        private List<ListImpactTransition> transitions;

        private Builder() {
            // force use of static builder() method
        }

        public Builder kind(@Nonnull ListImpactKind kind) {
            this.kind = Utils.checkNotNull(kind, "kind");
            return this;
        }

        public Builder value(@Nonnull String value) {
            this.value = Utils.checkNotNull(value, "value");
            return this;
        }

        public Builder action(@Nonnull Action action) {
            this.action = Utils.checkNotNull(action, "action");
            return this;
        }

        public Builder remove(@Nullable String remove) {
            this.remove = remove;
            return this;
        }

        public Builder matchedAccounts(long matchedAccounts) {
            this.matchedAccounts = matchedAccounts;
            return this;
        }

        public Builder changedOverrides(long changedOverrides) {
            this.changedOverrides = changedOverrides;
            return this;
        }

        public Builder truncated(boolean truncated) {
            this.truncated = truncated;
            return this;
        }

        public Builder transitions(@Nonnull List<ListImpactTransition> transitions) {
            this.transitions = Utils.checkNotNull(transitions, "transitions");
            return this;
        }

        public ListImpact build() {
            return new ListImpact(
                    kind, value, action, remove, matchedAccounts, changedOverrides, truncated, transitions);
        }

        private static final LazySingletonValue<String> _SINGLETON_VALUE_Scope =
                new LazySingletonValue<>("scope", "\"current_list_override_inputs\"", new TypeReference<String>() {});

        private static final LazySingletonValue<Boolean> _SINGLETON_VALUE_HistoricalPopulationKnown =
                new LazySingletonValue<>("historical_population_known", "false", new TypeReference<Boolean>() {});
    }
}
