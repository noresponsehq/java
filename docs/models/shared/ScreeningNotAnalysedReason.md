# ScreeningNotAnalysedReason

Why an item read was not analysed. video: a video, whose caption alone is read. image_unavailable: the image could not be fetched from the platform. image_unsupported: not a JPEG, PNG, GIF or WebP image, or too large to send. image_limit: past the most images one person's analysis reads. refused: the model declined to read it. unfinished: the model cut its batch short or answered outside the schema, or the request was rejected. unverified: a finding cited it with words it does not contain or an image it did not describe. unspecified: the analyser gave no reason.

## Example Usage

```java
import org.noresponse.models.shared.ScreeningNotAnalysedReason;

ScreeningNotAnalysedReason value = ScreeningNotAnalysedReason.VIDEO;

// Open enum: use .of() to create instances from custom string values
ScreeningNotAnalysedReason custom = ScreeningNotAnalysedReason.of("custom_value");
```


## Values

| Name                | Value               |
| ------------------- | ------------------- |
| `VIDEO`             | video               |
| `IMAGE_UNAVAILABLE` | image_unavailable   |
| `IMAGE_UNSUPPORTED` | image_unsupported   |
| `IMAGE_LIMIT`       | image_limit         |
| `REFUSED`           | refused             |
| `UNFINISHED`        | unfinished          |
| `UNVERIFIED`        | unverified          |
| `UNSPECIFIED`       | unspecified         |