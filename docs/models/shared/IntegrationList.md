# IntegrationList

GET /v1/integrations: every integration of the tenant, oldest first, at most 10. No secret is ever listed.


## Fields

| Field                                                    | Setter Type                                              | Getter Type                                              | Required                                                 | Description                                              |
| -------------------------------------------------------- | -------------------------------------------------------- | -------------------------------------------------------- | -------------------------------------------------------- | -------------------------------------------------------- |
| `items`                                                  | List\<[Integration](../../models/shared/Integration.md)> | List\<[Integration](../../models/shared/Integration.md)> | :heavy_check_mark:                                       | N/A                                                      |