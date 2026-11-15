# PersonBatchRequest

Adds 1 to 500 workspace people to the group. Reuses an explicit person_id or external_id. Matching email or phone values only suggest people; automatic merging is disabled.


## Fields

| Field                                                          | Setter Type                                                    | Getter Type                                                    | Required                                                       | Description                                                    |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| `people`                                                       | List\<[PersonIdentity](../../models/shared/PersonIdentity.md)> | List\<[PersonIdentity](../../models/shared/PersonIdentity.md)> | :heavy_check_mark:                                             | N/A                                                            |