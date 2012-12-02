package com.teddy.data;

import android.widget.TextView;

import android.app.Activity;
import android.os.Bundle;


public class Stats extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView text = new TextView(this);
        text.setText("This is the stats screen");
        setContentView(text);
	}
}
