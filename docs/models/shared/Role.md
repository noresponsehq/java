# Role

A built-in capability set. Specialist roles are independent; effective access is returned by GET /v1/workspaces/{workspace_id}/capabilities.

## Example Usage

```java
import org.noresponse.models.shared.Role;

Role value = Role.OWNER;

// Open enum: use .of() to create instances from custom string values
Role custom = Role.of("custom_value");
```


## Values

| Name           | Value          |
| -------------- | -------------- |
| `OWNER`        | owner          |
| `ADMIN`        | admin          |
| `DEVELOPER`    | developer      |
| `VIEWER`       | viewer         |
| `INVESTIGATOR` | investigator   |
| `AUDITOR`      | auditor        |
| `BILLING`      | billing        |