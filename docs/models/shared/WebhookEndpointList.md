# WebhookEndpointList

GET /v1/webhooks answer: every endpoint of the calling workspace, in every state, ordered by endpoint_id. A workspace holds at most 5, so there is no page.


## Fields

| Field                                                            | Setter Type                                                      | Getter Type                                                      | Required                                                         | Description                                                      |
| ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- |
| `items`                                                          | List\<[WebhookEndpoint](../../models/shared/WebhookEndpoint.md)> | List\<[WebhookEndpoint](../../models/shared/WebhookEndpoint.md)> | :heavy_check_mark:                                               | N/A                                                              |