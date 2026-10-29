# OperationalAggregateQueue

Current retained delivery queue, independent of the requested attempt window and status filter. Endpoint filter applies. Does not prove customer enforcement execution.


## Fields

| Field                | Setter Type          | Getter Type          | Required             | Description          | Example              |
| -------------------- | -------------------- | -------------------- | -------------------- | -------------------- | -------------------- |
| `pending`            | *long*               | *long*               | :heavy_check_mark:   | N/A                  |                      |
| `leased`             | *long*               | *long*               | :heavy_check_mark:   | N/A                  |                      |
| `deadRetained`       | *long*               | *long*               | :heavy_check_mark:   | N/A                  |                      |
| `oldestPendingAt`    | @Nullable *String*   | Optional\<*String*>  | :heavy_minus_sign:   | N/A                  | 2026-01-01T00:00:00Z |