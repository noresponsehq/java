<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationError;
import org.noresponse.models.errors.BadRequestError;
import org.noresponse.models.errors.ConflictError;
import org.noresponse.models.errors.NotFoundError;
import org.noresponse.models.errors.PermissionDeniedError;
import org.noresponse.models.errors.RateLimitError;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("<value>")
                .body(AssessmentRequest.builder()
                        .accountId("acct_7f3a9c")
                        .identifiers(AssessmentRequestIdentifiers.builder()
                                .email("new.user@example.com")
                                .build())
                        .build())
                .call();

        if (res.assessment().isPresent()) {
            System.out.println(res.assessment().get());
        }
    }
}

```
<!-- End SDK Example Usage [usage] -->