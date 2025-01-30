package Demo1;
 
import org.testng.annotations.Test;
 
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
 
/* POJO Classes of Nested JSON Payload

* {

	"governingBody" : "BWF",

	"hostingCity" : "Tokyo",

	"sponsors": "TotalEngergies"

	"elitePlayers" : {

		"ms" : "LIN DAN",

		"ws" : "P V SINDHU"

          }

}*/
 
public class POJOClassNestedJSONPayload {

	public class ElitePlayers {

		private String ms;

		private String ws;

		public String getMs() {

			return ms;

		}

		public void setMs(String ms) {

			this.ms = ms;

		}

		public String getWs() {

			return ws;

		}

		public void setWs(String ws) {

			this.ws = ws;

		}

	}

	private String governingBody;

	private String hostingCity;

	private String sponsers;

	ElitePlayers elitePalyers;

	public String getGoverningBody() {

		return governingBody;

	}

	public void setGoverningBody(String governingBody) {

		this.governingBody = governingBody;

	}

	public String getHostingCity() {

		return hostingCity;

	}

	public void setHostingCity(String hostingCity) {

		this.hostingCity = hostingCity;

	}

	public String getSponsers() {

		return sponsers;

	}

	public void setSponsers(String sponsers) {

		this.sponsers = sponsers;

	}

	public ElitePlayers getElitePalyers() {

		return elitePalyers;

	}

	public void setElitePalyers(ElitePlayers elitePalyers) {

		this.elitePalyers = elitePalyers;

	}


	public class CreateNestedJSONFromPOJOClasses {

		@Test

		public void function() throws JsonProcessingException {

			POJOClassNestedJSONPayload nestedPOJO = new POJOClassNestedJSONPayload();

			nestedPOJO.setGoverningBody("BWF");

			nestedPOJO.setHostingCity("Tokyo");

			nestedPOJO.setSponsers("TotalEnergies");

			ElitePlayers elitePlayers = new ElitePlayers();

			elitePlayers.setMs("LIN DAN");

			elitePlayers.setWs("P V SINDHU");

			nestedPOJO.setElitePalyers(elitePlayers);

			//class object --> String (Nested JSON Payload)

			ObjectMapper objMapper3 = new ObjectMapper();

			String nestedJSONPayload = objMapper3.writerWithDefaultPrettyPrinter().writeValueAsString(nestedPOJO);

			System.out.println(nestedJSONPayload);

			//String (Nested JSON Payload) --> class Object

			POJOClassNestedJSONPayload nestedPojo2= objMapper3.readValue(nestedJSONPayload, POJOClassNestedJSONPayload.class);

			System.out.println("Elite Player[WS] :"+nestedPojo2.getElitePalyers().getWs());

		}

	}
 
}

 