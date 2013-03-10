package com.teddy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
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
		static String textColor="White";
		static String textBkcolor ="Grey";

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
	        	textColor = extras.getString("color");
	        	textBkcolor = extras.getString("bkcolor");
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
						i.putExtra("color", textColor);
		    	    	i.putExtra("bkcolor",textBkcolor );
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
						i.putExtra("color", textColor);
		    	    	i.putExtra("bkcolor",textBkcolor );
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
				i.putExtra("color", textColor);
    	    	i.putExtra("bkcolor",textBkcolor );
	            finish();
	            startActivity(i);
		        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
				return true;
			    case R.id.instructions:
			    i = new Intent(getApplicationContext(), Instr.class);
			    i.putExtra("text", textSize);
				i.putExtra("id", "1");
				i.putExtra("color", textColor);
    	    	i.putExtra("bkcolor",textBkcolor );
				startActivity(i);
	        	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
			    return true;
			    case R.id.support:
			    i = new Intent(getApplicationContext(), Support.class);
				i.putExtra("text", textSize);
				i.putExtra("color", textColor);
    	    	i.putExtra("bkcolor",textBkcolor );
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
					// TODO DONE1 Auto-generated catch block
					e.printStackTrace();
					android.os.Process.killProcess(android.os.Process.myPid());

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
					// TODO DONE1 Catch exception here if JSON is not formulated well
					e.printStackTrace();
					android.os.Process.killProcess(android.os.Process.myPid());

            	 }
            	 catch (NullPointerException e) {
            		 e.printStackTrace();
            	 }
            	 TextView powerTextView = (TextView) findViewById(R.id.powertext);
            	 powerTextView.setText(String.format("Power consumption costs for last\n15 minutes: %.2f GBP",powerCost));
            	 powerTextView.setTextSize(textSizeInt);

            	 if(textColor.equals("White"))powerTextView.setTextColor(getResources().getColor(R.color.white));
                 else if(textColor.equals("Black"))powerTextView.setTextColor(getResources().getColor(R.color.black));
                 else if(textColor.equals("Red"))powerTextView.setTextColor(getResources().getColor(R.color.red));
                 else if(textColor.equals("Blue"))powerTextView.setTextColor(getResources().getColor(R.color.blue));
                 else if(textColor.equals("Green"))powerTextView.setTextColor(getResources().getColor(R.color.green));
                 else if(textColor.equals("Yellow"))powerTextView.setTextColor(getResources().getColor(R.color.yellow));
                 else if(textColor.equals("Orange"))powerTextView.setTextColor(getResources().getColor(R.color.orange));
                 else if(textColor.equals("Grey"))powerTextView.setTextColor(getResources().getColor(R.color.grey));
            	
            	 TextView idleTextView = (TextView) findViewById(R.id.idleText);
            	 idleTextView.setText(String.format("Possible savings: %.2f GBP",idleCost));
            	 idleTextView.setTextSize(textSizeInt);


      			if(textColor.equals("White"))idleTextView.setTextColor(getResources().getColor(R.color.white));
                  else if(textColor.equals("Black"))idleTextView.setTextColor(getResources().getColor(R.color.black));
                  else if(textColor.equals("Red"))idleTextView.setTextColor(getResources().getColor(R.color.red));
                  else if(textColor.equals("Blue"))idleTextView.setTextColor(getResources().getColor(R.color.blue));
                  else if(textColor.equals("Green"))idleTextView.setTextColor(getResources().getColor(R.color.green));
                  else if(textColor.equals("Yellow"))idleTextView.setTextColor(getResources().getColor(R.color.yellow));
                  else if(textColor.equals("Orange"))idleTextView.setTextColor(getResources().getColor(R.color.orange));
                  else if(textColor.equals("Grey"))idleTextView.setTextColor(getResources().getColor(R.color.grey));
            	 
            	 TextView calcTextView = (TextView) findViewById(R.id.calcText);
            	 calcTextView.setText(String.format("The power cost was calculated in relation with the folowing factors:\nNumber of machines: - -\n" +
            	 		"Cost per kW: - -"));
            	 calcTextView.setTextSize(textSizeInt);

      			if(textColor.equals("White"))calcTextView.setTextColor(getResources().getColor(R.color.white));
                  else if(textColor.equals("Black"))calcTextView.setTextColor(getResources().getColor(R.color.black));
                  else if(textColor.equals("Red"))calcTextView.setTextColor(getResources().getColor(R.color.red));
                  else if(textColor.equals("Blue"))calcTextView.setTextColor(getResources().getColor(R.color.blue));
                  else if(textColor.equals("Green"))calcTextView.setTextColor(getResources().getColor(R.color.green));
                  else if(textColor.equals("Yellow"))calcTextView.setTextColor(getResources().getColor(R.color.yellow));
                  else if(textColor.equals("Orange"))calcTextView.setTextColor(getResources().getColor(R.color.orange));
                  else if(textColor.equals("Grey"))calcTextView.setTextColor(getResources().getColor(R.color.grey));
      			
      			int colors[] = null;
             	View mlayout= findViewById(R.id.mainlayout);
             	
            	if(textBkcolor.equals("White"))colors = new int[]{Color.rgb(255,255,255),Color.rgb(255,250,240)};
                else if(textBkcolor.equals("Black"))colors = new int[]{Color.rgb(69,69,69),Color.rgb(0,0,0)};
                else if(textBkcolor.equals("Red")) colors = new int[]{Color.rgb(238,48,48),Color.rgb(205,0,0)};
                else if(textBkcolor.equals("Blue")) colors = new int[]{Color.rgb(72,118,255),Color.rgb(39,64,139)};
                else if(textBkcolor.equals("Green")) colors = new int[]{Color.rgb(154,255,154),Color.rgb(0,139,69)};
                else if(textBkcolor.equals("Yellow")) colors = new int[]{Color.rgb(255,236,139),Color.rgb(238,173,14)};
                else if(textBkcolor.equals("Orange")) colors = new int[]{Color.rgb(255,165,75),Color.rgb(255,127,0)};
                else if(textBkcolor.equals("Grey"))  colors = new int[]{Color.rgb(123,123,123),Color.rgb(50,50,50)};
                
            	GradientDrawable grDr =new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
                grDr.setCornerRadius(5);
                grDr.setGradientCenter((float) 0.5,0.5f);
                grDr.setGradientRadius(300);
                grDr.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                //grDr.setStroke(1,Color.WHITE);
                mlayout.setBackgroundDrawable(grDr);
                Window window = getWindow();
                window.setFormat(PixelFormat.RGBA_8888);
		     }
		}
}



