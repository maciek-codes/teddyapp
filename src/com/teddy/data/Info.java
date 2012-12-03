package com.teddy.data;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Info extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.info);
        
        TextView text = (TextView ) findViewById(R.id.infotext);
        text.setText("Info Screen");
	
              
        
	}
}