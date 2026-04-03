package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		PlaceValidStepDef sd = new PlaceValidStepDef();
		if(PlaceValidStepDef.place_id==null) {
			
		sd.add_place_payload_with("avva", "andhra", "kmt");
		sd.user_calls_api_with_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_in_maps_with_using("avva", "GetPlaceAPI");
		}
	}

}
