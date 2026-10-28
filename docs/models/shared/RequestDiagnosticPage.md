# RequestDiagnosticPage

Authenticated attempts in a bounded interval, newest first. Keep the same filters when paging.


## Fields

| Field                                                                      | Setter Type                                                                | Getter Type                                                                | Required                                                                   | Description                                                                | Example                                                                    |
| -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| `items`                                                                    | List\<[RequestDiagnostic](../../models/shared/RequestDiagnostic.md)>       | List\<[RequestDiagnostic](../../models/shared/RequestDiagnostic.md)>       | :heavy_check_mark:                                                         | N/A                                                                        |                                                                            |
| `nextCursor`                                                               | @Nullable *String*                                                         | Optional\<*String*>                                                        | :heavy_minus_sign:                                                         | N/A                                                                        | eyJ0IjoxNzY3MjI1NjAwMDAwMDAwLCJpIjoiYXNtX2V4YW1wbGUiLCJlIjoxNzY3MjI5MjAwfQ |