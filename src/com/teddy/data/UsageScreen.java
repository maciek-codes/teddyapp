package com.teddy.data;

<<<<<<< HEAD
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

>>>>>>> ee5623c248445e5f9f3e204fc77deb164a2e2b45
import android.app.Activity;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
public class UsageScreen extends Activity  {

	
	String selectedFrom, selectedFrom2;
=======

import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
public class UsageScreen extends Activity  {
	// Connection detector class
	ConnectionDetector cd;

	// Remember list of buildings and rooms associated
	Map<String, ArrayList<String>> buildingRoomDict;
	
	String buildingSelected, roomSelected;
>>>>>>> ee5623c248445e5f9f3e204fc77deb164a2e2b45
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
<<<<<<< HEAD
        setContentView(R.layout.usagescreen);
=======
        // creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());
        
        setContentView(R.layout.usagescreen);

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

>>>>>>> ee5623c248445e5f9f3e204fc77deb164a2e2b45
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons
                
        Button usage =(Button) findViewById(R.id.usagebutton);
<<<<<<< HEAD
        usage.setText("Computer Usage");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Statistics");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power Usage");
             
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
        //Spinners
        
	
        final Spinner build = (Spinner) findViewById(R.id.building_spinner);
        ArrayAdapter<CharSequence> adapterbuild = ArrayAdapter.createFromResource(this, R.array.building_array, android.R.layout.simple_spinner_item);
        adapterbuild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        build.setAdapter(adapterbuild);
        selectedFrom =(String) (build.getItemAtPosition(0));
                
        build.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	//TextView text = (TextView ) findViewById(R.id.usagetext);
				selectedFrom =(String) (build.getItemAtPosition(position));
				//text.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
				showlist(selectedFrom,selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        final Spinner room = (Spinner) findViewById(R.id.room_spinner);
        ArrayAdapter<CharSequence> adapterroom = ArrayAdapter.createFromResource(this, R.array.room_array, android.R.layout.simple_spinner_item);
        adapterroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(adapterroom);
        selectedFrom2 =(String) (room.getItemAtPosition(0));
        //TextView text0 = (TextView ) findViewById(R.id.usagetext);
		//text0.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
                
        room.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	//TextView text2 = (TextView ) findViewById(R.id.usagetext);
				selectedFrom2 =(String) (room.getItemAtPosition(position));
				//text2.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
			    showlist(selectedFrom,selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons click
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
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Options/ menu
	
	public void showlist(String selected,String selected2)
	{
		
				
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//HTTP req
		String T="null";
		try { T = (String)HTTPfunction("http://service-teddy2012.rhcloud.com/log"); }
		catch(Exception e){ T="No internet connection"; }
		//TextView hp = (TextView ) findViewById(R.id.htt);
		//hp.setText("http: "+"ok");
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		//list
		final String[] A ={"No data"};
		if(!T.contains("null")&& selected.contains("MVB") && selected2.contains("All") ) A[0]=new String(T); 
		ListView list = (ListView)findViewById(R.id.usagelist);
		
		ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this,   android.R.layout.simple_list_item_1, A);
		// ArrayAdapter<CharSequence> adapterlist = ArrayAdapter.createFromResource(this,R.array.test, android.R.layout.simple_spinner_item);
		adapterlist.setDropDownViewResource(android.R.layout.simple_list_item_1);
		list.setAdapter(adapterlist);
		list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
		
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			Intent i = new Intent(getApplicationContext(), Info.class);
			i.putExtra("info", A[arg2]);
        	startActivity(i);
		
		}
		});
	}
=======
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
	            	
	            	// Display available computers in this room:
	            	String requestUrl = "http://service-teddy2012.rhcloud.com/" + buildingSelected 
	            			+ "/" + roomSelected + "/available";
	            	JSONObject jObject = parser.getJSONFromUrl(requestUrl);
	            	int numberOfAvaliable = 0;
	            	try {
	            		numberOfAvaliable = jObject.getInt("number");
					} catch (JSONException e) {
						// TODO Catch exception here if json is not formulated well
						e.printStackTrace();
					}
	            	TextView text2 = (TextView) findViewById(R.id.usagetext);
	            	text2.setText(String.format("There are %d computers avaliable in %s, %s",
	            			numberOfAvaliable, roomSelected, buildingSelected));
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
        else {
        	System.out.println("No Internet Connection");
        }  
	}

>>>>>>> ee5623c248445e5f9f3e204fc77deb164a2e2b45
	
	
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
<<<<<<< HEAD
	
	
	public String HTTPfunction(String getURL) {
		try {
		    HttpClient client = new DefaultHttpClient();  
		    HttpGet get = new HttpGet(getURL);
		    HttpResponse responseGet = client.execute(get);  
		    HttpEntity resEntityGet = responseGet.getEntity();  
		    String response="No internet connection";
		    if (resEntityGet != null) {  
		        // do something with the response
		        response = EntityUtils.toString(resEntityGet);
		        //Log.i("GET RESPONSE", response);
		        return response;
		    }
		} catch (Exception e) {
		    //e.printStackTrace();
		    System.out.println(e.getMessage());
		}
	     return "No internet connection";
		    }
	}
=======
}
>>>>>>> ee5623c248445e5f9f3e204fc77deb164a2e2b45
