package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		//write a code to get place_id
		//execute this code only when the place_id is null
		
		StepDefination m = new StepDefination();
		
		if(StepDefination.placeId==null) {
			m.add_Place_Payload_with("Ishan", "Hindi", "boriponda");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_Id_created_maps_to_using("Ishan", "getPlaceAPI");
		}
	}
	

}
