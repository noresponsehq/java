# OtherGroups

Where else in the environment the person appears, read live from those groups: deleting one removes it here.


## Fields

| Field                                                                               | Setter Type                                                                         | Getter Type                                                                         | Required                                                                            | Description                                                                         |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `total`                                                                             | *long*                                                                              | *long*                                                                              | :heavy_check_mark:                                                                  | How many other groups include this person, of which items lists the 20 most recent. |
| `items`                                                                             | List\<[OtherGroup](../../models/shared/OtherGroup.md)>                              | List\<[OtherGroup](../../models/shared/OtherGroup.md)>                              | :heavy_check_mark:                                                                  | This person's memberships in the 20 most recent other groups, newest group first.   |