# ScreeningCoverageSources

How many sources this search asked and how many answered, across the lookup stage (sources asked whether an account exists for an identifier) and the platform stage (profiles read on each platform). A source that answered may have found nothing; one that did not answer failed or ran out of time.


## Fields

| Field                                             | Setter Type                                       | Getter Type                                       | Required                                          | Description                                       |
| ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------- |
| `asked`                                           | *long*                                            | *long*                                            | :heavy_check_mark:                                | Lookup sources plus platforms asked.              |
| `answered`                                        | *long*                                            | *long*                                            | :heavy_check_mark:                                | Lookup sources plus platforms that answered.      |
| `lookups`                                         | [SourceCount](../../models/shared/SourceCount.md) | [SourceCount](../../models/shared/SourceCount.md) | :heavy_check_mark:                                | N/A                                               |
| `platforms`                                       | [SourceCount](../../models/shared/SourceCount.md) | [SourceCount](../../models/shared/SourceCount.md) | :heavy_check_mark:                                | N/A                                               |