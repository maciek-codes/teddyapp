package com.teddy.data;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

public class Info extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.info);
        
        Bundle extras = getIntent().getExtras(); 
        if(extras !=null)
        {
        	String info = extras.getString("info");
        	TextView text = (TextView ) findViewById(R.id.infotext);
            text.setText(info);
        }
        
	
              
        
	}
}