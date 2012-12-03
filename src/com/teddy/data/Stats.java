package com.teddy.data;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Stats extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.stats);
        
        TextView text = (TextView ) findViewById(R.id.statstext);
        text.setText("Stats Screen");
        
        Button usage =(Button) findViewById(R.id.usagebutton2);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton2);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton2);
        power.setText("Power");
        
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	startActivity(i);
        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

        	Intent i = new Intent(getApplicationContext(), Power.class);
        	startActivity(i);
    	}
    });
        
        stats.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	
        	}
        });
              
	}
}