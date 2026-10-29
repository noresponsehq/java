# InviteList

GET /v1/workspaces/{workspace_id}/invites: the workspace's pending invites, oldest first, at most 50, with no token.


## Fields

| Field                                          | Setter Type                                    | Getter Type                                    | Required                                       | Description                                    |
| ---------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- |
| `items`                                        | List\<[Invite](../../models/shared/Invite.md)> | List\<[Invite](../../models/shared/Invite.md)> | :heavy_check_mark:                             | N/A                                            |