package Demo1;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class putExample {

	@Test
	public void test1() {
		baseURI = "https://petstore.swagger.io/v2";
		File jsonDataFile = new File("src/test/resources/payloads/Example2.json");
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(jsonDataFile)
		.when()
			.put("/pet")
		.then()
			.statusCode(200)
			.log().all();
	}
}