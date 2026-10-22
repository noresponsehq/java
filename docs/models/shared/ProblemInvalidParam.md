# ProblemInvalidParam


## Fields

| Field                                                | Setter Type                                          | Getter Type                                          | Required                                             | Description                                          | Example                                              |
| ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- |
| `field`                                              | *String*                                             | *String*                                             | :heavy_check_mark:                                   | N/A                                                  | identifiers                                          |
| `reason`                                             | *String*                                             | *String*                                             | :heavy_check_mark:                                   | N/A                                                  | at least one of email, username or phone is required |
| `example`                                            | @Nullable *String*                                   | Optional\<*String*>                                  | :heavy_minus_sign:                                   | N/A                                                  | new.user@example.com                                 |