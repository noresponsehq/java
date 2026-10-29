# PolicyRestoreRequest

Publish a captured configuration as a new version. Version checks and publication are atomic. Longer retention does not revive expired records, and restored policy does not undo customer enforcement.


## Fields

| Field                                               | Setter Type                                         | Getter Type                                         | Required                                            | Description                                         | Example                                             |
| --------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------- | --------------------------------------------------- |
| `version`                                           | *long*                                              | *long*                                              | :heavy_check_mark:                                  | N/A                                                 |                                                     |
| `expectedPolicyVersion`                             | *long*                                              | *long*                                              | :heavy_check_mark:                                  | N/A                                                 |                                                     |
| `changeReason`                                      | *String*                                            | *String*                                            | :heavy_check_mark:                                  | N/A                                                 | Adjust thresholds after reviewing retained evidence |