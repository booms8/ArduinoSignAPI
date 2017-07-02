# ArduinoSignAPI
Builder and API to provide access to an Arduino LED sign. Only the 8x8 sign is currently supported.
To function correctly, the ArduinoSignSketch project must exist somewhere accessible (including permissions on the actual files) by the Tomcat user (use this directory for the WORKING_DIRECTORY constant value).

## Endpoints
`GET http://sign.potato.fish/ArduinoSign/api/message/get/`  
Returns the message currently being displayed

`GET http://sign.potato.fish/ArduinoSign/api/message/set/[message]`  
Sets the current message to [message]

`GET http://sign.potato.fish/ArduinoSign/api/message/time`
Returns the Unix timestamp of the last time the message was updated

`DELETE http://sign.potato.fish/ArduinoSign/api/message`  
Erases the message from the sign
