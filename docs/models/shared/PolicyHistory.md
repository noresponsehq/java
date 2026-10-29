# PolicyHistory

Immutable settings snapshots, newest first. history_complete is false when earlier versions predate capture. A restored version is published with a fresh version number. History is removed with the tenant.


## Fields

| Field                                                                | Setter Type                                                          | Getter Type                                                          | Required                                                             | Description                                                          |
| -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- |
| `items`                                                              | List\<[PolicyHistoryItem](../../models/shared/PolicyHistoryItem.md)> | List\<[PolicyHistoryItem](../../models/shared/PolicyHistoryItem.md)> | :heavy_check_mark:                                                   | N/A                                                                  |
| `hasMore`                                                            | *boolean*                                                            | *boolean*                                                            | :heavy_check_mark:                                                   | N/A                                                                  |
| `nextBeforeVersion`                                                  | @Nullable *long*                                                     | Optional\<*long*>                                                    | :heavy_minus_sign:                                                   | N/A                                                                  |
| `historyComplete`                                                    | *boolean*                                                            | *boolean*                                                            | :heavy_check_mark:                                                   | N/A                                                                  |