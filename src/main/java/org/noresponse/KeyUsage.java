package org.noresponse;

import static org.noresponse.operations.Operations.RequestOperation;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.lang.String;
import org.noresponse.models.operations.RecordKeyUsageRequest;
import org.noresponse.models.operations.RecordKeyUsageRequestBuilder;
import org.noresponse.models.operations.RecordKeyUsageResponse;
import org.noresponse.models.shared.KeyUsageBatchRequest;
import org.noresponse.operations.RecordKeyUsage;
import org.noresponse.utils.Headers;
import org.noresponse.utils.Options;

public class KeyUsage {
    private static final Headers _headers = Headers.EMPTY;
    private final SDKConfiguration sdkConfiguration;
    private final AsyncKeyUsage asyncSDK;

    KeyUsage(SDKConfiguration sdkConfiguration) {
        this.sdkConfiguration = sdkConfiguration;
        this.asyncSDK = new AsyncKeyUsage(this, sdkConfiguration);
    }

    /**
     * Switches to the async SDK.
     *
     * @return The async SDK
     */
    public AsyncKeyUsage async() {
        return asyncSDK;
    }

    /**
     * Report requests made with API keys your signup gate issued.
     *
     * <p>Records requests made with API keys your signup gate issued to your accounts, one event per request:
     * the key's SHA-256 (the issued_credential.sha256 the account's assessment carried), the client IP,
     * the time and optionally the user agent, model and token counts. Most callers use the SDK's usage
     * buffer, which batches events and sends them in the background. Sampling is allowed: sample_rate
     * weights an event's request and token counts.
     *
     * <p>Requires the assess scope. Idempotency-Key is required and handled as on POST /v1/assessments: a
     * retry with the same key and body answers the same record with Idempotent-Replayed: true and counts
     * nothing again; a different body answers 409. A batch holds 1 to 500 events and at most 256 KiB.
     *
     * <p>No raw event is kept: usage is aggregated per key, and IPs, user agents and models are kept only as
     * keyed digests, with the IP's country, for 24 hours. When one key is used from 20 or more IP
     * addresses, or 3 or more countries, within 24 hours, every account whose assessment carried that key
     * gets the shared_api_key reason for 30 days.
     *
     * @return The call builder
     */
    public RecordKeyUsageRequestBuilder record() {
        return new RecordKeyUsageRequestBuilder(sdkConfiguration);
    }

    /**
     * Report requests made with API keys your signup gate issued.
     *
     * <p>Records requests made with API keys your signup gate issued to your accounts, one event per request:
     * the key's SHA-256 (the issued_credential.sha256 the account's assessment carried), the client IP,
     * the time and optionally the user agent, model and token counts. Most callers use the SDK's usage
     * buffer, which batches events and sends them in the background. Sampling is allowed: sample_rate
     * weights an event's request and token counts.
     *
     * <p>Requires the assess scope. Idempotency-Key is required and handled as on POST /v1/assessments: a
     * retry with the same key and body answers the same record with Idempotent-Replayed: true and counts
     * nothing again; a different body answers 409. A batch holds 1 to 500 events and at most 256 KiB.
     *
     * <p>No raw event is kept: usage is aggregated per key, and IPs, user agents and models are kept only as
     * keyed digests, with the IP's country, for 24 hours. When one key is used from 20 or more IP
     * addresses, or 3 or more countries, within 24 hours, every account whose assessment carried that key
     * gets the shared_api_key reason for 30 days.
     *
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the tenant principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/key_usage/batches body: facts about requests made with API keys your signup gate issued to your accounts, one event per request. Each event names the key by the SHA-256 that the assessment's issued_credential.sha256 carried, not the key itself. The SDKs buffer events and send them in the background. A batch holds 1 to 500 events and at most 256 KiB. Idempotency-Key is required and binds the body as on POST /v1/assessments, so a retried batch is counted once.
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordKeyUsageResponse record(@Nonnull String idempotencyKey, @Nonnull KeyUsageBatchRequest body) {
        return record(idempotencyKey, body, null);
    }

    /**
     * Report requests made with API keys your signup gate issued.
     *
     * <p>Records requests made with API keys your signup gate issued to your accounts, one event per request:
     * the key's SHA-256 (the issued_credential.sha256 the account's assessment carried), the client IP,
     * the time and optionally the user agent, model and token counts. Most callers use the SDK's usage
     * buffer, which batches events and sends them in the background. Sampling is allowed: sample_rate
     * weights an event's request and token counts.
     *
     * <p>Requires the assess scope. Idempotency-Key is required and handled as on POST /v1/assessments: a
     * retry with the same key and body answers the same record with Idempotent-Replayed: true and counts
     * nothing again; a different body answers 409. A batch holds 1 to 500 events and at most 256 KiB.
     *
     * <p>No raw event is kept: usage is aggregated per key, and IPs, user agents and models are kept only as
     * keyed digests, with the IP's country, for 24 hours. When one key is used from 20 or more IP
     * addresses, or 3 or more countries, within 24 hours, every account whose assessment carried that key
     * gets the shared_api_key reason for 30 days.
     *
     * @param idempotencyKey Opaque client key, 16 to 128 characters of [A-Za-z0-9._:-] (a UUID or ULID fits). Scoped to the tenant principal and bound to the canonical body for 24 hours.
     * @param body POST /v1/key_usage/batches body: facts about requests made with API keys your signup gate issued to your accounts, one event per request. Each event names the key by the SHA-256 that the assessment's issued_credential.sha256 carried, not the key itself. The SDKs buffer events and send them in the background. A batch holds 1 to 500 events and at most 256 KiB. Idempotency-Key is required and binds the body as on POST /v1/assessments, so a retried batch is counted once.
     * @param options additional options
     * @return The response from the API call
     * @throws RuntimeException subclass if the API call fails
     */
    public RecordKeyUsageResponse record(
            @Nonnull String idempotencyKey, @Nonnull KeyUsageBatchRequest body, @Nullable Options options) {
        RecordKeyUsageRequest request = new RecordKeyUsageRequest(idempotencyKey, body);
        RequestOperation<RecordKeyUsageRequest, RecordKeyUsageResponse> operation =
                new RecordKeyUsage.Sync(sdkConfiguration, options, _headers);
        return operation.handleResponse(operation.doRequest(request));
    }
}
