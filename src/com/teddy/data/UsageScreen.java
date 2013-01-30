package com.teddy.data;

import java.util.ArrayList;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
public class UsageScreen extends Activity  {


	// Remember list of buildings and rooms associated
	Map<String, List<String>> buildingRoomDict;
	
	String selectedFrom, selectedFrom2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.usagescreen);

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons
                
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
             
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
        //Spinners
        final Spinner build = (Spinner) findViewById(R.id.building_spinner);
        final Spinner room = (Spinner) findViewById(R.id.room_spinner);
        
        // Set resources
        ArrayAdapter<CharSequence> adapterbuild = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        adapterbuild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        final ArrayAdapter<CharSequence> adapterroom = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        adapterroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          
        // Get data of buildings
        // Use: http://service-teddy2012.rhcloud.com/buildings
        // and: http://service-teddy2012.rhcloud.com/building_name/
        buildingRoomDict = new HashMap<String, List<String>>();
        JSONArray buildings = null;
       
        // Create a parser
        JsonParser jParser = new JsonParser();
        JSONObject buildingsObject = jParser.getJSONFromUrl("http://service-teddy2012.rhcloud.com/buildings");
		try {
			buildings = buildingsObject.getJSONArray("buildings");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	            	adapterroom.add(roomName);
	            	listOfRooms.add(roomName);
	            }
		            
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // If there are any rooms, add building to the list
            if(listOfRooms != null && !listOfRooms.isEmpty()) {
            	adapterbuild.add(building);
            	buildingRoomDict.put(building, listOfRooms);
            }
        }
        
       
        //Now use dictionary to populate the UI
        build.setAdapter(adapterbuild);
        selectedFrom =(String) (build.getItemAtPosition(0));
        
        // Room spinner adapter
        room.setAdapter(adapterroom);
        selectedFrom2 =(String) (room.getItemAtPosition(0));
        //TextView text0 = (TextView ) findViewById(R.id.usagetext);
		//text0.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
         
        // Add event handler to handle room selection
        build.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	//TextView text2 = (TextView ) findViewById(R.id.usagetext);
            	selectedFrom =(String) (build.getItemAtPosition(position));
            	List<String> listOfRooms = buildingRoomDict.get(selectedFrom);
            	
            	// Update room spinner when building changes
            	adapterroom.clear();
            	for(String r : listOfRooms){
            		adapterroom.add(r);
            	}
            	room.setAdapter(adapterroom);
            	selectedFrom2 =(String) (room.getItemAtPosition(0));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        

        room.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	//TextView text2 = (TextView ) findViewById(R.id.usagetext);

            	selectedFrom2 =(String) (room.getItemAtPosition(position));    
				//text2.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
			    // Change room adapter				
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

        	Intent i = new Intent(getApplicationContext(),  Power.class);
        	finish();
        	startActivity(i);
    	}
        });
        
        stats.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(), Stats.class);
            	finish();
            	startActivity(i);
        	}
        });
        
        
	}

	
	
	@Override
    public void onBackPressed() {
     finish();
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.usagemenu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		    case R.id.instructions:
		    startActivity(new Intent(this, Instr.class));
		    return true;
		    case R.id.support:
		    {
		    startActivity(new Intent(this, Support.class));finish();}
		    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
}