package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

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
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		res = given().spec(resquestSpecification())
				.body(data.addPlacePlayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		//resourceAPI.getResource();
		//responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				//.expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST")) {
			response = res.when()
					.post(resourceAPI.getResource());
		}
		else if (method.equalsIgnoreCase("GET")) {
			response = res.when()
					.get(resourceAPI.getResource());
		}
		
		/*
		 * response = res.when() .post(resourceAPI.getResource()) .then()
		 * .spec(responseSpec) .extract().response();
		 */
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

	@Then("{string} in reponse body is {string}")
	public void in_reponse_body_is(String keyValue , String expectedValue) {
		Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		placeId = getJsonPath(response, "place_id");
		res = given().spec(resquestSpecification())
				.queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String name = getJsonPath(response, "name");
		Assert.assertEquals(name, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    res = given().spec(resquestSpecification())
	    	.body(data.deletePlacePayload(placeId));
	}

}
