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
import android.widget.ListView;
import android.widget.TextView;
public class Stats extends Activity {


    public String selectedFromList,selectedFromList2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.stats);
        
        TextView text = (TextView ) findViewById(R.id.statstext);
        text.setText("Stats Screen");
        
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
        
 /////////////////////////////////////       
        final ListView test = (ListView)findViewById(R.id.list1);

        ArrayAdapter<CharSequence> adaptertest = ArrayAdapter.createFromResource(this, R.array.test, android.R.layout.simple_spinner_item);
        adaptertest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        test.setAdapter(adaptertest);
        test.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView text = (TextView ) findViewById(R.id.statstext);
				selectedFromList =(String) (test.getItemAtPosition(arg2));
				text.setText("The list was clicked: "+selectedFromList);
				
			}
        });
        
        final ListView test2 = (ListView)findViewById(R.id.list2);

        ArrayAdapter<CharSequence> adaptertest2 = ArrayAdapter.createFromResource(this, R.array.test2, android.R.layout.simple_spinner_item);
        adaptertest2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        test2.setAdapter(adaptertest2);
        test2.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				TextView text2 = (TextView ) findViewById(R.id.statstext);
				selectedFromList2 =(String) (test2.getItemAtPosition(arg2));
				text2.setText("The list was clicked: "+selectedFromList+" "+selectedFromList2);
				
			}
        });
        
              
        ////////////////////
        
        usage.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	Intent i = new Intent(getApplicationContext(),  UsageScreen.class);
            	finish();
            	startActivity(i);
        	}
        });
              
        power.setOnClickListener(new Button.OnClickListener(){
        public void onClick(View v){

        	Intent i = new Intent(getApplicationContext(), Power.class);
        	finish();
        	startActivity(i);
    	}
    });
        
        stats.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v){

            	
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