package com.teddy.data;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class Support extends Activity {

	static String textSize="Medium";
	static int textSizeInt=16;
	static String textColor="White";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textSize = extras.getString("text");

            textColor = extras.getString("color");
        	if(textSize.equals("Small"))textSizeInt=14;
            else if(textSize.equals("Medium"))textSizeInt=16;
            else if(textSize.equals("Big"))textSizeInt=20;
            else if(textSize.equals("Extra Big"))textSizeInt=30;
            
        }
        
        setContentView(R.layout.support);
        
        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        TextView text = (TextView ) findViewById(R.id.supporttext);
        text.setText("Support");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        content.setText("\n\n\nSupport: uob.ibm.teddy@gmail.com \n\n\n Application Developed by:\n\n Cristian Cernatescu \n Alexandru Dumitrescu \n Maciej Kumorek \n Christa Mpundu \n Aankhi Mukherjee \n Ioannis Troumpis \n\n\n\n in colaboration with :");
        content.setTextSize(textSizeInt);
        content.setMovementMethod(new ScrollingMovementMethod());
        

		if(textColor.equals("White"))content.setTextColor(getResources().getColor(R.color.white));
        else if(textColor.equals("Black"))content.setTextColor(getResources().getColor(R.color.black));
        else if(textColor.equals("Red"))content.setTextColor(getResources().getColor(R.color.red));
        else if(textColor.equals("Blue"))content.setTextColor(getResources().getColor(R.color.blue));
        else if(textColor.equals("Green"))content.setTextColor(getResources().getColor(R.color.green));
        else if(textColor.equals("Yellow"))content.setTextColor(getResources().getColor(R.color.yellow));
        else if(textColor.equals("Orange"))content.setTextColor(getResources().getColor(R.color.orange));
		
        
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
    
        
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){


        		/*AlertDialog.Builder builder = new AlertDialog.Builder(Support.this);
        		 builder.setMessage("Are you sure you want to exit?")
        		        .setCancelable(false)
        		        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        		            public void onClick(DialogInterface dialog, int id) {
        		            }
        		        })
        		        .setNegativeButton("No", new DialogInterface.OnClickListener() {
        		            public void onClick(DialogInterface dialog, int id) {
        		                 dialog.cancel();
        		            }
        		        });
        		 
        		 AlertDialog alert = builder.create();
        		 alert.show();
        		*/
        		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
            	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });
	}
	
	@Override
    public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
    	finish();
    	startActivity(i);
    	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
}