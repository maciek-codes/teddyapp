package com.teddy.data;

import android.view.View;
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
        
        TextView text = (TextView ) findViewById(R.id.instrtext);
        text.setText("Instructions");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        content.setText("Instructions to follow...");
	
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