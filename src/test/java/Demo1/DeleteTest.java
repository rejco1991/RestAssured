package Demo1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DeleteTest {
	
	@Test
	public void delete_test() {
		given()
		.when()
			.delete("https://reqres.in/api/users/2")
		.then()
			.statusCode(204)
			.log().all();
	}

	@Test
	public void delete2() {
		given()
			//.accept(ContentType.JSON)
			.header("accept","application/json")
			.header("api_key","special-key")
		.when()
			.delete("https://petstore.swagger.io/v2/pet/9223372036854775807")
		.then()
			.statusCode(200)
			.log().all();
			
	}
}