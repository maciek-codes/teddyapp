package com.teddy.data;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;


public class Instr extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.instr);
        
        TextView text = (TextView ) findViewById(R.id.instrtext);
        text.setText("Instructions Screen");
	}
}