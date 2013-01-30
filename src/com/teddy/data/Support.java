package com.teddy.data;

import android.view.View;
import android.view.WindowManager;
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
        
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        TextView text = (TextView ) findViewById(R.id.supporttext);
        text.setText("Support");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        content.setText("Support: uob.ibm.teddy@gmail.com \n\n Application Developed by:\n\n Cristian Cernatescu \n Alexandru Dumitrescu \n Maciej Kumorek \n Christa Mpundu \n Aankhi Mukherjee \n Ioannis Troumpis \n\n\n\n in colaboration with :");
	
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
        	}
        });
	}
	
	@Override
    public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
    	finish();
    	startActivity(i);
    }
}