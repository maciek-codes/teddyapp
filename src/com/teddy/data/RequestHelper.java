package com.teddy.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestHelper {
	
	// Store array of rooms for each building
	public static Map<String, ArrayList<String>> buildingRoomList = null;

	
	public static Map<String, ArrayList<String>> getRoomsAndBuildings() {
		
		// Maybe you got it before?
		if(buildingRoomList != null) {
			return buildingRoomList;
		}
		
		// Otherwise populate the list and return it
		buildingRoomList = new HashMap<String, ArrayList<String>>();
        JSONArray buildings = null;
       
        // Create a parser
        JsonParser jParser = new JsonParser();
        
        // Get data of buildings
        // Use: http://service-teddy2012.rhcloud.com/buildings
        // and: http://service-teddy2012.rhcloud.com/building_name/
        
        
        // TODO: This can return null and we need to handle this situation when internet
        // Is not working
        
        try{
        	JSONObject buildingsObject = jParser.getJSONFromUrl("http://service-teddy2012.rhcloud.com/buildings");
        	try {
    			buildings = buildingsObject.getJSONArray("buildings");
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
        } catch(Error e){
        	return null;
        }
		
		for(int i = 0; i < buildings.length(); ++i) {
        	JSONArray rooms = null;
            JSONObject roomsObject;
			String building = null;
			ArrayList<String> listOfRooms = null;
            
			try {
            	building = buildings.get(i).toString();
				roomsObject = jParser.getJSONFromUrl("http://service-teddy2012.rhcloud.com/" + building);
				rooms = roomsObject.getJSONArray("rooms");
				
				listOfRooms = new ArrayList<String>();
	            for(int j = 0; j < rooms.length(); ++j) {
	            	String roomName = rooms.get(j).toString();
	            	//adapterroom.add(roomName);
	            	listOfRooms.add(roomName);
	            }
		            
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // If there are any rooms, add building to the list
            if(listOfRooms != null && !listOfRooms.isEmpty()) {
            	//adapterbuild.add(building);
            	buildingRoomList.put(building, listOfRooms);
            }
        }
		
		return buildingRoomList;
	}
}
