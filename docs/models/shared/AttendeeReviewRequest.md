# AttendeeReviewRequest

Records a decision on an attendee.


## Fields

| Field                                                       | Setter Type                                                 | Getter Type                                                 | Required                                                    | Description                                                 | Example                                                     |
| ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- | ----------------------------------------------------------- |
| `decision`                                                  | [AttendeeDecision](../../models/shared/AttendeeDecision.md) | [AttendeeDecision](../../models/shared/AttendeeDecision.md) | :heavy_check_mark:                                          | N/A                                                         |                                                             |
| `note`                                                      | @Nullable *String*                                          | Optional\<*String*>                                         | :heavy_minus_sign:                                          | Why, for the venue team and later reviewers.                | Known to the venue team.                                    |