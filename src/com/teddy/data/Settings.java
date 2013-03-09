package com.teddy.data;

import android.content.Intent;
import android.graphics.Color;
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
	static String textBkcolor ="Grey";
	
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
        	
        }     
        setContentView(R.layout.settings);
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Button hbutton= (Button)findViewById(R.id.homebutton);
                hbutton.setBackgroundResource(R.drawable.button_pressed);
            	Intent i = new Intent(getApplicationContext(), UsageScreen.class);
            	i.putExtra("time",timeSelected );
            	i.putExtra("text",textSize );
            	i.putExtra("color",textColor );
            	i.putExtra("bkcolor",textBkcolor );
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
    	TextView BkColorView = (TextView) findViewById(R.id.textBkcolor);
    	BkColorView.setText(String.format("Background Color"));
    	
    	final Spinner time = (Spinner) findViewById(R.id.time_spinner);
    	final Spinner text = (Spinner) findViewById(R.id.textsize_spinner);
    	final Spinner color = (Spinner) findViewById(R.id.textcolor_spinner);
    	final Spinner bkcolor = (Spinner) findViewById(R.id.bkcolor_spinner);
        
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
        
        final ArrayAdapter<CharSequence> adapterbkcolor = ArrayAdapter.createFromResource(this, R.array.textcolor_array, android.R.layout.simple_spinner_item);
        adapterbkcolor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bkcolor.setAdapter(adapterbkcolor);
        
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
        else if(textColor.equals("Grey"))color.setSelection(6);
       

        if(textBkcolor.equals("White"))bkcolor.setSelection(0);
        else if(textBkcolor.equals("Black"))bkcolor.setSelection(1);
        else if(textBkcolor.equals("Red"))bkcolor.setSelection(2);
        else if(textBkcolor.equals("Blue"))bkcolor.setSelection(3);
        else if(textBkcolor.equals("Green"))bkcolor.setSelection(4);
        else if(textBkcolor.equals("Yellow"))bkcolor.setSelection(5);
        else if(textBkcolor.equals("Orange"))bkcolor.setSelection(6);
        else if(textBkcolor.equals("Grey"))bkcolor.setSelection(7);
        
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
            	TextView notifTextView = (TextView) findViewById(R.id.notifText);
            	TextView sizeTextView = (TextView) findViewById(R.id.textSize);
            	TextView sizeColorView = (TextView) findViewById(R.id.textColor);
            	TextView BkColorView = (TextView) findViewById(R.id.textBkcolor);
            	
            	if(textColor.equals("White")){
            		notifTextView.setTextColor(getResources().getColor(R.color.white));
            		sizeTextView.setTextColor(getResources().getColor(R.color.white));
            		sizeColorView.setTextColor(getResources().getColor(R.color.white));
            		BkColorView.setTextColor(getResources().getColor(R.color.white));
            	}
                else if(textColor.equals("Black")){
                	notifTextView.setTextColor(getResources().getColor(R.color.black));
                	sizeTextView.setTextColor(getResources().getColor(R.color.black));
                	sizeColorView.setTextColor(getResources().getColor(R.color.black));
                	BkColorView.setTextColor(getResources().getColor(R.color.black));
                }
                else if(textColor.equals("Red")){
                	notifTextView.setTextColor(getResources().getColor(R.color.red));
                	sizeTextView.setTextColor(getResources().getColor(R.color.red));
                	sizeColorView.setTextColor(getResources().getColor(R.color.red));
                	BkColorView.setTextColor(getResources().getColor(R.color.red));
                }
                else if(textColor.equals("Blue")){
                	notifTextView.setTextColor(getResources().getColor(R.color.ultrablue));
                	sizeTextView.setTextColor(getResources().getColor(R.color.ultrablue));
                	sizeColorView.setTextColor(getResources().getColor(R.color.ultrablue));
                	BkColorView.setTextColor(getResources().getColor(R.color.ultrablue));
                }
                else if(textColor.equals("Green")){
                	notifTextView.setTextColor(getResources().getColor(R.color.green));
                	sizeTextView.setTextColor(getResources().getColor(R.color.green));
                	sizeColorView.setTextColor(getResources().getColor(R.color.green));
                	BkColorView.setTextColor(getResources().getColor(R.color.green));
                }
                else if(textColor.equals("Yellow")){
                	notifTextView.setTextColor(getResources().getColor(R.color.yellow));
                	sizeTextView.setTextColor(getResources().getColor(R.color.yellow));
                	sizeColorView.setTextColor(getResources().getColor(R.color.yellow));
                	BkColorView.setTextColor(getResources().getColor(R.color.yellow));
                }
                else if(textColor.equals("Orange")){
                	notifTextView.setTextColor(getResources().getColor(R.color.orange));
                	sizeTextView.setTextColor(getResources().getColor(R.color.orange));
                	sizeColorView.setTextColor(getResources().getColor(R.color.orange));
                	BkColorView.setTextColor(getResources().getColor(R.color.orange));
                }
                else if(textColor.equals("Grey")){
                	notifTextView.setTextColor(getResources().getColor(R.color.grey));
                	sizeTextView.setTextColor(getResources().getColor(R.color.grey));
                	sizeColorView.setTextColor(getResources().getColor(R.color.grey));
                	BkColorView.setTextColor(getResources().getColor(R.color.grey));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        bkcolor.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	            	
            	// Get new selection
            	textBkcolor =(String) (bkcolor.getItemAtPosition(position));
            	View mlayout= findViewById(R.id.mainlayout);
            	// set the color 
            	if(textBkcolor.equals("White"))mlayout.setBackgroundColor(Color.WHITE);
                else if(textBkcolor.equals("Black"))mlayout.setBackgroundColor(Color.BLACK);
                else if(textBkcolor.equals("Red"))mlayout.setBackgroundColor(getResources().getColor(R.color.darkred));
                else if(textBkcolor.equals("Blue"))mlayout.setBackgroundColor(getResources().getColor(R.color.blue));
                else if(textBkcolor.equals("Green"))mlayout.setBackgroundColor(Color.GREEN);
                else if(textBkcolor.equals("Yellow"))mlayout.setBackgroundColor(getResources().getColor(R.color.ocru));
                else if(textBkcolor.equals("Orange"))mlayout.setBackgroundColor(getResources().getColor(R.color.lorange));
                else if(textBkcolor.equals("Grey"))mlayout.setBackgroundColor(getResources().getColor(R.color.dgrey));
            	
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
    	i.putExtra("bkcolor",textBkcolor );
    	finish();
      	startActivity(i);
      	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
      }
}