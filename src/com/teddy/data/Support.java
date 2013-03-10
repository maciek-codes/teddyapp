package com.teddy.data;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;


public class Support extends Activity {

	static String textSize="Medium";
	static int textSizeInt=16;
	static String textColor="White";
	static String textBkcolor ="Grey";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textSize = extras.getString("text");
        	textBkcolor = extras.getString("bkcolor");
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
        else if(textColor.equals("Grey"))content.setTextColor(getResources().getColor(R.color.grey));
		
		int colors[] = null;
     	View mlayout= findViewById(R.id.mainlayout);
     	
    	if(textBkcolor.equals("White"))colors = new int[]{Color.rgb(255,255,255),Color.rgb(255,250,240)};
        else if(textBkcolor.equals("Black"))colors = new int[]{Color.rgb(69,69,69),Color.rgb(0,0,0)};
        else if(textBkcolor.equals("Red")) colors = new int[]{Color.rgb(238,48,48),Color.rgb(205,0,0)};
        else if(textBkcolor.equals("Blue")) colors = new int[]{Color.rgb(72,118,255),Color.rgb(39,64,139)};
        else if(textBkcolor.equals("Green")) colors = new int[]{Color.rgb(154,255,154),Color.rgb(0,139,69)};
        else if(textBkcolor.equals("Yellow")) colors = new int[]{Color.rgb(255,236,139),Color.rgb(238,173,14)};
        else if(textBkcolor.equals("Orange")) colors = new int[]{Color.rgb(255,165,75),Color.rgb(255,127,0)};
        else if(textBkcolor.equals("Grey"))  colors = new int[]{Color.rgb(123,123,123),Color.rgb(50,50,50)};
        
    	GradientDrawable grDr =new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        grDr.setCornerRadius(5);
        grDr.setGradientCenter((float) 0.5,0.5f);
        grDr.setGradientRadius(300);
        grDr.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        //grDr.setStroke(1,Color.WHITE);
        mlayout.setBackgroundDrawable(grDr);
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
        
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
    
        
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

        		Button hbutton= (Button)findViewById(R.id.homebutton);
                hbutton.setBackgroundResource(R.drawable.button_pressed);
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