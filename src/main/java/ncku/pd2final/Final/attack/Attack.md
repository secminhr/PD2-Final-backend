# Attack System
This documentation describes the endpoints related to calculating the attack point that players had got.

## login endpoints
### Attack
Calculate attack points.

**URL**: `/attack`

**Method**: `GET`

**Request Data**
```
/attack?lat={latitude of point_0}&lat={latitude of point_1}&...&lat={latitude of point_n}
       
       &lon={lonfgitude of point_0}&lon={longitude of point_1}&...&lon={longitude of point_n}
       
       &time={the time (minutes) that players had  recorded}
       
       &purpose={the "latitude" of the purpose that players wnt to attack}
       
       &purpose={the "longitude" of the purpose that players wnt to attack}  
```
**Example**

Players pass through three points 
(23.000663924688777, 120.2236738193727), 
(23.000795088510326, 120.22048718119788) and 
(22.999638457690317, 120.22040945831554 ).
```
/attack?lat=23.000663924688777&lat=23.000795088510326&lat=22.999638457690317
       
       &lon=120.2236738193727&lon=120.22048718119788&lon=120.22040945831554
       
       &time=15
       
       &purpose=22.991945528467088
       
       &purpose=120.20656731792185
```

**Success response** : Nothing

**Status code** : `204 No Content`


####Error Response
**Condition** :The player didn't log in before calling this endpoint.

**Status code**: `401 Unauthorized`

**Content**
```json
{
  "success": false,
  "auth": "/login"
} 
```

**Or**

**Condition** :Some parameters leak.

**Status code**: `400 Bad Request`

**Content**
```json
{
  "success": false,
  "message": "wrong arguments"
}
```