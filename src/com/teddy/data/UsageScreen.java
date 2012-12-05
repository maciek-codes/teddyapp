package com.teddy.data;

import android.app.Activity;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
public class UsageScreen extends Activity  {

	
	String selectedFrom, selectedFrom2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.usagescreen);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons
                
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
             
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
        //Spinners
        
        final Spinner build = (Spinner) findViewById(R.id.building_spinner);
        ArrayAdapter<CharSequence> adapterbuild = ArrayAdapter.createFromResource(this, R.array.building_array, android.R.layout.simple_spinner_item);
        adapterbuild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        build.setAdapter(adapterbuild);
        selectedFrom =(String) (build.getItemAtPosition(0));
                
        build.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	TextView text = (TextView ) findViewById(R.id.usagetext);
				selectedFrom =(String) (build.getItemAtPosition(position));
				text.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        final Spinner room = (Spinner) findViewById(R.id.room_spinner);
        ArrayAdapter<CharSequence> adapterroom = ArrayAdapter.createFromResource(this, R.array.room_array, android.R.layout.simple_spinner_item);
        adapterroom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        room.setAdapter(adapterroom);
        selectedFrom2 =(String) (room.getItemAtPosition(0));
        TextView text0 = (TextView ) findViewById(R.id.usagetext);
		text0.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
                
        room.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	TextView text2 = (TextView ) findViewById(R.id.usagetext);
				selectedFrom2 =(String) (room.getItemAtPosition(position));
				text2.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Buttons click
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

        	Intent i = new Intent(getApplicationContext(),  Power.class);
        	finish();
        	startActivity(i);
    	}
        });
        
        stats.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(), Stats.class);
            	finish();
            	startActivity(i);
        	}
        });
        
        
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Options/ menu
	
	@Override
    public void onBackPressed() {
     finish();
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.usagemenu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		    case R.id.instructions:
		    startActivity(new Intent(this, Instr.class));
		    return true;
		    case R.id.support:
		    startActivity(new Intent(this, Support.class));
		    return true;
		    default:
		    return super.onOptionsItemSelected(item);
		}
	}
}