package com.teddy.data;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
public class Stats extends Activity {

	// Remember list of buildings and rooms associated
	Map<String, ArrayList<String>> buildingRoomDict;
		
	String selectedFrom, selectedFrom2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        
        //String selectedFrom, selectedFrom2;
        setContentView(R.layout.stats);
        
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //TextView text = (TextView ) findViewById(R.id.statstext);
        //text.setText("Stats Screen");
        
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
        
        Button uni =(Button) findViewById(R.id.unibutton);
        uni.setText("University");
        
		///////////////////////
        
    	
		final Spinner builds = (Spinner) findViewById(R.id.building_spinner);
		ArrayAdapter<CharSequence> adapterbuilds = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
		adapterbuilds.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		buildingRoomDict = RequestHelper.getRoomsAndBuildings();
        String[] buildings = new String[buildingRoomDict.keySet().size()];
        
        buildingRoomDict.keySet().toArray(buildings);
        
        for(int i = 0; i < buildings.length; ++i) {
        	
			String building = buildings[i];
		   
            // Add the building too
            adapterbuilds.add(building);
        }
		
		builds.setAdapter(adapterbuilds);
		
		if(builds.getCount() > 0)
			selectedFrom =(String) (builds.getItemAtPosition(0));
		else 
			return;
		
		builds.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			//TextView textp = (TextView ) findViewById(R.id.powertext);
			selectedFrom =(String) (builds.getItemAtPosition(position));
			//textp.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
			showlist(selectedFrom,selectedFrom2);
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> parentView) {
		  // your code here
		}
		
		});
		
		final Spinner period = (Spinner) findViewById(R.id.period_spinner);
		ArrayAdapter<CharSequence> adapterperiod = ArrayAdapter.createFromResource(this, R.array.period_array, android.R.layout.simple_spinner_item);
		adapterperiod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		period.setAdapter(adapterperiod);
		
		if(period.getCount() > 0)
			selectedFrom2 =(String) (period.getItemAtPosition(0));
		else
			return;
		
		period.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			// Check if there are any items at all
			if(period.getCount() <= 0)
				return;
			
			selectedFrom2 =(String) (period.getItemAtPosition(position));
			showlist(selectedFrom,selectedFrom2);
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> parentView) {
		  // your code here
		}
		
		});
              
        ////////////////////
        
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
        	public void onClick(View v){

            	
        	}
        });
        
        uni.setOnClickListener(new Button.OnClickListener(){
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
		    startActivity(new Intent(this, Support.class));
		    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Options/ menu
	
	public void showlist(String buildingName,  String periodName)	{

//		JsonParser parser = new JsonParser();
//		try { 
//			JSONObject jObject = parser.getJSONFromUrl("http://service-teddy2012.rhcloud.com/log"); }
//		catch(Exception e){
//			// TODO: Handle this exception
//		}
//	
//		// Get list view for stats
//		ListView list = (ListView)findViewById(R.id.statslist);
//		
//		// Set array adapter for the list
//		ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, A);
//		adapterlist.setDropDownViewResource(android.R.layout.simple_list_item_1);
//		list.setAdapter(adapterlist);
//		
//		list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {			
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
//				//Intent i = new Intent(getApplicationContext(), Info.class);
//				//i.putExtra("info", A[arg2]);
//				//startActivity(i);
//			}
//		});
	}
}