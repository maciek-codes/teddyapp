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


public class Instr extends Activity {

    int sessionId;
    static String textSize="Medium";
	static int textSizeInt=16;
	static String textColor="White";
	static String textBkcolor ="Grey";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        String value="1";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("id");
            textSize = extras.getString("text");
            textColor = extras.getString("color");
        	textBkcolor = extras.getString("bkcolor");
        	if(textSize.equals("Small"))textSizeInt=14;
            else if(textSize.equals("Medium"))textSizeInt=16;
            else if(textSize.equals("Big"))textSizeInt=20;
            else if(textSize.equals("Extra Big"))textSizeInt=30;
            
        }
        
        setContentView(R.layout.instr);
        

        // Hide input keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
       
        TextView text = (TextView ) findViewById(R.id.instrtext);
        text.setText("Instructions");
        
        TextView content = (TextView ) findViewById(R.id.contenttext);
        if(value.equals("1")) content.setText("Welcome to Teddy! \n\n\n\n1. Introduction\n\n\nThis app monitors usage of computers in different labs across University of Bristol.\n\n");
        else if(value.equals("2")) content.setText("\n\n2. How to use\n\n\n\n (i) Available: This shows you the number of computers currently available for usage. Use the drop-down menus to select the building and room number. This is up-to-date over the last 15 minutes.\n\n (ii) Power Cost: This shows you the power costs for the last 15 minutes and the possible savings. Savings can be made because of the high number of idle computers, especially during off-peak time.\n\n (iii) Statistics: Statistics displays the power costs for different computer labs over a certain period of time. You can choose to view the data on a weekly, monthly or annual basis.\n");
        content.setTextSize(textSizeInt);
        if(textColor.equals("White"))content.setTextColor(getResources().getColor(R.color.white));
        else if(textColor.equals("Black"))content.setTextColor(getResources().getColor(R.color.black));
        else if(textColor.equals("Red"))content.setTextColor(getResources().getColor(R.color.red));
        else if(textColor.equals("Blue"))content.setTextColor(getResources().getColor(R.color.ultrablue));
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
     	View mlayout2= findViewById(R.id.mainlayout2);
     	mlayout2.setBackgroundDrawable(grDr);
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
        
        content.setMovementMethod(new ScrollingMovementMethod());
	
        Button home =(Button) findViewById(R.id.homebutton);
        home.setText("Home");
        
        Button next =(Button) findViewById(R.id.nextbutton);
        next.setText("Next");
        
        Button back =(Button) findViewById(R.id.backbutton);
        back.setText("Back");
        
        home.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Button hbutton= (Button)findViewById(R.id.homebutton);
                hbutton.setBackgroundResource(R.drawable.button_pressed);
            	Intent i = new Intent(getApplicationContext(), UsageScreen.class);
            	finish();
            	i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            	startActivity(i);
            	overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });
        final String translation=value;
        next.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Button nbutton= (Button)findViewById(R.id.nextbutton);
                nbutton.setBackgroundResource(R.drawable.button_pressed);
            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	if(translation.equals("1")) i.putExtra("id", "2");
            	if(translation.equals("2")) i.putExtra("id", "2");
            	i.putExtra("text", "textSize");
    			i.putExtra("color", textColor);
		    	i.putExtra("bkcolor",textBkcolor );

            	finish();
            	startActivity(i);
            	if(translation.equals("1"))overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out); 

            	if(translation.equals("2"))overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        	}
        });
             
        back.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){
        		Button bbutton= (Button)findViewById(R.id.backbutton);
                bbutton.setBackgroundResource(R.drawable.button_pressed);
            	Intent i = new Intent(getApplicationContext(),  Instr.class);
            	if(translation.equals("1")) i.putExtra("id", "1");
            	if(translation.equals("2")) i.putExtra("id", "1");
            	i.putExtra("text", "textSize");
    			i.putExtra("color", textColor);
		    	i.putExtra("bkcolor",textBkcolor );
            	finish();
            	startActivity(i);
            	if(translation.equals("1")) overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            	if(translation.equals("2")) overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
            	
        	}
        });
             
	}
}