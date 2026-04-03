package resourses;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public Location addPlacePayload(String name , String language, String address) {
		Location loc = new Location();
		loc.setAccuracy(60);
		loc.setAddress(address);
		loc.setLanguage(language);
		loc.setName(name);
		loc.setPhone_number("(+91) 800 893 3937");
		loc.setWebsite("http://google.com");
		List<String> types = new ArrayList<String>();
		types.add("trends");
		types.add("fashion");
		loc.setTypes(types);
		AddPlace adp = new AddPlace();
		adp.setLat(-38.383494);
		adp.setLng(33.427362);
		loc.setLocation(adp);
		
		return loc;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}\r\n";
	}

}
