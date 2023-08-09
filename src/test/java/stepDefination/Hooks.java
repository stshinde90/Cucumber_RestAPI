package stepDefination;

import io.cucumber.java.Before;

public class Hooks {
	//execute this code only when place id is null
	//write a code that will give you place id
	
	@Before("@tag2")
	public void beforeScenario() throws Throwable
	{
		if(Login_StepDefination.placeID==null)
		{
			Login_StepDefination stepDefination = new Login_StepDefination();
			stepDefination.add_place_payload_with_something_something_something("Johnson","English","Mumbai");
			stepDefination.user_calls_something_with_something_http_request("AddPlaceAPI", "POST");
			stepDefination.verify_placeid_created_maps_to_something_using_something("Johnson", "GetPlaceAPI");
		}
	}

}
