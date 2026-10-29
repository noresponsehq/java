# ReviewViewRequest


## Fields

| Field                                   | Setter Type                             | Getter Type                             | Required                                | Description                             | Example                                 |
| --------------------------------------- | --------------------------------------- | --------------------------------------- | --------------------------------------- | --------------------------------------- | --------------------------------------- |
| `name`                                  | *String*                                | *String*                                | :heavy_check_mark:                      | N/A                                     | High priority reviews                   |
| `shared`                                | *boolean*                               | *boolean*                               | :heavy_check_mark:                      | N/A                                     |                                         |
| `expectedVersion`                       | @Nullable *long*                        | Optional\<*long*>                       | :heavy_minus_sign:                      | N/A                                     |                                         |
| `filter`                                | [Filter](../../models/shared/Filter.md) | [Filter](../../models/shared/Filter.md) | :heavy_check_mark:                      | N/A                                     |                                         |