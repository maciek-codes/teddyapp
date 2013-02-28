package com.teddy.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
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

public class UsageScreen extends Activity  {
	// Connection detector class
	ConnectionDetector cd;

	// Remember list of buildings and rooms associated
	Map<String, ArrayList<String>> buildingRoomDict;
	
	String buildingSelected, roomSelected;
	static String timeSelected="15 Minutes";
	static String textSize="Medium";
	static int textSizeInt=16;
	static String textColor="White";
	static int count=0;
	static String textBkcolor ="Black";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usagescreen);
    }
	
	
	@Override
    public void onStart(){
        super.onStart();
    	 
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
        	buildingRoomDict = RequestHelper.getRoomsAndBuildings(UsageScreen.this);

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
	            	String requestUrl = "http://service-teddy2012.rhcloud.com/" + buildingSelected 
	            			+ "/" + roomSelected + "/available";
	            	
	            	// Get data from server
	            	new GetUsageStatsTask().execute(requestUrl);
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
	        	i.putExtra("time", timeSelected);
				i.putExtra("text", textSize);
				i.putExtra("color", textColor);
		    	i.putExtra("bkcolor",textBkcolor );
	        	finish();
	        	startActivity(i);
	        	overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out); 
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
		
		/*
		Context ctx = getApplicationContext();
		Intent i = null;
		
		int id = item.getItemId();
		if(id == R.id.video) {
				
				i = new Intent(ctx, About.class);
		} else if(id == R.id.settings) {
			
				i = new Intent(ctx, Settings.class);
				i.putExtra("time", timeSelected);
				i.putExtra("text", textSize);
		} else if(id == R.id.instructions) {
			
				i = new Intent(ctx, Instr.class);
			    i.putExtra("text", textSize);
				i.putExtra("id", "1");
		} else if(id == R.id.support) {
			
			i = new Intent(ctx, Support.class);
			    i.putExtra("text", textSize);
			    finish();
		} else {
		    	return super.onOptionsItemSelected(item);
		}
		
		startActivity(i);
		overridePendingTransition(R.anim.fadein,R.anim.fadeout);
	    return true;*/
	
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
	
	
	// Get power statistics JSON asynchronously
	@SuppressLint("NewApi")
	private class GetUsageStatsTask extends AsyncTask<String, Void, JSONObject> {
		
		// Progress dialog
		ProgressDialog prog;
	    
		protected void onPreExecute() {
			prog = new ProgressDialog(UsageScreen.this);
		    prog.setTitle("Waiting...");
		    prog.setMessage("Retrieving data from the service.");       
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
	    	 prog.hide();
	    	 
	    	 //{"busy": 3, "total_number": 79, "off_or_disconnected": 14, "busy_but_idle": 2, "date": "2013-02-16T14:30:06", "number_avaliable": 52}
	    	 
	    	 int numberOfAvaliable = 0;
	    	 int offOrDisconected = 0;
	    	 int busy = 0;
	    	 int busyButIdle = 0;
	    	 int used = 0;
	    	 Long time = System.currentTimeMillis();
	    	 String timestamp = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss").format(new java.util.Date(time));
	    	 
         	 try {
         		 numberOfAvaliable = result.getInt("number_avaliable");
         		 offOrDisconected = result.getInt("off_or_disconnected");
         		 busy = result.getInt("busy");
         		 busyButIdle = result.getInt("busy_but_idle");
         		 used = busy+busyButIdle;
         	 } catch (JSONException e) {
         		 // TODO DONE1 Catch exception here if JSON is not formulated well
         		 e.printStackTrace();
     			android.os.Process.killProcess(android.os.Process.myPid());

         	 }
         	catch (NullPointerException e) {   //exception
                
	         		            		
         		e.printStackTrace();
    			android.os.Process.killProcess(android.os.Process.myPid());

            }
         	 
         	 TextView usageTextView = (TextView) findViewById(R.id.usagetext);
         	 usageTextView.setText(String.format("%d available computers\n\n%d turned off or disconnected computers\n\n%d computers being used of which\n      - %d active\n      - %d idle", numberOfAvaliable,offOrDisconected,used,busy,busyButIdle));
         	 usageTextView.setTextSize(textSizeInt);
         	 
         	if(textColor.equals("White"))usageTextView.setTextColor(getResources().getColor(R.color.white));
            else if(textColor.equals("Black"))usageTextView.setTextColor(getResources().getColor(R.color.black));
            else if(textColor.equals("Red"))usageTextView.setTextColor(getResources().getColor(R.color.red));
            else if(textColor.equals("Blue"))usageTextView.setTextColor(getResources().getColor(R.color.ultrablue));
            else if(textColor.equals("Green"))usageTextView.setTextColor(getResources().getColor(R.color.green));
            else if(textColor.equals("Yellow"))usageTextView.setTextColor(getResources().getColor(R.color.yellow));
            else if(textColor.equals("Orange"))usageTextView.setTextColor(getResources().getColor(R.color.orange));
         	
         	View mlayout= findViewById(R.id.mainlayout);
        	// set the color 
        	if(textBkcolor.equals("White"))mlayout.setBackgroundColor(Color.WHITE);
            else if(textBkcolor.equals("Black"))mlayout.setBackgroundColor(Color.BLACK);
            else if(textBkcolor.equals("Red"))mlayout.setBackgroundColor(getResources().getColor(R.color.darkred));
            else if(textBkcolor.equals("Blue"))mlayout.setBackgroundColor(getResources().getColor(R.color.blue));
            else if(textBkcolor.equals("Green"))mlayout.setBackgroundColor(Color.GREEN);
            else if(textBkcolor.equals("Yellow"))mlayout.setBackgroundColor(getResources().getColor(R.color.ocru));
            else if(textBkcolor.equals("Orange"))mlayout.setBackgroundColor(getResources().getColor(R.color.lorange));
        
         	 
         	
         	 
         	 ///////////--------------------------------------------
         	/*Intent intent = new Intent(UsageScreen.this, UsageScreen.class);
            //AlarmManager am = (AlarmManager) UsageScreen.this.getSystemService(Context.ALARM_SERVICE);
                        
            //PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent , PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pi);
            
            
            NotificationCompat.Builder builder =
         	        new NotificationCompat.Builder(getApplicationContext())
            		.setSmallIcon(R.drawable.logoteddy_2)
            		.setContentTitle("Teddy")
            		.setContentText("There are "+numberOfAvaliable +" computers available");
         			//.setWhen(System.currentTimeMillis())
         			//.setContentIntent(pi);
     	
         	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         	manager.notify(0, builder.build());*/
         	
            
            //-----------------------------------------------------------------
         	 Intent intent = new Intent(UsageScreen.this, UsageScreen.class);
             AlarmManager am = (AlarmManager) UsageScreen.this.getSystemService(Context.ALARM_SERVICE);
             
             PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, intent , PendingIntent.FLAG_UPDATE_CURRENT);
             if(timeSelected.equals("Off"))am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 0, pi);
         	 else if(timeSelected.equals("15 Minutes"))am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pi);
         	 else if(timeSelected.equals("1 Hour"))am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),AlarmManager.INTERVAL_HOUR , pi);
         	 else if(timeSelected.equals("6 Hours"))am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HOUR*6, pi);
         	 else if(timeSelected.equals("Daily"))am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),AlarmManager.INTERVAL_DAY , pi);
             
             UsageScreen.this.getSystemService(Context.NOTIFICATION_SERVICE);        
             PendingIntent contentIntent = PendingIntent.getActivity(UsageScreen.this, 0,new Intent(), 0);
             TextView timeStamp = (TextView) findViewById(R.id.timestamp);
         	 timeStamp.setText(String.format("Last refreshed at: %s", timestamp));
         	 timeStamp.setTextSize(textSizeInt);
         	 
         	 
          	if(textColor.equals("White"))timeStamp.setTextColor(getResources().getColor(R.color.white));
             else if(textColor.equals("Black"))timeStamp.setTextColor(getResources().getColor(R.color.black));
             else if(textColor.equals("Red"))timeStamp.setTextColor(getResources().getColor(R.color.red));
             else if(textColor.equals("Blue"))timeStamp.setTextColor(getResources().getColor(R.color.ultrablue));
             else if(textColor.equals("Green"))timeStamp.setTextColor(getResources().getColor(R.color.green));
             else if(textColor.equals("Yellow"))timeStamp.setTextColor(getResources().getColor(R.color.yellow));
             else if(textColor.equals("Orange"))timeStamp.setTextColor(getResources().getColor(R.color.orange));
                          
             
            /* NotificationCompat.Builder builder =
          	        new NotificationCompat.Builder(UsageScreen.this)
          	        .setSmallIcon(R.drawable.logoteddy_2)
          	        .setContentTitle("Teddy")
          	        .setContentText("There are "+numberOfAvaliable +" computers available")
          			.setWhen(System.currentTimeMillis()+10*1000)
          			.setContentIntent(pi);
             */
             
             Notification notif = new Notification(R.drawable.logoteddy_2, "There are "+numberOfAvaliable +" computers available", System.currentTimeMillis());
             notif.setLatestEventInfo(UsageScreen.this, "Teddy", "There are "+numberOfAvaliable +" computers available", contentIntent);
             /*NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
             nm.notify(1, builder.build());*/
     }
	}
}
