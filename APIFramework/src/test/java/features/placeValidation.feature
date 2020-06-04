Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "post" http request
	Then the API call is success with status code 200
	And "status" in reponse body is "OK"
	And "scope" in reponse body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples: 
	| name    | language | address            |
	| AAhouse | English  | World Cross Center |
#	| BBhouse | Spanish  | Sea cross center   |	

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "post" http request
	Then the API call is success with status code 200
	And "status" in reponse body is "OK"