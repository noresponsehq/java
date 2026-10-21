# AssessmentRequestPolicyContext


## Fields

| Field                                                                   | Setter Type                                                             | Getter Type                                                             | Required                                                                | Description                                                             |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `flow`                                                                  | @Nullable [Flow](../../models/shared/Flow.md)                           | Optional\<[Flow](../../models/shared/Flow.md)>                          | :heavy_minus_sign:                                                      | The provider flow the assessment gates. Absent means signup.            |
| `region`                                                                | @Nullable *String*                                                      | Optional\<*String*>                                                     | :heavy_minus_sign:                                                      | ISO 3166-1 alpha-2 region of the signup, as the provider determines it. |