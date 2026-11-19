package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

public class StageTime {

    @JsonProperty("stage")
    private Stage stage;

    @JsonProperty("ms")
    private long ms;

    @JsonCreator
    public StageTime(@JsonProperty("stage") @Nonnull Stage stage, @JsonProperty("ms") long ms) {
        this.stage = Optional.ofNullable(stage).orElseThrow(() -> new IllegalArgumentException("stage cannot be null"));
        this.ms = ms;
    }

    public Stage stage() {
        return this.stage;
    }

    public long ms() {
        return this.ms;
    }

    public static Builder builder() {
        return new Builder();
    }

    public StageTime withStage(@Nonnull Stage stage) {
        this.stage = Utils.checkNotNull(stage, "stage");
        return this;
    }

    public StageTime withMs(long ms) {
        this.ms = ms;
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
        StageTime other = (StageTime) o;
        return Utils.enhancedDeepEquals(this.stage, other.stage) && Utils.enhancedDeepEquals(this.ms, other.ms);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(stage, ms);
    }

    @Override
    public String toString() {
        return Utils.toString(StageTime.class, "stage", stage, "ms", ms);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private Stage stage;

        private long ms;

        private Builder() {
            // force use of static builder() method
        }

        public Builder stage(@Nonnull Stage stage) {
            this.stage = Utils.checkNotNull(stage, "stage");
            return this;
        }

        public Builder ms(long ms) {
            this.ms = ms;
            return this;
        }

        public StageTime build() {
            return new StageTime(stage, ms);
        }
    }
}
