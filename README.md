# Warehouse management service
	This is a springboot serivce to manage the devices in the warehouse.
	The service is running on top of a MySql DB and provides REST interfaces to manage the devices lifecycle in the warehouse.
	Data validation logic is built in the service to validate the data mangement.

# Build and deploy the service
## Prerequisites
- MySql database running on port 3306 with a database created with name 'warehouse'.
- Java8 and mvn commands installed in the system
- Docker engine (*optional*)
## Build and deploy
The service is developed as a Maven project. To build and start the service run the following command in the application main directory 
`mvn clean install spring-boot:run` 
There is a dockerFile supplied in the repository to run the service in a container `docker build -t .`	

# REST Interfaces
Base URLs:
* <a href="http://localhost:8081">http://localhost:8081</a>
<h1 id="openapi-definition-iot-device-controller">iot-device-controller</h1>

## getDeviceBySerialNumber

<a id="opIdgetDeviceBySerialNumber"></a>

`GET /iotdevices`

<h3 id="getdevicebyserialnumber-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|serialNumber|query|string|true|none|

> Example responses

> 200 Response

<h3 id="getdevicebyserialnumber-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## updateDevice

<a id="opIdupdateDevice"></a>

`PUT /iotdevices`

> Body parameter

```json
{
  "serialNumber": "string",
  "pinCode": "string",
  "status": "ACTIVE",
  "temp": 0,
  "sold": true
}
```

<h3 id="updatedevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[IotDeviceDto](#schemaiotdevicedto)|true|none|

> Example responses

> 200 Response

<h3 id="updatedevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## createDevice

<a id="opIdcreateDevice"></a>

`POST /iotdevices`

> Body parameter

```json
{
  "serialNumber": "string",
  "pinCode": "string",
  "status": "ACTIVE",
  "temp": 0,
  "sold": true
}
```

<h3 id="createdevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[IotDeviceDto](#schemaiotdevicedto)|true|none|

> Example responses

> 200 Response

<h3 id="createdevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## deleteDevice

<a id="opIddeleteDevice"></a>

`DELETE /iotdevices`

<h3 id="deletedevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|pinCode|query|string|true|none|

> Example responses

> 200 Response

<h3 id="deletedevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## patchDevice

<a id="opIdpatchDevice"></a>

`PATCH /iotdevices`

> Body parameter

```json
{
  "serialNumber": "string",
  "pinCode": "string",
  "status": "ACTIVE",
  "temp": 0,
  "sold": true
}
```

<h3 id="patchdevice-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[IotDeviceDto](#schemaiotdevicedto)|true|none|

> Example responses

> 200 Response

<h3 id="patchdevice-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## getDeviceStatusBySerialNumber

<a id="opIdgetDeviceStatusBySerialNumber"></a>

`GET /iotdevices/status`

<h3 id="getdevicestatusbyserialnumber-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|serialNumber|query|string|true|none|

> Example responses

> 200 Response

<h3 id="getdevicestatusbyserialnumber-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ResponseDto](#schemaresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

## getAllActiveDevices

<a id="opIdgetAllActiveDevices"></a>

`GET /iotdevices/availableactivedevices`

<h3 id="getallactivedevices-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|page|query|integer(int32)|false|none|
|size|query|integer(int32)|false|none|

> Example responses

> 200 Response

<h3 id="getallactivedevices-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[PaginatedResponseDto](#schemapaginatedresponsedto)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Bad Request|[ResponseDto](#schemaresponsedto)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|[ResponseDto](#schemaresponsedto)|
|406|[Not Acceptable](https://tools.ietf.org/html/rfc7231#section-6.5.6)|Not Acceptable|[ResponseDto](#schemaresponsedto)|
|500|[Internal Server Error](https://tools.ietf.org/html/rfc7231#section-6.6.1)|Internal Server Error|[ResponseDto](#schemaresponsedto)|

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_ResponseDto">ResponseDto</h2>
<!-- backwards compatibility -->
<a id="schemaresponsedto"></a>
<a id="schema_ResponseDto"></a>
<a id="tocSresponsedto"></a>
<a id="tocsresponsedto"></a>

```json
{
  "success": true,
  "message": "Device data is retrieved successfully",
  "data": {}
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|success|boolean|false|none|none|
|message|string|false|none|none|
|data|object|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|message|Device data is retrieved successfully|
|message|Devices data are retrieved successfully|
|message|Device data is add successfully|
|message|Error occured while saving the device data|
|message|Device data is updated successfully|
|message|Error occured while updating the device data|
|message|Device data is patched successfully|
|message|Error occured while patching the device data|
|message|Device data is deleted successfully|
|message|Pin code exists for another device|
|message|Serial number exists for another device|
|message|Device not found|
|message|Data validation failed|
|message|Request contains invalid data|
|message|Technical failure occurred, Please contact support team|
|message|User doesn't have the required permission level to perform this action|

<h2 id="tocS_IotDeviceDto">IotDeviceDto</h2>
<!-- backwards compatibility -->
<a id="schemaiotdevicedto"></a>
<a id="schema_IotDeviceDto"></a>
<a id="tocSiotdevicedto"></a>
<a id="tocsiotdevicedto"></a>

```json
{
  "serialNumber": "string",
  "pinCode": "string",
  "status": "ACTIVE",
  "temp": 0,
  "sold": true
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|serialNumber|string|false|none|none|
|pinCode|string|false|none|none|
|status|string|false|none|none|
|temp|integer(int32)|false|none|none|
|sold|boolean|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|status|ACTIVE|
|status|READY|

<h2 id="tocS_PaginatedResponseDto">PaginatedResponseDto</h2>
<!-- backwards compatibility -->
<a id="schemapaginatedresponsedto"></a>
<a id="schema_PaginatedResponseDto"></a>
<a id="tocSpaginatedresponsedto"></a>
<a id="tocspaginatedresponsedto"></a>

```json
{
  "success": true,
  "message": "Device data is retrieved successfully",
  "data": {},
  "pageNo": 0,
  "pageSize": 0,
  "totalElements": 0,
  "totalPages": 0,
  "last": true
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|success|boolean|false|none|none|
|message|string|false|none|none|
|data|object|false|none|none|
|pageNo|integer(int32)|false|none|none|
|pageSize|integer(int32)|false|none|none|
|totalElements|integer(int64)|false|none|none|
|totalPages|integer(int32)|false|none|none|
|last|boolean|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|message|Device data is retrieved successfully|
|message|Devices data are retrieved successfully|
|message|Device data is add successfully|
|message|Error occured while saving the device data|
|message|Device data is updated successfully|
|message|Error occured while updating the device data|
|message|Device data is patched successfully|
|message|Error occured while patching the device data|
|message|Device data is deleted successfully|
|message|Pin code exists for another device|
|message|Serial number exists for another device|
|message|Device not found|
|message|Data validation failed|
|message|Request contains invalid data|
|message|Technical failure occurred, Please contact support team|
|message|User doesn't have the required permission level to perform this action|
