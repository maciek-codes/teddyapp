package com.teddy.data;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;


public class Support extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.support);
        
        TextView text = (TextView ) findViewById(R.id.supporttext);
        text.setText("Support Screen");
	}
}