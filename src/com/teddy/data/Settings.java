package com.teddy.data;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.os.Bundle;

public class Settings extends Activity {

	static String timeSelected="Off";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
            	Intent i = new Intent(getApplicationContext(), UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
            	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });  
        
        TextView titleTextView = (TextView) findViewById(R.id.titleText);
    	titleTextView.setText(String.format("Settings"));
        TextView notifTextView = (TextView) findViewById(R.id.notifText);
    	notifTextView.setText(String.format("Notifications"));
    	
    	final Spinner time = (Spinner) findViewById(R.id.time_spinner);
        
        // Set resources
    	final ArrayAdapter<CharSequence> adaptertime = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
        adaptertime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adaptertime);
        
        time.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	            	
            	// Get new selection
            	timeSelected =(String) (time.getItemAtPosition(position));    
            	
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
	} 
  	     
	public String getTextFieldString() {
	      return timeSelected;
	  }     
	
      @Override
      public void onBackPressed() {
  		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
      	finish();
      	startActivity(i);
      	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
      }
}