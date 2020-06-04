package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePlayload(String name, String language, String address) {
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setName(name);
		addPlace.setPhone_number("\"(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com\",");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		addPlace.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		addPlace.setLocation(l);
		return addPlace;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n" + 
				"    \"place_id\":\"" + placeId + "\"\r\n" + 
				"}";
	}
}
