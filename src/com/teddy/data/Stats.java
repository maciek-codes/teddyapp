package com.teddy.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
public class Stats extends Activity {
	ConnectionDetector cd;

	// Remember list of buildings and rooms associated
	Map<String, ArrayList<String>> buildingRoomDict;
	
	String buildingSelected, roomSelected;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
        
        setContentView(R.layout.stats);

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons
                
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Available");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Statistics");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power Cost");
             
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
        //Spinners
        final Spinner build = (Spinner) findViewById(R.id.building_spinner);
        final Spinner room = (Spinner) findViewById(R.id.room_spinner);
        
        // Set resources
        ArrayAdapter<CharSequence> adapterbuild = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        adapterbuild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        final ArrayAdapter<CharSequence> adapterroom = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        adapterroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          
        
        if (cd.isConnectingToInternet()) {
        	buildingRoomDict = RequestHelper.getRoomsAndBuildings();

	        String[] buildings = new String[buildingRoomDict.keySet().size()];
	        buildingRoomDict.keySet().toArray(buildings);
	        
	        for(int i = 0; i < buildings.length; ++i) {
	        	
				String building = buildings[i];
				ArrayList<String> listOfRooms = buildingRoomDict.get(building);
				
				// Skip if no rooms
				if(listOfRooms == null || listOfRooms.size() <= 0)
					continue;
				
				// Otherwise add them to spinner
	            for(int j = 0; j < listOfRooms.size(); ++j) {
	            	String roomName = listOfRooms.get(j).toString();
	            	adapterroom.add(roomName);
	            }
			   
	            // Add the building too
	            adapterbuild.add(building);
	        }
	        
	        //Now use dictionary to populate the UI
	        build.setAdapter(adapterbuild);
	        buildingSelected =(String) (build.getItemAtPosition(0));
	        
	        // Room spinner adapter
	        room.setAdapter(adapterroom);
	        roomSelected =(String) (room.getItemAtPosition(0));
	         
	        // Add event handler to handle room selection
	        build.setOnItemSelectedListener(new OnItemSelectedListener() {
	        
	        	
	        	@Override
	        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	        	//TextView text2 = (TextView ) findViewById(R.id.usagetext);
	        		buildingSelected =(String) (build.getItemAtPosition(position));
	        	
	        	List<String> listOfRooms = buildingRoomDict.get(buildingSelected);
	        	
	        	// Update room spinner when building changes
	        	adapterroom.clear();
	        	for(String r : listOfRooms){
	        		adapterroom.add(r);
	        	}
	        	room.setAdapter(adapterroom);
	        	roomSelected =(String) (room.getItemAtPosition(0));
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
	            	JsonParser parser = new JsonParser();
	            	
	            	// Get new selection
	            	roomSelected =(String) (room.getItemAtPosition(position));
	            	
	            	Calendar cal = Calendar.getInstance();
	            	int year = cal.get(Calendar.YEAR);  
	            	int month = cal.get(Calendar.MONTH)+1;
	            	
	            	// Display available computers in this room:
	            	String requestUrl = "http://service-teddy2012.rhcloud.com/log/" + buildingSelected 
	            			+ "/" + roomSelected + "/" + year + "/" + month;
	            	
	            	JSONObject jObject = parser.getJSONFromUrl(requestUrl);
	            	double powerCost = 0,idleCost=0;
	            	try {
	            		powerCost = jObject.getDouble("Total_Power_Cost");
	            		idleCost = jObject.getDouble("power_cost_no_idle");
					} catch (JSONException e) {
						// TODO Catch exception here if json is not formulated well
						e.printStackTrace();
					}
	            	
	            	System.out.println(idleCost);
	            	TextView text2 = (TextView) findViewById(R.id.powertext);
	            	text2.setText(String.format("The power consuption for the last month cost: %.2f GBP.",powerCost));
	            	
	            	TextView text3 = (TextView) findViewById(R.id.idletext);
	            	text3.setText(String.format("Possible savings: %.2f GBP.",idleCost));
	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> parentView) {
	                // your code here
	            }

	        });
	        
	        usage.setOnClickListener(new Button.OnClickListener(){
	        	public void onClick(View v){
		        	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
		        	finish();
		        	startActivity(i);
	        	}
	        });
	              
	        power.setOnClickListener(new Button.OnClickListener(){
		        public void onClick(View v){
	            	Intent i = new Intent(getApplicationContext(), Power.class);
	            	finish();
	            	startActivity(i);	
	
		    	}
	        });
	        
	        stats.setOnClickListener(new Button.OnClickListener(){
	        	public void onClick(View v){}
	        });
        
        }
        else {
        	System.out.println("No Internet Connection");
        }  
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
