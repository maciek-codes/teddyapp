package com.teddy.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class RequestHelper {
	
	// Store array of rooms for each building
	public static Map<String, ArrayList<String>> buildingRoomList = null;
	
	/* TODO: Display error message
	public static void displayExceptionMessage(String msg)
	{
	    Toast.makeText( msg, Toast.LENGTH_SHORT).show();
	}
/*/
	public static Map<String, ArrayList<String>> getRoomsAndBuildings(Context thisContext) {

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


		// TODO:DONE1 This can return null and we need to handle this situation when internet
		// Is not working

		try {
			JSONObject buildingsObject = jParser.getJSONFromUrl("http://service-teddy2012.rhcloud.com/buildings");
			buildings = buildingsObject.getJSONArray("buildings");
		} catch (JSONException e) {
			// TODO DONE1 aici Auto-generated catch block
			
			android.os.Process.killProcess(android.os.Process.myPid());

			//displayExceptionMessage(e.getMessage());
			
			/*android.app.AlertDialog.Builder builder = new AlertDialog.Builder(com.teddy.data.LoginActivity.this);
		    builder.setMessage("This will end the activity");
		    builder.setCancelable(true);
		    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					android.os.Process.killProcess(android.os.Process.myPid());
					// e.printStackTrace();
				}
			});
		    AlertDialog dialog = builder.create();
		    dialog.show();
			
			//e.printStackTrace();
			//!!!!!!!!!!!!!!!!
			//http://developer.android.com/reference/android/app/ApplicationErrorReport.html
			
			/*AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
			builder.setMessage("Are you sure you want to exit?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					// e.printStackTrace();
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

			AlertDialog alert = builder.create();
			alert.show();
			*/
			//return null;
		}
		catch (NullPointerException e) {
			//TODO: DONE1
			/*AlertDialog.Builder builder = new AlertDialog.Builder(thisContext);
			builder.setMessage("Are you sure you want to exit?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					android.os.Process.killProcess(android.os.Process.myPid());

					// e.printStackTrace();
				}
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});

			AlertDialog alert = builder.create();
			alert.show();
*/

			e.printStackTrace();
			android.os.Process.killProcess(android.os.Process.myPid());

			//return null;

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
				// TODO: DONE1 Auto-generated catch block
				e.printStackTrace();
				android.os.Process.killProcess(android.os.Process.myPid());

			}
			catch (NullPointerException e) {
				//TODO: DONE1
				e.printStackTrace();
				android.os.Process.killProcess(android.os.Process.myPid());

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
