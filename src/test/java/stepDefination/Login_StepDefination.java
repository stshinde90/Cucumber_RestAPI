package stepDefination;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class Login_StepDefination extends Utils {

	TestDataBuild td = new TestDataBuild();
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response res;
	static String placeID;

	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with_something_something_something(String name, String language, String address)
			throws Throwable {
		requestSpec = given().spec(requestSpecification()).body(td.addPlacePayload(name, language, address));
	}
	
	 @Given("^DeletePlace Payload$")
	    public void deleteplace_payload() throws Throwable {
		 requestSpec = given().spec(requestSpecification()).body(td.deletePlacePayload(placeID));
		 
	    }

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_something_with_something_http_request(String postData, String postMethod) throws Throwable {

		APIResources resourceValue = APIResources.valueOf(postData);
		System.out.println(resourceValue.getAPIResource());

		if (postMethod.equalsIgnoreCase("POST")) {
			res = requestSpec.when().post(resourceValue.getAPIResource());

		}
		else if(postMethod.equalsIgnoreCase("GET"))
		{
			res = requestSpec.when().get(resourceValue.getAPIResource());
		}

	}

	@Then("^the API call got success with status code 200$")
	public void the_api_call_got_success_with_status_code_200() throws Throwable {
		res = res.then().spec(responseSpecification()).extract().response();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String key, String ExpectedValue) throws Throwable {
		
		String keyValue = responseJesponceToValue(res.asString(), key);
		Assert.assertEquals(keyValue, ExpectedValue);
	}

	@And("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_placeid_created_maps_to_something_using_something(String expectedName, String resourceNAme) throws Throwable {
		placeID = responseJesponceToValue(res.asString(), "place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_something_with_something_http_request(resourceNAme, "GET");
		String actualName = responseJesponceToValue(res.asString(), "name");
		Assert.assertEquals(actualName, expectedName);
	}

}
