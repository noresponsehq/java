# OtherEvents

Where else in the environment the attendee appears, read live from those events: deleting one removes it here.


## Fields

| Field                                                                              | Setter Type                                                                        | Getter Type                                                                        | Required                                                                           | Description                                                                        |
| ---------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| `total`                                                                            | *long*                                                                             | *long*                                                                             | :heavy_check_mark:                                                                 | How many attendees of other events match, of which items lists the 20 most recent. |
| `items`                                                                            | List\<[OtherEvent](../../models/shared/OtherEvent.md)>                             | List\<[OtherEvent](../../models/shared/OtherEvent.md)>                             | :heavy_check_mark:                                                                 | The 20 attendees of the most recent other events, newest event first.              |