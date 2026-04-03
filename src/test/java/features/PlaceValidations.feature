Feature: To validate places in API's
	
   @AddPlace	
   Scenario Outline: To verify Add place is successfully added in Place Validations
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When  User calls "AddPlaceAPI" API with "POST" Request 
	Then the api call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created in maps with "<name>" using "GetPlaceAPI"
	
 Examples:
	|name       |language|address          |
	|Houseiravva|telugu  |venkateshNag Khmm|
#	|KondiHouse |english |shamsheernag bpl |

@DeletePlace
Scenario: To verifiy Delete Place API is working
	Given Delete place Api Payload
	When  User calls "DeletePlaceAPI" API with "POST" Request 
	Then the api call got success with status code 200
	And "status" in response body is "OK"
