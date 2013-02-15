package com.teddy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

public class Power extends Activity {
	// Connection detector class
		ConnectionDetector cd;
		static String textSize ="Medium";
		static String timeSelected="15 Minutes";
		static int textSizeInt=16;

		// Remember list of buildings and rooms associated
		Map<String, ArrayList<String>> buildingRoomDict;
		
		String buildingSelected, roomSelected;
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        Bundle extras = getIntent().getExtras(); 
	        if(extras !=null)
	        {
	        	timeSelected = extras.getString("time");
	        	textSize = extras.getString("text");
	        	if(textSize.equals("Small"))textSizeInt=14;
	            else if(textSize.equals("Medium"))textSizeInt=16;
	            else if(textSize.equals("Big"))textSizeInt=20;
	            else if(textSize.equals("Extra Big"))textSizeInt=30;
	        	
	        }   
	        
	        // creating connection detector class instance
	        cd = new ConnectionDetector(getApplicationContext());
	        
	        setContentView(R.layout.power);

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
	        	buildingRoomDict = RequestHelper.getRoomsAndBuildings(Power.this);

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
		            	
		            	// Display available computers in this room:
		            	String requestUrl = "http://service-teddy2012.rhcloud.com/log/" + buildingSelected 
		            			+ "/" + roomSelected;
		            	
		            	
		            	new GetPowerStatsTask().execute(requestUrl);
		            }

		            @Override
		            public void onNothingSelected(AdapterView<?> parentView) {
		                // your code here
		            }

		        });
		        
		        usage.setOnClickListener(new Button.OnClickListener(){
		        	public void onClick(View v){
			        	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
			        	i.putExtra("time", timeSelected);
						i.putExtra("text", textSize);
			        	finish();
			        	startActivity(i);
			        	overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
		        	}
		        });
		              
		        power.setOnClickListener(new Button.OnClickListener(){
		        public void onClick(View v){
		

		    	}
		        });
		        
		        stats.setOnClickListener(new Button.OnClickListener(){
		        	

					public void onClick(View v){
		
		            	Intent i = new Intent(getApplicationContext(), Stats.class);
		            	i.putExtra("time", timeSelected);
		    			i.putExtra("text", textSize);
		            	finish();
		            	startActivity(i);
		            	overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
		        	}
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
			Intent i;
			switch (item.getItemId()) {
				case R.id.video:
				i = new Intent(getApplicationContext(), About.class);
				startActivity(i);
	        	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
				return true;
				case R.id.settings:
				i = new Intent(getApplicationContext(), Settings.class);
				i.putExtra("time", timeSelected);
				i.putExtra("text", textSize);
	            finish();
	            startActivity(i);
		        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
				return true;
			    case R.id.instructions:
			    i = new Intent(getApplicationContext(), Instr.class);
			    i.putExtra("text", textSize);
				i.putExtra("id", "1");
				startActivity(i);
	        	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
			    return true;
			    case R.id.support:
			    i = new Intent(getApplicationContext(), Support.class);
				i.putExtra("text", textSize);
				startActivity(i);
			    finish();
	        	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
			    return true;
			    default:
			    return super.onOptionsItemSelected(item);
			}
		}
		
		// Get power stats json asynchronously
		private class GetPowerStatsTask extends AsyncTask<String, Void, JSONObject> {
			
			// Progress dialog
			ProgressDialog prog;
		    
			protected void onPreExecute() {
				prog = new ProgressDialog(Power.this);
			    prog.setTitle("Waiting...");
			    prog.setMessage("Getting data from the service");       
			    prog.setIndeterminate(false);
			    prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			    prog.show();
			}
		    
			protected JSONObject doInBackground(String... urls) {
				JsonParser parser = new JsonParser();
				
				JSONObject jObject = null;
				try {
					jObject = parser.getJSONFromUrl(urls[0]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return jObject;
			}
			

		     protected void onPostExecute(JSONObject result) {
		         System.out.println("Finished executing task");
		         
		         prog.hide();
		         
		         double powerCost = 0,idleCost=0;
            	 try {
            	    powerCost = result.getDouble("power_cost");
            		idleCost = result.getDouble("power_cost_idle");
            	 } catch (JSONException e) {
					// TODO Catch exception here if JSON is not formulated well
					e.printStackTrace();
            	 }	
            	 TextView powerTextView = (TextView) findViewById(R.id.powertext);
            	 powerTextView.setText(String.format("The power consuption for the last 15 minutes cost: %.2f GBP.",powerCost));
            	 powerTextView.setTextSize(textSizeInt);
            	
            	 TextView idleTextView = (TextView) findViewById(R.id.idleText);
            	 idleTextView.setText(String.format("Possible savings: %.2f GBP.",idleCost));
            	 idleTextView.setTextSize(textSizeInt);
            	 
            	 TextView calcTextView = (TextView) findViewById(R.id.calcText);
            	 calcTextView.setText(String.format("The power cost was calculated in relation with the folowing factors:\n\nNumber of machines: ___\n" +
            	 		"Cost per KWt: ___\netc"));
            	 calcTextView.setTextSize(textSizeInt);
		     }
		}
}



