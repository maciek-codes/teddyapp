package com.teddy.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class UsageScreen extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usagescreen);
        Button bt =(Button) findViewById(R.id.statsbutton);
        bt.setText("hey");
        
        bt.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(), Stats.class);
            	startActivity(i);
        	}
        });
	}
}