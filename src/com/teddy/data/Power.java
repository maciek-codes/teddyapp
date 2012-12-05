package com.teddy.data;

import android.app.Activity;
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
import android.widget.AdapterView.OnItemSelectedListener;

public class Power extends Activity {

	String selectedFrom, selectedFrom2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.power);
        
        TextView text = (TextView ) findViewById(R.id.powertext);
        text.setText("Power Screen");
        
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
        
        
///////////////////////
        /*final Spinner buildp = (Spinner) findViewById(R.id.building_spinner);
        ArrayAdapter<CharSequence> adapterbuildp = ArrayAdapter.createFromResource(this, R.array.building_array, android.R.layout.simple_spinner_item);
        adapterbuildp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildp.setAdapter(adapterbuildp);
        selectedFrom =(String) (buildp.getItemAtPosition(0));
                
        buildp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	TextView textp = (TextView ) findViewById(R.id.powertext);
				selectedFrom =(String) (buildp.getItemAtPosition(position));
				textp.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
        final Spinner roomp = (Spinner) findViewById(R.id.room_spinner);
        ArrayAdapter<CharSequence> adapterroomp = ArrayAdapter.createFromResource(this, R.array.room_array, android.R.layout.simple_spinner_item);
        adapterroomp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomp.setAdapter(adapterroomp);
        selectedFrom2 =(String) (roomp.getItemAtPosition(0));
        TextView text0 = (TextView ) findViewById(R.id.powertext);
		text0.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
                
        roomp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            	TextView text2p = (TextView ) findViewById(R.id.usagetext);
				selectedFrom2 =(String) (roomp.getItemAtPosition(position));
				text2p.setText("The list was clicked: "+selectedFrom+" "+selectedFrom2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });*/
        
        
//////////////////////
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	startActivity(i);
        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

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