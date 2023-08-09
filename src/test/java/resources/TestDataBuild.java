package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);
		List<String> myTypesList = new ArrayList<String>();
		myTypesList.add("shoe park");
		myTypesList.add("shop");
		ap.setTypes(myTypesList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}

}
