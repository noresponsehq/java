# WebhookEndpointState

unverified until the endpoint echoes an endpoint.verification challenge (send one with POST /v1/webhooks/{endpoint_id}/test); verified receives assessment.updated events; disabled after 72 hours of continuous delivery failure, and verified again by the same challenge.

## Example Usage

```java
import org.noresponse.models.shared.WebhookEndpointState;

WebhookEndpointState value = WebhookEndpointState.UNVERIFIED;

// Open enum: use .of() to create instances from custom string values
WebhookEndpointState custom = WebhookEndpointState.of("custom_value");
```


## Values

| Name         | Value        |
| ------------ | ------------ |
| `UNVERIFIED` | unverified   |
| `VERIFIED`   | verified     |
| `DISABLED`   | disabled     |