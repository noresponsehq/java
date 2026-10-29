# ListBulkRequest

At most 100 additions and removals combined. One transaction applies all changes or none. Idempotency-Key prevents duplicate changes for 24 hours; a different body under the same key returns 409. Matching reads see the new list immediately, while bounded durable jobs propagate account risk notifications.


## Fields

| Field                                                                         | Setter Type                                                                   | Getter Type                                                                   | Required                                                                      | Description                                                                   |
| ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| `add`                                                                         | @Nullable List\<[ListEntryRequest](../../models/shared/ListEntryRequest.md)>  | Optional\<List\<[ListEntryRequest](../../models/shared/ListEntryRequest.md)>> | :heavy_minus_sign:                                                            | N/A                                                                           |
| `remove`                                                                      | @Nullable List\<*String*>                                                     | Optional\<List\<*String*>>                                                    | :heavy_minus_sign:                                                            | N/A                                                                           |