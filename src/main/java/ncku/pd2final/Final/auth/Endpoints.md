# User Management and Auth System 
This documentation describes the endpoints related to player data and authentication.

## open endpoints
### Register
Register a new player.

**URL**: `/register`

**Method**: `POST`

**Request Data**
```json
{
  "username": "{specified username}",
  "nickname": "{display name of the player}",
  "password": "{plain password}",
  "faction": "{chosen faction}"
}
```

#### Success response
**Status code**: `201 Created`

**Content**
```json
{
    "success": true,
    "auth": "{endpoint to login}"
}
```

**Example content**
```json
{
  "success": true,
  "auth": "/login"
}
```

#### Error response
**Condition**: When some arguments are not provided.

**Status code**: `400 Bad Request`

**Content**
```json
{
    "success": false,
    "message": "wrong arguments"
}
```
##### OR
**Condition**: Username has been registered.

**Status code**: `409 Conflict`

**Content**
```json
{
    "success": false,
    "message": "username already exists",
    "auth": "{endpoint to login}"
}
```

**Example content**
```json
{
    "success": false,
    "message": "username already exists",
    "auth": "/login"
}
```
---
### Login
Login a user with username and password.

**URL**: `/login`

**Method**: `POST`

**Request Data**
```json
{
    "username": "{username}",
    "password": "{password}"
}
```
#### Success response
**Status code**: `200 OK`

**Content**
```json
{
    "success": true,
    "info": "{endpoint to get the player's data}"
}
```
**Example content**
```json
{
    "success": true,
    "info": "/info"
}
```
#### Error response
**Condition**: Either username or password in invalid.

**Status code**: `401 Unauthorized`

**Content**
```json
{
    "success": false,
    "auth": "{endpoint to login}",
    "message": "login failed"
}
```

**Example content**
```json
{
    "success": false,
    "auth": "/login",
    "message": "login failed"
}
```
---
## Authentication required Endpoints
In this section, if not specified, every request that is not authorized will receive the error response:
```json
{
    "success": false,
    "auth": "{endpoint to login}"
}
```
For example:
```json
{
    "success": false,
    "auth": "{/login"
}
```
---
### Get the current player's data
**URL**: `/info`

**Method**: `GET`

**Request Data**: None

#### Success response
**Status code**: `200 OK`

**Content**
```json
{
    "username": "{username}",
    "status": {
        "exp": {current experience point},
        "level": {current level},
        "nickname": "{nickname}",
        "faction": "{chosen faction}"
    }
}
```

**Example content**

For a username whose
- username is `sssss`
- experience point is 0
- level is 0
- nickname (display name) is `s1s`
- and chosen faction is `A`
```json
{
    "username": "sssss",
    "status": {
        "exp": 0,
        "level": 0,
        "nickname": "s1s",
        "faction": "A"
    }
}
```
---
### Logout
Log out the current user.

**URL**: `/logout`

**Method**: `GET`

**Request Data**: None

#### Success response
**Status code**: `204 No Content`

**Content**: None

#### Note
- This api won't fail even if the user is not logged in. 