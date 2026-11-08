# AttendeeReport

Everything known about one attendee.


## Fields

| Field                                                          | Setter Type                                                    | Getter Type                                                    | Required                                                       | Description                                                    |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| `attendee`                                                     | [Attendee](../../models/shared/Attendee.md)                    | [Attendee](../../models/shared/Attendee.md)                    | :heavy_check_mark:                                             | An attendee and where their screening and review stand.        |
| `profiles`                                                     | List\<[Profile](../../models/shared/Profile.md)>               | List\<[Profile](../../models/shared/Profile.md)>               | :heavy_check_mark:                                             | The accounts the screening matched, with how each was matched. |
| `findings`                                                     | List\<[Finding](../../models/shared/Finding.md)>               | List\<[Finding](../../models/shared/Finding.md)>               | :heavy_check_mark:                                             | What the screening flagged, with its evidence.                 |
| `reviews`                                                      | List\<[AttendeeReview](../../models/shared/AttendeeReview.md)> | List\<[AttendeeReview](../../models/shared/AttendeeReview.md)> | :heavy_check_mark:                                             | Every recorded decision, oldest first.                         |