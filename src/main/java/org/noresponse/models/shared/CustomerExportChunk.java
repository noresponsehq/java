package org.noresponse.models.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;
import org.noresponse.utils.Utils;

/**
 * CustomerExportChunk
 *
 * <p>Download one authorized export chunk.
 */
public class CustomerExportChunk {

    @JsonProperty("export_id")
    private String exportId;

    @JsonProperty("index")
    private long index;

    @JsonProperty("generation")
    private long generation;

    @JsonProperty("sha256")
    private String sha256;

    /**
     * Base64-encoded UTF-8 JSON Lines, at most 256 KiB decoded. Each line is a typed customer export
     * record. Decode and concatenate chunks in index order within the same generation.
     */
    @JsonProperty("data")
    private String data;

    @JsonInclude(Include.NON_ABSENT)
    @JsonProperty("next_chunk")
    private Long nextChunk;

    @JsonCreator
    public CustomerExportChunk(
            @JsonProperty("export_id") @Nonnull String exportId,
            @JsonProperty("index") long index,
            @JsonProperty("generation") long generation,
            @JsonProperty("sha256") @Nonnull String sha256,
            @JsonProperty("data") @Nonnull String data,
            @JsonProperty("next_chunk") @Nullable Long nextChunk) {
        this.exportId = Optional.ofNullable(exportId)
                .orElseThrow(() -> new IllegalArgumentException("exportId cannot be null"));
        this.index = index;
        this.generation = generation;
        this.sha256 =
                Optional.ofNullable(sha256).orElseThrow(() -> new IllegalArgumentException("sha256 cannot be null"));
        this.data = Optional.ofNullable(data).orElseThrow(() -> new IllegalArgumentException("data cannot be null"));
        this.nextChunk = nextChunk;
    }

    public CustomerExportChunk(
            @Nonnull String exportId, long index, long generation, @Nonnull String sha256, @Nonnull String data) {
        this(exportId, index, generation, sha256, data, null);
    }

    public String exportId() {
        return this.exportId;
    }

    public long index() {
        return this.index;
    }

    public long generation() {
        return this.generation;
    }

    public String sha256() {
        return this.sha256;
    }

    /**
     * Base64-encoded UTF-8 JSON Lines, at most 256 KiB decoded. Each line is a typed customer export
     * record. Decode and concatenate chunks in index order within the same generation.
     */
    public String data() {
        return this.data;
    }

    public Optional<Long> nextChunk() {
        return Optional.ofNullable(this.nextChunk);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerExportChunk withExportId(@Nonnull String exportId) {
        this.exportId = Utils.checkNotNull(exportId, "exportId");
        return this;
    }

    public CustomerExportChunk withIndex(long index) {
        this.index = index;
        return this;
    }

    public CustomerExportChunk withGeneration(long generation) {
        this.generation = generation;
        return this;
    }

    public CustomerExportChunk withSha256(@Nonnull String sha256) {
        this.sha256 = Utils.checkNotNull(sha256, "sha256");
        return this;
    }

    /**
     * Base64-encoded UTF-8 JSON Lines, at most 256 KiB decoded. Each line is a typed customer export
     * record. Decode and concatenate chunks in index order within the same generation.
     */
    public CustomerExportChunk withData(@Nonnull String data) {
        this.data = Utils.checkNotNull(data, "data");
        return this;
    }

    public CustomerExportChunk withNextChunk(@Nullable Long nextChunk) {
        this.nextChunk = nextChunk;
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
        CustomerExportChunk other = (CustomerExportChunk) o;
        return Utils.enhancedDeepEquals(this.exportId, other.exportId)
                && Utils.enhancedDeepEquals(this.index, other.index)
                && Utils.enhancedDeepEquals(this.generation, other.generation)
                && Utils.enhancedDeepEquals(this.sha256, other.sha256)
                && Utils.enhancedDeepEquals(this.data, other.data)
                && Utils.enhancedDeepEquals(this.nextChunk, other.nextChunk);
    }

    @Override
    public int hashCode() {
        return Utils.enhancedHash(exportId, index, generation, sha256, data, nextChunk);
    }

    @Override
    public String toString() {
        return Utils.toString(
                CustomerExportChunk.class,
                "exportId",
                exportId,
                "index",
                index,
                "generation",
                generation,
                "sha256",
                sha256,
                "data",
                data,
                "nextChunk",
                nextChunk);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static final class Builder {

        private String exportId;

        private long index;

        private long generation;

        private String sha256;

        private String data;

        private Long nextChunk;

        private Builder() {
            // force use of static builder() method
        }

        public Builder exportId(@Nonnull String exportId) {
            this.exportId = Utils.checkNotNull(exportId, "exportId");
            return this;
        }

        public Builder index(long index) {
            this.index = index;
            return this;
        }

        public Builder generation(long generation) {
            this.generation = generation;
            return this;
        }

        public Builder sha256(@Nonnull String sha256) {
            this.sha256 = Utils.checkNotNull(sha256, "sha256");
            return this;
        }

        /**
         * Base64-encoded UTF-8 JSON Lines, at most 256 KiB decoded. Each line is a typed customer export
         * record. Decode and concatenate chunks in index order within the same generation.
         */
        public Builder data(@Nonnull String data) {
            this.data = Utils.checkNotNull(data, "data");
            return this;
        }

        public Builder nextChunk(@Nullable Long nextChunk) {
            this.nextChunk = nextChunk;
            return this;
        }

        public CustomerExportChunk build() {
            return new CustomerExportChunk(exportId, index, generation, sha256, data, nextChunk);
        }
    }
}
