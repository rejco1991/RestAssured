package Demo1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class F1Example {
	
	//checking the count or size
	@Test
	public void test_NumberOfCircuts() {
		given()
		.when()
			.get("https://ergast.com/api/f1/2024/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(24) );
	}

	//checking the response header
	@Test
	public void test_responseHeaderData() {
		given()
		.when()
			.get("https://ergast.com/api/f1/2024/circuits.json")
		.then()
			.assertThat()
			.statusCode(200)
		.and()
			.contentType(ContentType.JSON)
		.and()
			.header("Content-Length", equalTo("5464"))
			.log().all();
	}
	//Path Parameter
	@Test
	public void test_NoOfCircuits_Parametrized() {
		String season = "2017";
		int numberOfRaces = 20;
		given()
			.pathParam("raceSeason", season)
		.when()
			.get("https://ergast.com/api/f1/{raceSeason}/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(numberOfRaces) );
			
	}
	//using Data Provider
		@DataProvider(name="seasonAndNoOfRaces")
		public Object[][] createTestData(){
			return new Object[][] {
				{"2017", 20},
				{"2022", 22},
				{"2023", 22},
				{"2024", 24}
			};
		}
		
		@Test(dataProvider="seasonAndNoOfRaces")
		public void testNoOfCircuits_dataDriven(String season, int noOfRaces) {
			given()
			.pathParam("raceSeason", season)
		.when()
			.get("https://ergast.com/api/f1/{raceSeason}/circuits.json")
		.then()
			.assertThat()
			.body("MRData.CircuitTable.Circuits", hasSize(noOfRaces) );
		}
		@Test
		public void test_circuitAndCountry() {
			//First retrive the circuitID for the first circuit of the 2022 season
			String var1 =
			given()
			.when()
				.get("https://ergast.com/api/f1/2022/circuits.json")
			.then()
				.extract()
				.path("MRData.CircuitTable.Circuits[0].circuitId");
			
			//Then passing it to second request and verify the circuit is located in country Australia
			given()
				.pathParam("circuitID1", var1)
			.when()
				.get("https://ergast.com/api/f1/2022/circuits/{circuitID1}.json")
			.then()
				.assertThat()
				.body("MRData.CircuitTable.Circuits[0].Location.country", equalTo("Australia"))
				.log().all();
		}
		
		//Response Specification Builder
		ResponseSpecification checkStatusCodeAndContent = new ResponseSpecBuilder()
																.expectStatusCode(200)
																.expectContentType(ContentType.JSON).build();
		@Test
		public void test_noOfCircuitsUsingResponseSpec() {
			given()
			.when()
				.get("https://ergast.com/api/f1/2022/circuits.json")
			.then()
				.assertThat()
				.spec(checkStatusCodeAndContent)
			.and()
				.body("MRData.CircuitTable.Circuits[0].Location.country", equalTo("Australia"));
		}
}