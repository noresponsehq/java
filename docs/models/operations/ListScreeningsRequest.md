# ListScreeningsRequest


## Fields

| Field                                | Setter Type                          | Getter Type                          | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `groupId`                            | *String*                             | *String*                             | :heavy_check_mark:                   | N/A                                  | grp_01JA2Z8K6Q4V7W9XBCDEFGHJKM       |
| `limit`                              | @Nullable *long*                     | Optional\<*long*>                    | :heavy_minus_sign:                   | At most this many items.             |                                      |
| `before`                             | @Nullable *String*                   | Optional\<*String*>                  | :heavy_minus_sign:                   | Read screenings older than this one. |                                      |