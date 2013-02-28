package com.teddy.data;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


public class Instr extends Activity {

    int sessionId;
    static String textSize="Medium";
	static int textSizeInt=16;
	static String textColor="White";
	static String textBkcolor ="Black";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        String value="1";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("id");
            textSize = extras.getString("text");
            textColor = extras.getString("color");
        	textBkcolor = extras.getString("bkcolor");
        	if(textSize.equals("Small"))textSizeInt=14;
            else if(textSize.equals("Medium"))textSizeInt=16;
            else if(textSize.equals("Big"))textSizeInt=20;
            else if(textSize.equals("Extra Big"))textSizeInt=30;
            
        }
        
        setContentView(R.layout.instr);
        

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
       
        TextView text = (TextView ) findViewById(R.id.instrtext);
        text.setText("Instructions");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        if(value.equals("1")) content.setText("Welcome to Teddy! \n\n\n\n1. Introduction\n\n\nThis app monitors usage of computers in different labs across University of Bristol.\n\n");
        else if(value.equals("2")) content.setText("\n\n2. How to use\n\n\n\n (i) Available: This shows you the number of computers currently available for usage. Use the drop-down menus to select the building and room number. This is up-to-date over the last 15 minutes.\n\n (ii) Power Cost: This shows you the power costs for the last 15 minutes and the possible savings. Savings can be made because of the high number of idle computers, especially during off-peak time.\n\n (iii) Statistics: Statistics displays the power costs for different computer labs over a certain period of time. You can choose to view the data on a weekly, monthly or annual basis.\n");
        content.setTextSize(textSizeInt);
        if(textColor.equals("White"))content.setTextColor(getResources().getColor(R.color.white));
        else if(textColor.equals("Black"))content.setTextColor(getResources().getColor(R.color.black));
        else if(textColor.equals("Red"))content.setTextColor(getResources().getColor(R.color.red));
        else if(textColor.equals("Blue"))content.setTextColor(getResources().getColor(R.color.ultrablue));
        else if(textColor.equals("Green"))content.setTextColor(getResources().getColor(R.color.green));
        else if(textColor.equals("Yellow"))content.setTextColor(getResources().getColor(R.color.yellow));
        else if(textColor.equals("Orange"))content.setTextColor(getResources().getColor(R.color.orange));
        
        View mlayout= findViewById(R.id.mainlayout);
    	// set the color 
    	if(textBkcolor.equals("White"))mlayout.setBackgroundColor(Color.WHITE);
        else if(textBkcolor.equals("Black"))mlayout.setBackgroundColor(Color.BLACK);
        else if(textBkcolor.equals("Red"))mlayout.setBackgroundColor(getResources().getColor(R.color.darkred));
        else if(textBkcolor.equals("Blue"))mlayout.setBackgroundColor(getResources().getColor(R.color.blue));
        else if(textBkcolor.equals("Green"))mlayout.setBackgroundColor(Color.GREEN);
        else if(textBkcolor.equals("Yellow"))mlayout.setBackgroundColor(getResources().getColor(R.color.ocru));
        else if(textBkcolor.equals("Orange"))mlayout.setBackgroundColor(getResources().getColor(R.color.lorange));
        
    	 View mlayout2= findViewById(R.id.mainlayout2);
     	// set the color 
     	if(textBkcolor.equals("White"))mlayout2.setBackgroundColor(Color.WHITE);
         else if(textBkcolor.equals("Black"))mlayout2.setBackgroundColor(Color.BLACK);
         else if(textBkcolor.equals("Red"))mlayout2.setBackgroundColor(getResources().getColor(R.color.darkred));
         else if(textBkcolor.equals("Blue"))mlayout2.setBackgroundColor(getResources().getColor(R.color.blue));
         else if(textBkcolor.equals("Green"))mlayout2.setBackgroundColor(Color.GREEN);
         else if(textBkcolor.equals("Yellow"))mlayout2.setBackgroundColor(getResources().getColor(R.color.ocru));
         else if(textBkcolor.equals("Orange"))mlayout2.setBackgroundColor(getResources().getColor(R.color.lorange));
        
        
        content.setMovementMethod(new ScrollingMovementMethod());
	
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        Button next =(Button) findViewById(R.id.nextbutton);
        next.setText("Next");
        
        Button back =(Button) findViewById(R.id.backbutton);
        back.setText("Back");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
            	Intent i = new Intent(getApplicationContext(), UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
            	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });
        final String translation=value;
        next.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	if(translation.equals("1")) i.putExtra("id", "2");
            	if(translation.equals("2")) i.putExtra("id", "2");
            	i.putExtra("text", "textSize");
    			i.putExtra("color", textColor);
		    	i.putExtra("bkcolor",textBkcolor );

            	finish();
            	startActivity(i);
            	if(translation.equals("1"))overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out); 

            	if(translation.equals("2"))overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });
             
        back.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	if(translation.equals("1")) i.putExtra("id", "1");
            	if(translation.equals("2")) i.putExtra("id", "1");
            	i.putExtra("text", "textSize");
    			i.putExtra("color", textColor);
		    	i.putExtra("bkcolor",textBkcolor );
            	finish();
            	startActivity(i);
            	if(translation.equals("1")) overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            	if(translation.equals("2")) overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
            	
        	}
        });
             
	}
}