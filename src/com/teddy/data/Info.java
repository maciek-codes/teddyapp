package com.teddy.data;

import android.view.WindowManager;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;

public class Info extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.info);
        
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        Bundle extras = getIntent().getExtras(); 
        if(extras !=null)
        {
        	String info = extras.getString("info");
        	TextView text = (TextView ) findViewById(R.id.infotext);
            text.setText(info);
        }            
        
	}
}