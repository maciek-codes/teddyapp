package com.teddy.data;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import android.view.WindowManager;

import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Instr extends Activity {

    int sessionId;
    static String textSize="Medium";
	static int textSizeInt=16;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        String value="1";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("id");
            textSize = extras.getString("text");
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
            	finish();
            	startActivity(i);
            	if(translation.equals("1")) overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            	if(translation.equals("2")) overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
            	
        	}
        });
             
	}
}