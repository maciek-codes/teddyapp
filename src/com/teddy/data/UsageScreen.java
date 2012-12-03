package com.teddy.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class UsageScreen extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.usagescreen);
        
        TextView text = (TextView ) findViewById(R.id.usagetext);
        text.setText("Usage Screen");
        
        
        Button usage =(Button) findViewById(R.id.usagebutton);
        usage.setText("Comp");
        
        Button stats =(Button) findViewById(R.id.statsbutton);
        stats.setText("Stats");
        
        Button power =(Button) findViewById(R.id.powerbutton);
        power.setText("Power");
        
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
	
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
	//    startActivity(new Intent(this, Instr.class));
	 //   return(true);
	//}
	
	@Override
    public void onBackPressed() {
		 TextView text = (TextView ) findViewById(R.id.usagetext);
	        text.setText("close");
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