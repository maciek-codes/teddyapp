package com.teddy.data;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Power extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.power);
        
        TextView text = (TextView ) findViewById(R.id.powertext);
        text.setText("Power Screen");
        
        Button usage =(Button) findViewById(R.id.usagebutton1);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton1);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton1);
        power.setText("Power");
        
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	startActivity(i);
        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

    	}
    });
        
        stats.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(), Stats.class);
            	startActivity(i);
        	}
        });
              
	}
}