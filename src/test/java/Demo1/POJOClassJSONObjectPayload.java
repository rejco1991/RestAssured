package Demo1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*create POJO class of a JSON Object payload
 * {
 "badmintonBrand" : "Yonex",
 "raketName" : "Voltric 50 E-tune"
 } 
 */
public class POJOClassJSONObjectPayload {
	private String badmintonBrand;
	public String getBadmintonBrand() {
		return badmintonBrand;
	}
	public void setBadmintonBrand(String badmintonBrand) {
		this.badmintonBrand = badmintonBrand;
	}
	public String getRacketName() {
		return racketName;
	}
	public void setRacketName(String racketName) {
		this.racketName = racketName;
	}
	private String racketName;
	
//using Data Provider
	@DataProvider(name="badminton")
	public Object[][] createTestData(){
		return new Object[][] {
			{"Yonex","Voltric 50 E-tune"},
			{"Victor", "Woods 20"}
			
		};
	}
	@Test(dataProvider="badminton")
	public void BadmintonJSONFromPOJOClass(String a, String b) throws JsonProcessingException {
		//create object of this class
		POJOClassJSONObjectPayload objpojo = new POJOClassJSONObjectPayload();
		
		//set Values
		objpojo.setBadmintonBrand(a);
		objpojo.setRacketName(b);
		
		//class object --> String (JSON Object Payload) -- Serialization
		ObjectMapper objectMapper = new ObjectMapper();
		String objPojoJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objpojo);
		System.out.println(objPojoJson);
		
		//String (JSON Object Payload) --> Class Object -- Deserialization
		POJOClassJSONObjectPayload objpojo2=objectMapper.readValue(objPojoJson, POJOClassJSONObjectPayload.class);
		System.out.println("Badminton Brand: "+objpojo2.getBadmintonBrand());
		System.out.println("RacketName: "+objpojo2.getRacketName());

		}
	}

