package com.teddy.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
public class Stats extends Activity {

	
	String selectedFrom, selectedFrom2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        
        //String selectedFrom, selectedFrom2;
        setContentView(R.layout.stats);
        
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
		ArrayAdapter<CharSequence> adapterbuilds = ArrayAdapter.createFromResource(this, R.array.building_array, android.R.layout.simple_spinner_item);
		adapterbuilds.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		builds.setAdapter(adapterbuilds);
		selectedFrom =(String) (builds.getItemAtPosition(0));
		  
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
		selectedFrom2 =(String) (period.getItemAtPosition(0));
		//TextView text0 = (TextView ) findViewById(R.id.powertext);
		//text0.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
		  
		period.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			//TextView text2p = (TextView ) findViewById(R.id.powertext);
			selectedFrom2 =(String) (period.getItemAtPosition(position));
			//text2p.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
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
	final String[] A ={T};
	if(!T.contains("null")&& selected.contains("MVB") && selected2.contains("Day") ) A[0]=new String(T); 
	ListView list = (ListView)findViewById(R.id.statslist);
	
	ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, A);
	//ArrayAdapter<CharSequence> adapterlist = ArrayAdapter.createFromResource(this,R.array.period_array, android.R.layout.simple_spinner_item);
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