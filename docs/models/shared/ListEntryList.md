# ListEntryList

GET /v1/workspaces/{workspace_id}/lists/{environment}: every entry of the environment's list, newest first. An environment holds at most 10,000 entries.


## Fields

| Field                                                | Setter Type                                          | Getter Type                                          | Required                                             | Description                                          |
| ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- |
| `items`                                              | List\<[ListEntry](../../models/shared/ListEntry.md)> | List\<[ListEntry](../../models/shared/ListEntry.md)> | :heavy_check_mark:                                   | N/A                                                  |