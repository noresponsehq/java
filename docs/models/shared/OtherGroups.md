# OtherGroups

Where else in the environment the person appears, read live from those groups: deleting one removes it here.


## Fields

| Field                                                                           | Setter Type                                                                     | Getter Type                                                                     | Required                                                                        | Description                                                                     |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `total`                                                                         | *long*                                                                          | *long*                                                                          | :heavy_check_mark:                                                              | How many people of other groups match, of which items lists the 20 most recent. |
| `items`                                                                         | List\<[OtherGroup](../../models/shared/OtherGroup.md)>                          | List\<[OtherGroup](../../models/shared/OtherGroup.md)>                          | :heavy_check_mark:                                                              | The 20 people of the most recent other groups, newest group first.              |