<!-- Start SDK Example Usage [usage] -->
```java
package hello.world;

import java.lang.Exception;
import org.noresponse.NoResponse;
import org.noresponse.models.errors.AuthenticationException;
import org.noresponse.models.errors.BadRequestException;
import org.noresponse.models.errors.ConflictException;
import org.noresponse.models.errors.NotFoundException;
import org.noresponse.models.errors.PermissionDeniedException;
import org.noresponse.models.errors.RateLimitException;
import org.noresponse.models.operations.CreateAssessmentResponse;
import org.noresponse.models.shared.AssessmentRequest;
import org.noresponse.models.shared.AssessmentRequestIdentifiers;

public class Application {

    public static void main(String[] args) throws Exception {

        NoResponse sdk = NoResponse.builder()
                .apiKey(System.getenv().getOrDefault("NORESPONSE_API_KEY", ""))
                .build();

        CreateAssessmentResponse res = sdk.assessments()
                .create()
                .idempotencyKey("signup-7f3a9c-0001")
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