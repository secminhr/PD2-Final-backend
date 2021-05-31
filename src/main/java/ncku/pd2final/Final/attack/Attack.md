# Attack System
This documentation describes the endpoints related to calculating the attack point that players had got.

## login endpoints
### Attack
Calculate attack points.

**URL**: `/attck`

**Method**: `GET`

**Request Data**
```aidl
/attack?lat={latitude of point_0}&lat={latitude of point_1}&...&lat={latitude of point_n}
       &lon={lonfgitude of point_0}&lon={longitude of point_1}&...&lon={longitude of point_n}  
```
**Example**

Players pass through three points 
(23.000663924688777, 120.2236738193727), 
(23.000795088510326, 120.22048718119788) and 
(22.999638457690317, 120.22040945831554 ).
```aidl
/attack?lat=23.000663924688777&lat=23.000795088510326&lat=22.999638457690317
       &lon=120.2236738193727&lon=120.22048718119788&lon=120.22040945831554  
```

**Success response** : Nothing

**Status code** : `204 No Content`