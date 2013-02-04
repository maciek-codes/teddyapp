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

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.instr);
        

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
       
        TextView text = (TextView ) findViewById(R.id.instrtext);
        text.setText("Instructions");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        content.setText("Welcome to Teddy! \n\n1. Introduction\n\nThis app monitors usage of computers in different labs across University of Bristol.\n\n2. How to use\n (i) Available: This shows you the number of computers currently available for usage. Use the drop-down menus to select the building and room number. This is up-to-date over the last 15 minutes.\n\n (ii) Power Cost: This shows you the power costs for the last 15 minutes and the possible savings. Savings can be made because of the high number of idle computers, especially during off-peak time.\n\n (iii) Statistics: Statistics displays the power costs for different computer labs over a certain period of time. You can choose to view the data on a weekly, monthly or annual basis.\n");
        content.setMovementMethod(new ScrollingMovementMethod());
	
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        Button next =(Button) findViewById(R.id.nextbutton);
        next.setText("Next");
        
        Button back =(Button) findViewById(R.id.backbutton);
        back.setText("Back");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
        	}
        });
             
        next.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	finish();
            	startActivity(i);
        	}
        });
             
        back.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	finish();
            	startActivity(i);
        	}
        });
             
	}
}