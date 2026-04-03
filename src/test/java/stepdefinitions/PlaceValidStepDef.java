package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resourses.APIResources;
import resourses.TestDataBuild;
import resourses.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class PlaceValidStepDef  extends Utility{
	RequestSpecification resp;
	ResponseSpecification respspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	
		resp = given().spec(requestSpecifiction())
				.body(data.addPlacePayload(name, language, address));
	}

		//resource are coming from APIResources
@When("User calls {string} API with {string} Request")
public void user_calls_api_with_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		respspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		 response = resp.when().post(resourceAPI.getResource());
		 else if(method.equalsIgnoreCase("GET"))
			 response = resp.when().post(resourceAPI.getResource());
	}

	@Then("the api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    assertEquals(getJsonPath(response,keyValue),expectedValue);
	}
	
	@Then("verify place_Id created in maps with {string} using {string}")
	public void verify_place_id_created_in_maps_with_using(String ExpectedName, String resource) throws IOException {
	    place_id = getJsonPath(response,"place_id");
		resp = given().spec(requestSpecifiction()).queryParam("place_id", place_id);
		user_calls_api_with_request(resource,"GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,ExpectedName);
	}
	

	@Given("Delete place Api Payload")
	public void delete_place_api_payload() throws IOException {
	   
		resp = given().spec(requestSpecifiction())
		.body(data.deletePlacePayload(place_id));
	}



	

}
