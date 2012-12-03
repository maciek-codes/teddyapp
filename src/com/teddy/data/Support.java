package com.teddy.data;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Support extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.support);
        
        TextView text = (TextView ) findViewById(R.id.supporttext);
        text.setText("Support Screen");
	
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	startActivity(i);
        	}
        });
              
        
	}
}