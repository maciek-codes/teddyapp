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

	static String timeSelected="15 Minutes";
	static String textSize ="Medium";
	static String textColor ="White";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras(); 
        if(extras !=null)
        {
        	timeSelected = extras.getString("time");
        	textSize = extras.getString("text");
        	textColor = extras.getString("color");
        	
        }     
        setContentView(R.layout.settings);
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
            	Intent i = new Intent(getApplicationContext(), UsageScreen.class);
            	i.putExtra("time",timeSelected );
            	i.putExtra("text",textSize );
            	i.putExtra("color",textColor );
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
    	TextView sizeTextView = (TextView) findViewById(R.id.textSize);
    	sizeTextView.setText(String.format("Text Size"));
    	TextView sizeColorView = (TextView) findViewById(R.id.textColor);
    	sizeColorView.setText(String.format("Text Color"));
    	
    	final Spinner time = (Spinner) findViewById(R.id.time_spinner);
    	final Spinner text = (Spinner) findViewById(R.id.textsize_spinner);
    	final Spinner color = (Spinner) findViewById(R.id.textcolor_spinner);
        
        // Set resources
    	final ArrayAdapter<CharSequence> adaptertime = ArrayAdapter.createFromResource(this, R.array.time_array, android.R.layout.simple_spinner_item);
        adaptertime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adaptertime);
        
        final ArrayAdapter<CharSequence> adaptertext = ArrayAdapter.createFromResource(this, R.array.text_array, android.R.layout.simple_spinner_item);
        adaptertext.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text.setAdapter(adaptertext);

        final ArrayAdapter<CharSequence> adaptercolor = ArrayAdapter.createFromResource(this, R.array.textcolor_array, android.R.layout.simple_spinner_item);
        adaptercolor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(adaptercolor);
        
        if(timeSelected.equals("Off"))time.setSelection(0);
        else if(timeSelected.equals("15 Minutes"))time.setSelection(1);
        else if(timeSelected.equals("1 Hour"))time.setSelection(2);
        else if(timeSelected.equals("6 Hours"))time.setSelection(3);
        else if(timeSelected.equals("Daily"))time.setSelection(4);
        
        if(textSize.equals("Small"))text.setSelection(0);
        else if(textSize.equals("Medium"))text.setSelection(1);
        else if(textSize.equals("Big"))text.setSelection(2);
        else if(textSize.equals("Extra Big"))text.setSelection(3);
        
        if(textColor.equals("White"))color.setSelection(0);
        else if(textColor.equals("Black"))color.setSelection(1);
        else if(textColor.equals("Red"))color.setSelection(2);
        else if(textColor.equals("Blue"))color.setSelection(3);
        else if(textColor.equals("Green"))color.setSelection(4);
        else if(textColor.equals("Yellow"))color.setSelection(5);
        else if(textColor.equals("Orange"))color.setSelection(6);
       
        
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
        
        text.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	            	
            	// Get new selection
            	textSize =(String) (text.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        color.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	            	
            	// Get new selection
            	textColor =(String) (color.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
	} 
  	      
	
      @Override
      public void onBackPressed() {
  		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
  		i.putExtra("time",timeSelected );
    	i.putExtra("text",textSize );
    	i.putExtra("color",textColor );
    	finish();
      	startActivity(i);
      	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
      }
}